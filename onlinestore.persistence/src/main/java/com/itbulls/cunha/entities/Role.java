package com.itbulls.cunha.entities;

import java.util.Set;

public interface Role {
	String getRoleName();
	Set<Privilege> getPrivileges();
	int getId();
}
