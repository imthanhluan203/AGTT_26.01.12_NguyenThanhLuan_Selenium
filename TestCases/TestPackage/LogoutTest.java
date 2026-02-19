package TestPackage;
import org.testng.Assert;
import org.testng.annotations.Test;
import Constant.Constant;
import DataObjects.UserInfo;
import Enum.PageTitle;
import Enum.Tab;
import Railway.FAQPage;
import Railway.GeneralPage;
import Railway.HomePage;
import Railway.LoginPage;


public class LogoutTest extends BaseTest {
	@Test(description = "User is redirected to Home page after logging out",enabled = true)
	public void TC6() {		
		
		myUserInfo = new UserInfo(Constant.USERNAME, Constant.PASSWORD);
		System.out.println("1. Navigate to QA Railway Website");	
		HomePage home = new HomePage();
		home.open();
		
		System.out.println("2. Login with valid Email and Password");		
		LoginPage loginPage = home.gotoPage(Tab.LOGIN,PageTitle.LOGIN,LoginPage.class);
		
		System.out.println(Constant.WEBDRIVER.getTitle());
		GeneralPage myPage = loginPage.login(myUserInfo);
		
		System.out.println("3. Click on \"FAQ\" tab");		
		FAQPage faqPage = myPage.gotoPage(Tab.FAQ, PageTitle.FAQ,FAQPage.class);
		
		System.out.println("4. Click on \"Log out\" tab");		
		faqPage.gotoPage(Tab.LOGOUT,PageTitle.HOME, HomePage.class);
		
		System.out.println("VP: Verify that Home page displays \"Log out\" tab is disappeared.");	
		Assert.assertTrue(!home.checkTabPageExist(Tab.LOGOUT), "VP: Verify that Home page displays \"Log out\" tab is disappeared.");
	}
	
}
