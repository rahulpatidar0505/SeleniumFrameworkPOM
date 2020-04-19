package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindBy;

import utility.BaseClass;

public class PO_HtmlContactForm extends BaseClass {

	public PO_HtmlContactForm() {
	}

	@FindBy(xpath = "//a[contains(text(),'HTML contact form')]")
	WebElement htmlContactFormLink;

	@FindBy(xpath = "//input[@placeholder='Your name..']")
	WebElement yourName;

	@FindBy(xpath = "//input[@id='lname']")
	WebElement lastName;

	@FindBy(xpath = "//input[@placeholder='Enter your Country']")
	WebElement enterYourCountry;

	@FindBy(xpath = "//textarea[@id='subject']")
	WebElement subject;

	@FindBy(xpath = "//div[@id='content']//a[1]")
	WebElement googleLink;

	@FindBy(xpath = "//a[contains(text(),'Google Link is here')]")
	WebElement googleLinkIsHere;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement submit;

	@FindBy(xpath = "//h1[contains(text(),'Oops! That page can')]")
	public WebElement pageTitle;

	public void fillForm() {
		clickOn(driver, htmlContactFormLink, 2);
		sendkeys(driver, yourName, 2, "Rahul");
		sendkeys(driver, lastName, 2, "Patidar");
		sendkeys(driver, enterYourCountry, 2, "India");
		sendkeys(driver, subject, 2, "Automation testing");
		clickOn(driver, submit, 2);
	}

}
