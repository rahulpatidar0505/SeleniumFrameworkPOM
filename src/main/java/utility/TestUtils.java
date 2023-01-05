package utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtils extends BaseClass {
	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 10;

	public static String randomStringGenerator(int length, boolean useLetters, boolean useNumbers) {
		return RandomStringUtils.random(length, useLetters, useNumbers);
	}

	public static void selectByVisibleText(WebElement element, int timeout, String visibleText) {
		waitForElementPresent(element, timeout);
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}

	public static void selectByValue(WebElement element, int timeout, String value) {
		waitForElementPresent(element, timeout);
		Select sel = new Select(element);
		sel.selectByValue(value);
	}

	public static WebElement waitForElementPresent(WebElement element, int timeout) {
		for (int i = 0; i < timeout; i++) {
			try {
				new WebDriverWait(driver, Duration.ofMillis(timeout))
						.until(ExpectedConditions.presenceOfElementLocated((By) element));
				break;
			} catch (Exception e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					System.out.println("Waiting for element to appear on DOM");
				}
			}
		}
		return element;
	}

	public static void clickElementByJS(WebElement element, WebDriver driver){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", element);

	}

	public static void scrollIntoView(WebElement element, WebDriver driver){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
}
