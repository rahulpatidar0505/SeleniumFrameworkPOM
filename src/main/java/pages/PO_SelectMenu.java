package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import utility.BaseClass;
import utility.TestUtils;

public class PO_SelectMenu extends BaseClass {

	public PO_SelectMenu() {
	}

	@FindBy(xpath = "//a[contains(text(),'Selectmenu')]")
	WebElement selectmenu;
	
	@FindBy(xpath = "//span[@id='speed-button']")
	WebElement speedDropdown;
	
	@FindBy(xpath = "//span[@id='files-button']")
	WebElement fileDropdown;
	
	@FindBy(xpath = "//span[@id='number-button']")
	WebElement umberDropdown;
	
	@FindBy(xpath = "//span[@id='salutation-button']")
	WebElement salutationDropdown;

	public void dropdwonCheck() {

	}

}
