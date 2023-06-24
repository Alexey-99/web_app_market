package by.koroza.zoo_market.service.sorting.comparator.product.other.typespets;

import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.sorting.comparator.SortComparator;

public class SortProductsByProductTypesPetsAscendingComparatorImpl implements SortComparator {

	@Override
	public int compare(Entry<? extends AbstractProduct, Long> o1, Entry<? extends AbstractProduct, Long> o2) {
		String productTypesPetsFirst = o1.getKey().getClass().equals(Pet.class) ? EMPTY_LINE_NAME
				: ((FeedAndOther) o1.getKey()).getPetTypes().toString().substring(1,
						((FeedAndOther) o1.getKey()).getPetTypes().toString().length());
		String productTypesPetsSecond = o2.getKey().getClass().equals(Pet.class) ? EMPTY_LINE_NAME
				: ((FeedAndOther) o2.getKey()).getPetTypes().toString().substring(1,
						((FeedAndOther) o2.getKey()).getPetTypes().toString().length());
		return productTypesPetsFirst.compareTo(productTypesPetsSecond);
	}
}