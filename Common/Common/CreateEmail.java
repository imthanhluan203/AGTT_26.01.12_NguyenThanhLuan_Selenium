package Common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;
import Railway.HomePage;
import Railway.RegisterPage;

public class CreateEmail {
	public static String createEmail(Boolean activated) throws Exception {
		Constant.WEBDRIVER = new ChromeDriver();
		Constant.WEBDRIVER.get(Constant.EMAIL_URL);
		WebDriverWait webWait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(20));
		Constant.WEBDRIVER.findElement(By.xpath("//span[@class='editable button']")).click();
		Constant.WEBDRIVER.findElement(By.xpath("//span[@id='inbox-id']/input")).sendKeys(Constant.NAME);
		Constant.WEBDRIVER.findElement(By.xpath("//button[text()='Set']")).click();
		webWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='editable button']")));
		String userName = Constant.WEBDRIVER.findElement(By.xpath("//span[@class='editable button']")).getText();
		
		Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
	    Constant.WEBDRIVER.get(Constant.RAILWAY_URL);
		
	    HomePage home = new HomePage();
		home.open();
		RegisterPage myPage = home.gotoPage("Register", RegisterPage.class);
		myPage.register();
		
		if(activated) {
		    Object[] windowHandles = Constant.WEBDRIVER.getWindowHandles().toArray();
		    Constant.WEBDRIVER.switchTo().window(windowHandles[0].toString());
			webWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'thanhletraining03@gmail.com')]"))).click();
			webWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@target,'_blank')]"))).click();
		}
		return userName;
		
	}

}
