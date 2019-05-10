package converter.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import converter.storage.StorageContainer;
import converter.storage.StorageDataBase;
import converter.storage.StorageValue;

public class XmlReader implements GlobalReader {

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
	
	public static boolean isBoolean(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		if(str.contains("true") || str.contains("false")) {
			return true;
		}
		return false;
	}

	public void parseFile(StringBuilder fileString, StorageDataBase dataBase, StorageContainer currentContainer,int startIndex, int endIndex) {
		String line;
		System.out.print("new");
		int currentIndex = startIndex;
		currentIndex = fileString.indexOf("<", currentIndex);
		while(currentIndex < endIndex && currentIndex != -1) {
			
			int commentIndex = fileString.indexOf("<--", currentIndex);
			int infoIndex = fileString.indexOf("<?", currentIndex);

			int endLabelIndex = fileString.indexOf(">", currentIndex);
			int spaceIndex = fileString.indexOf(" ", currentIndex);
			int quoteIndex = fileString.indexOf("\"", currentIndex);

			int inlineEndIndex = fileString.indexOf("/>", currentIndex);
			
			//ignore comment
			if(commentIndex == currentIndex) {
				currentIndex = fileString.indexOf("-->", currentIndex)+3;
			}
			//ignore info
			else if(infoIndex == currentIndex) {
				currentIndex = fileString.indexOf("?>", currentIndex)+2;
			}
			//real content
			else {
				//property contains values
				if(quoteIndex != -1 && quoteIndex < endLabelIndex) {
					
					int endQuoteIndex = fileString.indexOf("\"", quoteIndex+1);
					endLabelIndex = fileString.indexOf(">", endQuoteIndex);
					inlineEndIndex = fileString.indexOf("/>", endQuoteIndex);

					String containerLabel = fileString.substring(currentIndex+1,spaceIndex).trim();
					System.out.println(containerLabel);
					StorageContainer newContainer = new StorageContainer();
					newContainer.elementTag=fileString.substring(currentIndex+1, spaceIndex);
					while(quoteIndex < endIndex && quoteIndex != -1) {
						StorageValue<String> newValue;
						int equalSymbolIndex = fileString.indexOf("=", currentIndex);
						String labelString = fileString.substring(spaceIndex,equalSymbolIndex).trim();
						String valueString = fileString.substring(quoteIndex,endQuoteIndex).trim();
						
						newValue = new StorageValue<String>(labelString,valueString);
						
						newContainer.addNewElement(newValue);
						quoteIndex = fileString.indexOf("\"", endQuoteIndex+1);
						endQuoteIndex = fileString.indexOf("\"", quoteIndex+1);
						endLabelIndex = fileString.indexOf(">", endQuoteIndex);
						inlineEndIndex = fileString.indexOf("/>", endQuoteIndex);
					}
					
					if(endLabelIndex == inlineEndIndex + 1) {
						if(currentContainer != null) {
							currentContainer.addNewElement(newContainer);
						}else {
							dataBase.addNewData(newContainer);
						}
						currentIndex = inlineEndIndex+1;
					}else {
						int endPropertyIndex = fileString.indexOf("</"+containerLabel, endLabelIndex);
						int nextDeclaration = fileString.indexOf("<"+containerLabel, endLabelIndex);
						if(endPropertyIndex == nextDeclaration) {
							if(endPropertyIndex == -1) {
								System.out.println("error : fdjqsklfjklsqlj");
							}else {
								if(currentContainer != null) {
									currentContainer.addNewElement(newContainer);
								}else {
									dataBase.addNewData(newContainer);
								}
							}
						}else{
							parseFile(fileString, dataBase, newContainer,endLabelIndex,endPropertyIndex);
						}
						currentIndex = endPropertyIndex+1;
					}
					//property does not contain value
				}else {
					String elementLabel = fileString.substring(currentIndex+1,endLabelIndex).trim();
					int endPropertyIndex = fileString.indexOf("</"+elementLabel, endLabelIndex);
					int nextDeclaration = fileString.indexOf("<", endLabelIndex+1);
					//value
					if(endPropertyIndex == nextDeclaration) {
						if(endPropertyIndex == -1) {
							System.out.println("error : fdjqsklfjklsqlj");
						}else {
							String valueString = fileString.substring(endLabelIndex+1,nextDeclaration);
							StorageValue<String> newValue = new StorageValue<String>(elementLabel,valueString);

							if(currentContainer != null) {
								currentContainer.addNewElement(newValue);
							}else {
								dataBase.addNewData(newValue);
							}
						}
						//container
					}else{
						StorageContainer newContainer = new StorageContainer();
						parseFile(fileString, dataBase, newContainer,endLabelIndex,endPropertyIndex);
					}
					currentIndex = endPropertyIndex+1;

				}
			}
		}
		return;
	}

