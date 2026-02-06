package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
	
	public WebElement getErrorMessage() {
		return Constant.WEBDRIVER.findElement(_txtErrorMessage);
	}
	
	public RegisterPage register(UserInfo myInfo)  {
		System.out.println(myInfo.getName());
		Utilities.enter(_txtUsername, myInfo.getName());
		Utilities.enter(_txtPassword, myInfo.getPassword());
		Utilities.enter(_txtConfirmPassword, myInfo.getPassword());
		Utilities.enter(_txtPassport, myInfo.getPassword());
		Utilities.click(_btnSubmit);

		return new RegisterPage();
	}
	
}
