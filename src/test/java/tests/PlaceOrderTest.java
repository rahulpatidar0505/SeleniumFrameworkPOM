package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.PO_Checkout;
import pages.PO_CustomerAccount;
import pages.PO_MyOrder;
import pages.PO_SignIn;
import utility.BaseClass;
import utility.TestUtils;

import java.util.List;
import java.util.Map;

public class PlaceOrderTest extends BaseClass {

	String emailId = "test0505@gmail.com";
	String password = "Test@0505";
	String montana_jacket_details[] = {"Montana Wind Jacket", "M", "Green"};
	String lando_jacket_details[] = {"Lando Gym Jacket", "L", "Blue"};
	String zeppelin_pant_details[] = {"Zeppelin Yoga Pant", "32", "Red"};
	String thanks_msg = "Thank you for your purchase!";
	String customer_account_rul = "https://magento.softwaretestingboard.com/customer/account/";
	String my_order_rul = "https://magento.softwaretestingboard.com/sales/order/history/";

	@Test
	public void loginToApplicationAndPlaceAnOrder() {
		PO_SignIn poSignIn = PageFactory.initElements(driver, PO_SignIn.class);
		PO_CustomerAccount poCustomerAccount = PageFactory.initElements(driver, PO_CustomerAccount.class);
		PO_Checkout poCheckout = PageFactory.initElements(driver, PO_Checkout.class);
		PO_MyOrder poMyOrder = PageFactory.initElements(driver, PO_MyOrder.class);

		poSignIn.clickOnSignInLink();
		poSignIn.customerLogin(emailId, password);
//		poCustomerAccount.verifySuccessfulLogin();

		poCustomerAccount.selectJacket();
		poCustomerAccount.selectSpecificProduct(montana_jacket_details[0], montana_jacket_details[1], montana_jacket_details[2]);
		poCustomerAccount.selectSpecificProduct(lando_jacket_details[0], lando_jacket_details[1], lando_jacket_details[2]);

		poCustomerAccount.selectPant();
		poCustomerAccount.selectSpecificProduct(zeppelin_pant_details[0], zeppelin_pant_details[1], zeppelin_pant_details[2]);

		poCustomerAccount.goToCartAndCheckout();

		TestUtils.waitForElementPresent(poCheckout.locator_shippingMethod, 5);
		poCheckout.expandOrderSummary();

//		poCustomerAccount.verifyProductAndPrice("Montana Wind Jacket", 49.00);

		poCheckout.selectShippingMethodMoveAndPlaceOrder();
		Assert.assertEquals(poCheckout.locator_thanksMsg.getText(), thanks_msg);

		poCheckout.verifyPlacedOrder();
		Assert.assertEquals(poCustomerAccount.locator_myAccountText.getText(), "My Account");
		String current_url = driver.getCurrentUrl();
		Assert.assertEquals(current_url, customer_account_rul);

		poCustomerAccount.goToMyOrders();
		Assert.assertEquals(poMyOrder.locator_myOrderText.getText(), "My Orders");
		String current_url_new = driver.getCurrentUrl();
		Assert.assertEquals(current_url_new, my_order_rul);

	}

}
