package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Constant.Constant;

public class HomePage extends GeneralPage {
	private final By _txtWelcomeMessage = By.xpath("//div[@class='account']");
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}
	public WebElement getWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(_txtWelcomeMessage);
	}
	
	@Test
	public void hello() {
		System.out.print("hello");
	}
}
