package by.koroza.zoo_market.dao.impl.order;

import static by.koroza.zoo_market.dao.name.ColumnName.ORDERS_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.ORDERS_USERS_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.ORDERS_TOTAL_PAYMENT_AMOUNT;
import static by.koroza.zoo_market.dao.name.ColumnName.ORDERS_TOTAL_DISCOUNT_AMOUNT;
import static by.koroza.zoo_market.dao.name.ColumnName.ORDERS_TOTAL_PAYMENT_WITH_DISCOUNT_AMOUNT;
import static by.koroza.zoo_market.dao.name.ColumnName.ORDERS_TOTAL_PRODUCTS_DISCOUNT_AMOUNT;
import static by.koroza.zoo_market.dao.name.ColumnName.ORDERS_TOTAL_PERSON_DISCOUNT_AMOUNT;
import static by.koroza.zoo_market.dao.name.ColumnName.ORDERS_DATE;
import static by.koroza.zoo_market.dao.name.ColumnName.ORDERS_STATUS_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_SPECIE;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_BREED;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_BIRTH_DATE;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_IMAGE_PATH;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_PRICE;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_DISCOUNT;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_IMAGE_PATH;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_TYPE;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_BRAND;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_DESCRIPTION;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_PET_TYPE;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_PRICE;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_DISCOUNT;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_LAST_INSERT_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_ORDERS_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_ORDER_PRODUCTS_PRODUCT_PETS_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_ORDER_PRODUCTS_PRODUCT_FEEDS_AND_OTHER_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.USERS_LOGIN;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.pool.ConnectionPool;
import by.koroza.zoo_market.dao.pool.ProxyConnection;
import by.koroza.zoo_market.model.entity.detalization.OrderDetalizationByProduct;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.status.OrderStatus;
import by.koroza.zoo_market.model.entity.status.ProductType;
import by.koroza.zoo_market.dao.OrderDao;
import by.koroza.zoo_market.dao.exception.checkable.DaoException;

/**
 * The Class OrderDaoImpl.
 */
public class OrderDaoImpl implements OrderDao {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final OrderDao INSTANCE = new OrderDaoImpl();

	/** The Constant QUERY_SELECT_LAST_INSERT_ID. */
	private static final String QUERY_SELECT_LAST_INSERT_ID = """
			SELECT LAST_INSERT_ID();
			""";

	/**
	 * Instantiates a new order dao impl.
	 */
	private OrderDaoImpl() {
	}

	/**
	 * Get the single instance of OrderDaoImpl.
	 *
	 * @return single instance of OrderDaoImpl
	 */
	public static OrderDao getInstance() {
		return INSTANCE;
	}

	/** The Constant QUERY_SELECT_ORDERS_BY_USER_ID. */
	private static final String QUERY_SELECT_ORDERS_BY_USER_ID = """
			SELECT orders.id, orders.users_id, orders.total_payment_amount,
			orders.total_products_discount_amount, orders.total_person_discount_amount,
			orders.total_discount_amount, orders.total_payment_with_discount_amount, orders.date,
			orders.order_statuses_id
			FROM orders INNER JOIN order_statuses
			ON orders.order_statuses_id = order_statuses.id
			WHERE orders.users_id = ?;
			""";

	/** The Constant QUERY_SELECT_ORDER_PRODUCTS_PETS_BY_ORDERS_ID. */
	private static final String QUERY_SELECT_ORDER_PRODUCTS_PETS_BY_ORDERS_ID = """
			SELECT order_products.orders_id, pets.image_path, pets.id, pets.specie,
			pets.breed, pets.birth_date, pets.price, pets.discount
			FROM order_products INNER JOIN pets
			ON order_products.pets_id = pets.id
			WHERE order_products.orders_id = ? AND order_products.product_types_id = ?;
			""";

	/** The Constant QUERY_SELECT_ORDER_PRODUCTS_OTHER_PRODUCTS_BY_ORDERS_ID. */
	private static final String QUERY_SELECT_ORDER_PRODUCTS_OTHER_PRODUCTS_BY_ORDERS_ID = """
			SELECT order_products.orders_id, feeds_and_other.image_path, feeds_and_other.id,
			feeds_and_other.type, feeds_and_other.brand, feeds_and_other.description,
			feeds_and_other.pet_type, feeds_and_other.price, feeds_and_other.discount
			FROM order_products INNER JOIN feeds_and_other
			ON order_products.feeds_and_other_id = feeds_and_other.id
			WHERE order_products.orders_id = ? AND order_products.product_types_id = ?;
			""";

