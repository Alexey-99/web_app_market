package by.koroza.zoo_market.service.impl.sender;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.service.EmailSender;
import by.koroza.zoo_market.service.exception.ServiceException;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * The Class EmailSenderImpl.
 */
public class EmailSenderImpl implements EmailSender {
	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final EmailSender INSTANCE = new EmailSenderImpl();

	/** The Constant FILE_PATH_EMAIL_PROPERTIES. */
	private static final String FILE_PATH_EMAIL_PROPERTIES = "email_properties/mail_property.txt";

	/** The Constant FILE_PATH_OTHER_PROPERTIES. */
	private static final String FILE_PATH_OTHER_PROPERTIES = "email_properties/other_property.txt";

	/** The Constant PROPERTY_KEY_INTERNET_ADDRESS_GMAIL_FROM. */
	private static final String PROPERTY_KEY_INTERNET_ADDRESS_GMAIL_FROM = "internet_address_gmail_from";

	/** The Constant PROPERTY_KEY_INTERNET_ADDRESS_GMAIL_PASSWORD_FROM. */
	private static final String PROPERTY_KEY_INTERNET_ADDRESS_GMAIL_PASSWORD_FROM = "internet_address_gmail_password_from";

	/** The Constant PROPERTY_KEY_PASSWORD_SMTP. */
	private static final String PROPERTY_KEY_PASSWORD_SMTP = "password_smtp";

	/** The Constant PROPERTY_KEY_INTERNET_ADDRESS_FROM. */
	private static final String PROPERTY_KEY_INTERNET_ADDRESS_FROM = "internet_address_from";

	/**
	 * Instantiates a new email sender impl.
	 */
	private EmailSenderImpl() {
	}

	/**
	 * Get the single instance of EmailSenderImpl.
	 *
	 * @return single instance of EmailSenderImpl
	 */
	public static EmailSender getInstance() {
		return INSTANCE;
	}

	/**
	 * Email send.
	 *
	 * @param subject             the subject
	 * @param text                the text
	 * @param internetAddressesTo the internet addresses to
	 * @return true, if successful
	 */
	@Override
	public boolean emailSend(String subject, String text, String... internetAddressesTo) {
		boolean flag = true;
		try {
			Properties emailProperties = loadEmailPropertiesFile();
			Properties otherProperties = loadOtherPropertiesFile();
			Session mailSession = Session.getDefaultInstance(emailProperties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(
							otherProperties.getProperty(PROPERTY_KEY_INTERNET_ADDRESS_GMAIL_FROM),
							otherProperties.getProperty(PROPERTY_KEY_PASSWORD_SMTP));
				}
			});
			MimeMessage message = createEmailMessage(otherProperties.getProperty(PROPERTY_KEY_INTERNET_ADDRESS_FROM),
					mailSession, subject, text, internetAddressesTo);
			sendMessage(mailSession, message,
					otherProperties.getProperty(PROPERTY_KEY_INTERNET_ADDRESS_GMAIL_PASSWORD_FROM));
		} catch (ServiceException e) {
			log.log(Level.ERROR, e.getMessage());
			flag = false;
		}
		return flag;
	}

	/**
	 * Load email properties file.
	 *
	 * @return the properties
	 * @throws ServiceException the service exception
	 */
	private Properties loadEmailPropertiesFile() throws ServiceException {
		Properties properties = new Properties();
		try {
			InputStream inputStream = EmailSenderImpl.class.getClassLoader()
					.getResourceAsStream(FILE_PATH_EMAIL_PROPERTIES);
			properties.load(inputStream);
		} catch (IOException e) {
			throw new ServiceException(e);
		}
		return properties;
	}

	/**
	 * Load other properties file.
	 *
	 * @return the properties
	 * @throws ServiceException the service exception
	 */
	private Properties loadOtherPropertiesFile() throws ServiceException {
		Properties properties = new Properties();
		try {
			InputStream inputStream = EmailSenderImpl.class.getClassLoader()
					.getResourceAsStream(FILE_PATH_OTHER_PROPERTIES);
			properties.load(inputStream);
		} catch (IOException e) {
			throw new ServiceException(e);
		}
		return properties;
	}

	/**
	 * Create the email message.
	 *
	 * @param internetAddressFrom the internet address from
	 * @param mailSession         the mail session
	 * @param subject             the subject
	 * @param text                the text
	 * @param internetAddressesTo the internet addresses to
	 * @return the mime message
	 * @throws ServiceException the service exception
	 */
	private MimeMessage createEmailMessage(String internetAddressFrom, Session mailSession, String subject, String text,
			String... internetAddressesTo) throws ServiceException {
		MimeMessage message = new MimeMessage(mailSession);
		try {
			message.setFrom(new InternetAddress(internetAddressFrom));
			for (String address : internetAddressesTo) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
			}
			message.setSubject(subject);
			message.setText(text);
		} catch (MessagingException e) {
			throw new ServiceException(e);
		}
		return message;
	}

	/**
	 * Send message.
	 *
	 * @param mailSession the mail session
	 * @param message     the message
	 * @param password    the password
	 * @throws ServiceException the service exception
	 */
	private void sendMessage(Session mailSession, MimeMessage message, String password) throws ServiceException {
		Transport tr;
		try {
			tr = mailSession.getTransport();
			tr.connect(null, password);
			tr.sendMessage(message, message.getAllRecipients());
			tr.close();
		} catch (MessagingException e) {
			throw new ServiceException(e);
		}
	}
}