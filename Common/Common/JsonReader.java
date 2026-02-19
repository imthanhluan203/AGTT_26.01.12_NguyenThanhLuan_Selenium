package Common;

import java.io.File;

import org.openqa.selenium.By;

import Enum.PageTitle;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class JsonReader {
	private static JsonNode ROOTNODE;
	static {
		try {
            ObjectMapper mapper = new ObjectMapper();
            ROOTNODE = mapper.readTree(new File("DataProjects/DataObjects/locators.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	public static By getLocator(PageTitle pageName,String typeOfLocator, Object... values) {
		JsonNode element = ROOTNODE.path(pageName.getValue()).path(typeOfLocator);
		if(values.length == 0) {
			return By.xpath(element.asString());
		}
		return By.xpath(String.format(element.asString(), values));
	}
	
}
