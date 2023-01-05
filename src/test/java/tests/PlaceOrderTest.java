package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.*;
import utility.BaseClass;
import utility.TestConstant;
import utility.TestUtils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlaceOrderTest extends BaseClass {
	public static String randStr = RandomStringUtils.randomAlphabetic(2);
	public String timeStamp = new SimpleDateFormat("dd_MMM_yy_HHmmss").format(Calendar.getInstance().getTime());
	ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(
			System.getProperty("user.dir") + "/Reports/MyReport" + "_" + randStr + "_" + timeStamp + ".html");
	ExtentReports extentReports = new ExtentReports();
	ExtentTest test;
	@BeforeSuite
	public void suiteSetup() {
		extentSparkReporter.config().setDocumentTitle("Test Results");
		extentSparkReporter.config().setReportName("Automation Results");
		extentSparkReporter.config().setTheme(Theme.DARK);

		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Author", "Rahul Patidar");
		extentReports.setSystemInfo("OS", "Windows");
		extentReports.setSystemInfo("Host Name", "Rahul");
		extentReports.setSystemInfo("Environment", "QA");
	}
	@BeforeTest
	public void testSetup(ITestContext result) {
		initialisation();
	}
	@BeforeMethod
	public void methodSetup(ITestResult result) {
		test = extentReports.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO, "Test started : " + result.getMethod().getMethodName());
	}
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
		test.log(Status.INFO, "Initialize all page object");
		PO_SignIn poSignIn = PageFactory.initElements(driver, PO_SignIn.class);
		PO_CustomerAccount poCustomerAccount = PageFactory.initElements(driver, PO_CustomerAccount.class);
		PO_Checkout poCheckout = PageFactory.initElements(driver, PO_Checkout.class);
		PO_MyOrder poMyOrder = PageFactory.initElements(driver, PO_MyOrder.class);
		PO_CustomerAddress poCustomerAddress = PageFactory.initElements(driver, PO_CustomerAddress.class);

		test.log(Status.INFO, "Criteria 1: Login to magento application using registered user");
		poSignIn.clickOnSignInLink();
		poSignIn.customerLogin(TestUtils.emailId, TestUtils.password);

		test.log(Status.INFO, "Criteria 5: Go to my account and update address");
		poCheckout.goToMyAccount();
		poCustomerAddress.editAddressDetails(TestConstant.address_details[0], TestConstant.address_details[1], TestConstant.address_details[2],
				TestConstant.address_details[3], TestConstant.address_details[4],
				TestConstant.address_details[5], TestConstant.address_details[6], TestConstant.address_details[7],
				TestConstant.address_details[8], TestConstant.address_details[9]);

		test.log(Status.INFO, "Criteria 2,3: Add 2 jacket, 1 pant, verify the success message and fetch product name and price, go to cart and checkout the same");
		poCustomerAccount.selectJacket();
		poCustomerAccount.selectSpecificProduct(TestConstant.montana_jacket_details[0],
				TestConstant.montana_jacket_details[1], TestConstant.montana_jacket_details[2]);
		clickOn(driver, poCustomerAccount.locator_addToCartButton, 2);
		Assert.assertEquals(poCustomerAccount.getProductAddedMsg(),
				"You added Montana Wind Jacket to your shopping cart.");

		String montanaProductNameTextFromAccount = poCustomerAccount.getMontanaProductName();
		String montanaProductPriceTextFromAccount = poCustomerAccount.getMontanaProductPrice();

		poCustomerAccount.selectJacket();
		poCustomerAccount.selectSpecificProduct(TestConstant.lando_jacket_details[0], TestConstant.lando_jacket_details[1],
				TestConstant.lando_jacket_details[2]);
		clickOn(driver, poCustomerAccount.locator_addToCartButton, 2);
		Assert.assertEquals(poCustomerAccount.getProductAddedMsg(),
				"You added Lando Gym Jacket to your shopping cart.");

		String landoProductNameTextFromAccount = poCustomerAccount.getLandoProductName();
		String landoProductPriceTextFromAccount = poCustomerAccount.getLandoProductPrice();

		poCustomerAccount.selectPant();
		poCustomerAccount.selectSpecificProduct(TestConstant.zeppelin_pant_details[0], TestConstant.zeppelin_pant_details[1],
				TestConstant.zeppelin_pant_details[2]);
		clickOn(driver, poCustomerAccount.locator_addToCartButton, 2);
		Assert.assertEquals(poCustomerAccount.getProductAddedMsg(),
				"You added Zeppelin Yoga Pant to your shopping cart.");

		String zeppelinProductNameTextFromAccount = poCustomerAccount.getZeppelinProductName();
		String zeppelinProductPriceTextFromAccount = poCustomerAccount.getZeppelinProductPrice();

		test.log(Status.INFO, "Go to cart and checkout all added product");
		poCustomerAccount.goToCartAndCheckout();

		test.log(Status.INFO, "Criteria 4:  Verify order summary and compare product and price");
		TestUtils.waitForElementPresent(poCheckout.locator_shippingMethod, 5);
		poCheckout.expandOrderSummary();
