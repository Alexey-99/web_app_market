package by.koroza.zoo_market.service.sorting.comparator.list.product;

import java.util.Comparator;

import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;

/**
 * The Interface SortComparator.
 */
public interface SortComparatorListProduct extends Comparator<AbstractProduct> {

	/** The Constant EMPTY_LINE_NAME. */
	public static final String EMPTY_LINE_NAME = "-";

	@Override
	public int compare(AbstractProduct o1, AbstractProduct o2);

}