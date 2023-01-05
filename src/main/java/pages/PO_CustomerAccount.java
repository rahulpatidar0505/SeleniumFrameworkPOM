package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utility.BaseClass;
import utility.TestUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PO_CustomerAccount extends BaseClass {

	public PO_CustomerAccount() {
		
	}

	@FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
	WebElement locator_thanksMsg;

	@FindBy(xpath = "//span[normalize-space()='Men']")
	WebElement locator_men;

	@FindBy(xpath = "//a[contains(@href,'tops-men.html')]//span[contains(text(),'Tops')]")
	WebElement locator_tops;

	@FindBy(xpath = "//a[contains(@href,'jackets-men.html')]//span[contains(text(),'Jackets')]")
	WebElement locator_jackets;

	@FindBy(xpath = "//a[contains(@href,'bottoms-men.html')]//span[contains(text(),'Bottoms')]")
	WebElement locator_bottoms;

	@FindBy(xpath = "//a[contains(@href,'pants-men.html')]//span[contains(text(),'Pants')]")
	WebElement locator_pants;

	@FindBy(css = ".product.name.product-item-name")
	List<WebElement> productList;

	@FindBy(xpath = "//div[@class='swatch-option text'][@role='option']")
	List<WebElement> productSizeList;

	@FindBy(xpath = "//div[@class='swatch-option color']")
	List<WebElement> productColorList;

	@FindBy(xpath = "//span[normalize-space()='Add to Cart']")
	public
	WebElement locator_addToCartButton;

	@FindBy(xpath = "//a[@class='action showcart']")
	WebElement locator_cartLink;

	@FindBy(xpath = "//button[@id='top-cart-btn-checkout']")
	WebElement locator_checkout;

	public @FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement locator_myAccountText;

	public @FindBy(xpath = "//a[normalize-space()='My Orders']")
	WebElement locator_myOrder;

	@FindBy(xpath = "//span[contains(text(),'Montana Wind Jacket')]")
	WebElement locator_montanaProductName;

	@FindBy(xpath = "//span[@itemprop='offers']//span[@class='price-label'][normalize-space()='As low as']/following-sibling::span//span[@class='price']")
	WebElement locator_montanaProductPrice;

	@FindBy(xpath = "//span[contains(text(),'Lando Gym Jacket')]")
	WebElement locator_landoProductName;

	@FindBy(xpath = "//span[@itemprop='offers']//span[@class='price-label'][normalize-space()='As low as']/following-sibling::span//span[@class='price']")
	WebElement locator_landoProductPrice;

	@FindBy(xpath = "//span[contains(text(),'Zeppelin Yoga Pant')]")
	WebElement locator_zeppelinProductName;

	@FindBy(xpath = "//span[@class='price-label'][normalize-space()='As low as']/following-sibling::span//span[@class='price']")
	WebElement locator_zeppelinProductPrice;

	Actions action=new Actions(driver);
	public void selectJacket() {
		action.moveToElement(locator_men).build().perform();
		TestUtils.scrollIntoView(locator_tops, driver);
		TestUtils.clickElementByJS(locator_jackets, driver);
	}

	public void selectPant() {
		action.moveToElement(locator_men).build().perform();
		TestUtils.scrollIntoView(locator_bottoms, driver);
		TestUtils.clickElementByJS(locator_pants, driver);
	}

	public void selectProductName(String productName) {
		for (WebElement product : productList) {
			if (product.getText().contains(productName)) {
				product.click();
				break;
			}
		}
	}

	public String getMontanaProductName() {
		String productNameText = locator_montanaProductName.getText();
		return productNameText;
	}
	public String getMontanaProductPrice() {
		String productPriceText = locator_montanaProductPrice.getText();
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

//	public void verifyProductAndPrice(String productName, String priceValue) {
//		String productNameText = getMontanaProductName(productName);
//		String productPriceText = getMontanaProductPrice(priceValue);
//
////		int indexOfDollarSign = productPriceText.indexOf('$');
////		String price = productPriceText.substring(indexOfDollarSign + 1);
////
////		float finalPriceValue = Float.parseFloat(price);
//
//		if (productNameText.equals(productName) && productPriceText.equals(priceValue)) {
//			System.out.println("Order summary is correct");
//		} else {
//			System.out.println("Order summary is incorrect");
//		}
//	}

	public void selectProductSize(String productSize) {
		for (WebElement size : productSizeList) {
			if (size.getText().contains(productSize)) {
				size.click();
				break;
			}
		}
	}

	public void selectProductColor(String productColor) {
		for (WebElement color : productColorList) {
			if (color.getAttribute("aria-label").contains(productColor)) {
				color.click();
				break;
			}
		}
	}

//	public void selectSpecificProduct(String productName, String productSize, String productColor){
//		selectProductName(productName);
//		selectProductSize(productSize);
//		selectProductColor(productColor);
//		clickOn(driver, locator_addToCartButton, 2);
//	}

	public void selectSpecificProduct(String productName, String productSize, String productColor){
		selectProductName(productName);
		selectProductSize(productSize);
		selectProductColor(productColor);
//		clickOn(driver, locator_addToCartButton, 2);
	}
	public void goToCartAndCheckout(){
		TestUtils.waitForElementPresent(locator_cartLink, 5);
		clickOn(driver, locator_cartLink, 2);
		clickOn(driver, locator_checkout, 2);
	}

	public void goToMyOrders(){
		TestUtils.waitForElementPresent(locator_myOrder, 5);
		clickOn(driver, locator_myOrder, 2);
	}
}

