package TestPackage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Common.Utilities;
import Constant.Constant;
import Constant.PageTitle;
import Constant.Tab;
import DataObjects.UserInfo;
import Guerrillamail.GuerrillaMail;
import Railway.HomePage;
import Railway.RegisterPage;

public abstract class BaseTest {
	
	protected UserInfo myUserInfo;	
	@Parameters({"browser"})
	@BeforeMethod
	public void beforeMethod(@Optional("firefox") String myBrowser) {
		System.out.println("Pre-condition");
		if(myBrowser.equals("chrome")) {
			Constant.WEBDRIVER = new ChromeDriver();
		}else if(myBrowser.equals("firefox")) {
			Constant.WEBDRIVER = new FirefoxDriver();
		}
		Constant.WEBDRIVER.manage().window().maximize();
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
		RegisterPage registerPage = home.gotoPage(Tab.REGISTER,PageTitle.REGISTER, RegisterPage.class);
		registerPage = registerPage.register(myUser);
		
		
		Constant.WEBDRIVER.get(Constant.EMAIL_URL);
		mail.setAnEmail();	
		mail.waitAndClickConfirmEmail();
		Utilities.waitForPageFullyLoad(PageTitle.REGISTER);
		Utilities.closeAllTabsExceptMain(PageTitle.REGISTER_CONFIRM);
		
	}
}
