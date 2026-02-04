package TestPackage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Constant.Constant;
import Railway.HomePage;
import Railway.LoginPage;

public class TC04 {
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
	
	@Test()
	public void TC001() throws InterruptedException {
		System.out.println("TC04 - System shows message when user enters wrong password many times");
		
		System.out.println("1. Navigate to QA Railway Website");
		HomePage home = new HomePage();
		home.open();
		String errorMessage = "";
		
		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = home.gotoLoginPage();
	
		for(int i = 0; i < 3; i++) {
			System.out.println("Repeat step 3 and 4 three more times:" + (i + 1));
			System.out.println("3. Enter valid information into \"Username\" textbox except \"Password\" textbox.");
			System.out.println("4. Click on \"Login\" button");
		
			errorMessage = loginPage.login(Constant.USERNAME, "x" + Constant.PASSWORD).getLoginPage().getLblErrorLoginMessage().getText();
		}
		System.out.println("Verify step");
		errorMessage = loginPage.login(Constant.USERNAME, "x" + Constant.PASSWORD).getLoginPage().getLblErrorLoginMessage().getText();
		String expectedMessage = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
		Assert.assertEquals(errorMessage, expectedMessage,"System shows message when user enters wrong password many times");
	}
	
}
