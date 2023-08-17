package by.koroza.zoo_market.service.impl.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.dao.impl.product.ProductFeedsAndOtherDaoImpl;
import by.koroza.zoo_market.model.entity.filter.FilterFeedsAndOther;
import by.koroza.zoo_market.model.entity.filter.product.AbstractProductFilter;
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
	public List<Entry<FeedAndOther, Long>> getProductsFeedAndOtherByFilter(FilterFeedsAndOther filter)
			throws ServiceException {
		List<Entry<FeedAndOther, Long>> listProductsWithFilter = new ArrayList<>();
		try {
			listProductsWithFilter = ProductFeedsAndOtherDaoImpl.getInstance()
					.getAllProductsFeedAndOtherAndNumberOfUnits().entrySet().stream().toList();
			if (filter.isOnlyProductsWithDiscount()) {
				listProductsWithFilter = listProductsWithFilter.stream()
						.filter(entry -> entry.getKey().getDiscount() > 0).toList();
			} else if (filter.getMaxDiscount() != AbstractProductFilter.MAX_DISCOUNT
					|| filter.getMinDiscount() != AbstractProductFilter.MIN_DISCOUNT) {
				listProductsWithFilter = listProductsWithFilter.stream()
						.filter(entry -> entry.getKey().getDiscount() >= filter.getMinDiscount()
								&& entry.getKey().getDiscount() <= filter.getMaxDiscount())
						.toList();
			}
			if (filter.getMaxPriceEntered() != filter.getMaxPriceAllProducts()
					|| filter.getMinPrice() != AbstractProductFilter.MIN_PRICE) {
				listProductsWithFilter = listProductsWithFilter.stream()
						.filter(entry -> entry.getKey().getPrice() >= filter.getMinPrice()
								&& entry.getKey().getPrice() <= filter.getMaxPriceEntered())
						.toList();
			}
			listProductsWithFilter = selectProductsByTypeProduct(filter, listProductsWithFilter);
			listProductsWithFilter = selectProductsByTypesPets(filter, listProductsWithFilter);
			listProductsWithFilter = selectProductsByBrand(filter, listProductsWithFilter);
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
	public Map<Integer, Boolean> changeNumberOfUnitsProductsMinus(List<FeedAndOther> productsFeedAndOther)
			throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().changeNumberOfUnitsProductsMinus(productsFeedAndOther);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Change number of units products plus.
	 *
	 * @param productsFeedAndOther the products feed and other
	 * @param haveProductByIndex   the have product by index
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean changeNumberOfUnitsProductsPlus(List<FeedAndOther> productsFeedAndOther,
			Map<Integer, Boolean> haveProductByIndex) throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().changeNumberOfUnitsProductsPlus(productsFeedAndOther,
					haveProductByIndex);
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
	 * Transfer feed and other product from market to order.
	 *
	 * @param productId the product id
	 * @param orderId   the order id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean transferFeedAndOtherProductFromMarketToOrder(long productId, long orderId) throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().transferFeedsAndOtherProductFromMarketToOrder(productId,
					orderId);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Transfer feeds and other product from order to market.
	 *
	 * @param productId the product id
	 * @param orderId   the order id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean transferFeedsAndOtherProductFromOrderToMarket(long productId, long orderId) throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().transferFeedsAndOtherProductFromOrderToMarket(productId,
					orderId);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Select products by type product.
	 *
	 * @param filter                 the filter
	 * @param listProductsWithFilter the list products with filter
	 * @return the list
	 */
	private List<Entry<FeedAndOther, Long>> selectProductsByTypeProduct(FilterFeedsAndOther filter,
			List<Entry<FeedAndOther, Long>> listProductsWithFilter) {
		String[] productTypes = filter.getChoosedTypesProduct();
		if (productTypes != null) {
			listProductsWithFilter = listProductsWithFilter.stream().filter(entry -> {
				boolean flag = false;
				for (String productType : productTypes) {
					if (entry.getKey().getProductType().equalsIgnoreCase(productType)) {
						flag = true;
					}
				}
				return flag;
			}).toList();
		}
		return listProductsWithFilter;
	}

	/**
	 * Select products by types pets.
	 *
	 * @param filter                 the filter
	 * @param listProductsWithFilter the list products with filter
	 * @return the list
	 */
	private List<Entry<FeedAndOther, Long>> selectProductsByTypesPets(FilterFeedsAndOther filter,
			List<Entry<FeedAndOther, Long>> listProductsWithFilter) {
		String[] typePets = filter.getChoosedTypesPets();
		if (typePets != null) {
			listProductsWithFilter = listProductsWithFilter.stream().filter(entry -> {
				boolean flag = false;
				List<String> listPetTypes = entry.getKey().getPetTypes();
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
	 * Select products by brand.
	 *
	 * @param filter                 the filter
	 * @param listProductsWithFilter the list products with filter
	 * @return the list
	 */
	private List<Entry<FeedAndOther, Long>> selectProductsByBrand(FilterFeedsAndOther filter,
			List<Entry<FeedAndOther, Long>> listProductsWithFilter) {
		String[] brandProduct = filter.getChoosedProductBrand();
		if (brandProduct != null) {
			listProductsWithFilter = listProductsWithFilter.stream().filter(entry -> {
				boolean flag = false;
				for (String brand : brandProduct) {
					if (entry.getKey().getBrand().equalsIgnoreCase(brand)) {
						flag = true;
					}
				}
				return flag;
			}).toList();
		}
		return listProductsWithFilter;
	}
}