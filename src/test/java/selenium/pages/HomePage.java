package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.TestBase;

import java.time.Duration;
import java.util.Properties;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class HomePage extends TestBase {

	/**
	 * Initializes the HomePage instance by setting the WebDriver and locator properties
	 * in the underlying TestBase class.
	 *
	 * @param driver the Selenium WebDriver used to interact with web elements on the home page
	 * @param loc a Properties object containing locator mappings for the page elements
	 */
	public HomePage(WebDriver driver, Properties loc) {
		TestBase.driver = driver;
		TestBase.locators = loc;
	}

	By welcomePhoto = By.className(locators.getProperty("welcomePhoto"));

	By homePageLink = By.xpath(locators.getProperty("homePageLink"));

	By logoLink = By.xpath(locators.getProperty("logoLink"));

	/**
	 * Clicks the home page link element.
	 *
	 * <p>This method locates the web element corresponding to the home page link using the
	 * defined locator and simulates a click action. A NoSuchElementException is thrown if the element
	 * is not found in the DOM.</p>
	 */
	public void clickOnHomePageLink() {
		driver.findElement(homePageLink).click();
	}

	/**
	 * Waits up to 3 seconds for the welcome photo element to be visible and verifies if its "src" attribute matches the provided value.
	 *
	 * <p>This method uses Selenium's WebDriverWait to wait for the welcome photo element (located by the "welcomePhoto" locator) to become visible.
	 * Once visible, it retrieves the "src" attribute of the element and compares it to the given <code>src</code> parameter.</p>
	 *
	 * @param src the expected source URL of the welcome photo
	 * @return {@code true} if the welcome photo is visible and its "src" attribute equals the provided <code>src</code>, otherwise {@code false}
	 * @throws TimeoutException if the welcome photo element is not visible within 3 seconds
	 */
	public boolean isWelcomePhotoVisible(String src) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(visibilityOfElementLocated(welcomePhoto));
		WebElement welcomePicture = driver.findElement(welcomePhoto);
		return welcomePicture.getAttribute("src").equals(src);
	}

	/**
	 * Clicks on the logo link element.
	 *
	 * <p>This method locates the logo link element using the predefined locator and simulates a click action.
	 * It is typically used to navigate to the homepage or a designated section represented by the logo.</p>
	 */
	public void clickOnLogoLink() {
		driver.findElement(logoLink).click();
	}

}
