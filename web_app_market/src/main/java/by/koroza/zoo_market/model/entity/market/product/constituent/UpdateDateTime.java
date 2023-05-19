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
		StringBuilder builder = new StringBuilder();
		builder.append(this.value).append("").append(this.unit.toString().toLowerCase());
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