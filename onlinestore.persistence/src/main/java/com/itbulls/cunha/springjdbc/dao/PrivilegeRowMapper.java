package com.itbulls.cunha.springjdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.itbulls.cunha.entities.Privilege;

public class PrivilegeRowMapper implements RowMapper<Privilege>  {

	@Override
	public Privilege mapRow(ResultSet rs, int rowNum) throws SQLException {
		Privilege privilege = new Privilege();
		privilege.setId(rs.getLong("id"));
		privilege.setPrivilegeName(rs.getString("privilege_name"));
		return privilege;
	}

}
