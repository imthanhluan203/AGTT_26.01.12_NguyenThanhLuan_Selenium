package TestPackage;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;
import Constant.Constant;
import Constant.Tab;
import Constant.TableHeader;
import Constant.SeatType;
import DataObjects.Ticket;
import DataObjects.UserInfo;
import Railway.BookTicketPage;
import Railway.HomePage;
import Railway.LoginPage;
import Railway.TicketPricePage;
import Railway.TimeTablePage;

public class BookTicketTest extends BaseTest {
	@Test(description = "User can book 1 ticket at a time",enabled = false)
	public void TC12() {
		String expectedResult = "Ticket booked successfully!";
		
		
		System.out.println("Pre-condition: an actived account is existing.");
		myUserInfo = new UserInfo(Constant.USERNAME, Constant.PASSWORD);
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
		String verifyString = "VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)";
		System.out.println(verifyString);
		String actualResult = bookTicketPage.getBookTicketMessage();
		String actualDepartStation = bookTicketPage.getTextFieldBookedTicket("Depart Station");
		String actualDepartDate = bookTicketPage.getTextFieldBookedTicket("Depart Date");
		String actualArriveStation = bookTicketPage.getTextFieldBookedTicket("Arrive Station");
		String actualSeatType = bookTicketPage.getTextFieldBookedTicket("Seat Type");
		String actualAmount = bookTicketPage.getTextFieldBookedTicket("Amount");
		
		Assert.assertEquals(actualResult, expectedResult,verifyString);
		Assert.assertEquals(actualDepartDate, departDate, verifyString);
		Assert.assertEquals(actualDepartStation, myTicket.getDepartFrom(),verifyString);
		Assert.assertEquals(actualArriveStation, myTicket.getArriveAt(),verifyString);
		Assert.assertEquals(actualSeatType, myTicket.getSeatType(),verifyString);
		Assert.assertEquals(actualAmount, myTicket.getTicketAmount(),verifyString);		
	}
	
	@Test(description = "User can book many tickets at a time", enabled = false)
	public void TC13() {
		String expectedResult = "Ticket booked successfully!";
		System.out.println("Pre-condition: an actived account is existing");
		myUserInfo = new UserInfo(Constant.USERNAME, Constant.PASSWORD);
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
		String actualDepartStation = bookTicketPage.getTextFieldBookedTicket("Depart Station");
		String actualDepartDate = bookTicketPage.getTextFieldBookedTicket("Depart Date");
		String actualArriveStation = bookTicketPage.getTextFieldBookedTicket("Arrive Station");
		String actualSeatType = bookTicketPage.getTextFieldBookedTicket("Seat Type");
		String actualAmount = bookTicketPage.getTextFieldBookedTicket("Amount");
		
		Assert.assertEquals(actualResult, expectedResult,verifyString);
		Assert.assertEquals(actualDepartDate, departDate, verifyString);
		Assert.assertEquals(actualDepartStation, myTicket.getDepartFrom(),verifyString);
		Assert.assertEquals(actualArriveStation, myTicket.getArriveAt(),verifyString);
		Assert.assertEquals(actualSeatType, myTicket.getSeatType(),verifyString);
		Assert.assertEquals(actualAmount, myTicket.getTicketAmount(),verifyString);		
		
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
		TicketPricePage ticketPrice = timeTablePage.timeTableAction("Đà Nẵng","Sài Gòn",TableHeader.CHECKPRICE);
		String expectedPage = "Safe Railway - Ticket Price";
		String actualPage = Constant.WEBDRIVER.getTitle();
		
		String expectedTableName = "Ticket price from Đà Nẵng to Sài Gòn";
		String actualTableName = ticketPrice.getTableName();
		
		String actualPriceHS = ticketPrice.getPrice(SeatType.HS);
		String actualPriceSS = ticketPrice.getPrice(SeatType.SS);
		String actualPriceSSC = ticketPrice.getPrice(SeatType.SSC);
		String actualPriceHB = ticketPrice.getPrice(SeatType.HB);
		String actualPriceSB = ticketPrice.getPrice(SeatType.SB);
		String actualPriceSBC = ticketPrice.getPrice(SeatType.SBC);
		
		
		System.out.println(verifyString);
		
		Assert.assertEquals(actualPage, expectedPage,verifyString);
		Assert.assertEquals(expectedTableName, actualTableName,verifyString);
		Assert.assertEquals(actualPriceHS, "310000",verifyString);
		Assert.assertEquals(actualPriceSS, "335000",verifyString);
		Assert.assertEquals(actualPriceSSC, "360000",verifyString);
		Assert.assertEquals(actualPriceHB, "410000",verifyString);
		Assert.assertEquals(actualPriceSB, "460000",verifyString);
		Assert.assertEquals(actualPriceSBC, "510000",verifyString);
		
		
	}
	
