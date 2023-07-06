package by.koroza.zoo_market.service;

import by.koroza.zoo_market.model.entity.bank.BankCard;
import by.koroza.zoo_market.service.exception.ServiceException;

/**
 * The Interface BankCardService.
 */
public interface BankCardService {

	/**
	 * Checks if is have bank card.
	 *
	 * @param card the card
	 * @return true, if is have bank card
	 * @throws ServiceException the service exception
	 */
	public boolean isHaveBankCard(BankCard card) throws ServiceException;

	/**
	 * Checks if is have sum on bank card.
	 *
	 * @param card  the card
	 * @param price the price
	 * @return true, if is have sum on bank card
	 * @throws ServiceException the service exception
	 */
	public boolean isHaveSumOnBankCard(BankCard card, double price) throws ServiceException;
}