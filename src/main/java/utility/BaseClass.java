package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import browserFactory.BrowserFactory;
import dataProvider.ConfigDataProvider;

public class BaseClass {

	public static WebDriver driver;
	public static String randStr = RandomStringUtils.randomAlphabetic(2);
	public String timeStamp = new SimpleDateFormat("dd_MMM_yy_HHmmss").format(Calendar.getInstance().getTime());

	ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(
			System.getProperty("user.dir") + "/Reports/MyReport" + "_" + randStr + "_" + timeStamp + ".html");
	ExtentReports extentReports = new ExtentReports();;
	ExtentTest test;

	@BeforeSuite
	public void suiteSetup() {
		extentSparkReporter.config().setDocumentTitle("Test Results");
		extentSparkReporter.config().setReportName("Automation Results");
		extentSparkReporter.config().setTheme(Theme.DARK);

		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Author", "Rahul Patidar");
		extentReports.setSystemInfo("OS", "Windows");
		extentReports.setSystemInfo("Host Name", "Rahul");
		extentReports.setSystemInfo("Environment", "QA");
	}

	@BeforeTest
	public void testSetup(ITestContext result) {
		initialisation();
	}

	@BeforeMethod
	public void methodSetup(ITestResult result) {
		test = extentReports.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO, "Test started : " + result.getMethod().getMethodName());
	}

	@AfterMethod
	public void methodTeardown(ITestResult result) throws IOException, InterruptedException {
		String temp = captureScreenshot(result.getName());
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL,
					"Test Failed : "+"  "+ test.addScreenCaptureFromPath(temp));
			test.log(Status.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Skipped ");
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Passed : " + result.isSuccess());
		}
		test.log(Status.INFO, "Test Ended : " + result.getMethod().getMethodName());
	}

	@AfterTest
	public void testTeardown() {
		termination();
	}

	@AfterSuite
	public void suiteTeardown() {
		extentReports.flush();
	}

	public static void initialisation() {
		try {
			driver = BrowserFactory.getBrowser();
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.get(ConfigDataProvider.getApplicationURL());
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

	public static void termination() {
		BrowserFactory.closeBrowser();
	}

	public static String captureScreenshot(String screenshotName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName
				+ System.currentTimeMillis() + ".png";
		try {
			FileUtils.copyFile(src, new File(destination));
		} catch (IOException e) {
			e.getMessage();
		}
		return destination;
	}

	public static void sendkeys(WebDriver driver, WebElement element, int timeout, String value) {
		new WebDriverWait(driver, Duration.ofMillis(timeout)).until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(value);
	}

	public static void clickOn(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, Duration.ofMillis(timeout)).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
}