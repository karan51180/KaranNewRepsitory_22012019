package com.optum.navigatorapp.loadbalance.testDataProviders;

import java.util.Iterator;
import org.testng.annotations.DataProvider;
import com.optum.automation.coreframework.dataprovider.TestData;
import com.optum.navigatorapp.loadbalance.databeans.LoadBalanceManual;

public class LoadBalanceManualTestDataProvider {
		
			@DataProvider(name = "ValidLBRequestData")
			public static Iterator<Object[]> ValidData() {

				return TestData.getData(LoadBalanceManual.class, "ValidLBRequestData");

			}
			@DataProvider(name = "InvalidLBRequestData")
			public static Iterator<Object[]> InValidData() {

				return TestData.getData(LoadBalanceManual.class, "InvalidLBRequestData");

			}

}
