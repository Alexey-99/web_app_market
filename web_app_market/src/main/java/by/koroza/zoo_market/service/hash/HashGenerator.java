package by.koroza.zoo_market.service.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
	private static final HashGenerator INSTANCE = new HashGenerator();

	private static final String ALGORITHM__SHA_1 = "sha-1";

	private static final String HEXADECIMAL_FORMAT = "%02x";

	private HashGenerator() {
	}

	public static HashGenerator getInstance() {
		return INSTANCE;
	}

	public String getHash(String line) {
		String hashResult = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM__SHA_1);
			messageDigest.update(line.getBytes());
			byte[] digestedBytes = messageDigest.digest();
			hashResult = conventBytesToString(digestedBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashResult;
	}

	private static String conventBytesToString(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			builder.append(String.format(HEXADECIMAL_FORMAT, b));
		}
		return builder.toString();
	}
}