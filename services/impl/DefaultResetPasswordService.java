package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.impl;

import java.util.ResourceBundle;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.User;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.ResetPasswordService;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.utils.mail.MailSender;

public class DefaultResetPasswordService implements ResetPasswordService {

	private MailSender mailSender;
	private ApplicationContext context;
	private ResourceBundle bundle;
	
	{
		context = ApplicationContext.getInstance();
		bundle = context.getBundle();
	}

	@Override
	public void resetPasswordForUser(User user) {
		mailSender.sendEmail(user.getEmail(), bundle.getString("message_sent_to_email") + user.getPassword());
	}

}
