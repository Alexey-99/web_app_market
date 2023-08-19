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
	public List<OrderDetalizationByProduct> getDetailsAboutOrdersByProductPetIdAndOrderStatus(int orderStatusId,
			long productId) throws DaoException;

	/**
	 * Get the details about orders by product feed and other id and order status.
	 *
	 * @param orderStatusId the order status id
	 * @param productId     the product id
	 * @return the details about orders by product feed and other id and order
	 *         status
	 * @throws DaoException the dao exception
	 */
	public List<OrderDetalizationByProduct> getDetailsAboutOrdersByProductFeedAndOtherIdAndOrderStatus(
			int orderStatusId, long productId) throws DaoException;

	/**
	 * Get the quantity product feed and other in order by id and order status.
	 *
	 * @param orderId   the order id
	 * @param statusId  the status id
	 * @param productId the product id
	 * @return the quantity product feed and other in order by id and order status
	 * @throws DaoException the dao exception
	 */
	public long getQuantityProductFeedAndOtherInOrderByIdAndOrderStatus(long orderId, int statusId, long productId)
			throws DaoException;

	/**
	 * Get the quantity product pet in order by id and order status.
	 *
	 * @param orderId       the order id
	 * @param orderStatusId the order status id
	 * @param productId     the product id
	 * @return the quantity product pet in order by id and order status
	 * @throws DaoException the dao exception
	 */
	public long getQuantityProductPetInOrderByIdAndOrderStatus(long orderId, int orderStatusId, long productId)
			throws DaoException;

	/**
	 * Get the order without products by order id.
	 *
	 * @param orderId the order id
	 * @return the order with out products by order id
	 * @throws DaoException the dao exception
	 */
	public Order getOrderWithoutProductsByOrderId(long orderId) throws DaoException;
}