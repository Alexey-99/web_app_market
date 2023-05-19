package by.koroza.zoo_market.service.impl;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.dao.impl.VerificateDaoImpl;
import by.koroza.zoo_market.service.VerificateService;
import by.koroza.zoo_market.service.exception.ServiceException;

public class VerificateServiceImpl implements VerificateService {
	private static final VerificateService INSTANCE = new VerificateServiceImpl();

	private VerificateServiceImpl() {
	}

	public static VerificateService getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean addVerificateCodeWithUserId(long userId, String code) throws ServiceException {
		try {
			return VerificateDaoImpl.getInstance().addVerificateCodeWithUserId(userId, code);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public String getVerificateCodeByUserId(long userId) throws ServiceException {
		try {
			return VerificateDaoImpl.getInstance().getVerificateCodeByUserId(userId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean changeVerificateCodeStatusByUserId(long userId, String code, boolean status)
			throws ServiceException {
		try {
			return VerificateDaoImpl.getInstance().changeVerificateCodeStatusByUserId(userId, code, status);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}