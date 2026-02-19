package Railway;
import Common.JsonReader;
import Common.Utilities;
import DataObjects.UserInfo;
import Enum.PageTitle;

public class RegisterPage extends GeneralPage {
		
	public String getThankyouMessage() {
		return Utilities.getTextElement(JsonReader.getLocator(PageTitle.REGISTER, "lblThankyouMessage"));
	}
	
	public String getWelcomeMessage() {
		return Utilities.getTextElement(JsonReader.getLocator(PageTitle.REGISTER, "lblWelcomeMessage"));
	}
	
	public String getErrorMessage() {
		return Utilities.getTextElement(JsonReader.getLocator(PageTitle.REGISTER, "lblErrorMessage"));
	}
	
	public String getErrorInvalidPassword() {
		return Utilities.getTextElement(JsonReader.getLocator(PageTitle.REGISTER, "lblErrorInvalidPassword"));
	}
	
	public String getErrorInvalidPid() {
		return Utilities.getTextElement(JsonReader.getLocator(PageTitle.REGISTER, "lblErrorInvalidPid"));
	}
	
	public RegisterPage register(UserInfo myInfo)  {
		Utilities.scrollToElement(JsonReader.getLocator(PageTitle.REGISTER, "btnSubmit"));
		Utilities.enter(JsonReader.getLocator(PageTitle.REGISTER, "txtUsername"), myInfo.getName());
		Utilities.enter(JsonReader.getLocator(PageTitle.REGISTER, "txtPassword"), myInfo.getPassword());
		Utilities.enter(JsonReader.getLocator(PageTitle.REGISTER, "txtConfirmPassword"), myInfo.getPassword());
		Utilities.enter(JsonReader.getLocator(PageTitle.REGISTER, "txtPassport"), myInfo.getPid());
		Utilities.click(JsonReader.getLocator(PageTitle.REGISTER, "btnSubmit"));
		Utilities.waitForPageFullyLoad(PageTitle.REGISTER_THANK);
		return new RegisterPage();
	}
	
}
