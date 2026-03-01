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
import Enum.PageTitle;

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
		removeElement();
		Utilities.scrollToElement(element);
		element.click();
	}

	public static void removeElement() {
		try {

			WebElement element = Constant.WEBDRIVER.findElement(By.xpath("//div[@class='status_alert shadow']"));
			((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].remove();", element);
		} catch (Exception e) {
			System.out.println("Không tìm thấy quảng cáo để xóa: " + e.getMessage());
		}
	}
	public static String getTextElement(By locator) {
		WebElement element = Utilities.waitForElementLocated(locator);
		scrollToElement(element);
		return element.getText();
	}
	
	public static String getTextElementByJS(By locator) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT_WAIT_SECOND));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));;
		String text = (String) ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("return arguments[0].innerText;", element);
		return text.strip();
	}
	
	public static String getValueElement(By locator) {
		WebElement element = Utilities.waitForElementLocated(locator);
		scrollToElement(element);
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
	
	public static void waitForPageFullyLoad(PageTitle page) {
	    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(3));
	    try {
	    	wait.until(ExpectedConditions.titleIs(page.getValue()));
		} catch (Exception e) {
			return;
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
	
	public static void closeAllTabsExceptMain(PageTitle page) {
	    for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
	        Constant.WEBDRIVER.switchTo().window(handle);
	        Utilities.waitForPageFullyLoad(page);
	        if(!Constant.WEBDRIVER.getTitle().equals(page.getValue())) {
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
