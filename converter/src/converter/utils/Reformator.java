package converter.utils;

import java.lang.reflect.Field;



public class Reformator {
	public static String getExtension(String fileName) {
		int index = fileName.indexOf(".");
		while(fileName.indexOf(".",index+1)!=-1) {
			index = fileName.indexOf(".",index+1);
		}
		if(index == -1) {
			return null;
		}
		return fileName.substring(index+1, fileName.length());
	}
	
	public static void reformat(Object myObject){
		Class c = myObject.getClass();
		Field[] myObjectFields = c.getDeclaredFields();
		for(int i = 0; i < myObjectFields.length; i++) {
			Field currentField = myObjectFields[i];
			reformat(myObject, currentField.getName());
		}
	}
	
	
	public static void reformat(Object myObject, String fieldName){
		//get the class of the object
		Class c = myObject.getClass();
		//create field variable to get the variable and annotation
		Field argField;

		try {
			//get the field of the given field name
			argField = c.getField(fieldName);
			//check if there is an annotation before the field
			if(argField.isAnnotationPresent(FileFormat.class)) {
				//get the annotation in front of the field
				FileFormat formatAnno = argField.getAnnotation(FileFormat.class);
				//get the value of the field for myObject
				String stringValue = (String)argField.get(myObject);
				if(!formatAnno.supportedExtention().equals(getExtension(stringValue))) {
					System.out.print("reformating " + stringValue);
					stringValue += "." + formatAnno.supportedExtention();
					System.out.println(" to " + stringValue);
					argField.set(myObject, stringValue);
				}
			}
			
		} catch (NoSuchFieldException e) {
			System.out.println(e.getMessage());
		}	catch(IllegalAccessException e) {
			System.out.println(e.getMessage());

		}
	}
}
