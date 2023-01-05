package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.BaseClass;

public class PO_SignIn extends BaseClass {
	public PO_SignIn() {
	}

	@FindBy(xpath = "//div[@class='panel header']//a[contains(text(),'Sign In')]")
	WebElement locator_signInLink;

	@FindBy(xpath = "//input[@id='email']")
	WebElement locator_email;

	@FindBy(xpath = "//fieldset[@class='fieldset login']//input[@id='pass']")
	WebElement locator_password;

	@FindBy(xpath = "//fieldset[@class='fieldset login']//span[contains(text(),'Sign In')]")
	WebElement locator_signInButton;

	public void clickOnSignInLink() {
		clickOn(driver, locator_signInLink, 1);
	}

	public void customerLogin(String emailId, String passowrd) {
		sendkeys(driver, locator_email, 1, emailId);
		sendkeys(driver, locator_password, 1, passowrd);
		clickOn(driver, locator_signInButton, 1);
	}
}
