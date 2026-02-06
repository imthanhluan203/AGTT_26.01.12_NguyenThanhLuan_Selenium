package Guerrillamail;

import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;

import Common.Utilities;
import Constant.Constant;
import UserMail.UserInfo;

public class GuerrillaMail {
	

	private final By _editButton = By.xpath("//span[@class='editable button']");
	private final By _txtMail = By.xpath("//span[@id='inbox-id']/input");
	private final By _setButton = By.xpath("//button[text()='Set']");
	private final By _mailconfirmLink = By.xpath("//td[contains(text(),'thanhletraining03@gmail.com')]");
	private final By _confirmLink = By.xpath("//div[@class='email_body']//a[contains(@href,confirmationCode)]");
	
	private UserInfo userInfo;
	
	public GuerrillaMail(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
	
	public void createAnEmail() {
		Constant.WEBDRIVER.get(Constant.EMAIL_URL);
		this.setAnEmail(userInfo.getName());	
		Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
	    Constant.WEBDRIVER.get(Constant.RAILWAY_URL);   
	}
	
	public void setAnEmail(String emailName) {
		Utilities.click(_editButton);
		Utilities.enter(_txtMail, emailName);
		Utilities.click(_setButton);
		Utilities.waitForPageFullyLoad();
	}
	
	public void waitAndClickConfirmEmail() {
		Utilities.click(_mailconfirmLink);
		Utilities.click(_confirmLink);
	}
	
	
}