	/**
	 * Get the order with products by user id.
	 *
	 * @param userId the user id
	 * @return the order products by user id
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<Order> getOrderProductsByUserId(long userId) throws DaoException {
		List<Order> orders = new ArrayList<>();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_ORDERS_BY_USER_ID)) {
				statement.setLong(1, userId);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						Order order = new Order.OrderBuilder().setId(resultSet.getLong(ORDERS_ID))
								.setUserId(resultSet.getLong(ORDERS_USERS_ID))
								.setTotalPaymentAmount(resultSet.getDouble(ORDERS_TOTAL_PAYMENT_AMOUNT))
								.setTotalDiscountAmount(resultSet.getDouble(ORDERS_TOTAL_DISCOUNT_AMOUNT))
								.setTotalPaymentWithDiscountAmount(
										resultSet.getDouble(ORDERS_TOTAL_PAYMENT_WITH_DISCOUNT_AMOUNT))
								.setDateCreation(resultSet.getDate(ORDERS_DATE).toLocalDate())
								.setStatus(resultSet.getInt(ORDERS_STATUS_ID)).build();
						orders.add(order);
					}
				}
			}
			for (Order order : orders) {
				try (PreparedStatement statement = connection
						.prepareStatement(QUERY_SELECT_ORDER_PRODUCTS_PETS_BY_ORDERS_ID)) {
					statement.setLong(1, order.getId());
					statement.setLong(2, ProductType.PETS.getId());
					Map<Pet, Long> productsMap = new HashMap<>();
					try (ResultSet resultSet = statement.executeQuery()) {
						while (resultSet.next()) {
							Pet pet = new Pet.PetBuilder().setImagePath(resultSet.getString(PETS_IMAGE_PATH))
									.setId(resultSet.getLong(PETS_ID)).setSpecie(resultSet.getString(PETS_SPECIE))
									.setBreed(resultSet.getString(PETS_BREED))
									.setBirthDate(resultSet.getDate(PETS_BIRTH_DATE).toLocalDate()).build();
							if (productsMap.containsKey(pet)) {
								productsMap.put(pet, productsMap.get(pet) + 1);
							} else {
								productsMap.put(pet, 1L);
							}
						}
					}
					order.setProductsPets(productsMap.entrySet().stream().toList());
				}
			}
			for (Order order : orders) {
				try (PreparedStatement statement = connection
						.prepareStatement(QUERY_SELECT_ORDER_PRODUCTS_OTHER_PRODUCTS_BY_ORDERS_ID)) {
					statement.setLong(1, order.getId());
					statement.setLong(2, ProductType.FEEDS_AND_OTHER.getId());
					Map<FeedAndOther, Long> productsMap = new HashMap<>();
					try (ResultSet resultSet = statement.executeQuery()) {
						while (resultSet.next()) {
							FeedAndOther feedAndOther = new FeedAndOther.FeedAndOtherBuilder()
									.setId(resultSet.getLong(FEEDS_AND_OTHER_ID))
									.setImagePath(resultSet.getString(FEEDS_AND_OTHER_IMAGE_PATH))
									.setProductType(resultSet.getString(FEEDS_AND_OTHER_TYPE))
									.setBrand(resultSet.getString(FEEDS_AND_OTHER_BRAND))
									.setDescriptions(resultSet.getString(FEEDS_AND_OTHER_DESCRIPTION))
									.setPetTypes(resultSet.getString(FEEDS_AND_OTHER_PET_TYPE)).build();
							if (productsMap.containsKey(feedAndOther)) {
								productsMap.put(feedAndOther, productsMap.get(feedAndOther) + 1);
							} else {
								productsMap.put(feedAndOther, 1L);
							}
						}
					}
					order.setOtherProducts(productsMap.entrySet().stream().toList());
				}
			}
		} catch (SQLException | IllegalArgumentException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return orders;
	}

	/** The Constant QUERY_INSERT_ORDER. */
	private static final String QUERY_INSERT_ORDER = """
			INSERT INTO orders(orders.users_id, orders.total_payment_amount, orders.total_products_discount_amount,
			orders.total_person_discount_amount, orders.total_discount_amount, orders.total_payment_with_discount_amount, orders.order_statuses_id)
			VALUE(?, ?, ?, ?, ?, ?, ?);
			""";

