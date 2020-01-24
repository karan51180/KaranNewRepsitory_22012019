package com.optum.navigatorapp.intakerequest.testDataProviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.optum.automation.coreframework.dataprovider.TestData;
import com.optum.navigatorapp.intakerequest.databeans.EngineeringAndSupport;
import com.optum.navigatorapp.intakerequest.databeans.VendorAndThridParty;

public class VTPTestDataProvider  {

	@DataProvider(name = "ValidRequestData")
	public static Iterator<Object[]> ValidData() {

		return TestData.getData(VendorAndThridParty.class, "ValidRequestData");

	}
	
	
	@DataProvider(name = "NwMNGRAssignment")
	public static Iterator<Object[]> NtwMngrAssignment() {

		return TestData.getData(VendorAndThridParty.class, "NwMNGRAssignment");

	}
	
	@DataProvider(name = "InValidData")
	public static Iterator<Object[]> InValidData() {

		return TestData.getData(VendorAndThridParty.class, "InValidData");

	}
}
