package Azij.ExtentReports;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportsDemo {
	
	ExtentReports extent;
	WebDriver driver;
	@BeforeTest
	public void config() {
		//ExtentReports , // ExtentSparkReporter
		String Path = System.getProperty("user.dir")+ "\\Reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(Path);
		reporter.config().setReportName("WebAutomationResult");
		reporter.config().setDocumentTitle("TestResult");
		
		extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "mdAzij");
	}
	
	@Test
	public void initialDemo() {
		ExtentTest test = extent.createTest("index.html");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/");
		System.out.println(driver.getTitle());
		driver.close();
//		test.fail("Result do not match");
		extent.flush();
	}
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File file = new File(System.getProperty("user.dir") + "//Reports//" + testCaseName + timestamp + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//Reports//" + testCaseName + timestamp + ".png";
	}

}
