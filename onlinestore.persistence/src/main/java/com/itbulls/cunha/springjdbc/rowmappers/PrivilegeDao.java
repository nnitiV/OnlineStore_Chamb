package com.itbulls.cunha.springjdbc.rowmappers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.entities.Privilege;
import com.itbulls.cunha.springjdbc.dao.PrivilegeRowMapper;

@Repository
public class PrivilegeDao {
	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	public Set<Privilege> getAllPrivilegesWithRoleId(Long roleId){
        return new HashSet<Privilege>(jdbcTemplate.query("SELECT p.* FROM privilege p JOIN roles_privileges rp ON p.id = rp.privilege_id WHERE rp.role_id = ?", new Object[] { roleId }, new PrivilegeRowMapper())); 
	}
	
	public Privilege getPrivilegeByName(String privilegeName) {
		return jdbcTemplate.queryForObject("SELECT * FROM privilege p WHERE p.privilege_name = ?", new Object[] { privilegeName }, new PrivilegeRowMapper());
	}
}
