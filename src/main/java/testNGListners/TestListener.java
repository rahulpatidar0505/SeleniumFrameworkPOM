package testNGListners;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import extenReports.ExtentReporterNG;

public class TestListener implements ITestListener {

	WebDriver driver;
	ExtentTest test;
//	ExtentReports extent = ExtentReporterNG.extentReportGenerator();
	ExtentReports extent ;
	@Override
	public void onTestStart(ITestResult result) {
		
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source, new File("./Screenshots/" + result.getName() + ".png"));
				System.out.println("Screenshot taken");
			} catch (Exception e) {
				test.log(Status.FAIL, result.getThrowable());
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}
		driver.quit();
		extent.flush();
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ExtentReporterNG.extentReportGenerator();

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}
}