	public StorageContainer parseLine(BufferedReader br, String line, StorageContainer currentElement,
			StorageDataBase currentDataBase) {
		String currentLine = line;
		try {
			currentLine = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (currentLine != null) {
			//System.out.println(currentLine);
			currentLine = currentLine.trim();
			if(currentLine.charAt(0) == '<') {
				//opening property
				int i = currentLine.indexOf(">", 0);
				int j = currentLine.indexOf(" ", 0);
				int k = currentLine.indexOf("/>", 0);
				
				if(j != -1) {
					do {
						j = currentLine.indexOf(" ", j+1);
					}while(j!=-1);
				}
				
			}
			String[] splitedLine = currentLine.split(":");
			for (int i = 0; i < splitedLine.length; i++) {
				// splitedLine[i]=splitedLine[i].replace('\"',' ');
				splitedLine[i] = splitedLine[i].trim();
			}
			if (splitedLine.length == 1) {
				splitedLine[0] = splitedLine[0].replace('\"', ' ');
				splitedLine[0] = splitedLine[0].trim();
				if (splitedLine[0].contains("{")) {
					StorageContainer newContainer = new StorageContainer();
					StorageContainer newElement = parseLine(br, currentLine, newContainer, currentDataBase);
					if(newElement != null) {

						if (currentElement != null) {
							currentElement.addNewElement(newElement);
						} else {
							currentDataBase.addNewData(newElement);
						}
					}
				} else if (splitedLine[0].contains("}")) {
					return currentElement;
				} else {
					System.out.print("error = " + splitedLine[0]);
				}
			} else {
				splitedLine[0] = splitedLine[0].replace('\"', ' ');
				splitedLine[0] = splitedLine[0].trim();
				splitedLine[1] = splitedLine[1].trim();
				if (splitedLine[1].contains("{")) {
					StorageContainer newContainer = new StorageContainer();
					newContainer.elementTag = splitedLine[0];
					StorageContainer newElement = parseLine(br, currentLine, newContainer, currentDataBase);
					if(newElement != null) {
						if (currentElement != null) {
							currentElement.addNewElement(newElement);
						} else {
							currentDataBase.addNewData(newElement);
						}
					}
				} else {
					if (splitedLine[1].charAt(splitedLine[1].length() - 1) == ',') {
						splitedLine[1] = splitedLine[1].substring(0, splitedLine[1].length() - 1);
					}
					if (splitedLine[1].charAt(0) == '\"') {
						splitedLine[1] = splitedLine[1].replace('\"', ' ');
						splitedLine[1] = splitedLine[1].trim();
						StorageValue<String> newValue = new StorageValue<String>(splitedLine[0], splitedLine[1]);
						currentElement.addNewElement(newValue);
					} else {
						splitedLine[1] = splitedLine[1].trim();
						if (isDouble(splitedLine[1])) {
							StorageValue<Double> newValue = new StorageValue<Double>(splitedLine[0],
									Double.parseDouble(splitedLine[1]));
							currentElement.addNewElement(newValue);

						} else if (isInteger(splitedLine[1])) {
							StorageValue<Integer> newValue = new StorageValue<Integer>(splitedLine[0],
									Integer.parseInt(splitedLine[1]));
							currentElement.addNewElement(newValue);

						} else {
							StorageValue<Boolean> newValue;
							System.out.println(splitedLine[1]);
							if(splitedLine[1].contains("true")) {
								newValue = new StorageValue<Boolean>(splitedLine[0],
										true);
							}else {
								newValue = new StorageValue<Boolean>(splitedLine[0],
										false);
							}
							currentElement.addNewElement(newValue);
						}
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
		StorageDataBase parsedData;
		try (BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
		    String line = br.readLine();

			try {
				StringBuilder oneLineDoc = new StringBuilder(line);
			    while (line != null) {
			        line = br.readLine();
			        if(line != null) {
			        	oneLineDoc.append(line + " ");
			        }
			    }
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println("error : " + ex.getMessage() + ex.toString());
			for (int i = 0; i < ex.getStackTrace().length; i++) {
				System.out.println(
						ex.getStackTrace()[i].getClassName() + " - line " + ex.getStackTrace()[i].getLineNumber());
			}
			return null;
		}
		System.out.println("fin");
		return parsedData;
	}

}
