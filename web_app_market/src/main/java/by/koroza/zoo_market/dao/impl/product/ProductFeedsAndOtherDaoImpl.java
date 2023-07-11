package by.koroza.zoo_market.dao.impl.product;

import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_BRAND;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_DATE_UPDATE;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_DESCRIPTION;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_DISCOUNT;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_PET_TYPE;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_PRICE;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_TYPE;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_LAST_INSERT_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_IMAGE_PATH;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_FEEDS_AND_OTHER_IMAGE_PATH;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.pool.ConnectionPool;
import by.koroza.zoo_market.dao.pool.ProxyConnection;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.dao.ProductFeedsAndOtherDao;
import by.koroza.zoo_market.dao.exception.checkable.DaoException;

/**
 * The Class ProductFeedsAndOtherDaoImpl.
 */
public class ProductFeedsAndOtherDaoImpl implements ProductFeedsAndOtherDao {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final ProductFeedsAndOtherDao INSTANCE = new ProductFeedsAndOtherDaoImpl();

	/**
	 * Instantiates a new product feeds and other dao impl.
	 */
	private ProductFeedsAndOtherDaoImpl() {
	}

	/**
	 * Get the single instance of ProductFeedsAndOtherDaoImpl.
	 *
	 * @return single instance of ProductFeedsAndOtherDaoImpl
	 */
	public static ProductFeedsAndOtherDao getInstance() {
		return INSTANCE;
	}

	/** The Constant QUERY_SELECT_ALL_HAVING_PRODUCTS_FEED_AND_OTHER. */
	private static final String QUERY_SELECT_ALL_HAVING_PRODUCTS_FEED_AND_OTHER = """
			SELECT feeds_and_other.id, feeds_and_other.image_path, feeds_and_other.type, feeds_and_other.brand, feeds_and_other.description,
			feeds_and_other.pet_type, feeds_and_other.price, feeds_and_other.discount, feeds_and_other.date_update, feeds_and_other.number_of_units_products
			FROM feeds_and_other
			WHERE feeds_and_other.number_of_units_products > 0;
			""";

	/**
	 * Get the all having products feed and other.
	 *
	 * @return the all having products feed and other
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<FeedAndOther> getAllHavingProductsFeedAndOther() throws DaoException {
		List<FeedAndOther> listFeedAndOther = new ArrayList<>();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(QUERY_SELECT_ALL_HAVING_PRODUCTS_FEED_AND_OTHER);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				for (int i = 0; i < resultSet.getLong(FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT); i++) {
					FeedAndOther feedAndOther = new FeedAndOther.FeedAndOtherBuilder()
							.setId(resultSet.getLong(FEEDS_AND_OTHER_ID))
							.setImagePath(resultSet.getString(FEEDS_AND_OTHER_IMAGE_PATH))
							.setProductType(resultSet.getString(FEEDS_AND_OTHER_TYPE))
							.setBrand(resultSet.getString(FEEDS_AND_OTHER_BRAND))
							.setDescriptions(resultSet.getString(FEEDS_AND_OTHER_DESCRIPTION))
							.setPetTypes(resultSet.getString(FEEDS_AND_OTHER_PET_TYPE))
							.setPrice(resultSet.getDouble(FEEDS_AND_OTHER_PRICE))
							.setDiscount(resultSet.getDouble(FEEDS_AND_OTHER_DISCOUNT))
							.setUpdateDateTime(resultSet.getDate(FEEDS_AND_OTHER_DATE_UPDATE).toLocalDate(),
									resultSet.getTime(FEEDS_AND_OTHER_DATE_UPDATE).toLocalTime())
							.build();
					feedAndOther.setTotalPrice(
							feedAndOther.getPrice() - (feedAndOther.getPrice() * feedAndOther.getDiscount() / 100));
					listFeedAndOther.add(feedAndOther);
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return listFeedAndOther;
	}

	/** The Constant CODE_OF_TYPE_PRODUCT_FEEDS_AND_OTHER. */
	private static final char CODE_OF_TYPE_PRODUCT_FEEDS_AND_OTHER = 'o';

