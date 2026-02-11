package Railway;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Utilities;
import Constant.Constant;
import Constant.Tab;
import DataObjects.UserInfo;

public class LoginPage extends GeneralPage {
	private final By _txtUsername = By.xpath("//input[@id='username']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _btnLogin = By.xpath("//input[@type='submit']");
	private final By _lblErrorLoginMessage = By.xpath("//p[@class='message error LoginForm']");
	private final By _linkforgotPassword = By.xpath("//a[text()='Forgot Password page']");
	private final By _txtEmailReset = By.xpath("//input[@id='email']");
	private final By _btnSubmitReset = By.xpath("//input[@type='submit']");

	public String getLblErrorLoginMessage() {
		return Utilities.getTextElement(_lblErrorLoginMessage);
	}
	public Boolean isLoggedIn() {
		return checkTabPageExist(Tab.LOGIN);
	}
	
	public void clickForgotPassword() {
		Utilities.click(_linkforgotPassword);
	}
	
	public void sendRequestReset(String input) {
		Utilities.enter(_txtEmailReset, input);
		Utilities.click(_btnSubmitReset);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends GeneralPage> T login(UserInfo myUser) {
		Utilities.enter(_txtUsername, myUser.getName());
		Utilities.enter(_txtPassword, myUser.getPassword());
		Utilities.click(_btnLogin);
		try {
			WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(2));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Welcome to Safe Railway']")));
		} catch (Exception e) {
			
		}
		if(isLoggedIn()) {
			return (T) this;
		}
		return (T) new HomePage();
	}

}
