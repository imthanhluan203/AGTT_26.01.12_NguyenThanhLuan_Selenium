package Railway;

import Common.JsonReader;
import Common.Utilities;
import Constant.Constant;
import Enum.PageTitle;
import Enum.TableHeader;

public class TicketPage extends GeneralPage {
	
	public String getCellValue(String noOfTicket, TableHeader typeOfData) {
		Utilities.waitForElementLocated(JsonReader.getLocator(PageTitle.MY_TICKET, "lblTicketHeader"));
		return Utilities.getTextElement(JsonReader.getLocator(PageTitle.MY_TICKET, "dynamicXpathCellValueOfTable", noOfTicket, typeOfData.getValue()));
	}
	
	public void clickCancelButton(String noOfTicket) {
		Utilities.click(JsonReader.getLocator(PageTitle.MY_TICKET, "dynamicXpathCancel", noOfTicket));
	}
	public void clickOke() {
		Constant.WEBDRIVER.switchTo().alert().accept();
	}
	
	
}
