package by.koroza.zoo_market.dao.impl.user;

import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_USER_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_USER_LOGINS;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_LAST_INSERT_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.ROLES_NAME;
import static by.koroza.zoo_market.dao.name.ColumnName.USERS_DISCOUNT;
import static by.koroza.zoo_market.dao.name.ColumnName.USERS_EMAIL;
import static by.koroza.zoo_market.dao.name.ColumnName.USERS_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.USERS_LOGIN;
import static by.koroza.zoo_market.dao.name.ColumnName.USERS_PASSWORD;
import static by.koroza.zoo_market.dao.name.ColumnName.USERS_CONFIRMATION_EMAIL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.UserDao;
import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.dao.pool.ConnectionPool;
import by.koroza.zoo_market.dao.pool.ProxyConnection;
import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.User;

/**
 * The Class UserDaoImpl.
 */
public class UserDaoImpl implements UserDao {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final UserDao INSTANCE = new UserDaoImpl();

	/** The Constant QUERY_SELECT_LAST_INSERT_ID. */
	private static final String QUERY_SELECT_LAST_INSERT_ID = """
			SELECT LAST_INSERT_ID();
			""";

	/**
	 * Instantiates a new user dao impl.
	 */
	private UserDaoImpl() {
	}

	/**
	 * Gets the single instance of UserDaoImpl.
	 *
	 * @return single instance of UserDaoImpl
	 */
	public static UserDao getInstance() {
		return INSTANCE;
	}

	/** The Constant QUERY_SELECT_COUNT_REPEAT_LOGIN. */
	private static final String QUERY_SELECT_COUNT_REPEAT_LOGIN = """
			SELECT COUNT(users.login)
			FROM users
			WHERE users.login = ?;
			""";

	/**
	 * Check repeat login.
	 *
	 * @param login the login
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean checkRepeatLogin(String login) throws DaoException {
		int countReapeatLogin = 0;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_COUNT_REPEAT_LOGIN)) {
			statement.setString(1, login);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					countReapeatLogin = resultSet.getInt(IDENTIFIER_COUNT_ROWS_OF_USER_LOGINS);
					log.log(Level.INFO, countReapeatLogin);
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return countReapeatLogin > 0;
	}

	/** The Constant QUERY_INSERT_USER. */
	private static final String QUERY_INSERT_USER = """
			INSERT INTO users(users.login, users.password, users.email, users.confirmation_email, users.roles_id)
			VALUES (?, ?, ?, ?, ?);
			""";

	/**
	 * Add the user.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean addUser(User user) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_USER)) {
				statement.setString(1, user.getLogin());
				statement.setString(2, user.getPassword());
				statement.setString(3, user.getEmail());
				statement.setBoolean(4, user.isVerificatedEmail());
				statement.setInt(5, user.getRole().getId());
				result = statement.executeUpdate() > 0;
			}
			try (PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_LAST_INSERT_ID);
					ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					user.setId(resultSet.getLong(IDENTIFIER_LAST_INSERT_ID));
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_GET_USER_ID_BY_LOGIN. */
	private static final String QUERY_GET_USER_ID_BY_LOGIN = """
			SELECT users.id
			FROM users
			WHERE users.login = ?;
			""";

