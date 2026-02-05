package TestPackage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Constant.Constant;
import Guerrillamail.GuerrillaMail;
import Railway.HomePage;
import Railway.RegisterPage;

public abstract class BaseTest {
	@BeforeMethod
	public void beforeMethod() throws Exception {
		System.out.println("Pre-condition");
		Constant.WEBDRIVER = new ChromeDriver();
	}
	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition");
		Constant.WEBDRIVER.quit();
	}
	public void register() {
		GuerrillaMail mail = new GuerrillaMail();
		mail.createAnEmail();
		
	    HomePage home = new HomePage();
		home.open();
		RegisterPage myPage = home.gotoPage("Register", RegisterPage.class);
		myPage.register();
		
		Object[] windowHandles = Constant.WEBDRIVER.getWindowHandles().toArray();
	    Constant.WEBDRIVER.switchTo().window(windowHandles[0].toString());
	    mail.waitAndClickConfirmEmail();
		
	}
	
	public void registerAndNotActivated() {
		GuerrillaMail mail = new GuerrillaMail();
		mail.createAnEmail();
		
	    HomePage home = new HomePage();
		home.open();
		RegisterPage myPage = home.gotoPage("Register", RegisterPage.class);
		myPage.register();
		
		Object[] windowHandles = Constant.WEBDRIVER.getWindowHandles().toArray();
	    Constant.WEBDRIVER.switchTo().window(windowHandles[0].toString());
	}
}
