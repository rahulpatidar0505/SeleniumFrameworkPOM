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
		list.add("/Users/rahulpatidar/Documents/SeleniumFrameworkPOM/testng.xml");
		runner.setTestSuites(list);
		runner.run();
	}
}
