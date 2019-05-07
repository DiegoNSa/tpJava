package fr.pizzeria.utils;

import java.lang.reflect.Field;

import fr.pizzaria.exception.ValidationException;

public class Validator {
	public static void validate(Object myObject) throws ValidationException{
		Class c = myObject.getClass();
		Field[] myObjectFields = c.getDeclaredFields();
		for(int i = 0; i < myObjectFields.length; i++) {
			Field currentField = myObjectFields[i];
			validate(myObject, currentField.getName());
		}
	}
	
	
	public static void validate(Object myObject, String fieldName) throws ValidationException{
		//get the class of the object
		Class c = myObject.getClass();
		//create field variable to get the variable and annotation
		Field argField;

		try {
			//get the field of the given field name
			argField = c.getField(fieldName);
			//check if there is an annotation before the field
			if(argField.isAnnotationPresent(Rule.class)) {
				//get the annotation in front of the field
				Rule ruleAnno = argField.getAnnotation(Rule.class);
				//get the value of the field for myObject
				double doubleValue = argField.getDouble(myObject);
				if((double)ruleAnno.min() > doubleValue) {
					//throw exception if the value is less than the given min value in rule
					throw new ValidationException("invalid value");
				}
			}
			
			
		} catch (NoSuchFieldException e) {
			System.out.println(e.getMessage());
		}	catch(IllegalAccessException e) {
			System.out.println(e.getMessage());

		}
	}
}
