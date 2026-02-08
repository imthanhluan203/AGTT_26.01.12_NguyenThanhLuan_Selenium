package Railway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import Common.Utilities;
import Constant.BookTicketFormField;
import Constant.Constant;
import DataObjects.Ticket;

public class BookTicketPage extends GeneralPage {
	
	private final String _dynamicXpathFormField = "//select[@name='%s']";
	private final By _btnSubmit = By.xpath("//input[@type='submit']");
	private final By _txtMessage = By.xpath("//h1");
	private final By _tableBookedTicket = By.xpath("//table//tr//td");
	
	public void select(BookTicketFormField field, String value) {
        String xpath = String.format(_dynamicXpathFormField, field.getValue());
        Select mySelect = new Select(Constant.WEBDRIVER.findElement(By.xpath(xpath)));
        mySelect.selectByVisibleText(value);
    }
	
	public String select(BookTicketFormField field, int duration) {
        if(field == BookTicketFormField.DEPART_DATE) {
        	String xpath = String.format(_dynamicXpathFormField, field.getValue());
            Select mySelect = new Select(Constant.WEBDRIVER.findElement(By.xpath(xpath)));
            String currentSelect = mySelect.getFirstSelectedOption().getText();
    		long julianDay = Utilities.parseDateToJulian(currentSelect);
    		String afterSelect = Utilities.parseJulianToDate(julianDay + duration);
    		mySelect.selectByContainsVisibleText(afterSelect);
    		return afterSelect;
        }else {
        	return "";
        }
    }
	
	public String select(BookTicketFormField field,String yourDay, int duration) {
		if(field == BookTicketFormField.DEPART_DATE) {
			String xpath = String.format(_dynamicXpathFormField, field.getValue());
	        Select mySelect = new Select(Constant.WEBDRIVER.findElement(By.xpath(xpath)));
	        long julianDay = Utilities.parseDateToJulian(yourDay);
			String afterSelect = Utilities.parseJulianToDate(julianDay + duration);
			mySelect.selectByContainsVisibleText(afterSelect);
			return afterSelect;
		}
		else {
			return "";
        }
	}
	
	public void submit() {
		Utilities.click(_btnSubmit);
	}
	
	public String getBookTicketMessage() {
		return Utilities.getTextElement(_txtMessage);
	}
	
	public String bookTicket(Ticket myTicket,int duration,String... yourday) {
		String departDate = "";
		if(yourday.length > 0) {
			departDate = this.select(BookTicketFormField.DEPART_DATE, yourday[0], duration);
		}else {
			departDate = this.select(BookTicketFormField.DEPART_DATE, duration);
		}
		this.select(BookTicketFormField.DEPART_FROM, myTicket.getDepartFrom());
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.select(BookTicketFormField.ARRIVE_AT, myTicket.getArriveAt());
		this.select(BookTicketFormField.SEAT_TYPE, myTicket.getSeatType());
		this.select(BookTicketFormField.TICKET_AMOUNT, myTicket.getTicketAmount());
		return departDate;
	}
	
	public Map<BookTicketFormField,String> getTicketInformation() {
		String[] header = new String[] {"Depart Station",	"Arrive Station",	"Seat Type",	"Depart Date",	"Book Date",	"Expired Date",	"Amount",	"Total Price"};
		Map<BookTicketFormField,String> bookedTicketData = new HashMap<BookTicketFormField, String>();
		
		List<WebElement> listElements = Constant.WEBDRIVER.findElements(_tableBookedTicket);
		for(int i=0; i<listElements.size(); i++) {
			if(header[i].equals("Depart Station")) {
				bookedTicketData.put(BookTicketFormField.DEPART_FROM, listElements.get(i).getText());
			}
			if(header[i].equals("Arrive Station")) {
				bookedTicketData.put(BookTicketFormField.ARRIVE_AT, listElements.get(i).getText());
			}
			if(header[i].equals("Seat Type")) {
				bookedTicketData.put(BookTicketFormField.SEAT_TYPE, listElements.get(i).getText());
			}
			if(header[i].equals("Depart Date")) {
				bookedTicketData.put(BookTicketFormField.DEPART_DATE, listElements.get(i).getText());
			}
			if(header[i].equals("Amount")) {
				bookedTicketData.put(BookTicketFormField.TICKET_AMOUNT, listElements.get(i).getText());
			}
		}
		return bookedTicketData;
	}
}
