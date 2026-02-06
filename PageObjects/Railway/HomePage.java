package Railway;

import org.openqa.selenium.By;
import Constant.Constant;

public class HomePage extends GeneralPage {
	private final By _txtWelcomeMessage = By.xpath("//div[@class='account']");
	private final By _linkCreateAccount = By.xpath("//a[text()='create an account']");
	
	
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}
	public String getWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(_txtWelcomeMessage).getText();
	}
	
	public By getCreateAccountLink() {
		return _linkCreateAccount;
	}
	
	public LoginPage getLoginPage() {
		return new LoginPage();
	}
	
}
