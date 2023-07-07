package by.koroza.zoo_market.dao.impl.confirmation;

import static by.koroza.zoo_market.dao.name.ColumnName.VERIFICATE_CODES_CODE;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_VERIFICATE_CODES_CODE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.koroza.zoo_market.dao.pool.ConnectionPool;
import by.koroza.zoo_market.dao.pool.ProxyConnection;
import by.koroza.zoo_market.dao.ConfirmationDao;
import by.koroza.zoo_market.dao.exception.DaoException;

public class ConfirmationDaoImpl implements ConfirmationDao {
	private static final ConfirmationDao INSTANCE = new ConfirmationDaoImpl();

	private static final String QUERY_SELECT_COUNT_VERIFICATE_CODES_WITH_USER_ID_AND_STATUS_OPEN = """
			SELECT COUNT(verificate_сodes.code)
			FROM verificate_сodes
			WHERE verificate_сodes.users_id = ? AND verificate_сodes.is_open = ?
			""";

	private ConfirmationDaoImpl() {

	}

	public static ConfirmationDao getInstance() {
		return INSTANCE;
	}

	private static final String QUERY_INSERT_VERIFICATE_CODE_WITH_USER_ID = """
			INSERT INTO verificate_сodes(users_id, code, is_open)
			VALUE (?, ?, ?)
			""";

	private static final String QUERY_CHANGE_STATUS_ALL_OLD_VERIFICATE_CODE_WITH_USER_ID = """
			UPDATE verificate_сodes
			SET is_open = ?
			WHERE users_id = ? AND id != LAST_INSERT_ID()
			""";

	@Override
	public boolean addConfirmationEmailCodeWithUserId(long userId, String code) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			boolean resultInsertVerificateCode = false;
			try (PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_VERIFICATE_CODE_WITH_USER_ID)) {
				statement.setLong(1, userId);
				statement.setString(2, code);
				statement.setBoolean(3, true);
				resultInsertVerificateCode = statement.executeUpdate() > 0;
			}
			try (PreparedStatement statement = connection
					.prepareStatement(QUERY_CHANGE_STATUS_ALL_OLD_VERIFICATE_CODE_WITH_USER_ID)) {
				statement.setBoolean(1, false);
				statement.setLong(2, userId);
				statement.execute();
			}
			boolean resultCountVerificateCode = false;
			try (PreparedStatement statement = connection
					.prepareStatement(QUERY_SELECT_COUNT_VERIFICATE_CODES_WITH_USER_ID_AND_STATUS_OPEN)) {
				statement.setLong(1, userId);
				statement.setBoolean(2, VERIFICATE_CODE_STATUS_OPEN);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						if (resultSet.getLong(IDENTIFIER_COUNT_ROWS_OF_VERIFICATE_CODES_CODE) == 1) {
							resultCountVerificateCode = true;
						}
					}
				}
			}
			result = resultInsertVerificateCode == true && resultCountVerificateCode == true;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	private static final String QUERY_GET_VERIFICATE_CODE_BY_USER_ID = """
			SELECT verificate_сodes.code
			FROM verificate_сodes
			WHERE verificate_сodes.users_id = ?;
			""";

	@Override
	public String getConfirmationCodeByUserId(long userId) throws DaoException {
		String code = null;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_GET_VERIFICATE_CODE_BY_USER_ID)) {
			statement.setLong(1, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					code = resultSet.getString(VERIFICATE_CODES_CODE);
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return code;
	}

	private static final boolean VERIFICATE_CODE_STATUS_OPEN = true;
	@SuppressWarnings("unused")
	private static final boolean VERIFICATE_CODE_STATUS_CLOSED = false;

	private static final String QUERY_CHANGE_STATUS_VERIFICATE_CODE_WITH_USER_ID = """
			UPDATE verificate_сodes
			SET verificate_сodes.is_open = ?
			WHERE verificate_сodes.users_id = ? AND verificate_сodes.code != ?;
			""";

	@Override
	public boolean changeConfirmationCodeStatusByUserId(long userId, String code, boolean status) throws DaoException {
		boolean result = true;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			boolean resultChangeStatus = false;
			try (PreparedStatement statement = connection
					.prepareStatement(QUERY_CHANGE_STATUS_VERIFICATE_CODE_WITH_USER_ID)) {
				statement.setBoolean(1, status);
				statement.setLong(2, userId);
				statement.setString(3, code);
				resultChangeStatus = statement.executeUpdate() > 0;
			}
			boolean resultCountVerificateCode = false;
			try (PreparedStatement statement = connection
					.prepareStatement(QUERY_SELECT_COUNT_VERIFICATE_CODES_WITH_USER_ID_AND_STATUS_OPEN)) {
				statement.setLong(1, userId);
				statement.setBoolean(2, VERIFICATE_CODE_STATUS_OPEN);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						if (resultSet.getLong(IDENTIFIER_COUNT_ROWS_OF_VERIFICATE_CODES_CODE) == 0) {
							resultCountVerificateCode = true;
						}
					}
				}
			}
			result = resultChangeStatus == true && resultCountVerificateCode == true;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}
}