package com.itbulls.cunha.dto.converters;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.dto.PrivilegeDTO;
import com.itbulls.cunha.entities.Privilege;
import com.itbulls.cunha.entities.impl.DefaultPrivilege;

@Service
public class PrivilegeDtoToPrivilegeConverter {

	@Autowired
	private RoleToRoleDtoConverter roleConverter;
	
	public Privilege convertPrivilegeDtoToPrivilege(PrivilegeDTO privilegeDto) {
		return new DefaultPrivilege(privilegeDto.getId(), privilegeDto.getPrivilegeName(),
				roleConverter.converListRoleDtoToListRole(privilegeDto.getRoles()));
	}

	public PrivilegeDTO convertPrivilegeToPrivilegeDto(Privilege privilege) {
		PrivilegeDTO privilegeDto = new PrivilegeDTO();
		privilegeDto.setId(privilege.getId());
		privilegeDto.setPrivilegeName(privilege.getPrivilegeName());
		privilegeDto.setRoles(roleConverter.convertListRoleToListRoleDto(privilege.getRoles()));
		return privilegeDto;
	}

	public Set<Privilege> convertListPrivilegeDtoToPrivilege(Set<PrivilegeDTO> list) {
		Set<Privilege> privileges = new HashSet<>();
		list.stream().forEach(privilege -> privileges.add(convertPrivilegeDtoToPrivilege(privilege)));
		return privileges;
	}

	public Set<PrivilegeDTO> convertListPrivilegeToListPrivilegeDto(Set<Privilege> privileges) {
		Set<PrivilegeDTO> privilegesDto = new HashSet<>();
		privileges.stream().forEach(privilege -> privilegesDto.add(convertPrivilegeToPrivilegeDto(privilege)));
		return privilegesDto;
	}

}
