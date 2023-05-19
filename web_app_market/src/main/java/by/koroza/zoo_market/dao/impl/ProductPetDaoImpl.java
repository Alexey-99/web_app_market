package by.koroza.zoo_market.dao.impl;

import static by.koroza.zoo_market.dao.name.ColumnName.PETS_BREED;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_BIRTH_DATE;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_DATE_UPDATE;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_DISCOUNT;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_PRICE;
import static by.koroza.zoo_market.dao.name.ColumnName.PETS_SPECIE;
import static by.koroza.zoo_market.dao.name.ColumnName.PRODUCT_STATUSES_NAME;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.dao.pool.ConnectionPool;
import by.koroza.zoo_market.dao.pool.ProxyConnection;
import by.koroza.zoo_market.model.entity.filter.FilterPet;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.status.ProductStatus;
import by.koroza.zoo_market.dao.ProductPetDao;
import by.koroza.zoo_market.dao.exception.DaoException;

public class ProductPetDaoImpl implements ProductPetDao {
	private static final ProductPetDao INSTANCE = new ProductPetDaoImpl();

	private ProductPetDaoImpl() {
	}

	public static ProductPetDao getInstance() {
		return INSTANCE;
	}

	private static final String QUERY_SELECT_ALL_PRODUCTS_PETS = """
			SELECT pets.id, pets.specie, pets.breed, pets.birth_date, pets.price, product_statuses.name, pets.discount, pets.date_update
			FROM pets
			JOIN product_statuses
			ON pets.status_id = product_statuses.id
			WHERE pets.status_id = 1;
			""";

