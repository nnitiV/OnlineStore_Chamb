package com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.services.impl;

import java.util.ArrayList;

import com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.enteties.User;
import com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.services.UserManagementService;

public class DefaultUserManagementService implements UserManagementService {
	
	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
	private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";
	
//	private static final int DEFAULT_USERS_CAPACITY = 10;
	
	private static DefaultUserManagementService instance;
	
	private ArrayList<User> users;
//	private int lastUserIndex;
	
	{
		users = new ArrayList<>();
	}

	private DefaultUserManagementService() {
	}
	
	@Override
	public String registerUser(User user) {
		if (user == null) {
			return NO_ERROR_MESSAGE;
		}
		
		String errorMessage = checkUniqueEmail(user.getEmail());
		if (errorMessage != null && !errorMessage.isEmpty()) {
			return errorMessage;
		}
		
		users.add(user);
		
		return NO_ERROR_MESSAGE;
	}

	private String checkUniqueEmail(String email) {
		if (email == null || email.isEmpty()) {
			return EMPTY_EMAIL_ERROR_MESSAGE;
		}
		for (User user : users) {
			if (user != null && 
					user.getEmail() != null &&
					user.getEmail().equalsIgnoreCase(email)) {
				return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
			}
		}
		return NO_ERROR_MESSAGE;
	}

	public static UserManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultUserManagementService();
		}
		return instance;
	}

	
	@Override
	public ArrayList<User> getUsers() {
		ArrayList<User> nonNullUsers = new ArrayList<>();
		
//		int index = 0;
		for (User user : users) {
			if (user != null) {
				nonNullUsers.add(user);
			}
		}
		
		return nonNullUsers;
	}

	@Override
	public User getUserByEmail(String userEmail) {
		for (User user : users) {
			if (user != null && user.getEmail().equalsIgnoreCase(userEmail)) {
				return user;
			}
		}
		return null;
	}
	
	void clearServiceState() {
		users = new ArrayList<User>();
	}
	
	
//	// Stream API version of the method
//	@Override
//	public User[] getUsers() {
//		return Arrays.stream(users)
//				.filter(Objects::nonNull)
//				.toArray(User[]::new);
//	}

}