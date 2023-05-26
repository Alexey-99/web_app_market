package by.koroza.zoo_market.service.generator;

import java.util.Random;

public class GenerationVeriicationCode {
	private static final GenerationVeriicationCode INSTANCE = new GenerationVeriicationCode();

	private static final char[] DATE_CHARS = { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f',
			'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O',
			'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', '_'};

	private static final int MAX_LENGTH_CODE = 10;
	private static final int MIN_LENGTH_CODE = 2;
	private static final int INDEX_FIRST_ELEMENT = 0;

	private GenerationVeriicationCode() {
	}

	public static GenerationVeriicationCode getInstance() {
		return INSTANCE;
	}

	public String getGeneratedCode() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < randomNum(MIN_LENGTH_CODE, MAX_LENGTH_CODE); i++) {
			builder.append(DATE_CHARS[randomNum(INDEX_FIRST_ELEMENT, DATE_CHARS.length)]);
		}
		return builder.toString();
	}

	private int randomNum(int min, int max) {
		Random rand = new Random();
		int result = 0;
		do {
			result = rand.nextInt(max) + min;
		} while (result < min || result > max);
		return result;
	}
}