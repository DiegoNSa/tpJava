package converter.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import converter.storage.StorageContainer;
import converter.storage.StorageDataBase;
import converter.storage.StorageElement;
import converter.storage.StorageValue;

public class JsonReader implements GlobalReader {

	
	public static boolean isDouble(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    int i = 0;
	    if (str.charAt(0) == '-') {
	        if (length == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for (; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9' || c == '.') {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static boolean isInteger(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    int i = 0;
	    if (str.charAt(0) == '-') {
	        if (length == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for (; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}
	
	public StorageDataBase parseFile(BufferedReader br) {
	    String line;
		StorageDataBase dataBase = new StorageDataBase();
		dataBase.addNewData(parseLine(br,null,null,dataBase));
		return dataBase;	   
	}
	
	public StorageContainer parseLine(BufferedReader br, String line,StorageContainer currentElement,StorageDataBase currentDataBase) {
		String currentLine = line;
		try {
			currentLine = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(currentLine != null) {
			System.out.println(currentLine);
			currentLine = currentLine.trim();
			String[] splitedLine = currentLine.split(":");
			for(int i = 0; i < splitedLine.length;i++) {
				//splitedLine[i]=splitedLine[i].replace('\"',' ');
				splitedLine[i]=splitedLine[i].trim();
			}
			if(splitedLine.length==1) {
				splitedLine[0]=splitedLine[0].replace('\"',' ');
				splitedLine[0]=splitedLine[0].trim();
				if(splitedLine[0].equals("{")) {
					StorageContainer newContainer = new StorageContainer();
					StorageContainer newElement = parseLine(br,currentLine,newContainer,currentDataBase);
					currentElement.addNewElement(newElement);
					
				}else if(splitedLine[0].equals("}")) {
					return currentElement;
				}else {
					System.out.print("error :");
				}
			}else {
				splitedLine[0]=splitedLine[0].replace('\"',' ');
				splitedLine[0]=splitedLine[0].trim();
				splitedLine[1]=splitedLine[1].trim();
				if(splitedLine[1].equals("{")) {
					StorageContainer newContainer = new StorageContainer();
					newContainer.elementTag = splitedLine[0];
					parseLine(br,currentLine,newContainer,currentDataBase);
				}else {
					if(splitedLine[1].charAt(splitedLine[1].length() -1) == ',') {
						splitedLine[1] = splitedLine[1].substring(0, splitedLine[1].length() -2);
					}
					if(splitedLine[1].charAt(0) == '\"') {
						splitedLine[1]=splitedLine[1].replace('\"',' ');
						splitedLine[1]=splitedLine[1].trim();
						StorageValue<String> newValue = new StorageValue<String>(splitedLine[0],splitedLine[1]);
						currentElement.addNewElement(newValue);
					}
					splitedLine[1]=splitedLine[1].trim();
					if(isDouble(splitedLine[1])) {
						StorageValue<Double> newValue = new StorageValue<Double>(splitedLine[0],Double.parseDouble(splitedLine[1]));
						currentElement.addNewElement(newValue);

					}else if(isInteger(splitedLine[1])) {
						StorageValue<Integer> newValue = new StorageValue<Integer>(splitedLine[0],Integer.parseInt(splitedLine[1]));
						currentElement.addNewElement(newValue);

					}else {
						StorageValue<Boolean> newValue = new StorageValue<Boolean>(splitedLine[0],Boolean.parseBoolean(splitedLine[1]));
						currentElement.addNewElement(newValue);
					}
				}

			}
			try {
				currentLine = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public StorageDataBase readFile(File fileToRead) {
		try(BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
			parseFile(br);

		}catch(Exception ex) {
			System.out.println("error : " + ex.getMessage() + ex.toString());
			for(int i = 0; i < ex.getStackTrace().length; i++) {
				System.out.println(ex.getStackTrace()[i].getClassName() + " - line " +ex.getStackTrace()[i].getLineNumber() );
			}
		}
		System.out.println("fin");
		return null;
	}
}
