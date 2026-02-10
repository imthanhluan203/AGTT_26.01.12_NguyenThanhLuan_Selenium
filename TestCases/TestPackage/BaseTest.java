package TestPackage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Common.Utilities;
import Constant.Constant;
import Constant.Tab;
import DataObjects.UserInfo;
import Guerrillamail.GuerrillaMail;
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
		Constant.WEBDRIVER.quit();
	}
	
	public void register(UserInfo myUser) {
		
		//Clean all old mail
		GuerrillaMail mail = new GuerrillaMail(myUser);
		
		//Open HomePage to register
		HomePage home = new HomePage();
		home.open();
		RegisterPage registerPage = home.gotoPage(Tab.REGISTER, RegisterPage.class);
		registerPage = registerPage.register(myUser);
		
		
		Constant.WEBDRIVER.get(Constant.EMAIL_URL);
		mail.setAnEmail();	
		mail.waitAndClickConfirmEmail();
		
		Utilities.closeAllTabsExceptMain("Safe Railway");
		
	}
}
