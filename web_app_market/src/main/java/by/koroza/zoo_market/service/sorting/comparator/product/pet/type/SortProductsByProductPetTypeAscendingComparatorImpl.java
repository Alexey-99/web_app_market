package by.koroza.zoo_market.service.sorting.comparator.product.pet.type;

import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.sorting.comparator.SortComparator;

public class SortProductsByProductPetTypeAscendingComparatorImpl implements SortComparator {

	@Override
	public int compare(Entry<? extends AbstractProduct, Long> o1, Entry<? extends AbstractProduct, Long> o2) {
		String productPetTypeFirst = o1.getKey().getClass().equals(Pet.class) ? ((Pet) o1.getKey()).getSpecie()
				: EMPTY_LINE_NAME;
		String productPetTypeSecond = o2.getKey().getClass().equals(Pet.class) ? ((Pet) o2.getKey()).getSpecie()
				: EMPTY_LINE_NAME;
		return productPetTypeFirst.compareTo(productPetTypeSecond);
	}
}