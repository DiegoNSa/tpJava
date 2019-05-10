package converter.factory;

import converter.service.*;


public class ConverterFactory {
	public GlobalService getService(String[] parameters) {
		if(parameters.length == 0 || parameters[0].equals("--help")) {
			return new HelpService();
		}else {
			return new ConvertingService();

		}
	}

}
