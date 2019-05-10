package converter.console;

import java.io.File;

import converter.factory.ConverterFactory;
import converter.reader.*;
import converter.writer.*;

import converter.storage.*;

public class ConverterConsole {
	
	public static void main(String[] args) {
		ConverterFactory cf = new ConverterFactory();
		cf.getService(args).executerUC(args);
	}

}
