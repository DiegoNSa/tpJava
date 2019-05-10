package converter.writer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import converter.storage.*;
import converter.utils.FileFormat;

public class XmlWriter implements GlobalWriter {
	@FileFormat(supportedExtention = "xml")
	public String fileName;
	public XmlWriter(String fileName) {
		this.fileName = fileName;
	}
	
	private static String getElementString(StorageElement el) {
		if(el instanceof StorageContainer) {
			//System.out.println("bloblbo");

			return getElementString((StorageContainer) el); 
		}else {
			//System.out.println("blabal");
			return getElementString((StorageValue<Object>) el); 
		}
	}

	
	private static String getElementString(StorageValue<Object> val) {
		String resString = "";
		String tabulation = "";

		for(int i =0; i < val.level;i++) {
			tabulation += "\t";
		}
		resString += tabulation + "<" + val.elementTag + ">";
		resString += val.getValue().toString();

		resString += "</" + val.elementTag + ">\n";

		return resString;
	}
	
	private static String getElementString(StorageContainer container) {
		String resString = "";
		String tabulation = "";

		for(int i =0; i < container.level;i++) {
			tabulation += "\t";
		}
		if(container.elementTag != null) {
			resString += tabulation + "<" + container.elementTag + ">\n";
		}else
		{
			resString += tabulation;
		}
		
		for(StorageElement prop : container.propertyList) {
			
			resString += getElementString(prop);
		}
		if(container.elementTag != null) {
			resString += tabulation + "</" + container.elementTag + ">\n";
		}else
		{
			resString += tabulation + "\n";
		}
		
		return resString;
	}
	
	@Override
	public String getSpecificFormat(StorageDataBase data) {
		String resultString = ""; 
		for(StorageElement container : data.datas) {
			//System.out.println("container : " + container.elementTag);
			resultString += getElementString(container);
		}
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
