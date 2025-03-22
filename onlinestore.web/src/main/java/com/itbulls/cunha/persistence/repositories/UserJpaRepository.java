package com.itbulls.cunha.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.persistence.entities.User;

@Repository
public interface UserJpaRepository extends CrudRepository<User, Long> {
	List<User> findUserByFirstName(String firstName);

	User findByPartnerCode(String parnterCode);

	List<User> findByReferralUser(User referralUser);

	User findUserByEmail(String email);
}
