package by.koroza.zoo_market.model.entity.filter;

import java.util.Arrays;

import by.koroza.zoo_market.model.entity.filter.product.AbstractProductFilter;

public class FilterPet extends AbstractProductFilter {
	private String[] choosedTypesPets;
	private String[] choosedBreedPets;
	private int minNumberMonth;
	private int minNumberYear;
	private int maxNumberMonth;
	private int maxNumberYear;

	public FilterPet() {
		super();
		this.choosedTypesPets = null;
		this.choosedBreedPets = null;
		this.minNumberMonth = 0;
		this.maxNumberMonth = 0;
		this.minNumberMonth = 0;
		this.maxNumberYear = 0;
	}

	public String[] getChoosedTypesPets() {
		return this.choosedTypesPets;
	}

	public void setChoosedTypesPets(String[] choosedTypesPets) {
		this.choosedTypesPets = choosedTypesPets;
	}

	public String[] getChoosedBreedPets() {
		return this.choosedBreedPets;
	}

	public void setChoosedBreedPets(String[] choosedBreedPets) {
		this.choosedBreedPets = choosedBreedPets;
	}

	public int getMinNumberMonth() {
		return minNumberMonth;
	}

	public void setMinNumberMonth(int minNumberMonth) {
		this.minNumberMonth = minNumberMonth;
	}

	public int getMinNumberYear() {
		return minNumberYear;
	}

	public void setMinNumberYear(int minNumberYear) {
		this.minNumberYear = minNumberYear;
	}

	public int getMaxNumberMonth() {
		return maxNumberMonth;
	}

	public int getMaxNumberYear() {
		return maxNumberYear;
	}

	public void setMaxNumberYear(int maxNumberYear) {
		this.maxNumberYear = maxNumberYear;
	}

	public void setMaxNumberMonth(int maxNumberMonth) {
		this.maxNumberMonth = maxNumberMonth;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + super.hashCode();
		result = PRIME * result + (this.choosedTypesPets != null ? this.choosedTypesPets.hashCode() : 1);
		result = PRIME * result + (this.choosedBreedPets != null ? this.choosedBreedPets.hashCode() : 1);
		result = PRIME * result + this.minNumberMonth;
		result = PRIME * result + this.maxNumberMonth;
		result = PRIME * result + this.minNumberYear;
		result = PRIME * result + this.maxNumberYear;
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (super.equals(object) == false) {
			return false;
		}
		FilterPet otherFilter = (FilterPet) object;
		if (this.choosedTypesPets == null) {
			if (otherFilter.choosedTypesPets != null) {
				return false;
			}
		} else if (!this.choosedTypesPets.equals(otherFilter.choosedTypesPets)) {
			return false;
		}

		if (this.choosedBreedPets == null) {
			if (otherFilter.choosedBreedPets != null) {
				return false;
			}
		} else if (!this.choosedBreedPets.equals(otherFilter.choosedBreedPets)) {
			return false;
		}
		if (this.minNumberMonth != otherFilter.minNumberMonth) {
			return false;
		}
		if (this.maxNumberMonth != otherFilter.maxNumberMonth) {
			return false;
		}
		if (this.minNumberYear != otherFilter.minNumberYear) {
			return false;
		}
		if (this.maxNumberYear != otherFilter.maxNumberYear) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		if (this.choosedTypesPets != null && this.choosedTypesPets.length > 0) {
			builder.append("Choosed types pets: ").append(Arrays.toString(this.choosedTypesPets).subSequence(1,
					Arrays.toString(this.choosedTypesPets).length() - 1)).append("; ");
		}
		if (this.choosedBreedPets != null && this.choosedBreedPets.length > 0) {
			builder.append("Choosed breeds: ").append(Arrays.toString(this.choosedBreedPets).subSequence(1,
					Arrays.toString(this.choosedBreedPets).length() - 1)).append("; ");
		}
		if (minNumberMonth != 0 || minNumberYear != 0) {
			builder.append("Min age: ");
			if (minNumberYear != 0) {
				builder.append(this.minNumberYear).append(this.minNumberYear > 1 ? "years" : "year").append("  ");
			}
			if (minNumberMonth != 0) {
				builder.append(this.minNumberMonth).append(this.minNumberMonth > 1 ? "months" : "month").append("; ");
			}
		}
		if (maxNumberMonth != 0 || maxNumberYear != 0) {
			builder.append("Max age: ");
			if (this.maxNumberYear != 0) {
				builder.append(this.maxNumberYear).append(this.maxNumberYear > 1 ? "years" : "year").append("  ");
			}
			if (this.maxNumberMonth != 0) {
				builder.append(this.maxNumberMonth).append(this.maxNumberMonth > 1 ? "months" : "month").append("; ");
			}
		}
		return builder.toString();
	}

