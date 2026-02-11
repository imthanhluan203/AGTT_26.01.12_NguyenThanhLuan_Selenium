package TestPackage;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import Constant.Tab;
import DataObjects.UserInfo;
import Railway.HomePage;
import Railway.LoginPage;

public class LoginTest extends BaseTest{
	
	
	@Test(description = "TC1: User can log into Railway with valid username and password",enabled = true)
	public void TC1() {
		
		//Create valid user information
		myUserInfo = new UserInfo(Constant.USERNAME, Constant.PASSWORD);
		String expectedResult = String.format("Welcome %s", myUserInfo.getName());
		
		System.out.println("1. Navigate to QA Railway Website.");
		HomePage home = new HomePage();
		home.open();
	    
	    System.out.println("2. Click on \"Login\" tab.");
	    
		LoginPage loginPage = home.gotoPage(Tab.LOGIN,LoginPage.class);
		
		System.out.println("3. Enter valid Email and Password.");
		HomePage myPage = loginPage.login(myUserInfo);
		String actualResult = myPage.getWelcomeMessage();
		
		String verifyString = "VP: User is logged into Railway. Welcome user message is displayed";
		System.out.println(verifyString);
		
		Assert.assertEquals(actualResult, expectedResult, "VP: User is logged into Railway. Welcome user message is displayed");		   
		
	}
	
	
	
	@Test(description = "User cannot login with blank \"Username\" textbox",enabled = true)
	public void TC2() {
		
		myUserInfo = new UserInfo("",Constant.PASSWORD);		
		String expectedResult = "There was a problem with your login and/or errors exist in your form.";
		
		System.out.println("1. Navigate to QA Railway Website");		
		HomePage home = new HomePage();
		home.open();
		
		System.out.println("2. Click on \"Login\" tab");		
		LoginPage loginPage = home.gotoPage(Tab.LOGIN,LoginPage.class);
		
		System.out.println("3. User doesn't type any words into \"Username\" textbox but enter valid information into \"Password\" textbox ");
		System.out.println("4. Click on \"Login\" button");
		LoginPage myPage = loginPage.login(myUserInfo);
				
		String verifyString = "VP: User can't login and message \"There was a problem with your login and/or errors exist in your form. \" appears.";
		System.out.println(verifyString);
					    
	    String actualResult = myPage.getLblErrorLoginMessage();
		Assert.assertEquals(actualResult, expectedResult, "VP: User can't login and message \"There was a problem with your login and/or errors exist in your form. \" appears.");		
	}
	
	@Test(description = "User cannot log into Railway with invalid password.", enabled = true)
	public void TC3() {
		//Create user information with bank username 
		myUserInfo = new UserInfo(Constant.USERNAME,"x" + Constant.PASSWORD);		
		String expectedResult = "There was a problem with your login and/or errors exist in your form.";
		
		System.out.println("1. Navigate to QA Railway Website");		
		HomePage home = new HomePage();
		home.open();
		
		System.out.println("2. Click on \"Login\" tab");	
		LoginPage loginPage = home.gotoPage(Tab.LOGIN,LoginPage.class);
		
		System.out.println("3. Enter valid Email and invalid Password");		
		System.out.println("4. Click on \"Login\" button");		
		LoginPage myPage = loginPage.login(myUserInfo);
		
	    String actualResult = myPage.getLblErrorLoginMessage();
		String verifyString = "Verify that Error message \"There was a problem with your login and/or errors exist in your form.\" is displayed";
		System.out.println(verifyString);
		Assert.assertEquals(actualResult, expectedResult, "Verify that Error message \"There was a problem with your login and/or errors exist in your form.\" is displayed");
	}
	
	
	@Test(description = "System shows message when user enters wrong password many times",enabled = true)
	public void TC4() {
		//Create user information with bank username 
		myUserInfo = new UserInfo(Constant.USERNAME,"x" + Constant.PASSWORD);
		String expectedResult1 = "Invalid username or password. Please try again.";
		String expectedResult2 = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
		System.out.println("1. Navigate to QA Railway Website");
		
		HomePage home = new HomePage();
		home.open();
		
		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = home.gotoPage(Tab.LOGIN,LoginPage.class);
		
		for(int i=0;i<3;i++) {
			System.out.printf("Run step 3 and 4: %d time. %n",i+1);
			System.out.println("3. Enter valid information into \"Username\" textbox except \"Password\" textbox.");
			System.out.println("4. Click on \"Login\" button");
			LoginPage myPage = loginPage.login(myUserInfo); 
		    String actualResult = myPage.getLblErrorLoginMessage();
		    System.out.println("Verify that invalid username or password. Please try again.");
			Assert.assertEquals(actualResult, expectedResult1, "Verify that invalid username or password. Please try again.");
		}
		System.out.printf("Run step 3 and 4: 4 time. %n");	
		LoginPage myPage =  loginPage.login(myUserInfo);		
	    String actualResult = myPage.getLblErrorLoginMessage();
	    	    
		String verifyString = "VP: User can't login and message \"You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.\" appears.";
		System.out.println(verifyString);
		Assert.assertEquals(actualResult, expectedResult2, "VP: User can't login and message \"You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.\" appears.");
	}
	
	
	@Test(description = "User can't login with an account hasn't been activated", enabled = true)
	public void TC5() {
		myUserInfo = new UserInfo("exampleThanhLuan@gmail.com","123456789");
		String expectedResult = "Invalid username or password. Please try again.";		
		System.out.println("Pre-condition: a not-active account is existing");		
		System.out.println("1. Navigate to QA Railway Website.");
		HomePage home = new HomePage();
		home.open();
 
	    System.out.println("2. Click on \"Login\" tab.");
		LoginPage loginPage = home.gotoPage(Tab.LOGIN,LoginPage.class);
		
		System.out.println("3. Enter valid Email and Password.");
		LoginPage myPage = loginPage.login(myUserInfo);		
	    String actualResult = myPage.getLblErrorLoginMessage();
	    	    
		String verifyString = "VP: User can't login and message \"Invalid username or password. Please try again.\" appears.";
		System.out.println(verifyString);
	    Assert.assertEquals(actualResult, expectedResult, "VP: User can't login and message \"Invalid username or password. Please try again.\" appears.");
	}
}
