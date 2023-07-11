package by.koroza.zoo_market.web.listener;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.model.entity.status.OrderStatus.PROCESSING;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_FEED_AND_OTHER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_PETS;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ORDER;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;

import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.product.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class ListenerImpl
 *
 */
@WebListener
public class ListenerImpl implements HttpSessionListener {
	private static Logger log = LogManager.getLogger();

	/**
	 * Default constructor.
	 */
	public ListenerImpl() {
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute(ATTRIBUTE_SESSION_LOCALE, RUSSIAN);
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		Order order = (Order) se.getSession().getAttribute(ATTRIBUTE_ORDER);
		if (order != null && order.getStatus().getId() >= PROCESSING.getId()) {
			@SuppressWarnings("unchecked")
			Map<Integer, Boolean> haveProductPets = (Map<Integer, Boolean>) se.getSession()
					.getAttribute(ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_PETS);
			@SuppressWarnings("unchecked")
			Map<Integer, Boolean> haveProductFeedAndOther = (Map<Integer, Boolean>) se.getSession()
					.getAttribute(ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_FEED_AND_OTHER);
			try {
				ProductPetServiceImpl.getInstance().changeNumberOfUnitsProductsPlus(order.getProductsPets(),
						haveProductPets);
				ProductFeedsAndOtherServiceImpl.getInstance().changeNumberOfUnitsProductsPlus(order.getOtherProducts(),
						haveProductFeedAndOther);
				se.getSession().removeAttribute(ATTRIBUTE_ORDER);
				se.getSession().removeAttribute(ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_PETS);
				se.getSession().removeAttribute(ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_FEED_AND_OTHER);
			} catch (ServiceException e) {
				log.log(Level.ERROR, e.getMessage());
			}
		}
	}
}