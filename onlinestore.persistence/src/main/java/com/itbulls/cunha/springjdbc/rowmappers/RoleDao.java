package com.itbulls.cunha.springjdbc.rowmappers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.entities.Role;
import com.itbulls.cunha.springjdbc.dao.RoleRowMapper;

@Repository
public class RoleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Set<Role> getRoleById(Long roleId) {
		return Set.of(jdbcTemplate.queryForObject("SELECT * FROM role WHERE role.id = ?", new Object[] { roleId },
				new int[] { java.sql.Types.INTEGER }, new RoleRowMapper()));
	}

	public Role getRoleByName(String roleName) {
		return jdbcTemplate.queryForObject("SELECT * FROM role r WHERE r.role_name = ?", new Object[] { roleName },
				new RoleRowMapper());
	}
}
