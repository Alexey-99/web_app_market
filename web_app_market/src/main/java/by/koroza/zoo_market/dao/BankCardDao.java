package by.koroza.zoo_market.dao;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.model.entity.bank.BankCard;

/**
 * The Interface BankCardDao.
 */
public interface BankCardDao {

	/**
	 * Check if is have bank card.
	 *
	 * @param card the card
	 * @return true, if is have bank card
	 * @throws DaoException the dao exception
	 */
	public boolean isHaveBankCard(BankCard card) throws DaoException;

	/**
	 * Check if is have sum on bank card.
	 *
	 * @param card  the card
	 * @param price the price
	 * @return true, if is have sum on bank card
	 * @throws DaoException the dao exception
	 */
	public boolean isHaveSumOnBankCard(BankCard card, double price) throws DaoException;
}