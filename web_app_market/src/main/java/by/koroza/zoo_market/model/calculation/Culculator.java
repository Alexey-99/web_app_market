package by.koroza.zoo_market.model.calculation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Culculator {
	private static final Culculator INSTANCE = new Culculator();

	private Culculator() {
	}

	public static Culculator getInstance() {
		return INSTANCE;
	}

	public long calcUpdateDateTimeInSeconds(LocalDateTime productCreateInBD) {
		long seconds = ChronoUnit.SECONDS.between(productCreateInBD, LocalDateTime.now());
		return seconds;
	}

	public double calcProcentFromSum(double sum, double procent) {
		return sum * procent / 100;
	}
}