package TestPackage;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import Constant.BookTicketFormField;
import Constant.Tab;
import Constant.TimeTable;
import Constant.SeatType;
import DataObjects.Ticket;
import DataObjects.UserInfo;
import Railway.BookTicketPage;
import Railway.HomePage;
import Railway.LoginPage;
import Railway.TimeTablePage;

public class BookTicketTest extends BaseTest {
	@Test(description = "User can book 1 ticket at a time",enabled = false)
	public void TC12() {
		String expectedResult = "Ticket booked successfully!";
		
		
		System.out.println("Pre-condition: an actived account is existing.");
		myUserInfo = new UserInfo("hwtwwups@sharklasers.com", "987654321");
		int duration = 2;
		Ticket myTicket = new Ticket("Nha Trang", "Huế", SeatType.SBC, "1",duration);
	
		
		System.out.println("1. Navigate to QA Railway Website.");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("2. Login with a valid account.");
		LoginPage loginPage = homePage.gotoPage(Tab.LOGIN, LoginPage.class);
		homePage = loginPage.login(myUserInfo);
		
		System.out.println("3. Click on \"Book ticket\" tab.");
		BookTicketPage bookTicketPage = homePage.gotoPage(Tab.BOOKTICKET, BookTicketPage.class);
		
		System.out.println("4. Select the next 2 days from \"Depart date\"");
		System.out.println("5. Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
		System.out.println("6. Select \"Soft bed with air conditioner\" for \"Seat type\"");
		System.out.println("7. Select \"1\" for \"Ticket amount\"");
		String departDate = bookTicketPage.bookTicket(myTicket);
		
		System.out.println("8. Click on \"Book ticket\" button");
		bookTicketPage.submit();
		
		//Message "Ticket booked successfully!" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)
		String verifyString = "VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)";
		System.out.println(verifyString);
		String actualResult = bookTicketPage.getBookTicketMessage();
		Assert.assertEquals(actualResult, expectedResult,verifyString);
		Map<BookTicketFormField, String> actualResults = bookTicketPage.getTicketInformation();
		Assert.assertEquals(actualResults.get(BookTicketFormField.DEPART_DATE), departDate);
		Assert.assertEquals(actualResults.get(BookTicketFormField.DEPART_FROM), myTicket.getDepartFrom(),verifyString);
		Assert.assertEquals(actualResults.get(BookTicketFormField.ARRIVE_AT), myTicket.getArriveAt(),verifyString);
		Assert.assertEquals(actualResults.get(BookTicketFormField.SEAT_TYPE), myTicket.getSeatType(),verifyString);
		Assert.assertEquals(actualResults.get(BookTicketFormField.TICKET_AMOUNT), myTicket.getTicketAmount(),verifyString);		
	}
	
	@Test(description = "User can book many tickets at a time", enabled = false)
	public void TC13() {
		String expectedResult = "Ticket booked successfully!";
		System.out.println("Pre-condition: an actived account is existing");
		myUserInfo = new UserInfo("hwtwwups@sharklasers.com", "987654321");
		Ticket myTicket = new Ticket("Nha Trang", "Sài Gòn", SeatType.SBC, "5",10);
		
		System.out.println("1. Navigate to QA Railway Website");
		HomePage home = new HomePage();
		home.open();
		
		System.out.println("2. Login with a valid account ");
		LoginPage login = home.gotoPage(Tab.LOGIN, LoginPage.class);
		home = login.login(myUserInfo);
		
		System.out.println("3. Click on \"Book ticket\" tab");
		BookTicketPage bookTicketPage = home.gotoPage(Tab.BOOKTICKET, BookTicketPage.class);
		
		System.out.println("4. Select the next 25 days from \"Depart date\"");
		System.out.println("5. Select \"Nha Trang\" for \"Depart from\" and \"Sài Gòn\" for \"Arrive at\".");
		System.out.println("6. Select \"Soft seat with air conditioner\" for \"Seat type\"");
		System.out.println("7. Select \"5\" for \"Ticket amount\"");
		String departDate = bookTicketPage.bookTicket(myTicket);
		
		System.out.println("8. Click on \"Book ticket\" button");
		//Message "Ticket booked successfully!" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)
		bookTicketPage.submit();
		String verifyString = "VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)";
		System.out.println(verifyString);
		String actualResult = bookTicketPage.getBookTicketMessage();
		Assert.assertEquals(actualResult, expectedResult,verifyString);
		Map<BookTicketFormField, String> actualResults = bookTicketPage.getTicketInformation();
		Assert.assertEquals(actualResults.get(BookTicketFormField.DEPART_DATE), departDate);
		Assert.assertEquals(actualResults.get(BookTicketFormField.DEPART_FROM), myTicket.getDepartFrom(),verifyString);
		Assert.assertEquals(actualResults.get(BookTicketFormField.ARRIVE_AT), myTicket.getArriveAt(),verifyString);
		Assert.assertEquals(actualResults.get(BookTicketFormField.SEAT_TYPE), myTicket.getSeatType(),verifyString);
		Assert.assertEquals(actualResults.get(BookTicketFormField.TICKET_AMOUNT), myTicket.getTicketAmount(),verifyString);		
		
	}
	
	@Test(description = "User can check price of ticket from Timetable",enabled = true)
	public void TC14() {
		System.out.println("Pre-condition: an actived account is existing");
		myUserInfo = new UserInfo("hwtwwups@sharklasers.com", "987654321");
		String verifyString = String.format("VP: %s %n %s %n %s %n %s %n", 
				"\"Ticket Price\" page is loaded.",
				"Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\".",
				"Price for each seat displays correctly",
				"HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000"
				);
		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("2. Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(Tab.LOGIN, LoginPage.class);
		homePage = loginPage.login(myUserInfo);
		
		System.out.println("3. Click on \"Timetable\" tab");
		TimeTablePage timeTablePage = homePage.gotoPage(Tab.TIMETABLE, TimeTablePage.class);
		
		System.out.println("4. Click on \"check price\" link of the route from \"Đà Nẵng\" to \"Sài Gòn\"");
		timeTablePage.timeTableAction("Đà Nẵng","Sài Gòn",TimeTable.CHECK_PRICE);
		
		//"Ticket Price" page is loaded.
		//Ticket table shows "Ticket price from Đà Nẵng to Sài Gòn".
		//Price for each seat displays correctly
		//HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000
		System.out.println(verifyString);
	}
}
