package com.itbulls.cunha.facades.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.dao.RoleDAO;
import com.itbulls.cunha.dto.converters.RoleToRoleDtoConverter;
import com.itbulls.cunha.entities.Role;
import com.itbulls.cunha.facades.RoleFacade;

@Service
public class DefaultRoleFacade implements RoleFacade {

	@Autowired
	private RoleDAO roleDao;
	@Autowired
	private RoleToRoleDtoConverter roleConverter;
	
	@Override
	public Role findByName(String roleName) {
		return roleConverter.convertRoleDtoToRole(roleDao.getRoleByName(roleName));
	}

}
