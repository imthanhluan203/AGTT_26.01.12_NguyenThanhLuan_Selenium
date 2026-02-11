package Railway;

import org.openqa.selenium.By;

import Common.Utilities;
import Constant.Constant;
import Constant.TableHeader;

public class TicketPage extends GeneralPage {
	private final String dynamicCancelXpath = "//tr[td[text()='%s']]//input[@value='Cancel']";
	private final String dynamicXpathIndexOfHeaderTable = "(//tr[td[text()='%s']])//td[count(//tr[th[text()='%s']]//following-sibling::th[text()='%s']//preceding-sibling::th) + 1]";
	
	public String getCellValue(String noOfTicket, TableHeader typeOfData) {
		String xpath = String.format(dynamicXpathIndexOfHeaderTable, noOfTicket, typeOfData.getValue(), typeOfData.getValue());
		return Utilities.getTextElement(By.xpath(xpath));
	}
	
	public void clickCancelButton(String noOfTicket) {
		By xpath = By.xpath(String.format(dynamicCancelXpath, noOfTicket));
		Utilities.click(xpath);
	}
	public void clickOke() {
		Constant.WEBDRIVER.switchTo().alert().accept();
	}
	
	
}
