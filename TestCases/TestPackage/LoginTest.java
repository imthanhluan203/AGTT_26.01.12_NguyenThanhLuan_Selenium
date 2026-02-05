package TestPackage;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Tab;
import Railway.GeneralPage;
import Railway.HomePage;
import Railway.LoginPage;

public class LoginTest extends BaseTest{
	
	
	@Test(description = "TC1: User can log into Railway with valid username and password",enabled = true)
	public void TC1() {
		
		
		System.out.println("Pre-condition: Register step");
		register();//0  1  2
		
		
		String expectedResult = String.format("Welcome %s", myUserInfo.getFullEmailName());
		
		
		
		System.out.println("1. Navigate to QA Railway Website.");
		HomePage home = new HomePage();
		home.open();
		//sua lai roi qua, cho khach hang xem -->//Đã fix
		

		Utilities.closeAllTabsExceptMain();
	    
	    System.out.println("2. Click on \"Login\" tab.");
	    //Su dung enum cho tab name  --> Đã fix
		LoginPage loginPage = home.gotoPage(Tab.LOGIN,LoginPage.class);
		
		System.out.println("3. Enter valid Email and Password.");
		GeneralPage myPage = loginPage.login(myUserInfo.getFullEmailName(), myUserInfo.getPassword());
		if (myPage instanceof HomePage) {
			System.out.println("This is Home Page - Login Successfully");
			String actualResult = ((HomePage) myPage).getWelcomeMessage().getText();
			Assert.assertEquals(actualResult, expectedResult, "User is logged into Railway. Welcome user message is displayed");
		   
		} else if (myPage instanceof LoginPage) {
		    System.out.println("Still in the Login Page - Login Fail!");
		}
	}
	
	
	
	@Test(description = "User cannot login with blank \"Username\" textbox",enabled = false)
	public void TC2() {
		String expectedResult = "There was a problem with your login and/or errors exist in your form.";
		
		System.out.println("1. Navigate to QA Railway Website");
		
		HomePage home = new HomePage();
		home.open();
		
		System.out.println("2. Click on \"Login\" tab");
		
		LoginPage loginPage = home.gotoPage(Tab.LOGIN,LoginPage.class);
		
		System.out.println("3. User doesn't type any words into \"Username\" textbox but enter valid information into \"Password\" textbox ");
		System.out.println("4. Click on \"Login\" button");
	
		GeneralPage myPage = loginPage.login("", myUserInfo.getPassword());
		
		System.out.println("Verify that User can't login and message \"There was a problem with your login and/or errors exist in your form. \" appears.");
		
		if (myPage instanceof HomePage) {
			System.out.println("This is Home Page - Login Successfully");
			Assert.fail(expectedResult);
		   
		} else if (myPage instanceof LoginPage) {
		    System.out.println("Still in the Login Page - Login Fail!");
		    
		    String actualResult = ((LoginPage) myPage).getLblErrorLoginMessage().getText();
			Assert.assertEquals(actualResult, expectedResult, "Verify that User can't login and message \"There was a problem with your login and/or errors exist in your form. \" appears.");
		}
	}
	
	@Test(description = "User cannot log into Railway with invalid password.", enabled = false)
	public void TC3() {
		String expectedResult = "There was a problem with your login and/or errors exist in your form.";
		
		System.out.println("1. Navigate to QA Railway Website");
		
		HomePage home = new HomePage();
		home.open();
		
		System.out.println("2. Click on \"Login\" tab");
		
		LoginPage loginPage = home.gotoPage(Tab.LOGIN,LoginPage.class);
		
		System.out.println("3. Enter valid Email and invalid Password");		
		System.out.println("4. Click on \"Login\" button");
		
		GeneralPage myPage = loginPage.login(myUserInfo.getFullEmailName(),"x" + myUserInfo.getPassword());
		
		System.out.println("Verify that Error message \"There was a problem with your login and/or errors exist in your form.\" is displayed");
		
		if (myPage instanceof HomePage) {
			System.out.println("This is Home Page - Login Successfully");
			Assert.fail(expectedResult);
		   
		} else if (myPage instanceof LoginPage) {
		    System.out.println("Still in the Login Page - Login Fail!");
		    
		    String actualResult = ((LoginPage) myPage).getLblErrorLoginMessage().getText();
			Assert.assertEquals(actualResult, expectedResult, "Verify that Error message \"There was a problem with your login and/or errors exist in your form.\" is displayed");
		}
	
	}
	
	
	@Test(description = "System shows message when user enters wrong password many times",enabled = false)
	public void TC4() {
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
			
			GeneralPage myPage = loginPage.login(myUserInfo.getFullEmailName() ,"x" + myUserInfo.getPassword());
			
			if (myPage instanceof HomePage) {
				System.out.println("This is Home Page - Login Successfully");
				Assert.fail(expectedResult1);
			   
			} else if (myPage instanceof LoginPage) {
			    System.out.println("Still in the Login Page - Login Fail!");
			    
			    String actualResult = ((LoginPage) myPage).getLblErrorLoginMessage().getText();
				Assert.assertEquals(actualResult, expectedResult1, "Verify that invalid username or password. Please try again.");
			}
		}
		System.out.printf("Run step 3 and 4: 4 time. %n");
		System.out.println("Verify that user can't login and message \"You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.\" appears.");
	
		GeneralPage myPage =  loginPage.login(myUserInfo.getFullEmailName() ,"x" + myUserInfo.getPassword());
		
		if (myPage instanceof HomePage) {
			System.out.println("This is Home Page - Login Successfully");
			Assert.fail(expectedResult1);
		   
		} else if (myPage instanceof LoginPage) {
		    System.out.println("Still in the Login Page - Login Fail!");
		    
		    String actualResult = ((LoginPage) myPage).getLblErrorLoginMessage().getText();
			Assert.assertEquals(actualResult, expectedResult2, "Verify that user can't login and message you have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.");
		}
	}
	
	
	@Test(description = "User can't login with an account hasn't been activated", enabled = false)
	public void TC05() {
		String expectedResult = "Invalid username or password. Please try again.";
		
		System.out.println("Pre-condition: a not-active account is existing");
		
		registerAndNotActivated();
		
		System.out.println("1. Navigate to QA Railway Website.");
		HomePage home = new HomePage();
		home.open();
		
		Utilities.closeAllTabsExceptMain();
	    
	    System.out.println("2. Click on \"Login\" tab.");
		LoginPage loginPage = home.gotoPage(Tab.LOGIN,LoginPage.class);
		
		System.out.println("3. Enter valid Email and Password.");
		GeneralPage myPage = loginPage.login(myUserInfo.getFullEmailName(), myUserInfo.getPassword());
		
		if (myPage instanceof HomePage) {
			Assert.fail("User can't login and message \"Invalid username or password. Please try again.\" appears.");
		   
		} else if (myPage instanceof LoginPage) {
		    System.out.println("Still in the Login Page - Login Fail!");
		    String actualResult = ((LoginPage) myPage).getLblErrorLoginMessage().getText();
		    Assert.assertEquals(actualResult, expectedResult, "User can't login and message \"Invalid username or password. Please try again.\" appears.");
		}
	}
}
