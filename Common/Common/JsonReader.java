package Common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;

import Enum.PageTitle;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class JsonReader {
	private static JsonNode ROOTNODE;
	private static Map<String,Map<String,String>> LOCATORS;
	static {
		try {
			LOCATORS = new HashMap<>();
            ObjectMapper mapper = new ObjectMapper();
			LOCATORS = mapper.readValue(new File("DataProjects/DataObjects/locators.json"),Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	public static By getLocator(PageTitle pageName,String typeOfLocator, Object... values) {
		String element = LOCATORS.get(pageName.getValue()).get(typeOfLocator);
		if(values.length == 0) {
			return By.xpath(element);
		}
		return By.xpath(String.format(element, values));
	}
	
}
