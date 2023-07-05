package by.koroza.zoo_market.service.impl.user;

import java.util.List;
import java.util.Optional;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.dao.impl.order.OrderDaoImpl;
import by.koroza.zoo_market.dao.impl.user.UserDaoImpl;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.user.User;
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
	public boolean addUser(User user) throws ServiceException {
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
	public boolean changeVerificationEmailStatus(long userId, boolean status) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().changeVerificationEmailStatus(userId, status);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<User> getUserByLogin(String login, String password) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().getUserByLogin(login, password);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean changePersonInformation(User user) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().changePersonInformation(user);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean changeLogin(User user, String login) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().changeLogin(user, login);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean changePassword(User user, String password) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().changePassword(user, password);
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

	@Override
	public boolean changePersonProcentDiscount(User user) throws ServiceException {
		try {
			if (user.getDiscount() + User.getPercentForEachQuantityOfProducts() <= User.getMaxProcentDiscount()) {
				List<Order> allOrders = OrderDaoImpl.getInstance().getOrderProductsByUserId(user.getId());
				long numberProducts = numberProductsOfAllOrders(allOrders);
				double discount = calcDiscount(numberProducts);
				if (user.getDiscount() != discount) {
					user.setDiscount(discount);
					UserDaoImpl.getInstance().changeDiscount(user);
				}
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return false;
	}

	private long numberProductsOfAllOrders(List<Order> allOrders) {
		long count = 0;
		for (Order order : allOrders) {
			count += order.getProductsPets().size();
			count += order.getOtherProducts().size();
		}
		return count;
	}

	private double calcDiscount(long numberProducts) {
		double discount = 0;
		discount = numberProducts * User.getPercentForEachQuantityOfProducts()
				/ User.getQuantityForIncreasePersonalPersonalDiscount();
		if (discount % User.getPercentForEachQuantityOfProducts() != 0) {
			discount = Math.floor(discount);
			if (discount % User.getPercentForEachQuantityOfProducts() != 0) {
				do {
					discount--;
				} while (discount % User.getPercentForEachQuantityOfProducts() != 0);
			}
		}
		return discount;
	}
}