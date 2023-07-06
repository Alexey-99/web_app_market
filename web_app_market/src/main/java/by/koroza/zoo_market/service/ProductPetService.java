package by.koroza.zoo_market.service;

import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.model.entity.filter.FilterPet;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.service.exception.ServiceException;

/**
 * The Interface ProductPetService.
 */
public interface ProductPetService {

	/**
	 * Get the all having products pets.
	 *
	 * @return the all having products pets
	 * @throws ServiceException the service exception
	 */
	public List<Pet> getAllHavingProductsPets() throws ServiceException;

	/**
	 * Get the products pets by id.
	 *
	 * @param productsIdMap the products id map
	 * @return the products pets by id
	 * @throws ServiceException the service exception
	 */
	public List<Pet> getProductsPetsById(Map<String, String> productsIdMap) throws ServiceException;

	/**
	 * Get the products pets by filter.
	 *
	 * @param filter the filter
	 * @return the products pets by filter
	 * @throws ServiceException the service exception
	 */
	public List<Pet> getProductsPetsByFilter(FilterPet filter) throws ServiceException;

	/**
	 * Get the all products pets and number of units.
	 *
	 * @return the all products pets and number of units
	 * @throws ServiceException the service exception
	 */
	public Map<Pet, Long> getAllProductsPetsAndNumberOfUnits() throws ServiceException;

	/**
	 * Change number of units products.
	 *
	 * @param order the order
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean changeNumberOfUnitsProducts(Order order) throws ServiceException;

	/**
	 * Add the product pet.
	 *
	 * @param pet                  the pet
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean addProductPet(Pet pet, long numberOfUnitsProduct) throws ServiceException;

	/**
	 * Get the product pet by id.
	 *
	 * @param id the id
	 * @return the product pet by id
	 * @throws ServiceException the service exception
	 */
	public Pet getProductPetById(long id) throws ServiceException;

	/**
	 * Update product pet.
	 *
	 * @param pet                  the pet
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean updateProductPet(Pet pet, long numberOfUnitsProduct) throws ServiceException;

	/**
	 * Get the product image path by product id.
	 *
	 * @param id the id
	 * @return the product image path
	 * @throws ServiceException the service exception
	 */
	public String getProductImagePathByProductId(long id) throws ServiceException;
}