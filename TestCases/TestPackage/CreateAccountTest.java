package TestPackage;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import Constant.Tab;
import Railway.HomePage;
import Railway.RegisterPage;
import UserMail.UserInfo;

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
	
	@Test(description = "User can't create account while password and PID fields are empty", enabled = true)
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
	
//	@Test(description = "User create and activate account")
//	public void TC9() {
//		
//		myUserInfo = new UserInfo("", null);
//		System.out.println("1. Navigate to QA Railway Website");
//		
//		
//		
//		System.out.println("2. Click on \"Create an account\"");
//		System.out.println("3. Enter valid information into all fields");
//		System.out.println("4. Click on \"Register\" button");
//		System.out.println("5. Get email information (webmail address, mailbox and password) and navigate to that webmail.");
//		System.out.println("6. Login to the mailbox");
//		System.out.println("7. Open email with subject containing \"Please confirm your account\"  and the email of the new account at step 3");
//		System.out.println("8. Click on the activate link");
//	}
	
}
