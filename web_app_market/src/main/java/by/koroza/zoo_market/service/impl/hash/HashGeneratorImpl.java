package by.koroza.zoo_market.service.impl.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import by.koroza.zoo_market.service.HashGenerator;

public class HashGeneratorImpl implements HashGenerator {
	private static final HashGenerator INSTANCE = new HashGeneratorImpl();

	private static final String ALGORITHM__SHA_1 = "sha-1";

	private static final String HEXADECIMAL_FORMAT = "%02x";

	private HashGeneratorImpl() {
	}

	public static HashGenerator getInstance() {
		return INSTANCE;
	}

	@Override
	public String getHash(String line) {
		String hashResult = null;
		if (line != null) {
			try {
				MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM__SHA_1);
				messageDigest.update(line.getBytes());
				byte[] digestedBytes = messageDigest.digest();
				hashResult = conventBytesToString(digestedBytes);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
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