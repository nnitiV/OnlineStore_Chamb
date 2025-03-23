package com.itbulls.cunha.springjdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.itbulls.cunha.entities.Role;
import com.itbulls.cunha.springjdbc.rowmappers.PrivilegeDao;

public class RoleRowMapper implements RowMapper<Role>  {

	@Autowired
	private PrivilegeDao privilegeDao;
	
	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		Role role = new Role();
		role.setId(rs.getLong("id"));
		role.setRoleName(rs.getString("role_name"));
		role.setPrivileges(privilegeDao.getAllPrivilegesWithRoleId(rs.getLong("id")));
		return null;
	}
}
