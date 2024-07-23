package com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.enteties;

public interface User {
	
	String getFirstName();
	String getLastName();
	String getPassword();
	String getEmail();
	int getId();
	
	void setPassword(String newPassword);
	void setEmail(String newEmail);
	
	
}
