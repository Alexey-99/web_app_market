package by.koroza.zoo_market.service.validation.impl.user;

import static org.testng.Assert.fail;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.koroza.zoo_market.service.validation.UserValidation;

/**
 * @author Alexey
 */
public class UserValidationImplTest {
	private static UserValidation userValidation;

	/**
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		userValidation = UserValidationImpl.getInstance();
	}

	/**
	 * Test method for
	 * {@link by.koroza.zoo_market.service.validation.impl.user.UserValidationImpl#validEmail(java.lang.String)}.
	 */
	@Test(dataProvider = "createrArrayLines", description = "This method check to read line. If lines incorrect - return false, if correct lines - return true")
	public void testValidEmail() {
		userValidation.validEmail(null);
		fail("Not yet implemented"); // TODO
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