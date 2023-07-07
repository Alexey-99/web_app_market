package by.koroza.zoo_market.service.impl.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.dao.impl.product.ProductPetDaoImpl;
import by.koroza.zoo_market.model.entity.filter.FilterPet;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.service.ProductPetService;
import by.koroza.zoo_market.service.exception.ServiceException;

/**
 * The Class ProductPetServiceImpl.
 */
public class ProductPetServiceImpl implements ProductPetService {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final ProductPetService INSTANCE = new ProductPetServiceImpl();

	/**
	 * Instantiates a new product pet service impl.
	 */
	private ProductPetServiceImpl() {
	}

	/**
	 * Get the single instance of ProductPetServiceImpl.
	 *
	 * @return single instance of ProductPetServiceImpl
	 */
	public static ProductPetService getInstance() {
		return INSTANCE;
	}

	/**
	 * Get the all having products pets.
	 *
	 * @return the all having products pets
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<Pet> getAllHavingProductsPets() throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().getAllHavingProductsPets();
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Get the products pets by product id.
	 *
	 * @param productsIdMap the products id map
	 * @return the products pets by id
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<Pet> getProductsPetsById(Map<String, String> productsIdMap) throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().getProductsPetsById(productsIdMap);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Get the products pets by filter.
	 *
	 * @param filter the filter
	 * @return the products pets by filter
	 * @throws ServiceException the service exception
	 */
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
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
		return listPetsWithFilter;
	}

	/**
	 * Get the all products pets and number of units.
	 *
	 * @return the all products pets and number of units
	 * @throws ServiceException the service exception
	 */
	@Override
	public Map<Pet, Long> getAllProductsPetsAndNumberOfUnits() throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().getAllProductsPetsAndNumberOfUnits();
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Change number of units products.
	 *
	 * @param order the order
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean changeNumberOfUnitsProducts(Order order) throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().changeNumberOfUnitsProducts(order);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Add the product pet.
	 *
	 * @param pet                  the pet
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean addProductPet(Pet pet, long numberOfUnitsProduct) throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().addProductPet(pet, numberOfUnitsProduct);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Get the product pet by id.
	 *
	 * @param id the id
	 * @return the product pet by id
	 * @throws ServiceException the service exception
	 */
	@Override
	public Pet getProductPetById(long id) throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().getProductPetById(id);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Update product pet.
	 *
	 * @param pet                  the pet
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean updateProductPet(Pet pet, long numberOfUnitsProduct) throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().updateProductPet(pet, numberOfUnitsProduct);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Get the product image path by product id.
	 *
	 * @param id the id
	 * @return the product image path by product id
	 * @throws ServiceException the service exception
	 */
	@Override
	public String getProductImagePathByProductId(long id) throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().getProductImagePathById(id);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Select products pets by birth date.
	 *
	 * @param filter             the filter
	 * @param listPetsWithFilter the list pets with filter
	 * @return the list
	 */
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

	/**
	 * Select products pets by species.
	 *
	 * @param filter             the filter
	 * @param listPetsWithFilter the list pets with filter
	 * @return the list
	 */
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

	/**
	 * Select products pets by breed.
	 *
	 * @param filter             the filter
	 * @param listPetsWithFilter the list pets with filter
	 * @return the list
	 */
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