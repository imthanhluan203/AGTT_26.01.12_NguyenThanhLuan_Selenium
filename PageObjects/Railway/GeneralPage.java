package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public abstract class GeneralPage {
	private final By tabLogin = By.xpath("//a[contains(@href,'Login')]");
	private final By tabRegister = By.xpath("//a[contains(@href,'Register')]");
	
	public WebElement getTabLogin() {
		return Constant.WEBDRIVER.findElement(tabLogin);
	}
	
	public WebElement getTabRegister() {
		return Constant.WEBDRIVER.findElement(tabRegister);
	}
	public LoginPage gotoLoginPage() {
		this.getTabLogin().click();
		return new LoginPage();
	}
	
}
