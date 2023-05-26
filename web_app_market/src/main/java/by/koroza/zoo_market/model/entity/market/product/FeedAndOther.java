package by.koroza.zoo_market.model.entity.market.product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.koroza.zoo_market.model.entity.market.abstraction.AbstractProduct;

public class FeedAndOther extends AbstractProduct implements Comparable<FeedAndOther> {
	private static final String REG_EX_PATTERN_FOR_PARSE_PET_TYPES = "\\,\\s?";

	private String productType;
	private String brand;
	private String descriptions;
	private List<String> petTypes;

	public FeedAndOther() {
		super();
		this.productType = null;
		this.brand = null;
		this.descriptions = null;
		this.petTypes = new ArrayList<>();

	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescriptions() {
		return this.descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public List<String> getPetTypes() {
		return this.petTypes;
	}

	public void setPetTypes(List<String> petTypes) {
		this.petTypes = petTypes;
	}

	public void setPetTypes(String petTypesLine) {
		this.petTypes = parsePetType(petTypesLine);
	}

	public void addPetType(String petType) {
		this.petTypes.add(petType);
	}

	public String getDescription() {
		StringBuilder builder = new StringBuilder("Description");
		builder.append("Product Type = ").append(this.productType).append("; ");
		builder.append("Brand = ").append(this.brand).append("; ");
		builder.append("Description = ").append(this.descriptions).append("; ");
		builder.append("This product for: ").append(this.petTypes.toString()).append("\n");
		return builder.toString();
	}

	private List<String> parsePetType(String petTypesLine) {
		List<String> petTypes = new ArrayList<>();
		String[] petTypesSplited = petTypesLine.split(REG_EX_PATTERN_FOR_PARSE_PET_TYPES);
		Arrays.stream(petTypesSplited).forEach(type -> petTypes.add(type.toLowerCase()));
		return petTypes;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + super.hashCode();
		result = result * PRIME + (this.productType != null ? this.productType.hashCode() : 1);
		result = result * PRIME + (this.brand != null ? this.brand.hashCode() : 1);
		result = result * PRIME + (this.descriptions != null ? this.descriptions.hashCode() : 1);
		result = result * PRIME + (this.petTypes != null ? this.petTypes.hashCode() : 1);
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (!super.equals(object)) {
			return false;
		}
		FeedAndOther otherFeedAndOther = (FeedAndOther) object;
		if (this.productType == null) {
			if (otherFeedAndOther.productType != null) {
				return false;
			}
		} else if (!this.productType.equals(otherFeedAndOther.productType)) {
			return false;
		}
		if (this.brand == null) {
			if (otherFeedAndOther.brand != null) {
				return false;
			}
		} else if (!this.brand.equals(otherFeedAndOther.brand)) {
			return false;
		}
		if (this.descriptions == null) {
			if (otherFeedAndOther.descriptions != null) {
				return false;
			}
		} else if (!this.descriptions.equals(otherFeedAndOther.descriptions)) {
			return false;
		}
		if (this.petTypes == null) {
			if (otherFeedAndOther.petTypes != null) {
				return false;
			}
		} else if (!this.petTypes.equals(otherFeedAndOther.petTypes)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString()).append("\n");
		builder.append("Product type: ").append(this.productType).append("\n");
		builder.append("Brand: ").append(this.brand).append("\n");
		builder.append("Descriptions: ").append(this.descriptions != null ? this.descriptions.toString() : null)
				.append("\n");
		builder.append("Pet types: ");
		for (String type : this.petTypes) {
			if (this.petTypes != null) {
				builder.append(type).append("; ");
			} else {
				builder.append("all pet");
			}
		}
		return builder.toString();
	}

	@Override
	public int compareTo(FeedAndOther otherFeedAndOther) {
		return (int) (this.getId() - otherFeedAndOther.getId());
	}

	public static class FeedAndOtherBuilder {
		private FeedAndOther feedAndOther;

		public FeedAndOtherBuilder() {
			this.feedAndOther = new FeedAndOther();
		}

		public FeedAndOtherBuilder setPrice(double price) {
			this.feedAndOther.setPrice(price);
			return this;
		}

		public FeedAndOtherBuilder setDiscount(double discount) {
			this.feedAndOther.setDiscount(discount);
			return this;
		}

		public FeedAndOtherBuilder setProductType(String productType) {
			this.feedAndOther.setProductType(productType);
			return this;
		}

		public FeedAndOtherBuilder setBrand(String brand) {
			this.feedAndOther.setBrand(brand);
			return this;
		}

		public FeedAndOtherBuilder setDescriptions(String descriptions) {
			this.feedAndOther.setDescriptions(descriptions);
			return this;
		}

		public FeedAndOtherBuilder setPetTypes(List<String> petTypes) {
			this.feedAndOther.setPetTypes(petTypes);
			return this;
		}

		public FeedAndOtherBuilder setPetTypes(String petTypesLine) {
			this.feedAndOther.setPetTypes(petTypesLine);
			return this;
		}

		public FeedAndOtherBuilder addPetType(String petType) {
			this.feedAndOther.addPetType(petType);
			return this;
		}

		public FeedAndOtherBuilder setUpdateDateTime(LocalDateTime dateUpdate) throws DateTimeParseException {
			this.feedAndOther.setUpdateDateTime(dateUpdate);
			return this;
		}

		public FeedAndOtherBuilder setUpdateDateTime(String dateUpdate) throws DateTimeParseException {
			this.feedAndOther.setUpdateDateTime(dateUpdate);
			return this;
		}

		public FeedAndOtherBuilder setUpdateDateTime(LocalDate dateUpdate, LocalTime timeUpdate)
				throws DateTimeParseException {
			this.feedAndOther.setUpdateDateTime(dateUpdate, timeUpdate);
			return this;
		}

		public FeedAndOtherBuilder setUpdateDateTime(String dateUpdate, String timeUpdate)
				throws DateTimeParseException {
			this.feedAndOther.setUpdateDateTime(dateUpdate, timeUpdate);
			return this;
		}

		public FeedAndOtherBuilder setId(long id) {
			this.feedAndOther.setId(id);
			return this;
		}

		public FeedAndOtherBuilder setTotalPrice(double totalPrice) {
			this.feedAndOther.setTotalPrice(totalPrice);
			return this;
		}

		public FeedAndOther build() {
			return this.feedAndOther;
		}
	}
}