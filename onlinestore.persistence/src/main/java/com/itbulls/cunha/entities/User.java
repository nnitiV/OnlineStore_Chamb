package com.itbulls.cunha.entities;

import java.util.List;
import java.util.Set;

public interface User {
	
	String getFirstName();
	String getLastName();
	String getPassword();
	String getEmail();
	String getCredit_card_number();
	String getPartnerCode();
	Set<Role> getRoles();
	User getReferralUser();
	int getId();
	Double getCredit();
	
	void setPassword(String newPassword);
	void setEmail(String newEmail);
	void setFirstName(String firstName);
	void setLastName(String lastName);
	void setCredit_card_number(String credit_card_number);
	void setPartnerCode(String partnerCode);
	void setCredit(Double credit);
	
	void printUserWithoutPassword();
	void setReferralUser(User convertUserDtoToUser);
	boolean isEnabled();
}