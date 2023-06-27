package by.koroza.zoo_market.service;

import java.util.Optional;

import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.service.exception.ServiceException;

public interface UserService {

	public boolean addRegistratedUserToBD(AbstractRegistratedUser user) throws ServiceException;

	public boolean checkRepeatLogin(String login) throws ServiceException;

	public long getUserIdByLogin(String login) throws ServiceException;

	public boolean changeRoleStatus(long userId, int roleStatusId) throws ServiceException;

	public boolean changeRoleStatus(String userLogin, int roleStatusId) throws ServiceException;

	public boolean changeVerificationEmailStatus(long userId, boolean verificateStatus) throws ServiceException;

	public Optional<AbstractRegistratedUser> getUserByLogin(String login, String password) throws ServiceException;

	public boolean changePersonInformation(AbstractRegistratedUser user, String name, String surname, String email)
			throws ServiceException;

	public boolean changeLoginAndPassword(AbstractRegistratedUser user, String login, String password)
			throws ServiceException;

	public boolean isExistsUserWithLogin(String login) throws ServiceException;
}