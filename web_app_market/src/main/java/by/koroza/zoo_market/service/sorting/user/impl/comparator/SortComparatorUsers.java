package by.koroza.zoo_market.service.sorting.user.impl.comparator;

import java.util.Comparator;

import by.koroza.zoo_market.model.entity.user.User;

/**
 * The Interface SortComparatorListProduct.
 */
@FunctionalInterface
public interface SortComparatorUsers extends Comparator<User> {

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(User o1, User o2);
}