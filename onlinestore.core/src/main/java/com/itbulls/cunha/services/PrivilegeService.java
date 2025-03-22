package com.itbulls.cunha.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.entities.Privilege;
import com.itbulls.cunha.repositories.PrivilegeJpaRepository;

@Service
public class PrivilegeService {
	
	@Autowired
	private PrivilegeJpaRepository privilegeJpaRepository;
	
	@Transactional
	public List<Privilege> getAllPrivileges() {
		return (List<Privilege>) privilegeJpaRepository.findAll();
	}
	
	@Transactional
	public Privilege getPrivilegeByName(String privilegeName) {
		return privilegeJpaRepository.findByPrivilegeName(privilegeName);
	}
	
	@Transactional
	public boolean addPrivilege(Privilege privilege) {
		return privilegeJpaRepository.save(privilege) != null;
	}
	
	@Transactional
	public boolean updatePrivilege(Privilege privilege) {
		return privilegeJpaRepository.save(privilege) != null;
	}
	
	@Transactional
	public void addPrivilege(long privilegeId) {
		privilegeJpaRepository.deleteById(privilegeId);
	}
}
