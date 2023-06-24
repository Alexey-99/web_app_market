package by.koroza.zoo_market.service.sorting.comparator.impl.product.id;

import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.sorting.comparator.SortComparator;

public class SortProductsByIdDescendingComparatorImpl implements SortComparator {

	@Override
	public int compare(Entry<? extends AbstractProduct, Long> o1, Entry<? extends AbstractProduct, Long> o2) {
		String idProductFirst = o1.getKey().getClass().equals(Pet.class) ? "p-" + o1.getKey().getId()
				: "o-" + o1.getKey().getId();
		String idProductSecond = o2.getKey().getClass().equals(Pet.class) ? "p-" + o2.getKey().getId()
				: "o-" + o2.getKey().getId();
		return idProductSecond.compareTo(idProductFirst);
	}
}