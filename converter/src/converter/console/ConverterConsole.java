package converter.console;

import java.io.File;

import converter.reader.*;
import converter.writer.*;

import converter.storage.*;

public class ConverterConsole {

	public static void main(String[] args) {
		File fileToRead = new File("test.csv");
		CsvReader cr = new CsvReader();
		StorageDataBase dataBase =  cr.readFile(fileToRead);
		XmlWriter xmlWrite = new XmlWriter();
		xmlWrite.printSpecificFormat(dataBase);
	}

}
