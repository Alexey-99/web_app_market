package by.koroza.zoo_market.model.entity.market.product.abstraction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import by.koroza.zoo_market.model.entity.market.product.constituent.UpdateDateTime;

public abstract class AbstractProduct {
	private String imagePath;
	private long id;
	private double price;
	private double discount;
	private double totalPrice;
	private UpdateDateTime updateDateTime;

	private static final String PATTERN_FORMATTER_DATE_UPDATE = "yyyy-MM-dd";
	private static final String PATTERN_FORMATTER_TIME_UPDATE = "(HH):(mm):(ss)?";
	private static final String PATTERN_FORMATTER_DATE_TIME_UPDATE = PATTERN_FORMATTER_DATE_UPDATE + " "
			+ PATTERN_FORMATTER_TIME_UPDATE;

	public AbstractProduct() {
		this.imagePath = null;
		this.id = 0;
		this.price = 0;
		this.discount = 0;
		this.totalPrice = 0;
		this.updateDateTime = null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public UpdateDateTime getUpdateDateTime() {
		return this.updateDateTime;
	}

	public void setUpdateDateTime(UpdateDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime dateUpdate) throws DateTimeParseException {
		LocalDateTime productUpdateDateTime = LocalDateTime.parse(dateUpdate.toString(),
				DateTimeFormatter.ofPattern(PATTERN_FORMATTER_DATE_TIME_UPDATE));
		this.updateDateTime = new UpdateDateTime(calcUpdateDateTimeInSeconds(productUpdateDateTime));
	}

	public void setUpdateDateTime(String dateUpdate) throws DateTimeParseException {
		LocalDateTime productUpdateDateTime = LocalDateTime.parse(dateUpdate,
				DateTimeFormatter.ofPattern(PATTERN_FORMATTER_DATE_TIME_UPDATE));
		this.updateDateTime = new UpdateDateTime(calcUpdateDateTimeInSeconds(productUpdateDateTime));
	}

	public void setUpdateDateTime(LocalDate dateUpdate, LocalTime timeUpdate) throws DateTimeParseException {
		LocalDateTime productUpdateDateTime = LocalDateTime.of(dateUpdate, timeUpdate);
		this.updateDateTime = new UpdateDateTime(calcUpdateDateTimeInSeconds(productUpdateDateTime));
	}

	public void setUpdateDateTime(String dateUpdate, String timeUpdate) throws DateTimeParseException {
		LocalDateTime productUpdateDateTime = LocalDateTime.of(
				LocalDate.parse(dateUpdate, DateTimeFormatter.ofPattern(PATTERN_FORMATTER_DATE_UPDATE)),
				LocalTime.parse(timeUpdate, DateTimeFormatter.ofPattern(PATTERN_FORMATTER_TIME_UPDATE)));
		this.updateDateTime = new UpdateDateTime(calcUpdateDateTimeInSeconds(productUpdateDateTime));
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	private long calcUpdateDateTimeInSeconds(LocalDateTime productCreateInBD) {
		long seconds = ChronoUnit.SECONDS.between(productCreateInBD, LocalDateTime.now());
		return seconds;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + Long.hashCode(this.id);
		result = result * PRIME + Double.hashCode(this.price);
		result = result * PRIME + Double.hashCode(this.discount);
		result = result * PRIME + Double.hashCode(this.totalPrice);
		result = result * PRIME + (this.updateDateTime != null ? this.updateDateTime.hashCode() : 1);
		result = result * PRIME + (this.imagePath != null ? this.imagePath.hashCode() : 1);
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
		AbstractProduct otherAbstractProduct = (AbstractProduct) object;
		if (this.id != otherAbstractProduct.id) {
			return false;
		}
		if (this.price != otherAbstractProduct.price) {
			return false;
		}
		if (this.discount != otherAbstractProduct.discount) {
			return false;
		}
		if (this.totalPrice != otherAbstractProduct.totalPrice) {
			return false;
		}
		if (this.updateDateTime == null) {
			if (otherAbstractProduct.updateDateTime != null) {
				return false;
			}
		} else if (!this.updateDateTime.equals(otherAbstractProduct.updateDateTime)) {
			return false;
		}
		if (this.imagePath == null) {
			if (otherAbstractProduct.imagePath != null) {
				return false;
			}
		} else if (!this.imagePath.equals(otherAbstractProduct.imagePath)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AbstractProduct [id=");
		builder.append(id);
		builder.append(", price=");
		builder.append(price);
		builder.append(", discont=");
		builder.append(discount);
		builder.append(", totalPrice=");
		builder.append(totalPrice);
		builder.append(", updateDateTime=");
		builder.append(updateDateTime);
		builder.append("]");
		return builder.toString();
	}
}