package by.koroza.zoo_market.dao;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.model.entity.bank.BankCard;

public interface BankCardDao {

	public boolean isHaveBankCard(BankCard card) throws DaoException;

	public boolean isHaveSumOnBankCard(BankCard card, double price) throws DaoException;
}