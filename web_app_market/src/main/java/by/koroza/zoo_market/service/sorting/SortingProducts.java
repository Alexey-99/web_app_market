package by.koroza.zoo_market.service.sorting;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.sorting.impl.comparator.list.product.SortComparatorListProduct;
import by.koroza.zoo_market.service.sorting.impl.comparator.map.SortComparatorMap;

public interface SortingProducts {

	/**
	 * Sort products pets.
	 *
	 * @param list       the list
	 * @param comparator the comparator
	 * @return the list
	 * @throws SortingException the sorting exception
	 */
	public List<Entry<Pet, Long>> sortProductsPets(List<Entry<Pet, Long>> list, SortComparatorListProduct comparator)
			throws SortingException;

	/**
	 * Sort products feeds and other.
	 *
	 * @param list       the list
	 * @param comparator the comparator
	 * @return the list
	 * @throws SortingException the sorting exception
	 */
	public List<Entry<FeedAndOther, Long>> sortProductsFeedsAndOther(List<Entry<FeedAndOther, Long>> list,
			SortComparatorListProduct comparator) throws SortingException;

	/**
	 * Sort products map.
	 *
	 * @param products   the products
	 * @param comparator the comparator
	 * @return the map<? extends abstract product, long>
	 * @throws SortingException the sorting exception
	 */
	public Map<? extends AbstractProduct, Long> sortProductsMap(Map<? extends AbstractProduct, Long> products,
			SortComparatorMap comparator) throws SortingException;

}
