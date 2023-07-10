package by.koroza.zoo_market.dao;

import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.model.entity.market.product.Pet;

/**
 * The Interface ProductPetDao.
 */
public interface ProductPetDao {

	/**
	 * Get the all having products pets.
	 *
	 * @return the all having products pets
	 * @throws DaoException the dao exception
	 */
	public List<Pet> getAllHavingProductsPets() throws DaoException;

	/**
	 * Get the products pets by id.
	 *
	 * @param productsIdMap the products id map
	 * @return the products pets by id
	 * @throws DaoException the dao exception
	 */
	public List<Pet> getProductsPetsById(Map<String, String> productsIdMap) throws DaoException;

	/**
	 * Get the all products pets and number of units.
	 *
	 * @return the all products pets and number of units
	 * @throws DaoException the dao exception
	 */
	public Map<Pet, Long> getAllProductsPetsAndNumberOfUnits() throws DaoException;

	/**
	 * Change number of units products.
	 *
	 * @param productsPets the products pets
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean changeNumberOfUnitsProducts(List<Pet> productsPets) throws DaoException;

	/**
	 * Add the product pet.
	 *
	 * @param pet                  the pet
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean addProductPet(Pet pet, long numberOfUnitsProduct) throws DaoException;

	/**
	 * Get the product pet by id.
	 *
	 * @param id the id
	 * @return the product pet by id
	 * @throws DaoException the dao exception
	 */
	public Pet getProductPetById(long id) throws DaoException;

	/**
	 * Update product pet.
	 *
	 * @param pet                  the pet
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean updateProductPet(Pet pet, long numberOfUnitsProduct) throws DaoException;

	/**
	 * Exists product with image path.
	 *
	 * @param imagePath the image path
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean existsProductWithImagePath(String imagePath) throws DaoException;

	/**
	 * Get the product image path by product id.
	 *
	 * @param id the id
	 * @return the product image path by id
	 * @throws DaoException the dao exception
	 */
	public String getProductImagePathById(long id) throws DaoException;
}