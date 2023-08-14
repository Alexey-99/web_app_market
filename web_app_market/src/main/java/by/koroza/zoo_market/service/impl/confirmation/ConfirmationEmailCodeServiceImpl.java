package by.koroza.zoo_market.service.impl.confirmation;

import static by.koroza.zoo_market.web.command.name.email.EmailComponent.EMAIL_SUBJECT;
import static by.koroza.zoo_market.web.command.name.email.EmailComponent.EMAIL_TEXT;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.dao.impl.confirmation.ConfirmationEmailCodeDaoImpl;
import by.koroza.zoo_market.model.entity.code.ConfirmationEmailCode;
import by.koroza.zoo_market.service.ConfirmationEmailCodeService;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.generator.GenerationConfirmationEmailCodeImpl;
import by.koroza.zoo_market.service.impl.sender.EmailSenderImpl;

/**
 * The Class ConfirmationEmailCodeServiceImpl.
 */
public class ConfirmationEmailCodeServiceImpl implements ConfirmationEmailCodeService {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final ConfirmationEmailCodeService INSTANCE = new ConfirmationEmailCodeServiceImpl();

	/**
	 * Instantiates a new confirmation email code service impl.
	 */
	private ConfirmationEmailCodeServiceImpl() {
	}

	/**
	 * Get the single instance of ConfirmationEmailCodeServiceImpl.
	 *
	 * @return single instance of ConfirmationEmailCodeServiceImpl
	 */
	public static ConfirmationEmailCodeService getInstance() {
		return INSTANCE;
	}

	/**
	 * Get the confirmation email code by user id.
	 *
	 * @param userId the user id
	 * @return the confirmation email code by user id
	 * @throws ServiceException the service exception
	 */
	@Override
	public ConfirmationEmailCode getConfirmationEmailCodeByUserId(long userId) throws ServiceException {
		try {
			return ConfirmationEmailCodeDaoImpl.getInstance().getConfirmationCodeByUserId(userId);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Change confirmation code status by user id.
	 *
	 * @param userId the user id
	 * @param code   the code is Object with code is String, status of code is
	 *               boolean and date at create
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean changeConfirmationCodeStatusByUserId(long userId, ConfirmationEmailCode code)
			throws ServiceException {
		try {
			return ConfirmationEmailCodeDaoImpl.getInstance().changeConfirmationCodeStatusByUserId(userId, code);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Send confirmation email code.
	 *
	 * @param userId    the user id
	 * @param userEmail the user email
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean sendConfirmationEmailCode(long userId, String userEmail) throws ServiceException {
		try {
			String code = GenerationConfirmationEmailCodeImpl.getInstance().getGeneratedCode();
			ConfirmationEmailCodeDaoImpl.getInstance().addConfirmationEmailCodeWithUserId(userId, code);
			return EmailSenderImpl.getInstance().emailSend(EMAIL_SUBJECT, EMAIL_TEXT + code, userEmail);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}
}