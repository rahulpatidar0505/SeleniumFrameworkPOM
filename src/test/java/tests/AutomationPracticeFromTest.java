package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.PO_AutomationPracticeForm;
import utility.BaseClass;

public class AutomationPracticeFromTest extends BaseClass {

	@Test
	public void automationPracticeFormTest() {
		PO_AutomationPracticeForm automationPracticeFromPO = PageFactory.initElements(driver,
				PO_AutomationPracticeForm.class);
		automationPracticeFromPO.fillForm();

	}
	
	@Test
	public void assertionFailTest() {

		Assert.assertFalse(true);
	}
	
	@Test(dependsOnMethods = "assertionFailTest")
	public void assertionSkipTest() {

		Assert.assertFalse(true);
	}
}
