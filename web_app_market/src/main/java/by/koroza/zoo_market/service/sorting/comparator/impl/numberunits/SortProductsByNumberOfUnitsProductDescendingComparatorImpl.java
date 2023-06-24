package by.koroza.zoo_market.service.sorting.comparator.impl.numberunits;

import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.sorting.comparator.SortComparator;

public class SortProductsByNumberOfUnitsProductDescendingComparatorImpl implements SortComparator {

	@Override
	public int compare(Entry<? extends AbstractProduct, Long> o1, Entry<? extends AbstractProduct, Long> o2) {
		return (int) (o2.getValue() - o1.getValue());
	}
}