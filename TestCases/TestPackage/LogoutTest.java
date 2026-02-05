package TestPackage;
import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Tab;
import Railway.FAQPage;
import Railway.GeneralPage;
import Railway.HomePage;
import Railway.LoginPage;


public class LogoutTest extends BaseTest {
	@Test(description = "User is redirected to Home page after logging out",enabled = true)
	public void TC06() {
		register();
		
		
		System.out.println("1. Navigate to QA Railway Website");
		
		HomePage home = new HomePage();
		home.open();
		
		Utilities.closeAllTabsExceptMain();
		
		System.out.println("2. Login with valid Email and Password");
		
		LoginPage loginPage = home.gotoPage(Tab.LOGIN,LoginPage.class);
		GeneralPage myPage = loginPage.login(myUserInfo.getFullEmailName(), myUserInfo.getPassword());
		
		System.out.println("3. Click on \"FAQ\" tab");
		
		FAQPage faqPage = myPage.gotoPage(Tab.FAQ, FAQPage.class);
		
		System.out.println("4. Click on \"Log out\" tab");
		
		faqPage.clickTab(Tab.LOGOUT);
		
		System.out.println("Verify that Home page displays \"Log out\" tab is disappeared.");
		
		Assert.assertTrue(!home.checkTabPageExit(Tab.LOGOUT), "Verify that Home page displays \"Log out\" tab is disappeared.");

	}
}
