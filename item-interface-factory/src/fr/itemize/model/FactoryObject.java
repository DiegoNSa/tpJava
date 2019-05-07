package fr.itemize.model;

import java.util.Hashtable;

public class FactoryObject {
	public FactoryType defineType;
	public Hashtable<String,String> argumentValues;
	
	public FactoryObject(FactoryType type) {
		defineType = type;
		for(String key: defineType.variables){
			argumentValues.put(key, "");
		}
	}
	
	public FactoryObject(FactoryType type, Hashtable<String,String> values) {
		defineType = type;
		argumentValues = values;
	}
	
	public String describe() {
		String describingString = "";
		for(String key: argumentValues.keySet()) {
			if(argumentValues.get(key) != null) {
				describingString += key + " = " + argumentValues.get(key) + "- "; 

			}
			else {
				describingString += "null - "; 
			} 
		}
		describingString += "\n";
		return describingString;
	}
}
