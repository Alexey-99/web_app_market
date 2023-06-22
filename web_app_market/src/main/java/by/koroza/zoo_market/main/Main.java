/* 9. Система Интернет-магазин. 
 * Администратор осуществляет ведение каталога Товаров. 
 * Клиент делает и оплачивает Заказ на Товары. 
 * Администратор может занести неплательщиков в «черный список». 
 * */
package by.koroza.zoo_market.main;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.product.Pet;

public class Main {
	@SuppressWarnings("unused")
	private static final Logger log = LogManager.getLogger();

	public static void main(String[] args) {

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