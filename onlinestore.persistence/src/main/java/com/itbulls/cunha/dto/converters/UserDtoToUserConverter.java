package com.itbulls.cunha.dto.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.dto.UserDTO;
import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.entities.impl.DefaultUser;

@Service
public class UserDtoToUserConverter {

	@Autowired
	private RoleToRoleDtoConverter roleConverter;

	public User convertUserDtoToUser(UserDTO userDto) {
		if (userDto == null) {
			return null;
		}

		User user = new DefaultUser(userDto.getId(), userDto.getFirstName(), userDto.getLastName(),
				userDto.getPassword(), userDto.getEmail(), userDto.getCreditCardNumber(),
				roleConverter.convertRoleDtoToRole(userDto.getRoleDTO()), userDto.getPartnerCode(),
				convertUserDtoToUser(userDto.getReferralUser()), userDto.getCredit());
		return user;
	}

	public UserDTO convertUserToUserDto(User user) {
		UserDTO userDto = new UserDTO();
		if (user != null) {
			userDto.setId(user.getId());
			userDto.setFirst_name(user.getFirstName());
			userDto.setLast_name(user.getLastName());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			userDto.setCreditCardNumber(user.getCredit_card_number());
			userDto.setRoleDTO(roleConverter.convertRoleToRoleDto(user.getRole()));
			userDto.setPartnerCode(user.getPartnerCode());
			userDto.setReferralUser(convertUserToUserDto(user.getReferralUser()));
			userDto.setCredit(user.getCredit());
			return userDto;
		}
		return null;
	}

	public List<User> convertListUsersDtoToListUser(List<UserDTO> usersDto) {
		List<User> users = new ArrayList<>();
		usersDto.stream().forEach(userDto -> {
			users.add(convertUserDtoToUser(userDto));
		});
		return users;
	}
}
