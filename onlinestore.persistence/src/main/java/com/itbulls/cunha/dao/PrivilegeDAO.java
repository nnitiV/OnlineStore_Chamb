package com.itbulls.cunha.dao;

import com.itbulls.cunha.dto.PrivilegeDTO;

public interface PrivilegeDAO {

	void save(PrivilegeDTO privilege);
	
	PrivilegeDTO getPrivilegeByName(String privilegeName);
}
