package by.koroza.zoo_market.service.sorting.product.impl.comparator.list.product;

import java.util.Comparator;
import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;

/**
 * The Interface SortComparatorListProduct.
 */
@FunctionalInterface
public interface SortComparatorListProduct extends Comparator<Entry<? extends AbstractProduct, Long>> {

	/** The Constant EMPTY_LINE_NAME. */
	public static final String EMPTY_LINE_NAME = "-";

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(Entry<? extends AbstractProduct, Long> o1, Entry<? extends AbstractProduct, Long> o2);
}