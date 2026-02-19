package Railway;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.JsonReader;
import Common.Utilities;
import Constant.Constant;
import DataObjects.Ticket;
import Enum.BookTicketFormField;
import Enum.PageTitle;
import Enum.TableHeader;

public class BookTicketPage extends GeneralPage {
	
	public String getDepartionText() {
		return Utilities.getTextElement(JsonReader.getLocator(PageTitle.BOOK_TICKET, "txtDepartStation"));
	}
	
	public String getArriveAtText() {
		return Utilities.getTextElement(JsonReader.getLocator(PageTitle.BOOK_TICKET, "txtArriveStation"));
	}
		
	public void select(BookTicketFormField field, String value) {
	    By locator = JsonReader.getLocator(PageTitle.BOOK_TICKET, "dynamicXpathFormField", field.getValue());
	    Utilities.scrollToElement(locator);
	    new Select(Constant.WEBDRIVER.findElement(locator))
	        .selectByVisibleText(value);
	}
	
	public String select(BookTicketFormField field, int duration) {
        if(field != BookTicketFormField.DEPART_DATE) {
        	return "";
        }
        By locator = JsonReader.getLocator(PageTitle.BOOK_TICKET, "dynamicXpathFormField", field.getValue());
    	Utilities.scrollToElement(locator);
        Select mySelect = new Select(Constant.WEBDRIVER.findElement(locator));
        String currentSelect = mySelect.getFirstSelectedOption().getText();
		long julianDay = Utilities.parseDateToJulian(currentSelect);
		String afterSelect = Utilities.parseJulianToDate(julianDay + duration);
		mySelect.selectByContainsVisibleText(afterSelect);
		return afterSelect;        
    }
	
	public String select(BookTicketFormField field,String yourDay, int duration) {
		if(field != BookTicketFormField.DEPART_DATE) {
			return "";
		}
		By locator = JsonReader.getLocator(PageTitle.BOOK_TICKET, "dynamicXpathFormField", field.getValue());
		Utilities.scrollToElement(locator);
        Select mySelect = new Select(Constant.WEBDRIVER.findElement(locator));
        System.out.println(yourDay);
        long julianDay = Utilities.parseDateToJulian(yourDay);
		String afterSelect = Utilities.parseJulianToDate(julianDay + duration);
		System.out.println(afterSelect);
		mySelect.selectByContainsVisibleText(afterSelect);
		return afterSelect;
	}
	
	public void submit() {
		Utilities.click(JsonReader.getLocator(PageTitle.BOOK_TICKET, "btnSubmit"));
		Utilities.waitForPageFullyLoad(PageTitle.MY_TICKET);
	}
	
	public String getBookTicketMessage() {
		return Utilities.getTextElement(JsonReader.getLocator(PageTitle.BOOK_TICKET, "txtMessage"));
	}
	
	public String bookTicket(Ticket myTicket,String... yourday) {
		String departDate = "";
		if(yourday.length > 0) {
			departDate = this.select(BookTicketFormField.DEPART_DATE, yourday[0], myTicket.getDuration());
		}else {
			departDate = this.select(BookTicketFormField.DEPART_DATE, myTicket.getDuration());
		}
		By arriveLocator = JsonReader.getLocator(PageTitle.BOOK_TICKET, "dynamicXpathFormField", BookTicketFormField.ARRIVE_AT.getValue());
		
		if(!myTicket.getDepartFrom().isEmpty()) {
			WebElement element = Constant.WEBDRIVER.findElement(arriveLocator);
			this.select(BookTicketFormField.DEPART_FROM, myTicket.getDepartFrom());
			Utilities.waitForNewState(element);
		}
		
		if(!myTicket.getArriveAt().isEmpty()) {
			this.select(BookTicketFormField.ARRIVE_AT, myTicket.getArriveAt());
		}
		
		if(!myTicket.getSeatType().isEmpty()) {
			this.select(BookTicketFormField.SEAT_TYPE, myTicket.getSeatType());
		}
		
		if(!myTicket.getTicketAmount().isEmpty()) {
			this.select(BookTicketFormField.TICKET_AMOUNT, myTicket.getTicketAmount());
		}
		
		return departDate;
	}
	
	public String getTextFieldBookedTicket(TableHeader field) {
		return Utilities.getTextElement(JsonReader.getLocator(PageTitle.BOOK_TICKET, "dynamicXpathBookedTicketTable", field.getValue()));
	}
	
	
}