	@Override
	public List<Pet> getAllProductsPets() throws DaoException {
		List<Pet> listPets = new ArrayList<>();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_ALL_PRODUCTS_PETS);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				Pet pet = new Pet.PetBuilder().setId(resultSet.getLong(PETS_ID))
						.setSpecie(resultSet.getString(PETS_SPECIE)).setBreed(resultSet.getString(PETS_BREED))
						.setBirthDate(resultSet.getDate(PETS_BIRTH_DATE).toString())
						.setPrice(resultSet.getDouble(PETS_PRICE))
						.setStutus(ProductStatus.findEqualProductStatus(resultSet.getString(PRODUCT_STATUSES_NAME)))
						.setDiscount(resultSet.getDouble(PETS_DISCOUNT))
						.setUpdateDateTime(resultSet.getDate(PETS_DATE_UPDATE).toLocalDate(),
								resultSet.getTime(PETS_DATE_UPDATE).toLocalTime())
						.build();
				listPets.add(pet);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return listPets;
	}

	@Override
	public List<Pet> getProductsPetsById(Map<String, String> productsIdMap) throws DaoException {
		List<Pet> listPets = new ArrayList<>();
		String query = createQueryGetProductsPetsByIdEnd(productsIdMap);
		if (query != null) {
			try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
					PreparedStatement statement = connection.prepareStatement(query);
					ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Pet pet = new Pet.PetBuilder().setId(resultSet.getLong(PETS_ID))
							.setSpecie(resultSet.getString(PETS_SPECIE)).setBreed(resultSet.getString(PETS_BREED))
							.setBirthDate(resultSet.getDate(PETS_BIRTH_DATE).toString())
							.setPrice(resultSet.getDouble(PETS_PRICE))
							.setStutus(ProductStatus.findEqualProductStatus(resultSet.getString(PRODUCT_STATUSES_NAME)))
							.setDiscount(resultSet.getDouble(PETS_DISCOUNT))
							.setUpdateDateTime(resultSet.getDate(PETS_DATE_UPDATE).toLocalDate(),
									resultSet.getTime(PETS_DATE_UPDATE).toLocalTime())
							.build();
					listPets.add(pet);
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return listPets;
	}

	@Override
	public List<Pet> getProductsPetsById(List<Integer> productsIdList) throws DaoException {
		List<Pet> listPets = new ArrayList<>();
		String query = createQueryGetProductsPetsByIdStart(productsIdList);
		if (query != null) {
			try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
					PreparedStatement statement = connection.prepareStatement(query);
					ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Pet pet = new Pet.PetBuilder().setId(resultSet.getLong(PETS_ID))
							.setSpecie(resultSet.getString(PETS_SPECIE)).setBreed(resultSet.getString(PETS_BREED))
							.setBirthDate(resultSet.getDate(PETS_BIRTH_DATE).toString())
							.setPrice(resultSet.getDouble(PETS_PRICE))
							.setStutus(ProductStatus.findEqualProductStatus(resultSet.getString(PRODUCT_STATUSES_NAME)))
							.setDiscount(resultSet.getDouble(PETS_DISCOUNT))
							.setUpdateDateTime(resultSet.getDate(PETS_DATE_UPDATE).toLocalDate().toString(),
									resultSet.getTime(PETS_DATE_UPDATE).toLocalTime().toString())
							.build();
					listPets.add(pet);
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
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
						.setSpecie(resultSet.getString(PETS_SPECIE)).setBreed(resultSet.getString(PETS_BREED))
						.setBirthDate(resultSet.getDate(PETS_BIRTH_DATE).toString())
						.setPrice(resultSet.getDouble(PETS_PRICE))
						.setStutus(ProductStatus.findEqualProductStatus(resultSet.getString(PRODUCT_STATUSES_NAME)))
						.setDiscount(resultSet.getDouble(PETS_DISCOUNT))
						.setUpdateDateTime(resultSet.getDate(PETS_DATE_UPDATE).toLocalDate().toString(),
								resultSet.getTime(PETS_DATE_UPDATE).toLocalTime().toString())
						.build();
				listPets.add(pet);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return listPets;
	}

	private static final char CODE_OF_TYPE_PRODUCT_PET = 'p';

	private static final String QUERY_SELECT_ALL_PRODUCTS_PETS_NOT_CLOSED = """
			SELECT pets.id, pets.specie, pets.breed, pets.birth_date, pets.price, product_statuses.name, pets.discount, pets.date_update
			FROM pets
			JOIN product_statuses
			ON pets.status_id = product_statuses.id
			WHERE pets.status_id = 1
			""";

	private String createQueryGetProductsPetsByIdEnd(Map<String, String> productsIdMap) {
		boolean haveFirstElement = false;
		StringBuilder query = new StringBuilder(QUERY_SELECT_ALL_PRODUCTS_PETS_NOT_CLOSED);
		for (Map.Entry<String, String> entry : productsIdMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (key.charAt(0) == CODE_OF_TYPE_PRODUCT_PET) {
				if (haveFirstElement == false) {
					query.append(" and (").append(PETS_ID).append(" IN (").append(value);
					haveFirstElement = true;
				} else {
					query.append(", ").append(value);
				}
			}
		}
		return (haveFirstElement ? query.append("))").append(";").toString() : null);
	}

	private static final String QUERY_SELECT_PRODUCTS_PETS_BY_ID = """
			SELECT pets.id, pets.specie, pets.breed, pets.birth_date
			FROM pets
			WHERE
			""";

	private String createQueryGetProductsPetsByIdStart(List<Integer> productsId) {
		boolean haveFirstElement = false;
		StringBuilder query = new StringBuilder(QUERY_SELECT_PRODUCTS_PETS_BY_ID);
		for (Integer id : productsId) {
			if (haveFirstElement == false) {
				query.append(" ").append(PETS_ID).append(" IN (").append(id);
				haveFirstElement = true;
			} else {
				query.append(", ").append(id);
			}
		}
		return (haveFirstElement ? query.append(")").append(";").toString() : null);
	}

	private String createQueryGetProductsPetsByFilter(FilterPet filter) {
		StringBuilder query = new StringBuilder(QUERY_SELECT_ALL_PRODUCTS_PETS_NOT_CLOSED);
		int countParameters = 0;
		if (filter.isOnlyProductsWithDiscont()) {
			countParameters++;
			query.append(" and (").append(PETS_DISCOUNT).append(" > 0");
		} else if (filter.getMaxDiscont() != 0 || filter.getMinDiscont() != 0) {
			countParameters++;
			insertFilterParametersInQueryMinMaxDouble(query, filter.getMinDiscont(), filter.getMaxDiscont(),
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