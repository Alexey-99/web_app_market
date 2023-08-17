package by.koroza.zoo_market.web.command.impl.user.show.market.other.page;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_MAP;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.REQUEST_ATTRIBUTE_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PRODUCTS_FEED_AND_OTHER_PAGE_PATH;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.filter.FilterFeedsAndOther;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.factory.impl.MarketFilterProductFactoryImpl;
import by.koroza.zoo_market.service.impl.product.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.service.sorting.product.SortingProducts;
import by.koroza.zoo_market.service.sorting.product.impl.SortingProductsImpl;
import by.koroza.zoo_market.service.sorting.product.impl.comparator.list.product.impl.id.SortProductsByIdAscendingComparatorImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * The Class ShowProductFeedsAndOtherNumberPageCommand.
 */
public class ShowProductFeedsAndOtherNumberPageCommand implements Command {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The sort products. */
	private final SortingProducts SORT_PRODUCTS = SortingProductsImpl.getInstance();

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 * @throws CommandException the command exception
	 */
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		try {
			Map<FeedAndOther, Long> allProductsFeedAndOther = ProductFeedsAndOtherServiceImpl.getInstance()
					.getAllProductsFeedAndOtherAndNumberOfUnits();
			Map<String, Set<String>> filterMap = MarketFilterProductFactoryImpl.getInstance().createFilterFeedAndOther(
					allProductsFeedAndOther.keySet(), (String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE));
			session.setAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_MAP, filterMap);
			if (session.getAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER) != null) {
				FilterFeedsAndOther filterFeedsAndOther = (FilterFeedsAndOther) session
						.getAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER);
				List<Entry<FeedAndOther, Long>> productsByFilter = ProductFeedsAndOtherServiceImpl.getInstance()
						.getProductsFeedAndOtherByFilter(filterFeedsAndOther);
				productsByFilter = SORT_PRODUCTS.sortProductsFeedsAndOther(productsByFilter,
						new SortProductsByIdAscendingComparatorImpl());
				session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER, productsByFilter);
			} else {
				List<Entry<FeedAndOther, Long>> products = SORT_PRODUCTS.sortProductsFeedsAndOther(
						allProductsFeedAndOther.entrySet().stream().toList(),
						new SortProductsByIdAscendingComparatorImpl());
				session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER, products);
			}
		} catch (ServiceException | SortingException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		request.setAttribute(REQUEST_ATTRIBUTE_NUMBER_PAGE, request.getParameter(PARAMETER_NUMBER_PAGE));
		return new Router(PRODUCTS_FEED_AND_OTHER_PAGE_PATH);
	}
}