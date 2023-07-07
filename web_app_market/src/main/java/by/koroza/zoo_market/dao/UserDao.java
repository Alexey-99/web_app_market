package by.koroza.zoo_market.dao;

import java.util.Optional;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.model.entity.user.User;

/**
 * The Interface UserDao.
 */
public interface UserDao {

	/**
	 * Check repeat login.
	 *
	 * @param login the login
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean checkRepeatLogin(String login) throws DaoException;

	/**
	 * Add the user.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean addUser(User user) throws DaoException;

	/**
	 * Get the user id by login.
	 *
	 * @param login the login
	 * @return the user id by login
	 * @throws DaoException the dao exception
	 */
	public long getUserIdByLogin(String login) throws DaoException;

	/**
	 * Change role status.
	 *
	 * @param userId       the user id
	 * @param roleStatusId the role status id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean changeRoleStatus(long userId, int roleStatusId) throws DaoException;

	/**
	 * Change role status.
	 *
	 * @param login        the login
	 * @param roleStatusId the role status id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean changeRoleStatus(String login, int roleStatusId) throws DaoException;

	/**
	 * Change confirmation email status.
	 *
	 * @param userId the user id
	 * @param status the status
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean changeConfirmationEmailStatus(long userId, boolean status) throws DaoException;

	/**
	 * Gets the user by login.
	 *
	 * @param login    the login
	 * @param password the password
	 * @return the user by login
	 * @throws DaoException the dao exception
	 */
	public Optional<User> getUserByLogin(String login, String password) throws DaoException;

	/**
	 * Change personal information (name, surname, email).
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean changePersonInformation(User user) throws DaoException;

	/**
	 * Change login.
	 *
	 * @param userId the user id
	 * @param login  the login
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean changeLogin(long userId, String login) throws DaoException;

	/**
	 * Change password.
	 *
	 * @param userId   the user id
	 * @param password the password
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean changePassword(long userId, String password) throws DaoException;

	/**
	 * Check if is exists user with login.
	 *
	 * @param login the login
	 * @return true, if is exists user with login
	 * @throws DaoException the dao exception
	 */
	public boolean isExistsUserWithLogin(String login) throws DaoException;

	/**
	 * Change email.
	 *
	 * @param userId the user id
	 * @param email  the email
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean changeEmail(long userId, String email) throws DaoException;

	/**
	 * Change discount.
	 *
	 * @param userId   the user id
	 * @param discount the discount
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean changeDiscount(long userId, double discount) throws DaoException;
}