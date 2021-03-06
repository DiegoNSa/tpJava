package converter.writer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import converter.storage.StorageContainer;
import converter.storage.StorageDataBase;
import converter.storage.StorageElement;
import converter.storage.StorageValue;
import converter.utils.FileFormat;

public class JsonWriter implements GlobalWriter {
	@FileFormat(supportedExtention = "json")
	public String fileName;
	public JsonWriter(String fileName) {
		this.fileName = fileName;
	}
	
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
		resString += "\"" + val.getValue().toString() + "\",\n";
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
			resString += tabulation + preString + "},\n";
		}
		return resString;
	}
	
	@Override
	public String getSpecificFormat(StorageDataBase data) {
		String resultString = "{\n"; 
		for(StorageElement container : data.datas) {
			resultString += getElementString(container);
		}
		resultString += "}";
		return resultString;
	}

	
	
	@Override
	public void printSpecificFormat(StorageDataBase data) {
		System.out.println(getSpecificFormat(data));
	}
	
	public void writeSpecificFormat(StorageDataBase data){
		System.out.println("writing in " + fileName);
		try(PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {
			writer.println(getSpecificFormat(data));
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}

}
