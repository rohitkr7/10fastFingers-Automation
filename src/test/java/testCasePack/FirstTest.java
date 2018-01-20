package testCasePack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FirstTest {

	WebDriver driver = null;
	//String chromeDriver = "webdriver.chrome.driver";
	//String chromeDriverPath = "C:\\Users\\rohit_knw2paf\\Desktop\\testingAutomation\\chromedriver.exe";
	String url = "https://www.google.com";

	@Test(dataProvider = "myDataProvider")
	public void fun(String firstVar, String secondVar) throws InterruptedException {
		WebElement searchBox = driver.findElement(By.xpath("//input[@name='q']"));
		// searchBox.sendKeys("philips trimmer");
		searchBox.sendKeys(firstVar + " " + secondVar);
		System.out.println(firstVar + " " + secondVar);
		WebElement submitBtn = driver.findElement(By.xpath("//input[contains(@value,'Google')][@type='submit']"));
		submitBtn.click();

		Thread.sleep(3000);
	}

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		//System.setProperty(chromeDriver, chromeDriverPath);
		driver = new ChromeDriver();

		driver.navigate().to(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);

		Thread.sleep(3000);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
		driver.quit();
	}

	@DataProvider
	public Object[][] myDataProvider() {
		return new Object[][] { {"philips", "trimmer" }, { "rock", "band" }, };
	}
}