	/** The Constant QUERY_SELECT_PRODUCTS_FEED_AND_OTHER_HAVING_BY_ID. */
	private static final String QUERY_SELECT_PRODUCTS_FEED_AND_OTHER_HAVING_BY_ID = """
			SELECT feeds_and_other.id, feeds_and_other.image_path, feeds_and_other.type, feeds_and_other.brand, feeds_and_other.description,
			feeds_and_other.pet_type, feeds_and_other.price, feeds_and_other.discount, feeds_and_other.date_update, feeds_and_other.number_of_units_products
			FROM feeds_and_other
			WHERE feeds_and_other.number_of_units_products > 0 AND feeds_and_other.id = ?;
			""";

	/**
	 * Get the having products feed and other by product id.
	 *
	 * @param productsIdMap the products id map
	 * @return the having products feed and other by id
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<FeedAndOther> getHavingProductsFeedAndOtherById(Map<String, String> productsIdMap) throws DaoException {
		List<FeedAndOther> listFeedAndOther = new ArrayList<>();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			for (Map.Entry<String, String> entry : productsIdMap.entrySet()) {
				char productType = entry.getKey().charAt(0);
				String id = entry.getValue();
				if (productType == CODE_OF_TYPE_PRODUCT_FEEDS_AND_OTHER) {
					try (PreparedStatement statement = connection
							.prepareStatement(QUERY_SELECT_PRODUCTS_FEED_AND_OTHER_HAVING_BY_ID)) {
						statement.setLong(1, Long.parseLong(id));
						try (ResultSet resultSet = statement.executeQuery()) {
							while (resultSet.next()) {
								FeedAndOther feedAndOther = new FeedAndOther.FeedAndOtherBuilder()
										.setId(resultSet.getLong(FEEDS_AND_OTHER_ID))
										.setImagePath(resultSet.getString(FEEDS_AND_OTHER_IMAGE_PATH))
										.setProductType(resultSet.getString(FEEDS_AND_OTHER_TYPE))
										.setBrand(resultSet.getString(FEEDS_AND_OTHER_BRAND))
										.setDescriptions(resultSet.getString(FEEDS_AND_OTHER_DESCRIPTION))
										.setPetTypes(resultSet.getString(FEEDS_AND_OTHER_PET_TYPE))
										.setPrice(resultSet.getDouble(FEEDS_AND_OTHER_PRICE))
										.setDiscount(resultSet.getDouble(FEEDS_AND_OTHER_DISCOUNT))
										.setUpdateDateTime(resultSet.getDate(FEEDS_AND_OTHER_DATE_UPDATE).toLocalDate(),
												resultSet.getTime(FEEDS_AND_OTHER_DATE_UPDATE).toLocalTime())
										.build();
								feedAndOther.setTotalPrice(feedAndOther.getPrice()
										- (feedAndOther.getPrice() * feedAndOther.getDiscount() / 100));
								listFeedAndOther.add(feedAndOther);
							}
						}
					}
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return listFeedAndOther;
	}

	/** The Constant QUERY_SELECT_ALL_PRODUCTS_FEED_AND_OTHER. */
	private static final String QUERY_SELECT_ALL_PRODUCTS_FEED_AND_OTHER = """
			SELECT feeds_and_other.id, feeds_and_other.image_path, feeds_and_other.type, feeds_and_other.brand, feeds_and_other.description,
			feeds_and_other.pet_type, feeds_and_other.price, feeds_and_other.discount, feeds_and_other.date_update, feeds_and_other.number_of_units_products
			FROM feeds_and_other;
			""";

