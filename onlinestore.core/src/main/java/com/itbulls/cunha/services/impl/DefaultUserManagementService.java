package com.itbulls.cunha.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itbulls.cunha.dao.UserDAO;
import com.itbulls.cunha.dao.impl.JpaUserDao;
import com.itbulls.cunha.dto.UserDTO;
import com.itbulls.cunha.dto.converters.UserDtoToUserConverter;
import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.services.UserManagementService;

@Service
public class DefaultUserManagementService implements UserManagementService {

	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
	private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";

	private static DefaultUserManagementService instance;

	private List<User> registeredUsers;

	private UserDAO userDAO;
	private UserDtoToUserConverter userConverter;

	{
		registeredUsers = new ArrayList<>();
		userDAO = new JpaUserDao();
		userConverter = new UserDtoToUserConverter();
	}

	public DefaultUserManagementService() {
	}

	@Override
	public String registerUser(User user) {
		if (user == null) {
			return NO_ERROR_MESSAGE;
		}

		UserDTO userDTO = userDAO.getUserByEmail(user.getEmail());
		if (user.getEmail() == null || user.getEmail().isEmpty()) {
			return EMPTY_EMAIL_ERROR_MESSAGE;
		} else if (userDTO != null) {
			return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
		}

		userDAO.saveUser(userConverter.convertUserToUserDto(user));
		return NO_ERROR_MESSAGE;
	}

	public static UserManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultUserManagementService();
		}
		return instance;
	}

	public void updateLoggedUser(User user) {
		if (userDAO.getUserById(user.getId()) != null) {
			userDAO.updateUser(userConverter.convertUserToUserDto(user));
		}
	}

	@Override
	public List<User> getUsers() {
		return userConverter.convertListUsersDtoToListUser(userDAO.getUsers());
	}

	@Override
	public User getUserByEmail(String userEmail) {
		if (userEmail == null || userEmail.isEmpty()) {
			return null;
		}
		User user = userConverter.convertUserDtoToUser(userDAO.getUserByEmail(userEmail));
		return user;
	}

	void clearServiceState() {
		registeredUsers = new ArrayList<>();
	}

	@Override
	public void retrieveUsers() {
		registeredUsers = userConverter.convertListUsersDtoToListUser(userDAO.getUsers());
	}

	@Override
	public void printUsersToConsole() {
		registeredUsers.forEach(user -> user.printUserWithoutPassword());
	}

}