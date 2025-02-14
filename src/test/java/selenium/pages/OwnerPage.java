package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.TestBase;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class OwnerPage extends TestBase {

	/**
	 * Constructs an OwnerPage instance by initializing the WebDriver and locator properties.
	 *
	 * <p>This constructor assigns the specified WebDriver and locator Properties to the corresponding static
	 * members of the TestBase class, setting up the necessary environment for page interactions.</p>
	 *
	 * @param driver the WebDriver instance used for browser interactions
	 * @param loc    the Properties object containing locator values for page elements
	 */
	public OwnerPage(WebDriver driver, Properties loc) {
		TestBase.driver = driver;
		TestBase.locators = loc;
	}

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

	By editOwnerButton = By.xpath(locators.getProperty("editOwnerButton"));

	By addNewPetButton = By.xpath(locators.getProperty("addNewPetButton"));

	By editPetButton = By.xpath(locators.getProperty("editPetButton"));

	By addVisitButton = By.xpath(locators.getProperty("addVisitButton"));

	By nameLocator = By.xpath(locators.getProperty("nameLocator"));

	By successMessage = By.id(locators.getProperty("successMessageAlert"));

	By updateMessage = By.id(locators.getProperty("updateMessageAlert"));

	By newPetAddedMessage = By.id(locators.getProperty("newPetAddedMessageAlert"));

	By petDetailsClass = By.className(locators.getProperty("petDetails"));

	By petUpdateMessage = By.id(locators.getProperty("updateMessageAlert"));

	By visitAddedMessage = By.id(locators.getProperty("visitAddedMessage"));

	By visitDetailsClass = By.className(locators.getProperty("visitDetails"));

	/**
	 * Verifies if the specified last name is displayed on the page.
	 *
	 * <p>This method retrieves the web element identified by <code>nameLocator</code> from the driver,
	 * obtains its text, and checks whether it contains the given last name.</p>
	 *
	 * @param lastName the last name to check within the element's text
	 * @return {@code true} if the element's text contains the last name; {@code false} otherwise
	 * @throws NoSuchElementException if the element identified by <code>nameLocator</code> is not found
	 */
	public boolean isLastNameDisplayed(String lastName) {
		WebElement element = driver.findElement(nameLocator);
		return element.getText().contains(lastName);
	}

	/**
	 * Verifies that the success message on the page matches the expected value.
	 * <p>
	 * This method waits until the element identified by the <code>successMessage</code> locator is visible,
	 * retrieves its text, and compares it against the expected message obtained from the properties file.
	 * It returns <code>true</code> if the actual message equals the expected message, and <code>false</code>
	 * otherwise.
	 * </p>
	 *
	 * @return <code>true</code> if the displayed success message matches the expected message; <code>false</code> otherwise
	 * @throws TimeoutException if the success message element is not visible within the allotted wait time
	 */
	public boolean isSuccessMessageDisplayed() {
		wait.until(visibilityOfElementLocated(successMessage));
		WebElement message = driver.findElement(successMessage);
		String expectedSuccessMessage = tap.getProperty("successMessage");
		return message.getText().equals(expectedSuccessMessage);
	}

	/**
	 * Clicks the "Edit Owner" button and waits until the header element (h2) becomes visible.
	 *
	 * <p>This method locates the edit owner button using its predefined locator, clicks it to trigger
	 * the editing process, and then waits for the header element to be visible, indicating that the subsequent
	 * page or section has loaded.</p>
	 *
	 * @throws org.openqa.selenium.NoSuchElementException if the edit owner button or header element is not found in the DOM
	 */
	public void clickOnEditOwnerButton() {
		driver.findElement(editOwnerButton).click();
		wait.until(visibilityOf(driver.findElement(By.tagName("h2"))));
	}

	/**
	 * Checks if the update message is displayed on the page.
	 * <p>
	 * This method waits for the element located by {@code updateMessage} to become visible, retrieves its text,
	 * and compares it with the expected update message defined in the properties using the key "updateMessage".
	 * </p>
	 *
	 * @return {@code true} if the displayed update message matches the expected message; {@code false} otherwise.
	 */
	public boolean isUpdateMessageDisplayed() {
		wait.until(visibilityOfElementLocated(updateMessage));
		WebElement message = driver.findElement(updateMessage);
		String expectedUpdateMessage = tap.getProperty("updateMessage");
		return message.getText().equals(expectedUpdateMessage);
	}

	/**
	 * Clicks the "Add New Pet" button on the owner page and waits until the header element is visible.
	 * 
	 * <p>This method locates the "Add New Pet" button using its designated locator, clicks it, and then
	 * employs an explicit wait to ensure that a header element (identified by the tag name "h2") has become
	 * visible, indicating that the page has updated its content accordingly.</p>
	 *
	 * @throws org.openqa.selenium.NoSuchElementException if the button or header element is not found on the page
	 */
	public void clickOnAddNewPetButton() {
		driver.findElement(addNewPetButton).click();
		wait.until(visibilityOf(driver.findElement(By.tagName("h2"))));
	}

	/**
	 * Checks if the success message for adding a new pet is displayed on the page.
	 *
	 * <p>
	 * This method waits for the element identified by the "newPetAddedMessage" locator to become visible,
	 * then retrieves its text and compares it with the expected message obtained from the properties using the
	 * key "newPetAddedMessage". It returns {@code true} if the text matches the expected message; otherwise,
	 * it returns {@code false}.
	 * </p>
	 *
	 * @return {@code true} if the displayed pet addition success message matches the expected text; {@code false} otherwise.
	 * @throws org.openqa.selenium.TimeoutException if the message element is not visible within the specified wait time.
	 */
	public boolean isPetAddedSuccessMessageDisplayed() {
		wait.until(visibilityOfElementLocated(newPetAddedMessage));
		WebElement message = driver.findElement(newPetAddedMessage);
		String expectedMessage = tap.getProperty("newPetAddedMessage");
		return message.getText().equals(expectedMessage);
	}

	/**
	 * Checks if the expected pet name is displayed among the pet details on the page.
	 *
	 * <p>
	 * This method locates all web elements corresponding to pet details, retrieves their text, and then examines
	 * whether any of these text entries contains the expected pet name. The expected pet name is obtained from the
	 * properties using the provided key.
	 * </p>
	 *
	 * @param petName the key used to retrieve the expected pet name from the properties
	 * @return true if the pet details include the expected pet name; false otherwise
	 */
	public boolean isPetNameDisplayed(String petName) {
		List<WebElement> petDetails = driver.findElements(petDetailsClass);
		List<String> petDetailsText = petDetails.stream().map(WebElement::getText).toList();
		String expectedPetName = input.getProperty(petName);
		return petDetailsText.stream().anyMatch(petDetailsItem -> petDetailsItem.contains(expectedPetName));
	}

	/**
	 * Clicks the first available Edit Pet button on the owner page.
	 *
	 * <p>This method retrieves all elements that match the edit pet button locator and clicks the first one if one or more are found.
	 * If no edit pet button is present, the method will not perform any action.</p>
	 */
	public void clickOnEditPetButton() {
		List<WebElement> editButtons = driver.findElements(editPetButton);
		if (!editButtons.isEmpty()) {
			editButtons.get(0).click();
		}
	}

	/**
	 * Checks if the pet update message is displayed on the page.
	 *
	 * <p>This method waits for the visibility of the element defined by the pet update message locator,
	 * retrieves its text, and compares it with the expected update message retrieved from properties using
	 * the key "petUpdateMessage".</p>
	 *
	 * @return true if the displayed message exactly matches the expected update message; false otherwise.
	 * @throws TimeoutException if the pet update message element is not visible within the allotted wait time.
	 */
	public boolean isUpdatePetMessageDisplayed() {
		wait.until(visibilityOfElementLocated(petUpdateMessage));
		WebElement message = driver.findElement(petUpdateMessage);
		String expectedUpdateMessage = tap.getProperty("petUpdateMessage");
		return message.getText().equals(expectedUpdateMessage);
	}

	/**
	 * Clicks the "Add Visit" button on the owner page.
	 *
	 * <p>This method locates the "Add Visit" button using the <code>addVisitButton</code> locator
	 * and simulates a click action on it. Ensure that the button is present and clickable before
	 * invoking this method.</p>
	 *
	 * @throws org.openqa.selenium.NoSuchElementException if the button element cannot be found
	 */
	public void clickOnAddVisitButton() {
		driver.findElement(addVisitButton).click();
	}

	/**
	 * Checks if the visit added message is displayed on the page.
	 *
	 * <p>This method waits until the element associated with the visit added message is visible,
	 * retrieves its text, and compares it with the expected visit added message defined in the properties.
	 * It returns {@code true} if the displayed text matches the expected value; otherwise, it returns {@code false}.
	 *
	 * @return {@code true} if the visit added message equals the expected property value, {@code false} otherwise.
	 */
	public boolean isVisitAddedMessageDisplayed() {
		wait.until(visibilityOfElementLocated(visitAddedMessage));
		WebElement message = driver.findElement(visitAddedMessage);
		String expectedUpdateMessage = tap.getProperty("visitAddedMessage");
		return message.getText().equals(expectedUpdateMessage);
	}

	/**
	 * Checks if the visit details section displays a visit with the specified description.
	 *
	 * <p>This method retrieves the text from the web element identified by the visit details locator
	 * and verifies whether it contains the expected visit description. The expected description is obtained
	 * from the properties input using the provided description key.</p>
	 *
	 * @param description the key used to fetch the expected visit description from properties
	 * @return {@code true} if the visit details contain the expected description; {@code false} otherwise
	 * @throws NoSuchElementException if the visit details element cannot be found on the page
	 */
	public boolean isVisitAdded(String description) {
		WebElement visitDetails = driver.findElement(visitDetailsClass);
		String expectedDescription = input.getProperty(description);
		return visitDetails.getText().contains(expectedDescription);
	}

}
