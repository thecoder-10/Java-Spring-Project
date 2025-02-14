package selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	public static WebDriver driver;

	public static Properties prop = new Properties();

	public static Properties locators = new Properties();

	public static Properties tap = new Properties();

	public static Properties input = new Properties();

	public static FileReader frProp;

	public static FileReader frLoc;

	public static FileReader frTap;

	public static FileReader frInput;

	/**
	 * Sets up the Selenium WebDriver environment and loads configuration properties.
	 * <p>
	 * This method initializes the WebDriver for use in Selenium tests. It reads configuration,
	 * locator, texts and photos, and input properties from the respective property files located
	 * in the test directory. The WebDriver is only initialized if it has not been set yet.
	 * </p>
	 * <p>
	 * Based on the "browser" property, this method configures the corresponding headless driver
	 * (Chrome, Firefox, or Edge) with options including disabling GPU, setting the window size to 1920x1080,
	 * and specifying the language as English. After driver initialization, the browser window is maximized and the
	 * test URL is loaded. Finally, it locates the welcome photo element using a class name from the locators
	 * properties and asserts that its "src" attribute matches the expected value from the texts and photos properties.
	 * </p>
	 *
	 * @throws IOException if there is an error reading any of the property files.
	 * @throws IllegalArgumentException if an unsupported browser is specified in the configuration.
	 */
	@Before
	public void setUp() throws IOException {
		String userDirectory = System.getProperty("user.dir");
		String basePath = "\\src\\test\\java\\selenium\\config\\";
		String propPath = basePath + "config.properties";
		String locPath = basePath + "locators.properties";
		String tapPath = basePath + "textsAndPhotos.properties";
		String inputPath = basePath + "input.properties";

		if (driver == null) {
			frProp = new FileReader(userDirectory + propPath);
			frLoc = new FileReader(userDirectory + locPath);
			frTap = new FileReader(userDirectory + tapPath);
			frInput = new FileReader(userDirectory + inputPath);

			prop.load(frProp);
			locators.load(frLoc);
			tap.load(frTap);
			input.load(frInput);
		}

		String browser = prop.getProperty("browser").toLowerCase();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless=new", "-disable-gpu", "-window-size=1920,1080", "--lang=en");

		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.addArguments("--headless", "-disable-gpu", "-window-size=1920,1080", "--lang=en");

		EdgeOptions edgeOptions = new EdgeOptions();
		edgeOptions.addArguments("--headless=new", "-disable-gpu", "-window-size=1920,1080", "--lang=en");

		switch (browser) {
			case "chrome":
				driver = new ChromeDriver(chromeOptions);
				break;
			case "firefox":
				driver = new FirefoxDriver(firefoxOptions);
				break;
			case "edge":
				driver = new EdgeDriver(edgeOptions);
				break;
			default:
				throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		driver.manage().window().maximize();
		driver.get(prop.getProperty("testUrl"));

		WebElement welcomePhoto = driver.findElement(By.className(locators.getProperty("welcomePhoto")));
		String ActualWelcomePhotoSrc = welcomePhoto.getAttribute("src");
		String expectedWelcomePhoto = tap.getProperty("welcomePhoto");
		org.junit.Assert.assertEquals(ActualWelcomePhotoSrc, expectedWelcomePhoto);
	}

	/**
	 * Quits the WebDriver instance after test execution.
	 *
	 * <p>
	 * This method is annotated with {@code @After} and is automatically executed
	 * after each test case to clean up the testing environment by closing the browser
	 * and releasing associated system resources.
	 * </p>
	 */
	@After
	public void tearDown() {
		driver.quit();
	}

}
