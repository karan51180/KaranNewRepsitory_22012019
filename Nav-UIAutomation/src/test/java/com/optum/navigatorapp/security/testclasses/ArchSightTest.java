package com.optum.navigatorapp.security.testclasses;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Venugopal Vemula
 *
 */
import org.testng.annotations.Test;

import com.optum.automation.coreframework.baseclasses.BaseUITestCase;
import com.optum.automation.coreframework.utils.Log;
import com.optum.navigatorapp.base.pageobjects.DashBoardPage;
import com.optum.navigatorapp.base.pageobjects.HomePage;
import com.optum.navigatorapp.base.pageobjects.LoadRole;
import com.optum.navigatorapp.security.databeans.ArchSight;
import com.optum.navigatorapp.security.pageObjects.ArchSightPage;
import com.optum.navigatorapp.security.testDataProviders.ArchSightDataProvider;

public class ArchSightTest extends BaseUITestCase {

	private HomePage homePage;
	private DashBoardPage dashPage;
	private LoadRole ldr;
	private ArchSightPage archSightPage;
	private Map<String, Integer> bus_req_statsMap = new HashMap<String, Integer>();

	/*
	 * All possible combinations of Arch Sight Form Submission.
	 */

	@Test(dataProvider = "ValidRequestData", dataProviderClass = ArchSightDataProvider.class, priority = 1, description = "Verification of request processing functionality for different Action Types")
	public void testValidRequestData(ArchSight archSight) {
		Log.startTestCase("Starting Test Case " + archSight.testCaseID + ":" + archSight.testDescription);

		/*
		 * Before Starting the test 1. Update User Role to Business Requester 2.
		 * Get User Current stats.
		 */
		// This Part of code will only run for first Iteration.setting up role
		// and stats
		// count.

		if (bus_req_statsMap.isEmpty()) {
			homePage.clickDashBoardLink();
			ldr.setMSID(archSight.MSID);
			ldr.setRoleThroughDashboard(archSight.role);
			boolean isClicked = ldr.clickLoad();
			if (!(isClicked)) {
				bus_req_statsMap = dashPage.getStats();
				homePage.clickHomePageURL();
			} else {
				homePage.clickDashBoardLink();
				bus_req_statsMap = dashPage.getStats();
				homePage.clickHomePageURL();
			}
		}

		homePage.clickArchSightRequestLink();

		archSightPage.populateData(archSight);

		archSightPage.submitForm();

		archSightPage.submitConfirm();

		dashPage.verifyIsDashBoardPage();

		bus_req_statsMap = dashPage.verifyInProgressRequest(bus_req_statsMap);

		homePage.clickHomePageURL();

		Log.endTestCase();
	}

}
