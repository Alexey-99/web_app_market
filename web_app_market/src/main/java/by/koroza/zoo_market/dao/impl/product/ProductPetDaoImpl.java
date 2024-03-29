package by.koroza.zoo_market.dao.impl.product;

import static by.koroza.zoo_market.dao.name.ColumnName.PETS_BREED;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_LAST_INSERT_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_BIRTH_DATE;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_DATE_UPDATE;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_DISCOUNT;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_PRICE;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_SPECIE;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_IMAGE_PATH;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_PETS_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_PETS_IMAGE_PATH;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_ORDER_PRODUCTS_ORDER_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_ORDER_PRODUCTS_PRODUCT_PETS_ID;

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
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.status.ProductType;
import by.koroza.zoo_market.dao.ProductPetDao;
import by.koroza.zoo_market.dao.exception.checkable.DaoException;

/**
 * The Class ProductPetDaoImpl.
 */
public class ProductPetDaoImpl implements ProductPetDao {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final ProductPetDao INSTANCE = new ProductPetDaoImpl();

	/**
	 * Instantiates a new product pet dao impl.
	 */
	private ProductPetDaoImpl() {
	}

	/**
	 * Get the single instance of ProductPetDaoImpl.
	 *
	 * @return single instance of ProductPetDaoImpl
	 */
	public static ProductPetDao getInstance() {
		return INSTANCE;
	}

	/** The Constant QUERY_SELECT_ALL_PRODUCTS_PETS. */
	private static final String QUERY_SELECT_ALL_PRODUCTS = """
			SELECT pets.id, pets.image_path, pets.specie, pets.breed, pets.birth_date, pets.price, pets.discount, pets.date_update, pets.number_of_units_products
			FROM pets
			""";

