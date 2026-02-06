package Railway;

import org.openqa.selenium.By;

import Common.Utilities;
import Constant.Constant;
import UserMail.UserInfo;

public class RegisterPage extends GeneralPage {
	private final By _txtUsername = By.xpath("//input[@id='email']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _txtPassport = By.xpath("//input[@id='pid']");
	private final By _btnSubmit = By.xpath("//input[@type='submit']");
	private final By _txtErrorMessage = By.xpath("//p[@class='message error']");
	private final By _txtErrorInvalidPassword = By.xpath("//li[@class='password']//label[@class='validation-error']");
	private final By _txtErrorInvalidPid = By.xpath("//li[@class='pid-number']//label[@class='validation-error']");
	
	
	public String getErrorMessage() {
		return Constant.WEBDRIVER.findElement(_txtErrorMessage).getText();
	}
	
	public String getErrorInvalidPassword() {
		return Constant.WEBDRIVER.findElement(_txtErrorInvalidPassword).getText();
	}
	
	public String getErrorInvalidPid() {
		return Constant.WEBDRIVER.findElement(_txtErrorInvalidPid).getText();
	}
	
	public RegisterPage register(UserInfo myInfo)  {
		
		System.out.println(myInfo.getName());
		Utilities.enter(_txtUsername, myInfo.getName());
		Utilities.enter(_txtPassword, myInfo.getPassword());
		Utilities.enter(_txtConfirmPassword, myInfo.getPassword());
		Utilities.enter(_txtPassport, myInfo.getPid());
		Utilities.click(_btnSubmit);

		return new RegisterPage();
	}
	
}