	public String toStringRus() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toStringRus());
		if (this.choosedTypesPets != null && this.choosedTypesPets.length > 0) {
			builder.append("Выбранные типы питомцев: ").append(Arrays.toString(this.choosedTypesPets).subSequence(1,
					Arrays.toString(this.choosedTypesPets).length() - 1)).append("; ");
		}
		if (this.choosedBreedPets != null && this.choosedBreedPets.length > 0) {
			builder.append("Выбранные породы питомцев: ").append(Arrays.toString(this.choosedBreedPets).subSequence(1,
					Arrays.toString(this.choosedBreedPets).length() - 1)).append("; ");
		}
		if (minNumberMonth != 0 || minNumberYear != 0) {
			builder.append("Минимальный возрост питомца: ");
			if (minNumberYear != 0) {
				builder.append(this.minNumberYear).append(this.minNumberYear > 1 ? "лет(года)" : "год").append("  ");
			}
			if (minNumberMonth != 0) {
				builder.append(this.minNumberMonth).append(this.minNumberMonth > 1 ? "месяца(месяцев)" : "месяц")
						.append("; ");
			}
		}
		if (maxNumberMonth != 0 || maxNumberYear != 0) {
			builder.append("максимальный возрост питомца: ");
			if (this.maxNumberYear != 0) {
				builder.append(this.maxNumberYear).append(this.maxNumberYear > 1 ? "лет(года)" : "год").append("  ");
			}
			if (this.maxNumberMonth != 0) {
				builder.append(this.maxNumberMonth).append(this.maxNumberMonth > 1 ? "месяца(месяцев)" : "месяц")
						.append("; ");
			}
		}

		return builder.toString();
	}

	public static class FilterPetBuilder {
		private FilterPet filter;

		public FilterPetBuilder() {
			this.filter = new FilterPet();
		}

		public FilterPetBuilder setChoosedTypesPets(String[] choosedTypesPets) {
			this.filter.setChoosedTypesPets(choosedTypesPets);
			return this;
		}

		public FilterPetBuilder setChoosedBreedPets(String[] choosedBreedPets) {
			this.filter.setChoosedBreedPets(choosedBreedPets);
			return this;
		}

		public FilterPetBuilder setOnlyProductsWithDiscont(boolean onlyProductsWithDiscont) {
			this.filter.setOnlyProductsWithDiscont(onlyProductsWithDiscont);
			return this;
		}

		public FilterPetBuilder setMinDiscont(double minDiscont) {
			this.filter.setMinDiscount(minDiscont);
			return this;
		}

		public FilterPetBuilder setMaxDiscont(double maxDiscont) {
			this.filter.setMaxDiscount(maxDiscont);
			return this;
		}

		public FilterPetBuilder setMinPrice(double minPrice) {
			this.filter.setMinPrice(minPrice);
			return this;
		}

		public FilterPetBuilder setMaxPriceAllProducts(double maxPriceAllProducts) {
			this.filter.setMaxPriceAllProducts(maxPriceAllProducts);
			return this;
		}

		public FilterPetBuilder setMaxPriceEntered(double maxPriceEntered) {
			this.filter.setMaxPriceEntered(maxPriceEntered);
			return this;
		}

		public FilterPetBuilder setMinNumberMonth(int minNumberMonth) {
			this.filter.setMinNumberMonth(minNumberMonth);
			return this;
		}

		public FilterPetBuilder setMinNumberYear(int minNumberYear) {
			this.filter.setMinNumberYear(minNumberYear);
			return this;
		}

		public FilterPetBuilder setMaxNumberYear(int maxNumberYear) {
			this.filter.setMaxNumberYear(maxNumberYear);
			return this;
		}

		public FilterPetBuilder setMaxNumberMonth(int maxNumberMonth) {
			this.filter.setMaxNumberMonth(maxNumberMonth);
			return this;
		}

		public FilterPet build() {
			return this.filter;
		}
	}
}