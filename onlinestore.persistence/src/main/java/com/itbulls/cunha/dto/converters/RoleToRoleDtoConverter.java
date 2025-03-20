package com.itbulls.cunha.dto.converters;

import org.springframework.stereotype.Service;

import com.itbulls.cunha.dto.RoleDTO;
import com.itbulls.cunha.entities.Role;
import com.itbulls.cunha.entities.impl.DefaultRole;

@Service
public class RoleToRoleDtoConverter {
	public Role convertRoleDtoToRole(RoleDTO roleDTO) {
		return new DefaultRole(roleDTO.getId(), roleDTO.getRoleName());
	}
	
	public RoleDTO convertRoleToRoleDto(Role role) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(role.getId());
		roleDTO.setRoleName(role.getRoleName());
		return roleDTO;
	}
}
