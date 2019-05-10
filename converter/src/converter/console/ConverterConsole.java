package converter.console;

import java.io.File;

import converter.reader.*;
import converter.writer.*;

import converter.storage.*;

public class ConverterConsole {

	public static void main(String[] args) {
		/*
		File fileToRead = new File("test.csv");
		CsvReader cr = new CsvReader();
		StorageDataBase dataBase =  cr.readFile(fileToRead);
		JsonWriter ymlWrite = new JsonWriter();
		ymlWrite.printSpecificFormat(dataBase);
		*/
		
		/*File fileToRead = new File("test.json");
		JsonReader cr = new JsonReader();
		StorageDataBase dataBase =  cr.readFile(fileToRead);
		
		YamlWriter ymlWrite = new YamlWriter();
		ymlWrite.printSpecificFormat(dataBase);*/
		File fileToRead = new File("test.xml");
		XmlReader cr = new XmlReader();
		StorageDataBase dataBase =  cr.readFile(fileToRead);
		JsonWriter ymlWrite = new JsonWriter();
		ymlWrite.printSpecificFormat(dataBase);

	}

}
