package com.itbulls.cunha.dto.converters;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.dto.RoleDTO;
import com.itbulls.cunha.entities.Role;
import com.itbulls.cunha.entities.impl.DefaultRole;

@Service
public class RoleToRoleDtoConverter {

	@Autowired
	private PrivilegeDtoToPrivilegeConverter privilegeConverter;

	public Role convertRoleDtoToRole(RoleDTO roleDTO) {
		return new DefaultRole(roleDTO.getId(), roleDTO.getRoleName(),
				privilegeConverter.convertListPrivilegeDtoToPrivilege(roleDTO.getPrivileges()));
	}

	public RoleDTO convertRoleToRoleDto(Role role) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(role.getId());
		roleDTO.setRoleName(role.getRoleName());
		roleDTO.setPrivileges(privilegeConverter.convertListPrivilegeToListPrivilegeDto(role.getPrivileges()));
		return roleDTO;
	}

	public Set<Role> converListRoleDtoToListRole(Set<RoleDTO> rolesDto) {
		Set<Role> roles = new HashSet<>();
		rolesDto.forEach(role -> roles.add(convertRoleDtoToRole(role)));
		return roles;
	}

	public Set<RoleDTO> convertListRoleToListRoleDto(Set<Role> roles) {
		Set<RoleDTO> rolesDto = new HashSet<>();
		roles.stream().forEach(role -> convertRoleToRoleDto(role));
		return rolesDto;
	}
}
