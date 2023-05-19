package by.koroza.zoo_market.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.dao.impl.ProductPetDaoImpl;
import by.koroza.zoo_market.model.entity.filter.FilterPet;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.service.ProductPetService;
import by.koroza.zoo_market.service.exception.ServiceException;

public class ProductPetServiceImpl implements ProductPetService {
	private static final ProductPetService INSTANCE = new ProductPetServiceImpl();

	private ProductPetServiceImpl() {
	}

	public static ProductPetService getInstance() {
		return INSTANCE;
	}

	@Override
	public List<Pet> getAllProductsPets() throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().getAllProductsPets();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	private static final char CODE_OF_TYPE_PRODUCT_PET = 'p';

	@Override
	public List<Pet> getProductsPetsById(Map<String, String> productsIdMap) throws ServiceException {
		try {
			List<Pet> productsBD = ProductPetDaoImpl.getInstance().getProductsPetsById(productsIdMap);
			List<Pet> productsOrder = new ArrayList<>();
			for (Map.Entry<String, String> entry : productsIdMap.entrySet()) {
				String key = entry.getKey();
				String val = entry.getValue();
				if (key.charAt(0) == CODE_OF_TYPE_PRODUCT_PET) {
					for (Pet pet : productsBD) {
						if (pet.getId() == Integer.parseInt(val)) {
							productsOrder.add(pet);
						}
					}
				}
			}
			return productsOrder;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Pet> getProductsPetsByFilter(FilterPet filter) throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().getProductsPetsWithFilter(filter);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}