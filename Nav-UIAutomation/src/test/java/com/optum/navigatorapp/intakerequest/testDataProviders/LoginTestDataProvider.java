package com.optum.navigatorapp.intakerequest.testDataProviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.optum.automation.coreframework.dataprovider.TestData;
import com.optum.navigatorapp.intakerequest.databeans.Login;

public class LoginTestDataProvider {
	@DataProvider(name="ValidLogin")
	public static Iterator<Object[]> ValidLogin(){
		
		return TestData.getData(Login.class, "ValidLogin");
		
	}
	@DataProvider(name="InvalidLogin")
	public static Iterator<Object[]> InValidLogin(){
		
		return TestData.getData(Login.class, "InvalidLogin");
		
	}
}
