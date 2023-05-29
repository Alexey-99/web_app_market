package by.koroza.zoo_market.dao;

import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.model.entity.filter.FilterPet;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.Pet;

public interface ProductPetDao {

	public List<Pet> getAllHavingProductsPets() throws DaoException;

	public List<Pet> getProductsPetsById(Map<String, String> productsIdMap) throws DaoException;

	public List<Pet> getProductsPetsWithFilter(FilterPet filter) throws DaoException;

	public Map<Pet, Long> getAllProductsPetsAndNumberOfUnits() throws DaoException;

	public boolean changeNumberOfUnitsProducts(Order order) throws DaoException;

	public boolean addProductPet(Pet pet, long numberOfUnitsProduct) throws DaoException;

	public Pet getProductPetById(long id) throws DaoException;
}