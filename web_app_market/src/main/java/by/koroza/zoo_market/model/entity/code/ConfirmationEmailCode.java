package by.koroza.zoo_market.model.entity.code;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ConfirmationEmailCode {
	private static final int DURATION_OF_CONFIRMATION_CODE_VALUE = 1;
	private static final ChronoUnit DURATION_OF_CONFIRMATION_CODE_UNIT = ChronoUnit.MINUTES;

	private static final boolean CONFIRMATION_CODE_STATUS_OPEN = true;
	private static final boolean CONFIRMATION_CODE_STATUS_CLOSED = false;

	private String code;
	private boolean isOpen;
	private LocalDateTime openDateTime;

	public ConfirmationEmailCode() {
		this.code = null;
		this.isOpen = true;
		this.openDateTime = null;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isOpen() {
		return this.isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public LocalDateTime getOpenDateTime() {
		return this.openDateTime;
	}

	public void setOpenDateTime(LocalDateTime openDateTime) {
		this.openDateTime = openDateTime;
	}

	public static int getDurationValue() {
		return DURATION_OF_CONFIRMATION_CODE_VALUE;
	}

	public static ChronoUnit getDurationUnit() {
		return DURATION_OF_CONFIRMATION_CODE_UNIT;
	}

	public static boolean getStatusOpen() {
		return CONFIRMATION_CODE_STATUS_OPEN;
	}

	public static boolean getStatusClosed() {
		return CONFIRMATION_CODE_STATUS_CLOSED;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + (this.code != null ? this.code.hashCode() : 1);
		result = result * PRIME + Boolean.hashCode(this.isOpen);
		result = result * PRIME + (this.openDateTime != null ? this.openDateTime.hashCode() : 1);
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
		if (!getClass().equals(object.getClass())) {
			return false;
		}
		ConfirmationEmailCode otherConfirmationEmailCode = (ConfirmationEmailCode) object;
		if (this.code == null) {
			if (otherConfirmationEmailCode.code != null) {
				return false;
			}
		} else if (!this.code.equals(otherConfirmationEmailCode.code)) {
			return false;
		}
		if (this.isOpen != otherConfirmationEmailCode.isOpen) {
			return false;
		}
		if (this.openDateTime == null) {
			if (otherConfirmationEmailCode.openDateTime != null) {
				return false;
			}
		} else if (!this.openDateTime.equals(otherConfirmationEmailCode.openDateTime)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConfirmationEmailCode [code=");
		builder.append(code);
		builder.append(", isOpen=");
		builder.append(isOpen);
		builder.append(", openDateTime=");
		builder.append(openDateTime);
		builder.append("]");
		return builder.toString();
	}

	public static class ConfirmationEmailCodeBuilder {
		private ConfirmationEmailCode code;

		public ConfirmationEmailCodeBuilder() {
			this.code = new ConfirmationEmailCode();
		}

		public ConfirmationEmailCodeBuilder setCode(String code) {
			this.code.setCode(code);
			return this;
		}

		public ConfirmationEmailCodeBuilder setOpen(boolean isOpen) {
			this.code.setOpen(isOpen);
			return this;
		}

		public ConfirmationEmailCodeBuilder setOpenDateTime(LocalDateTime openDateTime) {
			this.code.setOpenDateTime(openDateTime);
			return this;
		}

		public ConfirmationEmailCode build() {
			return this.code;
		}
	}
}