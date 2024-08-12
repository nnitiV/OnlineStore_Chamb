package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.utils.validation.impl;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.annotations.Validate;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.utils.validation.Validator;

public class DefaultValidator implements Validator {

	@Override
	public boolean isValid(Object obj) {
		Class<?> clazz = obj.getClass();
		try {
			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(Validate.class)) {
					Validate validateAnnotation = field.getAnnotation(Validate.class);
					String validatePattern = validateAnnotation.pattern();
					field.setAccessible(true);
					String fieldTest = (String) field.get(obj);

					if (fieldTest != null) {
						return Pattern.matches(validatePattern, fieldTest);
					}
				}
			}
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

}
