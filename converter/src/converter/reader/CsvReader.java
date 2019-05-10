package converter.reader;

import converter.storage.*;
import converter.utils.FileFormat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class CsvReader implements GlobalReader {
	@FileFormat(supportedExtention = "csv")
	public String fileName;
	public CsvReader(String fileName) {
		this.fileName = fileName;
	}

	public static void printStringTable(String[] stringTable) {
		for(int i =0;i< stringTable.length; i++) {
			System.out.print(stringTable[i] + " - ");
		}
		System.out.print("\n");

	}
	
	public static String[] parseLine(String line) {
		if(line.length() > 0) {
			int start = 0;
			int end = 0;

			if(line.charAt(0) == '\"' && line.indexOf("\",", start) != -1) {
				end =  line.indexOf("\",", start);
			}else {
				end = line.indexOf(",", start);
			}

			ArrayList<String> stringList = new ArrayList<String>();
			do {
				if(end == -1) {
					stringList.add(line.substring(start, line.length()));
				}else {
					stringList.add(line.substring(start, end));
					start = end+1;
					if(line.charAt(start) == '\"' && line.indexOf("\",", start) != -1) {
						end =  line.indexOf("\",", start)+1;
					}else {
						end = line.indexOf(",", start);
					}
				}
			}while(end != -1);
			if(end == -1) {
				stringList.add(line.substring(start, line.length()));
			}
			String[] splitedString = new String[stringList.size()];
			splitedString = stringList.toArray(splitedString);
			for(int i =0;i< splitedString.length; i++) {
				splitedString[i] = splitedString[i].replace("\"\"", "\"");
				splitedString[i] = splitedString[i].trim();

			}
			return splitedString;
		}
		return null;
	}
	
	@Override
	public StorageDataBase readFile() {
		StorageDataBase storage = new StorageDataBase();
		String[] tagName;
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		   // StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    String[] parsedLine = parseLine(line);
		    //printStringTable(parsedLine);
		    tagName = parsedLine;
        	StorageContainer superContainer = new StorageContainer(true);

		    while (line != null) {
		        line = br.readLine();
		        if(line == null) {
		        	break;
		        }
		        parsedLine = parseLine(line);
			    //printStringTable(parsedLine);
	        	StorageContainer container = new StorageContainer(false);

		        for(int i = 0; i < parsedLine.length;i++) {
		        	if(!parsedLine[i].equals("-")) {
		        		StorageValue<String> newValue = new StorageValue<String>(tagName[i],parsedLine[i]);
		        		System.out.println(newValue.elementTag + " " + newValue.getValue());
		        		container.addNewElement(newValue);
		        	}
		        }
		        //System.out.println("line : " + line);
        		superContainer.addNewElement(container);
		    }
	        storage.addNewData(superContainer);

		}catch(Exception ex) {
			System.out.println("error : " + ex.getMessage() + ex.toString());
			for(int i = 0; i < ex.getStackTrace().length; i++) {
				System.out.println(ex.getStackTrace()[i].getClassName() + " - line " +ex.getStackTrace()[i].getLineNumber() );
			}
		}
		//System.out.println("fin");
		return storage;
	}

}
