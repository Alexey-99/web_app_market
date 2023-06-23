/* 9. Система Интернет-магазин. 
 * Администратор осуществляет ведение каталога Товаров. 
 * Клиент делает и оплачивает Заказ на Товары. 
 * Администратор может занести неплательщиков в «черный список». 
 * */
package by.koroza.zoo_market.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.product.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;

public class Main {
	@SuppressWarnings("unused")
	private static final Logger log = LogManager.getLogger();

	public static void main(String[] args) throws ServiceException {
		Map<Pet, Long> productPets = ProductPetServiceImpl.getInstance().getAllProductsPetsAndNumberOfUnits();
		Map<FeedAndOther, Long> productFeedsAndOther = ProductFeedsAndOtherServiceImpl.getInstance()
				.getAllProductsFeedAndOtherAndNumberOfUnits();
		Map<AbstractProduct, Long> products = new HashMap<>(productPets);
		products.putAll(productFeedsAndOther);
		for (Map.Entry<AbstractProduct, Long> entry : products.entrySet()) {
			AbstractProduct key = entry.getKey();
			if (key.getClass().equals(Pet.class)) {
				System.out.println(key.getClass());
			} else {

			}

		}
	}

	public static Map<Pet, Long> sortingMap(Map<Pet, Long> map) {
		List<Entry<Pet, Long>> list = new ArrayList<>();
		map.entrySet().stream().forEach(entry -> list.add(entry));
		list.sort(new Comparator<Entry<Pet, Long>>() {
			@Override
			public int compare(Entry<Pet, Long> o1, Entry<Pet, Long> o2) {
				return o1.getKey().getBreed().compareTo(o2.getKey().getBreed());
			}
		});
		Map<Pet, Long> sortMap = new LinkedHashMap<>();
		list.stream().forEach(entry -> sortMap.put(entry.getKey(), entry.getValue()));
		return sortMap;
	}
}