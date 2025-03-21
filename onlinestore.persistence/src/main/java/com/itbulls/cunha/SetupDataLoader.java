package com.itbulls.cunha;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.itbulls.cunha.dao.PrivilegeDAO;
import com.itbulls.cunha.dao.RoleDAO;
import com.itbulls.cunha.dao.UserDAO;
import com.itbulls.cunha.dto.PrivilegeDTO;
import com.itbulls.cunha.dto.RoleDTO;
import com.itbulls.cunha.dto.UserDTO;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	public static final String DELETE_PRIVILEGE = "DELETE_PRIVILEGE";
	public static final String WRITE_PRIVILEGE = "WRITE_PRIVILEGE";
	public static final String READ_PRIVILEGE = "READ_PRIVILEGE";
	public static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_MANAGER = "ROLE_MANAGER";

	private boolean isAlreadyConfigured;

	@Autowired
	private UserDAO userDao;
	@Autowired
	private RoleDAO roleDao;
	@Autowired
	private PrivilegeDAO privilegeDao;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (isAlreadyConfigured) {
			return;
		}

		PrivilegeDTO readPrivilege = createPrivilegeIfNotFound(READ_PRIVILEGE);
		PrivilegeDTO writePrivilege = createPrivilegeIfNotFound(WRITE_PRIVILEGE);
		PrivilegeDTO deletePrivilege = createPrivilegeIfNotFound(DELETE_PRIVILEGE);

		Set<PrivilegeDTO> adminPrivileges = Set.of(readPrivilege, writePrivilege, deletePrivilege);
		Set<PrivilegeDTO> managerPrivileges = Set.of(readPrivilege, writePrivilege);
		
		createRoleIfNotFound(ROLE_ADMIN, adminPrivileges);
		createRoleIfNotFound(ROLE_MANAGER, managerPrivileges);
		createRoleIfNotFound(ROLE_CUSTOMER, Set.of(readPrivilege));

		RoleDTO adminRole = roleDao.getRoleByName(ROLE_ADMIN);
		RoleDTO managerRole = roleDao.getRoleByName(ROLE_MANAGER);
		
		createUserIfNotFound("admin@test.com", adminRole, "admin");
		createUserIfNotFound("manager@test.com", managerRole, "manager");

		isAlreadyConfigured = true;
	}

	@Transactional
	private void createUserIfNotFound(String email, RoleDTO role, String password) {
		UserDTO user = userDao.getUserByEmail(email);
		if (user == null) {
			user = new UserDTO();
			user.setFirstName("Admin");
			user.setLastName("Admin");
			user.setPassword(passwordEncoder.encode(password));
			user.setEmail(email);
			user.setRoleDTO(Set.of(role));
			user.setEnabled(true);
			userDao.saveUser(user);
		}
	}

	@Transactional
	private PrivilegeDTO createPrivilegeIfNotFound(String name) {

		PrivilegeDTO privilege = privilegeDao.getPrivilegeByName(name);
		if (privilege == null) {
			privilege = new PrivilegeDTO(name);
			privilegeDao.save(privilege);
		}
		return privilege;
	}

	@Transactional
	private RoleDTO createRoleIfNotFound(String name, Set<PrivilegeDTO> privileges) {

		RoleDTO role = roleDao.getRoleByName(name);
		if (role == null) {
			role = new RoleDTO();
			role.setRoleName(name);
			role.setPrivileges(privileges);
			roleDao.save(role);
		}
		return role;
	}

}
