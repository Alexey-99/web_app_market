package by.koroza.zoo_market.dao;

import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.model.entity.filter.FilterFeedsAndOther;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;

/**
 * The Interface ProductFeedsAndOtherDao.
 */
public interface ProductFeedsAndOtherDao {

	/**
	 * Get the all having products feed and other.
	 *
	 * @return the all having products feed and other
	 * @throws DaoException the dao exception
	 */
	public List<FeedAndOther> getAllHavingProductsFeedAndOther() throws DaoException;

	/**
	 * Get the all having products feed and other by product id.
	 *
	 * @param productsIdMap the products id map
	 * @return the having products feed and other by id
	 * @throws DaoException the dao exception
	 */
	public List<FeedAndOther> getHavingProductsFeedAndOtherById(Map<String, String> productsIdMap) throws DaoException;

	/**
	 * Get the products feed and other with filter.
	 *
	 * @param filter the filter
	 * @return the products feed and other with filter
	 * @throws DaoException the dao exception
	 */
	public List<FeedAndOther> getProductsFeedAndOtherWithFilter(FilterFeedsAndOther filter) throws DaoException;

	/**
	 * Get the all products feed and other and number of units.
	 *
	 * @return the all products feed and other and number of units
	 * @throws DaoException the dao exception
	 */
	public Map<FeedAndOther, Long> getAllProductsFeedAndOtherAndNumberOfUnits() throws DaoException;

	/**
	 * Change number of units products.
	 *
	 * @param order the order
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean changeNumberOfUnitsProducts(Order order) throws DaoException;

	/**
	 * Add the product.
	 *
	 * @param product              the product
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean addProduct(FeedAndOther product, long numberOfUnitsProduct) throws DaoException;

	/**
	 * Get the product feed and other by product id.
	 *
	 * @param id the id
	 * @return the product feed and other by id
	 * @throws DaoException the dao exception
	 */
	public FeedAndOther getProductFeedAndOtherById(long id) throws DaoException;

	/**
	 * Update product by id.
	 *
	 * @param product              the product
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean updateProductById(FeedAndOther product, long numberOfUnitsProduct) throws DaoException;

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
}