package TestPackage;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Constant.Tab;
import DataObjects.UserInfo;
import Guerrillamail.GuerrillaMail;
import Railway.HomePage;
import Railway.RegisterPage;

public class CreateAccountTest extends BaseTest{
	
	@Test(description = "User can't create account with an already in-use email", enabled = false)
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
		String actualResult = registerPage.getErrorMessage();
		
		
		String verifyString = "VP: Error message \"This email address is already in use.\" displays above the form.";
		System.out.println(verifyString);
		Assert.assertEquals(actualResult, expectedResult, verifyString);
		
	}
	
	@Test(description = "User can't create account while password and PID fields are empty", enabled = false)
	public void TC8() {
		String expectedErrorMessage = "There're errors in the form. Please correct the errors and try again.";
		String expectedResultPassword = "Invalid password length";
		String expectedResultPid = "Invalid ID length";
				
		myUserInfo = new UserInfo(Constant.USERNAME, "", "");
		
		System.out.println("1. Navigate to QA Railway Website.");
		
		HomePage home = new HomePage();
		home.open();
		
		System.out.println("2. Click on \"Register\" tab");
		
		RegisterPage registerPage = home.gotoPage(Tab.REGISTER, RegisterPage.class);
		
		System.out.println("3. Enter valid email address and leave other fields empty.");
		System.out.println("4. Click on \"Register\" button");
		
		registerPage = registerPage.register(myUserInfo);
		String actualErrorMessage = registerPage.getErrorMessage();
		String actualResultPassword = registerPage.getErrorInvalidPassword();
		String actualResultPid = registerPage.getErrorInvalidPid();
		
		String verifyString = "VP: Message \"There're errors in the form. Please correct the errors and try again.\" appears above the form. Next to password fields, error message \"Invalid password length.\" displays. Next to PID field, error message \"Invalid ID length.\" displays.";
		System.out.println(verifyString);
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, verifyString);
		Assert.assertEquals(actualResultPassword, expectedResultPassword, verifyString);
		Assert.assertEquals(actualResultPid, expectedResultPid, verifyString);	
	}
	
	@Test(description = "User create and activate account", enabled = true)
	public void TC9() {
		String expectedResult3 = "Thank you for registering your account";	
		String expectedResult4 = "Registration Confirmed! You can now log in to the site.";
		
		String username = Utilities.generateRandomString(15) + Constant.MAIL_TYPE;
		String password = "123456789";
		
		myUserInfo = new UserInfo(username, password);
		System.out.println("1. Navigate to QA Railway Website");
		HomePage myHome = new HomePage();
		myHome.open();
		
		System.out.println("2. Click on \"Create an account\"");
		
	    RegisterPage accPage = myHome.getCreateAccountPage();
	    String verifyString1 = "VP: Home page is shown with guide containing href \"create an account\" to \"Register\" page";
	    String verifyString2 = "VP: Register page is shown";
	    System.out.println(verifyString1);
	    System.out.println(verifyString2);
	    if(!accPage.checkTabPageExist(Tab.REGISTER)) {
	    	Assert.fail(verifyString1);
	    	Assert.fail(verifyString2);
	    }
	    
	    
		System.out.println("3. Enter valid information into all fields");
		System.out.println("4. Click on \"Register\" button");	
		accPage.register(myUserInfo);	
		String verifyString3 = "VP: \"Thank you for registering your account\" is shown";
		String actualResult3 = "Thank you for registering your account";
		System.out.println(verifyString3);
		Assert.assertEquals(expectedResult3, actualResult3,verifyString3);
		
		System.out.println("5. Get email information (webmail address, mailbox and password) and navigate to that webmail.");
		System.out.println("6. Login to the mailbox");
		GuerrillaMail mail = new GuerrillaMail(myUserInfo);
		Constant.WEBDRIVER.get(Constant.EMAIL_URL);
		mail.setAnEmail();;
		
		System.out.println("7. Open email with subject containing \"Please confirm your account\"  and the email of the new account at step 3");		
		System.out.println("8. Click on the activate link");
		mail.waitAndClickConfirmEmail();
		Utilities.closeAllTabsExceptMain("Safe Railway");
		
		String actualResult4 = accPage.getWelcomeMessage();
		String verifyString4 = "VP: Redirect to Railways page and message \"Registration Confirmed! You can now log in to the site\" is shown";
		System.out.println(verifyString4);
		Assert.assertEquals(expectedResult4, actualResult4,verifyString4);
	}
	
}
