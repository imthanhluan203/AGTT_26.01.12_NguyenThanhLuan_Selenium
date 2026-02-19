package Railway;
import Common.JsonReader;
import Common.Utilities;
import Constant.Constant;
import Enum.PageTitle;

public class HomePage extends GeneralPage {	
	
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}
	
	public String getWelcomeMessage() {
		return Utilities.getTextElementByJS(JsonReader.getLocator(PageTitle.HOME, "txtWelcomeMessage"));
	}
	
	public RegisterPage getCreateAccountPage() {
		Utilities.click(JsonReader.getLocator(PageTitle.HOME, "linkCreateAccount"));
		return new RegisterPage();
	}
	
	public LoginPage getLoginPage() {
		return new LoginPage();
	}
	
}
