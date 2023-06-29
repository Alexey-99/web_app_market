package by.koroza.zoo_market.service;

import by.koroza.zoo_market.service.exception.ServiceException;

public interface ConfirmationService {

	public boolean addVerificateCodeWithUserId(long userId, String code) throws ServiceException;

	public String getVerificateCodeByUserId(long userId) throws ServiceException;

	public boolean changeVerificateCodeStatusByUserId(long userId, String code, boolean status) throws ServiceException;
}