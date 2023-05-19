package by.koroza.zoo_market.model.entity.bank;

public class BankCard {
	private String numberCard;
	private int monthEnd;
	private int yearEnd;
	private int cvs;
	private double sum;

	public BankCard() {
		this.numberCard = null;
		this.monthEnd = 0;
		this.yearEnd = 0;
		this.cvs = 0;
		this.sum = 0;
	}

	public String getNumberCard() {
		return this.numberCard;
	}

	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
	}

	public int getMonthEnd() {
		return this.monthEnd;
	}

	public void setMonthEnd(int monthEnd) {
		this.monthEnd = monthEnd;
	}

	public int getYearEnd() {
		return this.yearEnd;
	}

	public void setYearEnd(int yearEnd) {
		this.yearEnd = yearEnd;
	}

	public int getCvs() {
		return this.cvs;
	}

	public void setCvs(int cvs) {
		this.cvs = cvs;
	}

	public double getSum() {
		return this.sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + (this.numberCard != null ? this.numberCard.hashCode() : 1);
		result = result * PRIME + this.monthEnd;
		result = result * PRIME + this.yearEnd;
		result = result * PRIME + this.cvs;
		result = result * PRIME + Double.hashCode(this.sum);
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
		BankCard otherBankCard = (BankCard) object;
		if (this.numberCard == null) {
			if (otherBankCard.numberCard != null) {
				return false;
			}
		} else if (!numberCard.equals(otherBankCard.numberCard)) {
			return false;
		}
		if (this.monthEnd != otherBankCard.monthEnd) {
			return false;
		}
		if (this.yearEnd != otherBankCard.yearEnd) {
			return false;
		}
		if (this.cvs != otherBankCard.cvs) {
			return false;
		}
		if (this.sum != otherBankCard.sum) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BankCard [numberCard=");
		builder.append(numberCard);
		builder.append(", monthEnd=");
		builder.append(monthEnd);
		builder.append(", yearEnd=");
		builder.append(yearEnd);
		builder.append(", cvs=");
		builder.append(cvs);
		builder.append(", sum=");
		builder.append(sum);
		builder.append("]");
		return builder.toString();
	}

	public static class BankCardBuilder {
		private BankCard bankCard;

		public BankCardBuilder() {
			this.bankCard = new BankCard();
		}

		public BankCardBuilder setNumberCard(String numberCard) {
			this.bankCard.setNumberCard(numberCard);
			return this;
		}

		public BankCardBuilder setMonthEnd(int monthEnd) {
			this.bankCard.setMonthEnd(monthEnd);
			return this;
		}

		public BankCardBuilder setYearEnd(int yearEnd) {
			this.bankCard.setYearEnd(yearEnd);
			return this;
		}

		public BankCardBuilder setCvs(int cvs) {
			this.bankCard.setCvs(cvs);
			return this;
		}

		public BankCardBuilder setSum(double sum) {
			this.bankCard.setSum(sum);
			return this;
		}

		public BankCard build() {
			return this.bankCard;
		}
	}
}