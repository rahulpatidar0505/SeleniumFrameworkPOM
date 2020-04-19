package browserFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import dataProvider.ConfigDataProvider;

public class BrowserFactory {

	public static WebDriver driver;
	public static File folder;

	public static WebDriver getBrowser(String browser) throws IOException {

		ConfigDataProvider config = new ConfigDataProvider();

		if (browser.equalsIgnoreCase("Chrome")) {
			
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
			System.setProperty("webdriver.chrome.driver", config.getChromePath());
			driver = new ChromeDriver(options);

		} else if (browser.equalsIgnoreCase("IE")) {
			
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			options.setCapability("ignoreProtectedModeSettings", true);
			options.setCapability("ignoreZoomSetting", true);
			options.setCapability("unexpectedAlertBehaviour", "accept");
			options.setCapability("disable-popup-blocking", true);
			options.setCapability("enablePersistentHover", true);
			options.setCapability("nativeEvents", false);
			System.setProperty("webdriver.ie.driver", config.getIEPath());
			driver = new InternetExplorerDriver();
			
		} else if (browser.equalsIgnoreCase("Gecho")) {

			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("network.proxy.type", 0);
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(profile);
			System.setProperty("webdriver.gecho.driver", config.getGechoPath());
			driver = new FirefoxDriver(options);
		}

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
