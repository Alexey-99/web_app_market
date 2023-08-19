package by.koroza.zoo_market.service.sorting.user.impl.comparator.impl;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.sorting.user.impl.comparator.SortComparatorUsers;

/**
 * The Class SortComparatorUsersByIdThenTimeAtCreating.
 */
public class SortComparatorUsersById implements SortComparatorUsers {

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(User o1, User o2) {
		return (int) (o1.getId() - o2.getId());
	}
}