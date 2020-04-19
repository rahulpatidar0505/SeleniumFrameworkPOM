package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptExecutorHelper extends BaseClass {
	
    /************************************** PURPOSE **********************************
 	Must have feature Highlight element Selenium for better execution.
	In Automation, testing sometimes element highlighter plays very important role. 
	It helps us to track our execution flow which step is being processed. Some tools like QTP, Sahi etc. you will get this inbuilt feature. 
	For Selenium, we have to write small code, which simply highlights element based on our parameter values. let’s get started and see Highlight element Selenium using CSS values.
	In Selenium, we can use JavascriptExecutor (interface) to execute Javascript code into webdriver.*/
	
	JavascriptExecutor js = ((JavascriptExecutor) driver);

	public void flash(WebElement element, WebDriver driver) throws InterruptedException 
	{
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 10; i++) 
		{
			changeColor("rgb(0,200,0)", element, driver);// 1
			changeColor(bgcolor, element, driver);// 2
		}
	}

	public void changeColor(String color, WebElement element, WebDriver driver) throws InterruptedException 
	{
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		Thread.sleep(20);
	}

	public void drawBorder(WebElement element, WebDriver driver) 
	{
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public void generateAlert(WebDriver driver, String message) 
	{
		js.executeScript("alert('" + message + "')");
	}

	public void clickElementByJS(WebElement element, WebDriver driver) 
	{
		js.executeScript("arguments[0].click();", element);
	}

	public void refreshBrowserByJS(WebDriver driver) 
	{
		js.executeScript("history.go(0)");
	}

	public String getTitleByJS(WebDriver driver) 
	{
		String title = js.executeScript("return document.title;").toString();
		return title;
	}

	// Complete inner text of that particular Page
	public String getPageInnerText(WebDriver driver) 
	{
		String pageText = js.executeScript("return document.documentElement.innerText;").toString();
		return pageText;
	}

	// Scroll full page
	public void scrollPageDown(WebDriver driver) 
	{
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	// Keep scrolling until that element is visible
	public void scrollIntoView(WebElement element, WebDriver driver) 
	{
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// Handle calendar - JS direct hit the DOM and enter the value
	public void selectDateByJS(WebElement element, WebDriver driver, String dateValue) 
	{
		js.executeScript("arguments[0].setAttribute('value','" + dateValue + "');", element);
	}

}
