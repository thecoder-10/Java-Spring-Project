package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.TestBase;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class AddPetPage extends TestBase {

	/**
	 * Constructs a new AddPetPage instance and initializes the TestBase with the provided WebDriver and locator properties.
	 *
	 * <p>This constructor sets the static fields in the TestBase class to the given driver and properties,
	 * enabling the AddPetPage to interact with the web page elements associated with pet management.</p>
	 *
	 * @param driver the WebDriver instance used for browser automation
	 * @param loc the Properties object containing locator definitions for web elements
	 */
	public AddPetPage(WebDriver driver, Properties loc) {
		TestBase.driver = driver;
		TestBase.locators = loc;
	}

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

	By namePetField = By.id(locators.getProperty("namePetField"));

	By birthDateField = By.id(locators.getProperty("birthDateField"));

	By petTypeField = By.id(locators.getProperty("petTypeField"));

	By addPetButton = By.xpath(locators.getProperty("addPetButton"));

	By updatePetButton = By.xpath(locators.getProperty("updatePetButton"));

	/**
	 * Fills the pet information fields on the page.
	 *
	 * <p>This method enters the pet's name into the corresponding input field, inputs the provided
	 * birth date, and selects the appropriate pet type from the dropdown menu. It leverages Selenium's
	 * WebDriver to locate the elements and interact with them.</p>
	 *
	 * @param namePet   the pet's name to be entered in the name field
	 * @param birthDate the pet's birth date to be entered in the birth date field; must adhere to the expected format
	 * @param petType   the value corresponding to the pet type to be selected from the dropdown list
	 */
	public void fillTheFields(String namePet, String birthDate, String petType) {
		driver.findElement(namePetField).sendKeys(namePet);
		driver.findElement(birthDateField).sendKeys(birthDate);
		WebElement dropdownElement = driver.findElement(petTypeField);
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByValue(petType);
	}

	/**
	 * Clicks the "Add Pet" button on the pet addition page.
	 *
	 * <p>This method locates the "Add Pet" button using its locator and performs a click action to
	 * initiate the process of adding a new pet. It will throw a {@link org.openqa.selenium.NoSuchElementException}
	 * if the button is not present on the page.
	 */
	public void clickOnAddPetButton() {
		driver.findElement(addPetButton).click();
	}

	/**
	 * Checks if the duplicate pet name error message is displayed on the Add Pet page.
	 *
	 * <p>This method locates the error message element using the class name defined by the
	 * "addPetPageErrorMessage" property from the locators and compares its text against the expected
	 * error message obtained from the "samePetNameErrorMessage" property. It returns true if the two match.</p>
	 *
	 * @return {@code true} if the error message text matches the expected duplicate pet name error message,
	 *         {@code false} otherwise.
	 * @throws NoSuchElementException if the error message element is not found in the DOM.
	 */
	public boolean isErrorMessageDisplayedForSamePetName() {
		WebElement error = driver.findElement(By.className(locators.getProperty("addPetPageErrorMessage")));
		String expectedErrorText = tap.getProperty("samePetNameErrorMessage");
		return error.getText().equals(expectedErrorText);
	}

	/**
	 * Checks if exactly two error messages containing the specified text are displayed for empty fields.
	 *
	 * <p>
	 * This method locates all web elements whose text contains the given {@code message}, waits until all found elements
	 * are visible, and then verifies that exactly two such error messages are present. This is typically used to confirm
	 * that appropriate error validations are shown for empty input fields.
	 * </p>
	 *
	 * @param message the partial text expected to be present in each error message
	 * @return {@code true} if exactly two error messages are found and visible; {@code false} otherwise
	 * @throws TimeoutException if the error messages do not become visible within the specified wait time
	 */
	public boolean isErrorMessageDisplayedForEmptyFields(String message) {
		List<WebElement> messages = driver.findElements(By.xpath("//*[contains(text(),'" + message + "')]"));
		wait.until(visibilityOfAllElements(messages));
		return messages.size() == 2;
	}

	/**
	 * Checks if the invalid date error message is displayed on the page.
	 *
	 * <p>This method locates the error message element using the class name provided by the "addPetPageErrorMessage"
	 * property. It then retrieves the expected error text for an invalid date from the "invalidDateErrorMessage" property
	 * and compares it to the text of the located element.
	 *
	 * @return {@code true} if the displayed error message exactly matches the expected invalid date error message,
	 *         {@code false} otherwise.
	 * @throws NoSuchElementException if the error message element is not found on the page.
	 */
	public boolean isInvalidDateErrorMessageDisplayed() {
		WebElement error = driver.findElement(By.className(locators.getProperty("addPetPageErrorMessage")));
		String expectedErrorText = tap.getProperty("invalidDateErrorMessage");
		return error.getText().equals(expectedErrorText);
	}

	/**
	 * Clears the pet name and birth date input fields on the AddPet page.
	 *
	 * <p>This method locates the web elements corresponding to the pet name and birth date fields and clears
	 * any existing text. It is typically used to reset the input fields in preparation for new test data
	 * or form submission.</p>
	 *
	 * @throws NoSuchElementException if the pet name or birth date field cannot be found on the page.
	 */
	public void clearFields() {
		driver.findElement(namePetField).clear();
		driver.findElement(birthDateField).clear();
	}

	/**
	 * Clicks on the update pet button.
	 *
	 * <p>This method locates the update pet button element using its predefined locator and
	 * simulates a click action. This triggers the updating of pet information on the page.
	 *
	 * @throws NoSuchElementException if the update pet button is not found on the page.
	 */
	public void clickOnUpdatePetButton() {
		driver.findElement(updatePetButton).click();
	}

}
