package by.koroza.zoo_market.dao.impl;

import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_BRAND;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_DATE_UPDATE;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_DESCRIPTION;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_DISCOUNT;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_PET_TYPE;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_PRICE;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_TYPE;
import static by.koroza.zoo_market.dao.name.ColumnName.PRODUCT_STATUSES_NAME;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.dao.pool.ConnectionPool;
import by.koroza.zoo_market.dao.pool.ProxyConnection;
import by.koroza.zoo_market.model.entity.filter.FilterFeedsAndOther;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.status.ProductStatus;
import by.koroza.zoo_market.dao.ProductFeedsAndOtherDao;
import by.koroza.zoo_market.dao.exception.DaoException;

public class ProductFeedsAndOtherDaoImpl implements ProductFeedsAndOtherDao {
	private static final ProductFeedsAndOtherDao INSTANCE = new ProductFeedsAndOtherDaoImpl();

	private ProductFeedsAndOtherDaoImpl() {
	}

	public static ProductFeedsAndOtherDao getInstance() {
		return INSTANCE;
	}

	private static final String QUERY_SELECT_ALL_PRODUCTS_FEED_AND_OTHER_CLOSED = """
			SELECT feeds_and_other.id, feeds_and_other.type, feeds_and_other.brand, feeds_and_other.description, feeds_and_other.pet_type, feeds_and_other.price, feeds_and_other.discount, feeds_and_other.date_update, product_statuses.name
			FROM feeds_and_other
			INNER JOIN product_statuses
			ON feeds_and_other.statuses_id = product_statuses.id
			WHERE feeds_and_other.statuses_id = 1;
			""";

	@Override
	public List<FeedAndOther> getAllProductsFeedAndOther() throws DaoException {
		List<FeedAndOther> listFeedAndOther = new ArrayList<>();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(QUERY_SELECT_ALL_PRODUCTS_FEED_AND_OTHER_CLOSED);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				FeedAndOther feedAndOther = new FeedAndOther.FeedAndOtherBuilder()
						.setId(resultSet.getLong(FEEDS_AND_OTHER_ID))
						.setProductType(resultSet.getString(FEEDS_AND_OTHER_TYPE))
						.setBrand(resultSet.getString(FEEDS_AND_OTHER_BRAND))
						.setDescriptions(resultSet.getString(FEEDS_AND_OTHER_DESCRIPTION))
						.setPetTypes(resultSet.getString(FEEDS_AND_OTHER_PET_TYPE))
						.setPrice(resultSet.getDouble(FEEDS_AND_OTHER_PRICE))
						.setDiscount(resultSet.getDouble(FEEDS_AND_OTHER_DISCOUNT))
						.setUpdateDateTime(resultSet.getDate(FEEDS_AND_OTHER_DATE_UPDATE).toLocalDate(),
								resultSet.getTime(FEEDS_AND_OTHER_DATE_UPDATE).toLocalTime())
						.setStutus(ProductStatus.findEqualProductStatus(resultSet.getString(PRODUCT_STATUSES_NAME)))
						.build();
				listFeedAndOther.add(feedAndOther);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return listFeedAndOther;
	}

