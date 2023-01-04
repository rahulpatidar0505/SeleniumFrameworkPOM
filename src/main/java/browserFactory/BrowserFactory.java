package browserFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import dataProvider.ConfigDataProvider;

public class BrowserFactory {

	public static WebDriver driver;
	public static File folder;

	public static WebDriver getBrowser() throws IOException {

		ConfigDataProvider config = new ConfigDataProvider();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--incognito");
		options.addArguments("test-type=browser");
		options.addArguments("--disable-extensions--");
		options.addArguments("disable-geolocation");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("no-sandbox");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return driver;
	}

	public static void closeBrowser() {
		driver.quit();
	}
}