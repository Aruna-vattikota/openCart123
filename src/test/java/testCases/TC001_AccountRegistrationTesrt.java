package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTesrt extends BaseClass{

		@Test(groups={"Regression","Master"})
	public  void verify_account_registration() {
			logger.info("***** Starting TC001_AccountRegistrationTesrt ");
			try {
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on MyAccount link");
		hp.clickRegister();
		logger.info("clicked on Register link");
		AccountRegistrationPage regPage= new AccountRegistrationPage(driver);
		logger.info("Providing Customer details");
		regPage.setFirstName(randomeString().toUpperCase());
		regPage.setLastName(randomeString().toUpperCase());
		regPage.setEmail(randomeString()+"@gmail.com");
		regPage.setTelePhone(randomeNumbers());
		
		 String password=randomeAlphaNumeric();
		 regPage.setPassword(password);
		 regPage.setConfirmpwd(password);
		regPage.setprivacyPolicy();
		regPage.clickContinue();
		logger.info("Validating expected message");
		String confmsg=regPage.getConfirmationMsg();
		Assert.assertEquals(confmsg,"Your Account Has Been Created!");
		} catch (Exception e) {
			logger.error("Test Failed");
			logger.debug("Debug.logs..");
			Assert.fail();
		}
			logger.info("***** Finished TC001_AccountRegistrationTesrt ");
			
	}
	}
