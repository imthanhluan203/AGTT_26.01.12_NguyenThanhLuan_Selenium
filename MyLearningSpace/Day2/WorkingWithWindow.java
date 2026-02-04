package Day2;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WorkingWithWindow {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.selenium.dev/selenium/web/window_switching_tests/page_with_frame.html");
		driver.findElement(By.linkText("Open new window")).click();
        //fetch handles of all windows, there will be two, [0]- default, [1] - new window
        Object[] windowHandles=driver.getWindowHandles().toArray();
        Arrays.asList(windowHandles).stream().forEach(System.out::println);
        
        driver.switchTo().window((String) windowHandles[1]);
        //assert on title of new window
        String title=driver.getTitle();
        System.out.println(title);

	}
}
