package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import browserFactory.BrowserFactory;

public class TestUtils extends BaseClass {

	public static long PAGE_LOAD_TIMEOUT = 100;
	public static long IMPLICIT_WAIT = 90;

	public static void getBrokenLinks() throws MalformedURLException, IOException {
		List<WebElement> linksList = driver.findElements(By.tagName("a"));
		linksList.addAll(driver.findElements(By.tagName("img")));
		System.out.println("Total number of links and images are---" + linksList.size());

		List<WebElement> activeLinks = new ArrayList<>();

		for (int i = 0; i < linksList.size(); i++) {
			System.out.println(linksList.get(i).getAttribute("href"));
			if (linksList.get(i).getAttribute("href") != null
					&& (!linksList.get(i).getAttribute("href").contains("javascript"))) {
				activeLinks.add(linksList.get(i));
			}
		}

		for (int i = 0; i < activeLinks.size(); i++) {
			HttpURLConnection connection = (HttpURLConnection) new URL(activeLinks.get(i).getAttribute("href"))
					.openConnection();
			connection.connect();
			String response = connection.getResponseMessage();
			connection.disconnect();
			System.out.println(activeLinks.get(i).getAttribute("href") + "---->" + response);
		}
	}

	public static void getAllLinks() {
		List<WebElement> alllinks = driver.findElements(By.tagName("a"));
		for (int i = 0; i < alllinks.size(); i++)
			System.out.println(alllinks.get(i).getText());
	}

