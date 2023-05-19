package by.koroza.zoo_market.model.entity.status;

public enum ProductStatus {
	AVAILABLE, RESERVED, NOT_AVAILABLE;

	public static ProductStatus findEqualProductStatus(String status) {
		ProductStatus resultStutus = null;
		for (ProductStatus productStutus : ProductStatus.values()) {
			if (productStutus.toString().toUpperCase().equals(status.toUpperCase())) {
				resultStutus = productStutus;
			}
		}
		return resultStutus;
	}
}