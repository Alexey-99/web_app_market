package by.koroza.zoo_market.service.sorting.user;

import java.util.List;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.sorting.user.impl.comparator.SortComparatorUsers;

public interface SortingUsers {

	public List<User> sortUsers(List<User> list, SortComparatorUsers comparator) throws SortingException;
}