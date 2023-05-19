package by.koroza.zoo_market.web.filter;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_IS_HAVING_REGISTRATED_USER;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_SESSION_LOCALE;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class InitializeDefaultValuesFilter
 */
@SuppressWarnings("serial")
@WebFilter(filterName = "/InitializeDefaultValuesFilter", urlPatterns = { "/*" }, initParams = {
		@WebInitParam(name = ATTRIBUTE_SESSION_LOCALE, value = "en", description = "Default language"),
		@WebInitParam(name = ATTRIBUTE_IS_HAVING_REGISTRATED_USER, value = "false", description = "User is not authenticated") })
public class InitializeDefaultValuesFilter extends HttpFilter implements Filter {
	private static final String ENGLISH_LANGUAGE = "en";
	String locale;
	boolean isRegistratedUser;

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.locale = fConfig.getInitParameter(ATTRIBUTE_SESSION_LOCALE);
		this.isRegistratedUser = Boolean.parseBoolean(fConfig.getInitParameter(ATTRIBUTE_IS_HAVING_REGISTRATED_USER));
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("InitializeDefaultValuesFilter.doFilter");
		HttpServletRequest HttpServletRequest = (HttpServletRequest) request;
		HttpSession session = HttpServletRequest.getSession();
		if (session.getAttribute(ATTRIBUTE_SESSION_LOCALE) == null) {
			session.setAttribute(ATTRIBUTE_SESSION_LOCALE, ENGLISH_LANGUAGE);
		}
		if (session.getAttribute(ATTRIBUTE_IS_HAVING_REGISTRATED_USER) == null) {
			session.setAttribute(ATTRIBUTE_IS_HAVING_REGISTRATED_USER, false);
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		this.locale = null;
		this.isRegistratedUser = false;
	}
}