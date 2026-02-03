package TestPackage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Constant.Constant;
import Railway.HomePage;
import Railway.LoginPage;

public class LoginTest {
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Pre-condition");
	}
	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition");
		Constant.WEBDRIVER.quit();
	}
	@Test
	public void TC01() throws InterruptedException {
		System.out.println("TC01 - User can logs into Railway with valid username and password.");
		HomePage home = new HomePage();
		home.open();
		LoginPage loginPage = home.gotoLoginPage();
		String actualMessage = loginPage.login().getWelcomeMessage().getText();
		System.out.println(actualMessage);
	}

}
