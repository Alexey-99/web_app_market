package by.koroza.zoo_market.model.entity.market.order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.status.OrderStatus;

/**
 * The Class Order.
 */
public class Order {

	/** The id. */
	private long id;

	/** The user id. */
	private long userId;

	/** The products pets. */
	private List<Entry<Pet, Long>> productsPets;

	/** The other products. */
	private List<Entry<FeedAndOther, Long>> otherProducts;

	/** The total payment amount. */
	private double totalPaymentAmount;

	/** The total products discount amount. */
	private double totalProductsDiscountAmount;

	/** The total person discount amount. */
	private double totalPersonDiscountAmount;

	/** The total discount amount. */
	private double totalDiscountAmount;

	/** The total payment with discount amount. */
	private double totalPaymentWithDiscountAmount;

	/** The date creation. */
	private LocalDate dateCreation;

	/** The status. */
	private OrderStatus status;

	/**
	 * Instantiates a new order.
	 */
	public Order() {
		this.id = 0;
		this.userId = 0;
		this.productsPets = new ArrayList<>();
		this.otherProducts = new ArrayList<>();
		this.totalPaymentAmount = 0;
		this.totalProductsDiscountAmount = 0;
		this.totalPersonDiscountAmount = 0;
		this.totalDiscountAmount = 0;
		this.totalPaymentWithDiscountAmount = 0;
		this.dateCreation = null;
		this.status = OrderStatus.OPEN;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public long getUserId() {
		return this.userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the products pets.
	 *
	 * @return the products pets
	 */
	public List<Entry<Pet, Long>> getProductsPets() {
		return this.productsPets;
	}

	/**
	 * Sets the products pets.
	 *
	 * @param productsPets the new products pets
	 */
	public void setProductsPets(List<Entry<Pet, Long>> productsPets) {
		this.productsPets = productsPets;
	}

	/**
	 * Gets the other products.
	 *
	 * @return the other products
	 */
	public List<Entry<FeedAndOther, Long>> getOtherProducts() {
		return this.otherProducts;
	}

	/**
	 * Sets the other products.
	 *
	 * @param otherProducts the new other products
	 */
	public void setOtherProducts(List<Entry<FeedAndOther, Long>> otherProducts) {
		this.otherProducts = otherProducts;
	}

	/**
	 * Gets the total payment amount.
	 *
	 * @return the total payment amount
	 */
	public double getTotalPaymentAmount() {
		return this.totalPaymentAmount;
	}

	/**
	 * Sets the total payment amount.
	 *
	 * @param totalPaymentAmount the new total payment amount
	 */
	public void setTotalPaymentAmount(double totalPaymentAmount) {
		this.totalPaymentAmount = totalPaymentAmount;
	}

	/**
	 * Gets the total discount amount.
	 *
	 * @return the total discount amount
	 */
	public double getTotalDiscountAmount() {
		return this.totalDiscountAmount;
	}

	/**
	 * Sets the total discount amount.
	 *
	 * @param totalDiscountAmount the new total discount amount
	 */
	public void setTotalDiscountAmount(double totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}

	/**
	 * Gets the total payment with discount amount.
	 *
	 * @return the total payment with discount amount
	 */
	public double getTotalPaymentWithDiscountAmount() {
		return this.totalPaymentWithDiscountAmount;
	}

	/**
	 * Sets the total payment with discount amount.
	 *
	 * @param totalPaymentWithDiscountAmount the new total payment with discount
	 *                                       amount
	 */
	public void setTotalPaymentWithDiscountAmount(double totalPaymentWithDiscountAmount) {
		this.totalPaymentWithDiscountAmount = totalPaymentWithDiscountAmount;
	}

	/**
	 * Gets the total products discount amount.
	 *
	 * @return the total products discount amount
	 */
	public double getTotalProductsDiscountAmount() {
		return this.totalProductsDiscountAmount;
	}

	/**
	 * Sets the total products discount amount.
	 *
	 * @param totalProductsDiscountAmount the new total products discount amount
	 */
	public void setTotalProductsDiscountAmount(double totalProductsDiscountAmount) {
		this.totalProductsDiscountAmount = totalProductsDiscountAmount;
	}

	/**
	 * Gets the total person discount amount.
	 *
	 * @return the total person discount amount
	 */
	public double getTotalPersonDiscountAmount() {
		return this.totalPersonDiscountAmount;
	}

	/**
	 * Sets the total person discount amount.
	 *
	 * @param totalPersonDiscountAmount the new total person discount amount
	 */
	public void setTotalPersonDiscountAmount(double totalPersonDiscountAmount) {
		this.totalPersonDiscountAmount = totalPersonDiscountAmount;
	}

	/**
	 * Gets the date creation.
	 *
	 * @return the date creation
	 */
	public LocalDate getDateCreation() {
		return this.dateCreation;
	}

	/**
	 * Sets the date creation.
	 *
	 * @param dateCreation the new date creation
	 */
	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public OrderStatus getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	/**
	 * Sets the status.
	 *
	 * @param id the new status
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void setStatus(int id) throws IllegalArgumentException {
		this.status = OrderStatus.findStatusByStatusId(id);
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + Long.hashCode(this.id);
		result = result * PRIME + Long.hashCode(this.userId);
		result = result * PRIME + (this.productsPets != null ? this.productsPets.hashCode() : 1);
		result = result * PRIME + (this.otherProducts != null ? this.otherProducts.hashCode() : 1);
		result = result * PRIME + Double.hashCode(this.totalPaymentAmount);
		result = result * PRIME + Double.hashCode(this.totalProductsDiscountAmount);
		result = result * PRIME + Double.hashCode(this.totalPersonDiscountAmount);
		result = result * PRIME + Double.hashCode(this.totalDiscountAmount);
		result = result * PRIME + Double.hashCode(this.totalPaymentWithDiscountAmount);
		result = result * PRIME + (this.dateCreation != null ? this.dateCreation.hashCode() : 1);
		result = result * PRIME + (this.status != null ? this.status.hashCode() : 1);
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param object the object
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (!this.getClass().equals(object.getClass())) {
			return false;
		}
		Order otherOrder = (Order) object;
		if (this.id != otherOrder.id) {
			return false;
		}
		if (this.userId != otherOrder.userId) {
			return false;
		}
		if (this.productsPets == null) {
			if (otherOrder.productsPets != null) {
				return false;
			}
		} else if (!this.productsPets.equals(otherOrder.productsPets)) {
			return false;
		}
		if (this.otherProducts == null) {
			if (otherOrder.otherProducts != null) {
				return false;
			}
		} else if (!this.otherProducts.equals(otherOrder.otherProducts)) {
			return false;
		}
		if (this.totalPaymentAmount != otherOrder.totalPaymentAmount) {
			return false;
		}
		if (this.totalProductsDiscountAmount != otherOrder.totalProductsDiscountAmount) {
			return false;
		}
		if (this.totalPersonDiscountAmount != otherOrder.totalPersonDiscountAmount) {
			return false;
		}
		if (this.totalDiscountAmount != otherOrder.totalDiscountAmount) {
			return false;
		}
		if (this.totalPaymentWithDiscountAmount != otherOrder.totalPaymentWithDiscountAmount) {
			return false;
		}
		if (this.dateCreation == null) {
			if (otherOrder.dateCreation != null) {
				return false;
			}
		} else if (!this.dateCreation.equals(otherOrder.dateCreation)) {
			return false;
		}
		if (this.status == null) {
			if (otherOrder.status != null) {
				return false;
			}
		} else if (!this.status.equals(otherOrder.status)) {
			return false;
		}
		return true;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order id: ").append(this.id).append("\n");
		builder.append("User id: ").append(this.userId).append("\n");
		builder.append("Products: ").append(this.productsPets.toString()).append(" and ")
				.append(this.otherProducts.toString()).append("\n");
		builder.append("Total payment amount: ").append(this.totalPaymentAmount);
		builder.append(this.totalDiscountAmount > 0
				? builder.append("Total discount amount: ").append(this.totalDiscountAmount)
				: "");
		return builder.toString();
	}

	/**
	 * The Class OrderBuilder.
	 */
	public static class OrderBuilder {

		/** The order. */
		private Order order;

		/**
		 * Instantiates a new order builder.
		 */
		public OrderBuilder() {
			this.order = new Order();
		}

		/**
		 * Sets the id.
		 *
		 * @param id the id
		 * @return the order builder
		 */
		public OrderBuilder setId(long id) {
			this.order.setId(id);
			return this;
		}

		/**
		 * Sets the user id.
		 *
		 * @param userId the user id
		 * @return the order builder
		 */
		public OrderBuilder setUserId(long userId) {
			this.order.setUserId(userId);
			return this;
		}

		/**
		 * Sets the products pets.
		 *
		 * @param pets the pets
		 * @return the order builder
		 */
		public OrderBuilder setProductsPets(List<Entry<Pet, Long>> pets) {
			this.order.setProductsPets(pets);
			return this;
		}

		/**
		 * Sets the other products.
		 *
		 * @param otherProducts the other products
		 * @return the order builder
		 */
		public OrderBuilder setOtherProducts(List<Entry<FeedAndOther, Long>> otherProducts) {
			this.order.setOtherProducts(otherProducts);
			return this;
		}

		/**
		 * Sets the total payment amount.
		 *
		 * @param totalPaymentAmount the total payment amount
		 * @return the order builder
		 */
		public OrderBuilder setTotalPaymentAmount(double totalPaymentAmount) {
			this.order.setTotalPaymentAmount(totalPaymentAmount);
			return this;
		}

		/**
		 * Sets the total discount amount.
		 *
		 * @param totalDiscountAmount the total discount amount
		 * @return the order builder
		 */
		public OrderBuilder setTotalDiscountAmount(double totalDiscountAmount) {
			this.order.setTotalDiscountAmount(totalDiscountAmount);
			return this;
		}

		/**
		 * Sets the date creation.
		 *
		 * @param dateCreation the date creation
		 * @return the order builder
		 */
		public OrderBuilder setDateCreation(LocalDate dateCreation) {
			this.order.setDateCreation(dateCreation);
			return this;
		}

		/**
		 * Sets the total payment with discount amount.
		 *
		 * @param totalPaymentWithDiscountAmount the total payment with discount amount
		 * @return the order builder
		 */
		public OrderBuilder setTotalPaymentWithDiscountAmount(double totalPaymentWithDiscountAmount) {
			this.order.setTotalPaymentWithDiscountAmount(totalPaymentWithDiscountAmount);
			return this;
		}

		/**
		 * Sets the total products discount amount.
		 *
		 * @param totalProductsDiscountAmount the total products discount amount
		 * @return the order builder
		 */
		public OrderBuilder setTotalProductsDiscountAmount(double totalProductsDiscountAmount) {
			this.order.setTotalProductsDiscountAmount(totalProductsDiscountAmount);
			return this;
		}

		/**
		 * Sets the total person discount amount.
		 *
		 * @param totalPersonDiscountAmount the total person discount amount
		 * @return the order builder
		 */
		public OrderBuilder setTotalPersonDiscountAmount(double totalPersonDiscountAmount) {
			this.order.setTotalPersonDiscountAmount(totalPersonDiscountAmount);
			return this;
		}

		/**
		 * Sets the status.
		 *
		 * @param status the status
		 * @return the order builder
		 */
		public OrderBuilder setStatus(OrderStatus status) {
			this.order.setStatus(status);
			return this;
		}

		/**
		 * Sets the status.
		 *
		 * @param id the id
		 * @return the order builder
		 * @throws IllegalArgumentException the illegal argument exception
		 */
		public OrderBuilder setStatus(int id) throws IllegalArgumentException {
			this.order.setStatus(id);
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the order
		 */
		public Order build() {
			return this.order;
		}
	}
}