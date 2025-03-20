package com.itbulls.cunha.services.impl;

import org.springframework.stereotype.Service;

import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.services.ResetPasswordService;
import com.itbulls.cunha.utils.mail.MailSender;

@Service
public class DefaultResetPasswordService implements ResetPasswordService {

	private MailSender mailSender;

	@Override
	public void resetPasswordForUser(User user) {
		System.out.println("Your password has been sent to your email. Check your inbox.");
		mailSender.sendEmail(user.getEmail(), "Please, use this password to login: " + user.getPassword());
	}

}
