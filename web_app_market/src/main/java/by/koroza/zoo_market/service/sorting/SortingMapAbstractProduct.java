package by.koroza.zoo_market.service.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.exception.SortingException;

public class SortingMapAbstractProduct {
	private static final SortingMapAbstractProduct INSTANCE = new SortingMapAbstractProduct();

	private SortingMapAbstractProduct() {
	}

	public static SortingMapAbstractProduct getInstance() {
		return INSTANCE;
	}

	public Map<? extends AbstractProduct, Long> sortMapById(Map<? extends AbstractProduct, Long> map)
			throws SortingException {
		Map<AbstractProduct, Long> sortedMap = new LinkedHashMap<>();
		if (map != null) {
			List<Entry<? extends AbstractProduct, Long>> listEntry = new ArrayList<>();
			map.entrySet().stream().forEach(entry -> listEntry.add(entry));
			listEntry.sort(new Comparator<Entry<? extends AbstractProduct, Long>>() {
				@Override
				public int compare(Entry<? extends AbstractProduct, Long> o1,
						Entry<? extends AbstractProduct, Long> o2) {
					return (int) (o1.getKey().getId() - o2.getKey().getId());
				}
			});
			listEntry.stream().forEach(entry -> sortedMap.put((AbstractProduct) entry.getKey(), entry.getValue()));
		} else {
			throw new SortingException("map is null");
		}
		return sortedMap;
	}

}
