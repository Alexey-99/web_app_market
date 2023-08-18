package by.koroza.zoo_market.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.filter.FilterPet;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.service.exception.ServiceException;

/**
 * The Interface ProductPetService.
 */
public interface ProductPetService extends ProductService {

	/**
	 * Get the products by filter.
	 *
	 * @param filter the filter
	 * @return the products by filter
	 * @throws ServiceException the service exception
	 */
	public List<Entry<Pet, Long>> getProductsByFilter(FilterPet filter) throws ServiceException;

	/**
	 * Get the all products and number of units.
	 *
	 * @return the all products and number of units
	 * @throws ServiceException the service exception
	 */
	public Map<Pet, Long> getAllProductsAndNumberOfUnits() throws ServiceException;

	/**
	 * Add the product.
	 *
	 * @param pet                  the pet
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean addProduct(Pet pet, long numberOfUnitsProduct) throws ServiceException;

	/**
	 * Get the product by id.
	 *
	 * @param id the id
	 * @return the product by id
	 * @throws ServiceException the service exception
	 */
	public Pet getProductById(long id) throws ServiceException;

	/**
	 * Update product by id.
	 *
	 * @param pet                  the pet
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean updateProductById(Pet pet, long numberOfUnitsProduct) throws ServiceException;
}