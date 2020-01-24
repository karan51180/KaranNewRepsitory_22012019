package com.optum.navigatorapp.intakerequest.testclasses;

import org.testng.annotations.Test;

import com.optum.automation.coreframework.baseclasses.BaseUITestCase;
import com.optum.navigatorapp.intakerequest.databeans.Login;
import com.optum.navigatorapp.intakerequest.pageObjects.LoginPage;
import com.optum.navigatorapp.intakerequest.testDataProviders.LoginTestDataProvider;

public class LoginTest extends BaseUITestCase {

	private LoginPage lgnPage;
	
	@Test(dataProvider="ValidLogin", dataProviderClass=LoginTestDataProvider.class)
	public void testValidLogin(Login lgn)
	{
		//lgnPage.doLogin(lgn);
	}
	@Test(dataProvider="InvalidLogin", dataProviderClass=LoginTestDataProvider.class)
	public void testInValidLogin(Login lgn)
	{
		//lgnPage.doLogin(lgn);
	}
	
	
}
