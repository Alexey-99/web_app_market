package by.koroza.zoo_market.model.entity.market;

import java.util.ArrayList;
import java.util.List;

import by.koroza.zoo_market.model.entity.market.abstraction.AbstractMatket;
import by.koroza.zoo_market.model.entity.market.abstraction.AbstractProduct;

public class ZooMarket extends AbstractMatket {
	private List<AbstractProduct> products;

	public ZooMarket() {
		super();
		this.products = new ArrayList<>();
	}

	public List<AbstractProduct> getProducts() {
		return products;
	}

	public void setProducts(List<AbstractProduct> products) {
		this.products = products;
	}

	public boolean add(AbstractProduct e) {
		return products.add(e);
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + super.hashCode();
		result = result * PRIME + (this.products != null ? this.products.hashCode() : 1);
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (!super.equals(object)) {
			return false;
		}
		ZooMarket otherZooMarket = (ZooMarket) object;
		if (this.products == null) {
			if (otherZooMarket.products != null) {
				return false;
			}
		} else if (!this.products.equals(otherZooMarket.products)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString()).append("\n");
		builder.append(this.products.toString());
		return builder.toString();
	}

	public static class ZooMarketBuilder {
		private ZooMarket market;

		public ZooMarketBuilder() {
			this.market = new ZooMarket();
		}

		public ZooMarketBuilder setName(String name) {
			this.market.setName(name);
			return this;
		}

		public ZooMarketBuilder setProducts(List<AbstractProduct> products) {
			this.market.setProducts(products);
			return this;
		}

		public ZooMarket build() {
			return this.market;
		}
	}
}