package by.koroza.zoo_market.service.sorting.comparator.impl.product.type;

import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.sorting.comparator.SortComparator;

/**
 * The Class SortProductsByProductTypeAscendingComparatorImpl.
 */
public class SortProductsByProductTypeAscendingComparatorImpl implements SortComparator {

	/** The Constant PET_PRODUCT_TYPE_NAME. */
	private static final String PET_PRODUCT_TYPE_NAME = "pets";

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(Entry<? extends AbstractProduct, Long> o1, Entry<? extends AbstractProduct, Long> o2) {
		String productTypeFirst = o1.getKey().getClass().equals(Pet.class) ? PET_PRODUCT_TYPE_NAME
				: ((FeedAndOther) o1.getKey()).getProductType();
		String productTypeSecond = o2.getKey().getClass().equals(Pet.class) ? PET_PRODUCT_TYPE_NAME
				: ((FeedAndOther) o2.getKey()).getProductType();
		return productTypeFirst.compareTo(productTypeSecond);
	}
}