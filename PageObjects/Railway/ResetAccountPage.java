package Railway;

import Common.JsonReader;
import Common.Utilities;
import Enum.PageTitle;

public class ResetAccountPage extends GeneralPage {	
	
	public void resetPassWord(String password,String confirmPassword) {
		Utilities.enter(JsonReader.getLocator(PageTitle.RESET_PASSWORD, "txtNewPassword"), password);
		Utilities.enter(JsonReader.getLocator(PageTitle.RESET_PASSWORD, "txtConfirmPassword"), confirmPassword);
		Utilities.click(JsonReader.getLocator(PageTitle.RESET_PASSWORD, "btnSubmitReset"));
	}
	
	public String getMessageReset() {
		return Utilities.getTextElement(JsonReader.getLocator(PageTitle.RESET_PASSWORD, "txtMessageReset"));
	}
	
	public String getResetoken() {
		return Utilities.getValueElement(JsonReader.getLocator(PageTitle.RESET_PASSWORD, "txtResetToken"));
	}
	
	public String getConfirmPasswordErr() {
		return Utilities.getTextElement(JsonReader.getLocator(PageTitle.RESET_PASSWORD, "lblConfirmPassword"));
	}
	
}
