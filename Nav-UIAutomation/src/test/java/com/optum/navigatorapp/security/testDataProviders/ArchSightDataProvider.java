package com.optum.navigatorapp.security.testDataProviders;

import java.util.Iterator;
import org.testng.annotations.DataProvider;
import com.optum.automation.coreframework.dataprovider.TestData;
import com.optum.navigatorapp.security.databeans.ArchSight;


public class ArchSightDataProvider {
	@DataProvider(name = "ValidRequestData")
	public static Iterator<Object[]> ValidData() {

		return TestData.getData(ArchSight.class, "ValidRequestData");

	}

	@DataProvider(name = "InValidData")
	public static Iterator<Object[]> InValidData() {

		return TestData.getData(ArchSight.class, "InValidData");

	}
}
