package by.koroza.zoo_market.web.filter;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_INDEX_PAGE;

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
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class PageRedirectSecurityFilter
 */
@SuppressWarnings("serial")
@WebFilter(filterName = "/PageRedirectSecurityFilter", urlPatterns = { "/jsp/*" }, initParams = {
		@WebInitParam(name = ATTRIBUTE_INDEX_PAGE, value = "/index.jsp") })
public class PageRedirectSecurityFilter extends HttpFilter implements Filter {
	private String indexPath;

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.indexPath = fConfig.getInitParameter(ATTRIBUTE_INDEX_PAGE);
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendRedirect(httpRequest.getContextPath() + this.indexPath);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		this.indexPath = null;
	}
}
