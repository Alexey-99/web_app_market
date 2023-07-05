package by.koroza.zoo_market.dao;

import java.util.Optional;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.model.entity.user.User;

public interface UserDao {

	public boolean checkRepeatLogin(String login) throws DaoException;

	public boolean addUser(User user) throws DaoException;

	public long getUserIdByLogin(String login) throws DaoException;

	public boolean changeRoleStatus(long userId, int roleStatusId) throws DaoException;

	public boolean changeRoleStatus(String login, int roleStatusId) throws DaoException;

	public boolean changeVerificationEmailStatus(long userId, boolean status) throws DaoException;

	public Optional<User> getUserByLogin(String login, String password) throws DaoException;

	public boolean changePersonInformation(User user) throws DaoException;

	public boolean changeLogin(User user, String login) throws DaoException;

	public boolean changePassword(User user, String password) throws DaoException;

	public boolean isExistsUserWithLogin(String login) throws DaoException;

	public boolean changeEmail(User user) throws DaoException;

	public boolean changeDiscount(User user) throws DaoException;
}