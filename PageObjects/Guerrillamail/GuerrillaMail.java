package Guerrillamail;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.JsonReader;
import Common.Utilities;
import Constant.Constant;
import DataObjects.UserInfo;
import Enum.PageTitle;

public class GuerrillaMail {
		
	private UserInfo userInfo;
	
	public GuerrillaMail(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public void cleanAllOldMail() {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(5));
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
		try {
			wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(JsonReader.getLocator(PageTitle.GUERRILLA_MAIL, "checkboxMail"), 2));
			List<WebElement> listCheckBox = Constant.WEBDRIVER.findElements(JsonReader.getLocator(PageTitle.GUERRILLA_MAIL, "checkboxMail"));
			listCheckBox.forEach(x ->{
				js.executeScript("arguments[0].click();", x);
			});
			Utilities.click(JsonReader.getLocator(PageTitle.GUERRILLA_MAIL, "btnDelete"));
		} catch (Exception e) {
			List<WebElement> listCheckBox = Constant.WEBDRIVER.findElements(JsonReader.getLocator(PageTitle.GUERRILLA_MAIL, "checkboxMail"));
			listCheckBox.forEach(x ->{
				js.executeScript("arguments[0].click();", x);
				System.out.println(x);
			});
			Utilities.click(JsonReader.getLocator(PageTitle.GUERRILLA_MAIL, "btnDelete"));
		}
		
		
	}
	
	public void createAnEmail() {
		Constant.WEBDRIVER.get(Constant.EMAIL_URL);
		this.setAnEmail();	
		Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
	    Constant.WEBDRIVER.get(Constant.RAILWAY_URL);   
	}
	
	public void setAnEmail() {
		Utilities.click(JsonReader.getLocator(PageTitle.GUERRILLA_MAIL, "btnEdit"));
		Utilities.enter(JsonReader.getLocator(PageTitle.GUERRILLA_MAIL, "txtMail"), userInfo.getUserName());
		Utilities.click(JsonReader.getLocator(PageTitle.GUERRILLA_MAIL, "btnSetMail"));
	}
	
	public void waitAndClickConfirmEmail() {
		Utilities.click(JsonReader.getLocator(PageTitle.GUERRILLA_MAIL, "linkMailconfirm"));
		Utilities.click(JsonReader.getLocator(PageTitle.GUERRILLA_MAIL, "linkConfirm"));
		Utilities.waitForPageFullyLoad(PageTitle.REGISTER_CONFIRM);
	}
	
	
}
