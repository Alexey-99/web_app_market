package by.koroza.zoo_market.model.entity.status;

public enum OrderStatus {
	OPEN(1), WAITING_PAY(2), CLOSED(3), PROCESSING(4);

	private int id;

	private OrderStatus(int statusId) {
		this.id = statusId;
	}

	public int getId() {
		return this.id;
	}

	public static OrderStatus findStatusByStatusId(int id) {
		OrderStatus status = null;
		switch (id) {
		case 1 -> status = OPEN;
		case 2 -> status = WAITING_PAY;
		case 3 -> status = CLOSED;
		default -> throw new IllegalArgumentException("Unexpected value: " + id);
		}
		return status;
	}
}