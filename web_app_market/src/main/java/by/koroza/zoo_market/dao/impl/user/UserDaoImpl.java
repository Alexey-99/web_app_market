package by.koroza.zoo_market.dao.impl.user;

import static by.koroza.zoo_market.dao.name.ColumnName.USERS_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.USERS_NAME;
import static by.koroza.zoo_market.dao.name.ColumnName.USERS_SURNAME;
import static by.koroza.zoo_market.dao.name.ColumnName.USERS_EMAIL;
import static by.koroza.zoo_market.dao.name.ColumnName.USERS_LOGIN;
import static by.koroza.zoo_market.dao.name.ColumnName.ROLES_NAME;
import static by.koroza.zoo_market.dao.name.ColumnName.USERS_DISCOUNT;
import static by.koroza.zoo_market.dao.name.ColumnName.USERS_PASSWORD;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_USER_LOGINS;
import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_LAST_INSERT_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.USERS_VERIFICATED_EMAIL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.pool.ConnectionPool;
import by.koroza.zoo_market.dao.pool.ProxyConnection;
import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.model.entity.user.reserved.User;
import by.koroza.zoo_market.dao.UserDao;
import by.koroza.zoo_market.dao.exception.DaoException;

public class UserDaoImpl implements UserDao {
	private static final UserDao INSTANCE = new UserDaoImpl();
	private static final Logger log = LogManager.getLogger();

	private static final String QUERY_SELECT_LAST_INSERT_ID = """
			SELECT LAST_INSERT_ID();
			""";

	private UserDaoImpl() {
	}

	public static UserDao getInstance() {
		return INSTANCE;
	}

	private static final String QUERY_SELECT_COUNT_REPEAT_LOGIN = """
			SELECT COUNT(users.login)
			FROM users
			WHERE users.login = ?;
			""";

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
			throw new DaoException(e);
		}
		return countReapeatLogin > 0;
	}

	private static final String QUERY_INSERT_USER = """
			INSERT INTO users(users.name, users.surname, users.login, users.password, users.email, users.roles_id)
			VALUES (?, ?, ?, ?, ?, ?);
			""";

	@Override
	public boolean addUser(AbstractRegistratedUser user) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_USER)) {
				statement.setString(1, user.getName());
				statement.setString(2, user.getSurname());
				statement.setString(3, user.getLogin());
				statement.setString(4, user.getPassword());
				statement.setString(5, user.getEmail());
				statement.setInt(6, user.getRole().getIdRole());
				result = statement.executeUpdate() > 0;
			}
			try (PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_LAST_INSERT_ID);
					ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					user.setId(resultSet.getLong(IDENTIFIER_LAST_INSERT_ID));
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	private static final String QUERY_GET_USER_ID_BY_LOGIN = """
			SELECT users.id
			FROM users
			WHERE users.login = ?;
			""";

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
			throw new DaoException(e);
		}
		return userId;
	}

	private static final String QUERY_CHANGE_ROLE_STATUS = """
			UPDATE users
			SET users.roles_id = ?
			WHERE users.id = ?;
			""";

	@Override
	public boolean changeRoleStatus(long userId, int roleStatusId) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_CHANGE_ROLE_STATUS)) {
			statement.setInt(1, roleStatusId);
			statement.setLong(2, userId);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	private static final String QUERY_CHANGE_VERIFICATE_EMAIL_STATUS = """
			UPDATE users
			SET users.verificated_email = ?
			WHERE users.id = ?;
			""";

	@Override
	public boolean changeVerificationEmailStatus(long userId, boolean verificateStatus) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_CHANGE_VERIFICATE_EMAIL_STATUS)) {
			statement.setBoolean(1, verificateStatus);
			statement.setLong(2, userId);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	private static final String QUERY_GET_USER_BY_LOGIN_AND_PASSWORD = """
			SELECT users.id, users.name, users.surname, roles.name, users.email, users.verificated_email, users.login, users.password, users.discount, users.date_create
			FROM users INNER JOIN roles
			ON users.roles_id = roles.id
			WHERE users.login = ? AND users.password = ?;
			""";

	@Override
	public Optional<AbstractRegistratedUser> getUserByLogin(String login, String password) throws DaoException {
		Optional<AbstractRegistratedUser> user = Optional.empty();
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_GET_USER_BY_LOGIN_AND_PASSWORD)) {
			statement.setString(1, login);
			statement.setString(2, password);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					AbstractRegistratedUser userBuild = new User.UserBuilder().setId(resultSet.getLong(USERS_ID))
							.setName(resultSet.getString(USERS_NAME)).setSurname(resultSet.getString(USERS_SURNAME))
							.setEmail(resultSet.getString(USERS_EMAIL))
							.setVerificatedEmail(resultSet.getBoolean(USERS_VERIFICATED_EMAIL))
							.setLogin(resultSet.getString(USERS_LOGIN))
							.setRole(UserRole.valueOf(resultSet.getString(ROLES_NAME)))
							.setDiscount(resultSet.getDouble(USERS_DISCOUNT)).build();
					userBuild.setPassword(resultSet.getString(USERS_PASSWORD));
					user = Optional.of(userBuild);
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return user;
	}

	private static final String QUERY_CHANGE_PERSON_INFOMATION = """
			UPDATE users
			SET users.name = ?, users.surname = ?, users.email = ?, users.roles_id = ?
			WHERE users.id = ?;
			""";

	@Override
	public boolean changePersonInformation(AbstractRegistratedUser user, String name, String surname, String email)
			throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_CHANGE_PERSON_INFOMATION)) {
			statement.setString(1, name);
			statement.setString(2, surname);
			statement.setString(3, email);
			statement.setLong(4,
					(user.getEmail() != null ? user.getEmail().equals(email)
							: (user.getEmail() == null && email == null)) ? user.getRole().getIdRole()
									: UserRole.WAITING_CODE_REGISTRATION.getIdRole());
			statement.setLong(5, user.getId());
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	private static final String QUERY_CHANGE_LOGIN_AND_PASSWORD = """
			UPDATE users
			SET users.login = ?, users.password = ?
			WHERE users.id = ?;
			""";

	@Override
	public boolean changeLoginAndPassword(AbstractRegistratedUser user, String login, String password)
			throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_CHANGE_LOGIN_AND_PASSWORD)) {
			statement.setString(1, login);
			statement.setString(2, password);
			statement.setLong(3, user.getId());
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}
}