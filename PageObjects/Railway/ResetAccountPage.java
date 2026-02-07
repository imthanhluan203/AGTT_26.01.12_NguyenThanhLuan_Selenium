package Railway;

import org.openqa.selenium.By;

import Common.Utilities;

public class ResetAccountPage extends GeneralPage {
	private final By _txtNewPassword = By.xpath("//input[@id='newPassword']");
	private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _btnSubmitReset = By.xpath("//input[@value='Reset Password']");
	private final By _txtMessageReset = By.xpath("//p[contains(@class,'message')]");
	private final By _txtResetToken = By.xpath("//input[@id='resetToken']");
	
	public void enterPassword(String password) {
		Utilities.enter(_txtNewPassword, password);
	}
	
	public void enterConfirmPassword(String confirmPassword) {
		Utilities.enter(_txtConfirmPassword, confirmPassword);
	}
	
	public void clickSubmitButton() {
		Utilities.click(_btnSubmitReset);
	}
	
	public String getMessageReset() {
		return Utilities.getTextElement(_txtMessageReset);
	}
	
	public String getResetoken() {
		return Utilities.getValueElement(_txtResetToken);
	}
	
}
