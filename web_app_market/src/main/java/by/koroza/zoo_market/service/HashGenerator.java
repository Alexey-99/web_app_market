package by.koroza.zoo_market.service;

/**
 * The Interface HashGenerator.
 */
public interface HashGenerator {

	/**
	 * Get the hash from string.
	 *
	 * @param line the line
	 * @return the hash
	 */
	public String getHash(String line);
}