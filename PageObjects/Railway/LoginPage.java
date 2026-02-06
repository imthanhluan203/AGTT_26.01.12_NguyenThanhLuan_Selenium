package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import Constant.Tab;
import UserMail.UserInfo;

public class LoginPage extends GeneralPage {
	private final By _txtUsername = By.xpath("//input[@id='username']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _btnLogin = By.xpath("//input[@type='submit']");
	private final By _lblErrorLoginMessage = By.xpath("//p[@class='message error LoginForm']");

	public WebElement getLblErrorLoginMessage() {
		return Constant.WEBDRIVER.findElement(_lblErrorLoginMessage);
	}
	public Boolean isLoggedIn() {
		return checkTabPageExit(Tab.LOGIN);
	}
	@SuppressWarnings("unchecked")
	public <T extends GeneralPage> T login(UserInfo myUser) {
		Utilities.enter(_txtUsername, myUser.getName());
		Utilities.enter(_txtPassword, myUser.getPassword());
		Utilities.click(_btnLogin);
		Utilities.waitForPageFullyLoad();
		if(isLoggedIn()) {
			return (T) this;
		}
		return (T) new HomePage();
	}

}
