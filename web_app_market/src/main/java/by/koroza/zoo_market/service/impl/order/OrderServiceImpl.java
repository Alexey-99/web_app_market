package by.koroza.zoo_market.service.impl.order;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	 * Change order.
	 *
	 * @param order the order
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean changeOrder(Order order) throws ServiceException {
		try {
			return OrderDaoImpl.getInstance().changeOrder(order);
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
	 * @param personalDiscountPуrcent     the personal discount pуrcent
	 * @return the double
	 */
	@Override
	public double calcTotalPersonDiscountAmount(double totalPaymentAmount, double totalProductsDiscountAmount,
			double personalDiscountPуrcent) {
		return (totalPaymentAmount - totalProductsDiscountAmount) * personalDiscountPуrcent / 100;
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
	 * Calculation total payment with discount amount.
	 *
	 * @param order                   the order
	 * @param haveProductPets         the have product pets
	 * @param haveProductFeedAndOther the have product feed and other
	 * @param personalDiscountpercent the personal discount percent
	 * @return the double
	 */
	@Override
	public double calcTotalPaymentWithDiscountAmount(Order order, Map<Integer, Boolean> haveProductPets,
			Map<Integer, Boolean> haveProductFeedAndOther, double personalDiscountPercent) {
		List<Pet> petsList = order.getProductsPets() != null
				? order.getProductsPets().getClass().equals(LinkedList.class) ? order.getProductsPets()
						: new LinkedList<Pet>(order.getProductsPets())
				: new LinkedList<>();
		List<FeedAndOther> productsFeedsAndOtherList = order.getOtherProducts() != null
				? order.getOtherProducts().getClass().equals(LinkedList.class) ? order.getOtherProducts()
						: new LinkedList<FeedAndOther>(order.getOtherProducts())
				: new LinkedList<>();
		double totalPaymentAmount = calcTotalPaymentAmount(petsList, haveProductPets, productsFeedsAndOtherList,
				haveProductFeedAndOther);
		double totalDiscountAmount = calcTotalDiscountAmount(petsList, haveProductPets, productsFeedsAndOtherList,
				haveProductFeedAndOther, personalDiscountPercent);
		return totalPaymentAmount - totalDiscountAmount;
	}

	/**
	 * Calculation total payment amount.
	 *
	 * @param pets                     the pets
	 * @param haveProductsPets         the have products pets
	 * @param otherProducts            the other products
	 * @param haveProductsFeedAndOther the have products feed and other
	 * @return the double
	 */
	private double calcTotalPaymentAmount(List<Pet> pets, Map<Integer, Boolean> haveProductsPets,
			List<FeedAndOther> otherProducts, Map<Integer, Boolean> haveProductsFeedAndOther) {
		return calcTotalPaymentAmountTypeProducts(pets, haveProductsPets)
				+ calcTotalPaymentAmountTypeProducts(otherProducts, haveProductsFeedAndOther);
	}

	/**
	 * Calculation total discount amount.
	 *
	 * @param petsList                  the pets list
	 * @param haveProductPets           the have product pets
	 * @param productsFeedsAndOtherList the products feeds and other list
	 * @param haveProductFeedAndOther   the have product feed and other
	 * @param personalDiscountpercent   the personal discountpercent
	 * @return the double
	 */
	private double calcTotalDiscountAmount(List<Pet> petsList, Map<Integer, Boolean> haveProductPets,
			List<FeedAndOther> productsFeedsAndOtherList, Map<Integer, Boolean> haveProductFeedAndOther,
			double personalDiscountpercent) {
		double totalProductsDiscountAmount = calcTotalProductsDiscountAmount(petsList, haveProductPets,
				productsFeedsAndOtherList, haveProductFeedAndOther);
		double totalPersonDiscountAmount = calcTotalPersonDiscountAmount(
				calcTotalPaymentAmount(petsList, haveProductPets, productsFeedsAndOtherList, haveProductFeedAndOther),
				totalProductsDiscountAmount, personalDiscountpercent);
		return totalProductsDiscountAmount + totalPersonDiscountAmount;
	}

	/**
	 * Calculation total products discount amount.
	 *
	 * @param pets                     the pets
	 * @param haveProductsPets         the have products pets
	 * @param otherProducts            the other products
	 * @param haveProductsFeedAndOther the have products feed and other
	 * @return the double
	 */
	private double calcTotalProductsDiscountAmount(List<Pet> pets, Map<Integer, Boolean> haveProductsPets,
			List<FeedAndOther> otherProducts, Map<Integer, Boolean> haveProductsFeedAndOther) {
		return calcTotalProductsDiscountAmountTypeProducts(pets, haveProductsPets)
				+ calcTotalProductsDiscountAmountTypeProducts(otherProducts, haveProductsFeedAndOther);
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
	 * Calculation total payment amount type products.
	 *
	 * @param products     the products
	 * @param haveProducts the have products
	 * @return the double
	 */
	private double calcTotalPaymentAmountTypeProducts(List<? extends AbstractProduct> products,
			Map<Integer, Boolean> haveProducts) {
		double totalPaymentAmount = 0;
		if (products != null) {
			for (int i = 0; i < products.size(); i++) {
				if (haveProducts != null && haveProducts.get(i)) {
					totalPaymentAmount += products.get(i).getPrice();
				}
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

	/**
	 * Calculation total products discount amount type products.
	 *
	 * @param products     the products
	 * @param haveProducts the have products
	 * @return the double
	 */
	private double calcTotalProductsDiscountAmountTypeProducts(List<? extends AbstractProduct> products,
			Map<Integer, Boolean> haveProducts) {
		double totalProductsDiscountAmount = 0;
		if (products != null) {
			for (int i = 0; i < products.size(); i++) {
				if (haveProducts != null && haveProducts.get(i)) {
					totalProductsDiscountAmount += products.get(i).getPrice() * products.get(i).getDiscount() / 100;
				}
			}
		}
		return totalProductsDiscountAmount;
	}
}