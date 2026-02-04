package TestPackage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Constant.Constant;
import Railway.HomePage;
import Railway.LoginPage;

public class TC03 {
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
		System.out.println("TC03 - User cannot log into Railway with invalid password");
		HomePage home = new HomePage();
		home.open();
		
		LoginPage loginPage = home.gotoLoginPage();
		String errorMessage = loginPage.login(Constant.USERNAME, "x" + Constant.PASSWORD).getLoginPage().getLblErrorLoginMessage().getText();
		String expectedMessage = "There was a problem with your login and/or errors exist in your form.";
		System.out.println(errorMessage);
		Assert.assertEquals(errorMessage, expectedMessage);
	}
}
