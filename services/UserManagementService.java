package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services;

import java.util.ArrayList;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.User;


public interface UserManagementService {

	String registerUser(User user);
	
	ArrayList<User> getUsers();

	User getUserByEmail(String userEmail);

}