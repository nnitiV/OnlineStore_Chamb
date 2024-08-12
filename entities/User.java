package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities;

public interface User {
	
	String getFirstName();
	String getLastName();
	String getPassword();
	String getEmail();
	int getId();
	
	void setPassword(String newPassword);
	void setEmail(String newEmail);
	void setFirstName(String firstName);
	void setLastName(String lastName);
	
	
}
