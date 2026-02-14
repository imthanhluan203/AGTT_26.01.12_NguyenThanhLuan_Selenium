package Railway;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.Utilities;
import Constant.BookTicketFormField;
import Constant.Constant;
import Constant.PageTitle;
import Constant.TableHeader;
import DataObjects.Ticket;

public class BookTicketPage extends GeneralPage {
	
	private String _dynamicXpathFormField = "//select[@name='%s']";
	private String _dynamicXpathBookTicketPage = "(//tr[th[text()='%s']]//following-sibling::tr//td)[count(//tr//th[text()='%s']//preceding-sibling::th) + 1]";

	private final By _btnSubmit = By.xpath("//input[@type='submit']");
	private final By _txtMessage = By.xpath("//h1[text()='Ticket booked successfully!']");
	private final By _txtDepartStation = By.xpath("//select[@name='DepartStation']/option[@selected]");
	private final By _txtArriveStation = By.xpath("//select[@name='ArriveStation']/option[@selected]");
	
	
	public String getDepartionText() {
		return Utilities.getTextElement(_txtDepartStation);
	}
	
	public String getArriveAtText() {
		return Utilities.getTextElement(_txtArriveStation);
	}
		
	public void select(BookTicketFormField field, String value) {
	    String xpath = String.format(_dynamicXpathFormField, field.getValue());
	    Utilities.scrollToElement(By.xpath(xpath));
	    new Select(Constant.WEBDRIVER.findElement(By.xpath(xpath)))
	        .selectByVisibleText(value);
	}
	
	public String select(BookTicketFormField field, int duration) {
        if(field != BookTicketFormField.DEPART_DATE) {
        	return "";
        }
    	String xpath = String.format(_dynamicXpathFormField, field.getValue());
    	Utilities.scrollToElement(By.xpath(xpath));
        Select mySelect = new Select(Constant.WEBDRIVER.findElement(By.xpath(xpath)));
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
		String xpath = String.format(_dynamicXpathFormField, field.getValue());
		Utilities.scrollToElement(By.xpath(xpath));
        Select mySelect = new Select(Constant.WEBDRIVER.findElement(By.xpath(xpath)));
        System.out.println(yourDay);
        long julianDay = Utilities.parseDateToJulian(yourDay);
		String afterSelect = Utilities.parseJulianToDate(julianDay + duration);
		System.out.println(afterSelect);
		mySelect.selectByContainsVisibleText(afterSelect);
		return afterSelect;
	}
	
	public void submit() {
		Utilities.click(_btnSubmit);
		Utilities.waitForPageFullyLoad(PageTitle.MY_TICKET);
	}
	
	public String getBookTicketMessage() {
		return Utilities.getTextElement(_txtMessage);
	}
	
	public String bookTicket(Ticket myTicket,String... yourday) {
		String departDate = "";
		if(yourday.length > 0) {
			departDate = this.select(BookTicketFormField.DEPART_DATE, yourday[0], myTicket.getDuration());
		}else {
			departDate = this.select(BookTicketFormField.DEPART_DATE, myTicket.getDuration());
		}
		String xpath = String.format("//select[@name='%s']",BookTicketFormField.ARRIVE_AT.getValue());
		
		if(!myTicket.getDepartFrom().isEmpty()) {
			WebElement element = Constant.WEBDRIVER.findElement(By.xpath(xpath));
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
		String myXpath = String.format(_dynamicXpathBookTicketPage, field.getValue(),field.getValue());
		return Utilities.getTextElement(By.xpath(myXpath));
	}
	
	
}
