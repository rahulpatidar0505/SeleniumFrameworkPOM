package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.BaseClass;
import utility.TestUtils;

public class PO_Checkout extends BaseClass {
	public PO_Checkout() {
		
	}
	@FindBy(xpath = "//div[@class='title']")
	WebElement locator_orderSummary;

	@FindBy(css = "input[value='flatrate_flatrate']")
	WebElement locator_selectShippingMethod;

	@FindBy(xpath = "//span[normalize-space()='Next']")
	WebElement locator_nextButton;

	@FindBy(xpath = "//span[normalize-space()='Place Order']")
	WebElement locator_placeOrderButton;

	public @FindBy(xpath = "//span[contains(normalize-space(),'Thank you for your purchase!')]")
	WebElement locator_thanksMsg;

	public @FindBy(xpath = "//main[@id='maincontent']//p[1]")
	WebElement locator_orderNumber;

	@FindBy(xpath = "//div[@class='panel header']//button[@type='button']")
	WebElement locator_accountPanelArrow;

	@FindBy(xpath = "//div[@aria-hidden='false']//a[normalize-space()='My Account']")
	WebElement locator_myAccount;

	public @FindBy(xpath = "//div[normalize-space()='Shipping Methods']")
	WebElement locator_shippingMethod;

	public @FindBy(xpath = "//div[@aria-expanded='false']//strong")
	List<WebElement> locator_productDisplayList;
	
	public @FindBy(xpath = "//div[@style='display: block;']")
	WebElement locator_displayProductSummary;

	public @FindBy(xpath = "//strong[@class='product-item-name'][contains(text(),'Montana Wind Jacket')]")
	WebElement locator_montana_product_name;

	public @FindBy(xpath = "//strong[normalize-space()='Montana Wind Jacket']/parent::div/following-sibling::div[@class='subtotal']")
	WebElement locator_montana_product_price;

	@FindBy(xpath = "//strong[@class='product-item-name'][contains(text(),'Lando Gym Jacket')]")
	WebElement locator_landoProductName;

	@FindBy(xpath = "//strong[normalize-space()='Lando Gym Jacket']/parent::div/following-sibling::div[@class='subtotal']")
	WebElement locator_landoProductPrice;

	@FindBy(xpath = "//strong[@class='product-item-name'][contains(text(),'Zeppelin Yoga Pant')]")
	WebElement locator_zeppelinProductName;

	@FindBy(xpath = "//strong[normalize-space()='Zeppelin Yoga Pant']/parent::div/following-sibling::div[@class='subtotal']")
	WebElement locator_zeppelinProductPrice;

	public String getMontanaProductName() {
		String productNameText = locator_montana_product_name.getText();
		return productNameText;
	}
	public String getMontanaProductPrice() {
		String productPriceText = locator_montana_product_price.getText();
		return productPriceText;
	}
	public String getLandoProductName() {
		String productNameText = locator_landoProductName.getText();
		return productNameText;
	}
	public String getLandoProductPrice() {
		String productPriceText = locator_landoProductPrice.getText();
		return productPriceText;
	}
	public String getZeppelinProductName() {
		String productNameText = locator_zeppelinProductName.getText();
		return productNameText;
	}
	public String getZeppelinProductPrice() {
		String productPriceText = locator_zeppelinProductPrice.getText();
		return productPriceText;
	}
	public void expandOrderSummary(){
		TestUtils.waitForElementPresent(locator_placeOrderButton, 5);
		clickOn(driver, locator_orderSummary, 2);
	}
	public void selectShippingMethodMoveAndPlaceOrder(){
		clickOn(driver, locator_selectShippingMethod, 2);
		clickOn(driver, locator_nextButton, 2);
		TestUtils.waitForElementPresent(locator_placeOrderButton, 5);
		TestUtils.clickElementByJS(locator_placeOrderButton, driver);

	}
	public void goToMyAccount(){
		TestUtils.waitForElementPresent(locator_accountPanelArrow, 5);
		clickOn(driver, locator_accountPanelArrow, 2);
		clickOn(driver, locator_myAccount, 2);
	}
	public String fetchOrderNumber(){
		String orderNumberMessage = locator_orderNumber.getText();
		int colonIndex = orderNumberMessage.indexOf(':');
		String orderNumber = orderNumberMessage.substring(colonIndex + 2);
		String finalOrderNumber = orderNumber.replace(".", "");
		return finalOrderNumber;
	}
}
