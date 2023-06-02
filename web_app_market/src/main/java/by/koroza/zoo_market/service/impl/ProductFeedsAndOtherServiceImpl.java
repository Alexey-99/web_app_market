package by.koroza.zoo_market.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.dao.impl.ProductFeedsAndOtherDaoImpl;
import by.koroza.zoo_market.model.entity.filter.FilterFeedsAndOther;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.service.ProductFeedsAndOtherService;
import by.koroza.zoo_market.service.exception.ServiceException;

public class ProductFeedsAndOtherServiceImpl implements ProductFeedsAndOtherService {
	private static final ProductFeedsAndOtherService INSTANCE = new ProductFeedsAndOtherServiceImpl();
	@SuppressWarnings("unused")
	private static final Logger log = LogManager.getLogger();

	private ProductFeedsAndOtherServiceImpl() {
	}

	public static ProductFeedsAndOtherService getInstance() {
		return INSTANCE;
	}

	@Override
	public List<FeedAndOther> getAllProductsFeedsAndOther() throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().getAllHavingProductsFeedAndOther();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<FeedAndOther> getHavingProductsFeedAndOtherById(Map<String, String> productsIdMap)
			throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().getHavingProductsFeedAndOtherById(productsIdMap);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

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
			throw new ServiceException(e);
		}
		return listProductsWithFilter;
	}

	@Override
	public Map<FeedAndOther, Long> getAllProductsFeedAndOtherAndNumberOfUnits() throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().getAllProductsFeedAndOtherAndNumberOfUnits();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean changeNumberOfUnitsProducts(Order order) throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().changeNumberOfUnitsProducts(order);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean addProduct(FeedAndOther product, long numberOfUnitsProduct) throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().addProduct(product, numberOfUnitsProduct);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public FeedAndOther getProductFeedAndOtherById(long id) throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().getProductFeedAndOtherById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean upadateProductById(FeedAndOther product, long numberOfUnitsProduct) throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().upadateProductById(product, numberOfUnitsProduct);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

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