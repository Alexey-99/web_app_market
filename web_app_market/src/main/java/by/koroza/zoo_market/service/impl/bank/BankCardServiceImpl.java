package by.koroza.zoo_market.service.impl.bank;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.dao.impl.bank.BankCardDaoImpl;
import by.koroza.zoo_market.model.entity.bank.BankCard;
import by.koroza.zoo_market.service.BankCardService;
import by.koroza.zoo_market.service.exception.ServiceException;

public class BankCardServiceImpl implements BankCardService {
	private static final BankCardService INSTANCE = new BankCardServiceImpl();

	private BankCardServiceImpl() {
	}

	public static BankCardService getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean isHaveBankCard(BankCard card) throws ServiceException {
		try {
			return BankCardDaoImpl.getInstance().isHaveBankCard(card);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean isHaveSumOnBankCard(BankCard card, double price) throws ServiceException {
		try {
			return BankCardDaoImpl.getInstance().isHaveSumOnBankCard(card, price);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}