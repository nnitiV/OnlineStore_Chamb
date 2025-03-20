package com.itbulls.cunha.facades;

import java.util.List;

import com.itbulls.cunha.entities.User;

public interface UserFacade {
	User getUserById(int userId);
	User getUserByEmail(String userEmail);
	void saveUser(User user, String referrerCode);
	void updateUser(User user);
	List<User> getAllUsers();
	List<User> getAllUsersWithReferralCode(User user);
}
