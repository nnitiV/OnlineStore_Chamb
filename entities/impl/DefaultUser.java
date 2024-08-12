package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.impl;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ResourceBundle;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.annotations.Validate;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.User;

public class DefaultUser implements User, Externalizable {

	private static int userCounter = 0;

	private int id;
	@Validate(pattern = "\\w{3,}")
	private String firstName;
	@Validate(pattern = "\\w{3,}")
	private String lastName;
	@Validate(pattern = "^(?=.*[A-Z])(?=.*[\\W_])(?=.*[a-z])(?=.*[0-9]).*$")
	private String password;
	@Validate(pattern = "^(?=.*[\\w\\W]).+@(?:gmail\\.com|email\\.com|hotmail\\.com)$")
	private String email;
	private ApplicationContext context;
	private ResourceBundle bundle;

	{
		context = ApplicationContext.getInstance();
		bundle = context.getBundle();
		id = ++userCounter;
	}

	public DefaultUser() {
	}

	public DefaultUser(String firstName, String lastName, String password, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	public DefaultUser(int id, String firstName, String lastName, String password, String email) {
		this.id = id;
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
		return bundle.getString("first_name") + ": " + this.getFirstName() + "\t\t" + bundle.getString("last_name")
				+ ": " + this.getLastName() + "\t\t" + bundle.getString("email") + ": " + this.getEmail();
	}

	@Override
	public void setPassword(String password) {
		if (password == null)
			return;
		this.password = password;
	}

	@Override
	public void setEmail(String newEmail) {
		if (newEmail == null)
			return;
		this.email = newEmail;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int getId() {
		return this.id;
	}

	void clearState() {
		userCounter = 0;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(this.firstName);
		out.writeObject(this.lastName);
		out.writeObject(this.email);
		out.writeObject(this.password);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.firstName = (String) in.readObject();
		this.lastName = (String) in.readObject();
		this.email = (String) in.readObject();
		this.password = (String) in.readObject();
	}
}