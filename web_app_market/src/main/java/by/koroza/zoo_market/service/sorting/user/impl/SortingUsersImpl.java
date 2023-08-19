package by.koroza.zoo_market.service.sorting.user.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.sorting.user.SortingUsers;
import by.koroza.zoo_market.service.sorting.user.impl.comparator.SortComparatorUsers;

public class SortingUsersImpl implements SortingUsers {

	private static Logger log = LogManager.getLogger();

	private static final SortingUsers INSTANCE = new SortingUsersImpl();

	private SortingUsersImpl() {
	}

	public static SortingUsers getInstance() {
		return INSTANCE;
	}

	@Override
	public List<User> sortUsers(List<User> list, SortComparatorUsers comparator) throws SortingException {
		List<User> listSorting = new LinkedList<>();
		if (list != null) {
			listSorting.addAll(list);
			if (comparator != null) {
				listSorting.sort(comparator);
			} else {
				log.log(Level.ERROR, "comparator is null");
				throw new SortingException("comparator is null");
			}
		} else {
			log.log(Level.ERROR, "list is null");
			throw new SortingException("list is null");
		}
		return listSorting;
	}
}