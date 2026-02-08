package Guerrillamail;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Utilities;
import Constant.Constant;
import UserMail.UserInfo;

public class GuerrillaMail {
	

	private final By _editButton = By.xpath("//span[@class='editable button']");
	private final By _txtMail = By.xpath("//span[@id='inbox-id']/input");
	private final By _setButton = By.xpath("//button[text()='Set']");
	private final By _mailconfirmLink = By.xpath("//td[contains(text(),'thanhletraining03@gmail.com')]");
	private final By _confirmLink = By.xpath("//div[@class='email_body']//a");
	private final By _checkboxMail = By.xpath("//input[@type='checkbox']");
	private final By _btnDelete = By.xpath("//input[@value='Delete']");
	
	private UserInfo userInfo;
	
	public GuerrillaMail(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public void cleanAllOldMail() {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(5));
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
		try {
			wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(_checkboxMail, 2));
			List<WebElement> listCheckBox = Constant.WEBDRIVER.findElements(_checkboxMail);
			listCheckBox.forEach(x ->{
				js.executeScript("arguments[0].click();", x);
			});
			Utilities.click(_btnDelete);
		} catch (Exception e) {
			List<WebElement> listCheckBox = Constant.WEBDRIVER.findElements(_checkboxMail);
			listCheckBox.forEach(x ->{
				js.executeScript("arguments[0].click();", x);
				System.out.println(x);
			});
			Utilities.click(_btnDelete);
		}
		
		
	}
	
	public void createAnEmail() {
		Constant.WEBDRIVER.get(Constant.EMAIL_URL);
		this.setAnEmail();	
		Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
	    Constant.WEBDRIVER.get(Constant.RAILWAY_URL);   
	}
	
	public void setAnEmail() {
		Utilities.click(_editButton);
		Utilities.enter(_txtMail, userInfo.getUserName());
		Utilities.click(_setButton);
		Utilities.waitForPageFullyLoad();
	}
	
	public void waitAndClickConfirmEmail() {
		Utilities.click(_mailconfirmLink);
		Utilities.click(_confirmLink);
	}
	
	
}
