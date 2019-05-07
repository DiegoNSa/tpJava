package fr.pizzeria.utils;

import java.lang.reflect.Field;

public class StringUtils {
	public static void convertString(Object myObject) {
		Class c = myObject.getClass();
		Field[] myObjectFields = c.getDeclaredFields();
		for(int i = 0; i < myObjectFields.length; i++) {
			Field currentField = myObjectFields[i];
			convertString(myObject, currentField.getName());
		}

	}
	
	public static void convertString(Object myObject, String fieldName) {
		Class c = myObject.getClass();
		Field argField;

		try {
			//get the field of the given name
			argField = c.getField(fieldName);
			//check if the annotation exists in front of the argument
			if(argField.isAnnotationPresent(ToString.class)) {
				//get the annotation in front of the argument
				ToString stringAnno = argField.getAnnotation(ToString.class);
				if(stringAnno.uppercase()) {
					//cast the value of the field from myObject as a string
					String strValue = (String)argField.get(myObject);
					strValue = strValue.toUpperCase();
					//set the uppercase value of the argument for myObject
					argField.set(myObject, strValue);
				}
			}
		} catch (Exception  e) {
			//System.out.println("error :" + e.getMessage());
		}	
		
	}
}
