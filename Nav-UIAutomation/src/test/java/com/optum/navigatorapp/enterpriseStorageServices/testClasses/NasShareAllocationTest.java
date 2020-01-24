package com.optum.navigatorapp.enterpriseStorageServices.testClasses;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.optum.automation.coreframework.baseclasses.BaseUITestCase;
import com.optum.automation.coreframework.utils.Log;
import com.optum.navigatorapp.base.pageobjects.DashBoardPage;
import com.optum.navigatorapp.base.pageobjects.HomePage;
import com.optum.navigatorapp.base.pageobjects.LoadRole;
import com.optum.navigatorapp.enterpriseStorageServices.databeans.NasShareAllocation;
import com.optum.navigatorapp.enterpriseStorageServices.pageObjects.NasShareAllocationPage;

import com.optum.navigatorapp.enterpriseStorageServices.testDataProviders.NasShareTestDataProvider;

public class NasShareAllocationTest extends BaseUITestCase {
	private HomePage homePage;
	private NasShareAllocationPage nasSharePage;
	private DashBoardPage dashPage;
	private LoadRole ldr;
	private Map<String, Integer> statsMap = new HashMap<String, Integer>();

	@Test(dataProvider = "ValidNasData", dataProviderClass = NasShareTestDataProvider.class, priority = 1, description = "Create new nas share")
	public void testValidRequestData(NasShareAllocation nasShare) throws InterruptedException {
		Log.startTestCase("Starting Test Case " + nasShare.testCaseID + ":" + nasShare.testDescription);
		if (statsMap.isEmpty()) {
			homePage.clickDashBoardLink();
			ldr.setMSID(nasShare.MSID);
			ldr.setRoleThroughDashboard(nasShare.role);
			boolean isClicked = ldr.clickLoad();
			if (!(isClicked)) {
				statsMap = dashPage.getStats();
				homePage.clickHomePageURL();

			} else {
				homePage.clickDashBoardLink();
				statsMap = dashPage.getStats();
				homePage.clickHomePageURL();
			}
		}
		homePage.clickNASShareAllocationLink();

		if (!(nasShare.ManagerName.equalsIgnoreCase(""))) {
			nasSharePage.checkRequesterInformation(nasShare.ManagerName);
		}

		nasSharePage.populateData(nasShare);
		nasSharePage.populateAdminGroupData(nasShare);
		nasSharePage.populateConfigDetails(nasShare);
		nasSharePage.populateUserNotificationPage(nasShare);
		nasSharePage.populateReviewPage(nasShare);
		nasSharePage.submitForm();
		nasSharePage.SubmitConfirm();
		dashPage.verifyIsDashBoardPage();
		statsMap = dashPage.verifyInProgressRequest(statsMap);
		homePage.clickHomePageURL();

		Log.endTestCase();
	}
}
