package by.koroza.zoo_market.service.impl.user;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.dao.impl.order.OrderDaoImpl;
import by.koroza.zoo_market.dao.impl.user.UserDaoImpl;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.UserService;
import by.koroza.zoo_market.service.exception.ServiceException;

/**
 * The Class UserServiceImpl.
 */
public class UserServiceImpl implements UserService {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final UserService INSTANCE = new UserServiceImpl();

	/**
	 * Instantiates a new user service impl.
	 */
	private UserServiceImpl() {
	}

	/**
	 * Get the single instance of UserServiceImpl.
	 *
	 * @return single instance of UserServiceImpl
	 */
	public static UserService getInstance() {
		return INSTANCE;
	}

	/**
	 * Add the user.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean addUser(User user) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().addUser(user);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Check repeat login.
	 *
	 * @param login the login
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean checkRepeatLogin(String login) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().checkRepeatLogin(login);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Get the user id by login.
	 *
	 * @param login the login
	 * @return the user id by login
	 * @throws ServiceException the service exception
	 */
	@Override
	public long getUserIdByLogin(String login) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().getUserIdByLogin(login);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Change role status.
	 *
	 * @param userId       the user id
	 * @param roleStatusId the role status id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean changeRoleStatus(long userId, int roleStatusId) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().changeRoleStatus(userId, roleStatusId);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Change role status.
	 *
	 * @param userLogin    the user login
	 * @param roleStatusId the role status id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean changeRoleStatus(String userLogin, int roleStatusId) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().changeRoleStatus(userLogin, roleStatusId);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Change confirmation email status.
	 *
	 * @param userId the user id
	 * @param status the status
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean changeConfirmationEmailStatus(long userId, boolean status) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().changeConfirmationEmailStatus(userId, status);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Get the user by login.
	 *
	 * @param login    the login
	 * @param password the password
	 * @return the user by login
	 * @throws ServiceException the service exception
	 */
	@Override
	public Optional<User> getUserByLogin(String login, String password) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().getUserByLogin(login, password);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Change login.
	 *
	 * @param userId the user id
	 * @param login  the login
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean changeLogin(long userId, String login) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().changeLogin(userId, login);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Change password.
	 *
	 * @param userId   the user id
	 * @param password the password
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean changePassword(long userId, String password) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().changePassword(userId, password);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Check if is exists user with login.
	 *
	 * @param login the login
	 * @return true, if is exists user with login
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean isExistsUserWithLogin(String login) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().isExistsUserWithLogin(login);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Check if is exists user with login and password by user id.
	 *
	 * @param login    the login
	 * @param password the password
	 * @param userId   the user id
	 * @return true, if is exists user with login and password by user id
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean isExistsUserWithLoginAndPasswordByUserId(String login, String password, long userId)
			throws ServiceException {
		try {
			return UserDaoImpl.getInstance().isExistsUserWithLoginAndPasswordByUserId(login, password, userId);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Change email.
	 *
	 * @param userId the user id
	 * @param email  the email
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean changeEmail(long userId, String email) throws ServiceException {
		try {
			return UserDaoImpl.getInstance().changeEmail(userId, email);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Change person percent discount.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean changePersonPercentDiscount(User user) throws ServiceException {
		try {
			if (user.getDiscount() + User.getPercentForEachQuantityOfProducts() <= User.getMaxProcentDiscount()) {
				List<Order> allOrders = OrderDaoImpl.getInstance().getOrderProductsByUserId(user.getId());
				long numberProducts = numberProductsOfAllOrders(allOrders);
				double discount = calcDiscount(numberProducts);
				if (user.getDiscount() != discount) {
					user.setDiscount(discount);
					UserDaoImpl.getInstance().changeDiscount(user.getId(), user.getDiscount());
				}
			}
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
		return false;
	}

	/**
	 * Get the all users.
	 *
	 * @return the all users
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<User> getAllUsers() throws ServiceException {
		try {
			return UserDaoImpl.getInstance().getAllUsers();
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Number products of all orders.
	 *
	 * @param allOrders the all orders
	 * @return the long
	 */
	private long numberProductsOfAllOrders(List<Order> allOrders) {
		long count = 0;
		for (Order order : allOrders) {
			count += order.getProductsPets().size();
			count += order.getOtherProducts().size();
		}
		return count;
	}

	/**
	 * Calculation discount.
	 *
	 * @param numberProducts the number products
	 * @return the double
	 */
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