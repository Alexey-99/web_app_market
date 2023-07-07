package by.koroza.zoo_market.service.impl.bank;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.dao.impl.bank.BankCardDaoImpl;
import by.koroza.zoo_market.model.entity.bank.BankCard;
import by.koroza.zoo_market.service.BankCardService;
import by.koroza.zoo_market.service.exception.ServiceException;

/**
 * The Class BankCardServiceImpl.
 */
public class BankCardServiceImpl implements BankCardService {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final BankCardService INSTANCE = new BankCardServiceImpl();

	/**
	 * Instantiates a new bank card service impl.
	 */
	private BankCardServiceImpl() {
	}

	/**
	 * Get the single instance of BankCardServiceImpl.
	 *
	 * @return single instance of BankCardServiceImpl
	 */
	public static BankCardService getInstance() {
		return INSTANCE;
	}

	/**
	 * Check if is have bank card.
	 *
	 * @param card the card
	 * @return true, if is have bank card
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean isHaveBankCard(BankCard card) throws ServiceException {
		try {
			return BankCardDaoImpl.getInstance().isHaveBankCard(card);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Check if is have sum on bank card.
	 *
	 * @param card  the card
	 * @param price the price
	 * @return true, if is have sum on bank card
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean isHaveSumOnBankCard(BankCard card, double price) throws ServiceException {
		try {
			return BankCardDaoImpl.getInstance().isHaveSumOnBankCard(card, price);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}
}