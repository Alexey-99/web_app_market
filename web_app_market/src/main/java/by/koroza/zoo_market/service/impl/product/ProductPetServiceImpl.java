package by.koroza.zoo_market.service.impl.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.dao.impl.product.ProductPetDaoImpl;
import by.koroza.zoo_market.model.entity.filter.FilterPet;
import by.koroza.zoo_market.model.entity.filter.product.AbstractProductFilter;
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
	 * Gets the single instance of ProductPetServiceImpl.
	 *
	 * @return single instance of ProductPetServiceImpl
	 */
	public static ProductPetService getInstance() {
		return INSTANCE;
	}

	/**
	 * Get the products by filter.
	 *
	 * @param filter the filter
	 * @return the products by filter
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<Entry<Pet, Long>> getProductsByFilter(FilterPet filter) throws ServiceException {
		List<Entry<Pet, Long>> productsPetsByFilter = new ArrayList<>();
		try {
			productsPetsByFilter = ProductPetDaoImpl.getInstance().getAllProductsAndNumberOfUnits().entrySet().stream()
					.toList();
			if (filter.isOnlyProductsWithDiscount()) {
				productsPetsByFilter = productsPetsByFilter.stream().filter(entry -> entry.getKey().getDiscount() > 0)
						.toList();
			} else if (filter.getMaxDiscount() != AbstractProductFilter.MAX_DISCOUNT
					|| filter.getMinDiscount() != AbstractProductFilter.MIN_DISCOUNT) {
				productsPetsByFilter = productsPetsByFilter.stream()
						.filter(entry -> entry.getKey().getDiscount() >= filter.getMinDiscount()
								&& entry.getKey().getDiscount() <= filter.getMaxDiscount())
						.toList();
			}
			if (filter.getMaxPriceEntered() != filter.getMaxPriceAllProducts()
					|| filter.getMinPrice() != AbstractProductFilter.MIN_PRICE) {
				productsPetsByFilter = productsPetsByFilter.stream()
						.filter(entry -> entry.getKey().getPrice() >= filter.getMinPrice()
								&& entry.getKey().getPrice() <= filter.getMaxPriceEntered())
						.toList();
			}
			productsPetsByFilter = selectProductsPetsByBirthDate(filter, productsPetsByFilter);
			productsPetsByFilter = selectProductsPetsBySpecie(filter, productsPetsByFilter);
			productsPetsByFilter = selectProductsPetsByBreed(filter, productsPetsByFilter);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
		return productsPetsByFilter;
	}

	/**
	 * Get the all products and number of units.
	 *
	 * @return the all products and number of units
	 * @throws ServiceException the service exception
	 */
	@Override
	public Map<Pet, Long> getAllProductsAndNumberOfUnits() throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().getAllProductsAndNumberOfUnits();
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Add the product.
	 *
	 * @param pet                  the pet
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean addProduct(Pet pet, long numberOfUnitsProduct) throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().addProduct(pet, numberOfUnitsProduct);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Get the product by id.
	 *
	 * @param id the id
	 * @return the product by id
	 * @throws ServiceException the service exception
	 */
	@Override
	public Pet getProductById(long id) throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().getProductById(id);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Update product by id.
	 *
	 * @param pet                  the pet
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean updateProductById(Pet pet, long numberOfUnitsProduct) throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().updateProductById(pet, numberOfUnitsProduct);
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
	 * Transfer product from market to order.
	 *
	 * @param productId the product id
	 * @param orderId   the order id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean transferProductFromMarketToOrder(long productId, long orderId) throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().transferProductFromMarketToOrder(productId, orderId);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Transfer product from order to market.
	 *
	 * @param productId the product id
	 * @param orderId   the order id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean transferProductFromOrderToMarket(long productId, long orderId) throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().transferProductFromOrderToMarket(productId, orderId);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Get the free number of units by product id.
	 *
	 * @param productId the product id
	 * @return the free number of units by product id
	 * @throws ServiceException the service exception
	 */
	@Override
	public long getFreeNumberOfUnitsByProductId(long productId) throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().getFreeNumberOfUnitsByProductId(productId);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Get the quantity in orders by product id and order status.
	 *
	 * @param productId     the product id
	 * @param orderStatusId the order status id
	 * @return the quantity in orders by product id and order status
	 * @throws ServiceException the service exception
	 */
	@Override
	public long getQuantityInOrdersByProductIdAndOrderStatus(long productId, int orderStatusId)
			throws ServiceException {
		try {
			return ProductPetDaoImpl.getInstance().getQuantityInOrdersByProductIdAndOrderStatus(productId,
					orderStatusId);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Select products pets by birth date.
	 *
	 * @param filter               the filter
	 * @param productsPetsByFilter the products pets by filter
	 * @return the list
	 */
	private List<Entry<Pet, Long>> selectProductsPetsByBirthDate(FilterPet filter,
			List<Entry<Pet, Long>> productsPetsByFilter) {
		if ((filter.getMinNumberMonth() != 0 || filter.getMinNumberYear() != 0)
				|| (filter.getMaxNumberMonth() != 0 || filter.getMaxNumberYear() != 0)) {
			productsPetsByFilter = productsPetsByFilter.stream()
					.filter(entry -> entry.getKey().getBirthDate()
							.isAfter(LocalDate.now().minusYears(filter.getMaxNumberYear())
									.minusMonths(filter.getMaxNumberMonth()))
							&& entry.getKey().getBirthDate().isBefore(LocalDate.now()
									.minusYears(filter.getMinNumberYear()).minusMonths(filter.getMinNumberMonth())))
					.toList();
		}
		return productsPetsByFilter;
	}

	/**
	 * Select products pets by specie.
	 *
	 * @param filter               the filter
	 * @param productsPetsByFilter the products pets by filter
	 * @return the list
	 */
	private List<Entry<Pet, Long>> selectProductsPetsBySpecie(FilterPet filter,
			List<Entry<Pet, Long>> productsPetsByFilter) {
		String[] typePets = filter.getChoosedTypesPets();
		if (typePets != null) {
			productsPetsByFilter = productsPetsByFilter.stream().filter(entry -> {
				boolean flag = false;
				for (String specie : typePets) {
					if (entry.getKey().getSpecie().equalsIgnoreCase(specie)) {
						flag = true;
					}
				}
				return flag;
			}).toList();
		}
		return productsPetsByFilter;
	}

	/**
	 * Select products pets by breed.
	 *
	 * @param filter               the filter
	 * @param productsPetsByFilter the products pets by filter
	 * @return the list
	 */
	private List<Entry<Pet, Long>> selectProductsPetsByBreed(FilterPet filter,
			List<Entry<Pet, Long>> productsPetsByFilter) {
		String[] breedPets = filter.getChoosedBreedPets();
		if (breedPets != null) {
			productsPetsByFilter = productsPetsByFilter.stream().filter(entry -> {
				boolean flag = false;
				for (String breed : breedPets) {
					if (entry.getKey().getBreed().equalsIgnoreCase(breed)) {
						flag = true;
					}
				}
				return flag;
			}).toList();
		}
		return productsPetsByFilter;
	}
}