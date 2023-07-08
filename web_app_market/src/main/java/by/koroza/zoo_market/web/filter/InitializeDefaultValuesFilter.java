package by.koroza.zoo_market.web.filter;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;

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
 * Servlet Filter implementation class InitializeDefaultValuesFilter.
 */
@SuppressWarnings("serial")
@WebFilter(filterName = "/InitializeDefaultValuesFilter", urlPatterns = { "/*" }, initParams = {
		@WebInitParam(name = ATTRIBUTE_SESSION_LOCALE, value = RUSSIAN, description = "Default language") })
public class InitializeDefaultValuesFilter extends HttpFilter implements Filter {

	/** The locale. */
	String locale;

	/**
	 * Init the.
	 *
	 * @param fConfig the f config
	 * @throws ServletException the servlet exception
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.locale = fConfig.getInitParameter(ATTRIBUTE_SESSION_LOCALE);
	}

	/**
	 * Do filter.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param chain    the chain
	 * @throws IOException      Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		if (session.getAttribute(ATTRIBUTE_SESSION_LOCALE) == null
				|| (!session.getAttribute(ATTRIBUTE_SESSION_LOCALE).equals(RUSSIAN)
						&& !session.getAttribute(ATTRIBUTE_SESSION_LOCALE).equals(ENGLISH))) {
			session.setAttribute(ATTRIBUTE_SESSION_LOCALE, RUSSIAN);
		}
		chain.doFilter(request, response);
	}

	/**
	 * Destroy.
	 *
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		this.locale = null;
	}
}