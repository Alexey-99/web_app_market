package by.koroza.zoo_market.service;

import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.model.entity.filter.FilterPet;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.service.exception.ServiceException;

public interface ProductPetService {

	public List<Pet> getAllHavingProductsPets() throws ServiceException;

	public List<Pet> getProductsPetsById(Map<String, String> productsIdMap) throws ServiceException;

	public List<Pet> getProductsPetsByFilter(FilterPet filter) throws ServiceException;
}