package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.*;
import utility.BaseClass;
import utility.TestUtils;

import java.util.List;
import java.util.Map;

public class PlaceOrderTest extends BaseClass {

	String emailId = "test0505@gmail.com";
	String password = "Test@0505";

	String address_details[] = {"Test", "Test1", "MyCom", "1234567", "Street0", "Street1", "Pune", "553", "12345", "IN"};
	String montana_jacket_details[] = {"Montana Wind Jacket", "M", "Green"};
	String lando_jacket_details[] = {"Lando Gym Jacket", "L", "Blue"};
	String zeppelin_pant_details[] = {"Zeppelin Yoga Pant", "32", "Red"};
	String thanks_msg = "Thank you for your purchase!";
	String my_account_text = "My Account";
	String my_order_text = "My Orders";
	String customer_account_rul = "https://magento.softwaretestingboard.com/customer/account/";
	String my_order_rul = "https://magento.softwaretestingboard.com/sales/order/history/";

	/**
	 * Test Scenario: Login to application and place an order
	 * Test Steps:
	 * 	1. Login to magento application using registered user(Criteria 1).
	 * 	2. Address is already added, so updating address(Criteria 5)
	 * 	3. Add 2 different jacket, 1 pants and go to cart(Criteria 2, Criteria 3)
	 * 	4. Verify order summary(Criteria 4)
	 * 	5. Select shipping method and place an order(Criteria 6, Criteria 7)
	 * 	6. Verify submitted order using orderNumber(Criteria 8)
	 */
	@Test
	public void loginToApplicationAndPlaceAnOrder() {
		PO_SignIn poSignIn = PageFactory.initElements(driver, PO_SignIn.class);
		PO_CustomerAccount poCustomerAccount = PageFactory.initElements(driver, PO_CustomerAccount.class);
		PO_Checkout poCheckout = PageFactory.initElements(driver, PO_Checkout.class);
		PO_MyOrder poMyOrder = PageFactory.initElements(driver, PO_MyOrder.class);
		PO_CustomerAddress poCustomerAddress = PageFactory.initElements(driver, PO_CustomerAddress.class);

		poSignIn.clickOnSignInLink();
		poSignIn.customerLogin(emailId, password);

		poCheckout.verifyPlacedOrder();
		poCustomerAddress.editAddressDetails(address_details[0], address_details[1], address_details[2], address_details[3],
				address_details[4], address_details[5], address_details[6], address_details[7], address_details[8],
				address_details[9]);

		poCustomerAccount.selectJacket();
		poCustomerAccount.selectSpecificProduct(montana_jacket_details[0], montana_jacket_details[1], montana_jacket_details[2]);
		clickOn(driver, poCustomerAccount.locator_addToCartButton, 2);

		String montanaProductNameTextFromAccount = poCustomerAccount.getMontanaProductName();
		String montanaProductPriceTextFromAccount = poCustomerAccount.getMontanaProductPrice();

		poCustomerAccount.selectJacket();
		poCustomerAccount.selectSpecificProduct(lando_jacket_details[0], lando_jacket_details[1], lando_jacket_details[2]);
		clickOn(driver, poCustomerAccount.locator_addToCartButton, 2);

		String landoProductNameTextFromAccount = poCustomerAccount.getLandoProductName();
		String landoProductPriceTextFromAccount = poCustomerAccount.getLandoProductPrice();

		poCustomerAccount.selectPant();
		poCustomerAccount.selectSpecificProduct(zeppelin_pant_details[0], zeppelin_pant_details[1], zeppelin_pant_details[2]);
		clickOn(driver, poCustomerAccount.locator_addToCartButton, 2);

		String zeppelinProductNameTextFromAccount = poCustomerAccount.getZeppelinProductName();
		String zeppelinProductPriceTextFromAccount = poCustomerAccount.getZeppelinProductPrice();
		poCustomerAccount.goToCartAndCheckout();

		TestUtils.waitForElementPresent(poCheckout.locator_shippingMethod, 5);
		poCheckout.expandOrderSummary();

		TestUtils.waitForElementPresent(poCheckout.locator_displayProductSummary, 5);

		String montanaProductNameTextFromCheckout = poCheckout.getMontanaProductName();
		String montanaproductPriceTextFromCheckout  = poCheckout.getMontanaProductPrice();

		String landoProductNameTextFromCheckout  = poCheckout.getLandoProductName();
		String landoproductPriceTextFromCheckout  = poCheckout.getLandoProductPrice();

		String zeppelinProductNameTextFromCheckout  = poCheckout.getZeppelinProductName();
		String zeppelinProductPriceTextFromCheckout  = poCheckout.getZeppelinProductPrice();

		Assert.assertEquals(montanaProductNameTextFromAccount, montanaProductNameTextFromCheckout);
		Assert.assertEquals(montanaProductPriceTextFromAccount, montanaproductPriceTextFromCheckout);

		Assert.assertEquals(landoProductNameTextFromAccount, landoProductNameTextFromCheckout);
		Assert.assertEquals(landoProductPriceTextFromAccount, landoproductPriceTextFromCheckout);

		Assert.assertEquals(zeppelinProductNameTextFromAccount, zeppelinProductNameTextFromCheckout);
		Assert.assertEquals(zeppelinProductPriceTextFromAccount, zeppelinProductPriceTextFromCheckout);


		poCheckout.selectShippingMethodMoveAndPlaceOrder();
		Assert.assertEquals(poCheckout.locator_thanksMsg.getText(), thanks_msg);

		String orderNumber = poCheckout.fetchOrderNumber();

		poCheckout.verifyPlacedOrder();
		Assert.assertEquals(poCustomerAccount.locator_myAccountText.getText(), my_account_text);
		String current_url = driver.getCurrentUrl();
		Assert.assertEquals(current_url, customer_account_rul);

		poCustomerAccount.goToMyOrders();
		Assert.assertEquals(poMyOrder.locator_myOrderText.getText(), my_order_text);
		String current_url_new = driver.getCurrentUrl();
		Assert.assertEquals(current_url_new, my_order_rul);

		poMyOrder.verifySubmittedOrder(orderNumber);
	}

}
