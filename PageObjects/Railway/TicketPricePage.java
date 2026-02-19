package Railway;

import Common.JsonReader;
import Common.Utilities;
import Enum.PageTitle;
import Enum.SeatType;

public class TicketPricePage extends GeneralPage {
	
	public String getPrice(SeatType type) {
		return Utilities.getTextElement(JsonReader.getLocator(PageTitle.TICKET_PRICE, "dynamicXpathPriceBySeat", type));
	}
	
	public String getTableName() {
		return Utilities.getTextElement(JsonReader.getLocator(PageTitle.TICKET_PRICE, "txtTableName"));
	}
	
}
