package Railway;

import Common.JsonReader;
import Common.Utilities;

import Constant.Constant;
import Enum.PageTitle;
import Enum.Tab;

public abstract class GeneralPage {	
	
    public Boolean checkTabPageExist(Tab tabName) {
    	try {
    		Constant.WEBDRIVER.findElement(JsonReader.getLocator(PageTitle.GENERAL, "dynamicTabXpath", tabName.getValue()));
    		return true;
		} catch (Exception e) {
			return false;
		}
    }
    
	public <T extends GeneralPage> T gotoPage(Tab tab,PageTitle page ,Class<T> pageClass)  {
		Utilities.click(JsonReader.getLocator(PageTitle.GENERAL, "dynamicTabXpath", tab.getValue()));
		Utilities.waitForPageFullyLoad(page);
		try {
			return pageClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
