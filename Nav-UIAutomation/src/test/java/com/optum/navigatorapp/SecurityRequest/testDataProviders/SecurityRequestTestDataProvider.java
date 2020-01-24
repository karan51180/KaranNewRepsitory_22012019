package com.optum.navigatorapp.SecurityRequest.testDataProviders;

import java.util.Iterator;
import org.testng.annotations.DataProvider;
import com.optum.automation.coreframework.dataprovider.TestData;

import com.optum.navigatorapp.SecurityRequest.databeans.SecurityRequest;
import com.optum.navigatorapp.intakerequest.databeans.EngineeringAndSupport;

public class SecurityRequestTestDataProvider {
	@DataProvider(name = "ValidRequestData")
	public static Iterator<Object[]> ValidData() {

		return TestData.getData(SecurityRequest.class, "ValidRequestData");
	}

	@DataProvider(name = "InValidData")
	public static Iterator<Object[]> InValidData() {

		return TestData.getData(SecurityRequest.class, "InValidData");

	}
	@DataProvider(name = "ISOAEMNGRASSIGNMENT")
 public static Iterator<Object[]> NtwMngrAssignment() {

  return TestData.getData(SecurityRequest.class, "ISOAEMNGRASSIGNMENT");

 }



}
