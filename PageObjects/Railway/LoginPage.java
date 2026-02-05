package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class LoginPage extends GeneralPage {
	private final By _txtUsername = By.xpath("//input[@id='username']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _btnLogin = By.xpath("//input[@type='submit']");
	private final By _lblErrorLoginMessage = By.xpath("//p[@class='message error LoginForm']");

	public WebElement getLblErrorLoginMessage() {
		return Constant.WEBDRIVER.findElement(_lblErrorLoginMessage);
	}
	public Boolean isLoggedIn() {
		return checkTabPageExit("Login");
	}
	@SuppressWarnings("unchecked")
	public <T extends GeneralPage> T login(String userName, String password) {
		Utilities.enter(_txtUsername, userName);
		Utilities.enter(_txtPassword,password);
		Utilities.click(_btnLogin);
		Utilities.waitForPageFullyLoad();
		if(isLoggedIn()) {
			return (T) this;
		}
		return (T) new HomePage();
	}

}
