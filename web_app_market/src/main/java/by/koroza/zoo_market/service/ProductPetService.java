package by.koroza.zoo_market.service;

import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.model.entity.filter.FilterPet;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.service.exception.ServiceException;

public interface ProductPetService {

	public List<Pet> getAllHavingProductsPets() throws ServiceException;

	public List<Pet> getProductsPetsById(Map<String, String> productsIdMap) throws ServiceException;

	public List<Pet> getProductsPetsByFilter(FilterPet filter) throws ServiceException;

	public Map<Pet, Long> getAllProductsPetsAndNumberOfUnits() throws ServiceException;

	public boolean changeNumberOfUnitsProducts(Order order) throws ServiceException;

	public boolean addProductPet(Pet pet, long numberOfUnitsProduct) throws ServiceException;

	public Pet getProductPetById(long id) throws ServiceException;
}