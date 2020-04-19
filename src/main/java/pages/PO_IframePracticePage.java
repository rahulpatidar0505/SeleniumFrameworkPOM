package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import utility.BaseClass;
import utility.TestUtils;

public class PO_IframePracticePage extends BaseClass {

	public PO_IframePracticePage() {
	}

	@FindBy(xpath = "//a[contains(text(),'IFrame practice page')]")
	WebElement iframePracticePageLink;

	@FindBy(xpath = "//h1[contains(text(),'Sample Iframe page')]")
	public WebElement sampleIframePage;

	@FindBy(xpath = "//iframe[@id='IF2']")
	WebElement iframeValue;

	@FindBy(xpath = "//a[contains(text(),'Draggable')]")
	WebElement draggableinsideIframe;

	public void frameCheck() {
		clickOn(driver, iframePracticePageLink, 2);
		Assert.assertEquals(sampleIframePage.getText(), "Sample Iframe page");
		TestUtils.switchToFrameByElement(iframeValue, 2);
		Assert.assertEquals(draggableinsideIframe.getText(), "Draggable");
	}

}
