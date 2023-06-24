package by.koroza.zoo_market.service.sorting.comparator.impl.product.pet.breed;

import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.sorting.comparator.SortComparator;

public class SortProductsByProductPetBreedDescendingComparatorImpl implements SortComparator {

	@Override
	public int compare(Entry<? extends AbstractProduct, Long> o1, Entry<? extends AbstractProduct, Long> o2) {
		String productPetBreedFirst = o1.getKey().getClass().equals(Pet.class) ? ((Pet) o1.getKey()).getBreed()
				: EMPTY_LINE_NAME;
		String productPetBreedSecond = o2.getKey().getClass().equals(Pet.class) ? ((Pet) o2.getKey()).getBreed()
				: EMPTY_LINE_NAME;
		return productPetBreedSecond.compareTo(productPetBreedFirst);
	}
}