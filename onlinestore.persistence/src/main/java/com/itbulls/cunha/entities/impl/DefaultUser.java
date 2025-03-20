package com.itbulls.cunha.entities.impl;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.itbulls.cunha.entities.Role;
import com.itbulls.cunha.entities.User;

public class DefaultUser implements User {

	private int id;
	@NotEmpty(message = "First name should not be empty!")
	private String firstName;

	@NotEmpty(message = "Last name should not be empty!")
	private String lastName;

	@NotEmpty(message = "Password should not be empty!")
	@Size(min = 3, max = 20, message = "Password should be between 3 and 20 characters!")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+=\\\\[\\\\]{};':\\\"\\\\\\\\|,.<>\\\\/?-]).{8,}$", message = "Message should contain at least one uppercase and one number!")
	private String password;

	@NotEmpty(message = "Password should not be empty!")
	@Size(min = 3, max = 20, message = "Password should be between 3 and 20 characters!")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+=\\\\[\\\\]{};':\\\"\\\\\\\\|,.<>\\\\/?-]).{8,}$", message = "Message should contain at least one uppercase and one number!")
	private String repeatPassword;

	@NotEmpty(message = "Email should not be empty!")
	@Email(message = "Please, insert an email!")
	private String email;

	private String credit_card_number, partnerCode;
	private Double credit;
	private User referralUser;
	private Role role = new DefaultRole(2, "ROLE_CUSTOMER");

	public DefaultUser() {
	}

	public DefaultUser(int id, String firstName, String lastName, String password, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	public DefaultUser(String firstName, String lastName, String password, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	public DefaultUser(int id, String firstName, String lastName, String password, String email,
			String credit_card_number) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.credit_card_number = credit_card_number;
	}

	public DefaultUser(int id, String firstName, String lastName, String password, String email,
			String credit_card_number, Role role) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.credit_card_number = credit_card_number;
		this.role = role;
	}

	public DefaultUser(int id, String firstName, String lastName, String password, String email,
			String credit_card_number, Role role, String partnerCode, User referralUser) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.credit_card_number = credit_card_number;
		this.partnerCode = partnerCode;
		this.referralUser = referralUser;
		this.role = role;
	}

	public DefaultUser(int id, String firstName, String lastName, String password, String email,
			String credit_card_number, Role role, String partnerCode, User referralUser, double credit) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.credit_card_number = credit_card_number;
		this.partnerCode = partnerCode;
		this.credit = credit;
		this.referralUser = referralUser;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCredit_card_number() {
		return credit_card_number;
	}

	public void setCredit_card_number(String credit_card_number) {
		this.credit_card_number = credit_card_number;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public User getReferralUser() {
		return referralUser;
	}

	public void setReferralUser(User referralUser) {
		this.referralUser = referralUser;
	}

	public double getCredit() {
		return credit != null ? credit : 0.0;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "DefaultUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", email=" + email + ", credit_card_number=" + credit_card_number + ", partnerCode="
				+ partnerCode + ", credit=" + credit + ", referralUser=" + referralUser + ", role=" + role + "]";
	}

	@Override
	public void printUserWithoutPassword() {
		System.out.println("DefaultUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + "]");
	}
}