package com.itbulls.cunha.dao;

import java.util.List;

import com.itbulls.cunha.dto.UserDTO;

public interface UserDAO {
	List<UserDTO> getUsers();

	UserDTO getUserById(int id);

	UserDTO getUserByEmail(String email);

	UserDTO getUserByPartnerCode(String partnerCode);

	void saveUser(UserDTO user);

	void updateUser(UserDTO userDto);

	List<UserDTO> getAllUsersWithReferralCode(int userId);
}
