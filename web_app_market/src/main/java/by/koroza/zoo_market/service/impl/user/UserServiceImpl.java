package by.koroza.zoo_market.service.impl.user;

import java.util.Optional;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.dao.impl.user.UserDaoImpl;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.model.entity.user.reserved.User;
import by.koroza.zoo_market.service.UserService;
import by.koroza.zoo_market.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
	private static final UserService INSTANCE = new UserServiceImpl();

	private UserServiceImpl() {
	}

	public static UserService getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean addRegistratedUserToBD(AbstractRegistratedUser user) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().addUser(user);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean checkRepeatLogin(String login) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().checkRepeatLogin(login);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public long getUserIdByLogin(String login) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().getUserIdByLogin(login);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean changeRoleStatus(long userId, int roleStatusId) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().changeRoleStatus(userId, roleStatusId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean changeRoleStatus(String userLogin, int roleStatusId) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().changeRoleStatus(userLogin, roleStatusId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean changeVerificationEmailStatus(long userId, boolean verificateStatus) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().changeVerificationEmailStatus(userId, verificateStatus);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<AbstractRegistratedUser> getUserByLogin(String login, String password) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().getUserByLogin(login, password);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean changePersonInformation(AbstractRegistratedUser user, String name, String surname, String email)
			throws ServiceException {
		try {
			return UserDaoImpl.getInstance().changePersonInformation(user, name, surname, email);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean changeLoginAndPassword(AbstractRegistratedUser user, String login, String password)
			throws ServiceException {
		try {
			return UserDaoImpl.getInstance().changeLoginAndPassword(user, login, password);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean isExistsUserWithLogin(String login) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().isExistsUserWithLogin(login);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean changeEmail(User user) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().changeEmail(user);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}