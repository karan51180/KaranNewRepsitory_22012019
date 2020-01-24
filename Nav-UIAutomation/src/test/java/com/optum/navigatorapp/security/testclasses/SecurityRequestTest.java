package com.optum.navigatorapp.security.testclasses;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import com.optum.automation.coreframework.baseclasses.BaseUITestCase;
import com.optum.automation.coreframework.utils.Log;
import com.optum.navigatorapp.base.pageobjects.DashBoardPage;
import com.optum.navigatorapp.base.pageobjects.HomePage;
import com.optum.navigatorapp.base.pageobjects.LoadRole;
import com.optum.navigatorapp.base.pageobjects.RequestDetailPage;
import com.optum.navigatorapp.base.pageobjects.RequestorInformationPage;
import com.optum.navigatorapp.base.pageobjects.RequestDetailPage.RequestStatus;
import com.optum.navigatorapp.intakerequest.databeans.EngineeringAndSupport;
import com.optum.navigatorapp.intakerequest.testDataProviders.EngSupportTestDataProvider;
import com.optum.navigatorapp.SecurityRequest.databeans.SecurityRequest;
import com.optum.navigatorapp.base.pageobjects.AssignResourcePage;
import com.optum.navigatorapp.security.pageObjects.SecurityRequestPage;
import com.optum.navigatorapp.base.pageobjects.RequestInformationPage;
import com.optum.navigatorapp.SecurityRequest.testDataProviders.SecurityRequestTestDataProvider;
import com.optum.navigatorapp.base.pageobjects.RequestorInformationPage;

public class SecurityRequestTest extends BaseUITestCase {
	private HomePage						homePage;
	private DashBoardPage				dashPage;
	private LoadRole						ldr;
	private SecurityRequestPage	secReqPage;
	// private Map<String, Integer> statsMap = new HashMap<String, Integer>();

	private RequestorInformationPage	requestorInfPage;
	private Map<String, Integer>			bus_req_statsMap		= new HashMap<String, Integer>();
	private Map<String, Integer>			ISOAE_mng_statsMap	= new HashMap<String, Integer>();
	private RequestDetailPage					rqstDtlPage;
	private RequestInformationPage		requestInfPage;

	@Test(dataProvider = "ValidRequestData", dataProviderClass = SecurityRequestTestDataProvider.class, priority = 1, description = "Verification of request processing functionality for different Action Types")
	public void testValidRequestData(SecurityRequest secReq) {
		Log.startTestCase("Starting Test Case " + secReq.testCaseID + ":" + secReq.testDescription);
		if (bus_req_statsMap.isEmpty()) {
			homePage.clickDashBoardLink();
			ldr.setMSID(secReq.MSID);
			ldr.setRoleThroughDashboard(secReq.role);
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

		homePage.clickSecurityRequestLink();
		secReqPage.populateData(secReq);
		secReqPage.submitForm();
		secReqPage.submitConfirm();
		dashPage.verifyIsDashBoardPage();
		bus_req_statsMap = dashPage.verifyInProgressRequest(bus_req_statsMap);
		homePage.clickHomePageURL();
		Log.endTestCase();
	}
}