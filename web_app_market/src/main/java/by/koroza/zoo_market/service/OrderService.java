package by.koroza.zoo_market.service;

import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.service.exception.ServiceException;

/**
 * The Interface OrderService.
 */
public interface OrderService {

	/**
	 * Get the order with products by user id.
	 *
	 * @param userId the user id
	 * @return the order products by user id
	 * @throws ServiceException the service exception
	 */
	public List<Order> getOrderProductsByUserId(long userId) throws ServiceException;

	/**
	 * Add the order with products.
	 *
	 * @param order  the order
	 * @param userId the user id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean addOrder(Order order, long userId) throws ServiceException;

	/**
	 * Calculation total payment amount.
	 *
	 * @param pets          the pets
	 * @param otherProducts the other products
	 * @return the double
	 */
	public double calcTotalPaymentAmount(List<Pet> pets, List<FeedAndOther> otherProducts);

	/**
	 * Calculation total products discount amount.
	 *
	 * @param pets          the pets
	 * @param otherProducts the other products
	 * @return the double
	 */
	public double calcTotalProductsDiscountAmount(List<Pet> pets, List<FeedAndOther> otherProducts);

	/**
	 * Calculation total personal discount amount.
	 *
	 * @param totalPaymentAmount          the total payment amount
	 * @param totalProductsDiscountAmount the total products discount amount
	 * @param personalDiscountProcent     the personal discount procent
	 * @return the double
	 */
	public double calcTotalPersonDiscountAmount(double totalPaymentAmount, double totalProductsDiscountAmount,
			double personalDiscountProcent);

	/**
	 * Calculation total discount amount.
	 *
	 * @param totalProductsDiscountAmount the total products discount amount
	 * @param totalPersonDiscountAmount   the total person discount amount
	 * @return the double
	 */
	public double calcTotalDiscountAmount(double totalProductsDiscountAmount, double totalPersonDiscountAmount);

	/**
	 * Calculation total payment with discount amount.
	 *
	 * @param totalPaymentAmount  the total payment amount
	 * @param totalDiscountAmount the total discount amount
	 * @return the double
	 */
	public double calcTotalPaymentWithDiscountAmount(double totalPaymentAmount, double totalDiscountAmount);

	/**
	 * Calculation total payment with discount amount.
	 *
	 * @param order                   the order
	 * @param haveProductPets         the have product pets
	 * @param haveProductFeedAndOther the have product feed and other
	 * @param personalDiscountpercent the personal discount percent
	 * @return the double
	 */
	double calcTotalPaymentWithDiscountAmount(Order order, Map<Integer, Boolean> haveProductPets,
			Map<Integer, Boolean> haveProductFeedAndOther, double personalDiscountPercent);
}