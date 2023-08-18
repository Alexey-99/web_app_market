package by.koroza.zoo_market.dao;

import java.util.List;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.model.entity.detalization.OrderDetalizationByProduct;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.status.OrderStatus;

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

	/**
	 * Check if is have open order by user id.
	 *
	 * @param userId the user id
	 * @return true, if is have open order by user id
	 * @throws DaoException the dao exception
	 */
	public boolean isHaveOpenOrderByUserId(long userId) throws DaoException;

	/**
	 * Get the order with products by user id and order status.
	 *
	 * @param userId the user id
	 * @param status the status
	 * @return the order with products by user id and order status
	 * @throws DaoException the dao exception
	 */
	public Order getOrderWithProductsByUserIdAndOrderStatus(long userId, OrderStatus status) throws DaoException;

	/**
	 * Change order.
	 *
	 * @param order the order
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean changeOrder(Order order) throws DaoException;

	/**
	 * Get the details about orders by product id and order status.
	 *
	 * @param orderStatusId the order status id
	 * @param productId     the product id
	 * @return the details about orders by product id and order status
	 * @throws DaoException the dao exception
	 */
	public List<OrderDetalizationByProduct> getDetailsAboutOrdersByProductIdAndOrderStatus(int orderStatusId,
			long productId) throws DaoException;
}