	/**
	 * Get the user id by login.
	 *
	 * @param login the login
	 * @return the user id by login
	 * @throws DaoException the dao exception
	 */
	@Override
	public long getUserIdByLogin(String login) throws DaoException {
		long userId = 0;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_GET_USER_ID_BY_LOGIN)) {
			statement.setString(1, login);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					userId = resultSet.getLong(USERS_ID);
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return userId;
	}

	/** The Constant QUERY_CHANGE_ROLE_STATUS. */
	private static final String QUERY_CHANGE_ROLE_STATUS = """
			UPDATE users
			SET users.roles_id = ?
			WHERE users.id = ?;
			""";

	/**
	 * Change role status.
	 *
	 * @param userId       the user id
	 * @param roleStatusId the role status id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean changeRoleStatus(long userId, int roleStatusId) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_CHANGE_ROLE_STATUS)) {
			statement.setInt(1, roleStatusId);
			statement.setLong(2, userId);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_CHANGE_ROLE_STATUS_BY_LOGIN. */
	private static final String QUERY_CHANGE_ROLE_STATUS_BY_LOGIN = """
			UPDATE users
			SET users.roles_id = ?
			WHERE users.login = ?;
			""";

	/**
	 * Change role status.
	 *
	 * @param login        the login
	 * @param roleStatusId the role status id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean changeRoleStatus(String login, int roleStatusId) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_CHANGE_ROLE_STATUS_BY_LOGIN)) {
			statement.setInt(1, roleStatusId);
			statement.setString(2, login);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_CHANGE_VERIFICATE_EMAIL_STATUS. */
	private static final String QUERY_CHANGE_VERIFICATE_EMAIL_STATUS = """
			UPDATE users
			SET users.confirmation_email = ?
			WHERE users.id = ?;
			""";

	/**
	 * Change confirmation email status.
	 *
	 * @param userId the user id
	 * @param status the status
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean changeConfirmationEmailStatus(long userId, boolean status) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_CHANGE_VERIFICATE_EMAIL_STATUS)) {
			statement.setBoolean(1, status);
			statement.setLong(2, userId);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_GET_USER_BY_LOGIN_AND_PASSWORD. */
	private static final String QUERY_GET_USER_BY_LOGIN_AND_PASSWORD = """
			SELECT users.id, users.name, users.surname, roles.name, users.email, users.confirmation_email, users.login, users.password, users.discount, users.date_create
			FROM users INNER JOIN roles
			ON users.roles_id = roles.id
			WHERE users.login = ? AND users.password = ?;
			""";

	/**
	 * Get the user by login.
	 *
	 * @param login    the login
	 * @param password the password
	 * @return the user by login
	 * @throws DaoException the dao exception
	 */
	@Override
	public Optional<User> getUserByLogin(String login, String password) throws DaoException {
		Optional<User> user = Optional.empty();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_GET_USER_BY_LOGIN_AND_PASSWORD)) {
			statement.setString(1, login);
			statement.setString(2, password);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					User userBuild = new User.UserBuilder().setId(resultSet.getLong(USERS_ID))
							.setEmail(resultSet.getString(USERS_EMAIL))
							.setVerificatedEmail(resultSet.getBoolean(USERS_CONFIRMATION_EMAIL))
							.setLogin(resultSet.getString(USERS_LOGIN))
							.setRole(UserRole.valueOf(resultSet.getString(ROLES_NAME)))
							.setDiscount(resultSet.getDouble(USERS_DISCOUNT)).build();
					userBuild.setPassword(resultSet.getString(USERS_PASSWORD));
					user = Optional.of(userBuild);
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return user;
	}

	/** The Constant QUERY_CHANGE_LOGIN. */
	private static final String QUERY_CHANGE_LOGIN = """
			UPDATE users
			SET users.login = ?
			WHERE users.id = ?;
			""";

	/**
	 * Change login.
	 *
	 * @param userId the user id
	 * @param login  the login
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean changeLogin(long userId, String login) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_CHANGE_LOGIN)) {
			statement.setString(1, login);
			statement.setLong(2, userId);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_CHANGE_PASSWORD. */
	private static final String QUERY_CHANGE_PASSWORD = """
			UPDATE users
			SET users.password = ?
			WHERE users.id = ?;
			""";

	/**
	 * Change password.
	 *
	 * @param userId   the user id
	 * @param password the password
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean changePassword(long userId, String password) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_CHANGE_PASSWORD)) {
			statement.setString(1, password);
			statement.setLong(2, userId);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_COUNT_ROW_WITH_LOGIN. */
	private static final String QUERY_COUNT_ROW_WITH_LOGIN = """
			SELECT COUNT(users.login)
			FROM users
			WHERE users.login = ?;
			""";

	/**
	 * Check if is exists user with login.
	 *
	 * @param login the login
	 * @return true, if is exists user with login
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean isExistsUserWithLogin(String login) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_COUNT_ROW_WITH_LOGIN)) {
			statement.setString(1, login);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					result = resultSet.getInt(IDENTIFIER_COUNT_ROWS_OF_USER_LOGINS) > 0;
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	private static final String QUERY_COUNT_ROW_WITH_LOGIN_AND_PASSWORD_BY_USER_ID = """
			SELECT COUNT(users.id)
			FROM users
			WHERE users.login = ? AND users.password = ? AND users.id = ?;
			""";

	/**
	 * Check if is exists user with login and password by user id.
	 *
	 * @param login    the login
	 * @param password the password
	 * @param userId   the user id
	 * @return true, if is exists user with login and password by user id
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean isExistsUserWithLoginAndPasswordByUserId(String login, String password, long userId)
			throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(QUERY_COUNT_ROW_WITH_LOGIN_AND_PASSWORD_BY_USER_ID)) {
			statement.setString(1, login);
			statement.setString(2, password);
			statement.setLong(3, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					result = resultSet.getInt(IDENTIFIER_COUNT_ROWS_OF_USER_ID) > 0;
				}
			}
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_CHANGE_EMAIL. */
	private static final String QUERY_CHANGE_EMAIL = """
			UPDATE users
			SET users.email = ?,
			users.confirmation_email = false
			WHERE users.id = ?;
			""";

	/**
	 * Change email.
	 *
	 * @param userId the user id
	 * @param email  the email
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean changeEmail(long userId, String email) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_CHANGE_EMAIL)) {
			statement.setString(1, email);
			statement.setLong(2, userId);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}

	/** The Constant QUERY_CHANGE_DISCOUNT. */
	private static final String QUERY_CHANGE_DISCOUNT = """
			UPDATE users
			SET users.discount = ?,
			WHERE users.id = ?;
			""";

	/**
	 * Change discount.
	 *
	 * @param userId   the user id
	 * @param discount the discount
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean changeDiscount(long userId, double discount) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_CHANGE_DISCOUNT)) {
			statement.setDouble(1, discount);
			statement.setLong(2, userId);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new DaoException(e);
		}
		return result;
	}
}