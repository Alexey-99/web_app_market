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
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_IMAGE_PATH;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_FEEDS_AND_OTHER_IMAGE_PATH;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_ORDER_PRODUCTS_ORDER_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_FEEDS_AND_OTHER_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_ORDER_PRODUCTS_PRODUCT_FEEDS_AND_OTHER_ID;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.pool.ConnectionPool;
import by.koroza.zoo_market.dao.pool.ProxyConnection;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.status.ProductType;
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
	public Map<FeedAndOther, Long> getAllProductsAndNumberOfUnits() throws DaoException {
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
	 * Get the product by id.
	 *
	 * @param id the id
	 * @return the product by id
	 * @throws DaoException the dao exception
	 */
	@Override
	public FeedAndOther getProductById(long id) throws DaoException {
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

	private static final String QUERY_INSERT_PRODUCT_FEEDS_AND_OTHER_TO_ORDER_PRODUCTS = """
			INSERT INTO order_products(order_products.orders_id, order_products.product_types_id, order_products.feeds_and_other_id)
			VALUE(?, ?, ?);
			""";

	/**
	 * Transfer product from market to order.
	 *
	 * @param productId the product id
	 * @param orderId   the order id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean transferProductFromMarketToOrder(long productId, long orderId) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			long numberOfUnitsProduct = 0;
			try (PreparedStatement statement = connection
					.prepareStatement(QUERY_SELECT_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID)) {
				statement.setLong(1, productId);
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
					statement.setLong(2, productId);
					statement.execute();
				}
				try (PreparedStatement statement = connection
						.prepareStatement(QUERY_INSERT_PRODUCT_FEEDS_AND_OTHER_TO_ORDER_PRODUCTS)) {
					statement.setLong(1, orderId);
					statement.setInt(2, ProductType.FEEDS_AND_OTHER.getId());
					statement.setLong(3, productId);
					result = statement.executeUpdate() > 0;
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_SELECT_IS_HAVE_PRODUCT_FEEDS_AND_OTHER_IN_ORDER_BY_ID. */
	private static final String QUERY_SELECT_IS_HAVE_PRODUCT_FEEDS_AND_OTHER_IN_ORDER_BY_ID = """
			SELECT COUNT(order_products.orders_id)
			FROM order_products
			WHERE order_products.orders_id = ?
			AND order_products.product_types_id = ?
			AND order_products.feeds_and_other_id = ?;
			""";

	/**
	 * The Constant QUERY_DELETE_PRODUCT_FEEDS_AND_OTHER_BY_ID_FROM_ORDER_PRODUCTS.
	 */
	private static final String QUERY_DELETE_PRODUCT_FEEDS_AND_OTHER_BY_ID_FROM_ORDER_PRODUCTS = """
			DELETE
			FROM order_products
			WHERE order_products.orders_id = ?
			AND order_products.product_types_id = ?
			AND order_products.feeds_and_other_id = ?
			LIMIT 1;
			""";

	/**
	 * The Constant QUERY_SELECT_IS_HAVE_PRODUCT_FEEDS_AND_OTHER_IN_MARKET_BY_ID.
	 */
	private static final String QUERY_SELECT_IS_HAVE_PRODUCT_FEEDS_AND_OTHER_IN_MARKET_BY_ID = """
			SELECT COUNT(feeds_and_other.id)
			FROM feeds_and_other
			WHERE feeds_and_other.id = ?;
			""";

	/**
	 * Transfer product from order to market.
	 *
	 * @param productId the product id
	 * @param orderId   the order id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean transferProductFromOrderToMarket(long productId, long orderId) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			boolean isDeleteProiductInOrder = false;
			boolean isHaveProductInOrderByID = false;
			try (PreparedStatement statement = connection
					.prepareStatement(QUERY_SELECT_IS_HAVE_PRODUCT_FEEDS_AND_OTHER_IN_ORDER_BY_ID)) {
				statement.setLong(1, orderId);
				statement.setInt(2, ProductType.FEEDS_AND_OTHER.getId());
				statement.setLong(3, productId);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						isHaveProductInOrderByID = resultSet
								.getLong(IDENTIFIER_COUNT_ROWS_OF_ORDER_PRODUCTS_ORDER_ID) > 0;
					}
				}
			}
			if (isHaveProductInOrderByID) {
				try (PreparedStatement statement = connection
						.prepareStatement(QUERY_DELETE_PRODUCT_FEEDS_AND_OTHER_BY_ID_FROM_ORDER_PRODUCTS)) {
					statement.setLong(1, orderId);
					statement.setInt(2, ProductType.FEEDS_AND_OTHER.getId());
					statement.setLong(3, productId);
					isDeleteProiductInOrder = statement.executeUpdate() > 0;
				}
			}
			boolean isChangedNumberOfUnitsProductInMarket = false;
			boolean isHaveProductInMarketByID = false;
			try (PreparedStatement statement = connection
					.prepareStatement(QUERY_SELECT_IS_HAVE_PRODUCT_FEEDS_AND_OTHER_IN_MARKET_BY_ID)) {
				statement.setLong(1, productId);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						isHaveProductInMarketByID = resultSet.getLong(IDENTIFIER_COUNT_ROWS_OF_FEEDS_AND_OTHER_ID) > 0;
					}
				}
			}
			if (isHaveProductInMarketByID) {
				long numberOfUnitsProduct = 0;
				try (PreparedStatement statement = connection
						.prepareStatement(QUERY_SELECT_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID)) {
					statement.setLong(1, productId);
					try (ResultSet resultSet = statement.executeQuery()) {
						while (resultSet.next()) {
							numberOfUnitsProduct = resultSet.getLong(PETS_NUMBER_OF_UNITS_PRODUCT);
						}
					}
				}
				try (PreparedStatement statement = connection
						.prepareStatement(QUERY_CHANGE_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID)) {
					statement.setLong(1, numberOfUnitsProduct + 1);
					statement.setLong(2, productId);
					isChangedNumberOfUnitsProductInMarket = statement.executeUpdate() > 0;
				}
			}
			result = isDeleteProiductInOrder && isChangedNumberOfUnitsProductInMarket;
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_SELECT_FREE_NUMBER_OF_UNITS_BY_PRODUCT_ID. */
	private static final String QUERY_SELECT_FREE_NUMBER_OF_UNITS_BY_PRODUCT_ID = """
			SELECT feeds_and_other.number_of_units_products
			FROM feeds_and_other
			WHERE feeds_and_other.id = ?;
			""";

	/**
	 * Get the free number of units by product id.
	 *
	 * @param productId the product id
	 * @return the free number of units by product id
	 * @throws DaoException the dao exception
	 */
	@Override
	public long getFreeNumberOfUnitsByProductId(long productId) throws DaoException {
		long numberOfUnits = 0;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(QUERY_SELECT_FREE_NUMBER_OF_UNITS_BY_PRODUCT_ID)) {
			statement.setLong(1, productId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					numberOfUnits = resultSet.getLong(FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT);
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return numberOfUnits;
	}

	/** The Constant QUERY_SELECT_QUANTITY_IN_OPEN_ORDERS_BY_PRODUCT_ID. */
	private static final String QUERY_SELECT_QUANTITY_IN_OPEN_ORDERS_BY_PRODUCT_ID = """
			SELECT COUNT(order_products.feeds_and_other_id)
			FROM orders INNER JOIN order_products
				ON orders.id = order_products.orders_id
			WHERE orders.order_statuses_id = ?
				AND order_products.product_types_id = ?
				AND order_products.feeds_and_other_id = ?;
			""";

	/**
	 * Get the quantity in orders by product id and order status.
	 *
	 * @param productId     the product id
	 * @param orderStatusId the order status id
	 * @return the quantity in orders by product id and order status
	 * @throws DaoException the dao exception
	 */
	@Override
	public long getQuantityInOrdersByProductIdAndOrderStatus(long productId, int orderStatusId) throws DaoException {
		long quantity = 0;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(QUERY_SELECT_QUANTITY_IN_OPEN_ORDERS_BY_PRODUCT_ID)) {
			statement.setLong(1, orderStatusId);
			statement.setInt(2, ProductType.FEEDS_AND_OTHER.getId());
			statement.setLong(3, productId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					quantity = resultSet.getLong(IDENTIFIER_COUNT_ROWS_OF_ORDER_PRODUCTS_PRODUCT_FEEDS_AND_OTHER_ID);
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return quantity;
	}
}