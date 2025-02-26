import os
import openai
import requests
import re
import json
import sys

# Load API keys
OPENAI_API_KEY = os.getenv("OPENAI_API_KEY")
GITHUB_TOKEN = os.getenv("GITHUB_TOKEN")

# Get PR number and repo from GitHub Actions
REPO = os.getenv("GITHUB_REPOSITORY")
# Extract PR number from GitHub event payload
GITHUB_EVENT_PATH = os.getenv("GITHUB_EVENT_PATH")

with open(GITHUB_EVENT_PATH, "r") as f:
    event_data = json.load(f)
    PR_NUMBER = event_data["pull_request"]["number"]

# Read PR diff
with open("pr_diff.txt", "r") as f:
    pr_diff = f.read()

# Extract changed Java files
changed_java_files = re.findall(r"diff --git a/(.*\.java) b/.*", pr_diff)

# Identify missing unit tests
java_classes = [file.replace(".java", "") for file in changed_java_files if "/test/" not in file]
test_classes = [file.replace(".java", "") for file in changed_java_files if "/test/" in file]

missing_tests = [
    cls for cls in java_classes if f"{cls}Test" not in test_classes and f"Test{cls}" not in test_classes
]

missing_tests_feedback = ""
if missing_tests:
    missing_tests_feedback = f"\n⚠️ **Missing Tests:** The following classes might need unit tests:\n" + "\n".join(
        f"- `{cls}.java`" for cls in missing_tests
    )

# Send diff to GPT for review
response = openai.ChatCompletion.create(
    model="gpt-4",
    messages=[
        {"role": "system", "content": "You are a senior Java code reviewer. Focus on best practices, clean code, and missing unit tests."},
        {"role": "user", "content": f"Review this Java code diff:\n\n{pr_diff}"}
    ]
)

review_comment = response["choices"][0]["message"]["content"] + missing_tests_feedback

# Post comment to GitHub PR
comment_url = f"https://api.github.com/repos/{REPO}/issues/{PR_NUMBER}/comments"
headers = {"Authorization": f"token {GITHUB_TOKEN}", "Accept": "application/vnd.github.v3+json"}
data = {"body": review_comment}

requests.post(comment_url, json=data, headers=headers)
