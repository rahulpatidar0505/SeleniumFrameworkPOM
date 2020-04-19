package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import pages.PO_IframePracticePage;
import utility.BaseClass;

public class IFramePracticeTest extends BaseClass {

	@Test
	public void iframePracticeTest() {

		PO_IframePracticePage iframePracticePage = PageFactory.initElements(driver, PO_IframePracticePage.class);
		iframePracticePage.frameCheck();
	}

}
