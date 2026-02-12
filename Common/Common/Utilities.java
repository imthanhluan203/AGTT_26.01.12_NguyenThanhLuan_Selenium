package Common;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.JulianFields;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;
import Constant.Tab;

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
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT_WAIT_SECOND));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public static void waitForPageFullyLoad() {
	    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT_WAIT_SECOND));
	    wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'"));
	}
	public static void waitForTabFullyLoad(Tab tab) {
	    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(3));
	    try {
	    	
	    	if(tab == Tab.HOME || tab == Tab.LOGOUT) {
		    	wait.until(ExpectedConditions.titleIs("Safe Railway"));
		    }else {
		    	wait.until(ExpectedConditions.titleContains(tab.getValue()));
		    }
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void waitForNewState(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(3));
			wait.until(ExpectedConditions.stalenessOf(element));
		} catch (Exception e) {
			return;
		}
	}
	
	public static void closeAllTabsExceptMain(String tabtitle) {
	    for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
	        Constant.WEBDRIVER.switchTo().window(handle);
	        Utilities.waitForTabFullyLoad(Tab.REGISTER);
	        if(!Constant.WEBDRIVER.getTitle().contains(tabtitle)) {
	        	Constant.WEBDRIVER.close();
	        }
	    }
	    String mainTab = Constant.WEBDRIVER.getWindowHandle();
	    Constant.WEBDRIVER.switchTo().window(mainTab);
	}

	public static long parseDateToJulian(String dmmyyyy) {
		LocalDate date = LocalDate.parse(dmmyyyy, Constant.FORMATTER);
		long julianDay = date.getLong(JulianFields.JULIAN_DAY);
		return julianDay;
	}
	
	public static String parseJulianToDate(long day) {
		LocalDate date = LocalDate.MIN.with(JulianFields.JULIAN_DAY, day);
		return date.format(Constant.FORMATTER);
	}
	
	
	public static void scrollToElement(By locator) {
		WebElement webElement = Utilities.waitForElementLocated(locator);
		scrollToElement(webElement);
	}
	
	public static void scrollToElement(WebElement webElement) {
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
		js.executeScript("arguments[0].scrollIntoView(true)", webElement);
	}
	
}
