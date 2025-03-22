package com.itbulls.cunha.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.persistence.entities.Privilege;
import com.itbulls.cunha.persistence.repositories.PrivilegeJpaRepository;

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
