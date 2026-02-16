package Railway;

import org.openqa.selenium.By;

import Common.Utilities;
import Constant.PageTitle;
import DataObjects.UserInfo;

public class RegisterPage extends GeneralPage {
	private final By _txtUsername = By.xpath("//input[@id='email']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _txtPassport = By.xpath("//input[@id='pid']");
	private final By _btnSubmit = By.xpath("//input[@type='submit']");
	private final By _txtErrorMessage = By.xpath("//p[@class='message error']");
	private final By _txtErrorInvalidPassword = By.xpath("//li[@class='password']//label[@class='validation-error']");
	private final By _txtErrorInvalidPid = By.xpath("//li[@class='pid-number']//label[@class='validation-error']");
	private final By _txtWelcomeMessage = By.xpath("//div[@id='content']/p");
	private final By _txtThankyouMessage = By.xpath("//h1");
	
	
	public String getThankyouMessage() {
		return Utilities.getTextElement(_txtThankyouMessage);
	}
	
	public String getWelcomeMessage() {
		return Utilities.getTextElement(_txtWelcomeMessage);
	}
	
	public String getErrorMessage() {
		return Utilities.getTextElement(_txtErrorMessage);
	}
	
	public String getErrorInvalidPassword() {
		return Utilities.getTextElement(_txtErrorInvalidPassword);
	}
	
	public String getErrorInvalidPid() {
		return Utilities.getTextElement(_txtErrorInvalidPid);
	}
	
	public RegisterPage register(UserInfo myInfo)  {
		Utilities.scrollToElement(_btnSubmit);
		Utilities.enter(_txtUsername, myInfo.getName());
		Utilities.enter(_txtPassword, myInfo.getPassword());
		Utilities.enter(_txtConfirmPassword, myInfo.getPassword());
		Utilities.enter(_txtPassport, myInfo.getPid());
		Utilities.click(_btnSubmit);
		Utilities.waitForPageFullyLoad(PageTitle.REGISTER_THANK);
		return new RegisterPage();
	}
	
}
