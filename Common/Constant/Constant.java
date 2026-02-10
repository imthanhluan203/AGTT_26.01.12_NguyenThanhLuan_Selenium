package Constant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;

public class Constant {
	public static WebDriver WEBDRIVER;
	public static final int TIMEOUT_WAIT_SECOND = 60;
	public static final String MAIL_TYPE =	"@sharklasers.com";
	public static final String EMAIL_URL = "https://www.guerrillamail.com/inbox";
	public static final String RAILWAY_URL = "http://saferailway.somee.com/Page/HomePage.cshtml";
	public static final String USERNAME = "irrlbgzb@sharklasers.com";
	public static final String PASSWORD = "123456789";
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");
	public static final String TODAY = LocalDate.now().format(FORMATTER);
}
