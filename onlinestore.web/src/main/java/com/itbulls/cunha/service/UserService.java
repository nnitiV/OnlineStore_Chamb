package com.itbulls.cunha.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.persistence.entities.User;
import com.itbulls.cunha.persistence.repositories.UserJpaRepository;

@Service
public class UserService {

	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@Transactional
	public List<User> getAllUsers() {
		return (List<User>) userJpaRepository.findAll();
	}
	
	@Transactional
	public User getUserById(long id) {
		return (User) userJpaRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public User getUserByFirstName(String firstName) {
		return (User) userJpaRepository.findUserByFirstName(firstName);
	}
	
	@Transactional 
	public User getUserByEmail(String email) {
		return (User) userJpaRepository.findUserByEmail(email);
	}
	
	@Transactional 
	public User getUserByPartnerCode(String partnerCode) {
		return (User) userJpaRepository.findByPartnerCode(partnerCode);
	}
	
	@Transactional
	public User getUserByReferralUser(User referralUser) {
		return (User) userJpaRepository.findByReferralUser(referralUser);
	}
	
	@Transactional
	public void deletUserById(long userId) {
		userJpaRepository.deleteById(userId);
	}
	
	@Transactional
	public boolean addUser(User user) {
		return userJpaRepository.save(user) != null;
	}
	
	@Transactional
	public boolean updateUser(User user) {
		return userJpaRepository.save(user) != null;
	}
}
