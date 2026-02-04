package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Constant.Constant;

public class RegisterPage extends GeneralPage {
	private final By _txtUsername = By.xpath("//input[@id='email']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _txtPassport = By.xpath("//input[@id='pid']");
	private final By _btnSubmit = By.xpath("//input[@type='submit']");
	
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
	
	public WebElement getSubmitButton() {
		return Constant.WEBDRIVER.findElement(_btnSubmit);
	}
	
	public RegisterPage register() {
		this.getTxtUsername().sendKeys(Constant.USERNAME);
		this.getTxtPassword().sendKeys(Constant.PASSWORD);
		this.getTxtConfirmPassword().sendKeys(Constant.PASSWORD);
		this.getPassport().sendKeys(Constant.PASSWORD);
		this.getSubmitButton().click();
		return new RegisterPage();
	}
	
	public static void main(String[] args) throws Exception {
		Constant.WEBDRIVER = new ChromeDriver();
		HomePage home = new HomePage();
		home.open();
		RegisterPage myPage = home.gotoPage("Register", RegisterPage.class);
		myPage.register();
	}
}
