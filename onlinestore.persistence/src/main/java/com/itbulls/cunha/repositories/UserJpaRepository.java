package com.itbulls.cunha.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.entities.User;

@Repository
public interface UserJpaRepository extends CrudRepository<User, Long> {
	User findUserByFirstName(String firstName);
	User findByReferralUser(User referralUser);
	User findByPartnerCode(String parnterCode);
	User findUserByEmail(String email);
	
	List<User> findByReferralUserId(long referralUserId);
}
