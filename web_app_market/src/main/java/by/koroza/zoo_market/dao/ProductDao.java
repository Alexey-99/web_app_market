package by.koroza.zoo_market.dao;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;

/**
 * The Interface ProductDao.
 */
public interface ProductDao {

	/**
	 * Exists product with image path.
	 *
	 * @param imagePath the image path
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean existsProductWithImagePath(String imagePath) throws DaoException;

	/**
	 * Get the product image path by id.
	 *
	 * @param id the id
	 * @return the product image path by id
	 * @throws DaoException the dao exception
	 */
	public String getProductImagePathById(long id) throws DaoException;

	/**
	 * Transfer product from market to order.
	 *
	 * @param productId the product id
	 * @param orderId   the order id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean transferProductFromMarketToOrder(long productId, long orderId) throws DaoException;

	/**
	 * Transfer product from order to market.
	 *
	 * @param productId the product id
	 * @param orderId   the order id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean transferProductFromOrderToMarket(long productId, long orderId) throws DaoException;

	/**
	 * Get the free number of units by product id.
	 *
	 * @param productId the product id
	 * @return the free number of units by product id
	 * @throws DaoException the dao exception
	 */
	public long getFreeNumberOfUnitsByProductId(long productId) throws DaoException;

	/**
	 * Get the quantity in orders by product id and order status.
	 *
	 * @param productId     the product id
	 * @param orderStatusId the order status id
	 * @return the quantity in orders by product id and order status
	 * @throws DaoException the dao exception
	 */
	public long getQuantityInOrdersByProductIdAndOrderStatus(long productId, int orderStatusId) throws DaoException;

	/**
	 * Add the product to order.
	 *
	 * @param orderId   the order id
	 * @param productId the product id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean addProductToOrder(long orderId, long productId) throws DaoException;

	/**
	 * Delete product from order.
	 *
	 * @param orderId   the order id
	 * @param productId the product id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean deleteProductFromOrder(long orderId, long productId) throws DaoException;
}