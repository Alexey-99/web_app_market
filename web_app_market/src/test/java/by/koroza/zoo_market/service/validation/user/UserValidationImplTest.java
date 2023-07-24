package by.koroza.zoo_market.service.validation.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.koroza.zoo_market.service.validation.UserValidation;
import by.koroza.zoo_market.service.validation.impl.user.UserValidationImpl;

/**
 * @author Alexey
 */
public class UserValidationImplTest {
	@SuppressWarnings("unused")
	private static Logger log = LogManager.getLogger();
	private static UserValidation userValidation;

	/**
	 * Sets the up before class.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		userValidation = UserValidationImpl.getInstance();
	}

	@DataProvider(name = "ProviderExpectedEmails")
	public Object[][] createEmails() {
		return new Object[][] { { null, false }, { "", false }, { "email@gmail", false }, { "email@.com", false },
				{ "email@gmail.com", true } };
	}

	@DataProvider(name = "ProviderExpectedLogins")
	public Object[][] createLogins() {
		return new Object[][] { { null, false }, { "", false }, { "login", true } };
	}

	@DataProvider(name = "ProviderExpectedPasswords")
	public Object[][] createPasswords() {
		return new Object[][] { { null, false }, { "", false }, { "password", true } };
	}

	/**
	 * Test method for
	 * {@link by.koroza.zoo_market.service.validation.impl.user.UserValidationImpl#validEmail(java.lang.String)}.
	 */
	@Test(dataProvider = "ProviderExpectedEmails", description = "This method check to email. If email incorrect - return false, if correct email - return true")
	public void testValidEmail(String email, boolean expected) {
		boolean actual = userValidation.validEmail(email);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for
	 * {@link by.koroza.zoo_market.service.validation.impl.user.UserValidationImpl#validLogin(java.lang.String)}.
	 */
	@Test(dataProvider = "ProviderExpectedLogins", description = "This method check to login. If login incorrect - return false, if correct login - return true")
	public void testValidLogin(String login, boolean expected) {
		boolean actual = userValidation.validLogin(login);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for
	 * {@link by.koroza.zoo_market.service.validation.impl.user.UserValidationImpl#isRepeatUserLogin(java.lang.String)}.
	 */
	@Test
	public void testIsRepeatUserLogin() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.koroza.zoo_market.service.validation.impl.user.UserValidationImpl#validPassword(java.lang.String)}.
	 */
	@Test(dataProvider = "ProviderExpectedPasswords", description = "This method check to password. If password incorrect - return false, if correct password - return true")
	public void testValidPassword(String password, boolean expected) {
		boolean actual = userValidation.validPassword(password);
		assertEquals(actual, expected);
	}

	/**
	 */
	@AfterClass
	public static void tearDownAfterClass() {
		userValidation = null;
	}
}