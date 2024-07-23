package com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.services;

import java.util.ArrayList;

import com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.enteties.User;

public interface UserManagementService {

	String registerUser(User user);
	
	ArrayList<User> getUsers();

	User getUserByEmail(String userEmail);

}