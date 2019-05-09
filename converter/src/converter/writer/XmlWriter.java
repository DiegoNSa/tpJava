package converter.writer;

import converter.storage.*;

public class XmlWriter implements GlobalWriter {

	private static String getElementString(StorageElement el) {
		if(el instanceof StorageContainer) {
			getElementString((StorageContainer) el); 
		}else {
			return getElementString((StorageValue<Object>) el); 
		}
		return "";
	}

	
	private static String getElementString(StorageValue<Object> val) {
		String resString = "";
		String tabulation = "";

		for(int i =0; i < val.level;i++) {
			tabulation += "\t";
		}
		resString += tabulation + "<" + val.elementTag + ">";
		resString += val.getValue().toString();

		resString += "<" + val.elementTag + ">\n";

		return resString;
	}
	
	private static String getElementString(StorageContainer container) {
		String resString = "";

		for(StorageElement prop : container.propertyList) {
			String tabulation = "";
			for(int i =0; i < prop.level;i++) {
				tabulation += "\t";
			}
			resString += tabulation + "<" + prop.elementTag + ">\n";
			resString += getElementString(prop);
			resString += tabulation + "</" + prop.elementTag + ">\n";
		}
		return resString;
	}
	
	@Override
	public String getSpecificFormat(StoragaDataBase data) {
		String resultString = ""; 
		for(StorageElement element : data.datas) {
			resultString += getElementString(element);
		}
		return resultString;
	}

	
	
	@Override
	public void printSpecificFormat(StoragaDataBase data) {
		System.out.println(getSpecificFormat(data));
	}

}
