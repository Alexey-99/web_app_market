package by.koroza.zoo_market.web.filter;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PREVIOUS_COMMAND;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_BUFFER_CHANGE_USER_STATUS_LOGIN;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_BUFFER_CHANGE_USER_STATUS_ROLE_ID;

import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SHOW_HOME_PAGE;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SHOW_BACKET_PAGE;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_HISTORY_ORDERS_PAGE;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_ADMIN_PAGE;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SHOW_PRODUCT_PETS_OFF_FILTER_PAGE;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SHOW_PRODUCT_PETS_INCLUDED_FILTER_PAGE;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SHOW_PRODUCT_FEED_AND_OTHER_OFF_FILTER_PAGE;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SHOW_PRODUCT_FEEDS_AND_OTHER_INCLUDED_FILTER_PAGE;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SHOW_MAKET_PAGE_PRODUCT_PETS_BY_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SHOW_MAKET_PAGE_PRODUCT_FEEDS_AND_OTHER_BY_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_PRODUCTS_OFF_FILTER;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_ID_ASCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_ID_DESCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPE_ASCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPE_DESCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_ASCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_DESCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DESCRIPTION_ASCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DESCRIPTION_DESCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPES_PETS_ASCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPES_PETS_DESCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_TYPE_ASCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_TYPE_DESCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BREED_ASCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BREED_DESCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BIRTH_DATE_ASCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BIRTH_DATE_DESCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PRICE_ASCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PRICE_DESCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DISCOUNT_ASCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DISCOUNT_DESCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TOTAL_PRICE_ASCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TOTAL_PRICE_DESCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_NUMBER_OF_UNITS_ASCENDING;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_NUMBER_OF_UNITS_DESCENDING;

import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_COMMAND;

import static by.koroza.zoo_market.web.command.name.ServletName.MAIN_SERVLET_CONTROLLER_NAME;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
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
	private static final String[] PAGE_COMMAND_NAMES = new String[] { COMMAND_SHOW_HOME_PAGE, COMMAND_SHOW_BACKET_PAGE,
			COMMAND_SHOW_PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE, COMMAND_SHOW_PERSONAL_ACCOUNT_HISTORY_ORDERS_PAGE,
			COMMAND_SHOW_PERSONAL_ACCOUNT_ADMIN_PAGE, COMMAND_SHOW_PRODUCT_PETS_OFF_FILTER_PAGE,
			COMMAND_SHOW_PRODUCT_PETS_INCLUDED_FILTER_PAGE, COMMAND_SHOW_PRODUCT_FEED_AND_OTHER_OFF_FILTER_PAGE,
			COMMAND_SHOW_PRODUCT_FEEDS_AND_OTHER_INCLUDED_FILTER_PAGE,
			COMMAND_SHOW_MAKET_PAGE_PRODUCT_PETS_BY_NUMBER_PAGE,
			COMMAND_SHOW_MAKET_PAGE_PRODUCT_FEEDS_AND_OTHER_BY_NUMBER_PAGE, COMMAND_ADMIN_PAGE_SHOW_PRODUCTS_OFF_FILTER,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_ID_ASCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_ID_DESCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPE_ASCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPE_DESCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_ASCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_DESCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DESCRIPTION_ASCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DESCRIPTION_DESCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPES_PETS_ASCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPES_PETS_DESCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_TYPE_ASCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_TYPE_DESCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BREED_ASCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BREED_DESCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BIRTH_DATE_ASCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BIRTH_DATE_DESCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PRICE_ASCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PRICE_DESCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DISCOUNT_ASCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DISCOUNT_DESCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TOTAL_PRICE_ASCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TOTAL_PRICE_DESCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_NUMBER_OF_UNITS_ASCENDING,
			COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_NUMBER_OF_UNITS_DESCENDING, };

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		String commandName = request.getParameter(PARAMETER_COMMAND);
		if (isNeedSaveCommand(commandName)) {
			session.setAttribute(ATTRIBUTE_PREVIOUS_COMMAND, commandName);
			removeAtributes(session);
		}
		chain.doFilter(request, response);
	}

	private boolean isNeedSaveCommand(String commandName) {
		boolean result = false;
		for (String command : PAGE_COMMAND_NAMES) {
			if (commandName.equalsIgnoreCase(command)) {
				result = true;
			}
		}
		return result;
	}

	private void removeAtributes(HttpSession session) {
		session.removeAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER);
		session.removeAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT);
		session.removeAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET);
		session.removeAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT);
		session.removeAttribute(ATTRIBUTE_BUFFER_CHANGE_USER_STATUS_LOGIN);
		session.removeAttribute(ATTRIBUTE_BUFFER_CHANGE_USER_STATUS_ROLE_ID);
	}
}