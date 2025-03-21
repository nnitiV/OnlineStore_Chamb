package com.itbulls.cunha.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name = "user")
public class UserDTO implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "first_name")
	private String first_name;

	@Column(name = "last_name")
	private String last_name;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "credit_card_number")
	private String credit_card_number;

	@Column(name = "partner_code")
	private String partnerCode;
	@Column(name = "enabled")
	private boolean isEnabled;

	@Column(name = "credit")
	private Double credit;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<RoleDTO> roles;

	@ManyToOne
	@JoinColumn(name = "referral_user")
	private UserDTO referralUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getCreditCardNumber() {
		return credit_card_number;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.credit_card_number = creditCardNumber;
	}

	public Set<RoleDTO> getRoleDTO() {
		return roles;
	}

	public void setRoleDTO(Set<RoleDTO> roleDTO) {
		this.roles = roleDTO;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public UserDTO getReferralUser() {
		return referralUser;
	}

	public void setReferralUser(UserDTO referralUser) {
		this.referralUser = referralUser;
	}

	public double getCredit() {
		return credit != null ? credit : 0.0;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", password=" + password + ", credit_card_number=" + credit_card_number + ", partnerCode="
				+ partnerCode + ", credit=" + credit + ", roleDTO=" + roles + ", referralUser=" + referralUser + "]";
	}

}
