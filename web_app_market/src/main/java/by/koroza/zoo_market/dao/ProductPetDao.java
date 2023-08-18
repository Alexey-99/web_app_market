package by.koroza.zoo_market.dao;

import java.util.Map;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.model.entity.market.product.Pet;

/**
 * The Interface ProductPetDao.
 */
public interface ProductPetDao extends ProductDao {

	/**
	 * Get the all products and number of units.
	 *
	 * @return the all products and number of units
	 * @throws DaoException the dao exception
	 */
	public Map<Pet, Long> getAllProductsAndNumberOfUnits() throws DaoException;

	/**
	 * Add the product.
	 *
	 * @param pet                  the pet
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean addProduct(Pet pet, long numberOfUnitsProduct) throws DaoException;

	/**
	 * Get the product by id.
	 *
	 * @param id the id
	 * @return the product by id
	 * @throws DaoException the dao exception
	 */
	public Pet getProductById(long id) throws DaoException;

	/**
	 * Update product by id.
	 *
	 * @param pet                  the pet
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean updateProductById(Pet pet, long numberOfUnitsProduct) throws DaoException;
}