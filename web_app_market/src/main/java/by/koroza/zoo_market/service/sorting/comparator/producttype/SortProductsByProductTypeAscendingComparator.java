package by.koroza.zoo_market.service.sorting.comparator.producttype;

import java.util.Comparator;
import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;

public class SortProductsByProductTypeAscendingComparator
		implements Comparator<Entry<? extends AbstractProduct, Long>> {
	private static final String PET_PRODUCT_TYPE_NAME = "pets";

	@Override
	public int compare(Entry<? extends AbstractProduct, Long> o1, Entry<? extends AbstractProduct, Long> o2) {
		String productTypeFirst = o1.getKey().getClass().equals(Pet.class) ? PET_PRODUCT_TYPE_NAME
				: ((FeedAndOther) o1.getKey()).getProductType();
		String productTypeSecond = o2.getKey().getClass().equals(Pet.class) ? PET_PRODUCT_TYPE_NAME
				: ((FeedAndOther) o1.getKey()).getProductType();
		return productTypeFirst.compareTo(productTypeSecond);
	}
}