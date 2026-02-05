package TestPackage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Common.Utilities;
import Constant.Constant;
import Constant.Tab;
import Guerrillamail.GuerrillaMail;
import Guerrillamail.UserInfo;
import Railway.HomePage;
import Railway.RegisterPage;

public abstract class BaseTest {
	
	protected UserInfo myUserInfo;
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		System.out.println("Pre-condition");
		Constant.WEBDRIVER = new ChromeDriver();
	}
	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition");
//		Constant.WEBDRIVER.quit();
	}
	public void register() {
		myUserInfo = new UserInfo(Utilities.generateRandomString(15), "123456789");
		GuerrillaMail mail = new GuerrillaMail(myUserInfo);
		mail.createAnEmail(); //0
		
	    HomePage home = new HomePage();
		home.open();
		RegisterPage myPage = home.gotoPage(Tab.REGISTER, RegisterPage.class);
		myPage.register(myUserInfo); // 1
		
		Object[] windowHandles = Constant.WEBDRIVER.getWindowHandles().toArray();
	    Constant.WEBDRIVER.switchTo().window(windowHandles[0].toString()); // 0
	    mail.waitAndClickConfirmEmail();
		
	}
	
	public void registerAndNotActivated() {
		myUserInfo = new UserInfo(Utilities.generateRandomString(15), "123456789");
		GuerrillaMail mail = new GuerrillaMail(myUserInfo);
		mail.createAnEmail();
		
	    HomePage home = new HomePage();
		home.open();
		RegisterPage myPage = home.gotoPage(Tab.REGISTER, RegisterPage.class);
		myPage.register(myUserInfo);
		
		Object[] windowHandles = Constant.WEBDRIVER.getWindowHandles().toArray();
	    Constant.WEBDRIVER.switchTo().window(windowHandles[0].toString());
	}
}
