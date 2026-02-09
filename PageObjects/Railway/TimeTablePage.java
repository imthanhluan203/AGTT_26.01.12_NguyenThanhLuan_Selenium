package Railway;
import org.openqa.selenium.By;
import Common.Utilities;
import Constant.TimeTable;

public class TimeTablePage extends GeneralPage{
	private final String _linkCellInTable = "//table//td[text()='%s']//following-sibling::td[text()='%s']//following-sibling::td//a[text()='%s']";
	
	@SuppressWarnings("unchecked")
	public <T extends GeneralPage> T timeTableAction(String from,String to,TimeTable action) {
		String xpath = String.format(_linkCellInTable, from,to,action.getValue());
		Utilities.click(By.xpath(xpath));
		if(action == TimeTable.CHECK_PRICE) {
			return (T) new TicketPricePage();
		}
		return (T) new BookTicketPage();
	}
	
}
