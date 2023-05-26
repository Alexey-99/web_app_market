package by.koroza.zoo_market.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.dao.impl.ProductPetDaoImpl;
import by.koroza.zoo_market.model.entity.filter.FilterPet;
import by.koroza.zoo_market.model.entity.market.order.Order;
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
	public List<Pet> getAllHavingProductsPets() throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().getAllHavingProductsPets();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Pet> getProductsPetsById(Map<String, String> productsIdMap) throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().getProductsPetsById(productsIdMap);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Pet> getProductsPetsByFilter(FilterPet filter) throws ServiceException {
		List<Pet> listPetsWithFilter = new ArrayList<>();
		try {
			listPetsWithFilter = ProductPetDaoImpl.getInstance().getAllHavingProductsPets();
			if (filter.isOnlyProductsWithDiscount()) {
				listPetsWithFilter = listPetsWithFilter.stream().filter(product -> product.getDiscount() > 0).toList();
			} else if (filter.getMaxDiscount() != 0 || filter.getMinDiscount() != 0) {
				listPetsWithFilter = listPetsWithFilter.stream()
						.filter(product -> product.getDiscount() >= filter.getMinDiscount()
								&& product.getDiscount() <= filter.getMaxDiscount())
						.toList();
			}
			if (filter.getMaxPrice() != 0 || filter.getMinPrice() != 0) {
				listPetsWithFilter = listPetsWithFilter.stream()
						.filter(product -> product.getPrice() >= filter.getMinPrice()
								&& product.getPrice() <= filter.getMaxPrice())
						.toList();
			}
			listPetsWithFilter = selectProductsPetsByBirthDate(filter, listPetsWithFilter);
			listPetsWithFilter = selectProductsPetsBySpecie(filter, listPetsWithFilter);
			listPetsWithFilter = selectProductsPetsByBreed(filter, listPetsWithFilter);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return listPetsWithFilter;
	}

	@Override
	public Map<Pet, Long> getAllProductsPetsAndNumberOfUnits() throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().getAllProductsPetsAndNumberOfUnits();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean changeNumberOfUnitsProducts(Order order) throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().changeNumberOfUnitsProducts(order);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean addProductPet(Pet pet, long numberOfUnitsProduct) throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().addProductPet(pet, numberOfUnitsProduct);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	private List<Pet> selectProductsPetsByBirthDate(FilterPet filter, List<Pet> listPetsWithFilter) {
		if ((filter.getMinNumberMonth() != 0 || filter.getMinNumberYear() != 0)
				|| (filter.getMaxNumberMonth() != 0 || filter.getMaxNumberYear() != 0)) {
			listPetsWithFilter = listPetsWithFilter.stream()
					.filter(pet -> pet.getBirthDate()
							.isAfter(LocalDate.now().minusYears(filter.getMaxNumberYear())
									.minusMonths(filter.getMaxNumberMonth()))
							&& pet.getBirthDate().isBefore(LocalDate.now().minusYears(filter.getMinNumberYear())
									.minusMonths(filter.getMinNumberMonth())))
					.toList();
		}
		return listPetsWithFilter;
	}

	private List<Pet> selectProductsPetsBySpecie(FilterPet filter, List<Pet> listPetsWithFilter) {
		String[] typePets = filter.getChoosedTypesPets();
		if (typePets != null) {
			listPetsWithFilter = listPetsWithFilter.stream().filter(pet -> {
				boolean flag = false;
				for (String specie : typePets) {
					if (pet.getSpecie().equalsIgnoreCase(specie)) {
						flag = true;
					}
				}
				return flag;
			}).toList();
		}
		return listPetsWithFilter;
	}

	private List<Pet> selectProductsPetsByBreed(FilterPet filter, List<Pet> listPetsWithFilter) {
		String[] breedPets = filter.getChoosedBreedPets();
		if (breedPets != null) {
			listPetsWithFilter = listPetsWithFilter.stream().filter(pet -> {
				boolean flag = false;
				for (String breed : breedPets) {
					if (pet.getBreed().equalsIgnoreCase(breed)) {
						flag = true;
					}
				}
				return flag;
			}).toList();
		}
		return listPetsWithFilter;
	}
}