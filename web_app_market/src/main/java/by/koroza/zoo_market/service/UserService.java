package by.koroza.zoo_market.service;

import java.util.Optional;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;

public interface UserService {

	public boolean addRegistratedUserToBD(User user) throws ServiceException;

	public boolean checkRepeatLogin(String login) throws ServiceException;

	public long getUserIdByLogin(String login) throws ServiceException;

	public boolean changeRoleStatus(long userId, int roleStatusId) throws ServiceException;

	public boolean changeRoleStatus(String userLogin, int roleStatusId) throws ServiceException;

	public boolean changeVerificationEmailStatus(long userId, boolean verificateStatus) throws ServiceException;

	public Optional<User> getUserByLogin(String login, String password) throws ServiceException;

	public boolean changePersonInformation(User user) throws ServiceException;

	public boolean changeLogin(User user, String login) throws ServiceException;

	public boolean changePassword(User user, String password) throws ServiceException;

	public boolean isExistsUserWithLogin(String login) throws ServiceException;

	public boolean changeEmail(User user) throws ServiceException;

	public boolean changePersonProcentDiscount(User user) throws ServiceException;
}