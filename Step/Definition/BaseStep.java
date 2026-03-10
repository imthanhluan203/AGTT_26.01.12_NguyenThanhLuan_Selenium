package Definition;

import Constant.Constant;
import DataObjects.UserInfo;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseStep {
    @Before
    public void beforeMethod() {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new FirefoxDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }
    @After
    public void afterMethod(){
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
}
