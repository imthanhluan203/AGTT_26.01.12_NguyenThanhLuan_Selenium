package TestPackage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Constant.Constant;
import Railway.HomePage;
import Railway.LoginPage;

public class TC01 {
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Pre-condition");
		Constant.WEBDRIVER = new ChromeDriver();
	}
	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition");
		Constant.WEBDRIVER.quit();
	}
	@Test
	public void TC001() throws InterruptedException {
		System.out.println("TC01 - User can logs into Railway with valid username and password.");
		HomePage home = new HomePage();
		home.open();
		LoginPage loginPage = home.gotoLoginPage();
		String actualMessage = loginPage.login(Constant.USERNAME,Constant.PASSWORD).getWelcomeMessage().getText();
		String expectedMessage = "Welcome " + Constant.USERNAME;
		System.out.println(actualMessage);
		
		Assert.assertEquals(actualMessage, expectedMessage);
	}

}
