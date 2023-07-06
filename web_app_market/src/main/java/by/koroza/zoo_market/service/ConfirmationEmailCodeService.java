package by.koroza.zoo_market.service;

import by.koroza.zoo_market.service.exception.ServiceException;

/**
 * The Interface ConfirmationEmailCodeService.
 */
public interface ConfirmationEmailCodeService {

	/**
	 * Get the confirmation email code by user id.
	 *
	 * @param userId the user id
	 * @return the confirmation email code by user id
	 * @throws ServiceException the service exception
	 */
	public String getConfirmationEmailCodeByUserId(long userId) throws ServiceException;

	/**
	 * Change confirmation email code status by user id.
	 *
	 * @param userId the user id
	 * @param code   the code
	 * @param status the status
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean changeConfirmationCodeStatusByUserId(long userId, String code, boolean status)
			throws ServiceException;

	/**
	 * Send confirmation email code.
	 *
	 * @param userId    the user id
	 * @param userEmail the user email
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean sendConfirmationEmailCode(long userId, String userEmail) throws ServiceException;
}