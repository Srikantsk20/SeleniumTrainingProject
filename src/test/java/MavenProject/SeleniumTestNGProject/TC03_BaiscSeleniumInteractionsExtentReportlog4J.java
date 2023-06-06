package MavenProject.SeleniumTestNGProject;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.BeforeTest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.JavascriptExecutor;
public class TC03_BaiscSeleniumInteractionsExtentReportlog4J {
  WebDriver driver;
  String url = "https://www.selenium.dev/selenium/web/web-form.html";
 // ExtentReports report;  
 // ExtentTest test;
  Logger log = Logger.getLogger(TC03_BaiscSeleniumInteractionsExtentReportlog4J.class);
  
  @Test (priority = 0)
  public void TInputER() throws IOException, InterruptedException {
	  log.info("*****************************Start of new test case************************************************");
	  String Title = driver.getTitle();
	  
	  try {
		  Assert.assertEquals(Title, "Web form");
		  // test.log(LogStatus.PASS, test.addScreenCapture(CaptureScreenShot(driver)) + "Title is Validated to be True"); 
		  log.info("Title is Validated to be True");
	  } catch (AssertionError e) {
		  // test.log(LogStatus.FAIL, test.addScreenCapture(CaptureScreenShot(driver)) + "Title Mismatch " + e.getMessage());
		  log.info("Title is in Valid");
	  }
	  
	  driver.findElement(By.id("my-text-id")).sendKeys("HI THis is TExt Input");
	  driver.findElement(By.xpath("/html/body/main/div/form/div/div[1]/label[2]/input")).sendKeys("PasswordIS");
	  driver.findElement(By.xpath("//*[@class='form-control' and @name='my-textarea']")).sendKeys("Hi this is going to be good to learn");
	  
	  Boolean enabled = driver.findElement(By.xpath("//*[@class='form-control' and @name='my-disabled']")).isEnabled();
	  System.out.println("Disabled text is :" + enabled);
	 
	  try {
		  Assert.assertFalse(enabled);
		  // test.log(LogStatus.PASS, test.addScreenCapture(CaptureScreenShot(driver)) + "Button is Enabled"); 
		  log.info("Field is Validated to be True");
	  } catch (AssertionError e) {
		  // test.log(LogStatus.FAIL, test.addScreenCapture(CaptureScreenShot(driver)) + "Button is Disabled " + e.getMessage());
		  log.info("Field is invalid");
	  }
	  
	  
	  String readonlyField = driver.findElement(By.xpath("//*[@class='form-control' and @name='my-readonly']")).getAttribute("readonly");
	  //System.out.println("Text box is readonlyField:" + readonlyField);
	 
	  // test.log(LogStatus.INFO, "Field should be read only");
	  Thread.sleep(2000);
	  try {
		  Assert.assertEquals(readonlyField, "true");
		  // test.log(LogStatus.PASS, test.addScreenCapture(CaptureScreenShot(driver)) + "Button is ReadOnly"); 
		  log.info("Field is Validated to be True");
	  } catch (AssertionError e) {
		  // test.log(LogStatus.FAIL, test.addScreenCapture(CaptureScreenShot(driver)) + "Button is not Readonly " + e.getMessage());
		  log.info("Field is invalid");
	  }
	  
	  
  }
  
