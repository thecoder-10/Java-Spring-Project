package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.TestBase;

import java.time.Duration;
import java.util.Properties;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class AddVisitPage extends TestBase {

	/**
	 * Constructs a new AddVisitPage instance by setting the WebDriver and locator properties.
	 *
	 * <p>This constructor initializes the AddVisitPage by assigning the provided WebDriver and Properties
	 * (which contains the web element locators) to the corresponding static fields in the TestBase class.
	 * It prepares the framework for subsequent interactions with the add visit page.</p>
	 *
	 * @param driver the WebDriver instance used for browser interactions
	 * @param loc the Properties object containing locator values for web elements
	 */
	public AddVisitPage(WebDriver driver, Properties loc) {
		TestBase.driver = driver;
		TestBase.locators = loc;
	}

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

	By visitDateField = By.id(locators.getProperty("visitDate"));

	By descriptionField = By.id(locators.getProperty("descriptionField"));

	By addVisitButton = By.xpath(locators.getProperty("addVisit"));

	/**
	 * Fills in the visit date and description input fields on the page.
	 *
	 * <p>This method locates the elements for the visit date and description using their respective locators
	 * and inputs the provided values. This facilitates the automation of form filling for adding a visit.
	 * It relies on the WebDriver instance inherited from the TestBase class.</p>
	 *
	 * @param visitDate   the visit date string to input in the visit date field
	 * @param description the description text to input in the description field
	 */
	public void fillTheFields(String visitDate, String description) {
		driver.findElement(visitDateField).sendKeys(visitDate);
		driver.findElement(descriptionField).sendKeys(description);
	}

	/**
	 * Clicks the "Add Visit" button on the page.
	 *
	 * <p>
	 * This method uses the WebDriver instance to locate the button identified by the
	 * {@code addVisitButton} locator and simulates a click action. It is typically used to submit
	 * the visit details entered by the user.
	 * </p>
	 *
	 * @throws NoSuchElementException if the "Add Visit" button cannot be found on the page.
	 */
	public void clickOnAddVisitButton() {
		driver.findElement(addVisitButton).click();
	}

	/**
	 * Checks whether the error message for an empty field is displayed on the page.
	 * 
	 * <p>This method locates an element containing the specified error message text using an XPath expression,
	 * waits until the element is visible, and compares its text to the expected message. The method returns
	 * {@code true} if the error message is visible and the text exactly matches the provided message.</p>
	 *
	 * @param message the expected error message text (must match exactly)
	 * @return {@code true} if the error message is visible and its text exactly equals the provided message,
	 *         {@code false} otherwise
	 * @throws org.openqa.selenium.NoSuchElementException if the error message element is not found
	 * @throws org.openqa.selenium.TimeoutException if the error message element does not become visible in time
	 */
	public boolean isErrorMessageDisplayedForEmptyField(String message) {
		WebElement ErrorMessage = driver.findElement(By.xpath("//*[contains(text(),'" + message + "')]"));
		wait.until(visibilityOf(ErrorMessage));
		return ErrorMessage.getText().equals(message);
	}

	/**
	 * Checks if the error message for an invalid date is displayed with the expected text.
	 *
	 * <p>This method locates an element containing the provided error message substring using an XPath query,
	 * waits until the element is visible, and then verifies that its full text exactly matches the expected message.
	 *
	 * @param message the expected error message text for an invalid date
	 * @return {@code true} if the error message element's text exactly matches the expected message; {@code false} otherwise
	 * @throws org.openqa.selenium.NoSuchElementException if the error message element is not found on the page
	 */
	public boolean isErrorMessageDisplayedForInvalidDate(String message) {
		WebElement ErrorMessage = driver.findElement(By.xpath("//*[contains(text(),'" + message + "')]"));
		wait.until(visibilityOf(ErrorMessage));
		return ErrorMessage.getText().equals(message);
	}

}
