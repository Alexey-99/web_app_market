package by.koroza.zoo_market.model.entity.market.product.constituent;

import java.util.Map;

public interface Description {

	public Map<String, String> getDescriptions();

	public void setDescriptions(Map<String, String> descriptions);

	public void setDescriptions(String description);

	/**
	 * add value for description with name 'feedType'
	 */
	public void setFeedType(String feedType);

	/**
	 * add value for description with name 'taste'
	 */
	public void setTaste(String taste);

	/**
	 * add value for description with name 'packagingType'
	 */
	public void setPackagingType(String packagingType);

	/**
	 * add value for description with name 'weight'
	 */
	public void setWeight(String weight);

	/**
	 * add name and value to descriptions
	 */
	public void addDescription(String key, String value);

	/**
	 * remove name description for descriptions
	 */
	public void removeKey(String key);

	/**
	 * remove value from descriptions with input key, if having this key
	 */
	public void removeValue(String key);

	/**
	 * remove value from descriptions with input key, if having input key with input
	 * value
	 */
	public void removeValue(String key, String value);

	@Override
	public int hashCode();

	@Override
	public boolean equals(Object object);

	@Override
	public String toString();
}