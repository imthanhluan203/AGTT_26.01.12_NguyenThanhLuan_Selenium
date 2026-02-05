package TestPackage;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Railway.FAQPage;
import Railway.GeneralPage;
import Railway.HomePage;
import Railway.LoginPage;


public class LogoutTest extends BaseTest {
	@Test(description = "User is redirected to Home page after logging out")
	public void TC06() {
		register();
		System.out.println("1. Navigate to QA Railway Website");
		
		HomePage home = new HomePage();
		home.open();
		
		Object[] windowHandles = Constant.WEBDRIVER.getWindowHandles().toArray();
		String mainTab = windowHandles[0].toString();
		for (int i = 1; i < windowHandles.length; i++) {
		    Constant.WEBDRIVER.switchTo().window(windowHandles[i].toString());
		    Utilities.waitForPageFullyLoad();
		    Constant.WEBDRIVER.close(); 
		}
		Constant.WEBDRIVER.switchTo().window(mainTab);
		
		System.out.println("2. Login with valid Email and Password");
		
		LoginPage loginPage = home.gotoPage("Login",LoginPage.class);
		GeneralPage myPage = loginPage.login(Constant.USERNAME, Constant.PASSWORD);
		
		System.out.println("3. Click on \"FAQ\" tab");
		
		FAQPage faqPage = myPage.gotoPage("FAQ", FAQPage.class);
		
		System.out.println("4. Click on \"Log out\" tab");
		
		faqPage.getTab("Logout").click();
		
		
		System.out.println("Verify that Home page displays \"Log out\" tab is disappeared.");
	}
}
