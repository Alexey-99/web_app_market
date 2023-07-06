package by.koroza.zoo_market.dao;

import by.koroza.zoo_market.dao.exception.DaoException;

public interface ConfirmationDao {

	public boolean addConfirmationEmailCodeWithUserId(long userId, String code) throws DaoException;

	public String getVerificateCodeByUserId(long userId) throws DaoException;

	public boolean changeVerificateCodeStatusByUserId(long userId, String code, boolean status) throws DaoException;
}