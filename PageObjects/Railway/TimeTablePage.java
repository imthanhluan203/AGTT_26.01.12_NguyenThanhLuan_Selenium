package Railway;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;
import Constant.TimeTable;

public class TimeTablePage extends GeneralPage{
	private final String _rowCell = "((//table//tr)[%d]//td)[%d]";
	
	public void timeTableAction(String from, String to,TimeTable action) {
		String[] header = new String[] {"No.", "Depart Station", "Arrive Station", "Depart Time", "Arrive Time", "Check Price", "Book ticket"};
		
		List<WebElement> rows = Constant.WEBDRIVER.findElements(By.xpath("//table//tr"));
		List<WebElement> cols = Constant.WEBDRIVER.findElements(By.xpath("(//table//tr)[1]//th"));
		int rowSize = rows.size();
		int colSize = cols.size();
		for(int i=2;i<=rowSize;i++) {
			Boolean flagFrom = false;
			Boolean flagTo = false;
			String realFrom = "";
			String realTo = "";
			for (int j=1;j<=colSize;j++) {
			
				if(header[j-1].equals("Depart Station")) {
					realFrom = Constant.WEBDRIVER.findElement(By.xpath(String.format(_rowCell, i,j))).getText();
					if(realFrom.equals(from)) {
						flagFrom = true;
					}
				}
				if(header[j-1].equals("Arrive Station")) {
					realTo = Constant.WEBDRIVER.findElement(By.xpath(String.format(_rowCell, i,j))).getText();
					if(realTo.equals(to)) {
						flagTo = true;
					}
				}
				
				if(flagFrom && flagTo) {
					System.out.printf("%s : %s %n",realFrom,realTo);
					break;
				}
			}
		}
	}
}
