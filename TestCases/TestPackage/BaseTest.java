package TestPackage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import Constant.Constant;
import DataObjects.UserInfo;

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
		//Constant.WEBDRIVER.quit();
	}
}
