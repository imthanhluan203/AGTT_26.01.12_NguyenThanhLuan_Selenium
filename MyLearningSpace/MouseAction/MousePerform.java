package MouseAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;


public class MousePerform {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");
		
		WebElement element = driver.findElement(By.xpath("//div[@id='draggable']"));
		
		Action myAction = new Action() {
			
			@Override
			public void perform() {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
