package by.koroza.zoo_market.web.filter;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PREVIOUS_COMMAND;

import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SET_RUSSIAN_LOCALE;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SET_ENGLISH_LOCALE;

import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_COMMAND;

import static by.koroza.zoo_market.web.command.name.ServletName.MAIN_SERVLET_CONTROLLER_NAME;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class PreviousPageFilter
 */
@SuppressWarnings("serial")
@WebFilter(filterName = "/PreviousPageFilter", urlPatterns = { "/" + MAIN_SERVLET_CONTROLLER_NAME })
public class PreviousPageFilter extends HttpFilter implements Filter {

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		if (!request.getParameter(PARAMETER_COMMAND).equals(COMMAND_SET_RUSSIAN_LOCALE)
				&& !request.getParameter(PARAMETER_COMMAND).equals(COMMAND_SET_ENGLISH_LOCALE)) {
			session.setAttribute(ATTRIBUTE_PREVIOUS_COMMAND, request.getParameter(PARAMETER_COMMAND));
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
	}
}