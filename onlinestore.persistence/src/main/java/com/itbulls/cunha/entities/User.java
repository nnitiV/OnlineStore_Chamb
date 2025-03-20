package com.itbulls.cunha.entities;

public interface User {
	
	String getFirstName();
	String getLastName();
	String getPassword();
	String getEmail();
	String getCredit_card_number();
	String getPartnerCode();
	Role getRole();
	User getReferralUser();
	int getId();
	double getCredit();
	
	void setPassword(String newPassword);
	void setEmail(String newEmail);
	void setFirstName(String firstName);
	void setLastName(String lastName);
	void setCredit_card_number(String credit_card_number);
	void setPartnerCode(String partnerCode);
	void setCredit(double credit);
	
	void printUserWithoutPassword();
	void setReferralUser(User convertUserDtoToUser);
}