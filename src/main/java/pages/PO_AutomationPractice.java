package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utility.BaseClass;
import utility.TestUtils;

public class PO_AutomationPractice extends BaseClass {

	public PO_AutomationPractice() {
		
	}

	@FindBy(xpath = "//button[text()='Home']")
	WebElement homeButton;

	@FindBy(xpath = "//button[text()='Practice']")
	WebElement practiceButton;

	@FindBy(xpath = "//button[text()='Login']")
	WebElement loginButton;

	@FindBy(xpath = "//button[text()='Signup']")
	WebElement signupButton;

	@FindBy(xpath = "//input[@name='radioButton']")
	List<WebElement> radioButtonExample;

	@FindBy(xpath = "//input[@type='checkbox']")
	List<WebElement> checkboxExample;

	@FindBy(id = "dropdown-class-example")
	WebElement dropdownExample;

	@FindBy(id = "autocomplete")
	WebElement suggessionClassExample;

	@FindBy(id = "openwindow")
	WebElement openWindowButton;

	@FindBy(id = "opentab")
	WebElement opentabButton;

	@FindBy(id = "name")
	WebElement nameText;

	@FindBy(id = "alertbtn")
	WebElement alertButton;

	@FindBy(id = "confirmbtn")
	WebElement confirmButton;

	@FindBy(id = "hide-textbox")
	WebElement hideButton;

	@FindBy(id = "show-textbox")
	WebElement showButton;

	@FindBy(name = "show-hide")
	WebElement showHideTextbox;

	@FindBy(id = "mousehover")
	WebElement mousehoverButton;

	@FindBy(xpath = "//a[text()='Top']")
	WebElement topLink;
	
	@FindBy(xpath = "//a[text()='Practice Projects']")
	List<WebElement> practiceProjectsLink;

	public void fillPracticeForm() {
		
		sendkeys(driver, nameText, 1, "Rahul");
		/*
		 * clickOn(driver, radioButtonExample.get(2), 1); clickOn(driver,
		 * checkboxExample.get(1), 1); TestUtils.selectByVisibleText(dropdownExample, 1,
		 * "Option2"); clickOn(driver, alertButton, 1); TestUtils.acceptAlert(1);
		 * clickOn(driver, alertButton, 1); TestUtils.dismissAlert(1);
		 * Assert.assertTrue(showHideTextbox.isDisplayed()); clickOn(driver, hideButton,
		 * 1); Assert.assertFalse(showHideTextbox.isDisplayed()); clickOn(driver,
		 * showButton, 1); Assert.assertTrue(showHideTextbox.isDisplayed());
		 * 
		 * Actions action=new Actions(driver);
		 * action.moveToElement(mousehoverButton).moveToElement(topLink).build().perform
		 * ();
		 * 
		 */
	}
}
