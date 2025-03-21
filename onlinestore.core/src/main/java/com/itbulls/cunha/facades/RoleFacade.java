package com.itbulls.cunha.facades;

import com.itbulls.cunha.entities.Role;

public interface RoleFacade {

	Role findByName(String roleName);
}
