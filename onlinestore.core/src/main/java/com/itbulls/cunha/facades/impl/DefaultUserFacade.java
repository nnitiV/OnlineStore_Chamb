package com.itbulls.cunha.facades.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.dao.UserDAO;
import com.itbulls.cunha.dto.converters.UserDtoToUserConverter;
import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.facades.UserFacade;
import com.itbulls.cunha.services.AffiliateMarketingService;

@Service
public class DefaultUserFacade implements UserFacade {
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserDtoToUserConverter userConverter;
	@Autowired
	private AffiliateMarketingService affiliateMarketingService;


	@Override
	public User getUserById(int userId) {
		return userConverter.convertUserDtoToUser(userDAO.getUserById(userId));
	}

	@Override
	public User getUserByEmail(String userEmail) {
		return userConverter.convertUserDtoToUser(userDAO.getUserByEmail(userEmail));
	}

	@Override
	public void saveUser(User user, String referrerCode) {
		user.setPartnerCode(affiliateMarketingService.generateUniquePartnerCode());
		user.setReferralUser(userConverter.convertUserDtoToUser(userDAO.getUserByPartnerCode(referrerCode)));
		userDAO.saveUser(userConverter.convertUserToUserDto(user));
	}

	@Override
	public List<User> getAllUsersWithReferralCode(User user) {
		return userConverter.convertListUsersDtoToListUser(userDAO.getAllUsersWithReferralCode(user.getId()));
	}

	@Override
	public void updateUser(User user) {
		userDAO.updateUser(userConverter.convertUserToUserDto(user));
	}

	@Override
	public List<User> getAllUsers() {
		return userConverter.convertListUsersDtoToListUser(userDAO.getUsers());
	}

}
