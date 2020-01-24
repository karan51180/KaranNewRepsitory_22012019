package com.optum.navigatorapp.intakerequest.testclasses;

import java.util.HashMap; 
import java.util.Map;

/**
 * @author SundarSharma
 *
 */
import org.testng.annotations.Test;
import com.optum.automation.coreframework.baseclasses.BaseUITestCase;
import com.optum.automation.coreframework.utils.Log;
import com.optum.navigatorapp.base.pageobjects.DashBoardPage;
import com.optum.navigatorapp.base.pageobjects.HomePage;
import com.optum.navigatorapp.base.pageobjects.LoadRole;
import com.optum.navigatorapp.base.pageobjects.RequestDetailPage;
import com.optum.navigatorapp.base.pageobjects.VtpRequestInformationPage;
import com.optum.navigatorapp.base.pageobjects.RequestorInformationPage;
import com.optum.navigatorapp.intakerequest.databeans.EngineeringAndSupport;
import com.optum.navigatorapp.intakerequest.databeans.VendorAndThridParty;
import com.optum.navigatorapp.intakerequest.pageObjects.VendorAndThirdpartyPage;
import com.optum.navigatorapp.intakerequest.testDataProviders.EngSupportTestDataProvider;
import com.optum.navigatorapp.intakerequest.testDataProviders.VTPTestDataProvider;

public class VendorAndThirdpartyTest extends BaseUITestCase {

	private HomePage					homePage;
	private DashBoardPage				dashPage;
	private LoadRole					ldr;
	private VendorAndThirdpartyPage     vtpPage;
	private RequestorInformationPage	requestorInfPage;
//	private VtpRequestInformationPage		requestInfPage;
	private RequestDetailPage			rqstDtlPage;
	private Map<String, Integer>		statsMap	= new HashMap<String, Integer>();
//	private Map<String, Integer>		bus_req_statsMap	= new HashMap<String, Integer>();
	private Map<String, Integer>		ntwk_mngr_statsMap	= new HashMap<String, Integer>();
//	private Map<String, Integer>		net_engr_statsMap	= new HashMap<String, Integer>();

	/**
	 * @param vtp
	 */
	@Test(dataProvider = "ValidRequestData", dataProviderClass = VTPTestDataProvider.class, priority = 1, description = "Verify the functionality of \"Business Partner\" as connecting entity Request submission by BR")
	public void testValidRequestData(VendorAndThridParty vtp) {
		Log.startTestCase("Starting Test Case " + vtp.testCaseID + ":" + vtp.testDescription);
		/**
		 * Before Starting the test 1. Update User Role to Business Requester 2. Get
		 * User Current status.
		 */
		// This Part of code will only run for first Iteration.setting up role and stats
		// count.

		if (statsMap.isEmpty()) {
			homePage.clickDashBoardLink();
			ldr.setMSID(vtp.MSID);
			ldr.setRoleThroughDashboard(vtp.role);
			boolean isClicked = ldr.clickLoad();
			if (!(isClicked)) {
				statsMap = dashPage.getStats();
				homePage.clickHomePageURL();
			}
			else {
			homePage.clickDashBoardLink();
			statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
			}
			}
		
		homePage.clickVTPLink();		
		vtpPage.populateData(vtp);
		vtpPage.submitForm();
		vtpPage.submitConfirmation(vtp);
			
//		vtpPage.submitconfirm();
		dashPage.verifyIsDashBoardPage();
		statsMap = dashPage.verifyInProgressRequest(statsMap);
		homePage.clickHomePageURL();

		Log.endTestCase();
	}

	
	
	@Test(dataProvider = "NwMNGRAssignment", dataProviderClass = VTPTestDataProvider.class, description = "Valdiate Network manager is able to assign Resurce to request", priority = 2)
	public void testRequestAssignmentByNetworkManager(VendorAndThridParty vtp) {
		
		
		if (ntwk_mngr_statsMap.isEmpty()) {
			homePage.clickDashBoardLink();
			ldr.setMSID(vtp.MSID);
			ldr.setRoleThroughDashboard(vtp.role);
			boolean isClicked = ldr.clickLoad();
			if (!(isClicked)) {
				ntwk_mngr_statsMap = dashPage.getStats();
				homePage.clickHomePageURL();
			} else {
				homePage.clickDashBoardLink();
				ntwk_mngr_statsMap = dashPage.getStats();
				homePage.clickHomePageURL();
			}
		}
		
		homePage.clickDashBoardLink();
		// Update Setting to show atleast 50 links
		dashPage.udpateActionRequiredResultsSetting("100");
		int reqId = dashPage.getRequestId(vtp.RequestTitle);
		dashPage.selectRequestForAssignment(vtp.RequestTitle);
		rqstDtlPage.verifyRequestDeatilPage(reqId);
		
		if (!(vtp.expRequestorInformation.equalsIgnoreCase(""))) {
			rqstDtlPage.clickRequestorInformation();
			requestorInfPage.verifyRequestorInformation(vtp.expRequestorInformation, String.valueOf(reqId));
		}
		
			
		
		
		
		
		
		
		
		
		}
	
	
	
	
	
//	@Test(dataProvider = "InValidData", dataProviderClass = VTPTestDataProvider.class, priority = 2, description = "Test Mandatory Fields")
//	public void testMandatoryFields(VendorAndThridParty vtp) {
//		Log.startTestCase("Starting Test Case " + vtp.testCaseID + ":" + vtp.testDescription);
//		if (!(vtpPage.isVtpPage())) {
//			homePage.clickVTPLink();
//			ldr.setMSID(vtp.MSID);
//			ldr.setRole(vtp.role);
//			boolean isClicked = ldr.clickLoad();
//			if (isClicked) {
//				homePage.clickVTPLink();
//			}
//
//		}
//		vtpPage.populateData(vtp);
//		vtpPage.submitForm();
//		
//
//	}

	public VendorAndThirdpartyPage getVtpPage() {
		return vtpPage;
	}

	public void setVtpPage(VendorAndThirdpartyPage vtpPage) {
		this.vtpPage = vtpPage;
	}

}
