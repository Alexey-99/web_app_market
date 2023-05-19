package by.koroza.zoo_market.service;

import by.koroza.zoo_market.model.entity.bank.BankCard;
import by.koroza.zoo_market.service.exception.ServiceException;

public interface BankCardService {

	public boolean isHaveBankCard(BankCard card) throws ServiceException;

	public boolean isHaveSumOnBankCard(BankCard card, double price) throws ServiceException;
}