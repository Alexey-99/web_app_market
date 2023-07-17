package by.koroza.zoo_market.service.validation.impl.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.koroza.zoo_market.service.validation.UserValidation;

/**
 * @author Alexey
 */
public class UserValidationImplTest {
	private static Logger log = LogManager.getLogger();
	private static UserValidation userValidation;

	/**
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		userValidation = UserValidationImpl.getInstance();
	}

	@DataProvider(name = "ProviderExpectedEmails")
	public Object[][] createEmails() {
		return new Object[][] { { null, false }, { "", false }, { "example@gmail", false }, { "example@.com", false },
				{ "example@gmail.com", true } };
	}

	/**
	 * Test method for
	 * {@link by.koroza.zoo_market.service.validation.impl.user.UserValidationImpl#validEmail(java.lang.String)}.
	 */
	@Test(dataProvider = "ProviderExpectedEmails", description = "This method check to email. If email incorrect - return false, if correct email - return true")
	public void testValidEmail(String email, boolean expected) {
		boolean actual = userValidation.validEmail(email);
		log.info(email);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for
	 * {@link by.koroza.zoo_market.service.validation.impl.user.UserValidationImpl#validLogin(java.lang.String)}.
	 */
	@Test
	public void testValidLogin() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link by.koroza.zoo_market.service.validation.impl.user.UserValidationImpl#isRepeatUserLogin(java.lang.String)}.
	 */
	@Test
	public void testIsRepeatUserLogin() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link by.koroza.zoo_market.service.validation.impl.user.UserValidationImpl#validPassword(java.lang.String)}.
	 */
	@Test
	public void testValidPassword() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 */
	@AfterClass
	public static void tearDownAfterClass() {
		userValidation = null;
	}
}