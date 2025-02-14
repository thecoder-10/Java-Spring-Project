package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.TestBase;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ListOwnersPage extends TestBase {

	/**
	 * Constructs a new ListOwnersPage instance using the specified WebDriver and locator properties.
	 *
	 * <p>This constructor initializes the ListOwnersPage by assigning the provided WebDriver
	 * instance and Properties object to the corresponding static fields in the TestBase class.
	 * These fields are used to interact with web elements on the page, particularly for operations
	 * related to a table displaying owner information.</p>
	 *
	 * @param driver the WebDriver instance used to execute browser interactions.
	 * @param loc the Properties object containing locator values for web elements.
	 */
	public ListOwnersPage(WebDriver driver, Properties loc) {
		TestBase.driver = driver;
		TestBase.locators = loc;
	}

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

	By table = By.id(locators.getProperty("tableId"));

	WebElement tableElement = driver.findElement(table);

	List<WebElement> rows = tableElement.findElements(By.tagName("tr"));

	/**
	 * Verifies the appearance and content of the owners table on the page.
	 *
	 * <p>This method waits for the table element to become visible and asserts that it is displayed.
	 * It also verifies that the table contains exactly 6 rows. The text of the second and third rows is
	 * retrieved using XPath and then compared against the corresponding values from the table's row list.
	 * Any mismatch in the displayed content or row count will cause an assertion failure.</p>
	 */
	public void tableAppearance() {
		wait.until(visibilityOf(tableElement));

		assertTrue(tableElement.isDisplayed(), "Table is displayed");
		assertEquals(rows.size(), 6, "Expected 6 rows in the table");

		String owner1 = driver.findElement(By.xpath("(//tr)[2]")).getText();
		String owner2 = driver.findElement(By.xpath("(//tr)[3]")).getText();

		assertEquals(rows.get(1).getText(), owner1, "Incorrect data in row 1");
		assertEquals(rows.get(2).getText(), owner2, "Incorrect data in row 2");
	}

	/**
	 * Clicks the owner's name from a specified table row and verifies the displayed name.
	 * 
	 * <p>This method retrieves the table row at the given index from the list of rows, extracts the text from the first 
	 * cell (assumed to contain the owner's name), then clicks that cell. After the click action, it locates a page element 
	 * using an XPath expression to obtain the displayed owner's name and asserts that this text matches the text from the clicked cell.
	 * This ensures that the correct owner's name is presented after the click.
	 *
	 * @param row the zero-based index of the table row containing the owner's name to be clicked
	 * @throws IndexOutOfBoundsException if the specified row index is out of bounds of the available table rows
	 */
	public void clickOnNameFromTable(int row) {
		WebElement firstRow = rows.get(row);
		List<WebElement> name = firstRow.findElements(By.tagName("td"));
		WebElement firstName = name.get(0);
		String firstNameText = firstName.getText();
		firstName.click();

		WebElement ownerName = driver.findElement(By.xpath("(//td)[1]"));
		String ownerNameText = ownerName.getText();
		assertEquals(ownerNameText, firstNameText);
	}

	/**
	 * Clicks on a web element identified by the provided XPath expression.
	 *
	 * <p>This method locates an element on the current page using the given XPath string
	 * and simulates a click action on it. It is typically used to navigate to a different page.
	 *
	 * @param page the XPath expression that locates the target element to be clicked
	 * @throws NoSuchElementException if no element matches the given XPath expression
	 */
	public void clickOnDifferentPage(String page) {
		driver.findElement(By.xpath(page)).click();
	}

}
