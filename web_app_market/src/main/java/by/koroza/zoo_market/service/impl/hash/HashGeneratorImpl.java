package by.koroza.zoo_market.service.impl.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.service.HashGenerator;
import by.koroza.zoo_market.service.exception.HashGeneratorException;

/**
 * The Class HashGeneratorImpl.
 */
public class HashGeneratorImpl implements HashGenerator {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final HashGenerator INSTANCE = new HashGeneratorImpl();

	/** The Constant ALGORITHM__SHA_1. */
	private static final String ALGORITHM__SHA_1 = "sha-1";

	/** The Constant HEXADECIMAL_FORMAT. */
	private static final String HEXADECIMAL_FORMAT = "%02x";

	/**
	 * Instantiates a new hash generator impl.
	 */
	private HashGeneratorImpl() {
	}

	/**
	 * Get the single instance of HashGeneratorImpl.
	 *
	 * @return single instance of HashGeneratorImpl
	 */
	public static HashGenerator getInstance() {
		return INSTANCE;
	}

	/**
	 * Get the hash.
	 *
	 * @param line the line
	 * @return the hash
	 * @throws HashGeneratorException the hash generator exception
	 */
	@Override
	public String getHash(String line) throws HashGeneratorException {
		String hashResult = null;
		if (line != null) {
			try {
				MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM__SHA_1);
				messageDigest.update(line.getBytes());
				byte[] digestedBytes = messageDigest.digest();
				hashResult = conventBytesToString(digestedBytes);
			} catch (NoSuchAlgorithmException e) {
				log.log(Level.ERROR, e.getMessage());
				throw new HashGeneratorException(e);
			}
		}
		return hashResult;
	}

	/**
	 * Convent bytes to string.
	 *
	 * @param bytes the bytes
	 * @return the string
	 */
	private static String conventBytesToString(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			builder.append(String.format(HEXADECIMAL_FORMAT, b));
		}
		return builder.toString();
	}
}