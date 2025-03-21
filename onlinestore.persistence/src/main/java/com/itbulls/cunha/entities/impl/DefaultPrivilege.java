package com.itbulls.cunha.entities.impl;

import java.util.List;
import java.util.Set;

import com.itbulls.cunha.entities.Privilege;
import com.itbulls.cunha.entities.Role;

public class DefaultPrivilege implements Privilege {

	private int id;
	private String privilegeName;
	private Set<Role> roles;

	public DefaultPrivilege(int id, String privilegeName) {
		this.id = id;
		this.privilegeName = privilegeName;
	}

	public DefaultPrivilege(int id, String privilegeName, Set<Role> list) {
		this.id = id;
		this.privilegeName = privilegeName;
		this.roles = list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	@Override
	public String getPrivilegeName() {
		return this.privilegeName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Privilege [id=" + id + ", privilegeName=" + privilegeName + "]";
	}
}
