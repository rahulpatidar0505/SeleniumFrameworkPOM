package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.BaseClass;

public class PO_MyOrder extends BaseClass {

	public PO_MyOrder() {
		
	}
	public @FindBy(xpath = "//span[normalize-space()='My Orders']")
	WebElement locator_myOrderText;


	public void verifySubmittedOrder(String emailId, String passowrd) {

	}

}
