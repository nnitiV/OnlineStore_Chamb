package com.itbulls.cunha.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.facades.UserFacade;
import com.itbulls.cunha.security.DefaultAuthenticationSuccessHandler;

@WebFilter("/*")
public class RememberMeFilter implements Filter {

	private UserFacade userFacade;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		if (session.getAttribute(DefaultAuthenticationSuccessHandler.LOGGED_IN_USER_ATTR) == null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			System.out.println("Authentication: " + authentication);
			
			if (!(authentication instanceof AnonymousAuthenticationToken)) {
			    User user = userFacade.getUserByEmail(authentication.getName());
			    if (user != null) {
			    	session.setAttribute(DefaultAuthenticationSuccessHandler.LOGGED_IN_USER_ATTR, user);
			    }
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		 ApplicationContext ctx = WebApplicationContextUtils
			      .getRequiredWebApplicationContext(filterConfig.getServletContext());
		 this.userFacade = ctx.getBean(UserFacade.class);
	}

	@Override
	public void destroy() {
	}

}
