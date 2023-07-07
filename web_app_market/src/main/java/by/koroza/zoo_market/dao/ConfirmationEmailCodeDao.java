package by.koroza.zoo_market.dao;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.model.entity.code.ConfirmationEmailCode;

// TODO: Auto-generated Javadoc
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
	 * @param code   the code
	 * @param status the status
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public boolean changeConfirmationCodeStatusByUserId(long userId, String code, boolean status) throws DaoException;
}