	/**
	 * Get the all products feed and other and number of units.
	 *
	 * @return the all products feed and other and number of units
	 * @throws DaoException the dao exception
	 */
	@Override
	public Map<FeedAndOther, Long> getAllProductsFeedAndOtherAndNumberOfUnits() throws DaoException {
		Map<FeedAndOther, Long> mapFeedAndOtherAndNumber = new HashMap<>();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_ALL_PRODUCTS_FEED_AND_OTHER);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				FeedAndOther feedAndOther = new FeedAndOther.FeedAndOtherBuilder()
						.setId(resultSet.getLong(FEEDS_AND_OTHER_ID))
						.setImagePath(resultSet.getString(FEEDS_AND_OTHER_IMAGE_PATH))
						.setProductType(resultSet.getString(FEEDS_AND_OTHER_TYPE))
						.setBrand(resultSet.getString(FEEDS_AND_OTHER_BRAND))
						.setDescriptions(resultSet.getString(FEEDS_AND_OTHER_DESCRIPTION))
						.setPetTypes(resultSet.getString(FEEDS_AND_OTHER_PET_TYPE))
						.setPrice(resultSet.getDouble(FEEDS_AND_OTHER_PRICE))
						.setDiscount(resultSet.getDouble(FEEDS_AND_OTHER_DISCOUNT))
						.setUpdateDateTime(resultSet.getDate(FEEDS_AND_OTHER_DATE_UPDATE).toLocalDate(),
								resultSet.getTime(FEEDS_AND_OTHER_DATE_UPDATE).toLocalTime())
						.build();
				feedAndOther.setTotalPrice(
						feedAndOther.getPrice() - (feedAndOther.getPrice() * feedAndOther.getDiscount() / 100));
				mapFeedAndOtherAndNumber.put(feedAndOther, resultSet.getLong(FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT));
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return mapFeedAndOtherAndNumber;
	}

	/** The Constant QUERY_SELECT_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID. */
	private static final String QUERY_SELECT_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID = """
			SELECT feeds_and_other.number_of_units_products
			FROM feeds_and_other
			WHERE feeds_and_other.id = ?;
			""";

	/** The Constant QUERY_CHANGE_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID. */
	private static final String QUERY_CHANGE_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID = """
			UPDATE feeds_and_other
			SET feeds_and_other.number_of_units_products = ?
			WHERE feeds_and_other.id = ?;
			""";

	@Override
	public Map<Integer, Boolean> changeNumberOfUnitsProductsMinus(List<FeedAndOther> productsFeedAndOther)
			throws DaoException {
		Map<Integer, Boolean> haveProductByIndex = new LinkedHashMap<>();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			for (int i = 0; i < productsFeedAndOther.size(); i++) {
				long numberOfUnitsProduct = 0;
				try (PreparedStatement statement = connection
						.prepareStatement(QUERY_SELECT_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID)) {
					statement.setLong(1, productsFeedAndOther.get(i).getId());
					try (ResultSet resultSet = statement.executeQuery()) {
						while (resultSet.next()) {
							numberOfUnitsProduct = resultSet.getLong(FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT);
						}
					}
				}
				if (numberOfUnitsProduct > 0) {
					try (PreparedStatement statement = connection
							.prepareStatement(QUERY_CHANGE_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID)) {
						statement.setLong(1, numberOfUnitsProduct - 1);
						statement.setLong(2, productsFeedAndOther.get(i).getId());
						haveProductByIndex.put(i, statement.executeUpdate() > 0);
					}
				} else {
					haveProductByIndex.put(i, false);
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return haveProductByIndex;
	}

	@Override
	public boolean changeNumberOfUnitsProductsPlus(List<FeedAndOther> productsFeedAndOther,
			Map<Integer, Boolean> haveProductByIndex) throws DaoException {
		int countChanging = 0;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			for (int i = 0; i < productsFeedAndOther.size(); i++) {
				if (haveProductByIndex.get(i)) {
					long numberOfUnitsProduct = 0;
					try (PreparedStatement statement = connection
							.prepareStatement(QUERY_SELECT_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID)) {
						statement.setLong(1, productsFeedAndOther.get(i).getId());
						try (ResultSet resultSet = statement.executeQuery()) {
							while (resultSet.next()) {
								numberOfUnitsProduct = resultSet.getLong(FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT);
							}
						}
					}
					try (PreparedStatement statement = connection
							.prepareStatement(QUERY_CHANGE_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID)) {
						statement.setLong(1, numberOfUnitsProduct + 1);
						statement.setLong(2, productsFeedAndOther.get(i).getId());
						if (statement.executeUpdate() > 0) {
							countChanging++;
						}
					}
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return countChanging == numberNeedChangeProducts(haveProductByIndex);
	}

	private int numberNeedChangeProducts(Map<Integer, Boolean> haveProductByIndex) {
		int countChanging = 0;
		for (Map.Entry<Integer, Boolean> entry : haveProductByIndex.entrySet()) {
			if (entry.getValue()) {
				countChanging++;
			}
		}
		return countChanging;
	}

	/** The Constant QUERY_INSERT_PRODUCT_FEEDS_AND_OTHER. */
	private static final String QUERY_INSERT_PRODUCT_FEEDS_AND_OTHER = """
			INSERT INTO feeds_and_other(feeds_and_other.image_path, feeds_and_other.type, feeds_and_other.brand, feeds_and_other.description,
			feeds_and_other.pet_type, feeds_and_other.price, feeds_and_other.discount, feeds_and_other.number_of_units_products)
			VALUE(?, ?, ?, ?, ?, ?, ?, ?);
			""";

	/** The Constant QUERY_SELECT_LAST_INSERT_ID. */
	private static final String QUERY_SELECT_LAST_INSERT_ID = """
			SELECT LAST_INSERT_ID();
			""";

	/**
	 * Add the product.
	 *
	 * @param product              the product
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean addProduct(FeedAndOther product, long numberOfUnitsProduct) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_PRODUCT_FEEDS_AND_OTHER)) {
				statement.setString(1, product.getImagePath());
				statement.setString(2, product.getProductType());
				statement.setString(3, product.getBrand());
				statement.setString(4, product.getDescriptions());
				statement.setString(5,
						product.getPetTypes().toString().substring(1, product.getPetTypes().toString().length() - 1));
				statement.setDouble(6, product.getPrice());
				statement.setDouble(7, product.getDiscount());
				statement.setLong(8, numberOfUnitsProduct);
				result = statement.executeUpdate() > 0;
			}
			try (PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_LAST_INSERT_ID);
					ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					product.setId(resultSet.getLong(IDENTIFIER_LAST_INSERT_ID));
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_SELECT_PRODUCT_FEED_AND_OTHER_BY_ID. */
	private static final String QUERY_SELECT_PRODUCT_FEED_AND_OTHER_BY_ID = """
			SELECT feeds_and_other.id, feeds_and_other.image_path, feeds_and_other.type, feeds_and_other.brand, feeds_and_other.description,
			feeds_and_other.pet_type, feeds_and_other.price, feeds_and_other.discount, feeds_and_other.date_update, feeds_and_other.number_of_units_products
			FROM feeds_and_other
			WHERE feeds_and_other.number_of_units_products > 0 AND feeds_and_other.id = ?;
			""";

	/**
	 * Get the product feed and other by product id.
	 *
	 * @param id the id
	 * @return the product feed and other by id
	 * @throws DaoException the dao exception
	 */
	@Override
	public FeedAndOther getProductFeedAndOtherById(long id) throws DaoException {
		FeedAndOther feedAndOther = null;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_PRODUCT_FEED_AND_OTHER_BY_ID)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					feedAndOther = new FeedAndOther.FeedAndOtherBuilder().setId(resultSet.getLong(FEEDS_AND_OTHER_ID))
							.setImagePath(resultSet.getString(FEEDS_AND_OTHER_IMAGE_PATH))
							.setProductType(resultSet.getString(FEEDS_AND_OTHER_TYPE))
							.setBrand(resultSet.getString(FEEDS_AND_OTHER_BRAND))
							.setDescriptions(resultSet.getString(FEEDS_AND_OTHER_DESCRIPTION))
							.setPetTypes(resultSet.getString(FEEDS_AND_OTHER_PET_TYPE))
							.setPrice(resultSet.getDouble(FEEDS_AND_OTHER_PRICE))
							.setDiscount(resultSet.getDouble(FEEDS_AND_OTHER_DISCOUNT))
							.setUpdateDateTime(resultSet.getDate(FEEDS_AND_OTHER_DATE_UPDATE).toLocalDate(),
									resultSet.getTime(FEEDS_AND_OTHER_DATE_UPDATE).toLocalTime())
							.build();
					feedAndOther.setTotalPrice(
							feedAndOther.getPrice() - (feedAndOther.getPrice() * feedAndOther.getDiscount() / 100));
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return feedAndOther;
	}

	/** The Constant QUERY_UPDETE_PRODUCT_BY_ID. */
	private static final String QUERY_UPDETE_PRODUCT_BY_ID = """
			UPDATE feeds_and_other
			SET feeds_and_other.image_path = ?,
			feeds_and_other.type = ?,
			feeds_and_other.brand = ?,
			feeds_and_other.description = ?,
			feeds_and_other.pet_type = ?,
			feeds_and_other.price = ?,
			feeds_and_other.discount = ?,
			feeds_and_other.date_update = NOW(),
			feeds_and_other.number_of_units_products = ?
			WHERE feeds_and_other.id = ?;
			""";

	/**
	 * Update product by id.
	 *
	 * @param product              the product
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean updateProductById(FeedAndOther product, long numberOfUnitsProduct) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(QUERY_UPDETE_PRODUCT_BY_ID)) {
				statement.setString(1, product.getImagePath());
				statement.setString(2, product.getProductType());
				statement.setString(3, product.getBrand());
				statement.setString(4, product.getDescriptions());
				statement.setString(5,
						product.getPetTypes().toString().substring(1, product.getPetTypes().toString().length() - 1));
				statement.setDouble(6, product.getPrice());
				statement.setDouble(7, product.getDiscount());
				statement.setLong(8, numberOfUnitsProduct);
				statement.setLong(9, product.getId());
				result = statement.executeUpdate() > 0;
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_SELECT_COUNT_PRODUCTS_WITH_IMAGE_PATH. */
	private static final String QUERY_SELECT_COUNT_PRODUCTS_WITH_IMAGE_PATH = """
			SELECT COUNT(feeds_and_other.image_path)
			FROM feeds_and_other
			WHERE feeds_and_other.image_path = ?;
			""";

	/**
	 * Exists product with image path.
	 *
	 * @param imagePath the image path
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean existsProductWithImagePath(String imagePath) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(QUERY_SELECT_COUNT_PRODUCTS_WITH_IMAGE_PATH)) {
			statement.setString(1, imagePath);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					result = resultSet.getInt(IDENTIFIER_COUNT_ROWS_OF_FEEDS_AND_OTHER_IMAGE_PATH) > 0;
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_SELECT_PRODUCT_IMAGE_PATH_BY_ID. */
	private static final String QUERY_SELECT_PRODUCT_IMAGE_PATH_BY_ID = """
			SELECT feeds_and_other.image_path
			FROM feeds_and_other
			WHERE feeds_and_other.id = ?;
			""";

	/**
	 * Get the product image path by id.
	 *
	 * @param id the id
	 * @return the product image path by id
	 * @throws DaoException the dao exception
	 */
	@Override
	public String getProductImagePathById(long id) throws DaoException {
		String imagePath = null;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_PRODUCT_IMAGE_PATH_BY_ID)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					imagePath = resultSet.getString(FEEDS_AND_OTHER_IMAGE_PATH);
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return imagePath;
	}
}