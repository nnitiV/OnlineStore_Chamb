package com.itbulls.cunha.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.persistence.entities.Role;

@Repository
public interface RoleJpaRepository extends CrudRepository<Role, Long> {
	Role findRoleByRoleName(String roleName);
}
