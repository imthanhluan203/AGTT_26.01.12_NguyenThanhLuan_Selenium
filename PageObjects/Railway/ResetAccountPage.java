package Railway;

import org.openqa.selenium.By;

import Common.Utilities;

public class ResetAccountPage extends GeneralPage {
	private final By _txtNewPassword = By.xpath("//input[@id='newPassword']");
	private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _btnSubmitReset = By.xpath("//input[@value='Reset Password']");
	private final By _txtMessageReset = By.xpath("//p[contains(@class,'message')]");
	private final By _txtResetToken = By.xpath("//input[@id='resetToken']");
	private final By _lblConfirmPassword = By.xpath("//li[@class='confirm-password']//label[@class='validation-error']");
	
	
	public void resetPassWord(String password,String confirmPassword) {
		Utilities.enter(_txtNewPassword, password);
		Utilities.enter(_txtConfirmPassword, confirmPassword);
		Utilities.click(_btnSubmitReset);
	}
	
	
	public String getMessageReset() {
		return Utilities.getTextElement(_txtMessageReset);
	}
	
	public String getResetoken() {
		return Utilities.getValueElement(_txtResetToken);
	}
	
	public String getConfirmPasswordErr() {
		return Utilities.getTextElement(_lblConfirmPassword);
	}
	
}
