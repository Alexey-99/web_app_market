package by.koroza.zoo_market.dao;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.model.entity.code.ConfirmationEmailCode;

/**
 * The Interface ConfirmationDao.
 */
public interface ConfirmationEmailCodeDao {

	/**
	 * Add the confirmation email code with user id.
	 *
	 * @param userId the user id
	 * @param code   the code
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean addConfirmationEmailCodeWithUserId(long userId, String code) throws DaoException;

	/**
	 * Get the confirmation code by user id.
	 *
	 * @param userId the user id
	 * @return the confirmation code by user id
	 * @throws DaoException the dao exception
	 */
	public ConfirmationEmailCode getConfirmationCodeByUserId(long userId) throws DaoException;

	/**
	 * Change confirmation code status by user id.
	 *
	 * @param userId the user id
	 * @param code   the code is Object with code is String, status of code is
	 *               boolean and date at create
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean changeConfirmationCodeStatusByUserId(long userId, ConfirmationEmailCode code) throws DaoException;
}