	@Test(description = "User can book ticket from Timetable", enabled = false)
	public void TC15() {
		System.out.println("Pre-condition: an actived account is existing");
		myUserInfo = new UserInfo("lonxskqi@sharklasers.com", "987654321");
		Ticket myTicket = new Ticket("Quảng Ngãi", "Huế", SeatType.HS, "5",1);
		
		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("2. Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(Tab.LOGIN, LoginPage.class);
		homePage = loginPage.login(myUserInfo);
		
		System.out.println("3. Click on \"Timetable\" tab");
		TimeTablePage timeTablePage = homePage.gotoPage(Tab.TIMETABLE, TimeTablePage.class);
		
		System.out.println("4. Click on book ticket of route \"Quảng Ngãi\" to \"Huế\"");
		BookTicketPage bookTicketPage = timeTablePage.timeTableAction("Quảng Ngãi", "Huế", TableHeader.BOOKTICKET);
		//Book ticket form is shown with the corrected "depart from" and "Arrive at"
		String verifyString1 = "VP: Book ticket form is shown with the corrected \"depart from\" and \"Arrive at\"";
		String departFrom = bookTicketPage.getDepartionText();
		String arriveAt = bookTicketPage.getArriveAtText();
		System.out.println(verifyString1);
		Assert.assertEquals(departFrom, "Quảng Ngãi",verifyString1);
		Assert.assertEquals(arriveAt, "Huế", verifyString1);		
				
		System.out.println("5. Select Depart date = tomorrow");
		System.out.println("6. Select Ticket amount = 5");
		String departDate = bookTicketPage.bookTicket(myTicket, LocalDate.now().plusDays(5).format(Constant.FORMATTER));
		
		System.out.println("7. Click on \"Book ticket\" button");
		bookTicketPage.submit();
		String verifyString2 = "VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)";
		String expectedResult = "Ticket booked successfully!";
		String actualResult = bookTicketPage.getBookTicketMessage();
		String actualDepartStation = bookTicketPage.getTextFieldBookedTicket("Depart Station");
		String actualDepartDate = bookTicketPage.getTextFieldBookedTicket("Depart Date");
		String actualArriveStation = bookTicketPage.getTextFieldBookedTicket("Arrive Station");
		String actualSeatType = bookTicketPage.getTextFieldBookedTicket("Seat Type");
		String actualAmount = bookTicketPage.getTextFieldBookedTicket("Amount");
		
		Assert.assertEquals(actualResult, expectedResult,verifyString2);
		Assert.assertEquals(actualDepartDate, departDate, verifyString2);
		Assert.assertEquals(actualDepartStation, myTicket.getDepartFrom(),verifyString2);
		Assert.assertEquals(actualArriveStation, myTicket.getArriveAt(),verifyString2);
		Assert.assertEquals(actualSeatType, myTicket.getSeatType(),verifyString2);
		Assert.assertEquals(actualAmount, myTicket.getTicketAmount(),verifyString2);		
	}
}
