package com.itbulls.cunha.utils.validation.impl;

import java.util.Arrays;

import com.itbulls.cunha.annotation.Validate;
import com.itbulls.cunha.utils.validation.Validator;

public class DefaultValidator implements Validator {

	@Override
	public boolean isValid(Object obj) {
		if (obj == null)
			return false;
		Class<? extends Object> clazz = obj.getClass();
		return Arrays.stream(clazz.getDeclaredFields()).allMatch(field -> {
			Validate validate = field.getAnnotation(Validate.class);
			if (validate != null) {
				String pattern = validate.pattern();
				field.setAccessible(true);

				Object fieldValue = null;
				try {
					fieldValue = field.get(obj);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				if (fieldValue instanceof String) {
					if (!((String) fieldValue).matches(pattern)) {
						return false;
					}
				}
			}
			return true;
		});
	}

}
