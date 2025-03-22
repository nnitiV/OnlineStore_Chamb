package com.itbulls.cunha.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.entities.Role;

@Repository
public interface RoleJpaRepository extends CrudRepository<Role, Long> {
	Role findRoleByRoleName(String roleName);
}
