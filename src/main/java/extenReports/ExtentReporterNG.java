package extenReports;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.RandomStringUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static String randStr = RandomStringUtils.randomAlphabetic(2);
	public static String timeStamp = new SimpleDateFormat("dd_MMM_yy_HHmmss").format(Calendar.getInstance().getTime());
	
	public static ExtentReports extentReportGenerator() {
		
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "./Reports/MyReport" + "_" + randStr + "_" + timeStamp + ".html");
		extentSparkReporter.config().setDocumentTitle("Test Results");
		extentSparkReporter.config().setReportName("Automation Results");

		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Author", "Rahul Patidar");
		extentReports.setSystemInfo("OS", "Windows");
		extentReports.setSystemInfo("Host Name", "Rahul");
		extentReports.setSystemInfo("Environment", "QA");
		return extentReports;

	}
}
