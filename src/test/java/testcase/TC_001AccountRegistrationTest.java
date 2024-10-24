package testcase;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationpage;
import pageObjects.Homepage;
import testBase.Baseclass;

public class TC_001AccountRegistrationTest extends Baseclass
{
	
	
	@Test(groups= {"Regression","Master"})
	public void test()
	{
		
		logger.info("************ Starting TC_001AccountRegistrationTest*********");
		
	try {	
		Homepage pg= new Homepage(driver);
		pg.clickmyaccount();
		logger.info("Clicked on MyACcount...");
		pg.clickregister();
		logger.info("Clicked on Register linnk...");
		AccountRegistrationpage regpage= new AccountRegistrationpage(driver);
		
		/*regpage.setfirstname("karishma");  //this data is used for once only....using same data gives email already exists
		regpage.setlastname("shaik");
		regpage.setemail("yfg123@gmail.com");
		regpage.settelephone("89956789");
		regpage.setpwd("xyz123");
		regpage.setconfirmpassword("xyz123");
		regpage.setprivacypolicy();
		regpage.clickcontinue();
		String conmsg=regpage.getconfirmationmsg();
		Assert.assertEquals(conmsg, "Your Account Has Been Created!");*/
	
//to avoid element already exist we use the randomstringutils from java		
		
		logger.info("providing Customer Details...");
		
		regpage.setfirstname(randomstring().toUpperCase());
		regpage.setlastname(randomstring().toUpperCase());
		regpage.setemail(randomstring()+"@gmail.com");
		regpage.settelephone(randomnumeric());
		
		String pwd = randomnumalpha();
		regpage.setpwd(pwd);
		regpage.setconfirmpassword(pwd);
		regpage.setprivacypolicy();
		regpage.clickcontinue();
		logger.info("Validating expected message...");
		
		String conmsg=regpage.getconfirmationmsg();
		if(conmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test failed");
			logger.debug("Debug logs...");
			Assert.assertFalse(false);
		}
	}
	
	
	catch(Exception e)
	{
		
		Assert.fail();
	}
	logger.info("************ Finish TC_001AccountRegistrationTest*********");
	
	}	
	
	
	
	public String randomstring()
	{
		@SuppressWarnings("deprecation")
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	
	public String randomnumeric()
	{
		@SuppressWarnings("deprecation")
		String generatednumber = RandomStringUtils.randomNumeric(9);
		return generatednumber;
	}
	
	
	public String randomnumalpha()
	{
		@SuppressWarnings("deprecation")
		String generatednumalpha = RandomStringUtils.randomAlphanumeric(6);
		return generatednumalpha;
	}
	
}
