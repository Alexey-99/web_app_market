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

	private static final char CODE_OF_TYPE_PRODUCT_FEEDS_AND_OTHER = 'o';

	private ProductFeedsAndOtherServiceImpl() {
	}

	public static ProductFeedsAndOtherService getInstance() {
		return INSTANCE;
	}

	@Override
	public List<FeedAndOther> getAllProductsFeedsAndOther() throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().getAllProductsFeedAndOther();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<FeedAndOther> getProductsFeedsAndOtherById(Map<String, String> productsIdMap) throws ServiceException {
		try {
			List<FeedAndOther> productsBD = ProductFeedsAndOtherDaoImpl.getInstance()
					.getProductsFeedAndOtherById(productsIdMap);
			List<FeedAndOther> productsOrder = new ArrayList<>();
			for (Map.Entry<String, String> entry : productsIdMap.entrySet()) {
				String key = entry.getKey();
				String val = entry.getValue();
				if (key.charAt(0) == CODE_OF_TYPE_PRODUCT_FEEDS_AND_OTHER) {
					for (FeedAndOther product : productsBD) {
						if (product.getId() == Integer.parseInt(val)) {
							productsOrder.add(product);
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
	public List<FeedAndOther> getProductsPetsByFilter(FilterFeedsAndOther filter) throws ServiceException {
		try {
			return ProductFeedsAndOtherDaoImpl.getInstance().getProductsFeedAndOtherWithFilter(filter);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}