package by.koroza.zoo_market.model.entity.market.order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;

public class Order {

	public enum OrderStatus {
		OPEN(1), WAITING_PAY(2), CLOSED(3);

		private int statusId;

		private OrderStatus(int statusId) {
			this.statusId = statusId;
		}

		public int getStatusId() {
			return statusId;
		}

		public static OrderStatus findStatusByStatusId(int id) {
			OrderStatus status = null;
			switch (id) {
			case 1 -> status = OPEN;
			case 2 -> status = WAITING_PAY;
			case 3 -> status = CLOSED;
			default -> throw new IllegalArgumentException("Unexpected value: " + id);
			}
			return status;
		}
	}

	private long id;
	private long userId;
	private List<Pet> productsPets;
	private List<FeedAndOther> otherProducts;
	private double totalPaymentAmount;
	private double totalProductsDiscountAmount;
	private double totalPersonDiscountAmount;
	private double totalDiscountAmount;
	private double totalPaymentWithDiscountAmount;
	private LocalDate dateCreation;
	private OrderStatus status;

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

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<Pet> getProductsPets() {
		return this.productsPets;
	}

	public void setProductsPets(List<Pet> productsPets) {
		this.productsPets = productsPets;
	}

	public List<FeedAndOther> getOtherProducts() {
		return this.otherProducts;
	}

	public void setOtherProducts(List<FeedAndOther> otherProducts) {
		this.otherProducts = otherProducts;
	}

	public double getTotalPaymentAmount() {
		return this.totalPaymentAmount;
	}

	public void setTotalPaymentAmount(double totalPaymentAmount) {
		this.totalPaymentAmount = totalPaymentAmount;
	}

	public double getTotalDiscountAmount() {
		return this.totalDiscountAmount;
	}

	public void setTotalDiscountAmount(double totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}

	public double getTotalPaymentWithDiscountAmount() {
		return this.totalPaymentWithDiscountAmount;
	}

	public void setTotalPaymentWithDiscountAmount(double totalPaymentWithDiscountAmount) {
		this.totalPaymentWithDiscountAmount = totalPaymentWithDiscountAmount;
	}

	public double getTotalProductsDiscountAmount() {
		return this.totalProductsDiscountAmount;
	}

	public void setTotalProductsDiscountAmount(double totalProductsDiscountAmount) {
		this.totalProductsDiscountAmount = totalProductsDiscountAmount;
	}

	public double getTotalPersonDiscountAmount() {
		return this.totalPersonDiscountAmount;
	}

	public void setTotalPersonDiscountAmount(double totalPersonDiscountAmount) {
		this.totalPersonDiscountAmount = totalPersonDiscountAmount;
	}

	public LocalDate getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}

	public OrderStatus getStatus() {
		return this.status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public void setStatus(int id) throws IllegalArgumentException {
		this.status = OrderStatus.findStatusByStatusId(id);
	}

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

	public static class OrderBuilder {
		private Order order;

		public OrderBuilder() {
			this.order = new Order();
		}

		public OrderBuilder setId(long id) {
			this.order.setId(id);
			return this;
		}

		public OrderBuilder setUserId(long userId) {
			this.order.setUserId(userId);
			return this;
		}

		public OrderBuilder setProductsPets(List<Pet> pets) {
			this.order.setProductsPets(pets);
			return this;
		}

		public OrderBuilder setOtherProducts(List<FeedAndOther> otherProducts) {
			this.order.setOtherProducts(otherProducts);
			return this;
		}

		public OrderBuilder setTotalPaymentAmount(double totalPaymentAmount) {
			this.order.setTotalPaymentAmount(totalPaymentAmount);
			return this;
		}

		public OrderBuilder setTotalDiscountAmount(double totalDiscountAmount) {
			this.order.setTotalDiscountAmount(totalDiscountAmount);
			return this;
		}

		public OrderBuilder setDateCreation(LocalDate dateCreation) {
			this.order.setDateCreation(dateCreation);
			return this;
		}

		public OrderBuilder setTotalPaymentWithDiscountAmount(double totalPaymentWithDiscountAmount) {
			this.order.setTotalPaymentWithDiscountAmount(totalPaymentWithDiscountAmount);
			return this;
		}

		public OrderBuilder setTotalProductsDiscountAmount(double totalProductsDiscountAmount) {
			this.order.setTotalProductsDiscountAmount(totalProductsDiscountAmount);
			return this;
		}

		public OrderBuilder setTotalPersonDiscountAmount(double totalPersonDiscountAmount) {
			this.order.setTotalPersonDiscountAmount(totalPersonDiscountAmount);
			return this;
		}

		public OrderBuilder setStatus(OrderStatus status) {
			this.order.setStatus(status);
			return this;
		}

		public OrderBuilder setStatus(int id) throws IllegalArgumentException {
			this.order.setStatus(id);
			return this;
		}

		public Order build() {
			return this.order;
		}
	}
}