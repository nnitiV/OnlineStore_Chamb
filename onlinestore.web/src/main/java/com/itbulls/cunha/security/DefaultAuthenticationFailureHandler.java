package com.itbulls.cunha.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {

	public static String UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY = "unsuccessfull_login_count";
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String contextPath = request.getServletContext().getContextPath();
		request.getSession().setAttribute("error", "Exception: " + exception.getMessage());
		Integer failedLoginCounter = (Integer) session.getAttribute(UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY);
		session.setAttribute(UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY, failedLoginCounter == null ? 1 : ++failedLoginCounter);
		response.sendRedirect(contextPath + "/login");
	}

}
