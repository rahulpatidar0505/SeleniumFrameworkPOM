package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utility.BaseClass;
import utility.TestUtils;

import java.time.Duration;
import java.util.List;

public class PO_Checkout extends BaseClass {

	public PO_Checkout() {
		
	}
	@FindBy(xpath = "//div[@class='title']")
	WebElement locator_orderSummary;

	@FindBy(xpath = "//input[@name='firstname']")
	WebElement locator_firstName;

	@FindBy(xpath = "//input[@name='lastname']")
	WebElement locator_lastName;

	@FindBy(xpath = "//input[@name='company']")
	WebElement locator_company;

	@FindBy(xpath = "//input[@name='street[0]']")
	WebElement locator_street_0;

	@FindBy(xpath = "//input[@name='street[1]']")
	WebElement locator_street_1;

	@FindBy(xpath = "//input[@name='city']")
	WebElement locator_city;

	@FindBy(xpath = "//select[@name='region_id']")
	WebElement locator_state;

	@FindBy(xpath = "//input[@name='postcode']")
	WebElement locator_postcode;

	@FindBy(xpath = "//select[@name='country_id']")
	WebElement locator_bottoms;

	@FindBy(xpath = "//span[contains(text(),'Pants')]")
	WebElement locator_country;

	@FindBy(xpath = "//input[@name='telephone']")
	WebElement locator_phoneNumber;

	@FindBy(css = "input[value='flatrate_flatrate']")
	WebElement locator_selectShippingMethod;

	@FindBy(xpath = "//span[normalize-space()='Next']")
	WebElement locator_nextButton;

	@FindBy(xpath = "//span[normalize-space()='Place Order']")
	WebElement locator_placeOrderButton;

	public @FindBy(xpath = "//span[contains(normalize-space(),'Thank you for your purchase!')]")
	WebElement locator_thanksMsg;

	@FindBy(xpath = "//div[@class='panel header']//button[@type='button']")
	WebElement locator_accountPanelArrow;

	@FindBy(xpath = "//div[@aria-hidden='false']//a[normalize-space()='My Account']")
	WebElement locator_myAccount;

	public @FindBy(xpath = "//div[normalize-space()='Shipping Methods']")
	WebElement locator_shippingMethod;

	public void expandOrderSummary(){
		clickOn(driver, locator_orderSummary, 2);
	}

	public void provideAddress(){
		clickOn(driver, locator_orderSummary, 2);
	}

	public void selectShippingMethodMoveAndPlaceOrder(){
		clickOn(driver, locator_selectShippingMethod, 2);
		clickOn(driver, locator_nextButton, 2);
		TestUtils.waitForElementPresent(locator_placeOrderButton, 5);
		clickOn(driver, locator_placeOrderButton, 2);
	}

	public void verifyPlacedOrder(){
		clickOn(driver, locator_accountPanelArrow, 2);
		clickOn(driver, locator_myAccount, 2);
	}
}
