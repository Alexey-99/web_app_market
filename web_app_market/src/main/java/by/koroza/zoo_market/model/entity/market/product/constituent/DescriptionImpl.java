package by.koroza.zoo_market.model.entity.market.product.constituent;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DescriptionImpl implements Description {
	private static final String REG_EX_PATTERN_FOR_SPLITE_BY_DESCRIPTION = "\\;\\s";
	private static final String REG_EX_PATTERN_FOR_SPLITE_DESCRIPTION_ON_KEY_AND_VALUE = "\\:\\s";

	private static final Logger log = LogManager.getLogger();

	private static final String KEY__FEED_TYPE = "feedType";
	private static final String KEY__TASTE = "taste";
	private static final String KEY__PACKAGING_TYPE = "packagingType";
	private static final String KEY__WEIGHT = "weight";

	private Map<String, String> descriptions;

	public DescriptionImpl() {
		this.descriptions = new HashMap<>();
		initKeysDescriptions();
	}

	@Override
	public Map<String, String> getDescriptions() {
		return descriptions;
	}

	@Override
	public void setDescriptions(Map<String, String> descriptions) {
		this.descriptions = descriptions;
	}

	@Override
	public void setDescriptions(String description) {
		this.descriptions = parseDescriptions(description);
	}

	/**
	 * add names for descriptions
	 */
	private void initKeysDescriptions() {
		this.descriptions.put(KEY__FEED_TYPE, null);
		this.descriptions.put(KEY__TASTE, null);
		this.descriptions.put(KEY__PACKAGING_TYPE, null);
		this.descriptions.put(KEY__WEIGHT, null);
	}

	@Override
	public void setFeedType(String feedType) {
		this.descriptions.put(KEY__FEED_TYPE, feedType);
	}

	@Override
	public void setTaste(String taste) {
		this.descriptions.put(KEY__TASTE, taste);
	}

	@Override
	public void setPackagingType(String packagingType) {
		this.descriptions.put(KEY__PACKAGING_TYPE, packagingType);
	}

	@Override
	public void setWeight(String weight) {
		this.descriptions.put(KEY__WEIGHT, weight);
	}

	@Override
	public void addDescription(String key, String value) {
		if (!this.descriptions.containsKey(key)) {
			this.descriptions.put(key, value);
		} else {
			// TODO DO YOU SURE THAT YOU WANT CHANGE VALUE BY KEY
			// YES / NO
			this.descriptions.put(key, value);
		}
	}

	@Override
	public void removeKey(String key) {
		if (this.descriptions.containsKey(key)) {
			Map<String, String> descriptions = new HashMap<>();
			for (Map.Entry<String, String> entry : this.descriptions.entrySet()) {
				String descpiptionKey = entry.getKey();
				String descpiptionVal = entry.getValue();
				if (!descpiptionKey.equals(key)) {
					descriptions.put(descpiptionKey, descpiptionVal);
				}
			}
			this.descriptions = descriptions;
		} else {
			log.log(Level.INFO, "descriptions don't have description with name: " + key);
		}
	}

	@Override
	public void removeValue(String key) {
		this.descriptions.remove(key); // return String
	}

	@Override
	public void removeValue(String key, String value) {
		descriptions.remove(key, value); // return boolean
	}

	private Map<String, String> parseDescriptions(String descriptionStr) {
		Map<String, String> descriptionsMap = new HashMap<>();
		if (descriptionStr != null) {
			String[] descriptions = descriptionStr.split(REG_EX_PATTERN_FOR_SPLITE_BY_DESCRIPTION);
			for (String description : descriptions) {
				String[] mapdescriptionString = description
						.split(REG_EX_PATTERN_FOR_SPLITE_DESCRIPTION_ON_KEY_AND_VALUE);
				this.descriptions.put(mapdescriptionString[0], mapdescriptionString[1]);
			}
		}
		return descriptionsMap;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + (this.descriptions != null ? this.descriptions.hashCode() : 1);
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (!this.getClass().equals(object.getClass())) {
			return false;
		}
		DescriptionImpl otherDescription = (DescriptionImpl) object;
		if (this.descriptions == null) {
			if (otherDescription.descriptions != null) {
				return false;
			}
		} else if (!this.descriptions.equals(otherDescription.descriptions)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> entry : this.descriptions.entrySet()) {
			String key = entry.getKey();
			String val = entry.getValue();
			if (val != null && !val.isBlank()) {
				builder.append(key).append(": ").append(val).append("; ");
			}
		}
		return builder.toString();
	}

	public static class DescriptionBuilder {
		private DescriptionImpl description;

		public DescriptionBuilder() {
			this.description = new DescriptionImpl();
		}

		public DescriptionBuilder setDescriptions(Map<String, String> descriptions) {
			this.description.setDescriptions(descriptions);
			return this;
		}

		public DescriptionBuilder setDescriptions(String description) {
			this.description.setDescriptions(description);
			return this;
		}

		public DescriptionBuilder setFeedType(String feedType) {
			this.description.setFeedType(feedType);
			return this;
		}

		public DescriptionBuilder setTaste(String taste) {
			this.description.setTaste(taste);
			return this;
		}

		public DescriptionBuilder setPackagingType(String packagingType) {
			this.description.setPackagingType(packagingType);
			return this;
		}

		public DescriptionBuilder setWeight(String weight) {
			this.description.setWeight(weight);
			return this;
		}

		public DescriptionBuilder addDescription(String key, String value) {
			this.description.addDescription(key, value);
			return this;
		}

		public DescriptionBuilder removeKey(String key) {
			this.description.removeKey(key);
			return this;
		}

		public DescriptionBuilder remove(String key) {
			this.description.removeValue(key);
			return this;
		}

		public DescriptionBuilder remove(String key, String value) {
			this.description.removeValue(key, value);
			return this;
		}

		public Description build() {
			return this.description;
		}
	}
}