	public static String randomStringGenerator(int length, boolean useLetters, boolean useNumbers) {

		return RandomStringUtils.random(length, useLetters, useNumbers);

	}

	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date); // 2016/11/16 12:08:43
	}

	// Select dropdown which have option tag
	public static void selectByIndex(WebElement element, int timeout, int indexValue) {
		waitForElementPresent(element, timeout);
		Select sel = new Select(element);
		sel.selectByIndex(indexValue);
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

	public void getAllOptionFromDropdown(WebElement element) {
		waitForElementPresent(element, 10);

		Select option = new Select(element);

		List<WebElement> options = option.getOptions();

		System.out.println("Total number of options available are ---->" + options.size());

		for (int i = 0; i < options.size(); i++) {
			System.out.println("OPtions are--->" + options.get(i).getText());
		}
	}

//	public static void acceptAlert(int timeout) {
//		new WebDriverWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
//		Alert alert = driver.switchTo().alert();
//		alert.accept();
//	}
//
//	public static void dismissAlert(int timeout) {
//		new WebDriverWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
//		Alert alert = driver.switchTo().alert();
//		alert.dismiss();
//	}
//
//	public static String getTextFromAlert(int timeout) {
//		new WebDriverWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
//		Alert alert = driver.switchTo().alert();
//		return alert.getText();
//	}
//
//	public static void setTextToAlert(int timeout, String value) {
//		new WebDriverWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
//		Alert alert = driver.switchTo().alert();
//		alert.sendKeys(value);
//	}

//	public static void switchToFrameByIndex(int index, int timeOut) {
//		new WebDriverWait(driver, timeOut).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
//	}
//
//	public static void switchToFrameByName(String name, int timeOut) {
//		new WebDriverWait(driver, timeOut).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(name));
//	}
//
//	public static void switchToFrameByElement(WebElement element, int timeOut) {
//		new WebDriverWait(driver, timeOut).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
//	}

	// in DOM type='file'
	public static void fileUploadUsingSenkeys(WebElement element, int timeout, String FILE_NAME) {
		sendkeys(driver, element, timeout, FILE_NAME);

	}

	public static void downloadFile(WebElement element, int timeout) throws InterruptedException {
		clickOn(driver, element, timeout);
		Thread.sleep(5000);
		File[] listOfFiles = BrowserFactory.folder.listFiles();

		Assert.assertTrue(listOfFiles.length > 0);

		for (File file : listOfFiles) {
			Assert.assertTrue(file.length() > 0);
		}
	}

	public static void handleStaleElementRefException(WebElement element, int timeout) {

		for (int i = 0; i <= 3; i++) {
			try {
				clickOn(driver, element, timeout);
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/*
	 * Selenium does not provide direct API to handle, so we have to handle using
	 * Actions class check the frame before mouse over
	 */
	public void handleIrritatingChatModelPopUp(WebElement chatBox, int timeout, WebElement crossButton) {
		waitForElementPresent(chatBox, timeout);
		Actions action = new Actions(driver);
		action.moveToElement(chatBox).build().perform();
		crossButton.click();
	}

//	public void handlePagination(int timeOut) {
//		new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(By.tagName("a")));
//		List<WebElement> elements = driver.findElements(By.tagName("a"));
//		for (int i = 0; i < elements.size(); i++) {
//			String title = elements.get(i).getAttribute("title");
//			if (title.equals("Next Page")) {
//				elements.get(i).click();
//				break;
//			}
//		}
//	}

	public void handlePagination(String navXpath, String nextButtonXpath, String prevButtonXpath, String lastPage) {
		/*
		 * "//div[@class='nav-pages']//a"
		 * 
		 * "//*[@id='nextbutton id']"
		 * 
		 * "//*[@id='prevButtonid']"
		 * 
		 * "//*[@text='>>]/preceding::/span[1]"
		 */

		List<WebElement> pagination = driver.findElements(By.xpath(navXpath));

		WebElement NextButton = driver.findElement(By.xpath(nextButtonXpath));

		WebElement prevButton = driver.findElement(By.xpath(prevButtonXpath));

		int LastPageNumber = Integer.parseInt(driver.findElement(By.xpath(lastPage)).getText());

		// check if pagination link exists
		if (pagination.size() > 0) {
			System.out.println("pagination exists");

			for (int i = 1; i < pagination.size(); i++) {

				if (NextButton.isEnabled()) {

					NextButton.click();
				} else {
					System.out.println("pagination not exists");
				}
			}
		}
	}

	public static void calendarSelectTest(String xpath_month, String xpath_year) {
		String date = "dd-mmm-yyyy";
		String dateArr[] = date.split("-");
		String day = dateArr[0];
		String month = dateArr[1];
		String year = dateArr[2];

		Select select = new Select(driver.findElement(By.xpath(xpath_month)));
		select.selectByVisibleText(month);

		Select select1 = new Select(driver.findElement(By.xpath(xpath_year)));
		select1.selectByVisibleText(year);

		// *[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[1]
		// *[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]
		// *[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[6]

		String beforeXpath = "//*[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr[";
		String afterXpath = "]/td[";

		final int totalWeekDays = 7;

		// 2-1 2-2 2-3 2-4 2-5 2-6 2-7
		// 3-2 3-2 3-3 3-4 3-5 3-6 3-7
		boolean flag = false;
		String dayVal = null;
		for (int rowNum = 2; rowNum <= 7; rowNum++) {

			for (int colNum = 1; colNum <= totalWeekDays; colNum++) {
				try {
					dayVal = driver.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + "]")).getText();
				} catch (Exception e) {
					System.out.println("Please enter a correct date value");
					flag = false;
					break;
				}
				System.out.println(dayVal);
				if (dayVal.equals(day)) {
					driver.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + "]")).click();
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}

		}

	}

	public static void dynamicWebTableHandle() {
		// Method-1:
		String before_xpath = "//*[@id='vContactsForm']/table/tbody/tr[";
		String after_xpath = "]/td[2]/a";

		for (int i = 4; i <= 7; i++) {
			String name = driver.findElement(By.xpath(before_xpath + i + after_xpath)).getText();
			System.out.println(name);
			if (name.contains("test2 test2")) { // i=6
				// *[@id='vContactsForm']/table/tbody/tr[6]/td[1]/input
				driver.findElement(By.xpath("//*[@id='vContactsForm']/table/tbody/tr[" + i + "]/td[1]/input")).click();
			}
		}

		// Method-2:
		driver.findElement(By.xpath(
				"//a[contains(text(),'test2 test2')]/parent::td//preceding-sibling::td//input[@name='contact_id']"))
				.click();
		driver.findElement(By
				.xpath("//a[contains(text(),'ui uiii')]/parent::td//preceding-sibling::td//input[@name='contact_id']"))
				.click();

	}

	public static void handleWindowPopUp() throws Exception {
		Set<String> handler = driver.getWindowHandles();

		Iterator<String> it = handler.iterator();

		String parentWindowId = it.next();
		System.out.println("parent window id:" + parentWindowId);

		String childWindowId = it.next();
		System.out.println("Child window id:" + childWindowId);

		driver.switchTo().window(childWindowId);

		Thread.sleep(2000);

		System.out.println("child window pop up title" + driver.getTitle());

		driver.close();

		driver.switchTo().window(parentWindowId);

		Thread.sleep(2000);

		System.out.println("parent window title" + driver.getTitle());
	}

}
