package by.koroza.zoo_market.service.sorting.comparator.product.pet.birthdate;

import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.sorting.comparator.SortComparator;

public class SortProductsByProductPetBirthDateAscendingComparatorImpl implements SortComparator {

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