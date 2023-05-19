package by.koroza.zoo_market.model.entity.filter;

import java.util.Arrays;

public class FilterPet extends AbstractFilter {
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
		builder.append(super.toString()).append("\n");
		builder.append("FilterPet [choosedTypesPets=");
		builder.append(Arrays.toString(choosedTypesPets));
		builder.append(", choosedBreedPets=");
		builder.append(Arrays.toString(choosedBreedPets));
		builder.append(", minNumberMonth=");
		builder.append(minNumberMonth);
		builder.append(", minNumberYear=");
		builder.append(minNumberYear);
		builder.append(", maxNumberMonth=");
		builder.append(maxNumberMonth);
		builder.append(", maxNumberYear=");
		builder.append(maxNumberYear);
		builder.append("]");
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
			this.filter.setMinDiscont(minDiscont);
			return this;
		}

		public FilterPetBuilder setMaxDiscont(double maxDiscont) {
			this.filter.setMaxDiscont(maxDiscont);
			return this;
		}

		public FilterPetBuilder setMinPrice(double minPrice) {
			this.filter.setMinPrice(minPrice);
			return this;
		}

		public FilterPetBuilder setMaxPrice(double maxPrice) {
			this.filter.setMaxPrice(maxPrice);
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