package by.koroza.zoo_market.service.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.exception.SortingException;

public class SortingMapProducts {
	private static final SortingMapProducts INSTANCE = new SortingMapProducts();

	private SortingMapProducts() {
	}

	public static SortingMapProducts getInstance() {
		return INSTANCE;
	}

	public Map<? extends AbstractProduct, Long> sortProductsMap(Map<? extends AbstractProduct, Long> map,
			Comparator<Entry<? extends AbstractProduct, Long>> comparator) throws SortingException {
		Map<AbstractProduct, Long> sortedMap = new LinkedHashMap<>();
		if (map != null) {
			List<Entry<? extends AbstractProduct, Long>> listEntry = new ArrayList<>();
			map.entrySet().stream().forEach(entry -> listEntry.add(entry));
			listEntry.sort(comparator);
			listEntry.stream().forEach(entry -> sortedMap.put((AbstractProduct) entry.getKey(), entry.getValue()));
		} else {
			throw new SortingException("map is null");
		}
		return sortedMap;
	}
}