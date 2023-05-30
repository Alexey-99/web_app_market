package by.koroza.zoo_market.model.entity.market.product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import by.koroza.zoo_market.model.entity.market.abstraction.AbstractProduct;
import by.koroza.zoo_market.model.entity.market.product.constituent.ImageFile;

public class Pet extends AbstractProduct implements Comparable<Pet> {
	private String specie;
	private String breed;
	private LocalDate birthDate;

	private static final String PATTERN_FORMATTER_DATE_BIRTH_DATE = "yyyy-MM-dd";

	public Pet() {
		super();
		this.specie = null;
		this.breed = null;
		this.birthDate = null;
	}

	public String getSpecie() {
		return this.specie;
	}

	public void setSpecie(String specie) {
		this.specie = specie;
	}

	public String getBreed() {
		return this.breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public LocalDate getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(LocalDate birthDate) throws DateTimeParseException {
		this.birthDate = LocalDate.parse(birthDate.toString(),
				DateTimeFormatter.ofPattern(PATTERN_FORMATTER_DATE_BIRTH_DATE));
	}

	public void setBirthDate(String birthDate) throws DateTimeParseException {
		this.birthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern(PATTERN_FORMATTER_DATE_BIRTH_DATE));
	}

	public String getDescription() {
		StringBuilder builder = new StringBuilder("Description");
		builder.append("Specie = ").append(this.specie).append("; ");
		builder.append("Breed = ").append(this.breed).append("; ");
		builder.append("Birth date = ").append(this.birthDate.toString()).append("; ");
		builder.append("Birth date = ").append(this.birthDate.toString()).append("\n");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + super.hashCode();
		result = result * PRIME + (this.specie != null ? this.specie.hashCode() : 1);
		result = result * PRIME + (this.breed != null ? this.breed.hashCode() : 1);
		result = result * PRIME + (this.birthDate != null ? this.birthDate.hashCode() : 1);
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (!super.equals(object)) {
			return false;
		}
		Pet otherPet = (Pet) object;
		if (this.specie == null) {
			if (otherPet.specie != null) {
				return false;
			}
		} else if (!this.specie.equals(otherPet.specie)) {
			return false;
		}
		if (this.breed == null) {
			if (otherPet.breed != null) {
				return false;
			}
		} else if (!this.breed.equals(otherPet.breed)) {
			return false;
		}
		if (this.birthDate == null) {
			if (otherPet.birthDate != null) {
				return false;
			}
		} else if (!this.birthDate.equals(otherPet.birthDate)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString()).append("\n");
		builder.append("Pet [specie=");
		builder.append(specie);
		builder.append(", breed=");
		builder.append(breed);
		builder.append(", birthDate=");
		builder.append(birthDate);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(Pet otherPet) {
		return (int) (this.getId() - otherPet.getId());
	}

	public static class PetBuilder {
		private Pet pet;

		public PetBuilder() {
			this.pet = new Pet();
		}

		public PetBuilder setPrice(double price) {
			this.pet.setPrice(price);
			return this;
		}

		public PetBuilder setDiscount(double discount) {
			this.pet.setDiscount(discount);
			return this;
		}

		public PetBuilder setSpecie(String specie) {
			this.pet.setSpecie(specie);
			return this;
		}

		public PetBuilder setBreed(String breed) {
			this.pet.setBreed(breed);
			return this;
		}

		public PetBuilder setBirthDate(LocalDate birthDate) throws DateTimeParseException {
			this.pet.setBirthDate(birthDate);
			return this;
		}

		public PetBuilder setBirthDate(String birthDate) throws DateTimeParseException {
			this.pet.setBirthDate(birthDate);
			return this;
		}

		public PetBuilder setUpdateDateTime(LocalDateTime dateUpdate) throws DateTimeParseException {
			this.pet.setUpdateDateTime(dateUpdate);
			return this;
		}

		public PetBuilder setUpdateDateTime(String dateUpdate) throws DateTimeParseException {
			this.pet.setUpdateDateTime(dateUpdate);
			return this;
		}

		public PetBuilder setUpdateDateTime(LocalDate dateUpdate, LocalTime timeUpdate) throws DateTimeParseException {
			this.pet.setUpdateDateTime(dateUpdate, timeUpdate);
			return this;
		}

		public PetBuilder setId(long id) {
			this.pet.setId(id);
			return this;
		}

		public PetBuilder setTotalPrice(double totalPrice) {
			this.pet.setTotalPrice(totalPrice);
			return this;
		}

		public PetBuilder setImageFile(ImageFile imageFile) {
			this.pet.setImageFile(imageFile);
			return this;
		}

		public Pet build() {
			return this.pet;
		}
	}
}