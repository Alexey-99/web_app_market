package by.koroza.zoo_market.service;

/**
 * The Interface EmailSender.
 */
public interface EmailSender {

	/**
	 * Email send.
	 *
	 * @param subject             the subject
	 * @param text                the text
	 * @param internetAddressesTo the Internet addresses to
	 * @return true, if successful
	 */
	public boolean emailSend(String subject, String text, String... internetAddressesTo);
}