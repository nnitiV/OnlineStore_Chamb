package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.impl;

import java.util.Objects;
import java.util.ResourceBundle;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.io.hw.backendonlineshop.entities.User;

public class UserForHashTables implements User {
	private static int userCounter = 0;

	private int id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private ApplicationContext context;
	private ResourceBundle bundle;

	{
		context = ApplicationContext.getInstance();
		bundle = context.getBundle();
		id = ++userCounter;
	}

	public UserForHashTables() {
	}

	public UserForHashTables(String firstName, String lastName, String password, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	public UserForHashTables(int id, String firstName, String lastName, String password, String email) {
		this.id = id;
		userCounter--; // to keep sequantial id
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public String getLastName() {
		return this.lastName;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public String toString() {
		return "ID: " + this.getId() + "\t\t" + bundle.getString("first_name") + ": " + this.getFirstName() + "\t\t"
				+ bundle.getString("last-name") + ": " + this.getLastName() + "\t\t" + bundle.getString("email") + ": "
				+ this.getEmail();
	}

	@Override
	public void setPassword(String password) {
		if (password == null) {
			return;
		}
		this.password = password;
	}

	@Override
	public void setEmail(String newEmail) {
		if (newEmail == null) {
			return;
		}
		this.email = newEmail;
	}

	@Override
	public int getId() {
		return this.id;
	}

	void clearState() {
		userCounter = 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserForHashTables other = (UserForHashTables) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password);
	}

}