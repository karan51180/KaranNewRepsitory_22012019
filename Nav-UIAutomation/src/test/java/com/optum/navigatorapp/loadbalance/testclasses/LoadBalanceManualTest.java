package com.optum.navigatorapp.loadbalance.testclasses;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import com.optum.automation.coreframework.baseclasses.BaseUITestCase;
import com.optum.automation.coreframework.utils.Log;
import com.optum.navigatorapp.base.pageobjects.DashBoardPage;
import com.optum.navigatorapp.base.pageobjects.HomePage;
import com.optum.navigatorapp.base.pageobjects.LoadRole;
import com.optum.navigatorapp.loadbalance.databeans.LoadBalanceManual;
import com.optum.navigatorapp.loadbalance.pageObjects.LoadBalanceManualPage;
import com.optum.navigatorapp.loadbalance.testDataProviders.LoadBalanceManualTestDataProvider;

public class LoadBalanceManualTest extends BaseUITestCase {

	private HomePage homePage;
	private DashBoardPage dashPage;
	private LoadRole ldr;
	private LoadBalanceManualPage lbmanualPage;
	private Map<String, Integer> LBstatsMap = new HashMap<String, Integer>();

	/**
	 * 
	 * 
	 * @param engSupp
	 */
	@Test(dataProvider = "ValidLBRequestData", dataProviderClass = LoadBalanceManualTestDataProvider.class, priority = 1, description = "Verification of request processing functionality for different Action Types")
	public void testValidLBRequestData(LoadBalanceManual lbm) {
		Log.startTestCase("Starting Test Case " + lbm.TestCaseId + ":" + lbm.TestDescription);
		/**
		 * Before Starting the test 1. Update User Role to Business Requester 2. Get
		 * User Current stats.
		 */
		if (LBstatsMap.isEmpty()) {
			homePage.clickDashBoardLink();
			ldr.setMSID(lbm.MSID);
			ldr.setRoleThroughDashboard(lbm.role);
			boolean isClicked = ldr.clickLoad();
			if (!(isClicked)) {
				LBstatsMap = dashPage.getStats();
				homePage.clickHomePageURL();
			}
			else {
				homePage.clickDashBoardLink();
				LBstatsMap = dashPage.getStats();
				homePage.clickHomePageURL();
				}
		}

		homePage.clickLoadBalanceManualLink();
		// Only in first Iteration check Requester Information

		if ((!(lbm.managerName.equalsIgnoreCase("")))) {
			lbmanualPage.checkRequesterInformation(lbm.managerName);
		}

		lbmanualPage.populateLBData(lbm);
		lbmanualPage.submitLBForm();
		lbmanualPage.submitLBConfirm();
		dashPage.verifyIsDashBoardPage();
		LBstatsMap = dashPage.verifyInProgressRequest(LBstatsMap);
		homePage.clickHomePageURL();

		Log.endTestCase();
	}
	// ---

	@Test(dataProvider = "InvalidLBRequestData", dataProviderClass = LoadBalanceManualTestDataProvider.class, priority = 2, description = "Test Mandatory Fields but invalid ms id")
	public void testInvalidRequestData(LoadBalanceManual lbm) {
		/*
		 * Log.startTestCase("Starting Test Case " + lbm.TestCaseId + ":" +
		 * lbm.TestDescription); if (LBstatsMap.isEmpty()) {
		 * homePage.clickDashBoardLink(); LBstatsMap = dashPage.getStats();
		 * ldr.setMSID(lbm.MSID); ldr.setRoleThroughDashboard(lbm.role); boolean
		 * isClicked = ldr.clickLoad(); if (!(isClicked)) { homePage.clickHomePageURL();
		 * } }
		 */

		/*
		 * lbmanualPage.populateLBData(lbm); lbmanualPage.submitLBForm();
		 * lbmanualPage.verifyDataValidationPopUpAppears();
		 * lbmanualPage.verifyErrorText(lbm.expectedErrorText);
		 * lbmanualPage.closePopup(); lbmanualPage.refreshPage();
		 */
		/* Log.endTestCase(); */

	}

}