	/** The Constant QUERY_INSERT_ORDER_PRODUCTS. */
	private static final String QUERY_INSERT_ORDER_PRODUCTS = """
			INSERT INTO order_products(order_products.orders_id, order_products.product_types_id, order_products.pets_id, order_products.feeds_and_other_id)
			VALUE(?, ?, ?, ?);
			""";

	/**
	 * Add the order with products.
	 *
	 * @param order  the order
	 * @param userId the user id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean addOrder(Order order, long userId) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			boolean resultInsetOrder = false;
			try (PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_ORDER)) {
				statement.setLong(1, userId);
				statement.setDouble(2, order.getTotalPaymentAmount());
				statement.setDouble(3, order.getTotalProductsDiscountAmount());
				statement.setDouble(4, order.getTotalPersonDiscountAmount());
				statement.setDouble(5, order.getTotalDiscountAmount());
				statement.setDouble(6, order.getTotalPaymentWithDiscountAmount());
				statement.setInt(7, order.getStatus().getId());
				resultInsetOrder = statement.executeUpdate() > 0;
			}
			try (PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_LAST_INSERT_ID);
					ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					order.setId(resultSet.getLong(IDENTIFIER_LAST_INSERT_ID));
				}
			}
			boolean resultInsetOrderProducts = false;
			try (PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_ORDER_PRODUCTS)) {
				int countInserts = 0;
				for (Entry<Pet, Long> entry : order.getProductsPets()) {
					statement.setLong(1, order.getId());
					statement.setLong(2, ProductType.PETS.getId());
					statement.setLong(3, entry.getKey().getId());
					statement.setNull(4, Types.BIGINT);
					if (statement.executeUpdate() > 0) {
						countInserts++;
					}
				}
				for (Entry<FeedAndOther, Long> entry : order.getOtherProducts()) {
					statement.setLong(1, order.getId());
					statement.setLong(2, ProductType.FEEDS_AND_OTHER.getId());
					statement.setNull(3, Types.BIGINT);
					statement.setLong(4, entry.getKey().getId());
					if (statement.executeUpdate() > 0) {
						countInserts++;
					}
				}
				resultInsetOrderProducts = countInserts == (order.getOtherProducts().size()
						+ order.getProductsPets().size());
			}
			result = resultInsetOrder == true && resultInsetOrderProducts == true;
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_SELECT_ORDERS_BY_USER_ID_AND_STATUS. */
	private static final String QUERY_SELECT_ORDERS_BY_USER_ID_AND_STATUS = """
			SELECT orders.id, orders.users_id, orders.total_payment_amount, orders.total_products_discount_amount,
			orders.total_person_discount_amount, orders.total_discount_amount, orders.total_payment_with_discount_amount, orders.order_statuses_id
			FROM orders
			WHERE orders.users_id = ? AND orders.order_statuses_id = ?;
			""";

