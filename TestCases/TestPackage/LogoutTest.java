package TestPackage;
import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Constant.Tab;
import Railway.FAQPage;
import Railway.GeneralPage;
import Railway.HomePage;
import Railway.LoginPage;
import Railway.RegisterPage;
import UserMail.UserInfo;


public class LogoutTest extends BaseTest {
	@Test(description = "User is redirected to Home page after logging out",enabled = false)
	public void TC6() {		
		
		myUserInfo = new UserInfo(Constant.USERNAME, Constant.PASSWORD);
		System.out.println("1. Navigate to QA Railway Website");
		
		HomePage home = new HomePage();
		home.open();
		
		System.out.println("2. Login with valid Email and Password");
		
		LoginPage loginPage = home.gotoPage(Tab.LOGIN,LoginPage.class);
		GeneralPage myPage = loginPage.login(myUserInfo);
		
		System.out.println("3. Click on \"FAQ\" tab");
		
		FAQPage faqPage = myPage.gotoPage(Tab.FAQ, FAQPage.class);
		
		System.out.println("4. Click on \"Log out\" tab");
		
		faqPage.clickTab(Tab.LOGOUT);
		
		System.out.println("VP: Verify that Home page displays \"Log out\" tab is disappeared.");
		
		Assert.assertTrue(!home.checkTabPageExit(Tab.LOGOUT), "VP: Verify that Home page displays \"Log out\" tab is disappeared.");
	}
	
	@Test(description = "User can't create account with an already in-use email", enabled = true)
	public void TC7() {
		
		myUserInfo = new UserInfo(Constant.USERNAME, "0987654321");
		
		String expectedResult = "This email address is already in use.";
		
		System.out.println("Pre-condition: an actived account is existing");
		System.out.println("1. Navigate to QA Railway Website.");
		
		HomePage home = new HomePage();
		home.open();
		
		System.out.println("2. Click on \"Register\" tab.");
		
		RegisterPage registerPage = home.gotoPage(Tab.REGISTER, RegisterPage.class);
		
		
		System.out.println("3. Enter information of the created account in Pre-condition");
		System.out.println("4. Click on \"Register\" button.");
		
		
		registerPage.register(myUserInfo);
		
		
		System.out.println("VP: Error message \"This email address is already in use.\" displays above the form.");
		
		String actualResult = registerPage.getErrorMessage().getText();
		
		Assert.assertEquals(actualResult, expectedResult, "VP: Error message \"This email address is already in use.\" displays above the form.");
		
	}
	
	
}
