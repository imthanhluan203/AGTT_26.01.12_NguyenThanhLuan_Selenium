package Common;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class Utilities {
	
	public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }
        
        return result.toString();
    }
	public static void click(By locator) {
		WebElement element = Utilities.waitForElementLocated(locator);
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
		js.executeScript("arguments[0].click();", element);
	}
	
	public static String getTextElement(By locator) {
		WebElement element = Utilities.waitForElementLocated(locator);
		return element.getText();
	}
	
	public static String getValueElement(By locator) {
		WebElement element = Utilities.waitForElementLocated(locator);
		return element.getAttribute("value");
	}
	
	
	public static void enter(By locator,String key) {
		WebElement element = Utilities.waitForElementLocated(locator);
		element.clear();
		element.sendKeys(key);
	}
	
	public static WebElement waitForElementLocated(By locator) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(60));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public static void waitForPageFullyLoad() {
	    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(30));
	    wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'"));
	}
	
	public static void closeAllTabsExceptMain(String tabtitle) {
	    for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
	        Constant.WEBDRIVER.switchTo().window(handle);
	        if(!Constant.WEBDRIVER.getTitle().contains(tabtitle)) {
	        	Constant.WEBDRIVER.close();
	        }
	    }
	    String mainTab = Constant.WEBDRIVER.getWindowHandle();
	    Constant.WEBDRIVER.switchTo().window(mainTab);
	}

}
