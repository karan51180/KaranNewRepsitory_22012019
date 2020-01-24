package com.optum.navigatorapp.enterpriseStorageServices.testDataProviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.optum.automation.coreframework.dataprovider.TestData;
import com.optum.navigatorapp.enterpriseStorageServices.databeans.NasShareAllocation;


public class NasShareTestDataProvider {
	@DataProvider(name = "ValidNasData")
	public static Iterator<Object[]> ValidData() {

		return TestData.getData(NasShareAllocation.class, "ValidNasData");

	}
//
//	@DataProvider(name = "InValidData")
//	public static Iterator<Object[]> InValidData() {
//
//		return TestData.getData(NasShareAllocation.class, "InValidData");
//
//	}
}
