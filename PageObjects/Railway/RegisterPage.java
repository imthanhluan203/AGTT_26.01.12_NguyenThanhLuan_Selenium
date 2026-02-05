package Railway;

import org.openqa.selenium.By;
import Common.Utilities;
import Guerrillamail.UserInfo;

public class RegisterPage extends GeneralPage {
	private final By _txtUsername = By.xpath("//input[@id='email']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _txtPassport = By.xpath("//input[@id='pid']");
	private final By _btnSubmit = By.xpath("//input[@type='submit']");
	
	
	public RegisterPage register(UserInfo myInfo)  {
		System.out.println(myInfo.getFullEmailName());
		Utilities.enter(_txtUsername, myInfo.getFullEmailName());
		Utilities.enter(_txtPassword, myInfo.getPassword());
		Utilities.enter(_txtConfirmPassword, myInfo.getPassword());
		Utilities.enter(_txtPassport, myInfo.getPassword());
		Utilities.click(_btnSubmit);

		return new RegisterPage();
	}
	
}