// 		TestUtils.waitForElementPresent(poCheckout.locator_displayProductSummary, 5);

		String montanaProductNameTextFromCheckout = poCheckout.getMontanaProductName();
		String montanaproductPriceTextFromCheckout  = poCheckout.getMontanaProductPrice();

		String landoProductNameTextFromCheckout  = poCheckout.getLandoProductName();
		String landoproductPriceTextFromCheckout  = poCheckout.getLandoProductPrice();

		String zeppelinProductNameTextFromCheckout  = poCheckout.getZeppelinProductName();
		String zeppelinProductPriceTextFromCheckout  = poCheckout.getZeppelinProductPrice();

		test.log(Status.INFO, "Compare 'Montana Wind Jacket' product and price from account page to checkout page");
		Assert.assertEquals(montanaProductNameTextFromAccount, montanaProductNameTextFromCheckout);
		Assert.assertEquals(montanaProductPriceTextFromAccount, montanaproductPriceTextFromCheckout);

		test.log(Status.INFO, "Compare 'Lando Gym Jacket' product and price from account page to checkout page");
		Assert.assertEquals(landoProductNameTextFromAccount, landoProductNameTextFromCheckout);
		Assert.assertEquals(landoProductPriceTextFromAccount, landoproductPriceTextFromCheckout);

		test.log(Status.INFO, "Compare 'Zeppelin Yoga Pant' product and price from account page to checkout page");
		Assert.assertEquals(zeppelinProductNameTextFromAccount, zeppelinProductNameTextFromCheckout);
		Assert.assertEquals(zeppelinProductPriceTextFromAccount, zeppelinProductPriceTextFromCheckout);

		test.log(Status.INFO, "Criteria 6,7:  Select shipping address, place an order and fetch order number");
		poCheckout.selectShippingMethodMoveAndPlaceOrder();
		Assert.assertEquals(poCheckout.locator_thanksMsg.getText(), TestConstant.thanks_msg);
		String orderNumber = poCheckout.fetchOrderNumber();

		test.log(Status.INFO, "Criteria 8: Go to my order and verify submitted order using orderNumber");
		poCheckout.goToMyAccount();
		Assert.assertEquals(poCustomerAccount.locator_myAccountText.getText(), TestConstant.my_account_text);
		String current_url = driver.getCurrentUrl();
		Assert.assertEquals(current_url, TestConstant.customer_account_rul);

		poCustomerAccount.goToMyOrders();
		Assert.assertEquals(poMyOrder.locator_myOrderText.getText(), TestConstant.my_order_text);
		String current_url_new = driver.getCurrentUrl();
		Assert.assertEquals(current_url_new, TestConstant.my_order_rul);

		poMyOrder.verifySubmittedOrder(orderNumber);
	}
	@AfterMethod
	public void methodTeardown(ITestResult result) throws IOException, InterruptedException {
		String temp = captureScreenshot(result.getName());
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL,
					"Test Failed : "+"  "+ test.addScreenCaptureFromPath(temp));
			test.log(Status.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Skipped ");
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Passed : " + result.isSuccess());
		}
		test.log(Status.INFO, "Test Ended : " + result.getMethod().getMethodName());
	}
	@AfterTest
	public void testTeardown() {
		termination();
	}
	@AfterSuite
	public void suiteTeardown() {
		extentReports.flush();
	}
}
