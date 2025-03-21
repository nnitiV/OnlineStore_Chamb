package com.itbulls.cunha.entities.impl;

import java.util.List;
import java.util.Set;

import com.itbulls.cunha.entities.Privilege;
import com.itbulls.cunha.entities.Role;

public class DefaultRole implements Role {

	private int id;
	private String roleName;
	private Set<Privilege> privileges;

	public DefaultRole(int id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}

	public DefaultRole(int id, String roleName, Set<Privilege> set) {
		this.id = id;
		this.roleName = roleName;
		this.privileges = set;
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

	@Override
	public Set<Privilege> getPrivileges() {
		return privileges;
	}

}
