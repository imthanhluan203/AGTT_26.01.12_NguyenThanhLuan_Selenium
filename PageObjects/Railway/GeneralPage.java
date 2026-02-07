package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import Constant.Tab;

public abstract class GeneralPage {	
	private final String tabXpath = "//a/span[contains(text(),'%s')]";
	
	
    public WebElement getTab(String tabName) {
        return Constant.WEBDRIVER.findElement(By.xpath(String.format(tabXpath, tabName)));
    }
      
    public Boolean checkTabPageExist(Tab tabName) {
    	try {
    		Constant.WEBDRIVER.findElement(By.xpath(String.format(tabXpath, tabName.getValue())));
    		return true;
		} catch (Exception e) {
			return false;
		}
    }
    
	public <T extends GeneralPage> T gotoPage(Tab tab,Class<T> pageClass)  {
		
		By tabPara = By.xpath(String.format(tabXpath, tab.getValue()));
		Utilities.click(tabPara);
		try {
			return pageClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