	/**
	 * Get the order with products by user id and order status.
	 *
	 * @param userId the user id
	 * @param status the status
	 * @return the order with products by user id and order status
	 * @throws DaoException the dao exception
	 */
	@Override
	public Order getOrderWithProductsByUserIdAndOrderStatus(long userId, OrderStatus status) throws DaoException {
		Order orderResult = null;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_ORDERS_BY_USER_ID_AND_STATUS)) {
				statement.setLong(1, userId);
				statement.setInt(2, status.getId());
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						orderResult = new Order.OrderBuilder().setId(resultSet.getLong(ORDERS_ID))
								.setUserId(resultSet.getLong(ORDERS_USERS_ID))
								.setTotalPaymentAmount(resultSet.getDouble(ORDERS_TOTAL_PAYMENT_AMOUNT))
								.setTotalProductsDiscountAmount(
										resultSet.getDouble(ORDERS_TOTAL_PRODUCTS_DISCOUNT_AMOUNT))
								.setTotalPersonDiscountAmount(resultSet.getDouble(ORDERS_TOTAL_PERSON_DISCOUNT_AMOUNT))
								.setTotalDiscountAmount(resultSet.getDouble(ORDERS_TOTAL_DISCOUNT_AMOUNT))
								.setTotalPaymentWithDiscountAmount(
										resultSet.getDouble(ORDERS_TOTAL_PAYMENT_WITH_DISCOUNT_AMOUNT))
								.setStatus(resultSet.getInt(ORDERS_STATUS_ID)).build();
					}
				}
			}
			try (PreparedStatement statement = connection
					.prepareStatement(QUERY_SELECT_ORDER_PRODUCTS_PETS_BY_ORDERS_ID)) {
				statement.setLong(1, orderResult.getId());
				statement.setLong(2, ProductType.PETS.getId());
				Map<Pet, Long> productsMap = new HashMap<>();
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						Pet pet = new Pet.PetBuilder().setImagePath(resultSet.getString(PETS_IMAGE_PATH))
								.setId(resultSet.getLong(PETS_ID)).setSpecie(resultSet.getString(PETS_SPECIE))
								.setBreed(resultSet.getString(PETS_BREED))
								.setBirthDate(resultSet.getDate(PETS_BIRTH_DATE).toLocalDate())
								.setPrice(resultSet.getDouble(PETS_PRICE))
								.setDiscount(resultSet.getDouble(PETS_DISCOUNT)).build();
						pet.setTotalPrice(pet.getPrice() - (pet.getPrice() * pet.getDiscount() / 100));
						if (productsMap.containsKey(pet)) {
							productsMap.put(pet, productsMap.get(pet) + 1);
						} else {
							productsMap.put(pet, 1L);
						}
					}
				}
				orderResult.setProductsPets(productsMap.entrySet().stream().toList());
			}
			try (PreparedStatement statement = connection
					.prepareStatement(QUERY_SELECT_ORDER_PRODUCTS_OTHER_PRODUCTS_BY_ORDERS_ID)) {
				statement.setLong(1, orderResult.getId());
				statement.setLong(2, ProductType.FEEDS_AND_OTHER.getId());
				Map<FeedAndOther, Long> productsMap = new HashMap<>();
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
								.setDiscount(resultSet.getDouble(FEEDS_AND_OTHER_DISCOUNT)).build();
						feedAndOther.setTotalPrice(
								feedAndOther.getPrice() - (feedAndOther.getPrice() * feedAndOther.getDiscount() / 100));
						if (productsMap.containsKey(feedAndOther)) {
							productsMap.put(feedAndOther, productsMap.get(feedAndOther) + 1);
						} else {
							productsMap.put(feedAndOther, 1L);
						}
					}
				}
				orderResult.setOtherProducts(productsMap.entrySet().stream().toList());
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return orderResult;
	}

	/** The Constant QUERY_SELECT_IS_HAVE_OPEN_ORDER. */
	private static final String QUERY_SELECT_IS_HAVE_OPEN_ORDER = """
			SELECT COUNT(orders.id)
			FROM orders
			WHERE orders.order_statuses_id = ? AND orders.users_id = ?;
			""";

	/**
	 * Check if is have open order by user id.
	 *
	 * @param userId the user id
	 * @return true, if is have open order by user id
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean isHaveOpenOrderByUserId(long userId) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_IS_HAVE_OPEN_ORDER)) {
			statement.setInt(1, OrderStatus.OPEN.getId());
			statement.setLong(2, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					result = resultSet.getInt(IDENTIFIER_COUNT_ROWS_OF_ORDERS_ID) > 0;
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_UPDATE_ORDER. */
	private static final String QUERY_UPDATE_ORDER = """
			UPDATE orders
			SET orders.total_payment_amount = ?,
			orders.total_products_discount_amount = ?,
			orders.total_person_discount_amount = ?,
			orders.total_discount_amount = ?,
			orders.total_payment_with_discount_amount = ?,
			orders.date = NOW(),
			orders.order_statuses_id = ?
			WHERE orders.id = ?;
			""";

	/**
	 * Change order.
	 *
	 * @param order the order
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean changeOrder(Order order) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_ORDER)) {
			statement.setDouble(1, order.getTotalPaymentAmount());
			statement.setDouble(2, order.getTotalProductsDiscountAmount());
			statement.setDouble(3, order.getTotalPersonDiscountAmount());
			statement.setDouble(4, order.getTotalDiscountAmount());
			statement.setDouble(5, order.getTotalPaymentWithDiscountAmount());
			statement.setInt(6, order.getStatus().getId());
			statement.setLong(7, order.getId());
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/**
	 * The Constant
	 * SELECT_GET_DETAILS_INFORMATION_ABOUT_ORDERS_BY_PRODUCT_PET_ID_AND_ORDER_STATUS.
	 */
	private static final String SELECT_GET_DETAILS_INFORMATION_ABOUT_ORDERS_BY_PRODUCT_PET_ID_AND_ORDER_STATUS = """
			SELECT orders.id, orders.total_payment_with_discount_amount, orders.users_id, users.login, COUNT(order_products.pets_id)
			FROM orders INNER JOIN order_products INNER JOIN users
				ON orders.id = order_products.orders_id AND users.id = orders.users_id
			WHERE orders.order_statuses_id = ?
				AND order_products.product_types_id = ?
				AND order_products.pets_id = ?
			GROUP BY orders.id;
			""";

	/**
	 * Get the details about orders by product id and order status.
	 *
	 * @param orderStatusId the order status id
	 * @param productId     the product id
	 * @return the details about orders by product id and order status
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<OrderDetalizationByProduct> getDetailsAboutOrdersByProductPetIdAndOrderStatus(int orderStatusId,
			long productId) throws DaoException {
		List<OrderDetalizationByProduct> listDetails = new LinkedList<>();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						SELECT_GET_DETAILS_INFORMATION_ABOUT_ORDERS_BY_PRODUCT_PET_ID_AND_ORDER_STATUS)) {
			statement.setInt(1, orderStatusId);
			statement.setInt(2, ProductType.PETS.getId());
			statement.setLong(3, productId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					listDetails.add(new OrderDetalizationByProduct(resultSet.getLong(ORDERS_ID),
							resultSet.getLong(IDENTIFIER_COUNT_ROWS_OF_ORDER_PRODUCTS_PRODUCT_PETS_ID),
							resultSet.getDouble(ORDERS_TOTAL_PAYMENT_WITH_DISCOUNT_AMOUNT),
							resultSet.getLong(ORDERS_USERS_ID), resultSet.getString(USERS_LOGIN)));
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return listDetails;
	}

	/**
	 * The Constant
	 * SELECT_GET_DETAILS_INFORMATION_ABOUT_ORDERS_BY_PRODUCT_FEED_AND_OTHER_ID_AND_ORDER_STATUS.
	 */
	private static final String SELECT_GET_DETAILS_INFORMATION_ABOUT_ORDERS_BY_PRODUCT_FEED_AND_OTHER_ID_AND_ORDER_STATUS = """
			SELECT orders.id, orders.total_payment_with_discount_amount, orders.users_id, users.login, COUNT(order_products.feeds_and_other_id)
			FROM orders INNER JOIN order_products INNER JOIN users
				ON orders.id = order_products.orders_id AND users.id = orders.users_id
			WHERE orders.order_statuses_id = ?
				AND order_products.product_types_id = ?
				AND order_products.feeds_and_other_id = ?
			GROUP BY orders.id;
			""";

	/**
	 * Get the details about orders by product feed and other id and order status.
	 *
	 * @param orderStatusId the order status id
	 * @param productId     the product id
	 * @return the details about orders by product feed and other id and order
	 *         status
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<OrderDetalizationByProduct> getDetailsAboutOrdersByProductFeedAndOtherIdAndOrderStatus(
			int orderStatusId, long productId) throws DaoException {
		List<OrderDetalizationByProduct> listDetails = new LinkedList<>();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						SELECT_GET_DETAILS_INFORMATION_ABOUT_ORDERS_BY_PRODUCT_FEED_AND_OTHER_ID_AND_ORDER_STATUS)) {
			statement.setInt(1, orderStatusId);
			statement.setInt(2, ProductType.FEEDS_AND_OTHER.getId());
			statement.setLong(3, productId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					listDetails.add(new OrderDetalizationByProduct(resultSet.getLong(ORDERS_ID),
							resultSet.getLong(IDENTIFIER_COUNT_ROWS_OF_ORDER_PRODUCTS_PRODUCT_FEEDS_AND_OTHER_ID),
							resultSet.getDouble(ORDERS_TOTAL_PAYMENT_WITH_DISCOUNT_AMOUNT),
							resultSet.getLong(ORDERS_USERS_ID), resultSet.getString(USERS_LOGIN)));
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return listDetails;
	}
}