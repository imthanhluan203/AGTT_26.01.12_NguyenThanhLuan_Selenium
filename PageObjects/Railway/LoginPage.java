package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class LoginPage extends GeneralPage {
	private final By _txtUsername = By.xpath("//input[@id='username']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _btnLogin = By.xpath("//input[@type='submit']");
	private final By _lblErrorLoginMessage = By.xpath("//p[@class='message error LoginForm']");

	public WebElement getUserName() {
		return Constant.WEBDRIVER.findElement(_txtUsername);
	}

	public WebElement getPassword() {
		return Constant.WEBDRIVER.findElement(_txtPassword);
	}

	public WebElement getBtnLogin() {
		return Constant.WEBDRIVER.findElement(_btnLogin);
	}

	public WebElement getLblErrorLoginMessage() {
		return Constant.WEBDRIVER.findElement(_lblErrorLoginMessage);
	}

	public HomePage login(String userName, String password) throws InterruptedException {
		this.getUserName().sendKeys(userName);
		this.getPassword().sendKeys(password);
		this.getBtnLogin().click();
		Thread.sleep(2000);
		return new HomePage();
	}

}
