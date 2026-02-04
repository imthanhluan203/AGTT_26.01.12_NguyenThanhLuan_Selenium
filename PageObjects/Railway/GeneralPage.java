package Railway;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public abstract class GeneralPage {	
	private final String tabXpath = "//a/span[contains(text(),'%s')]";
    public WebElement getTab(String tabName) {
        return Constant.WEBDRIVER.findElement(By.xpath(String.format(tabXpath, tabName)));
    }
	public <T extends GeneralPage> T gotoPage(String tabName,Class<T> pageClass) throws Exception {
		this.getTab(tabName).click();
		return pageClass.getDeclaredConstructor().newInstance();
	}
	
}
