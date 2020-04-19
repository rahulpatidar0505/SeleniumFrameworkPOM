package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindBy;

import utility.BaseClass;

public class PO_AutomationPracticeForm extends BaseClass {

	public PO_AutomationPracticeForm() {
	}

	@FindBy(xpath = "//a[contains(text(),'Automation practice form')]")
	WebElement practiceFormTest;

	@FindBy(xpath = "//input[@name='firstname']")
	WebElement firstname;

	@FindBy(xpath = "//input[@id='lastname']")
	WebElement lastName;

	@FindBy(xpath = "//button[@id='buttonwithclass']")
	WebElement simpleButton;

	@FindBy(xpath = "//input[@id='sex-0']")
	WebElement sex;

	@FindBy(xpath = "//input[@id='exp-4']")
	WebElement exp;

	@FindBy(xpath = "//input[@id='datepicker']")
	WebElement date;

	@FindBy(xpath = "//input[@id='profession-1']")
	WebElement automationTester;

	@FindBy(xpath = "//input[@id='tool-2']")
	WebElement tool;

	@FindBy(xpath = "//select[@id='continents']")
	WebElement continents;

	@FindBy(xpath = "//select[@id='continentsmultiple']//option[contains(text(),'Asia')]")
	WebElement asia;

	@FindBy(xpath = "//button[@id='submit']")
	WebElement button;

	public void fillForm() {
		clickOn(driver, practiceFormTest, 2);
		sendkeys(driver, firstname, 1, "Ankan");
	}

}
