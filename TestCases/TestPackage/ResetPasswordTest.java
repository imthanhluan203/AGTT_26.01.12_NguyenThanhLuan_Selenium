package TestPackage;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import DataObjects.UserInfo;
import Enum.PageTitle;
import Enum.Tab;
import Guerrillamail.GuerrillaMail;
import Railway.HomePage;
import Railway.LoginPage;
import Railway.ResetAccountPage;

public class ResetPasswordTest extends BaseTest {
	@Test(description = "Reset password shows error if the new password is same as current", enabled = true)
	public void TC10() {		
		String expectedResult = "The new password cannot be the same with the current password";
		
		System.out.println("Pre-condition: an actived account is existing");
		myUserInfo = new UserInfo(Utilities.generateRandomString(15) + Constant.MAIL_TYPE, Constant.PASSWORD);
		register(myUserInfo);
		String newPassWord = myUserInfo.getPassword();
		
		//Clean all old mail before go to the next step
		GuerrillaMail mail = new GuerrillaMail(myUserInfo);
		Constant.WEBDRIVER.get(Constant.EMAIL_URL);
		mail.setAnEmail();	
		mail.cleanAllOldMail();
		
		System.out.println("1. Navigate to QA Railway Login page");
		HomePage home = new HomePage();
		home.open();
		LoginPage login = home.gotoPage(Tab.LOGIN, PageTitle.LOGIN, LoginPage.class);
		
		System.out.println("2. Click on \"Forgot Password page\" link");
		login.clickForgotPassword();
		
		System.out.println("3. Enter the email address of the activated account");
		System.out.println("4. Click on \"Send Instructions\" button");
		login.sendRequestReset(myUserInfo.getName());
		
		System.out.println("5. Login to the mailbox (the same mailbox when creating account) ");
		Constant.WEBDRIVER.navigate().to(Constant.EMAIL_URL);
		mail.setAnEmail();
		
		System.out.println("6. Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");
		System.out.println("7. Click on reset link");//Redirect to Railways page and Form "Password Change Form" is shown with the reset password token
		mail.waitAndClickConfirmEmail();
		Utilities.closeAllTabsExceptMain(PageTitle.RESET_PASSWORD);
		String verifyString = "VP: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token";
		ResetAccountPage reset = new ResetAccountPage();
		System.out.println(verifyString);
		String tokenReset = reset.getResetoken();
		Assert.assertTrue(tokenReset.length() > 1, "VP: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
			
		System.out.println("8. Input same password into 2 fields  \"new password\" and \"confirm password\"");
		System.out.println("9. Click Reset Password");
		reset.resetPassWord(newPassWord, newPassWord);
		verifyString = "VP: Message \"The new password cannot be the same with the current password\" is shown";
		System.out.println(verifyString);
		String actualResult = reset.getMessageReset();
		Assert.assertEquals(actualResult, expectedResult, "VP: Message \"The new password cannot be the same with the current password\" is shown");
		
	}
	
	@Test(description = "Reset password shows error if the new password and confirm password doesn't match",enabled = true)
	public void TC11() {
		
		String expectedResult1 = "Could not reset password. Please correct the errors and try again.";
		String expectedResult2 = "The password confirmation did not match the new password.";
		
		System.out.println("Pre-condition: an actived account is existing");
		myUserInfo = new UserInfo(Utilities.generateRandomString(15) + Constant.MAIL_TYPE, Constant.PASSWORD);
		register(myUserInfo);
		String newPassWord ="first" + myUserInfo.getPassword();
		String confirmPassWord ="second" + myUserInfo.getPassword();
		
		//Clean all old mail before go to the next step
		GuerrillaMail mail = new GuerrillaMail(myUserInfo);
		Constant.WEBDRIVER.get(Constant.EMAIL_URL);
		mail.setAnEmail();	
		mail.cleanAllOldMail();	
		
		System.out.println("1. Navigate to QA Railway Login page");
		HomePage home = new HomePage();
		home.open();
		LoginPage login = home.gotoPage(Tab.LOGIN, PageTitle.LOGIN, LoginPage.class);
		
		System.out.println("2. Click on \"Forgot Password page\" link");
		login.clickForgotPassword();
		
		System.out.println("3. Enter the email address of the activated account");
		System.out.println("4. Click on \"Send Instructions\" button");
		login.sendRequestReset(myUserInfo.getName());
		
		System.out.println("5. Login to the mailbox (the same mailbox when creating account) ");
		Constant.WEBDRIVER.navigate().to(Constant.EMAIL_URL);
		mail.setAnEmail();
		
		System.out.println("6. Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");
		System.out.println("7. Click on reset link"); //Redirect to Railways page and Form "Password Change Form" is shown with the reset password token
		mail.waitAndClickConfirmEmail();
		Utilities.closeAllTabsExceptMain(PageTitle.RESET_PASSWORD);
		String verifyString1 = "VP: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token";
		ResetAccountPage reset = new ResetAccountPage();
		System.out.println(verifyString1);
		String tokenReset = reset.getResetoken();
		Assert.assertTrue(tokenReset.length() > 1, verifyString1);
		
		System.out.println("8. Input different input into 2 fields  \"new password\" and \"confirm password\"");
		System.out.println("9. Click Reset Password");
		//Error message "Could not reset password. Please correct the errors and try again." displays above the form.
		//Error message "The password confirmation did not match the new password." displays next to the confirm password field.
		reset.resetPassWord(newPassWord, confirmPassWord);
		String verifyString2 = "VP: Error message \"Could not reset password. Please correct the errors and try again.\" displays above the form.";
		String verifyString3 = "VP: Error message \"The password confirmation did not match the new password.\" displays next to the confirm password field.";
		System.out.println(verifyString2);
		System.out.println(verifyString3);
		String actualResult1 = reset.getMessageReset();
		String actualResult2 = reset.getConfirmPasswordErr();
		Assert.assertEquals(actualResult1, expectedResult1,"VP: Error message \"Could not reset password. Please correct the errors and try again.\" displays above the form.");
		Assert.assertEquals(actualResult2, expectedResult2, "VP: Error message \"The password confirmation did not match the new password.\" displays next to the confirm password field.");
	}
}
