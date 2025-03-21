package com.itbulls.cunha.filters;

import java.io.IOException;
import java.util.stream.Collectors;

import com.itbulls.cunha.entities.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/admin/*")
public class AdminFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		User user = (User)((HttpServletRequest)request).getSession().getAttribute("user");
		if (user != null) {
			if (user.getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.toList()).contains("ROLE_ADMIN")) {
				chain.doFilter(request, response);
			} else {
				((HttpServletResponse)response).sendError(403);
			}
		} else {
			((HttpServletResponse) response).sendRedirect(request.getScheme() + "://" + request.getServerName() + ":"
					+ request.getServerPort() + request.getServletContext().getContextPath() + "/login");
		}
	}

}
