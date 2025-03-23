package com.itbulls.cunha.springjdbc.rowmappers;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.springjdbc.dao.UserRowMapper;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<User> getAllUsers() {
		return jdbcTemplate.queryForList("SELECT * FROM user", User.class);
	}

	public User getUserById(Long referralId) {
		return jdbcTemplate.queryForObject("SELECT * FROM user WHERE user.id = ?", new Object[] { referralId },
				new int[] { java.sql.Types.INTEGER }, new UserRowMapper());
	}

	public User getUserByFirstName(String firstName) {
		String searchParameterString = "%" + firstName + "%";
		return jdbcTemplate.queryForObject("SELECT * FROM user u WHERE u.firstName LIKE %?%",
				new Object[] { searchParameterString }, new UserRowMapper());
	}

	public User findByPartnerCode(String partnerCode) {
		return jdbcTemplate.queryForObject("SELECT * FROM user u WHERE u.partnerCode = ?", new Object[] { partnerCode },
				new UserRowMapper());
	}

	public User findUserByEmail(String email) {
		return jdbcTemplate.queryForObject("SELECT * FROM user u WHERE u.email = ?", new Object[] { email },
				new UserRowMapper());
	}

	public List<User> findUsersByReferralUserId(Long referralUserId) {
		return jdbcTemplate.query("SELECT * FROM user u WHERE u.referral_user = ?", new Object[] { referralUserId },
				new UserRowMapper());
	}

	public void deleteUserById(Long userId) {
		jdbcTemplate.update("DELETE FROM user u WHERE u.id = ?", userId);
	}

	public void addUser(User user) {
		String sql = "INSERT INTO user (email, first_name, last_name, enabled, credit, credit_card_number, partner_code, referral_user, user_role_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setBoolean(4, user.isEnabled());
			ps.setDouble(5, user.getCredit());
			ps.setString(6, user.getCreditCardNumber());
			ps.setString(7, user.getPartnerCode());
			ps.setObject(8, user.getReferralUser() != null ? user.getReferralUser().getId() : null);
			ps.setObject(9,
					user.getRoles() != null && !user.getRoles().isEmpty() ? user.getRoles().iterator().next().getId()
							: null);
			return ps;
		}, keyHolder);
	}

	public void updateUser(User user) {
		String sql = "UPDATE user " + "SET email = ?, " + "first_name = ?, " + "last_name = ?, " + "enabled = ?, "
				+ "credit = ?, " + "credit_card_number = ?, " + "partner_code = ?, " + "referral_user = ?, "
				+ "user_role_id = ? " + "WHERE id = ?";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setBoolean(4, user.isEnabled());
			ps.setDouble(5, user.getCredit());
			ps.setString(6, user.getCreditCardNumber());
			ps.setString(7, user.getPartnerCode());
			ps.setObject(8, user.getReferralUser() != null ? user.getReferralUser().getId() : null);
			ps.setObject(9,
					user.getRoles() != null && !user.getRoles().isEmpty() ? user.getRoles().iterator().next().getId()
							: null);
			return ps;
		}, keyHolder);
	}

}
