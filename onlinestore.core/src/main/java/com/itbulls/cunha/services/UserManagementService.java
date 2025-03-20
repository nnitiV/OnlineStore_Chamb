package com.itbulls.cunha.services;

import java.util.List;

import com.itbulls.cunha.entities.User;

public interface UserManagementService {

	String registerUser(User user);

	List<User> getUsers();

	User getUserByEmail(String userEmail);

	void updateLoggedUser(User user);

	void retrieveUsers();

	void printUsersToConsole();

}