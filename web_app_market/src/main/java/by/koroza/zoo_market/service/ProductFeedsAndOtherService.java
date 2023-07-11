package by.koroza.zoo_market.service;

import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.model.entity.filter.FilterFeedsAndOther;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.service.exception.ServiceException;

/**
 * The Interface ProductFeedsAndOtherService.
 */
public interface ProductFeedsAndOtherService {

	/**
	 * Get the all products feeds and other.
	 *
	 * @return the all products feeds and other
	 * @throws ServiceException the service exception
	 */
	public List<FeedAndOther> getAllProductsFeedsAndOther() throws ServiceException;

	/**
	 * Get the all having products feed and other by id.
	 *
	 * @param productsIdMap the products id map
	 * @return the having products feed and other by id
	 * @throws ServiceException the service exception
	 */
	public List<FeedAndOther> getHavingProductsFeedAndOtherById(Map<String, String> productsIdMap)
			throws ServiceException;

	/**
	 * Get the products feed and other by filter.
	 *
	 * @param filter the filter
	 * @return the products feed and other by filter
	 * @throws ServiceException the service exception
	 */
	public List<FeedAndOther> getProductsFeedAndOtherByFilter(FilterFeedsAndOther filter) throws ServiceException;

	/**
	 * Get the all products feed and other and number of units.
	 *
	 * @return the all products feed and other and number of units
	 * @throws ServiceException the service exception
	 */
	public Map<FeedAndOther, Long> getAllProductsFeedAndOtherAndNumberOfUnits() throws ServiceException;

	/**
	 * Change number of units products minus.
	 *
	 * @param productsFeedAndOther the products feed and other
	 * @return the map
	 * @throws ServiceException the service exception
	 */
	public Map<Integer, Boolean> changeNumberOfUnitsProductsMinus(List<FeedAndOther> productsFeedAndOther)
			throws ServiceException;

	/**
	 * Change number of units products plus.
	 *
	 * @param productsFeedAndOther the products feed and other
	 * @param haveProductByIndex   the have product by index
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean changeNumberOfUnitsProductsPlus(List<FeedAndOther> productsFeedAndOther,
			Map<Integer, Boolean> haveProductByIndex) throws ServiceException;

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
	public FeedAndOther getProductFeedAndOtherById(long id) throws ServiceException;

	/**
	 * Update product by id.
	 *
	 * @param product              the product
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean updateProductById(FeedAndOther product, long numberOfUnitsProduct) throws ServiceException;

	/**
	 * Get the product image path by product id.
	 *
	 * @param id the id
	 * @return the product image path by id
	 * @throws ServiceException the service exception
	 */
	public String getProductImagePathByProductId(long id) throws ServiceException;

}