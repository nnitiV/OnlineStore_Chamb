package com.itbulls.cunha.dao;

import java.util.List;

import com.itbulls.cunha.dto.RoleDTO;

public interface RoleDAO {
	List<RoleDTO> getRoles();
	
	void save(RoleDTO roleDto);

	RoleDTO getRoleById(int id);

	RoleDTO getRoleByName(String roleName);
}
