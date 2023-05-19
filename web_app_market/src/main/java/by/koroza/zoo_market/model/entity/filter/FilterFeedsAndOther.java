package by.koroza.zoo_market.model.entity.filter;

import java.util.Arrays;

public class FilterFeedsAndOther extends AbstractFilter {
	private String[] choosedTypesProduct;
	private String[] choosedProductBrend;
	private String[] choosedTypesPets;

	public FilterFeedsAndOther() {
		super();
		this.choosedTypesPets = null;
		this.choosedTypesProduct = null;
		this.choosedProductBrend = null;
	}

	public String[] getChoosedTypesProduct() {
		return this.choosedTypesProduct;
	}

	public void setChoosedTypesProduct(String[] choosedTypesProduct) {
		this.choosedTypesProduct = choosedTypesProduct;
	}

	public String[] getChoosedProductBrend() {
		return this.choosedProductBrend;
	}

	public void setChoosedProductBrend(String[] choosedProductBrend) {
		this.choosedProductBrend = choosedProductBrend;
	}

	public String[] getChoosedTypesPets() {
		return this.choosedTypesPets;
	}

	public void setChoosedTypesPets(String[] choosedTypesPets) {
		this.choosedTypesPets = choosedTypesPets;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + super.hashCode();
		result = PRIME * result + (this.choosedTypesProduct != null ? this.choosedTypesProduct.hashCode() : 1);
		result = PRIME * result + (this.choosedProductBrend != null ? this.choosedProductBrend.hashCode() : 1);
		result = PRIME * result + (this.choosedTypesPets != null ? this.choosedTypesPets.hashCode() : 1);
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (super.equals(object) == false) {
			return false;
		}
		FilterFeedsAndOther otherFilterFeedsAndOther = (FilterFeedsAndOther) object;
		if (this.choosedTypesProduct == null) {
			if (otherFilterFeedsAndOther.choosedTypesProduct != null) {
				return false;
			}
		} else if (!this.choosedTypesProduct.equals(otherFilterFeedsAndOther.choosedTypesProduct)) {
			return false;
		}
		if (this.choosedProductBrend == null) {
			if (otherFilterFeedsAndOther.choosedProductBrend != null) {
				return false;
			}
		} else if (!this.choosedProductBrend.equals(otherFilterFeedsAndOther.choosedProductBrend)) {
			return false;
		}
		if (this.choosedTypesPets == null) {
			if (otherFilterFeedsAndOther.choosedTypesPets != null) {
				return false;
			}
		} else if (!this.choosedTypesPets.equals(otherFilterFeedsAndOther.choosedTypesPets)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString()).append("\n");
		builder.append("FilterFeedsAndOther [choosedTypesProduct=");
		builder.append(Arrays.toString(choosedTypesProduct));
		builder.append(", choosedProductBrend=");
		builder.append(Arrays.toString(choosedProductBrend));
		builder.append(", choosedTypesPets=");
		builder.append(Arrays.toString(choosedTypesPets));
		builder.append("]");
		return builder.toString();
	}

	public static class FilterFeedsAndOtherBuilder {
		private FilterFeedsAndOther filter;

		public FilterFeedsAndOtherBuilder() {
			this.filter = new FilterFeedsAndOther();
		}

		public FilterFeedsAndOtherBuilder setChoosedTypesPets(String[] choosedTypesPets) {
			this.filter.setChoosedTypesPets(choosedTypesPets);
			return this;
		}

		public FilterFeedsAndOtherBuilder setOnlyProductsWithDiscont(boolean onlyProductsWithDiscont) {
			this.filter.setOnlyProductsWithDiscont(onlyProductsWithDiscont);
			return this;
		}

		public FilterFeedsAndOtherBuilder setMinDiscont(double minDiscont) {
			this.filter.setMinDiscont(minDiscont);
			return this;
		}

		public FilterFeedsAndOtherBuilder setMaxDiscont(double maxDiscont) {
			this.filter.setMaxDiscont(maxDiscont);
			return this;
		}

		public FilterFeedsAndOtherBuilder setMinPrice(double minPrice) {
			this.filter.setMinPrice(minPrice);
			return this;
		}

		public FilterFeedsAndOtherBuilder setChoosedTypesProduct(String[] choosedTypesProduct) {
			this.filter.setChoosedTypesProduct(choosedTypesProduct);
			return this;
		}

		public FilterFeedsAndOtherBuilder setChoosedProductBrend(String[] choosedProductBrend) {
			this.filter.setChoosedProductBrend(choosedProductBrend);
			return this;
		}

		public FilterFeedsAndOtherBuilder setMaxPrice(double maxPrice) {
			this.filter.setMaxPrice(maxPrice);
			return this;
		}

		public FilterFeedsAndOther build() {
			return this.filter;
		}
	}
}