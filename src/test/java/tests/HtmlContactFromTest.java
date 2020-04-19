package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.PO_HtmlContactForm;
import utility.BaseClass;

public class HtmlContactFromTest extends BaseClass {

	@Test
	public void htmlContactFormTest() {

		PO_HtmlContactForm htmlContactForm = PageFactory.initElements(driver, PO_HtmlContactForm.class);
		htmlContactForm.fillForm();
		Assert.assertEquals(htmlContactForm.pageTitle.getText(), "Oops! That page can’t be found.");
	}

}
