package MavenProject.SeleniumTestNGProject;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TC01_BaiscSeleniumInteractions {
  WebDriver driver;
  String url = "https://www.selenium.dev/selenium/web/web-form.html";
  ExtentReports extent = new ExtentReports();
  ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
  
  @Test (priority = 0, enabled = true)
  public void TInput() {
	  
	  ExtentTest test = extent.createTest("Launch Browser Website").assignAuthor("Srikant").assignCategory("Regression TEst").assignDevice("Chrome");
	  
	  
	  String Title = driver.getTitle();
	  assertEquals(Title, "Web form");
	  
	  driver.findElement(By.id("my-text-id")).sendKeys("HI THis is TExt Input");
	  driver.findElement(By.xpath("/html/body/main/div/form/div/div[1]/label[2]/input")).sendKeys("PasswordIS");
	  driver.findElement(By.xpath("//*[@class='form-control' and @name='my-textarea']")).sendKeys("Hi this is going to be good to learn");
	  
	  Boolean enabled = driver.findElement(By.xpath("//*[@class='form-control' and @name='my-disabled']")).isEnabled();
	  System.out.println("Disabled text is :" + enabled);
	  Assert.assertFalse(enabled);
	  
	  String readonlyField = driver.findElement(By.xpath("//*[@class='form-control' and @name='my-readonly']")).getAttribute("readonly");
	  System.out.println("Text box is readonlyField:" + readonlyField);
	  Assert.assertEquals(readonlyField, "true");
	  
	  test.log(Status.PASS, "User Launched Website");
	  test.pass("User Launched Website verified");
	  
  }
  
  @Test (priority = 1)
  public void linkTextCount () throws InterruptedException {
	  
	  ExtentTest test = extent.createTest("linktext count testing ").assignAuthor("Kajal").assignCategory("Regression TEst 2").assignDevice("EDGE");;
	  test.info("linktext Count");
	  test.pass("linktext total count is successful");
	  test.warning("Linktext is not possible");
	  driver.findElement(By.linkText("Return to index")).click();
	  Thread.sleep(2000);
	  List<WebElement> WebElementLink = driver.findElements(By.tagName("a"));
	  System.out.println("Number of link present in the page is : "+ WebElementLink.size());
	 
	  /*
	  for(WebElement wbl: WebElementLink) {
		  System.out.println(wbl.getText());
	  }*/
	  Assert.assertEquals(WebElementLink.size(),414);
	  driver.navigate().back();
  }
	@Test(priority = 2)
	public void dropDownAndRadio() {
		ExtentTest test = extent.createTest("File upload testing ").assignAuthor("SK").assignCategory("Regression TEsts").assignDevice("Chrome v4");;
		  
		WebElement DDSelect = driver.findElement(By.xpath("//*[@class='form-select'][@name='my-select']"));
		Select ddSelectObj = new Select(DDSelect);
		List<WebElement> ddElements = ddSelectObj.getOptions();
		System.out.println("Number of options in dropdown are :" + ddElements.size());
		
		for (WebElement DDO : ddElements) {
			System.out.println(DDO.getText());
		}
		
		ddSelectObj.selectByIndex(3);
		
		//Data list handling
		
		driver.findElement(By.xpath("//*[@class='form-control'][@name='my-datalist']")).clear();
		driver.findElement(By.xpath("//*[@class='form-control'][@name='my-datalist']")).sendKeys("France");
		
		WebElement DDDatalist = driver.findElement(By.xpath("//*[@id='my-options']"));
		List<WebElement> DDDtemp = DDDatalist.findElements(By.tagName("option"));
		for (WebElement DDDL : DDDtemp) {
			System.out.println(DDDL.getAttribute("value"));
		}
		
		//File upload
		WebElement fileUpload = driver.findElement(By.xpath("//*[@class='form-control'][@name='my-file']"));
		fileUpload.sendKeys("C:\\AA\\Selenium.txt");
		driver.findElement(By.id("my-check-1")).click();
		
		driver.findElement(By.xpath("//*[@class='form-control form-control-color'][@name='my-colors']")).sendKeys("#000000");
		
		test.skip("file upload skipped");
		test.skip("file upload fail");
		 
		
	}
	
	@Test(priority = 3)
	public void finalExtentReport () {
		ExtentTest test = extent.createTest("Fail Testing ").assignAuthor("KS").assignCategory("Regression TEst").assignDevice("SAfari");;
		test.fail("filed test cases");
	}
	
	@Test(priority = 4)
	public void passExtentReport () {
		ExtentTest test = extent.createTest("LOG out of the application ").assignAuthor("Wojhoo").assignCategory("Regression TEst").assignDevice("Chrome");;
		test.pass("filed test cases is pass and log out is successful");
		test.info("filed TC are pass log out is successful");
	}
	
	
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\Srikant\\eclipse\\ChromeDriver\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.get(url);
	  driver.manage().window().maximize();
	  //driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
  }
  
  @BeforeTest
  public void btest() {
	  extent.attachReporter(spark);
  }

  @AfterClass
  public void afterClass() {
	 extent.flush();
	 driver.quit();
  }

}
