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
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class AddOwnerPage extends TestBase {

	/**
	 * Constructs an AddOwnerPage instance using the provided WebDriver and locator properties.
	 *
	 * <p>
	 * This constructor initializes the testing context by setting the static driver and locators fields
	 * in the TestBase class with the given WebDriver instance and Properties object, respectively.
	 * </p>
	 *
	 * @param driver the WebDriver instance controlling the browser session
	 * @param loc    the Properties object containing locator values for the web elements used in tests
	 */
	public AddOwnerPage(WebDriver driver, Properties loc) {
		TestBase.driver = driver;
		TestBase.locators = loc;
	}

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

	By firstNameField = By.id(locators.getProperty("firstNameField"));

	By lastNameField = By.id(locators.getProperty("lastNameField"));

	By addressField = By.id(locators.getProperty("address"));

	By cityField = By.id(locators.getProperty("city"));

	By telephoneField = By.id(locators.getProperty("telephone"));

	By addOwnerButton = By.xpath(locators.getProperty("addOwnerButton2"));

	By updateOwnerButton = By.xpath(locators.getProperty("updateOwnerButton"));

	/**
	 * Populates the owner form fields with the specified text values.
	 *
	 * <p>This method locates the input fields for first name, last name, address, city, and telephone
	 * on the Add Owner page using predefined locators, and enters the provided values using Selenium's
	 * {@code sendKeys} method. If any field is not found, a {@link org.openqa.selenium.NoSuchElementException}
	 * will be thrown.
	 *
	 * @param firstName the first name to input into the first name field
	 * @param lastName  the last name to input into the last name field
	 * @param address   the address to input into the address field
	 * @param city      the city to input into the city field
	 * @param telephone the telephone number to input into the telephone field
	 * @throws org.openqa.selenium.NoSuchElementException if any of the necessary elements are not found on the page
	 */
	public void setTextInFields(String firstName, String lastName, String address, String city, String telephone) {
		driver.findElement(firstNameField).sendKeys(firstName);
		driver.findElement(lastNameField).sendKeys(lastName);
		driver.findElement(addressField).sendKeys(address);
		driver.findElement(cityField).sendKeys(city);
		driver.findElement(telephoneField).sendKeys(telephone);
	}

	/**
	 * Simulates a click on the "Add Owner" button to initiate the owner addition process.
	 *
	 * <p>This method locates the "Add Owner" button using its Selenium locator and triggers a click event.
	 * It is used to submit the owner addition form in the test framework.</p>
	 */
	public void clickingOnAddOwnerButton() {
		driver.findElement(addOwnerButton).click();
	}

	/**
	 * Checks if error messages for empty form fields are displayed.
	 * <p>
	 * This method searches the page for elements containing the specified message text,
	 * waits until all these elements are visible, and verifies that at least four such error
	 * messages are present, indicating that all required fields are empty.
	 * </p>
	 *
	 * @param message the substring to match within the error message text
	 * @return {@code true} if four or more error message elements are found; {@code false} otherwise
	 * @throws TimeoutException if the error messages do not become visible within the configured wait time
	 */
	public boolean isErrorMessageDisplayedForEmptyFields(String message) {
		List<WebElement> messages = driver.findElements(By.xpath("//*[contains(text(),'" + message + "')]"));
		wait.until(visibilityOfAllElements(messages));
		return messages.size() >= 4;
	}

	/**
	 * Checks if the specified error message is displayed for the telephone field.
	 *
	 * <p>This method locates the error message element by searching for any element that contains the provided
	 * message text. It then waits until the error message is visible and compares its text to the expected message,
	 * returning {@code true} if they match exactly.</p>
	 *
	 * @param message the expected error message text to verify for the telephone field
	 * @return {@code true} if the error message is visible and its text matches the expected message; {@code false} otherwise
	 * @throws NoSuchElementException if the error message element is not found in the DOM
	 */
	public boolean isErrorMessageDisplayedForTextInTelephoneField(String message) {
		WebElement ErrorMessage = driver.findElement(By.xpath("//*[contains(text(),'" + message + "')]"));
		wait.until(visibilityOf(ErrorMessage));
		return ErrorMessage.getText().equals(message);
	}

	/**
	 * Clears the text from all input fields on the Add Owner page.
	 *
	 * <p>
	 * This method clears the contents of the text fields for first name, last name, address, city, and telephone
	 * by locating each element using its designated locator and invoking the clear method on it.
	 * It is typically used to reset the form before entering new data.
	 * </p>
	 *
	 * @throws org.openqa.selenium.NoSuchElementException if any input field cannot be found on the page.
	 */
	public void clearFields() {
		driver.findElement(firstNameField).clear();
		driver.findElement(lastNameField).clear();
		driver.findElement(addressField).clear();
		driver.findElement(cityField).clear();
		driver.findElement(telephoneField).clear();
	}

	/**
	 * Clicks the "Update Owner" button on the Add Owner page.
	 *
	 * <p>This method locates the update owner button using its defined locator and simulates a click event.
	 * It is typically used to confirm updates after modifying owner details in the form.
	 *
	 * @throws NoSuchElementException if the update owner button cannot be found on the page
	 */
	public void clickOnUpdateOwnerButton() {
		driver.findElement(updateOwnerButton).click();
	}

}
