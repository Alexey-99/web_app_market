package by.koroza.zoo_market.dao.impl.confirmation;

import static by.koroza.zoo_market.dao.name.ColumnName.VERIFICATE_CODES_CODE;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_VERIFICATE_CODES_CODE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.pool.ConnectionPool;
import by.koroza.zoo_market.dao.pool.ProxyConnection;
import by.koroza.zoo_market.dao.ConfirmationEmailCodeDao;
import by.koroza.zoo_market.dao.exception.checkable.DaoException;

/**
 * The Class ConfirmationEmailCodeDaoImpl.
 */
public class ConfirmationEmailCodeDaoImpl implements ConfirmationEmailCodeDao {
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final ConfirmationEmailCodeDao INSTANCE = new ConfirmationEmailCodeDaoImpl();

	/**
	 * The Constant
	 * QUERY_SELECT_COUNT_CONFIRMATION_CODES_WITH_USER_ID_AND_STATUS_OPEN.
	 */
	private static final String QUERY_SELECT_COUNT_CONFIRMATION_CODES_WITH_USER_ID_AND_STATUS_OPEN = """
			SELECT COUNT(verificate_сodes.code)
			FROM verificate_сodes
			WHERE verificate_сodes.users_id = ? AND verificate_сodes.is_open = ?
			""";

	/**
	 * Instantiates a new confirmation email code dao impl.
	 */
	private ConfirmationEmailCodeDaoImpl() {

	}

	/**
	 * Get the single instance of ConfirmationEmailCodeDaoImpl.
	 *
	 * @return single instance of ConfirmationEmailCodeDaoImpl
	 */
	public static ConfirmationEmailCodeDao getInstance() {
		return INSTANCE;
	}

	/** The Constant QUERY_INSERT_CONFIRMATION_CODE_WITH_USER_ID. */
	private static final String QUERY_INSERT_CONFIRMATION_CODE_WITH_USER_ID = """
			INSERT INTO verificate_сodes(users_id, code, is_open)
			VALUE (?, ?, ?)
			""";

	/** The Constant QUERY_CHANGE_STATUS_ALL_OLD_CONFIRMATION_CODE_WITH_USER_ID. */
	private static final String QUERY_CHANGE_STATUS_ALL_OLD_CONFIRMATION_CODE_WITH_USER_ID = """
			UPDATE verificate_сodes
			SET is_open = ?
			WHERE users_id = ? AND id != LAST_INSERT_ID()
			""";

	/**
	 * Add the confirmation email code with user id.
	 *
	 * @param userId the user id
	 * @param code   the code
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean addConfirmationEmailCodeWithUserId(long userId, String code) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			boolean resultInsertVerificateCode = false;
			try (PreparedStatement statement = connection
					.prepareStatement(QUERY_INSERT_CONFIRMATION_CODE_WITH_USER_ID)) {
				statement.setLong(1, userId);
				statement.setString(2, code);
				statement.setBoolean(3, true);
				resultInsertVerificateCode = statement.executeUpdate() > 0;
			}
			try (PreparedStatement statement = connection
					.prepareStatement(QUERY_CHANGE_STATUS_ALL_OLD_CONFIRMATION_CODE_WITH_USER_ID)) {
				statement.setBoolean(1, false);
				statement.setLong(2, userId);
				statement.execute();
			}
			boolean resultCountVerificateCode = false;
			try (PreparedStatement statement = connection
					.prepareStatement(QUERY_SELECT_COUNT_CONFIRMATION_CODES_WITH_USER_ID_AND_STATUS_OPEN)) {
				statement.setLong(1, userId);
				statement.setBoolean(2, CONFIRMATION_CODE_STATUS_OPEN);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						if (resultSet.getLong(IDENTIFIER_COUNT_ROWS_OF_VERIFICATE_CODES_CODE) == 1) {
							resultCountVerificateCode = true;
						}
					}
				}
			}
			result = resultInsertVerificateCode && resultCountVerificateCode;
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_GET_CONFIRMATION_CODE_BY_USER_ID. */
	private static final String QUERY_GET_CONFIRMATION_CODE_BY_USER_ID = """
			SELECT verificate_сodes.code
			FROM verificate_сodes
			WHERE verificate_сodes.users_id = ?;
			""";

	/**
	 * Get the confirmation code by user id.
	 *
	 * @param userId the user id
	 * @return the confirmation code by user id
	 * @throws DaoException the dao exception
	 */
	@Override
	public String getConfirmationCodeByUserId(long userId) throws DaoException {
		String code = null;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_GET_CONFIRMATION_CODE_BY_USER_ID)) {
			statement.setLong(1, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					code = resultSet.getString(VERIFICATE_CODES_CODE);
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return code;
	}

	/** The Constant CONFIRMATION_CODE_STATUS_OPEN. */
	private static final boolean CONFIRMATION_CODE_STATUS_OPEN = true;

	/** The Constant CONFIRMATION_CODE_STATUS_CLOSED. */
	@SuppressWarnings("unused")
	private static final boolean CONFIRMATION_CODE_STATUS_CLOSED = false;

	/** The Constant QUERY_CHANGE_STATUS_CONFIRMATION_CODE_WITH_USER_ID. */
	private static final String QUERY_CHANGE_STATUS_CONFIRMATION_CODE_WITH_USER_ID = """
			UPDATE verificate_сodes
			SET verificate_сodes.is_open = ?
			WHERE verificate_сodes.users_id = ? AND verificate_сodes.code != ?;
			""";

	/**
	 * Change confirmation code status by user id.
	 *
	 * @param userId the user id
	 * @param code   the code
	 * @param status the status
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean changeConfirmationCodeStatusByUserId(long userId, String code, boolean status) throws DaoException {
		boolean result = true;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			boolean resultChangeStatus = false;
			try (PreparedStatement statement = connection
					.prepareStatement(QUERY_CHANGE_STATUS_CONFIRMATION_CODE_WITH_USER_ID)) {
				statement.setBoolean(1, status);
				statement.setLong(2, userId);
				statement.setString(3, code);
				resultChangeStatus = statement.executeUpdate() > 0;
			}
			boolean resultCountVerificateCode = false;
			try (PreparedStatement statement = connection
					.prepareStatement(QUERY_SELECT_COUNT_CONFIRMATION_CODES_WITH_USER_ID_AND_STATUS_OPEN)) {
				statement.setLong(1, userId);
				statement.setBoolean(2, CONFIRMATION_CODE_STATUS_OPEN);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						if (resultSet.getLong(IDENTIFIER_COUNT_ROWS_OF_VERIFICATE_CODES_CODE) == 0) {
							resultCountVerificateCode = true;
						}
					}
				}
			}
			result = resultChangeStatus && resultCountVerificateCode;
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}
}