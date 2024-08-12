package com.itbulls.learnit.cunha.javacore.examsection43;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.User;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.impl.DefaultUser;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.ResetPasswordService;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.impl.DefaultResetPasswordService;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.utils.mail.MailSender;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.utils.validation.Validator;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.utils.validation.impl.DefaultValidator;

@ExtendWith(MockitoExtension.class)
public class UserTest {

	@Mock
	private MailSender mailSenderMock;

	@InjectMocks
	private ResetPasswordService resetPasswordService = new DefaultResetPasswordService();

	@Captor
	private ArgumentCaptor<String> captor;
	
	@Test
	void shouldSendMessageToUserEmail() {
		User user = new DefaultUser();
		String userEmail = "user@gmail.com";
		user.setEmail(userEmail);

		resetPasswordService.resetPasswordForUser(user);
		verify(mailSenderMock).sendEmail(captor.capture(), any());
		assertEquals(userEmail, captor.getValue());
	}

	@Test
	void shouldSendPasswordInfo() {
		User user = new DefaultUser();
		String userPassword = "user@gmail.com";
		user.setPassword(userPassword);

		resetPasswordService.resetPasswordForUser(user);
		verify(mailSenderMock).sendEmail(any(), captor.capture());
		assertEquals(userPassword, captor.getValue().split(":")[1].trim());
	}

}
