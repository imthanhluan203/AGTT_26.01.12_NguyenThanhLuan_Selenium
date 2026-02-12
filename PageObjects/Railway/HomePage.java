package Railway;

import org.openqa.selenium.By;

import Common.Utilities;
import Constant.Constant;

public class HomePage extends GeneralPage {
	private final By _txtWelcomeMessage = By.xpath("//div[@class='account']");
	private final By _linkCreateAccount = By.xpath("//a[text()='create an account']");
	
	
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}
	
	public String getWelcomeMessage() {
		return Utilities.getTextElement(_txtWelcomeMessage);
	}
	
	public RegisterPage getCreateAccountPage() {
		Utilities.click(_linkCreateAccount);
		return new RegisterPage();
	}
	
	public LoginPage getLoginPage() {
		return new LoginPage();
	}
	
}
