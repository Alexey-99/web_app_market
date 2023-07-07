package by.koroza.zoo_market.service.sorting.comparator.impl.product.pet.birthdate;

import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.sorting.comparator.SortComparator;

/**
 * The Class SortProductsByProductPetBirthDateAscendingComparatorImpl.
 */
public class SortProductsByProductPetBirthDateAscendingComparatorImpl implements SortComparator {

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(Entry<? extends AbstractProduct, Long> o1, Entry<? extends AbstractProduct, Long> o2) {
		String productPetBirthDateFirst = o1.getKey().getClass().equals(Pet.class)
				? ((Pet) o1.getKey()).getBirthDate().toString()
				: EMPTY_LINE_NAME;
		String productPetBirthDateSecond = o2.getKey().getClass().equals(Pet.class)
				? ((Pet) o2.getKey()).getBirthDate().toString()
				: EMPTY_LINE_NAME;
		return productPetBirthDateFirst.compareTo(productPetBirthDateSecond);
	}
}