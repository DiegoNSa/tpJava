package fr.pizzeria.utils;

import java.lang.reflect.Field;

public class StringUtils {
	public static void convertString(Object myObject, String fieldName) {
		Class c = myObject.getClass();
		Field argField;

		try {
			argField = c.getField(fieldName);
			ToString stringAnno = argField.getAnnotation(ToString.class);
			if(stringAnno.uppercase()) {
				String strValue = (String)argField.get(myObject);
				//System.out.println(strValue);
				strValue = strValue.toUpperCase();
				argField.set(myObject, strValue);
			}
		} catch (Exception  e) {
			System.out.println(e.getMessage());
		}	
		
	}
}
