package TestPackage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.CreateEmail;
import Constant.Constant;
import Railway.HomePage;
import Railway.LoginPage;

public class TC01 {
	@BeforeMethod
	public void beforeMethod() throws Exception {
		System.out.println("Pre-condition");
		CreateEmail.createEmail(true);
		Constant.WEBDRIVER = new ChromeDriver();
	}
	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition");
		Constant.WEBDRIVER.quit();
	}
	@Test
	public void TC001() throws Exception {
		System.out.println("TC01 - User can logs into Railway with valid username and password.");
		HomePage home = new HomePage();
		home.open();
		LoginPage loginPage = home.gotoPage("Login",LoginPage.class);
		String actualMessage = loginPage.login(Constant.USERNAME,Constant.PASSWORD).getWelcomeMessage().getText();
		String expectedMessage = "Welcome " + Constant.USERNAME;
		System.out.println(actualMessage);
		
		Assert.assertEquals(actualMessage, expectedMessage);
	}

}
