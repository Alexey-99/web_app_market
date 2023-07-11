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
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_PETS_IMAGE_PATH;

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
import by.koroza.zoo_market.model.entity.market.product.Pet;
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

	/** The Constant QUERY_SELECT_ALL_HAVING_PRODUCTS_PETS. */
	private static final String QUERY_SELECT_ALL_HAVING_PRODUCTS_PETS = """
			SELECT pets.id, pets.image_path, pets.specie, pets.breed, pets.birth_date, pets.price, pets.discount, pets.date_update, pets.number_of_units_products
			FROM pets
			WHERE pets.number_of_units_products > 0;
			""";

	/**
	 * Get the all having products pets.
	 *
	 * @return the all having products pets
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<Pet> getAllHavingProductsPets() throws DaoException {
		List<Pet> listPets = new ArrayList<>();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_ALL_HAVING_PRODUCTS_PETS);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				for (int i = 0; i < resultSet.getLong(PETS_NUMBER_OF_UNITS_PRODUCT); i++) {
					Pet pet = new Pet.PetBuilder().setId(resultSet.getLong(PETS_ID))
							.setImagePath(resultSet.getString(PETS_IMAGE_PATH))
							.setSpecie(resultSet.getString(PETS_SPECIE)).setBreed(resultSet.getString(PETS_BREED))
							.setBirthDate(resultSet.getDate(PETS_BIRTH_DATE).toString())
							.setPrice(resultSet.getDouble(PETS_PRICE)).setDiscount(resultSet.getDouble(PETS_DISCOUNT))
							.setUpdateDateTime(resultSet.getDate(PETS_DATE_UPDATE).toLocalDate(),
									resultSet.getTime(PETS_DATE_UPDATE).toLocalTime())
							.build();
					pet.setTotalPrice(pet.getPrice() - (pet.getPrice() * pet.getDiscount() / 100));
					listPets.add(pet);
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return listPets;
	}

	/** The Constant CODE_OF_TYPE_PRODUCT_PET. */
	private static final char CODE_OF_TYPE_PRODUCT_PET = 'p';

	/** The Constant QUERY_SELECT_HAVING_PRODUCT_PET_BY_ID. */
	private static final String QUERY_SELECT_HAVING_PRODUCT_PET_BY_ID = """
			SELECT pets.id, pets.image_path, pets.specie, pets.breed, pets.birth_date, pets.price, pets.discount, pets.date_update, pets.number_of_units_products
			FROM pets
			WHERE pets.number_of_units_products > 0 AND pets.id = ?;
			""";

	/**
	 * Get the products pets by id.
	 *
	 * @param productsIdMap the products id map
	 * @return the products pets by id
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<Pet> getProductsPetsById(Map<String, String> productsIdMap) throws DaoException {
		List<Pet> listPets = new ArrayList<>();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			for (Map.Entry<String, String> entry : productsIdMap.entrySet()) {
				char productType = entry.getKey().charAt(0);
				String id = entry.getValue();
				if (productType == CODE_OF_TYPE_PRODUCT_PET) {
					try (PreparedStatement statement = connection
							.prepareStatement(QUERY_SELECT_HAVING_PRODUCT_PET_BY_ID)) {
						statement.setLong(1, Long.parseLong(id));
						try (ResultSet resultSet = statement.executeQuery()) {
							while (resultSet.next()) {
								Pet pet = new Pet.PetBuilder().setId(resultSet.getLong(PETS_ID))
										.setImagePath(resultSet.getString(PETS_IMAGE_PATH))
										.setSpecie(resultSet.getString(PETS_SPECIE))
										.setBreed(resultSet.getString(PETS_BREED))
										.setBirthDate(resultSet.getDate(PETS_BIRTH_DATE).toString())
										.setPrice(resultSet.getDouble(PETS_PRICE))
										.setDiscount(resultSet.getDouble(PETS_DISCOUNT))
										.setUpdateDateTime(resultSet.getDate(PETS_DATE_UPDATE).toLocalDate(),
												resultSet.getTime(PETS_DATE_UPDATE).toLocalTime())
										.build();
								pet.setTotalPrice(pet.getPrice() - (pet.getPrice() * pet.getDiscount() / 100));
								listPets.add(pet);
							}
						}
					}
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return listPets;
	}

	/** The Constant QUERY_SELECT_ALL_PRODUCTS_PETS. */
	private static final String QUERY_SELECT_ALL_PRODUCTS_PETS = """
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
	public Map<Pet, Long> getAllProductsPetsAndNumberOfUnits() throws DaoException {
		Map<Pet, Long> mapPetsAndNumber = new HashMap<>();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_ALL_PRODUCTS_PETS);
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
	private static final String QUERY_SELECT_PRODUCT_PET_BY_ID = """
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
	public Pet getProductPetById(long id) throws DaoException {
		Pet pet = null;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_PRODUCT_PET_BY_ID)) {
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

	/**
	 * Change number of units products.
	 *
	 * @param productsPets the products pets
	 * @return the map
	 * @throws DaoException the dao exception
	 */
	@Override
	public Map<Integer, Boolean> changeNumberOfUnitsProductsMinus(List<Pet> productsPets) throws DaoException {
		Map<Integer, Boolean> haveProductByIndex = new LinkedHashMap<>();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			for (int i = 0; i < productsPets.size(); i++) {
				long numberOfUnitsProduct = 0;
				try (PreparedStatement statement = connection
						.prepareStatement(QUERY_SELECT_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID)) {
					statement.setLong(1, productsPets.get(i).getId());
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
						statement.setLong(2, productsPets.get(i).getId());
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

	/**
	 * Change number of units products plus.
	 *
	 * @param productsPets       the products pets
	 * @param haveProductByIndex the have product by index
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean changeNumberOfUnitsProductsPlus(List<Pet> productsPets, Map<Integer, Boolean> haveProductByIndex)
			throws DaoException {
		int countChanging = 0;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			for (int i = 0; i < productsPets.size(); i++) {
				if (haveProductByIndex.get(i)) {
					long numberOfUnitsProduct = 0;
					try (PreparedStatement statement = connection
							.prepareStatement(QUERY_SELECT_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID)) {
						statement.setLong(1, productsPets.get(i).getId());
						try (ResultSet resultSet = statement.executeQuery()) {
							while (resultSet.next()) {
								numberOfUnitsProduct = resultSet.getLong(PETS_NUMBER_OF_UNITS_PRODUCT);
							}
						}
					}
					try (PreparedStatement statement = connection
							.prepareStatement(QUERY_CHANGE_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID)) {
						statement.setLong(1, numberOfUnitsProduct + 1);
						statement.setLong(2, productsPets.get(i).getId());
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

	/**
	 * Number need change products.
	 *
	 * @param haveProductByIndex the have product by index
	 * @return the int
	 */
	private int numberNeedChangeProducts(Map<Integer, Boolean> haveProductByIndex) {
		int countChanging = 0;
		for (Map.Entry<Integer, Boolean> entry : haveProductByIndex.entrySet()) {
			if (entry.getValue()) {
				countChanging++;
			}
		}
		return countChanging;
	}

	/** The Constant QUERY_INSERT_PRODUCT_PET. */
	private static final String QUERY_INSERT_PRODUCT_PET = """
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
	public boolean addProductPet(Pet pet, long numberOfUnitsProduct) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_PRODUCT_PET)) {
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

	/** The Constant QUERY_UPDETE_PRODUCT_PET_BY_ID. */
	private static final String QUERY_UPDETE_PRODUCT_PET_BY_ID = """
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
	public boolean updateProductPet(Pet pet, long numberOfUnitsProduct) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_UPDETE_PRODUCT_PET_BY_ID)) {
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
}