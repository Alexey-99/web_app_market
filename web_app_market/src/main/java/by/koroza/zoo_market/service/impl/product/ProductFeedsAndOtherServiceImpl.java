package by.koroza.zoo_market.service.impl.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.dao.impl.product.ProductFeedsAndOtherDaoImpl;
import by.koroza.zoo_market.model.entity.filter.FilterFeedsAndOther;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.service.ProductFeedsAndOtherService;
import by.koroza.zoo_market.service.exception.ServiceException;

/**
 * The Class ProductFeedsAndOtherServiceImpl.
 */
public class ProductFeedsAndOtherServiceImpl implements ProductFeedsAndOtherService {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final ProductFeedsAndOtherService INSTANCE = new ProductFeedsAndOtherServiceImpl();

	/**
	 * Instantiates a new product feeds and other service impl.
	 */
	private ProductFeedsAndOtherServiceImpl() {
	}

	/**
	 * Get the single instance of ProductFeedsAndOtherServiceImpl.
	 *
	 * @return single instance of ProductFeedsAndOtherServiceImpl
	 */
	public static ProductFeedsAndOtherService getInstance() {
		return INSTANCE;
	}

	/**
	 * Get the all products feeds and other.
	 *
	 * @return the all products feeds and other
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<FeedAndOther> getAllProductsFeedsAndOther() throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().getAllHavingProductsFeedAndOther();
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Get the having products feed and other by id.
	 *
	 * @param productsIdMap the products id map
	 * @return the having products feed and other by id
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<FeedAndOther> getHavingProductsFeedAndOtherById(Map<String, String> productsIdMap)
			throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().getHavingProductsFeedAndOtherById(productsIdMap);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Get the products feed and other by filter.
	 *
	 * @param filter the filter
	 * @return the products feed and other by filter
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<FeedAndOther> getProductsFeedAndOtherByFilter(FilterFeedsAndOther filter) throws ServiceException {
		List<FeedAndOther> listProductsWithFilter = new ArrayList<>();
		try {
			listProductsWithFilter = ProductFeedsAndOtherDaoImpl.getInstance().getAllHavingProductsFeedAndOther();
			if (filter.isOnlyProductsWithDiscount()) {
				listProductsWithFilter = listProductsWithFilter.stream().filter(product -> product.getDiscount() > 0)
						.toList();
			} else if (filter.getMaxDiscount() != 0 || filter.getMinDiscount() != 0) {
				listProductsWithFilter = listProductsWithFilter.stream()
						.filter(product -> product.getDiscount() >= filter.getMinDiscount()
								&& product.getDiscount() <= filter.getMaxDiscount())
						.toList();
			}
			if (filter.getMaxPrice() != 0 || filter.getMinPrice() != 0) {
				listProductsWithFilter = listProductsWithFilter.stream()
						.filter(product -> product.getPrice() >= filter.getMinPrice()
								&& product.getPrice() <= filter.getMaxPrice())
						.toList();
			}
			listProductsWithFilter = selectProductsWithTypeProduct(filter, listProductsWithFilter);
			listProductsWithFilter = selectProductsForTypesPets(filter, listProductsWithFilter);
			listProductsWithFilter = selectProductsWithBrand(filter, listProductsWithFilter);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
		return listProductsWithFilter;
	}

	/**
	 * Get the all products feed and other and number of units.
	 *
	 * @return the all products feed and other and number of units
	 * @throws ServiceException the service exception
	 */
	@Override
	public Map<FeedAndOther, Long> getAllProductsFeedAndOtherAndNumberOfUnits() throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().getAllProductsFeedAndOtherAndNumberOfUnits();
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
			return ProductFeedsAndOtherDaoImpl.getInstance().changeNumberOfUnitsProducts(order);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Add the product.
	 *
	 * @param product              the product
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean addProduct(FeedAndOther product, long numberOfUnitsProduct) throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().addProduct(product, numberOfUnitsProduct);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Get the product feed and other by id.
	 *
	 * @param id the id
	 * @return the product feed and other by id
	 * @throws ServiceException the service exception
	 */
	@Override
	public FeedAndOther getProductFeedAndOtherById(long id) throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().getProductFeedAndOtherById(id);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Update product by id.
	 *
	 * @param product              the product
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean updateProductById(FeedAndOther product, long numberOfUnitsProduct) throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().updateProductById(product, numberOfUnitsProduct);
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
			return ProductFeedsAndOtherDaoImpl.getInstance().getProductImagePathById(id);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Select products with type product.
	 *
	 * @param filter                 the filter
	 * @param listProductsWithFilter the list products with filter
	 * @return the list
	 */
	private List<FeedAndOther> selectProductsWithTypeProduct(FilterFeedsAndOther filter,
			List<FeedAndOther> listProductsWithFilter) {
		String[] productTypes = filter.getChoosedTypesProduct();
		if (productTypes != null) {
			listProductsWithFilter = listProductsWithFilter.stream().filter(product -> {
				boolean flag = false;
				for (String productType : productTypes) {
					if (product.getProductType().equalsIgnoreCase(productType)) {
						flag = true;
					}
				}
				return flag;
			}).toList();
		}
		return listProductsWithFilter;
	}

	/**
	 * Select products for types pets.
	 *
	 * @param filter                 the filter
	 * @param listProductsWithFilter the list products with filter
	 * @return the list
	 */
	private List<FeedAndOther> selectProductsForTypesPets(FilterFeedsAndOther filter,
			List<FeedAndOther> listProductsWithFilter) {
		String[] typePets = filter.getChoosedTypesPets();
		if (typePets != null) {
			listProductsWithFilter = listProductsWithFilter.stream().filter(product -> {
				boolean flag = false;
				List<String> listPetTypes = product.getPetTypes();
				if (listPetTypes != null) {
					for (String petType : typePets) {
						if (listPetTypes.contains(petType.toLowerCase())) {
							flag = true;
						}
					}
				}
				return flag;
			}).toList();
		}
		return listProductsWithFilter;
	}

	/**
	 * Select products with brand.
	 *
	 * @param filter                 the filter
	 * @param listProductsWithFilter the list products with filter
	 * @return the list
	 */
	private List<FeedAndOther> selectProductsWithBrand(FilterFeedsAndOther filter,
			List<FeedAndOther> listProductsWithFilter) {
		String[] brandProduct = filter.getChoosedProductBrand();
		if (brandProduct != null) {
			listProductsWithFilter = listProductsWithFilter.stream().filter(product -> {
				boolean flag = false;
				for (String brand : brandProduct) {
					if (product.getBrand().equalsIgnoreCase(brand)) {
						flag = true;
					}
				}
				return flag;
			}).toList();
		}
		return listProductsWithFilter;
	}
}