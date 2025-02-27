import os
import requests
import json

# Load API Key from GitHub Secrets
GEMINI_API_KEY = os.getenv("GEMINI_API_KEY")
PR_NUMBER = os.getenv("PR_NUMBER")
REPO = os.getenv("GITHUB_REPOSITORY") 
GITHUB_TOKEN = os.getenv("GH_PAT")

if not GEMINI_API_KEY:
    raise ValueError("❌ Missing Google Gemini API Key. Set GEMINI_API_KEY in GitHub Secrets.")

# Debug: Check API Key
print(f"🔹 Loaded API Key: {GEMINI_API_KEY[:5]}... (truncated)")

# API URL (pass API key in URL)
API_URL = f"https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key={GEMINI_API_KEY}"

# Get PR diff from GitHub Actions
pr_diff = os.getenv("PR_DIFF", "No PR diff found.")

# Request Payload
data = {
    "contents": [{
        "parts": [{"text": f"Review this Java PR:\n{pr_diff}"}]
    }]
}

# Make API Request
response = requests.post(API_URL, headers={"Content-Type": "application/json"}, data=json.dumps(data))

# Debug Response
# print(f"🔹 API Response: {response.status_code}")
# print(response.text)

if response.status_code == 200:
    result = response.json()
    print("### PR Review Feedback ###", result)
    review_feedback = result["candidates"][0]["content"]["parts"][0]["text"]
    print("### PR Review Feedback ###")
    print(review_feedback)
else:
    print(f"❌ API Error {response.status_code}: {response.text}")
    review_feedback = "❌ Error fetching review from Gemini."

# Post Review Comment on PR
GITHUB_API_URL = f"https://api.github.com/repos/{REPO}/issues/{PR_NUMBER}/comments"
headers = {"Authorization": f"Bearer {GITHUB_TOKEN}", "Accept": "application/vnd.github.v3+json"}
payload = {"body": f"🤖 **Automated Code Review by Gemini AI:**\n\n{review_feedback}"}
print("API URL:", GITHUB_API_URL, headers)
print("Token:", GITHUB_TOKEN)
comment_response = requests.post(GITHUB_API_URL, headers=headers, json=payload)

# Debug GitHub API Response
if comment_response.status_code == 201:
    print("✅ PR Review Comment Posted Successfully!")
else:
    print(f"❌ Failed to Post Comment: {comment_response.text}")

# Parse Response
# if response.status_code == 200:
#     result = response.json()
#     print("### PR Review Feedback ###")
#     print(result["candidates"][0]["content"]["parts"][0]["text"])
# else:
#     print(f"❌ API Error {response.status_code}: {response.text}")
