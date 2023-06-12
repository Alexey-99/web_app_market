package by.koroza.zoo_market.model.entity.market.product.constituent;

import java.util.concurrent.TimeUnit;

public class UpdateDateTime {

	public enum UnitType {
		YEARS, MONTHS, DAYS, HOURS, MINUTS, SECONDS;
	}

	private long value;
	private UnitType unit;

	public UpdateDateTime() {
		this.value = 0;
		this.unit = UnitType.SECONDS;
	}

	public UpdateDateTime(long value, UnitType unit) {
		this.value = value;
		this.unit = unit;
	}

	public UpdateDateTime(long seconds) {
		createUpdateDateTime(seconds);
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public UnitType getUnit() {
		return unit;
	}

	public void setUnit(UnitType unit) {
		this.unit = unit;
	}

	public void setUnitYear() {
		this.unit = UnitType.YEARS;
	}

	public void setUnitMonth() {
		this.unit = UnitType.MONTHS;
	}

	public void setUnitDay() {
		this.unit = UnitType.DAYS;
	}

	public void setUnitHour() {
		this.unit = UnitType.HOURS;
	}

	public void setUnitMinut() {
		this.unit = UnitType.MINUTS;
	}

	public void setUnitSecond() {
		this.unit = UnitType.SECONDS;
	}

	private void createUpdateDateTime(long seconds) {
		if (TimeUnit.SECONDS.toDays(seconds) > 0) {
			this.value = TimeUnit.SECONDS.toDays(seconds);
			this.unit = UnitType.DAYS;
		} else if (TimeUnit.SECONDS.toHours(seconds) > 0) {
			this.value = TimeUnit.SECONDS.toHours(seconds);
			this.unit = UnitType.HOURS;
		} else if (TimeUnit.SECONDS.toMinutes(seconds) >= 0) {
			this.value = TimeUnit.SECONDS.toMinutes(seconds);
			this.unit = UnitType.MINUTS;
		} else {
			this.value = TimeUnit.SECONDS.toMinutes(seconds);
			this.unit = UnitType.SECONDS;
		}
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + Long.hashCode(this.value);
		result = result * PRIME + (this.unit != null ? this.unit.hashCode() : 1);
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
		UpdateDateTime otherUpdateDateTime = (UpdateDateTime) object;
		if (value != otherUpdateDateTime.value) {
			return false;
		}
		if (this.unit == null) {
			if (otherUpdateDateTime.unit != null) {
				return false;
			}
		} else if (this.unit.equals(otherUpdateDateTime.unit)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return toStringEn();
	}

	public String toStringEn() {
		StringBuilder builder = new StringBuilder();
		switch (this.unit) {
		case YEARS -> {
			if (this.value == 1) {
				builder.append(this.value).append(" ").append("year");
			} else {
				builder.append(this.value).append(" ").append("years");
			}
		}
		case MONTHS -> {
			if (this.value == 1) {
				builder.append(this.value).append(" ").append("month");
			} else {
				builder.append(this.value).append(" ").append("months");
			}
		}
		case DAYS -> {
			if (this.value == 1) {
				builder.append(this.value).append(" ").append("day");
			} else {
				builder.append(this.value).append(" ").append("days");
			}
		}
		case HOURS -> {
			if (this.value == 1) {
				builder.append(this.value).append(" ").append("hour");
			} else {
				builder.append(this.value).append(" ").append("hours");
			}
		}
		case MINUTS -> {
			if (this.value == 1) {
				builder.append(this.value).append(" ").append("minut");
			} else {
				builder.append(this.value).append(" ").append("minuts");
			}
		}
		case SECONDS -> {
			if (this.value == 1) {
				builder.append(this.value).append(" ").append("second");
			} else {
				builder.append(this.value).append(" ").append("seconds");
			}
		}
		}
		return builder.toString();
	}

	public String toStringRus() {
		StringBuilder builder = new StringBuilder();
		switch (this.unit) {
		case YEARS -> {
			if (this.value == 1) {
				builder.append(this.value).append(" ").append("год");
			} else if (this.value > 1 && this.value < 5) {
				builder.append(this.value).append(" ").append("года");
			} else {
				builder.append(this.value).append(" ").append("лет");
			}
		}
		case MONTHS -> {
			if (this.value == 1) {
				builder.append(this.value).append(" ").append("месяц");
			} else if (this.value > 1 && this.value < 5) {
				builder.append(this.value).append(" ").append("месяца");
			} else {
				builder.append(this.value).append(" ").append("месяцев");
			}
		}
		case DAYS -> {
			if (this.value == 1 || this.value == 21 || this.value == 31) {
				builder.append(this.value).append(" ").append("день");
			} else if ((this.value > 1 && this.value < 5) || (this.value > 21 && this.value <= 24)) {
				builder.append(this.value).append(" ").append("дня");
			} else {
				builder.append(this.value).append(" ").append("дней");
			}
		}
		case HOURS -> {
			if (this.value == 1 || this.value == 21) {
				builder.append(this.value).append(" ").append("час");
			} else if ((this.value > 1 && this.value < 5) || (this.value > 21 && this.value <= 24)) {
				builder.append(this.value).append(" ").append("часа");
			} else {
				builder.append(this.value).append(" ").append("часов");
			}
		}
		case MINUTS -> {
			if (this.value == 1 || this.value == 21 || this.value == 31 || this.value == 41 || this.value == 51) {
				builder.append(this.value).append(" ").append("минута");
			} else if ((this.value > 1 && this.value < 5) || (this.value > 21 && this.value <= 24)
					|| (this.value > 31 && this.value <= 34) || (this.value > 41 && this.value <= 44)
					|| (this.value > 51 && this.value <= 54)) {
				builder.append(this.value).append(" ").append("минуты");
			} else {
				builder.append(this.value).append(" ").append("минут");
			}
		}
		case SECONDS -> {
			if (this.value == 1 || this.value == 21 || this.value == 31 || this.value == 41 || this.value == 51) {
				builder.append(this.value).append(" ").append("секунда");
			} else if ((this.value > 1 && this.value < 5) || (this.value > 21 && this.value <= 24)
					|| (this.value > 31 && this.value <= 34) || (this.value > 41 && this.value <= 44)
					|| (this.value > 51 && this.value <= 54)) {
				builder.append(this.value).append(" ").append("секунды");
			} else {
				builder.append(this.value).append(" ").append("секунд");
			}
		}
		}
		return builder.toString();
	}

	public static class UpdateDateTimeBuilder {
		private UpdateDateTime updateDateTime;

		public UpdateDateTimeBuilder() {
			this.updateDateTime = new UpdateDateTime();
		}

		public UpdateDateTimeBuilder setValue(long value) {
			this.updateDateTime.setValue(value);
			return this;
		}

		public UpdateDateTimeBuilder setUnit(UnitType unit) {
			this.updateDateTime.setUnit(unit);
			return this;
		}

		public UpdateDateTimeBuilder setUnitYear() {
			this.updateDateTime.setUnitYear();
			return this;
		}

		public UpdateDateTimeBuilder setUnitMonth() {
			this.updateDateTime.setUnitMonth();
			return this;
		}

		public UpdateDateTimeBuilder setUnitDay() {
			this.updateDateTime.setUnitDay();
			return this;
		}

		public UpdateDateTimeBuilder setUnitHour() {
			this.updateDateTime.setUnitHour();
			return this;
		}

		public UpdateDateTimeBuilder setUnitMinut() {
			this.updateDateTime.setUnitMinut();
			return this;
		}

		public UpdateDateTimeBuilder setUnitSecond() {
			this.updateDateTime.setUnitSecond();
			return this;
		}

		public UpdateDateTime build() {
			return this.updateDateTime;
		}
	}
}