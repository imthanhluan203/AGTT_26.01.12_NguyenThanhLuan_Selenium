Feature: Test Login function
  Background:
  Given User navigate to QA Railway Website.
  When User click on Login tab.
  Scenario: TC01: User can log into Railway with valid username and password.
    And User enter valid Email and Password and click on Login button.
    Then VP: User is logged into Railway. Welcome user message is displayed.

  Scenario: TC02: User cannot login with blank Username textbox.
    And User doesn't type any words into Username textbox but enter valid information into Password textbox and  Click on Login button.
    Then VP: User can't login and message "There was a problem with your login and/or errors exist in your form. " appears.

  Scenario:  TC03: User cannot log into Railway with invalid password.
    And User enter valid Email and invalid Password and click on Login button.
    Then VP: Error message "There was a problem with your login and/or errors exist in your form." is displayed.

  Scenario: TC04: System shows message when user enters wrong password many times.
    And User enter valid information into Username textbox except Password textbox and click on Login button and VP:"Invalid username or password." Please try again is shown.
    Then Repeat 3th and 4th  three more times and VP: "User can't login and message You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes." appears.

  Scenario: TC05: User can't login with an account hasn't been activated
    And User enter username and password of account hasn't been activated and click on Login button
    Then VP: User can't login and message Invalid username or password. Please try again appears.