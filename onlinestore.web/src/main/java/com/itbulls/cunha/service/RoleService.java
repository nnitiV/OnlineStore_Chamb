package com.itbulls.cunha.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.persistence.entities.Role;
import com.itbulls.cunha.persistence.repositories.RoleJpaRepository;

@Service
public class RoleService {

	@Autowired
	private RoleJpaRepository roleJpaRepository;
	
	@Transactional
	public List<Role> getAllRoles() {
		return (List<Role>) roleJpaRepository.findAll();
	}
	
	@Transactional
	public Role getRoleByName(String roleName) {
		return roleJpaRepository.findRoleByRoleName(roleName);
	}
	
	@Transactional
	public Role getRoleById(long roleId) {
		return (Role) roleJpaRepository.findById(roleId).orElse(null);
	}
	
	@Transactional
	public boolean addRole(Role role) {
		return roleJpaRepository.save(role) != null;
	}
	
	@Transactional
	public void deleteRole(long roleId) {
		roleJpaRepository.deleteById(roleId);
	}
	
	@Transactional 
	public boolean updateRole(Role role) {
		return roleJpaRepository.save(role) != null;
	}
}
