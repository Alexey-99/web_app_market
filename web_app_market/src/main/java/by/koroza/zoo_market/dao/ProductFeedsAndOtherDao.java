package by.koroza.zoo_market.dao;

import java.util.Map;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;

/**
 * The Interface ProductFeedsAndOtherDao.
 */
public interface ProductFeedsAndOtherDao extends ProductDao {

	/**
	 * Get the all products and number of units.
	 *
	 * @return the all products and number of units
	 * @throws DaoException the dao exception
	 */
	public Map<FeedAndOther, Long> getAllProductsAndNumberOfUnits() throws DaoException;

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
	 * Get the product by id.
	 *
	 * @param id the id
	 * @return the product by id
	 * @throws DaoException the dao exception
	 */
	public FeedAndOther getProductById(long id) throws DaoException;

	/**
	 * Update product by id.
	 *
	 * @param product              the product
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean updateProductById(FeedAndOther product, long numberOfUnitsProduct) throws DaoException;
}