package converter.service;

import java.io.File;

import converter.reader.*;
import converter.utils.Reformator;
import converter.writer.*;

public class ConvertingService extends GlobalService{

	public static String getExtension(String fileName) {
		int index = fileName.indexOf(".");
		while(fileName.indexOf(".",index+1)!=-1) {
			index = fileName.indexOf(".",index+1);
		}
		if(index == -1) {
			return null;
		}
		return fileName.substring(index+1, fileName.length());
	}
	
	public static GlobalReader getReaderForFile(String fileName) {
		String fileExt = getExtension(fileName);
		//System.out.println(fileExt);
		switch(fileExt) {
		case "csv":
			return new CsvReader(fileName); 
		case "xml":
			return new XmlReader(fileName); 
		case "json":
			return new JsonReader(fileName); 
		default:
			return null;
		}
	}
	
	public static GlobalWriter getWriterForFile(String fileName) {
		String fileExt = getExtension(fileName);
		switch(fileExt) {
		case "json":
			return new JsonWriter(fileName); 
		case "xml":
			return new XmlWriter(fileName); 
		case "yml":
			return new YamlWriter(fileName); 
		default:
			return null;
		}	
	}
	
	public static GlobalWriter getWriterForFile(String fileName,String option) {
		switch(option) {
		case "json":
			return new JsonWriter(fileName); 
		case "xml":
			return new XmlWriter(fileName); 
		case "yml":
			return new YamlWriter(fileName); 
		default:
			return null;
		}	
	}
	
	@Override
	public void executerUC(String[] parameters) {
		if(parameters[0].equals("-o")) {
			GlobalReader reader = getReaderForFile(parameters[2]);
			GlobalWriter writer = getWriterForFile(parameters[3],parameters[1]);
			Reformator.reformat(writer);

			
			File fileToRead = new File(parameters[2]);
			writer.writeSpecificFormat(reader.readFile());
		}else {
			GlobalReader reader = getReaderForFile(parameters[0]);
			GlobalWriter writer = getWriterForFile(parameters[1]);
			Reformator.reformat(writer);

			File fileToRead = new File(parameters[0]);
			writer.writeSpecificFormat(reader.readFile());
		}
	}

}
