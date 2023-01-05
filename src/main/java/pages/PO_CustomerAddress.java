package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.BaseClass;
import utility.TestUtils;

public class PO_CustomerAddress extends BaseClass {
	public PO_CustomerAddress() {
	}
	@FindBy(xpath = "//strong[normalize-space()='Address Book']")
	WebElement locator_addressBook;

	@FindBy(xpath = "//div[@class='box box-billing-address']//span[contains(text(),'Edit Address')]")
	WebElement locator_editAddress;

	@FindBy(xpath = "//input[@name='firstname']")
	WebElement locator_firstName;

	@FindBy(xpath = "//input[@name='lastname']")
	WebElement locator_lastName;

	@FindBy(xpath = "//input[@name='company']")
	WebElement locator_company;

	@FindBy(xpath = "//input[@title='Street Address']")
	WebElement locator_street_0;

	@FindBy(xpath = "//input[@title=\"Street Address: Line 2\"]")
	WebElement locator_street_1;

	@FindBy(xpath = "//input[@name='city']")
	WebElement locator_city;

	@FindBy(xpath = "//select[@name='region_id']")
	WebElement locator_state;

	@FindBy(xpath = "//input[@name='postcode']")
	WebElement locator_postcode;

	@FindBy(xpath = "//select[@name='country_id']")
	WebElement locator_bottoms;

	@FindBy(xpath = "//select[@id='country']")
	WebElement locator_country;

	@FindBy(xpath = "//input[@name='telephone']")
	WebElement locator_phoneNumber;

	@FindBy(xpath = "//span[normalize-space()='Save Address']")
	WebElement locator_saveAddress;

	public void editAddressDetails(String fName, String lName, String company, String pNumber, String street0, String street1,
								   String city, String state, String postcode, String country){
		clickOn(driver, locator_editAddress, 2);
		sendkeys(driver, locator_firstName, 2, fName);
		sendkeys(driver, locator_lastName, 2, lName);
		sendkeys(driver, locator_company, 2, company);
		sendkeys(driver, locator_phoneNumber, 2, pNumber);
		sendkeys(driver, locator_street_0, 2, street0);
		sendkeys(driver, locator_street_1, 2, street1);
		sendkeys(driver, locator_city, 2, city);
		TestUtils.selectByVisibleText(locator_country, 2, country);
		TestUtils.selectByVisibleText(locator_state, 2, state);
		sendkeys(driver, locator_postcode, 2, postcode);
		clickOn(driver, locator_saveAddress, 2);
	}

}
