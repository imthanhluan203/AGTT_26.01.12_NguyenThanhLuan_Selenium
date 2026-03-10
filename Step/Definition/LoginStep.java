package Definition;

import Constant.Constant;
import DataObjects.UserInfo;
import Railway.HomePage;
import Railway.LoginPage;
import Enum.Tab;
import Enum.PageTitle;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginStep {
    HomePage home = new HomePage();
    LoginPage loginPage;
    UserInfo myUserInfo;


    @Given("User navigate to QA Railway Website.")
    public void user_navigate_to_qa_railway_website() {
        home.open();
    }
    @When("User click on Login tab.")
    public void user_click_on_tab() {
        // Write code here that turns the phrase above into concrete actions
        loginPage = home.gotoPage(Tab.LOGIN, PageTitle.LOGIN,LoginPage.class);
    }

    //TC01
    @And("User enter valid Email and Password and click on Login button.")
    public void user_enter_valid_email_and_password_and_click_on_login_button() {
        myUserInfo = new UserInfo(Constant.USERNAME, Constant.PASSWORD);
        home = loginPage.login(myUserInfo);
    }
    @Then("VP: User is logged into Railway. Welcome user message is displayed.")
    public void vp_user_is_logged_into_railway_welcome_user_message_is_displayed() {
        String actualResult = home.getWelcomeMessage();
        String expectedResult = String.format("Welcome %s", myUserInfo.getName());
        String verifyString = "VP: User is logged into Railway. Welcome user message is displayed";
        Assert.assertEquals(actualResult, expectedResult, "VP: User is logged into Railway. Welcome user message is displayed");
    }

    //TC02
    @And("User doesn't type any words into Username textbox but enter valid information into Password textbox and  Click on Login button.")
    public void user_doesn_t_type_any_words_into_username_textbox_but_enter_valid_information_into_password_textbox_and_click_on_login_button() {
        myUserInfo = new UserInfo("",Constant.PASSWORD);
        loginPage = loginPage.login(myUserInfo);
    }
    @Then("VP: User can't login and message {string} appears.")
    public void vp_user_can_t_login_and_message_appears(String string) {
        String expectedResult = "There was a problem with your login and/or errors exist in your form.";
        String verifyString = String.format("VP: User can't login and message \"%s\" appears.",string);
        String actualResult = loginPage.getLblErrorLoginMessage();
        Assert.assertEquals(actualResult, expectedResult, verifyString);
    }

    //TC03
    @When("User enter valid Email and invalid Password and click on Login button.")
    public void user_enter_valid_email_and_invalid_password_and_click_on_login_button()  {
        myUserInfo = new UserInfo(Constant.USERNAME,"xxx");
        loginPage = loginPage.login(myUserInfo);
    }
    @Then("VP: Error message {string} is displayed.")
    public void vp_error_message_is_displayed(String string)  {
        String expectedResult = "There was a problem with your login and/or errors exist in your form.";
        String actualResult = loginPage.getLblErrorLoginMessage();
        String verifyString = String.format("Verify that Error message \"%s\" is displayed",string);
        Assert.assertEquals(actualResult, expectedResult, verifyString);
    }

    //TC04
    @And("User enter valid information into Username textbox except Password textbox and click on Login button and VP:{string} Please try again is shown.")
    public void user_enter_valid_information_into_username_textbox_except_password_textbox_and_click_on_login_button_and_vp_please_try_again_is_shown(String string) {
        myUserInfo = new UserInfo(Constant.USERNAME,"x" + Constant.PASSWORD);
        String expectedResult1 = "Invalid username or password. Please try again.";
        for(int i=0;i<3;i++) {
            loginPage = loginPage.login(myUserInfo);
            String actualResult = loginPage.getLblErrorLoginMessage();
            Assert.assertEquals(actualResult, expectedResult1, "Verify that invalid username or password. Please try again.");
        }
    }
    @Then("Repeat 3th and 4th  three more times and VP: {string} appears.")
    public void repeat_3th_and_4th_three_more_times_and_vp_appears(String string) {
        String expectedResult2 = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
        loginPage =  loginPage.login(myUserInfo);
        String actualResult = loginPage.getLblErrorLoginMessage();

        String verifyString = String.format("VP: User can't login and message \"%s\" appears.",string);
        System.out.println(verifyString);
        Assert.assertEquals(actualResult, expectedResult2, verifyString);
    }

    //TC05
    @When("User enter username and password of account hasn't been activated and click on Login button")
    public void user_enter_username_and_password_of_account_hasn_t_been_activated_and_click_on_login_button() {
        myUserInfo = new UserInfo("exampleThanhLuan@gmail.com","123456789");
        loginPage = loginPage.login(myUserInfo);
    }
    @Then("VP: User can't login and message Invalid username or password. Please try again appears.")
    public void user_vp(){
        String expectedResult = "Invalid username or password. Please try again.";
        String actualResult = loginPage.getLblErrorLoginMessage();
        Assert.assertEquals(actualResult, expectedResult, "VP: User can't login and message \"Invalid username or password. Please try again.\" appears.");

    }


}
