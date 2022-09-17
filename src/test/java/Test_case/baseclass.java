package Test_case;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class baseclass {

WebDriver driver;
	
	XSSFWorkbook wbook;
	XSSFSheet sheet;
	
	ExtentReports report;
	ExtentTest test;
	
	@BeforeTest
	public void DataSetUp() throws IOException {
		
		FileInputStream fis =new FileInputStream("exceldata.xlsx");
	
		this.wbook=new XSSFWorkbook(fis);
		this.sheet= wbook.getSheet("sheet1");
		
		report = new ExtentReports("ExtentReports");
		
		
	}
	
	@AfterTest
	public void dataClean() throws IOException {
		wbook.close();
		
		report.flush();//memory clean
		report.close();
	
	}
	
	@BeforeMethod
	public void SetUp(Method method) { //every method it will go like test1,2,3
		
		test=report.startTest(method.getName());
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.get("https://www.simplilearn.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
	}
	
	
	@AfterMethod
	public void TearDown() {
		
		
		report.endTest(test);
		
		//Step7: Close the browser
		driver.close();
	}

}




