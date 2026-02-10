package TestPackage;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.City;
import Constant.SeatType;
import Constant.Tab;
import DataObjects.Ticket;
import DataObjects.UserInfo;
import Railway.BookTicketPage;
import Railway.HomePage;
import Railway.LoginPage;
import Railway.TicketPage;

public class CancelBookingTest extends BaseTest {
	@Test(description = "Cancel booking",enabled = true)
	public void TC16() {
		System.out.println("Pre-condition: an actived account is existing");
		myUserInfo = new UserInfo("lonxskqi@sharklasers.com", "987654321");
		Ticket myTicket = new Ticket(City.QUANGNGAI, City.HUE, SeatType.HS, "1",1);
		
		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("2. Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(Tab.LOGIN, LoginPage.class);
		homePage = loginPage.login(myUserInfo);
		
		System.out.println("3. Book a ticket");
		BookTicketPage bookTicketPage = homePage.gotoPage(Tab.BOOKTICKET, BookTicketPage.class);
		bookTicketPage.bookTicket(myTicket);
		//bookTicketPage.submit();
		
		System.out.println("4. Click on \"My ticket\" tab");
		TicketPage ticketPage = bookTicketPage.gotoPage(Tab.MYTICKET, TicketPage.class);
		String noOfTicKetDelete = "1";
		
		String beforeCancelDepartStation = ticketPage.getCellValue(noOfTicKetDelete, "Depart Station");
		String beforeCancelDepartDate = ticketPage.getCellValue(noOfTicKetDelete, "Depart Date");
		String beforeCancelArriveStation = ticketPage.getCellValue(noOfTicKetDelete, "Arrive Station");
		String beforeCancelSeatType = ticketPage.getCellValue(noOfTicKetDelete, "Seat Type");
		String beforeCancelAmount = ticketPage.getCellValue(noOfTicKetDelete, "Amount"); 
		
		System.out.println("5. Click on \"Cancel\" button of ticket which user want to cancel.");
		ticketPage.clickCancelButton(noOfTicKetDelete);
		System.out.println("6. Click on \"OK\" button on Confirmation message \"Are you sure?\"");
		ticketPage.clickOke();
		String verifyString = "The canceled ticket is disappeared";
		String afterCancelDepartStation = ticketPage.getCellValue(noOfTicKetDelete, "Depart Station");
		String afterCancelDepartDate = ticketPage.getCellValue(noOfTicKetDelete, "Depart Date");
		String afterCancelArriveStation = ticketPage.getCellValue(noOfTicKetDelete, "Arrive Station");
		String afterCancelSeatType = ticketPage.getCellValue(noOfTicKetDelete, "Seat Type");
		String afterCancelAmount = ticketPage.getCellValue(noOfTicKetDelete, "Amount"); 
		
		Assert.assertNotEquals(beforeCancelDepartStation, afterCancelDepartStation, verifyString);
		Assert.assertNotEquals(beforeCancelDepartDate, afterCancelDepartDate, verifyString);
		Assert.assertNotEquals(beforeCancelArriveStation, afterCancelArriveStation, verifyString);
		Assert.assertNotEquals(beforeCancelSeatType, afterCancelSeatType, verifyString);
		Assert.assertNotEquals(beforeCancelAmount, afterCancelAmount, verifyString);
	}
}
