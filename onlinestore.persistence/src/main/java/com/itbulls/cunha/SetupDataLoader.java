package com.itbulls.cunha;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.itbulls.cunha.entities.Privilege;
import com.itbulls.cunha.entities.Role;
import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.repositories.PrivilegeJpaRepository;
import com.itbulls.cunha.repositories.RoleJpaRepository;
import com.itbulls.cunha.repositories.UserJpaRepository;

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
	private UserJpaRepository userJpaRepository;
	@Autowired
	private RoleJpaRepository roleJpaRepository;
	@Autowired
	private PrivilegeJpaRepository privilegeJpaRepository;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (isAlreadyConfigured) {
			return;
		}

		Privilege readPrivilege = createPrivilegeIfNotFound(READ_PRIVILEGE);
		Privilege writePrivilege = createPrivilegeIfNotFound(WRITE_PRIVILEGE);
		Privilege deletePrivilege = createPrivilegeIfNotFound(DELETE_PRIVILEGE);

		Set<Privilege> adminPrivileges = Set.of(readPrivilege, writePrivilege, deletePrivilege);
		Set<Privilege> managerPrivileges = Set.of(readPrivilege, writePrivilege);
		
		Role adminRole = createRoleIfNotFound(ROLE_ADMIN, adminPrivileges);
		Role managerRole = createRoleIfNotFound(ROLE_MANAGER, managerPrivileges);
		Role customerRole = createRoleIfNotFound(ROLE_CUSTOMER, Set.of(readPrivilege));


		createUserIfNotFound("admin@test.com", adminRole, "admin");
		createUserIfNotFound("manager@test.com", managerRole, "manager");

		isAlreadyConfigured = true;
	}

	@Transactional
	private void createUserIfNotFound(String email, Role role, String password) {
		User user = userJpaRepository.findUserByEmail(email);
		if (user == null) {
			user = new User();
			user.setFirstName("Admin");
			user.setLastName("Admin");
			user.setPassword(passwordEncoder.encode(password));
			user.setEmail(email);
			user.setRoles(Set.of(role));
			user.setEnabled(true);
			userJpaRepository.save(user);
		}
	}

	@Transactional
	private Privilege createPrivilegeIfNotFound(String name) {

		Privilege privilege = privilegeJpaRepository.findByPrivilegeName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			privilegeJpaRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	private Role createRoleIfNotFound(String name, Set<Privilege> privileges) {

		Role role = roleJpaRepository.findRoleByRoleName(name);
		if (role == null) {
			role = new Role();
			role.setRoleName(name);
			role.setPrivileges(privileges);
			roleJpaRepository.save(role);
		}
		return role;
	}

}
