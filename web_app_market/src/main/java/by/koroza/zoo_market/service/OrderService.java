package by.koroza.zoo_market.service;

import java.util.List;

import by.koroza.zoo_market.model.entity.detalization.OrderDetalizationByProduct;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.service.exception.ServiceException;

/**
 * The Interface OrderService.
 */
public interface OrderService {

	/**
	 * Get the order with products by user id.
	 *
	 * @param userId the user id
	 * @return the order products by user id
	 * @throws ServiceException the service exception
	 */
	public List<Order> getOrderProductsByUserId(long userId) throws ServiceException;

	/**
	 * Add the order with products.
	 *
	 * @param order  the order
	 * @param userId the user id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean addOrder(Order order, long userId) throws ServiceException;

	/**
	 * Change order.
	 *
	 * @param order the order
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean changeOrder(Order order) throws ServiceException;

	/**
	 * Get the open order by user id.
	 *
	 * @param userId the user id
	 * @return the open order by user id
	 * @throws ServiceException the service exception
	 */
	public Order getOpenOrderByUserId(long userId) throws ServiceException;

	/**
	 * Calculation total payment with discount amount.
	 *
	 * @param order                   the order
	 * @param personalDiscountPercent the personal discount percent
	 * @return the double
	 */
	public double calcTotalPaymentWithDiscountAmount(Order order, double personalDiscountPercent);

	/**
	 * Get the details about orders by product id and order status.
	 *
	 * @param orderStatusId the order status id
	 * @param productId     the product id
	 * @return the details about orders by product id and order status
	 * @throws ServiceException the service exception
	 */
	public List<OrderDetalizationByProduct> getDetailsAboutOrdersByProductPetIdAndOrderStatus(int orderStatusId,
			long productId) throws ServiceException;

	/**
	 * Get the details about orders by product feed and other id and order status.
	 *
	 * @param orderStatusId the order status id
	 * @param productId     the product id
	 * @return the details about orders by product feed and other id and order
	 *         status
	 * @throws ServiceException the service exception
	 */
	public List<OrderDetalizationByProduct> getDetailsAboutOrdersByProductFeedAndOtherIdAndOrderStatus(
			int orderStatusId, long productId) throws ServiceException;

	/**
	 * Change quantity product feed and other in order by order id.
	 *
	 * @param orderId       the order id
	 * @param orderStatusId the order status id
	 * @param productId     the product id
	 * @param quantity      the quantity
	 * @param userDiscount  the user discount
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean changeQuantityProductFeedAndOtherInOrderByOrderId(long orderId, int orderStatusId, long productId,
			long quantity, double userDiscount) throws ServiceException;

	/**
	 * Change quantity product pet in order by order id.
	 *
	 * @param orderId       the order id
	 * @param orderStatusId the order status id
	 * @param productId     the product id
	 * @param quantity      the quantity
	 * @param userDiscount  the user discount
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean changeQuantityProductPetInOrderByOrderId(long orderId, int orderStatusId, long productId,
			long quantity, double userDiscount) throws ServiceException;

	/**
	 * Get the order without products by order id.
	 *
	 * @param orderId the order id
	 * @return the order without products by order id
	 * @throws ServiceException the service exception
	 */
	public Order getOrderWithoutProductsByOrderId(long orderId) throws ServiceException;
}