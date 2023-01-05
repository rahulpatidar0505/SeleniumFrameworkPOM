package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utility.BaseClass;

import java.util.List;

public class PO_MyOrder extends BaseClass {

	public PO_MyOrder() {
		
	}
	public @FindBy(xpath = "//span[normalize-space()='My Orders']")
	WebElement locator_myOrderText;

	@FindBy(xpath = "//table[@id=\"my-orders-table\"]//tr")
	List<WebElement> orderRowList;

	public void verifySubmittedOrder(String orderNumber) {
		String beforeXpath="//table[@id=\"my-orders-table\"]//tr[";
		String afterXpath="]/td[1]";

		for (int i = 1; i <=orderRowList.size(); i++)
		{
			String finalXpath=beforeXpath+i+afterXpath;
			String text=driver.findElement(By.xpath(finalXpath)).getText();
			if (text.equalsIgnoreCase(orderNumber)) {
				System.out.println("Order present in order table");
				break;
			}
			else{
				System.out.println("Order not present in order table");
			}
		}
	}

}
