package com.itbulls.cunha.services;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.repositories.RoleJpaRepository;
import com.itbulls.cunha.repositories.UserJpaRepository;
import com.itbulls.cunha.services.impl.DefaultAffiliateMarketingService;

@Service
public class UserService {

	@Autowired
	private UserJpaRepository userJpaRepository;
	@Autowired
	private RoleJpaRepository roleJpaRepository;
	private AffiliateMarketingService affiliateMarketingService;
	
	{
		affiliateMarketingService = new DefaultAffiliateMarketingService();
	}
	
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
	public List<User> getUsersByReferralUserId(Long referralUserId){
		return (List<User>) userJpaRepository.findByReferralUserId(referralUserId);
	}
	
	@Transactional
	public void deletUserById(long userId) {
		userJpaRepository.deleteById(userId);
	}
	
	@Transactional
	public boolean addUser(User user) {
		user.setPartnerCode(affiliateMarketingService.generateUniquePartnerCode());
		user.setRoles(Set.of(roleJpaRepository.findById((long) 2).orElse(null)));
		System.out.println(user);
		return userJpaRepository.save(user) != null;
	}
	
	@Transactional
	public boolean updateUser(User user) {
		return userJpaRepository.save(user) != null;
	}
}
