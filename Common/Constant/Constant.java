package Constant;

import org.openqa.selenium.WebDriver;
import Common.Utilities;

public class Constant {
	public static WebDriver WEBDRIVER;
	public static final String MAIL_TYPE =	"@sharklasers.com";
	public static final String EMAIL_URL = "https://www.guerrillamail.com/inbox";
	public static final String RAILWAY_URL = "http://saferailway.somee.com/Page/HomePage.cshtml";
	public static final String NAME = Utilities.generateRandomString(15);
	
	public static final String USERNAME = String.format("%s@sharklasers.com",NAME);
	public static final String PASSWORD = "123456789";
}