	@Override
	public List<FeedAndOther> getProductsFeedAndOtherById(Map<String, String> productsIdMap) throws DaoException {
		List<FeedAndOther> listFeedAndOther = new ArrayList<>();
		String query = createQueryGetProductsFeedAndOtherById(productsIdMap);
		if (query != null) {
			try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
					PreparedStatement statement = connection.prepareStatement(query);
					ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					FeedAndOther feedAndOther = new FeedAndOther.FeedAndOtherBuilder()
							.setId(resultSet.getLong(FEEDS_AND_OTHER_ID))
							.setProductType(resultSet.getString(FEEDS_AND_OTHER_TYPE))
							.setBrand(resultSet.getString(FEEDS_AND_OTHER_BRAND))
							.setDescriptions(resultSet.getString(FEEDS_AND_OTHER_DESCRIPTION))
							.setPetTypes(resultSet.getString(FEEDS_AND_OTHER_PET_TYPE))
							.setPrice(resultSet.getDouble(FEEDS_AND_OTHER_PRICE))
							.setDiscount(resultSet.getDouble(FEEDS_AND_OTHER_DISCOUNT))
							.setUpdateDateTime(resultSet.getDate(FEEDS_AND_OTHER_DATE_UPDATE).toLocalDate(),
									resultSet.getTime(FEEDS_AND_OTHER_DATE_UPDATE).toLocalTime())
							.setStutus(ProductStatus.findEqualProductStatus(resultSet.getString(PRODUCT_STATUSES_NAME)))
							.build();
					listFeedAndOther.add(feedAndOther);
				}
			} catch (SQLException | NullPointerException e) {
				throw new DaoException(e);
			}
		}
		return listFeedAndOther;
	}

	@Override
	public List<FeedAndOther> getProductsFeedAndOtherWithFilter(FilterFeedsAndOther filter) throws DaoException {
		List<FeedAndOther> listFeedAndOther = new ArrayList<>();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(createQueryGetProductsPetsByFilter(filter));
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				FeedAndOther feedAndOther = new FeedAndOther.FeedAndOtherBuilder()
						.setId(resultSet.getLong(FEEDS_AND_OTHER_ID))
						.setProductType(resultSet.getString(FEEDS_AND_OTHER_TYPE))
						.setBrand(resultSet.getString(FEEDS_AND_OTHER_BRAND))
						.setDescriptions(resultSet.getString(FEEDS_AND_OTHER_DESCRIPTION))
						.setPetTypes(resultSet.getString(FEEDS_AND_OTHER_PET_TYPE))
						.setPrice(resultSet.getDouble(FEEDS_AND_OTHER_PRICE))
						.setDiscount(resultSet.getDouble(FEEDS_AND_OTHER_DISCOUNT))
						.setUpdateDateTime(resultSet.getDate(FEEDS_AND_OTHER_DATE_UPDATE).toLocalDate().toString(),
								resultSet.getTime(FEEDS_AND_OTHER_DATE_UPDATE).toLocalTime().toString())
						.setStutus(ProductStatus.findEqualProductStatus(resultSet.getString(PRODUCT_STATUSES_NAME)))
						.build();
				listFeedAndOther.add(feedAndOther);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}

		return listFeedAndOther;
	}

	private static final char CODE_OF_TYPE_PRODUCT_FEEDS_AND_OTHER = 'o';

	private static final String QUERY_SELECT_PRODUCTS_FEED_AND_OTHER_NOT_CLOSED = """
			SELECT feeds_and_other.id, feeds_and_other.type, feeds_and_other.brand, feeds_and_other.description, feeds_and_other.pet_type, feeds_and_other.price, feeds_and_other.discount, feeds_and_other.date_update, product_statuses.name
			FROM feeds_and_other
			INNER JOIN product_statuses
			ON feeds_and_other.statuses_id = product_statuses.id
			WHERE feeds_and_other.statuses_id = 1
			""";

	private String createQueryGetProductsFeedAndOtherById(Map<String, String> productsIdMap) {
		boolean haveFirstElement = false;
		StringBuilder query = new StringBuilder(QUERY_SELECT_PRODUCTS_FEED_AND_OTHER_NOT_CLOSED);
		for (Map.Entry<String, String> entry : productsIdMap.entrySet()) {
			String key = entry.getKey();
			String val = entry.getValue();
			if (key.charAt(0) == CODE_OF_TYPE_PRODUCT_FEEDS_AND_OTHER) {
				if (haveFirstElement == false) {
					query.append("and (").append(FEEDS_AND_OTHER_ID).append(" = ").append(val);
					haveFirstElement = true;
				} else {
					query.append(" or ").append(FEEDS_AND_OTHER_ID).append(" = ").append(val);
				}
			}
		}
		return (haveFirstElement ? query.append(")").append(";").toString() : null);
	}

	private String createQueryGetProductsPetsByFilter(FilterFeedsAndOther filter) {
		StringBuilder query = new StringBuilder(QUERY_SELECT_PRODUCTS_FEED_AND_OTHER_NOT_CLOSED);
		int countParameters = 0;
		if (filter.isOnlyProductsWithDiscont()) {
			countParameters++;
			query.append(" and (").append(FEEDS_AND_OTHER_DISCOUNT).append(" > 0");
		} else if (filter.getMaxDiscont() != 0 || filter.getMinDiscont() != 0) {
			countParameters++;
			insertFilterParametersInQueryMinMaxDouble(query, filter.getMinDiscont(), filter.getMaxDiscont(),
					FEEDS_AND_OTHER_DISCOUNT);
		}
		if (filter.getMaxPrice() != 0 || filter.getMinPrice() != 0) {
			countParameters++;
			insertFilterParametersInQueryMinMaxDouble(query, filter.getMinPrice(), filter.getMaxPrice(),
					FEEDS_AND_OTHER_PRICE);
		}
		String[] typeProduct = filter.getChoosedTypesProduct();
		if (typeProduct != null) {
			countParameters++;
			insertFilterParametersInQueryArrayString(query, typeProduct, FEEDS_AND_OTHER_TYPE);
		}
		String[] typePets = filter.getChoosedTypesPets();
		if (typePets != null) {
			countParameters++;
			insertFilterParametersInQueryArrayStringWithMatch(query, typePets, FEEDS_AND_OTHER_PET_TYPE);
		}
		String[] brandProduct = filter.getChoosedProductBrend();
		if (brandProduct != null) {
			countParameters++;
			insertFilterParametersInQueryArrayString(query, brandProduct, FEEDS_AND_OTHER_BRAND);
		}
		for (int i = 0; i < countParameters; i++) {
			query.append(")");
		}
		System.out.println(query.toString());
		return query.append(";").toString();
	}

	private void insertFilterParametersInQueryArrayString(StringBuilder query, String[] parameters,
			String nameParameter) {
		boolean haveFirstType = false;
		query.append(" and (");
		for (String type : parameters) {
			if (haveFirstType == false) {
				query.append(nameParameter).append(" = '").append(type).append("'");
				haveFirstType = true;
			} else {
				query.append(" or ").append(nameParameter).append(" = '").append(type).append("'");
			}
		}
	}

	private void insertFilterParametersInQueryArrayStringWithMatch(StringBuilder query, String[] parameters,
			String nameParameter) {
		boolean haveFirstType = false;
		query.append(" and (");
		for (String type : parameters) {
			if (haveFirstType == false) {
				query.append("match(").append(nameParameter).append(") Against('").append(type).append("'").append(")");
				haveFirstType = true;
			} else {
				query.append(" or ").append("match(").append(nameParameter).append(") Against('").append(type)
						.append("'").append(")");
			}
		}
	}

	private void insertFilterParametersInQueryMinMaxDouble(StringBuilder query, double min, double max,
			String nameParameter) {
		if (min > 0) {
			query.append(" and (").append(nameParameter).append(" > ").append(min);
			if (max != 0) {
				query.append(" and ").append(nameParameter).append(" < ").append(max);
			}
		} else {
			query.append(" and (").append(nameParameter).append(" < ").append(max);
		}
	}

}