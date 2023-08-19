package by.koroza.zoo_market.service.impl.order;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.OrderDao;
import by.koroza.zoo_market.dao.exception.checkable.DaoException;
import by.koroza.zoo_market.dao.impl.order.OrderDaoImpl;
import by.koroza.zoo_market.dao.impl.product.ProductFeedsAndOtherDaoImpl;
import by.koroza.zoo_market.dao.impl.product.ProductPetDaoImpl;
import by.koroza.zoo_market.model.entity.detalization.OrderDetalizationByProduct;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.model.entity.status.OrderStatus;
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
	 * Get the open order by user id.
	 *
	 * @param userId the user id
	 * @return the open order by user id
	 * @throws ServiceException the service exception
	 */
	@Override
	public Order getOpenOrderByUserId(long userId) throws ServiceException {
		try {
			Order order = null;
			OrderDao orderDao = OrderDaoImpl.getInstance();
			if (orderDao.isHaveOpenOrderByUserId(userId)) {
				order = orderDao.getOrderWithProductsByUserIdAndOrderStatus(userId, OrderStatus.OPEN);
			} else {
				order = new Order();
				orderDao.addOrder(order, userId);
			}
			return order;
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
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
	public double calcTotalPaymentWithDiscountAmount(Order order, double personalDiscountPercent) {
		List<Entry<Pet, Long>> petsList = order.getProductsPets() != null
				? order.getProductsPets().getClass().equals(LinkedList.class) ? order.getProductsPets()
						: new LinkedList<>(order.getProductsPets())
				: new LinkedList<>();
		List<Entry<FeedAndOther, Long>> productsFeedsAndOtherList = order.getOtherProducts() != null
				? order.getOtherProducts().getClass().equals(LinkedList.class) ? order.getOtherProducts()
						: new LinkedList<>(order.getOtherProducts())
				: new LinkedList<>();
		double totalPaymentAmount = calcTotalPaymentAmount(petsList, productsFeedsAndOtherList);
		double totalDiscountAmount = calcTotalDiscountAmount(petsList, productsFeedsAndOtherList,
				personalDiscountPercent);
		return totalPaymentAmount - totalDiscountAmount;
	}

	/**
	 * Get the details about orders by product pet id and order status.
	 *
	 * @param orderStatusId the order status id
	 * @param productId     the product id
	 * @return the details about orders by product pet id and order status
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<OrderDetalizationByProduct> getDetailsAboutOrdersByProductPetIdAndOrderStatus(int orderStatusId,
			long productId) throws ServiceException {
		try {
			return OrderDaoImpl.getInstance().getDetailsAboutOrdersByProductPetIdAndOrderStatus(orderStatusId,
					productId);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Get the details about orders by product feed and other id and order status.
	 *
	 * @param orderStatusId the order status id
	 * @param productId     the product id
	 * @return the details about orders by product feed and other id and order
	 *         status
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<OrderDetalizationByProduct> getDetailsAboutOrdersByProductFeedAndOtherIdAndOrderStatus(
			int orderStatusId, long productId) throws ServiceException {
		try {
			return OrderDaoImpl.getInstance().getDetailsAboutOrdersByProductFeedAndOtherIdAndOrderStatus(orderStatusId,
					productId);
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Change quantity product feed and other in order by order id.
	 *
	 * @param orderId       the order id
	 * @param orderStatusId the order status id
	 * @param productId     the product id
	 * @param quantity      the quantity
	 * @param userDiscount  the user discount
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean changeQuantityProductFeedAndOtherInOrderByOrderId(long orderId, int orderStatusId, long productId,
			long quantity, double userDiscount) throws ServiceException {
		boolean result = false;
		try {
			Order order = OrderServiceImpl.getInstance().getOrderWithoutProductsByOrderId(orderId);
			FeedAndOther product = ProductFeedsAndOtherDaoImpl.getInstance().getProductById(productId);
			if (order != null && product != null) {
				long quantityNow = OrderDaoImpl.getInstance().getQuantityProductFeedAndOtherInOrderByIdAndOrderStatus(
						order.getId(), order.getStatus().getId(), product.getId());
				if (quantityNow < quantity) {
					for (int i = 0; i < quantity - quantityNow; i++) {
						if (ProductFeedsAndOtherDaoImpl.getInstance().addProductToOrder(order.getId(),
								product.getId())) {
							addProductToOrder(order, product, userDiscount);
						}
					}
				} else if (quantityNow > quantity) {
					for (int i = 0; i < quantityNow - quantity; i++) {
						if (ProductFeedsAndOtherDaoImpl.getInstance().deleteProductFromOrder(orderId, productId)) {
							deleteProductFromOrder(order, product, userDiscount);
						}
					}
				}
				changeOrder(order);
				result = OrderDaoImpl.getInstance().getQuantityProductFeedAndOtherInOrderByIdAndOrderStatus(
						order.getId(), order.getStatus().getId(), product.getId()) == quantity;
			} else {
				result = false;
			}
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
		return result;
	}

	/**
	 * Change quantity product pet in order by order id.
	 *
	 * @param orderId       the order id
	 * @param orderStatusId the order status id
	 * @param productId     the product id
	 * @param quantity      the quantity
	 * @param userDiscount  the user discount
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean changeQuantityProductPetInOrderByOrderId(long orderId, int orderStatusId, long productId,
			long quantity, double userDiscount) throws ServiceException {
		boolean result = false;
		try {
			Order order = getOrderWithoutProductsByOrderId(orderId);
			Pet product = ProductPetDaoImpl.getInstance().getProductById(productId);
			if (order != null && product != null) {
				long quantityNow = OrderDaoImpl.getInstance().getQuantityProductPetInOrderByIdAndOrderStatus(
						order.getId(), order.getStatus().getId(), product.getId());
				if (quantityNow < quantity) {
					for (int i = 0; i < quantity - quantityNow; i++) {
						if (ProductPetDaoImpl.getInstance().addProductToOrder(order.getId(), product.getId())) {
							addProductToOrder(order, product, userDiscount);
						}
					}
				} else if (quantityNow > quantity) {
					for (int i = 0; i < quantityNow - quantity; i++) {
						if (ProductPetDaoImpl.getInstance().deleteProductFromOrder(order.getId(), product.getId())) {
							deleteProductFromOrder(order, product, userDiscount);
						}
					}
				}
				changeOrder(order);
				result = OrderDaoImpl.getInstance().getQuantityProductFeedAndOtherInOrderByIdAndOrderStatus(
						order.getId(), order.getStatus().getId(), product.getId()) == quantity;
			} else {
				result = false;
			}
		} catch (DaoException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}
		return result;
	}

	/**
	 * Delete product from order.
	 *
	 * @param order        the order
	 * @param product      the product
	 * @param userDiscount the user discount
	 */
	private void deleteProductFromOrder(Order order, AbstractProduct product, double userDiscount) {
		order.setTotalPaymentAmount(order.getTotalPaymentAmount() - product.getPrice());
		order.setTotalProductsDiscountAmount(
				order.getTotalProductsDiscountAmount() - (product.getPrice() * product.getDiscount() / 100));
		order.setTotalPersonDiscountAmount(
				order.getTotalPersonDiscountAmount() - (product.getPrice() * userDiscount / 100));
		order.setTotalDiscountAmount(order.getTotalProductsDiscountAmount() + order.getTotalPersonDiscountAmount());
		order.setTotalPaymentWithDiscountAmount(order.getTotalPaymentAmount() - order.getTotalDiscountAmount());
	}

	/**
	 * Adds the product to order.
	 *
	 * @param order        the order
	 * @param product      the product
	 * @param userDiscount the user discount
	 */
	private void addProductToOrder(Order order, AbstractProduct product, double userDiscount) {
		order.setTotalPaymentAmount(order.getTotalPaymentAmount() + product.getPrice());
		order.setTotalProductsDiscountAmount(
				order.getTotalProductsDiscountAmount() + (product.getPrice() * product.getDiscount() / 100));
		order.setTotalPersonDiscountAmount(
				order.getTotalPersonDiscountAmount() + (product.getPrice() * userDiscount / 100));
		order.setTotalDiscountAmount(order.getTotalProductsDiscountAmount() + order.getTotalPersonDiscountAmount());
		order.setTotalPaymentWithDiscountAmount(order.getTotalPaymentAmount() - order.getTotalDiscountAmount());
	}

	/**
	 * Get the order without products by order id.
	 *
	 * @param orderId the order id
	 * @return the order without products by order id
	 * @throws ServiceException the service exception
	 */
	@Override
	public Order getOrderWithoutProductsByOrderId(long orderId) throws ServiceException {
		try {
			return OrderDaoImpl.getInstance().getOrderWithoutProductsByOrderId(orderId);
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
	private double calcTotalPaymentAmount(List<Entry<Pet, Long>> pets, List<Entry<FeedAndOther, Long>> otherProducts) {
		return calcTotalPaymentAmountByProductPet(pets) + calcTotalPaymentAmountByProductFeedAndOther(otherProducts);
	}

	/**
	 * Calculation total person discount amount.
	 *
	 * @param totalPaymentAmount          the total payment amount
	 * @param totalProductsDiscountAmount the total products discount amount
	 * @param personalDiscountPуrcent     the personal discount pуrcent
	 * @return the double
	 */
	private double calcTotalPersonDiscountAmount(double totalPaymentAmount, double totalProductsDiscountAmount,
			double personalDiscountPуrcent) {
		return (totalPaymentAmount - totalProductsDiscountAmount) * personalDiscountPуrcent / 100;
	}

	/**
	 * Calculation total discount amount.
	 *
	 * @param petsList                  the pets list
	 * @param productsFeedsAndOtherList the products feeds and other list
	 * @param personalDiscountpercent   the personal discount percent
	 * @return the double
	 */
	private double calcTotalDiscountAmount(List<Entry<Pet, Long>> petsList,
			List<Entry<FeedAndOther, Long>> productsFeedsAndOtherList, double personalDiscountPercent) {
		double totalProductsDiscountAmount = calcTotalProductsDiscountAmount1(petsList, productsFeedsAndOtherList);
		double totalPersonDiscountAmount = calcTotalPersonDiscountAmount(
				calcTotalPaymentAmount(petsList, productsFeedsAndOtherList), totalProductsDiscountAmount,
				personalDiscountPercent);
		return totalProductsDiscountAmount + totalPersonDiscountAmount;
	}

	/**
	 * Calculation total products discount amount 1.
	 *
	 * @param pets          the pets
	 * @param otherProducts the other products
	 * @return the double
	 */
	private double calcTotalProductsDiscountAmount1(List<Entry<Pet, Long>> pets,
			List<Entry<FeedAndOther, Long>> otherProducts) {
		return calcTotalProductsDiscountAmountByProductPet(pets)
				+ calcTotalProductsDiscountAmountByProductFeedAndOther(otherProducts);
	}

	/**
	 * Calculation total payment amount type products.
	 *
	 * @param products the products
	 * @return the double
	 */
	private double calcTotalPaymentAmountByProductPet(List<Entry<Pet, Long>> products) {
		double totalPaymentAmount = 0;
		if (products != null) {
			for (Entry<Pet, Long> entry : products) {
				totalPaymentAmount += entry.getKey().getPrice() * entry.getValue();
			}
		}
		return totalPaymentAmount;
	}

	/**
	 * Calculation total payment amount by product feed and other.
	 *
	 * @param products the products
	 * @return the double
	 */
	private double calcTotalPaymentAmountByProductFeedAndOther(List<Entry<FeedAndOther, Long>> products) {
		double totalPaymentAmount = 0;
		if (products != null) {
			for (Entry<FeedAndOther, Long> entry : products) {
				totalPaymentAmount += entry.getKey().getPrice() * entry.getValue();
			}
		}
		return totalPaymentAmount;
	}

	/**
	 * Calculation total products discount amount by product pet.
	 *
	 * @param products the products
	 * @return the double
	 */
	private double calcTotalProductsDiscountAmountByProductPet(List<Entry<Pet, Long>> products) {
		double totalProductsDiscountAmount = 0;
		if (products != null) {
			for (Entry<Pet, Long> entry : products) {
				totalProductsDiscountAmount += (entry.getKey().getPrice() * entry.getKey().getDiscount() / 100)
						* entry.getValue();
			}
		}
		return totalProductsDiscountAmount;
	}

	/**
	 * Calculation total products discount amount by product feed and other.
	 *
	 * @param products the products
	 * @return the double
	 */
	private double calcTotalProductsDiscountAmountByProductFeedAndOther(List<Entry<FeedAndOther, Long>> products) {
		double totalProductsDiscountAmount = 0;
		if (products != null) {
			for (Entry<FeedAndOther, Long> entry : products) {
				totalProductsDiscountAmount += (entry.getKey().getPrice() * entry.getKey().getDiscount() / 100)
						* entry.getValue();
			}
		}
		return totalProductsDiscountAmount;
	}
}