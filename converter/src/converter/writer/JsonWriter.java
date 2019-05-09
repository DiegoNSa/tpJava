package converter.writer;

import converter.storage.StorageContainer;
import converter.storage.StorageDataBase;
import converter.storage.StorageElement;
import converter.storage.StorageValue;

public class JsonWriter implements GlobalWriter {
	private static String getElementString(StorageElement el) {
		return getElementString(el,"");
	}

		
	private static String getElementString(StorageElement el,String preString) {
		if(el instanceof StorageContainer) {

			return getElementString((StorageContainer) el,preString); 
		}else {
			return getElementString((StorageValue<Object>) el,preString); 
		}
	}

	private static String getElementString(StorageValue<Object> val) {
		return getElementString(val,"");
	}
	
	private static String getElementString(StorageValue<Object> val, String preString) {
		String resString = "";
		String tabulation = "";

		for(int i =0; i < val.level;i++) {
			tabulation += "  ";
		}
		String reducedTag = val.elementTag.replace('\"', ' ');
		reducedTag = reducedTag.trim();

		resString += tabulation+ preString + "\"" + reducedTag + "\":";
		resString += val.getValue().toString() + "\n";
		return resString;
	}
	
	private static String getElementString(StorageContainer container) {
		return getElementString(container,"");
	}

		
	private static String getElementString(StorageContainer container, String preString) {
		String resString = "";
		String tabulation = "";

		for(int i =0; i < container.level;i++) {
			tabulation += "  ";
		}
		
		if(container.elementTag != null) {
			String reducedTag = container.elementTag.replace('\"', ' ');
			reducedTag = reducedTag.trim();

			if(container.isEnum) {
				resString += tabulation + preString +"\"" + reducedTag + "\":[\n";
			}else {
				resString += tabulation + preString + "\"" + reducedTag + "\":{\n";
			}
		}else {
			if(container.isEnum) {
				resString += tabulation + preString  + "[\n";
			}else {
				resString += tabulation + preString + "{\n";
			}
		}
		
		
		
		
		for(StorageElement prop : container.propertyList) {
				resString += getElementString(prop);
		}
		if(container.isEnum) {
			resString += tabulation + "]\n";
		}else {
			resString += tabulation + preString + "}\n";
		}
		return resString;
	}
	
	@Override
	public String getSpecificFormat(StorageDataBase data) {
		String resultString = ""; 
		for(StorageElement container : data.datas) {
			resultString += getElementString(container);
		}
		return resultString;
	}

	
	
	@Override
	public void printSpecificFormat(StorageDataBase data) {
		System.out.println(getSpecificFormat(data));
	}
}
