package com.proiect;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.user.User;

//filtrul care nu permite accesul clientilor neinregistrati
public class LoginFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);

	public static final String LOGIN_PAGE = "/login";

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {

	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

		User userManager = (User) httpServletRequest.getSession().getAttribute("user");

		if (userManager != null) {

			filterChain.doFilter(servletRequest, servletResponse);

		} else {
			LOGGER.debug("userManager not found");
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + LOGIN_PAGE);
		}
	}
}
