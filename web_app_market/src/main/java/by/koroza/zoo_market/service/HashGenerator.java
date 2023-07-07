package by.koroza.zoo_market.service;

import by.koroza.zoo_market.service.exception.HashGeneratorException;

/**
 * The Interface HashGenerator.
 */
public interface HashGenerator {

	/**
	 * Gets the hash.
	 *
	 * @param line the line
	 * @return the hash
	 * @throws HashGeneratorException the hash generator exception
	 */
	public String getHash(String line) throws HashGeneratorException;
}