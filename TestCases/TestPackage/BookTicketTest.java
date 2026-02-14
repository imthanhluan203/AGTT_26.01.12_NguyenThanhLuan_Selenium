package TestPackage;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.City;
import Constant.Constant;
import Constant.PageTitle;
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
	
	@Test(description = "User can book 1 ticket at a time",enabled = true)
	public void TC12() {
		
		String expectedResult = "Ticket booked successfully!";
		
		System.out.println("Pre-condition: an actived account is existing.");
		myUserInfo = new UserInfo(Utilities.generateRandomString(15) + Constant.MAIL_TYPE, Constant.PASSWORD);
		register(myUserInfo);
		Ticket myTicket = new Ticket(City.NHATRANG, City.HUE, SeatType.SBC, "1",2);
		
		System.out.println("1. Navigate to QA Railway Website.");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("2. Login with a valid account.");
		LoginPage loginPage = homePage.gotoPage(Tab.LOGIN,PageTitle.LOGIN, LoginPage.class);
		homePage = loginPage.login(myUserInfo);
		
		System.out.println("3. Click on \"Book ticket\" tab.");
		BookTicketPage bookTicketPage = homePage.gotoPage(Tab.BOOKTICKET,PageTitle.BOOK_TICKET, BookTicketPage.class);
		
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
		String actualDepartStation = bookTicketPage.getTextFieldBookedTicket(TableHeader.DEPART_STATION);
		String actualDepartDate = bookTicketPage.getTextFieldBookedTicket(TableHeader.DEPART_DATE);
		String actualArriveStation = bookTicketPage.getTextFieldBookedTicket(TableHeader.ARRIVE_STATION);
		String actualSeatType = bookTicketPage.getTextFieldBookedTicket(TableHeader.SEAT_TYPE);
		String actualAmount = bookTicketPage.getTextFieldBookedTicket(TableHeader.AMOUNT);
		
		Assert.assertEquals(actualResult, expectedResult,"VP: Message \"Ticket booked successfully!\" displays.");
		Assert.assertEquals(actualDepartDate, departDate, "VP: Ticket information display correctly Depart Date");
		Assert.assertEquals(actualDepartStation, myTicket.getDepartFrom(),"VP: Ticket information display correctly Depart Station");
		Assert.assertEquals(actualArriveStation, myTicket.getArriveAt(),"VP: Ticket information display correctly Arrive Station");
		Assert.assertEquals(actualSeatType, myTicket.getSeatType(),"VP: Ticket information display correctly Seat Type");
		Assert.assertEquals(actualAmount, myTicket.getTicketAmount(),"VP: Ticket information display correctly Amount");		
	}
	
	@Test(description = "User can book many tickets at a time", enabled = true)
	public void TC13() {
		String expectedResult = "Ticket booked successfully!";
		System.out.println("Pre-condition: an actived account is existing");
		myUserInfo = new UserInfo(Utilities.generateRandomString(15) + Constant.MAIL_TYPE, Constant.PASSWORD);
		register(myUserInfo);
		Ticket myTicket = new Ticket(City.NHATRANG, City.SAIGON, SeatType.SBC, "5",25);
		
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
		String actualDepartStation = bookTicketPage.getTextFieldBookedTicket(TableHeader.DEPART_STATION);
		String actualDepartDate = bookTicketPage.getTextFieldBookedTicket(TableHeader.DEPART_DATE);
		String actualArriveStation = bookTicketPage.getTextFieldBookedTicket(TableHeader.ARRIVE_STATION);
		String actualSeatType = bookTicketPage.getTextFieldBookedTicket(TableHeader.SEAT_TYPE);
		String actualAmount = bookTicketPage.getTextFieldBookedTicket(TableHeader.AMOUNT);
		
		Assert.assertEquals(actualResult, expectedResult,"VP: Message \"Ticket booked successfully!\" displays.");
		Assert.assertEquals(actualDepartDate, departDate, "VP: Ticket information display correctly Depart Date");
		Assert.assertEquals(actualDepartStation, myTicket.getDepartFrom(),"VP: Ticket information display correctly Depart Station");
		Assert.assertEquals(actualArriveStation, myTicket.getArriveAt(),"VP: Ticket information display correctly Arrive Station");
		Assert.assertEquals(actualSeatType, myTicket.getSeatType(),"VP: Ticket information display correctly Seat Type");
		Assert.assertEquals(actualAmount, myTicket.getTicketAmount(),"VP: Ticket information display correctly Amount");				
	}
	
	@Test(description = "User can check price of ticket from Timetable",enabled = true)
	public void TC14() {
		System.out.println("Pre-condition: an actived account is existing");
		myUserInfo = new UserInfo(Utilities.generateRandomString(15) + Constant.MAIL_TYPE, Constant.PASSWORD);
		register(myUserInfo);
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
		TicketPricePage ticketPrice = timeTablePage.timeTableAction(City.DANANG,City.SAIGON,TableHeader.CHECK_PRICE);
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
		
		Assert.assertEquals(actualPage, expectedPage,"VP: \"Ticket Price\" page is loaded.");
		Assert.assertEquals(expectedTableName, actualTableName,"VP: Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\"");
		Assert.assertEquals(actualPriceHS,  "310000", "VP: Price for each seat displays correctly HS = 310000");
		Assert.assertEquals(actualPriceSS,  "335000", "VP: Price for each seat displays correctly SS = 335000");
		Assert.assertEquals(actualPriceSSC, "360000","VP: Price for each seat displays correctly SSC = 360000");
		Assert.assertEquals(actualPriceHB,  "410000", "VP: Price for each seat displays correctly HB = 410000");
		Assert.assertEquals(actualPriceSB,  "460000", "VP: Price for each seat displays correctly SB = 460000");
		Assert.assertEquals(actualPriceSBC, "510000","VP: Price for each seat displays correctly SBC = 510000");		
		
	}
	
	@Test(description = "User can book ticket from Timetable", enabled = true)
	public void TC15() {
		System.out.println("Pre-condition: an actived account is existing");
		myUserInfo = new UserInfo(Utilities.generateRandomString(15) + Constant.MAIL_TYPE, Constant.PASSWORD);
		register(myUserInfo);
		Ticket myTicket = new Ticket(City.QUANGNGAI, City.HUE, SeatType.HS, "5",1);
		
		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("2. Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(Tab.LOGIN, LoginPage.class);
		homePage = loginPage.login(myUserInfo);
		
		System.out.println("3. Click on \"Timetable\" tab");
		TimeTablePage timeTablePage = homePage.gotoPage(Tab.TIMETABLE, TimeTablePage.class);
		
		System.out.println("4. Click on book ticket of route \"Quảng Ngãi\" to \"Huế\"");
		BookTicketPage bookTicketPage = timeTablePage.timeTableAction(City.QUANGNGAI, City.HUE, TableHeader.BOOK_TICKET);
		//Book ticket form is shown with the corrected "depart from" and "Arrive at"
		String verifyString1 = "VP: Book ticket form is shown with the corrected \"depart from\" and \"Arrive at\"";
		String departFrom = bookTicketPage.getDepartionText();
		String arriveAt = bookTicketPage.getArriveAtText();
		System.out.println(verifyString1);
		Assert.assertEquals(departFrom, "Quảng Ngãi",verifyString1);
		Assert.assertEquals(arriveAt, "Huế", verifyString1);		
				
		System.out.println("5. Select Depart date = tomorrow");
		System.out.println("6. Select Ticket amount = 5");
		String departDate = bookTicketPage.bookTicket(myTicket, LocalDate.now().format(Constant.FORMATTER));
		
		System.out.println("7. Click on \"Book ticket\" button");
		bookTicketPage.submit();
		String verifyString2 = "VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)";
	    System.out.println(verifyString2);
		
		String expectedResult = "Ticket booked successfully!";
		String actualResult = bookTicketPage.getBookTicketMessage();
		String actualDepartStation = bookTicketPage.getTextFieldBookedTicket(TableHeader.DEPART_STATION);
		String actualDepartDate = bookTicketPage.getTextFieldBookedTicket(TableHeader.DEPART_DATE);
		String actualArriveStation = bookTicketPage.getTextFieldBookedTicket(TableHeader.ARRIVE_STATION);
		String actualSeatType = bookTicketPage.getTextFieldBookedTicket(TableHeader.SEAT_TYPE);
		String actualAmount = bookTicketPage.getTextFieldBookedTicket(TableHeader.AMOUNT);
		
		Assert.assertEquals(actualResult, expectedResult,"VP: Message \"Ticket booked successfully!\" displays.");
		Assert.assertEquals(actualDepartDate, departDate, "VP: Ticket information display correctly Depart Date");
		Assert.assertEquals(actualDepartStation, myTicket.getDepartFrom(),"VP: Ticket information display correctly Depart Station");
		Assert.assertEquals(actualArriveStation, myTicket.getArriveAt(),"VP: Ticket information display correctly Arrive Station");
		Assert.assertEquals(actualSeatType, myTicket.getSeatType(),"VP: Ticket information display correctly Seat Type");
		Assert.assertEquals(actualAmount, myTicket.getTicketAmount(),"VP: Ticket information display correctly Amount");		
	}
}
