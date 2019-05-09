package converter.file.format;

import java.io.File;
import java.io.FileReader;

public abstract class ConverterFile {
	public File fileToRead;
	public FileReader inputFile;
	public boolean isOpen = false;
	
	public void openFile(String fileName) {
		closeFile();
		fileToRead = new File(fileName);
		
		
	}
	public abstract void readFile();
	public  void closeFile() {
		if(isOpen) {
			try {
				inputFile.close();

			}catch(Exception ex) {
				System.out.print(ex.getMessage());
			}
		}
	}

}
