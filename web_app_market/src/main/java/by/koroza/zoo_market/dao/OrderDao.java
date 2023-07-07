package by.koroza.zoo_market.dao;

import java.util.List;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.model.entity.market.order.Order;

/**
 * The Interface OrderDao.
 */
public interface OrderDao {

	/**
	 * Get the order with products by user id.
	 *
	 * @param userId the user id
	 * @return the order products by user id
	 * @throws DaoException the dao exception
	 */
	public List<Order> getOrderProductsByUserId(long userId) throws DaoException;

	/**
	 * Add the order with products by user id.
	 *
	 * @param order  the order
	 * @param userId the user id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean addOrder(Order order, long userId) throws DaoException;
}