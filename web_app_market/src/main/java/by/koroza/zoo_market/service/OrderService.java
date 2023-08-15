package by.koroza.zoo_market.service;

import java.util.List;

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
}