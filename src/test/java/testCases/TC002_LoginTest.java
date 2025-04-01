package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	@Test(groups = {"Sanity","Master"})
	public void verify_Login() {
		logger.info("***** Starting TC002_LoginTest *****");
		try {
		//HomePage
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		//Login
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		//lp.setEmail("Arunavattikota@gmail.com");
		//lp.setPassword("Aruna@123");
		lp.clickLogin();
		
		//MyAccount
		Thread.sleep(1000);
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		Assert.assertTrue(targetPage);//Assert.assertEquals(targetPage,true,"Login failed");
	}
	catch (Exception e) {
		Assert.fail();
	}
		logger.info("***** Finished TC002_LoginTest*****");
		
	}
	
	

}
