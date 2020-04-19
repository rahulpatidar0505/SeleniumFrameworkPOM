package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.PO_AutomationPractice;
import utility.BaseClass;

public class AutomationPracticeTest extends BaseClass {

	@Test
	public void practiceFormTest() {

		PO_AutomationPractice automationPracticePO = PageFactory.initElements(driver, PO_AutomationPractice.class);
		automationPracticePO.fillPracticeForm();
	}

	@Test
	public void assertiontest() {

		Assert.assertFalse(true);
	}
}