  @Test (priority = 1)
  public void linkTextCountER () throws InterruptedException, IOException {
	  
	  // test.log(LogStatus.INFO, "Link Count in Web Page");
	  driver.findElement(By.linkText("Return to index")).click();
	  Thread.sleep(2000);
	  List<WebElement> WebElementLink = driver.findElements(By.tagName("a"));
	  System.out.println("Number of link present in the page is : "+ WebElementLink.size());
	  Thread.sleep(2000);
	  log.warn("This is warning");
	  try {
	  Assert.assertEquals(WebElementLink.size(),410);
	  // test.log(LogStatus.WARNING, "Number of links present in the website is More than Expected");
	  // test.log(LogStatus.PASS, test.addScreenCapture(CaptureScreenShot(driver)) + "Number of Links are as Expected test step is pass");
	  log.info("Everything is fine");
	  } catch (AssertionError e) {
	   // test.log(LogStatus.FAIL, test.addScreenCapture(CaptureScreenShot(driver)) + "Number of Links is not as what was expected" + e.getMessage());
		  log.fatal("Looks like something is not matching "+ e);
		  log.debug("Debug message" + e);
	  }
	  
	  //Scrolling function
	  ///html/body/ul/li[374]/a
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("window.scrollBy(0,6200)");
	  // test.log(LogStatus.PASS, test.addScreenCapture(CaptureScreenShot(driver)) + "See the view of the screen shot");
	  
	  
	  js.executeScript("window.scrollTo(top)");
	  // test.log(LogStatus.PASS, test.addScreenCapture(CaptureScreenShot(driver)) + "See the view of the scroll bar is at the top");
	  
	  WebElement linkView = driver.findElement(By.linkText("scrolling_tests/page_with_nested_scrolling_frames_out_of_view.html"));
	  js.executeScript("arguments[0].scrollIntoView(true);",linkView);
	  
	  // test.log(LogStatus.PASS, test.addScreenCapture(CaptureScreenShot(driver)) + "View of the page until the desired link");
	  
	  linkView.click();
	  	  
  }
	@Test(priority = 2)
	public void dropDownAndRadioER () throws IOException, InterruptedException {
		
		driver.navigate().to(url);
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
		driver.findElement(By.id("my-text-id")).click();
		
		String FullfileName = driver.findElement(By.xpath("//*[@class='form-control'][@name='my-file']")).getAttribute("value");
		File filepath  = new File(FullfileName);
		String fileName = filepath.getName();
		System.out.println("File Name is :" + fileName);
		// test.log(LogStatus.INFO, "File upload validation"); 
		  try {
			  Assert.assertEquals(fileName, "Selenium.txt");
			  // test.log(LogStatus.PASS, test.addScreenCapture(CaptureScreenShot(driver)) + "File is uploaded successfully"); 
		  } catch (AssertionError e) {
			  // test.log(LogStatus.FAIL, test.addScreenCapture(CaptureScreenShot(driver)) + "File upload failed " + e.getMessage());
		  }
		  log.info("Everything is fine");
	}
	
	@Test(priority = 3)
	public void CalendarOneER () throws InterruptedException {
		String yearmon = "August 2025";
		String day = "20";
		
		WebElement DateField = driver.findElement(By.xpath("//*[@class=\"form-control\"][@name=\"my-date\"]"));
		DateField.click();
		Thread.sleep(2000);
		
		while(true) {
		String CurrMonYearVal = driver.findElement(By.xpath("/html/body/div/div[1]/table/thead/tr[2]/th[2]")).getText();
		if (CurrMonYearVal.equals(yearmon)) {
			break;
		}
		else {
			driver.findElement(By.xpath("/html/body/div/div[1]/table/thead/tr[2]/th[3]")).click();
		}
		}
		
		driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr/td[contains(text(),"+day+")]")).click();
		 log.info("Everything is fine");
		
		// test.log(LogStatus.SKIP, "Not testing this step as part of this test case");
	}
	
	@Test(priority = 4)
	public void passExtentReportER () throws IOException, InterruptedException {
		
		driver.findElement(By.id("my-text-id")).click();
		 log.info("Everything is fine");
		 log.info("*****************************End of the test case************************************************");
	}
		

@BeforeTest
  public void btestER() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\Srikant\\eclipse\\ChromeDriver\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.get(url);
	  driver.manage().window().maximize();
	  //report = new ExtentReports("C:\\Users\\Srikant\\eclipse-workspace\\SeleniumTestNG\\SeleniumTestNGProject\\TestResultDocument\\TestResultReport.html",true);
	 // test = report.startTest("Test Case 01: Text Input in different fields");
	  //driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	 	  
  }
  @AfterTest
  public void aTestER() {
	driver.quit();
	  //report.endTest(test);
	 // report.flush();
	log.info("*****************************Browser is closed ************************************************");
  }
  
  public static String CaptureScreenShot(WebDriver driver) throws IOException {
	  File FileSrc=  ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	  File FileDst= new File("./Screenshots/"+System.currentTimeMillis()+".png");
	  String AbsoluteDestination = FileDst.getAbsolutePath();
	  FileUtils.copyFile(FileSrc, FileDst);  
	 return AbsoluteDestination;
  }


}
