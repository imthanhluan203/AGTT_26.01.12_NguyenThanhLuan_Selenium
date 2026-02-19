package Railway;
import org.openqa.selenium.By;

import Common.JsonReader;
import Common.Utilities;
import Constant.Constant;
import Enum.City;
import Enum.PageTitle;
import Enum.TableHeader;

public class TimeTablePage extends GeneralPage{
	
	@SuppressWarnings("unchecked")
	public <T extends GeneralPage> T timeTableAction(City from,City to,TableHeader header) {
		int numTimeTableRows = Constant.WEBDRIVER.findElements(By.xpath("//table//tbody//tr")).size();
		
		int indexNumber = -1;
		for(int i=1; i<=numTimeTableRows; i++) {
			String depart = Utilities.getTextElement(JsonReader.getLocator(PageTitle.TIME_TABLE, "dynamicXpathTableCell", i, TableHeader.DEPART_STATION.getValue()));
			String arrive = Utilities.getTextElement(JsonReader.getLocator(PageTitle.TIME_TABLE, "dynamicXpathTableCell", i, TableHeader.ARRIVE_STATION.getValue()));
			if(depart.equals(from.getValue()) && arrive.equals(to.getValue())) {
				indexNumber = i;
				break;
			}
		}
		
		Utilities.click(JsonReader.getLocator(PageTitle.TIME_TABLE, "dynamicXpathCheckOrBook", indexNumber,header.getValue()));
		if(header == TableHeader.CHECK_PRICE) {
			Utilities.waitForPageFullyLoad(PageTitle.TICKET_PRICE);
			return (T) new TicketPricePage();
		}
		Utilities.waitForPageFullyLoad(PageTitle.BOOK_TICKET);
		return (T) new BookTicketPage();
	}
	
}
