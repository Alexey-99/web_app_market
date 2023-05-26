package by.koroza.zoo_market.service;

import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.model.entity.filter.FilterFeedsAndOther;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.service.exception.ServiceException;

public interface ProductFeedsAndOtherService {

	public List<FeedAndOther> getAllProductsFeedsAndOther() throws ServiceException;

	public List<FeedAndOther> getHavingProductsFeedAndOtherById(Map<String, String> productsIdMap)
			throws ServiceException;

	public List<FeedAndOther> getProductsFeedAndOtherByFilter(FilterFeedsAndOther filter) throws ServiceException;

	public Map<FeedAndOther, Long> getAllProductsFeedAndOtherAndNumberOfUnits() throws ServiceException;

	public boolean changeNumberOfUnitsProducts(Order order) throws ServiceException;

	public boolean addProduct(FeedAndOther product, long numberOfUnitsProduct) throws ServiceException;
}