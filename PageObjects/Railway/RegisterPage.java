package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class RegisterPage extends GeneralPage {
	private final By _txtUsername = By.xpath("//input[@id='email']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _txtPassport = By.xpath("//input[@id='pid']");
	
	public WebElement getTxtUsername() {
		return Constant.WEBDRIVER.findElement(_txtUsername);
	}
	public WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(_txtPassword);
	}
	
	public WebElement getTxtConfirmPassword() {
		return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
	}
	
	public WebElement getPassport() {
		return Constant.WEBDRIVER.findElement(_txtPassport);
	}
}
