package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProvider;

/*
 Data is valid-login success-test pass-logout
 data  is valid--login failed-test fail
 Data is invalid-login success-test fail-logout
 data  is invalid--login failed-test pass
 
 */
public class TC003_LoginDDT extends BaseClass{
	@Test(dataProvider="LoginData",dataProviderClass=DataProvider.class,groups="DataDriven")
	public void verifyTC003_LoginDDT(String email,String pwd,String exp) {
		logger.info("**** starting TC003_LoginDDT*****");
		try {
		//HomePage
				HomePage hp= new HomePage(driver);
				hp.clickMyAccount();
				hp.clickLogin();
				//Login
				LoginPage lp=new LoginPage(driver);
				lp.setEmail(email);
				lp.setPassword(pwd);
				//MyAccount
				//Thread.sleep(1000);
				MyAccountPage macc=new MyAccountPage(driver);
				boolean targetPage=macc.isMyAccountPageExists();
				if(exp.equalsIgnoreCase("Valid")) {
					if(targetPage==true) {
						macc.clickLogout();
						Assert.assertTrue(true);
					}
					else {
						Assert.assertTrue(false);
					}
					
				}
				if(exp.equalsIgnoreCase("InValid")) {
					if(targetPage==true) {
						macc.clickLogout();
						Assert.assertTrue(false);
					}
					else {
						Assert.assertTrue(true);
					}
					
				}
				}
		catch (Exception e) {
			Assert.fail();
		}
				logger.info("**** finished TC003_LoginDDT*****");	

	}

}
