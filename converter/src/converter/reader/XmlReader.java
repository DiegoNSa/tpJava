package converter.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import converter.storage.StorageContainer;
import converter.storage.StorageDataBase;
import converter.storage.StorageValue;
import converter.utils.FileFormat;

public class XmlReader implements GlobalReader {
	@FileFormat(supportedExtention = "xml")
	public String fileName;
	public XmlReader(String fileName) {
		this.fileName = fileName;
	}
	
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

	public static void parseFile(StringBuilder fileString, StorageDataBase dataBase, StorageContainer currentContainer,int startIndex, int endIndex) {
		if(endIndex == -1) {
			endIndex = fileString.length()-1;
		}
		//System.out.println("new : " + startIndex + " / "  +endIndex);
		//System.out.println("bliblablo" + fileString.substring(startIndex,endIndex));
		int currentIndex = startIndex;
		currentIndex = fileString.indexOf("<", currentIndex);
		while(currentIndex < endIndex && currentIndex != -1) {
			//System.out.println("head : " + currentIndex);
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
					
					String containerLabel = fileString.substring(currentIndex+1,spaceIndex).trim();
					//System.out.println(containerLabel);
					StorageContainer newContainer = new StorageContainer();
					int equalSymbolIndex = fileString.indexOf("=", currentIndex);

					newContainer.elementTag=containerLabel;
					while(quoteIndex < endIndex && quoteIndex != -1 && quoteIndex < endLabelIndex) {
						int endQuoteIndex = fileString.indexOf("\"", quoteIndex+1);
						endLabelIndex = fileString.indexOf(">", endQuoteIndex);
						inlineEndIndex = fileString.indexOf("/>", endQuoteIndex);
						
						StorageValue<String> newValue;
						//System.out.println(spaceIndex + " ///// " + equalSymbolIndex);
						String labelString = fileString.substring(spaceIndex,equalSymbolIndex).trim();
						String valueString = fileString.substring(quoteIndex+1,endQuoteIndex).trim();
						//System.out.println(labelString + " == " + valueString);

						newValue = new StorageValue<String>(labelString,valueString);
						
						newContainer.addNewElement(newValue);

						quoteIndex = fileString.indexOf("\"", endQuoteIndex+1);
						spaceIndex = fileString.indexOf(" ", endQuoteIndex+1);
						equalSymbolIndex = fileString.indexOf("=", endQuoteIndex+1);
						
						//System.out.println(quoteIndex + " --------- " + endLabelIndex);

					}
					//System.out.println("SOOOOOOOOOOOOOOOOOOOOORTIIIIIIIIIIIIIIIIIIIIIIE");

					if(endLabelIndex == inlineEndIndex + 1) {
						if(currentContainer != null) {
							currentContainer.addNewElement(newContainer);
						}else {
							dataBase.addNewData(newContainer);
						}
						currentIndex = fileString.indexOf("<",inlineEndIndex+1);
					}else {
						int endPropertyIndex = fileString.indexOf("</"+containerLabel, endLabelIndex);
						int nextDeclaration = fileString.indexOf("<", endLabelIndex);
						if(endPropertyIndex == nextDeclaration) {
							if(endPropertyIndex == -1) {
								System.out.println("error : " + containerLabel);
							}else {
								if(currentContainer != null) {
									currentContainer.addNewElement(newContainer);
								}else {
									dataBase.addNewData(newContainer);
								}
							}
						}else{
							parseFile(fileString, dataBase, newContainer,endLabelIndex+1,endPropertyIndex);
							if(currentContainer != null) {
								currentContainer.addNewElement(newContainer);
							}else {
								dataBase.addNewData(newContainer);
							}
						}
						currentIndex = fileString.indexOf("<",endPropertyIndex+1);
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
						if(currentContainer != null) {
							currentContainer.addNewElement(newContainer);
						}else {
							dataBase.addNewData(newContainer);
						}
					}
					currentIndex = fileString.indexOf("<",endPropertyIndex+1);

				}
			}
		}
		return;
	}

	public static StorageDataBase parseFile(StringBuilder fileString) {
		StorageDataBase newDataBase = new StorageDataBase();
		parseFile(fileString,newDataBase,null,0,fileString.length()-1);
		return newDataBase;
		
	}

	@Override
	public StorageDataBase readFile() {
		StorageDataBase parsedData =  new StorageDataBase();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    String line = br.readLine();

			try {
				StringBuilder oneLineDoc = new StringBuilder(line);
			    while (line != null) {
			        line = br.readLine();
			        if(line != null) {
			        	oneLineDoc.append(line + " ");
			        }
			    }
			    parsedData = parseFile(oneLineDoc);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		//System.out.println("fin");
		return parsedData;
	}

}
