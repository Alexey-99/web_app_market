package by.koroza.zoo_market.service.sorting.comparator;

import java.util.Comparator;
import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;

public interface SortComparator extends Comparator<Entry<? extends AbstractProduct, Long>> {
	public static final String EMPTY_LINE_NAME = "-";

	@Override
	public int compare(Entry<? extends AbstractProduct, Long> o1, Entry<? extends AbstractProduct, Long> o2);

}
