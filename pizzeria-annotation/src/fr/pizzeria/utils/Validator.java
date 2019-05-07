package fr.pizzeria.utils;

import java.lang.reflect.Field;

import fr.pizzaria.exception.ValidationException;

public class Validator {
	public static void validate(Object myObject, String fieldName) throws ValidationException{
		Class c = myObject.getClass();
		Field argField;

		try {
			argField = c.getField(fieldName);
			if(argField.isAnnotationPresent(Rule.class)) {
				Rule ruleAnno = argField.getAnnotation(Rule.class);
				double doubleValue = argField.getDouble(myObject);
				if((double)ruleAnno.min() > doubleValue) {
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
