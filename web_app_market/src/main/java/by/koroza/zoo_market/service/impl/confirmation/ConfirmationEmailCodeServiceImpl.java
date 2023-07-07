package by.koroza.zoo_market.service.impl.confirmation;

import static by.koroza.zoo_market.web.command.name.email.EmailComponent.EMAIL_SUBJECT;
import static by.koroza.zoo_market.web.command.name.email.EmailComponent.EMAIL_TEXT;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.dao.impl.confirmation.ConfirmationEmailCodeDaoImpl;
import by.koroza.zoo_market.service.ConfirmationEmailCodeService;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.generator.GenerationConfirmationEmailCodeImpl;
import by.koroza.zoo_market.service.impl.sender.EmailSenderImpl;

public class ConfirmationEmailCodeServiceImpl implements ConfirmationEmailCodeService {
	private static final ConfirmationEmailCodeService INSTANCE = new ConfirmationEmailCodeServiceImpl();

	private ConfirmationEmailCodeServiceImpl() {
	}

	public static ConfirmationEmailCodeService getInstance() {
		return INSTANCE;
	}

	@Override
	public String getConfirmationEmailCodeByUserId(long userId) throws ServiceException {
		try {
			return ConfirmationEmailCodeDaoImpl.getInstance().getConfirmationCodeByUserId(userId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean changeConfirmationCodeStatusByUserId(long userId, String code, boolean status)
			throws ServiceException {
		try {
			return ConfirmationEmailCodeDaoImpl.getInstance().changeConfirmationCodeStatusByUserId(userId, code, status);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean sendConfirmationEmailCode(long userId, String userEmail) throws ServiceException {
		try {
			String code = GenerationConfirmationEmailCodeImpl.getInstance().getGeneratedCode();
			ConfirmationEmailCodeDaoImpl.getInstance().addConfirmationEmailCodeWithUserId(userId, code);
			return EmailSenderImpl.getInstance().emailSend(EMAIL_SUBJECT, EMAIL_TEXT + code, userEmail);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}