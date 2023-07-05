package by.koroza.zoo_market.service;

public interface EmailSender {

	public boolean emailSend(String subject, String text, String... internetAddressesTo);
}