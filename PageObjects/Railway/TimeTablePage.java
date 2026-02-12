package Railway;
import org.openqa.selenium.By;
import Common.Utilities;
import Constant.City;
import Constant.Constant;
import Constant.Tab;
import Constant.TableHeader;

public class TimeTablePage extends GeneralPage{
	private String dynamicXpathTableCell = "((//table//tbody//tr)[%d]//td)[count(//table//thead//tr//th[text()= '%s']//preceding-sibling::th) + 1]";
	
	@SuppressWarnings("unchecked")
	public <T extends GeneralPage> T timeTableAction(City from,City to,TableHeader header) {
		int numTimeTableRows = Constant.WEBDRIVER.findElements(By.xpath("//table//tbody//tr")).size();
		int indexNumber = -1;
		for(int i=1; i<=numTimeTableRows; i++) {
			String depart = Utilities.getTextElement(By.xpath(String.format(dynamicXpathTableCell, i, TableHeader.DEPART_STATION.getValue())));
			String arrive  = Utilities.getTextElement(By.xpath(String.format(dynamicXpathTableCell, i, TableHeader.ARRIVE_STATION.getValue())));
			if(depart.equals(from.getValue()) && arrive.equals(to.getValue())) {
				indexNumber = i;
				break;
			}
		}
		String xpathCheckPrice = dynamicXpathTableCell + "//a";
		String xpath = String.format(xpathCheckPrice,indexNumber, header.getValue());
		Utilities.click(By.xpath(xpath));
		if(header == TableHeader.CHECK_PRICE) {
			Utilities.waitForTabFullyLoad(Tab.TICKETPRICE);
			return (T) new TicketPricePage();
		}
		return (T) new BookTicketPage();
	}
	
}
