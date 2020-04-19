package utility;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.Test;

public class TestRunner {

	@Test
	public void test() {
		TestNG runner = new TestNG();
		List<String> list = new ArrayList<String>();
		list.add("C:\\Users\\rahul.patidar\\Documents\\Learning\\SeleniumFrameworkPOM\\testng.xml");
//		list.add("C:\\Users\\rahul.patidar\\Documents\\Learning\\SeleniumFrameworkPOM\\testng1.xml");
		runner.setTestSuites(list);
		runner.run();
	}
}
