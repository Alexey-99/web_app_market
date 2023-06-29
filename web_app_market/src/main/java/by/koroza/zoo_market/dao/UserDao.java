package by.koroza.zoo_market.dao;

import java.util.Optional;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.model.entity.user.reserved.User;

public interface UserDao {

	public boolean checkRepeatLogin(String login) throws DaoException;

	public boolean addUser(AbstractRegistratedUser user) throws DaoException;

	public long getUserIdByLogin(String login) throws DaoException;

	public boolean changeRoleStatus(long userId, int roleStatusId) throws DaoException;

	public boolean changeRoleStatus(String login, int roleStatusId) throws DaoException;

	public boolean changeVerificationEmailStatus(long userId, boolean verificateStatus) throws DaoException;

	public Optional<AbstractRegistratedUser> getUserByLogin(String login, String password) throws DaoException;

	public boolean changePersonInformation(AbstractRegistratedUser user, String name, String surname, String email)
			throws DaoException;

	public boolean changeLogin(AbstractRegistratedUser user, String login) throws DaoException;

	public boolean changePassword(AbstractRegistratedUser user, String password) throws DaoException;

	public boolean isExistsUserWithLogin(String login) throws DaoException;

	public boolean changeEmail(User user) throws DaoException;
}