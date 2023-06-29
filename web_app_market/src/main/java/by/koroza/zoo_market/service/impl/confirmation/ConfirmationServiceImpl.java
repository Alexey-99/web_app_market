package by.koroza.zoo_market.service.impl.confirmation;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.dao.impl.confirmation.ConfirmationDaoImpl;
import by.koroza.zoo_market.service.ConfirmationService;
import by.koroza.zoo_market.service.exception.ServiceException;

public class ConfirmationServiceImpl implements ConfirmationService {
	private static final ConfirmationService INSTANCE = new ConfirmationServiceImpl();

	private ConfirmationServiceImpl() {
	}

	public static ConfirmationService getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean addVerificateCodeWithUserId(long userId, String code) throws ServiceException {
		try {
			return ConfirmationDaoImpl.getInstance().addVerificateCodeWithUserId(userId, code);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public String getVerificateCodeByUserId(long userId) throws ServiceException {
		try {
			return ConfirmationDaoImpl.getInstance().getVerificateCodeByUserId(userId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean changeVerificateCodeStatusByUserId(long userId, String code, boolean status)
			throws ServiceException {
		try {
			return ConfirmationDaoImpl.getInstance().changeVerificateCodeStatusByUserId(userId, code, status);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}