	/**
	 * Get the all products pets and number of units.
	 *
	 * @return the all products pets and number of units
	 * @throws DaoException the dao exception
	 */
	@Override
	public Map<Pet, Long> getAllProductsAndNumberOfUnits() throws DaoException {
		Map<Pet, Long> mapPetsAndNumber = new HashMap<>();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_ALL_PRODUCTS);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				Pet pet = new Pet.PetBuilder().setId(resultSet.getLong(PETS_ID))
						.setImagePath(resultSet.getString(PETS_IMAGE_PATH)).setSpecie(resultSet.getString(PETS_SPECIE))
						.setBreed(resultSet.getString(PETS_BREED))
						.setBirthDate(resultSet.getDate(PETS_BIRTH_DATE).toString())
						.setPrice(resultSet.getDouble(PETS_PRICE)).setDiscount(resultSet.getDouble(PETS_DISCOUNT))
						.setUpdateDateTime(resultSet.getDate(PETS_DATE_UPDATE).toLocalDate(),
								resultSet.getTime(PETS_DATE_UPDATE).toLocalTime())
						.build();
				pet.setTotalPrice(pet.getPrice() - (pet.getPrice() * pet.getDiscount() / 100));
				mapPetsAndNumber.put(pet, resultSet.getLong(PETS_NUMBER_OF_UNITS_PRODUCT));
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return mapPetsAndNumber;
	}

	/** The Constant QUERY_SELECT_PRODUCT_PET_BY_ID. */
	private static final String QUERY_SELECT_PRODUCT_BY_ID = """
			SELECT pets.id, pets.image_path, pets.specie, pets.breed, pets.birth_date, pets.price, pets.discount, pets.date_update, pets.number_of_units_products
			FROM pets
			WHERE pets.id = ?;
			""";

	/**
	 * Get the product pet by id.
	 *
	 * @param id the id
	 * @return the product pet by id
	 * @throws DaoException the dao exception
	 */
	@Override
	public Pet getProductById(long id) throws DaoException {
		Pet pet = null;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_PRODUCT_BY_ID)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					pet = new Pet.PetBuilder().setId(resultSet.getLong(PETS_ID))
							.setImagePath(resultSet.getString(PETS_IMAGE_PATH))
							.setSpecie(resultSet.getString(PETS_SPECIE)).setBreed(resultSet.getString(PETS_BREED))
							.setBirthDate(resultSet.getDate(PETS_BIRTH_DATE).toString())
							.setPrice(resultSet.getDouble(PETS_PRICE)).setDiscount(resultSet.getDouble(PETS_DISCOUNT))
							.setUpdateDateTime(resultSet.getDate(PETS_DATE_UPDATE).toLocalDate(),
									resultSet.getTime(PETS_DATE_UPDATE).toLocalTime())
							.build();
					pet.setTotalPrice(pet.getPrice() - (pet.getPrice() * pet.getDiscount() / 100));
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return pet;
	}

	/** The Constant QUERY_SELECT_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID. */
	private static final String QUERY_SELECT_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID = """
			SELECT pets.number_of_units_products
			FROM pets
			WHERE pets.id = ?;
			""";

	/** The Constant QUERY_CHANGE_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID. */
	private static final String QUERY_CHANGE_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID = """
			UPDATE pets
			SET pets.number_of_units_products = ?
			WHERE pets.id = ?;
			""";

	/** The Constant QUERY_INSERT_PRODUCT_PET. */
	private static final String QUERY_INSERT_PRODUCT = """
			INSERT INTO pets(pets.image_path, pets.specie, pets.breed, pets.birth_date, pets.price, pets.discount, pets.number_of_units_products)
			VALUE(?, ?, ?, ?, ?, ?, ?);
			""";

	/** The Constant QUERY_SELECT_LAST_INSERT_ID. */
	private static final String QUERY_SELECT_LAST_INSERT_ID = """
			SELECT LAST_INSERT_ID();
			""";

	/**
	 * Add the product pet.
	 *
	 * @param pet                  the pet
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean addProduct(Pet pet, long numberOfUnitsProduct) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_PRODUCT)) {
				statement.setString(1, pet.getImagePath());
				statement.setString(2, pet.getSpecie());
				statement.setString(3, pet.getBreed());
				statement.setString(4, pet.getBirthDate().toString());
				statement.setDouble(5, pet.getPrice());
				statement.setDouble(6, pet.getDiscount());
				statement.setLong(7, numberOfUnitsProduct);
				result = statement.executeUpdate() > 0;
			}
			try (PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_LAST_INSERT_ID);
					ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					pet.setId(resultSet.getLong(IDENTIFIER_LAST_INSERT_ID));
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_INSERT_PRODUCT_PET_TO_ORDER_PRODUCTS. */
	private static final String QUERY_INSERT_PRODUCT_TO_ORDER_PRODUCTS = """
			INSERT INTO order_products(order_products.orders_id, order_products.product_types_id, order_products.pets_id)
			VALUE(?, ?, ?);
			""";

	/**
	 * Transfer pet product from market to order.
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
						numberOfUnitsProduct = resultSet.getLong(PETS_NUMBER_OF_UNITS_PRODUCT);
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
						.prepareStatement(QUERY_INSERT_PRODUCT_TO_ORDER_PRODUCTS)) {
					statement.setLong(1, orderId);
					statement.setInt(2, ProductType.PETS.getId());
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

	private static final String QUERY_SELECT_IS_HAVE_PRODUCT_IN_ORDER_BY_ID = """
			SELECT COUNT(order_products.orders_id)
			FROM order_products
			WHERE order_products.orders_id = ?
			AND order_products.product_types_id = ?
			AND order_products.pets_id = ?;
			""";

	public static final String QUERY_DELETE_PRODUCT_BY_ID_FROM_ORDER_PRODUCTS = """
			DELETE
			FROM order_products
			WHERE order_products.orders_id = ?
			AND order_products.product_types_id = ?
			AND order_products.pets_id = ?
			LIMIT 1;
			""";

	private static final String QUERY_SELECT_IS_HAVE_PRODUCT_IN_MARKET_BY_ID = """
			SELECT COUNT(pets.id)
			FROM pets
			WHERE pets.id = ?;
			""";

	/**
	 * Transfer pet product from order to market.
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
					.prepareStatement(QUERY_SELECT_IS_HAVE_PRODUCT_IN_ORDER_BY_ID)) {
				statement.setLong(1, orderId);
				statement.setInt(2, ProductType.PETS.getId());
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
						.prepareStatement(QUERY_DELETE_PRODUCT_BY_ID_FROM_ORDER_PRODUCTS)) {
					statement.setLong(1, orderId);
					statement.setInt(2, ProductType.PETS.getId());
					statement.setLong(3, productId);
					isDeleteProiductInOrder = statement.executeUpdate() > 0;
				}
			}
			boolean isChangedNumberOfUnitsProductInMarket = false;
			boolean isHaveProductInMarketByID = false;
			try (PreparedStatement statement = connection
					.prepareStatement(QUERY_SELECT_IS_HAVE_PRODUCT_IN_MARKET_BY_ID)) {
				statement.setLong(1, productId);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						isHaveProductInMarketByID = resultSet.getLong(IDENTIFIER_COUNT_ROWS_OF_PETS_ID) > 0;
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

	/** The Constant QUERY_UPDETE_PRODUCT_PET_BY_ID. */
	private static final String QUERY_UPDATE_PRODUCT_BY_ID = """
			UPDATE pets
			SET pets.image_path = ?,
			pets.specie = ?,
			pets.breed = ?,
			pets.birth_date = ?,
			pets.price = ?,
			pets.discount = ?,
			pets.date_update = NOW(),
			pets.number_of_units_products = ?
			WHERE pets.id = ?;
			""";

	/**
	 * Update product pet.
	 *
	 * @param pet                  the pet
	 * @param numberOfUnitsProduct the number of units product
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean updateProductById(Pet pet, long numberOfUnitsProduct) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_PRODUCT_BY_ID)) {
			statement.setString(1, pet.getImagePath());
			statement.setString(2, pet.getSpecie());
			statement.setString(3, pet.getBreed());
			statement.setString(4, pet.getBirthDate().toString());
			statement.setDouble(5, pet.getPrice());
			statement.setDouble(6, pet.getDiscount());
			statement.setLong(7, numberOfUnitsProduct);
			statement.setLong(8, pet.getId());
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_SELECT_COUNT_PRODUCTS_WITH_IMAGE_PATH. */
	private static final String QUERY_SELECT_COUNT_PRODUCTS_WITH_IMAGE_PATH = """
			SELECT COUNT(pets.image_path)
			FROM pets
			WHERE pets.image_path = ?;
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
					result = resultSet.getInt(IDENTIFIER_COUNT_ROWS_OF_PETS_IMAGE_PATH) > 0;
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
			SELECT pets.image_path
			FROM pets
			WHERE pets.id = ?;
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
					imagePath = resultSet.getString(PETS_IMAGE_PATH);
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return imagePath;
	}

	/** The Constant QUERY_SELECT_FREE_NUMBER_OF_UNITS_BY_PRODUCT_ID. */
	private static final String QUERY_SELECT_FREE_NUMBER_OF_UNITS_BY_PRODUCT_ID = """
			SELECT pets.number_of_units_products
			FROM pets
			WHERE pets.id = ?;
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
					numberOfUnits = resultSet.getLong(PETS_NUMBER_OF_UNITS_PRODUCT);
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
			SELECT COUNT(order_products.pets_id)
			FROM orders INNER JOIN order_products
				ON orders.id = order_products.orders_id
			WHERE orders.order_statuses_id = ?
				AND order_products.product_types_id = ?
				AND order_products.pets_id = ?;
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
			statement.setInt(2, ProductType.PETS.getId());
			statement.setLong(3, productId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					quantity = resultSet.getLong(IDENTIFIER_COUNT_ROWS_OF_ORDER_PRODUCTS_PRODUCT_PETS_ID);
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return quantity;
	}

	/**
	 * Add the product to order.
	 *
	 * @param orderId   the order id
	 * @param productId the product id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean addProductToOrder(long orderId, long productId) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_PRODUCT_TO_ORDER_PRODUCTS)) {
			statement.setLong(1, orderId);
			statement.setInt(2, ProductType.PETS.getId());
			statement.setLong(3, productId);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/**
	 * Delete product from order.
	 *
	 * @param orderId   the order id
	 * @param productId the product id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean deleteProductFromOrder(long orderId, long productId) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(QUERY_DELETE_PRODUCT_BY_ID_FROM_ORDER_PRODUCTS)) {
			statement.setLong(1, orderId);
			statement.setInt(2, ProductType.PETS.getId());
			statement.setLong(3, productId);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}
}