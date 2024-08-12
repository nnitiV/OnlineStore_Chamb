package com.itbulls.learnit.cunha.javacore.examsection43;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.User;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.impl.DefaultUser;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.utils.validation.Validator;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.utils.validation.impl.DefaultValidator;

public class ValidatorTest {
	
	private Validator testInstance;
	private User user;
	
	@BeforeEach 
	void setUp() {
		testInstance = new DefaultValidator();
	}
	
	@Test
	void shouldValidateUserEmail() {
		User user = new DefaultUser();
		user.setEmail("vitor@gmail.com");

		assertTrue(testInstance.isValid(user));
	}

	@Test
	void shouldValidateUserPassword() {
		User user = new DefaultUser();
		user.setPassword("Vitor_com1");

		assertTrue(testInstance.isValid(user));
	}

	@Test
	void shouldValidateUserFirstName() {
		User user = new DefaultUser();
		user.setFirstName("Vitor");

		assertTrue(testInstance.isValid(user));
	}

	@Test
	void shouldValidateUserLastName() {
		User user = new DefaultUser();
		user.setLastName("Augusto");

		assertTrue(testInstance.isValid(user));
	}

}
