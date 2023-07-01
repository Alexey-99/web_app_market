package by.koroza.zoo_market.web.filter;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ENCODING;

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

/**
 * Servlet Filter implementation class EncodingFilter
 */
@SuppressWarnings("serial")
@WebFilter(filterName = "/EncodingFilter", urlPatterns = { "/*" }, initParams = {
		@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param") })
public class EncodingFilter extends HttpFilter implements Filter {
	private static final String ENCODING_UTF_8 = "UTF-8";
	private String code;

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.code = fConfig.getInitParameter(ATTRIBUTE_ENCODING);
		if (this.code == null) {
			this.code = ENCODING_UTF_8;
		}
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String codeRequest = request.getCharacterEncoding();
		if (!this.code.equalsIgnoreCase(codeRequest)) {
			request.setCharacterEncoding(this.code);
			response.setCharacterEncoding(this.code);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		this.code = null;
	}
}