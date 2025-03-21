package com.itbulls.cunha.entities;

import java.util.Set;

public interface Privilege {

	String getPrivilegeName();
	Set<Role> getRoles();
	int getId();
	
	void setRoles(Set<Role> roles);
}
