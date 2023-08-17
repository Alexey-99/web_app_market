package by.koroza.zoo_market.main;

import by.koroza.zoo_market.service.exception.HashGeneratorException;
import by.koroza.zoo_market.service.impl.hash.HashGeneratorImpl;

public class Main {

	public static void main(String[] args) throws HashGeneratorException {
		System.out.println(HashGeneratorImpl.getInstance().getHash("12345"));
		System.out.println(HashGeneratorImpl.getInstance().getHash("12345"));
		System.out.println(HashGeneratorImpl.getInstance().getHash("12345"));
		System.out.println(HashGeneratorImpl.getInstance().getHash("12345"));
	}

}
