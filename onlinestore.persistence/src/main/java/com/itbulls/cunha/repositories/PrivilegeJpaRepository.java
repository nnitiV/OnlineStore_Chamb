package com.itbulls.cunha.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.entities.Privilege;

@Repository
public interface PrivilegeJpaRepository extends CrudRepository<Privilege, Long>{
	Privilege findByPrivilegeName(String privilegeName);
}
