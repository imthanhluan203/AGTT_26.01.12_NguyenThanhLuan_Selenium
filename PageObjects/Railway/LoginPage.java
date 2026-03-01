package Railway;
import Common.JsonReader;
import Common.Utilities;
import Constant.Constant;
import DataObjects.UserInfo;
import Enum.PageTitle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends GeneralPage {


	public String getLblErrorLoginMessage() {
		return Utilities.getTextElement(JsonReader.getLocator(PageTitle.LOGIN, "lblErrorLoginMessage"));
	}
	
	public void clickForgotPassword() {
		Utilities.click(JsonReader.getLocator(PageTitle.LOGIN, "linkforgotPassword"));
	}
	
	public void sendRequestReset(String input) {
		Utilities.enter(JsonReader.getLocator(PageTitle.LOGIN, "txtEmailReset"), input);
		Utilities.click(JsonReader.getLocator(PageTitle.LOGIN, "btnSubmitReset"));
	}
	
	@SuppressWarnings("unchecked")
	public <T extends GeneralPage> T login(UserInfo myUser) {
		Utilities.scrollToElement(JsonReader.getLocator(PageTitle.LOGIN, "btnLogin"));
		Utilities.enter(JsonReader.getLocator(PageTitle.LOGIN, "txtUsername"), myUser.getName());
		Utilities.enter(JsonReader.getLocator(PageTitle.LOGIN, "txtPassword"), myUser.getPassword());
		Utilities.click(JsonReader.getLocator(PageTitle.LOGIN, "btnLogin"));		
		Utilities.waitForPageFullyLoad(PageTitle.HOME);
		if(Constant.WEBDRIVER.getTitle().equals(PageTitle.HOME.getValue())) {
			return (T) new HomePage();
		}
		return (T) this;
		
	}

}
