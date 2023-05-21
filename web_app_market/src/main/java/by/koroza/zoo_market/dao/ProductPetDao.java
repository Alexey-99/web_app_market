package by.koroza.zoo_market.dao;

import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.model.entity.filter.FilterPet;
import by.koroza.zoo_market.model.entity.market.product.Pet;

public interface ProductPetDao {

	public List<Pet> getAllHavingProductsPets() throws DaoException;

	public List<Pet> getProductsPetsById(Map<String, String> productsIdMap) throws DaoException;

	public List<Pet> getProductsPetsById(List<Integer> productsIdList) throws DaoException;

	public List<Pet> getProductsPetsWithFilter(FilterPet filter) throws DaoException;
}