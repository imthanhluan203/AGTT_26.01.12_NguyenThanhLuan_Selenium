package Railway;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import Common.Utilities;
import Constant.Constant;
import Constant.PageTitle;
import Constant.Tab;

public abstract class GeneralPage {	
	private String tabXpath = "//a/span[contains(text(),'%s')]";
	
	
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
    
	public <T extends GeneralPage> T gotoPage(Tab tab,PageTitle page ,Class<T> pageClass)  {
		
		By tabPara = By.xpath(String.format(tabXpath, tab.getValue()));
		Utilities.click(tabPara);
		Utilities.waitForPageFullyLoad(page);
		try {
			return pageClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
