package by.koroza.zoo_market.model.entity.filter;

import java.util.Arrays;

import by.koroza.zoo_market.model.entity.filter.product.AbstractProductFilter;

public class FilterFeedsAndOther extends AbstractProductFilter {
	private String[] choosedTypesProduct;
	private String[] choosedProductBrand;
	private String[] choosedTypesPets;

	public FilterFeedsAndOther() {
		super();
		this.choosedTypesPets = null;
		this.choosedTypesProduct = null;
		this.choosedProductBrand = null;
	}

	public String[] getChoosedTypesProduct() {
		return this.choosedTypesProduct;
	}

	public void setChoosedTypesProduct(String[] choosedTypesProduct) {
		this.choosedTypesProduct = choosedTypesProduct;
	}

	public String[] getChoosedProductBrand() {
		return this.choosedProductBrand;
	}

	public void setChoosedProductBrand(String[] choosedProductBrand) {
		this.choosedProductBrand = choosedProductBrand;
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
		result = PRIME * result + (this.choosedProductBrand != null ? this.choosedProductBrand.hashCode() : 1);
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
		if (this.choosedProductBrand == null) {
			if (otherFilterFeedsAndOther.choosedProductBrand != null) {
				return false;
			}
		} else if (!this.choosedProductBrand.equals(otherFilterFeedsAndOther.choosedProductBrand)) {
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
		builder.append(super.toString());
		if (this.choosedTypesProduct != null && this.choosedTypesProduct.length > 0) {
			builder.append("Choosed types products: ").append(Arrays.toString(this.choosedTypesProduct).subSequence(1,
					Arrays.toString(this.choosedTypesProduct).length() - 1)).append("; ");
		}
		if (this.choosedTypesPets != null && this.choosedTypesPets.length > 0) {
			builder.append("Choosed types pets: ").append(Arrays.toString(this.choosedTypesPets).subSequence(1,
					Arrays.toString(this.choosedTypesPets).length() - 1)).append("; ");
		}
		if (this.choosedProductBrand != null && this.choosedProductBrand.length > 0) {
			builder.append("Choosed product brand: ").append(Arrays.toString(this.choosedProductBrand).subSequence(1,
					Arrays.toString(this.choosedProductBrand).length() - 1)).append("; ");
		}
		return builder.toString();
	}

	public String toStringRus() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toStringRus());
		if (this.choosedTypesProduct != null && this.choosedTypesProduct.length > 0) {
			builder.append("Выбранные типы продуктов: ").append(Arrays.toString(this.choosedTypesProduct).subSequence(1,
					Arrays.toString(this.choosedTypesProduct).length() - 1)).append("; ");
		}
		if (this.choosedTypesPets != null && this.choosedTypesPets.length > 0) {
			builder.append("Выбранные типы питомцев: ").append(Arrays.toString(this.choosedTypesPets).subSequence(1,
					Arrays.toString(this.choosedTypesPets).length() - 1)).append("; ");
		}
		if (this.choosedProductBrand != null && this.choosedProductBrand.length > 0) {
			builder.append("Выбранные брэнды товаров: ").append(Arrays.toString(this.choosedProductBrand).subSequence(1,
					Arrays.toString(this.choosedProductBrand).length() - 1)).append("; ");
		}
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
			this.filter.setMinDiscount(minDiscont);
			return this;
		}

		public FilterFeedsAndOtherBuilder setMaxDiscont(double maxDiscont) {
			this.filter.setMaxDiscount(maxDiscont);
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
			this.filter.setChoosedProductBrand(choosedProductBrend);
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