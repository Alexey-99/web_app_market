package by.koroza.zoo_market.service.sorting.comparator.product.totalprice;

import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.sorting.comparator.SortComparator;

public class SortProductsByTotalPriceDescendingComparatorImpl implements SortComparator {

	@Override
	public int compare(Entry<? extends AbstractProduct, Long> o1, Entry<? extends AbstractProduct, Long> o2) {
		return (int) (o2.getKey().getTotalPrice() - o1.getKey().getTotalPrice());
	}
}