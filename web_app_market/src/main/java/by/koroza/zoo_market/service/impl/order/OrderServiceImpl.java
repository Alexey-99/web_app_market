package by.koroza.zoo_market.service.impl.order;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.dao.impl.order.OrderDaoImpl;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.OrderService;
import by.koroza.zoo_market.service.exception.ServiceException;

/**
 * The Class OrderServiceImpl.
 */
public class OrderServiceImpl implements OrderService {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final OrderService INSTANCE = new OrderServiceImpl();

	/**
	 * Instantiates a new order service impl.
	 */
	private OrderServiceImpl() {
	}

	/**
	 * Get the single instance of OrderServiceImpl.
	 *
	 * @return single instance of OrderServiceImpl
	 */
	public static OrderService getInstance() {
		return INSTANCE;
	}

	/**
	 * Get the order products by user id.
	 *
	 * @param userId the user id
	 * @return the order products by user id
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<Order> getOrderProductsByUserId(long userId) throws ServiceException {
		try {
			return OrderDaoImpl.getInstance().getOrderProductsByUserId(userId);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Add the order.
	 *
	 * @param order  the order
	 * @param userId the user id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean addOrder(Order order, long userId) throws ServiceException {
		try {
			return OrderDaoImpl.getInstance().addOrder(order, userId);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Calculation total payment amount.
	 *
	 * @param pets          the pets
	 * @param otherProducts the other products
	 * @return the double
	 */
	@Override
	public double calcTotalPaymentAmount(List<Pet> pets, List<FeedAndOther> otherProducts) {
		return calcTotalPaymentAmountTypeProducts(pets) + calcTotalPaymentAmountTypeProducts(otherProducts);
	}

	/**
	 * Calculation total products discount amount.
	 *
	 * @param pets          the pets
	 * @param otherProducts the other products
	 * @return the double
	 */
	@Override
	public double calcTotalProductsDiscountAmount(List<Pet> pets, List<FeedAndOther> otherProducts) {
		return calcTotalProductsDiscountAmountTypeProducts(pets)
				+ calcTotalProductsDiscountAmountTypeProducts(otherProducts);
	}

	/**
	 * Calculation total person discount amount.
	 *
	 * @param totalPaymentAmount          the total payment amount
	 * @param totalProductsDiscountAmount the total products discount amount
	 * @param personalDiscountProcent     the personal discount procent
	 * @return the double
	 */
	@Override
	public double calcTotalPersonDiscountAmount(double totalPaymentAmount, double totalProductsDiscountAmount,
			double personalDiscountProcent) {
		return (totalPaymentAmount - totalProductsDiscountAmount) * personalDiscountProcent / 100;
	}

	/**
	 * Calculation total discount amount.
	 *
	 * @param totalProductsDiscountAmount the total products discount amount
	 * @param totalPersonDiscountAmount   the total person discount amount
	 * @return the double
	 */
	@Override
	public double calcTotalDiscountAmount(double totalProductsDiscountAmount, double totalPersonDiscountAmount) {
		return totalProductsDiscountAmount + totalPersonDiscountAmount;
	}

	/**
	 * Calculation total payment with discount amount.
	 *
	 * @param totalPaymentAmount  the total payment amount
	 * @param totalDiscountAmount the total discount amount
	 * @return the double
	 */
	@Override
	public double calcTotalPaymentWithDiscountAmount(double totalPaymentAmount, double totalDiscountAmount) {
		return totalPaymentAmount - totalDiscountAmount;
	}

	/**
	 * Calculation total payment amount type products.
	 *
	 * @param products the products
	 * @return the double
	 */
	private double calcTotalPaymentAmountTypeProducts(List<? extends AbstractProduct> products) {
		double totalPaymentAmount = 0;
		if (products != null) {
			for (AbstractProduct product : products) {
				totalPaymentAmount += product.getPrice();
			}
		}
		return totalPaymentAmount;
	}

	/**
	 * Calculation total products discount amount type products.
	 *
	 * @param products the products
	 * @return the double
	 */
	private double calcTotalProductsDiscountAmountTypeProducts(List<? extends AbstractProduct> products) {
		double totalProductsDiscountAmount = 0;
		if (products != null) {
			for (AbstractProduct product : products) {
				totalProductsDiscountAmount += product.getPrice() * product.getDiscount() / 100;
			}
		}
		return totalProductsDiscountAmount;
	}
}