package by.koroza.zoo_market.dao.impl.order;

import static by.koroza.zoo_market.dao.name.ColumnName.ORDERS_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.ORDERS_USERS_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.ORDERS_TOTAL_PAYMENT_AMOUNT;
import static by.koroza.zoo_market.dao.name.ColumnName.ORDERS_TOTAL_DISCOUNT_AMOUNT;
import static by.koroza.zoo_market.dao.name.ColumnName.ORDERS_TOTAL_PAYMENT_WITH_DISCOUNT_AMOUNT;
import static by.koroza.zoo_market.dao.name.ColumnName.ORDERS_DATE;
import static by.koroza.zoo_market.dao.name.ColumnName.ORDERS_STATUS_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_SPECIE;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_BREED;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_BIRTH_DATE;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_TYPE;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_BRAND;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_DESCRIPTION;
import static by.koroza.zoo_market.dao.name.ColumnName.FEEDS_AND_OTHER_PET_TYPE;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_LAST_INSERT_ID;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.pool.ConnectionPool;
import by.koroza.zoo_market.dao.pool.ProxyConnection;
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
			SELECT orders.id, orders.users_id, orders.total_payment_amount, orders.total_products_discount_amount,
			orders.total_person_discount_amount, orders.total_discount_amount, orders.total_payment_with_discount_amount, orders.date, orders.order_statuses_id
			FROM orders INNER JOIN order_statuses
			ON orders.order_statuses_id = order_statuses.id
			WHERE orders.users_id = ?;
			""";

	/** The Constant QUERY_SELECT_ORDER_PRODUCTS_PETS_BY_ORDERS_ID. */
	private static final String QUERY_SELECT_ORDER_PRODUCTS_PETS_BY_ORDERS_ID = """
			SELECT order_products.orders_id, pets.id, pets.specie, pets.breed, pets.birth_date
			FROM order_products INNER JOIN pets
			ON order_products.pets_id = pets.id
			WHERE order_products.orders_id = ? AND order_products.product_types_id = ?;
			""";

	/** The Constant QUERY_SELECT_ORDER_PRODUCTS_OTHER_PRODUCTS_BY_ORDERS_ID. */
	private static final String QUERY_SELECT_ORDER_PRODUCTS_OTHER_PRODUCTS_BY_ORDERS_ID = """
			SELECT order_products.orders_id, feeds_and_other.id, feeds_and_other.type, feeds_and_other.brand, feeds_and_other.description, feeds_and_other.pet_type
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
					try (ResultSet resultSet = statement.executeQuery()) {
						while (resultSet.next()) {
							order.getProductsPets()
									.add(new Pet.PetBuilder().setId(resultSet.getLong(PETS_ID))
											.setSpecie(resultSet.getString(PETS_SPECIE))
											.setBreed(resultSet.getString(PETS_BREED))
											.setBirthDate(resultSet.getDate(PETS_BIRTH_DATE).toLocalDate()).build());
						}
					}
				}
			}
			for (Order order : orders) {
				try (PreparedStatement statement = connection
						.prepareStatement(QUERY_SELECT_ORDER_PRODUCTS_OTHER_PRODUCTS_BY_ORDERS_ID)) {
					statement.setLong(1, order.getId());
					statement.setLong(2, ProductType.FEEDS_AND_OTHER.getId());
					try (ResultSet resultSet = statement.executeQuery()) {
						while (resultSet.next()) {
							order.getOtherProducts()
									.add(new FeedAndOther.FeedAndOtherBuilder()
											.setId(resultSet.getLong(FEEDS_AND_OTHER_ID))
											.setProductType(resultSet.getString(FEEDS_AND_OTHER_TYPE))
											.setBrand(resultSet.getString(FEEDS_AND_OTHER_BRAND))
											.setDescriptions(FEEDS_AND_OTHER_DESCRIPTION)
											.setPetTypes(resultSet.getString(FEEDS_AND_OTHER_PET_TYPE)).build());
						}
					}
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
				statement.setInt(7, OrderStatus.WAITING_PAY.getId());
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
				for (Pet productPet : order.getProductsPets()) {
					statement.setLong(1, order.getId());
					statement.setLong(2, ProductType.PETS.getId());
					statement.setLong(3, productPet.getId());
					statement.setNull(4, Types.BIGINT);
					if (statement.executeUpdate() > 0) {
						countInserts++;
					}
				}
				for (FeedAndOther productFeedAndOther : order.getOtherProducts()) {
					statement.setLong(1, order.getId());
					statement.setLong(2, ProductType.FEEDS_AND_OTHER.getId());
					statement.setNull(3, Types.BIGINT);
					statement.setLong(4, productFeedAndOther.getId());
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
}