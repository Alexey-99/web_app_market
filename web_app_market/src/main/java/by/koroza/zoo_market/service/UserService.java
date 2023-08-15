package by.koroza.zoo_market.service;

import java.util.Optional;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;

/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Add the user.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean addUser(User user) throws ServiceException;

	/**
	 * Check repeat login.
	 *
	 * @param login the login
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean checkRepeatLogin(String login) throws ServiceException;

	/**
	 * Get the user id by login.
	 *
	 * @param login the login
	 * @return the user id by login
	 * @throws ServiceException the service exception
	 */
	public long getUserIdByLogin(String login) throws ServiceException;

	/**
	 * Change role status.
	 *
	 * @param userId       the user id
	 * @param roleStatusId the role status id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean changeRoleStatus(long userId, int roleStatusId) throws ServiceException;

	/**
	 * Change role status.
	 *
	 * @param userLogin    the user login
	 * @param roleStatusId the role status id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean changeRoleStatus(String userLogin, int roleStatusId) throws ServiceException;

	/**
	 * Change confirmation email status.
	 *
	 * @param userId the user id
	 * @param status the status
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean changeConfirmationEmailStatus(long userId, boolean status) throws ServiceException;

	/**
	 * Get the user by login.
	 *
	 * @param login    the login
	 * @param password the password
	 * @return the user by login
	 * @throws ServiceException the service exception
	 */
	public Optional<User> getUserByLogin(String login, String password) throws ServiceException;

	/**
	 * Change login.
	 *
	 * @param userId the user id
	 * @param login  the login
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean changeLogin(long userId, String login) throws ServiceException;

	/**
	 * Change password.
	 *
	 * @param userId   the user id
	 * @param password the password
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean changePassword(long userId, String password) throws ServiceException;

	/**
	 * Checks if is exists user with login.
	 *
	 * @param login the login
	 * @return true, if is exists user with login
	 * @throws ServiceException the service exception
	 */
	public boolean isExistsUserWithLogin(String login) throws ServiceException;

	/**
	 * Change email.
	 *
	 * @param userId the user id
	 * @param email  the email
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean changeEmail(long userId, String email) throws ServiceException;

	/**
	 * Change person percent discount.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean changePersonPercentDiscount(User user) throws ServiceException;
}