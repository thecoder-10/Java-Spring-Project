package selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.TestBase;

import java.time.Duration;
import java.util.Properties;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class FindOwnersPage extends TestBase {

	/**
	 * Constructs a new FindOwnersPage instance.
	 *
	 * <p>Initializes the TestBase's driver and locator properties with the provided WebDriver
	 * and Properties objects. These are used for browser interactions and element identification
	 * on the Find Owners page.
	 *
	 * @param driver the WebDriver instance for interacting with the browser
	 * @param loc the Properties object containing locators for page elements
	 */
	public FindOwnersPage(WebDriver driver, Properties loc) {
		TestBase.driver = driver;
		TestBase.locators = loc;
	}

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

	By findOwnersPageLink = By.xpath(locators.getProperty("findOwnersLink"));

	By lastNameField = By.id(locators.getProperty("lastNameField"));

	By findOwnerButton = By.cssSelector(locators.getProperty("findOwnerButton"));

	By addOwnerButton = By.xpath(locators.getProperty("addOwnerButton"));

	By ownerNotFound = By.cssSelector(locators.getProperty("ownerNotFound"));

	/**
	 * Navigates to the "Find Owners" page.
	 * <p>
	 * Waits for the "Find Owners" link to be visible before clicking on it to navigate to the page.
	 * This method uses an explicit wait to ensure that the element is present and clickable.
	 * If the element does not become visible within the timeout period, a TimeoutException may be thrown.
	 * </p>
	 *
	 * @throws TimeoutException if the "Find Owners" link is not visible within the specified timeout.
	 */
	public void navigateToFindOwnersPage() {
		wait.until(visibilityOfElementLocated(findOwnersPageLink));
		driver.findElement(findOwnersPageLink).click();
	}

	/**
	 * Waits until the last name input field is visible and enters the provided text into it.
	 *
	 * @param text the text to be entered into the last name field
	 */
	public void setTextInLastNameField(String text) {
		wait.until(visibilityOfElementLocated(lastNameField));
		driver.findElement(lastNameField).sendKeys(text);
	}

	/**
	 * Simulates a click on the "Find Owner" button by sending the RETURN key.
	 *
	 * <p>This method locates the "Find Owner" button using its designated locator and triggers the owner search
	 * functionality by dispatching a RETURN key press. It does not return any value.</p>
	 */
	public void clickOnFindOwnerButton() {
		driver.findElement(findOwnerButton).sendKeys(Keys.RETURN);
	}

	/**
	 * Clicks the "Add Owner" button and waits for the Add Owner page to load.
	 *
	 * <p>This method simulates a click on the Add Owner button on the Find Owners page. After clicking, it
	 * waits explicitly for an <code>h2</code> element to become visible, which indicates that the navigation
	 * to the Add Owner page was successful.</p>
	 *
	 * @throws org.openqa.selenium.NoSuchElementException if the Add Owner button is not found in the DOM.
	 * @throws org.openqa.selenium.TimeoutException if the <code>h2</code> element does not appear within the configured wait time.
	 */
	public void clickOnAddOwnerButton() {
		driver.findElement(addOwnerButton).click();
		wait.until(visibilityOfElementLocated(By.tagName("h2")));
	}

	/**
	 * Retrieves the text from the element that indicates no owner was found.
	 *
	 * <p>This method attempts to locate the element identified by the {@code ownerNotFound}
	 * locator. If the element is found, its text is returned; otherwise, if no such element exists,
	 * a {@link org.openqa.selenium.NoSuchElementException} is caught and the method returns {@code null}.</p>
	 *
	 * @return the text of the "owner not found" message, or {@code null} if the element is not present.
	 */
	public String getOwnerNotFoundText() {
		try {
			WebElement ownerNotFoundElement = driver.findElement(ownerNotFound);
			return ownerNotFoundElement.getText();
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}

}
