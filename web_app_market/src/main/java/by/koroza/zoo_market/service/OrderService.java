package by.koroza.zoo_market.service;

import java.util.List;

import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.service.exception.ServiceException;

public interface OrderService {

	public List<Order> getOrderProductsByUserId(long userId) throws ServiceException;

	public boolean addOrder(Order order, long userId) throws ServiceException;

	public double calcTotalPaymentAmount(List<Pet> pets, List<FeedAndOther> otherProducts);

	public double calcTotalProductsDiscountAmount(List<Pet> pets, List<FeedAndOther> otherProducts);

	public double calcTotalPersonDiscountAmount(double totalPaymentAmount, double totalProductsDiscountAmount,
			double personalDiscountProcent);

	public double calcTotalDiscountAmount(double totalProductsDiscountAmount, double totalPersonDiscountAmount);

	public double calcTotalPaymentWithDiscountAmount(double totalPaymentAmount, double totalDiscountAmount);
}