package Railway;

import org.openqa.selenium.By;
import Common.Utilities;
import Constant.Constant;

public class RegisterPage extends GeneralPage {
	private final By _txtUsername = By.xpath("//input[@id='email']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _txtPassport = By.xpath("//input[@id='pid']");
	private final By _btnSubmit = By.xpath("//input[@type='submit']");
	
	
	public RegisterPage register() {
		Utilities.enter(_txtUsername, Constant.USERNAME);
		Utilities.enter(_txtPassword, Constant.PASSWORD);
		Utilities.enter(_txtConfirmPassword, Constant.PASSWORD);
		Utilities.enter(_txtPassport, Constant.PASSWORD);
		Utilities.click(_btnSubmit);
		return new RegisterPage();
	}
	
}
