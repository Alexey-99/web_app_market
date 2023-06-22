package by.koroza.zoo_market.dao.impl;

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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.dao.pool.ConnectionPool;
import by.koroza.zoo_market.dao.pool.ProxyConnection;
import by.koroza.zoo_market.model.calculation.Calculator;
import by.koroza.zoo_market.model.entity.filter.FilterPet;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.dao.ProductPetDao;
import by.koroza.zoo_market.dao.exception.DaoException;

public class ProductPetDaoImpl implements ProductPetDao {
	private static final ProductPetDao INSTANCE = new ProductPetDaoImpl();

	private ProductPetDaoImpl() {
	}

	public static ProductPetDao getInstance() {
		return INSTANCE;
	}

	private static final String QUERY_SELECT_ALL_HAVING_PRODUCTS_PETS = """
			SELECT pets.id, pets.image_path, pets.specie, pets.breed, pets.birth_date, pets.price, pets.discount, pets.date_update, pets.number_of_units_products
			FROM pets
			WHERE pets.number_of_units_products > 0;
			""";

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
					pet.setTotalPrice(pet.getPrice()
							- Calculator.getInstance().calcProcentFromSum(pet.getPrice(), pet.getDiscount()));
					listPets.add(pet);
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return listPets;
	}

	private static final char CODE_OF_TYPE_PRODUCT_PET = 'p';

	private static final String QUERY_SELECT_HAVING_PRODUCT_PET_BY_ID = """
			SELECT pets.id, pets.image_path, pets.specie, pets.breed, pets.birth_date, pets.price, pets.discount, pets.date_update, pets.number_of_units_products
			FROM pets
			WHERE pets.number_of_units_products > 0 AND pets.id = ?;
			""";

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
								pet.setTotalPrice(pet.getPrice() - Calculator.getInstance()
										.calcProcentFromSum(pet.getPrice(), pet.getDiscount()));
								listPets.add(pet);
							}
						}
					}
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return listPets;
	}

	@Override
	public List<Pet> getProductsPetsWithFilter(FilterPet filter) throws DaoException {
		List<Pet> listPets = new ArrayList<>();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(createQueryGetProductsPetsByFilter(filter));
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
				pet.setTotalPrice(pet.getPrice()
						- Calculator.getInstance().calcProcentFromSum(pet.getPrice(), pet.getDiscount()));
				listPets.add(pet);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return listPets;
	}

	private static final String QUERY_SELECT_ALL_PRODUCTS_PETS = """
			SELECT pets.id, pets.image_path, pets.specie, pets.breed, pets.birth_date, pets.price, pets.discount, pets.date_update, pets.number_of_units_products
			FROM pets
			""";

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
				pet.setTotalPrice(pet.getPrice()
						- Calculator.getInstance().calcProcentFromSum(pet.getPrice(), pet.getDiscount()));
				mapPetsAndNumber.put(pet, resultSet.getLong(PETS_NUMBER_OF_UNITS_PRODUCT));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return mapPetsAndNumber;
	}

	private static final String QUERY_SELECT_PRODUCT_PET_BY_ID = """
			SELECT pets.id, pets.image_path, pets.specie, pets.breed, pets.birth_date, pets.price, pets.discount, pets.date_update, pets.number_of_units_products
			FROM pets
			WHERE pets.id = ?;
			""";

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
					pet.setTotalPrice(pet.getPrice()
							- Calculator.getInstance().calcProcentFromSum(pet.getPrice(), pet.getDiscount()));
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return pet;
	}

	private static final String QUERY_SELECT_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID = """
			SELECT pets.number_of_units_products
			FROM pets
			WHERE pets.id = ?;
			""";

	private static final String QUERY_CHANGE_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID = """
			UPDATE pets
			SET pets.number_of_units_products = ?
			WHERE pets.id = ?;
			""";

	@Override
	public boolean changeNumberOfUnitsProducts(Order order) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			for (Pet productPet : order.getProductsPets()) {
				long numberOfUnitsProduct = 0;
				try (PreparedStatement statement = connection
						.prepareStatement(QUERY_SELECT_NUMBER_OF_UNITS_PRODUCTS_BY_PRODUCT_ID)) {
					statement.setLong(1, productPet.getId());
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
						statement.setLong(2, productPet.getId());
						result = statement.executeUpdate() > 0;
					}
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	private static final String QUERY_INSERT_PRODUCT_PET = """
			INSERT INTO pets(pets.image_path, pets.specie, pets.breed, pets.birth_date, pets.price, pets.discount, pets.number_of_units_products)
			VALUE(?, ?, ?, ?, ?, ?, ?);
			""";

	private static final String QUERY_SELECT_LAST_INSERT_ID = """
			SELECT LAST_INSERT_ID();
			""";

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
			throw new DaoException(e);
		}
		return result;
	}

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

	@Override
	public boolean upadateProductPet(Pet pet, long numberOfUnitsProduct) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(QUERY_UPDETE_PRODUCT_PET_BY_ID)) {
				statement.setString(1, pet.getImagePath());
				statement.setString(2, pet.getSpecie());
				statement.setString(3, pet.getBreed());
				statement.setString(4, pet.getBirthDate().toString());
				statement.setDouble(5, pet.getPrice());
				statement.setDouble(6, pet.getDiscount());
				statement.setLong(7, numberOfUnitsProduct);
				statement.setLong(8, pet.getId());
				result = statement.executeUpdate() > 0;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	private static final String QUERY_SELECT_ALL_HAVING_PRODUCTS_PETS_NOT_CLOSED = """
			SELECT pets.id, pets.image_path, pets.specie, pets.breed, pets.birth_date, pets.price, pets.discount, pets.date_update, pets.number_of_units_products
			FROM pets
			WHERE pets.number_of_units_products > 0
			""";

	private String createQueryGetProductsPetsByFilter(FilterPet filter) {
		StringBuilder query = new StringBuilder(QUERY_SELECT_ALL_HAVING_PRODUCTS_PETS_NOT_CLOSED);
		int countParameters = 0;
		if (filter.isOnlyProductsWithDiscount()) {
			countParameters++;
			query.append(" and (").append(PETS_DISCOUNT).append(" > 0");
		} else if (filter.getMaxDiscount() != 0 || filter.getMinDiscount() != 0) {
			countParameters++;
			insertFilterParametersInQueryMinMaxDouble(query, filter.getMinDiscount(), filter.getMaxDiscount(),
					PETS_DISCOUNT);
		}
		if (filter.getMaxPrice() != 0 || filter.getMinPrice() != 0) {
			countParameters++;
			insertFilterParametersInQueryMinMaxDouble(query, filter.getMinPrice(), filter.getMaxPrice(), PETS_PRICE);
		}
		if ((filter.getMinNumberMonth() != 0 || filter.getMinNumberYear() != 0)
				|| (filter.getMaxNumberMonth() != 0 || filter.getMaxNumberYear() != 0)) {
			countParameters++;
			insertFilterParametersInQueryMinMaxDate(query,
					LocalDate.now().minusYears(filter.getMaxNumberYear()).minusMonths(filter.getMaxNumberMonth()),
					LocalDate.now().minusYears(filter.getMinNumberYear()).minusMonths(filter.getMinNumberMonth()),
					PETS_BIRTH_DATE);
		}
		String[] typePets = filter.getChoosedTypesPets();
		if (typePets != null) {
			countParameters++;
			insertFilterParametersInQueryArrayString(query, typePets, PETS_SPECIE);
		}
		String[] breedPets = filter.getChoosedBreedPets();
		if (breedPets != null) {
			countParameters++;
			insertFilterParametersInQueryArrayString(query, breedPets, PETS_BREED);
		}
		for (int i = 0; i < countParameters; i++) {
			query.append(")");
		}
		return query.append(";").toString();
	}

	private void insertFilterParametersInQueryArrayString(StringBuilder query, String[] parameters,
			String nameParameter) {
		boolean haveFirstType = false;
		query.append(" and (");
		for (String type : parameters) {
			if (haveFirstType == false) {
				query.append("pet.").append(nameParameter).append(" = '").append(type).append("'");
				haveFirstType = true;
			} else {
				query.append(" or ").append("pet.").append(nameParameter).append(" = '").append(type).append("'");
			}
		}
	}

	private void insertFilterParametersInQueryMinMaxDouble(StringBuilder query, double min, double max,
			String nameParameter) {
		if (min > 0) {
			query.append(" and (").append("pet.").append(nameParameter).append(" > ").append(min);
			if (max != 0) {
				query.append(" and ").append("pet.").append(nameParameter).append(" < ").append(max);
			}
		} else {
			query.append(" and (").append("pet.").append(nameParameter).append(" < ").append(max);
		}
	}

	private void insertFilterParametersInQueryMinMaxDate(StringBuilder query, LocalDate min, LocalDate max,
			String nameParameter) {
		if (min != null) {
			query.append(" and (").append("pet.").append(nameParameter).append(" > '").append(min.toString())
					.append("'");
			query.append(" and ").append("pet.").append(nameParameter).append(" < '").append(max.toString())
					.append("'");
		} else {
			query.append(" and (").append("pet.").append(nameParameter).append(" < '").append(max.toString())
					.append("'");
		}
	}
}