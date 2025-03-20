package com.itbulls.cunha.filters;

import java.io.IOException;

import javax.servlet.annotation.WebFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletResponse;

public class PartnerCodeFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String partnerCode = request.getParameter("partner_code");
		if(partnerCode != null && !partnerCode.isEmpty()) {
			((HttpServletResponse)response).addCookie(new Cookie("partner_code", partnerCode));
		}
		chain.doFilter(request, response);
	}

}
