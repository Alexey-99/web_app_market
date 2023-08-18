package by.koroza.zoo_market.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.filter.FilterFeedsAndOther;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.service.exception.ServiceException;

/**
 * The Interface ProductFeedsAndOtherService.
 */
public interface ProductFeedsAndOtherService extends ProductService {

//	/**
//	 * Get the all products feeds and other.
//	 *
//	 * @return the all products feeds and other
//	 * @throws ServiceException the service exception
//	 */
//	public List<FeedAndOther> getAllProductsFeedsAndOther() throws ServiceException;

//	/**
//	 * Get the all having products feed and other by id.
//	 *
//	 * @param productsIdMap the products id map
//	 * @return the having products feed and other by id
//	 * @throws ServiceException the service exception
//	 */
//	public List<FeedAndOther> getAllHavingProductsById(Map<String, String> productsIdMap) throws ServiceException;

	/**
	 * Get the products feed and other by filter.
	 *
	 * @param filter the filter
	 * @return the products feed and other by filter
	 * @throws ServiceException the service exception
	 */
	public List<Entry<FeedAndOther, Long>> getProductsByFilter(FilterFeedsAndOther filter) throws ServiceException;

	/**
	 * Get the all products feed and other and number of units.
	 *
	 * @return the all products feed and other and number of units
	 * @throws ServiceException the service exception
	 */
	public Map<FeedAndOther, Long> getAllProductsAndNumberOfUnits() throws ServiceException;

	/**
	 * Add the product.
	 *
	 * @param product              the product
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean addProduct(FeedAndOther product, long numberOfUnitsProduct) throws ServiceException;

	/**
	 * Get the product feed and other by id.
	 *
	 * @param id the id
	 * @return the product feed and other by id
	 * @throws ServiceException the service exception
	 */
	public FeedAndOther getProductById(long id) throws ServiceException;

	/**
	 * Update product by id.
	 *
	 * @param product              the product
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean updateProductById(FeedAndOther product, long numberOfUnitsProduct) throws ServiceException;

//	/**
//	 * Get the product image path by product id.
//	 *
//	 * @param id the id
//	 * @return the product image path by id
//	 * @throws ServiceException the service exception
//	 */
//	public String getProductImagePathByProductId(long id) throws ServiceException;
//
//	/**
//	 * Transfer feed and other product from market to order.
//	 *
//	 * @param productId the product id
//	 * @param orderId   the order id
//	 * @return true, if successful
//	 * @throws ServiceException the service exception
//	 */
//	public boolean transferProductFromMarketToOrder(long productId, long orderId) throws ServiceException;
//
//	/**
//	 * Transfer feeds and other product from order to market.
//	 *
//	 * @param productId the product id
//	 * @param orderId   the order id
//	 * @return true, if successful
//	 * @throws ServiceException the service exception
//	 */
//	public boolean transferProductFromOrderToMarket(long productId, long orderId) throws ServiceException;
}