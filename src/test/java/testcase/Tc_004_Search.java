package testcase;
import org.testng.Assert;
import org.testng.annotations.Test;


import pageObjects.Homepage;
import pageObjects.SearchPage;

import testBase.Baseclass;


public class Tc_004_Search extends Baseclass
{
	@Test(groups= {"Master"})
	public void verify_pruductSearch() throws InterruptedException {
		logger.info(" Starting TC_004_SearchProductTest ");

		try {
			
			Homepage hm=new Homepage(driver);
			
			//hm.enterProductName("iPhone");
			hm.entertxt("mac");
			
			hm.clickonsearchsymbol();
			
			SearchPage sp=new SearchPage(driver);
			sp.isProductExist("MacBook");

			Assert.assertEquals(sp.isProductExist("MacBook"),true);

		} catch (Exception e) {
			Assert.fail();
		}

		logger.info(" Finished TC_004_SearchProductTest ");

	}

}
