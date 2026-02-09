package Railway;

import org.openqa.selenium.By;

import Common.Utilities;
import Constant.SeatType;

public class TicketPricePage extends GeneralPage {
	private final By _txtTableName = By.xpath("//table[@class='MyTable MedTable']//tr[@class='TableSmallHeader']//th");
	
	private final String price = "(//tr[td[text()='%s']]//following-sibling::tr//td)[count(//tr[td[text()='%s']]//td[text()='%s']//preceding-sibling::td) + 1]";
	
	public String getPrice(SeatType type) {
		String xpath = String.format(price, type,type,type);
		return Utilities.getTextElement(By.xpath(xpath));
	}
	
	public String getTableName() {
		return Utilities.getTextElement(_txtTableName);
	}
}
