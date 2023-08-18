package by.koroza.zoo_market.model.entity.detalization;

import java.util.LinkedList;
import java.util.List;

import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;

public abstract class InformatorAboutProduct {
	private long quantityAvailable;
	private long quantityInReserve;
	private long quantityInReserveInOpenOrders;
	private long quantityInReserveInWaitingPayOrders;
	private long quantityInReserveInCloseOrders;
	private List<OrderDetalizationByProduct> detailsOpenOrdersWithProduct;
	private List<OrderDetalizationByProduct> detailsWaitingPayOrdersWithProduct;
	private List<OrderDetalizationByProduct> detailsCloseOrdersWithProduct;

	public InformatorAboutProduct() {
		this.detailsOpenOrdersWithProduct = new LinkedList<>();
		this.detailsWaitingPayOrdersWithProduct = new LinkedList<>();
		this.detailsCloseOrdersWithProduct = new LinkedList<>();
	}

	public long getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(long quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public List<OrderDetalizationByProduct> getDetailsOpenOrdersWithProduct() {
		return detailsOpenOrdersWithProduct;
	}

	public void setDetailsOpenOrdersWithProduct(List<OrderDetalizationByProduct> detailsOpenOrdersWithProduct) {
		this.detailsOpenOrdersWithProduct = detailsOpenOrdersWithProduct;
	}

	public List<OrderDetalizationByProduct> getDetailsWaitingPayOrdersWithProduct() {
		return detailsWaitingPayOrdersWithProduct;
	}

	public void setDetailsWaitingPayOrdersWithProduct(
			List<OrderDetalizationByProduct> detailsWaitingPayOrdersWithProduct) {
		this.detailsWaitingPayOrdersWithProduct = detailsWaitingPayOrdersWithProduct;
	}

	public List<OrderDetalizationByProduct> getDetailsCloseOrdersWithProduct() {
		return detailsCloseOrdersWithProduct;
	}

	public void setDetailsCloseOrdersWithProduct(List<OrderDetalizationByProduct> detailsCloseOrdersWithProduct) {
		this.detailsCloseOrdersWithProduct = detailsCloseOrdersWithProduct;
	}

	public long getQuantityInReserveInOpenOrders() {
		return quantityInReserveInOpenOrders;
	}

	public void setQuantityInReserveInOpenOrders(long quantityInReserveInOpenOrders) {
		this.quantityInReserveInOpenOrders = quantityInReserveInOpenOrders;
	}

	public long getQuantityInReserveInWaitingPayOrders() {
		return quantityInReserveInWaitingPayOrders;
	}

	public long getQuantityInReserveInCloseOrders() {
		return quantityInReserveInCloseOrders;
	}

	public void setQuantityInReserveInCloseOrders(long quantityInReserveInCloseOrders) {
		this.quantityInReserveInCloseOrders = quantityInReserveInCloseOrders;
	}

	public void setQuantityInReserveInWaitingPayOrders(long quantityInReserveInWaitingPayOrders) {
		this.quantityInReserveInWaitingPayOrders = quantityInReserveInWaitingPayOrders;
	}

	public long getQuantityInReserve() {
		return quantityInReserve;
	}

	public void setQuantityInReserve(long quantityInReserve) {
		this.quantityInReserve = quantityInReserve;
	}

	public abstract AbstractProduct getProduct();

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + Long.hashCode(this.quantityAvailable);
		result = result * PRIME + Long.hashCode(this.quantityInReserve);
		result = result * PRIME + Long.hashCode(this.quantityInReserveInOpenOrders);
		result = result * PRIME + Long.hashCode(this.quantityInReserveInWaitingPayOrders);
		result = result * PRIME + Long.hashCode(this.quantityInReserveInCloseOrders);
		result = result * PRIME
				+ (this.detailsOpenOrdersWithProduct != null ? this.detailsOpenOrdersWithProduct.hashCode() : 1);
		result = result * PRIME
				+ (this.detailsWaitingPayOrdersWithProduct != null ? this.detailsWaitingPayOrdersWithProduct.hashCode()
						: 1);
		result = result * PRIME
				+ (this.detailsCloseOrdersWithProduct != null ? this.detailsCloseOrdersWithProduct.hashCode() : 1);
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
		InformatorAboutProduct other = (InformatorAboutProduct) object;
		if (detailsCloseOrdersWithProduct == null) {
			if (other.detailsCloseOrdersWithProduct != null) {
				return false;
			}
		} else if (!detailsCloseOrdersWithProduct.equals(other.detailsCloseOrdersWithProduct)) {
			return false;
		}
		if (detailsOpenOrdersWithProduct == null) {
			if (other.detailsOpenOrdersWithProduct != null) {
				return false;
			}
		} else if (!detailsOpenOrdersWithProduct.equals(other.detailsOpenOrdersWithProduct)) {
			return false;
		}
		if (detailsWaitingPayOrdersWithProduct == null) {
			if (other.detailsWaitingPayOrdersWithProduct != null) {
				return false;
			}
		} else if (!detailsWaitingPayOrdersWithProduct.equals(other.detailsWaitingPayOrdersWithProduct)) {
			return false;
		}
		if (quantityAvailable != other.quantityAvailable) {
			return false;
		}
		if (quantityInReserve != other.quantityInReserve) {
			return false;
		}
		if (quantityInReserveInCloseOrders != other.quantityInReserveInCloseOrders) {
			return false;
		}
		if (quantityInReserveInOpenOrders != other.quantityInReserveInOpenOrders) {
			return false;
		}
		if (quantityInReserveInWaitingPayOrders != other.quantityInReserveInWaitingPayOrders) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InformatorAboutProduct [quantityAvailable=");
		builder.append(quantityAvailable);
		builder.append(", quantityInReserve=");
		builder.append(quantityInReserve);
		builder.append(", quantityInReserveInOpenOrders=");
		builder.append(quantityInReserveInOpenOrders);
		builder.append(", quantityInReserveInWaitingPayOrders=");
		builder.append(quantityInReserveInWaitingPayOrders);
		builder.append(", quantityInReserveInCloseOrders=");
		builder.append(quantityInReserveInCloseOrders);
		builder.append(", detailsOpenOrdersWithProduct=");
		builder.append(detailsOpenOrdersWithProduct);
		builder.append(", detailsWaitingPayOrdersWithProduct=");
		builder.append(detailsWaitingPayOrdersWithProduct);
		builder.append(", detailsCloseOrdersWithProduct=");
		builder.append(detailsCloseOrdersWithProduct);
		builder.append("]");
		return builder.toString();
	}
}