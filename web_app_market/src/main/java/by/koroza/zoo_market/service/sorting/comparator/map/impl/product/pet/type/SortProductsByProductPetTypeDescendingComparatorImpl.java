package by.koroza.zoo_market.service.sorting.comparator.map.impl.product.pet.type;

import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.sorting.comparator.map.SortComparatorMap;

/**
 * The Class SortProductsByProductPetTypeDescendingComparatorImpl.
 */
public class SortProductsByProductPetTypeDescendingComparatorImpl implements SortComparatorMap {

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(Entry<? extends AbstractProduct, Long> o1, Entry<? extends AbstractProduct, Long> o2) {
		String productPetTypeFirst = o1.getKey().getClass().equals(Pet.class) ? ((Pet) o1.getKey()).getSpecie()
				: EMPTY_LINE_NAME;
		String productPetTypeSecond = o2.getKey().getClass().equals(Pet.class) ? ((Pet) o2.getKey()).getSpecie()
				: EMPTY_LINE_NAME;
		return productPetTypeSecond.compareTo(productPetTypeFirst);
	}
}