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

	/**
	 * Get the products by filter.
	 *
	 * @param filter the filter
	 * @return the products by filter
	 * @throws ServiceException the service exception
	 */
	public List<Entry<FeedAndOther, Long>> getProductsByFilter(FilterFeedsAndOther filter) throws ServiceException;

	/**
	 * Get the all products and number of units.
	 *
	 * @return the all products and number of units
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
	 * Get the product by id.
	 *
	 * @param id the id
	 * @return the product by id
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
}