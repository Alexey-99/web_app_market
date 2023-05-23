package by.koroza.zoo_market.dao;

import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.model.entity.filter.FilterFeedsAndOther;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;

public interface ProductFeedsAndOtherDao {

	public List<FeedAndOther> getAllHavingProductsFeedAndOther() throws DaoException;

	public List<FeedAndOther> getHavingProductsFeedAndOtherById(Map<String, String> productsIdMap) throws DaoException;

	public List<FeedAndOther> getProductsFeedAndOtherWithFilter(FilterFeedsAndOther filter) throws DaoException;

	public Map<FeedAndOther, Long> getAllProductsFeedAndOtherAndNumberOfUnits() throws DaoException;
}