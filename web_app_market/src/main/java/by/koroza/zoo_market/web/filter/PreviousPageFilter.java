package by.koroza.zoo_market.web.filter;

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
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class PreviousPageFilter
 */
@SuppressWarnings("serial")
@WebFilter(filterName = "/PreviousPageFilter", urlPatterns = { "/Controller" })
public class PreviousPageFilter extends HttpFilter implements Filter {

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println(
				"------------------------------------PreviousPageFilter--------------------------------------");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String current = httpServletRequest.getRequestURI();
		String url = httpServletRequest.getHeader("referer");
		
		System.out.println("current = " + current);
		System.out.println("url = " + url);
		System.out.println("current page " + current + url);

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}
}