package by.koroza.zoo_market.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.dao.impl.ProductFeedsAndOtherDaoImpl;
import by.koroza.zoo_market.model.entity.filter.FilterFeedsAndOther;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.service.ProductFeedsAndOtherService;
import by.koroza.zoo_market.service.exception.ServiceException;

public class ProductFeedsAndOtherServiceImpl implements ProductFeedsAndOtherService {
	private static final ProductFeedsAndOtherService INSTANCE = new ProductFeedsAndOtherServiceImpl();

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
			List<FeedAndOther> listAllHavingProducts = ProductFeedsAndOtherDaoImpl.getInstance()
					.getAllHavingProductsFeedAndOther();
			if (filter.isOnlyProductsWithDiscont()) {
				listProductsWithFilter = listAllHavingProducts.stream().filter(product -> product.getDiscount() > 0)
						.toList();
			} else if (filter.getMaxDiscont() != 0 || filter.getMinDiscont() != 0) {
				listProductsWithFilter = listProductsWithFilter.stream()
						.filter(product -> product.getDiscount() >= filter.getMinDiscont()
								&& product.getDiscount() <= filter.getMaxDiscont())
						.toList();
			}
			if (filter.getMaxPrice() != 0 || filter.getMinPrice() != 0) {
				listProductsWithFilter = listProductsWithFilter.stream()
						.filter(product -> product.getPrice() >= filter.getMinPrice()
								&& product.getPrice() <= filter.getMaxPrice())
						.toList();
			}
			selectProductsWithTypeProduct(filter, listProductsWithFilter);
			selectProductsForTypesPets(filter, listProductsWithFilter);
			selectProductsWithBrand(filter, listProductsWithFilter);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return listProductsWithFilter;
	}

	private void selectProductsWithTypeProduct(FilterFeedsAndOther filter, List<FeedAndOther> listProductsWithFilter) {
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
	}

	private void selectProductsForTypesPets(FilterFeedsAndOther filter, List<FeedAndOther> listProductsWithFilter) {
		String[] typePets = filter.getChoosedTypesPets();
		if (typePets != null) {
			listProductsWithFilter = listProductsWithFilter.stream().filter(product -> {
				boolean flag = false;
				List<String> listPetTypes = product.getPetTypes();
				if (listPetTypes != null) {
					for (String petType : typePets) {
						if (listPetTypes.contains(petType)) {
							flag = true;
						}
					}
				}
				return flag;
			}).toList();
		}
	}

	private void selectProductsWithBrand(FilterFeedsAndOther filter, List<FeedAndOther> listProductsWithFilter) {
		String[] brandProduct = filter.getChoosedProductBrend();
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
	}
}