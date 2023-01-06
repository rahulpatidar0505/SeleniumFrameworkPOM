package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.BaseClass;
import java.util.List;

public class PO_MyOrder extends BaseClass {
	public PO_MyOrder() {
	}
	public @FindBy(xpath = "//span[normalize-space()='My Orders']")
	WebElement locator_myOrderText;

	@FindBy(xpath = "//table[@id=\"my-orders-table\"]//tr")
	List<WebElement> orderRowList;

	public String verifySubmittedOrder() {
		String beforeXpath="//table[@id=\"my-orders-table\"]//tr[";
		String afterXpath="]/td[1]";
		String text=null;
		for (int i = 1; i <=orderRowList.size(); i++)
		{
			String finalXpath=beforeXpath+i+afterXpath;
			text=driver.findElement(By.xpath(finalXpath)).getText();
			break;
		}
		return text;
	}
}
