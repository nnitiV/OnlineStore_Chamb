package com.itbulls.cunha.springjdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.springjdbc.rowmappers.RoleDao;
import com.itbulls.cunha.springjdbc.rowmappers.UserDao;

public class UserRowMapper implements RowMapper<User> {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setEmail(rs.getString("email"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setEnabled(rs.getBoolean("enabled"));
		user.setCredit(rs.getDouble("credit"));
		user.setCreditCardNumber(rs.getString("credit_card_number"));
		user.setPartnerCode(rs.getString("partner_code"));
		user.setReferralUser(userDao.getUserById(rs.getLong("referral_user")));
		user.setRoles(roleDao.getRoleById(rs.getLong("user_role_id")));
		return user;
	}

}
