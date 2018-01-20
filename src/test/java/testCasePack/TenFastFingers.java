package testCasePack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TenFastFingers {

	WebDriver driver = null;
	String url = "https://10fastfingers.com/typing-test/english";

	@Test
	public void fun() throws InterruptedException {
		Thread.sleep(5000);

		WebElement word = null;
		WebElement inputField = driver.findElement(By.xpath("//input[@type='text'][@id='inputfield']"));

		int i = 0;
		String path = "F:\\10FastFingersWords\\newfile.txt";
		saveWordsInTextFile("\n", path);
		while (true) {
			// System.out.println(i);
			try {
				word = driver.findElement(By.xpath("//span[@wordnr='" + i + "'][@class='highlight']"));
			} catch (Exception e) {
				break;
			}
			inputField.sendKeys(word.getText() + " ");
			saveWordsInTextFile(word.getText() + " ", path);
			i++;
			Thread.sleep(50);

		}
		String wpm = driver.findElement(By.xpath("//small[contains(text(),'words per minute')]/../strong")).getText();
		saveWordsInTextFile("\n" + wpm, path);
		Thread.sleep(5000);
	}

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);

		Thread.sleep(3000);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
		driver.quit();
	}

	public void saveWordsInTextFile(String word, String path) {
		File file = null;
		FileWriter fw = null;
		BufferedWriter bw = null;

		boolean created = false;

		file = new File(path);
		try {
			if (!file.exists()) {
				created = file.createNewFile();
				System.out.println("File Creation Status: " + created);
				System.out.println("A new File is created!");
			}

			fw = new FileWriter(file, true);

			bw = new BufferedWriter(fw);

			bw.write(word + " ");

			bw.flush();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			if (fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
		}

	}

}
