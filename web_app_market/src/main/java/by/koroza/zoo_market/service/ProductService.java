package by.koroza.zoo_market.service;

import by.koroza.zoo_market.service.exception.ServiceException;

/**
 * The Interface ProductService.
 */
public interface ProductService {

	/**
	 * Get the product image path by product id.
	 *
	 * @param id the id
	 * @return the product image path by product id
	 * @throws ServiceException the service exception
	 */
	public String getProductImagePathByProductId(long id) throws ServiceException;

	/**
	 * Transfer product from market to order.
	 *
	 * @param productId the product id
	 * @param orderId   the order id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean transferProductFromMarketToOrder(long productId, long orderId) throws ServiceException;

	/**
	 * Transfer product from order to market.
	 *
	 * @param productId the product id
	 * @param orderId   the order id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean transferProductFromOrderToMarket(long productId, long orderId) throws ServiceException;

	/**
	 * Get the free number of units by product id.
	 *
	 * @param productId the product id
	 * @return the free number of units by product id
	 * @throws ServiceException the service exception
	 */
	public long getFreeNumberOfUnitsByProductId(long productId) throws ServiceException;

	/**
	 * Get the quantity in orders by product id and order status.
	 *
	 * @param productId     the product id
	 * @param orderStatusId the order status id
	 * @return the quantity in orders by product id and order status
	 * @throws ServiceException the service exception
	 */
	public long getQuantityInOrdersByProductIdAndOrderStatus(long productId, int orderStatusId) throws ServiceException;
}