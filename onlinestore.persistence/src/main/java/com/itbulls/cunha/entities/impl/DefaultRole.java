package com.itbulls.cunha.entities.impl;

import com.itbulls.cunha.entities.Role;

public class DefaultRole implements Role {

	private int id;
	private String roleName;

	public DefaultRole(int id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
