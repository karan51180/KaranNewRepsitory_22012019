package com.optum.navigatorapp.intakerequest.testclasses;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Manoj Sharma
 *
 */
import org.testng.annotations.Test;
import com.optum.automation.coreframework.baseclasses.BaseUITestCase;
import com.optum.automation.coreframework.utils.Log;
import com.optum.navigatorapp.base.pageobjects.DashBoardPage;
import com.optum.navigatorapp.base.pageobjects.HomePage;
import com.optum.navigatorapp.base.pageobjects.LoadRole;
import com.optum.navigatorapp.base.pageobjects.RequestDetailPage;
import com.optum.navigatorapp.base.pageobjects.RequestDetailPage.RequestStatus;
import com.optum.navigatorapp.base.pageobjects.RequestInformationPage;
import com.optum.navigatorapp.base.pageobjects.RequestorInformationPage;
import com.optum.navigatorapp.intakerequest.databeans.EngineeringAndSupport;
import com.optum.navigatorapp.intakerequest.pageObjects.EngineeringAndSupportPage;
import com.optum.navigatorapp.intakerequest.testDataProviders.EngSupportTestDataProvider;

public class EngineeringAndSupportTest extends BaseUITestCase {

	private HomePage									homePage;
	private DashBoardPage							dashPage;
	private LoadRole									ldr;
	private EngineeringAndSupportPage	engSupportPage;
	private RequestorInformationPage	requestorInfPage;
	private RequestInformationPage		requestInfPage;
	private RequestDetailPage					rqstDtlPage;
	private Map<String, Integer>			bus_req_statsMap	= new HashMap<String, Integer>();
	private Map<String, Integer>			net_mng_statsMap	= new HashMap<String, Integer>();
	private Map<String, Integer>			net_eng_statsMap	= new HashMap<String, Integer>();

	/**
	 * Scenario and Test case Covered TS-01 TC-01,TS-02 TC-01,TS-03 TC-01,TS-04
	 * TC-01 TS-05 TC-01,TS-06 TC-01,TS-07 TC-01,TS-08 TC-01 TS-09 TC-01
	 * 
	 * @param engSupp
	 */
	@Test(dataProvider = "ValidRequestData", dataProviderClass = EngSupportTestDataProvider.class, priority = 1, description = "Verification of request processing functionality for different Action Types")
	public void testValidRequestData(EngineeringAndSupport engSupp) {
		Log.startTestCase("Starting Test Case " + engSupp.testCaseID + ":" + engSupp.testDescription);
		/*
		 * Before Starting the test 1. Update User Role to Business Requester 2. Get
		 * User Current stats.
		 */
		// This Part of code will only run for first Iteration.setting up role and stats
		// count.

		if (bus_req_statsMap.isEmpty()) {
			homePage.clickDashBoardLink();
			ldr.setMSID(engSupp.MSID);
			ldr.setRoleThroughDashboard(engSupp.role);
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

		homePage.clickEngSupportLink();
		// Only in first Iteration check Requester Information
		if ((!(engSupp.managerName.equalsIgnoreCase("")))) {
			engSupportPage.checkRequesterInformation(engSupp.managerName);
		}
		engSupportPage.populateData(engSupp);
		engSupportPage.submitForm();
		engSupportPage.submitConfirm();
		dashPage.verifyIsDashBoardPage();
		bus_req_statsMap = dashPage.verifyInProgressRequest(bus_req_statsMap);
		homePage.clickHomePageURL();

		Log.endTestCase();
	}

	@Test(dataProvider = "NtwMngrAssignment", dataProviderClass = EngSupportTestDataProvider.class, description = "Valdiate Network manager is able to assign Resurce to request", priority = 2)
	public void testRequestAssignmentByNetworkManager(EngineeringAndSupport engSupp) {
		
		if (net_mng_statsMap.isEmpty()) {
			homePage.clickDashBoardLink();
			ldr.setMSID(engSupp.MSID);
			ldr.setRoleThroughDashboard(engSupp.role);
			boolean isClicked = ldr.clickLoad();
			if (!(isClicked)) {
				net_mng_statsMap = dashPage.getStats();
				homePage.clickHomePageURL();
			} else {
				homePage.clickDashBoardLink();
				net_mng_statsMap = dashPage.getStats();
				homePage.clickHomePageURL();
			}
		}
		homePage.clickDashBoardLink();
		// Update Setting to show atleast 50 links
		dashPage.udpateActionRequiredResultsSetting("100");
		int requestId = dashPage.getRequestId(engSupp.RequestTitle);
		dashPage.selectRequestForAssignment(engSupp.RequestTitle);

		// Verify RequestDeatil Page is loaded for same RequestID
		rqstDtlPage.verifyRequestDeatilPage(requestId);

		// Verify Requester Information for only one request. So in sheet if this column
		// is populated it will verify, else not
		if (!(engSupp.expRequestorInformation.equalsIgnoreCase(""))) {
			rqstDtlPage.clickRequestorInformation();
			requestorInfPage.verifyRequestorInformation(engSupp.expRequestorInformation, String.valueOf(requestId));
		}

		// Verify Request Information for only one request. So in sheet if atleast one
		// field is present will verify, else not
		if (!(engSupp.WorkedInUnitedStates.equalsIgnoreCase(""))) {
			rqstDtlPage.clickRequestInformation();
			requestInfPage.verifyRequestInformation(engSupp, String.valueOf(requestId));
		}

		// check Request Status
		rqstDtlPage.verifyRequestStatus(RequestStatus.AwaitingResource, "active");
		rqstDtlPage.verifyRequestStatus(RequestStatus.ResourceAssignment, "inactive");
		rqstDtlPage.verifyRequestStatus(RequestStatus.AwaitingReview, "inactive");
		rqstDtlPage.verifyRequestStatus(RequestStatus.Completed, "inactive");

		// Add Estimated Hours for some cases.
		if (!(engSupp.estimatedHours.equalsIgnoreCase(""))) {
			rqstDtlPage.addEstimatedHours(engSupp.estimatedHours);
		}

		// Add Attachement if this fields is present in Sheet.
		if (!(engSupp.attachments.equalsIgnoreCase(""))) {
			rqstDtlPage.addAttachment(engSupp.attachments);
		}

		// Add Notes
		if (!(engSupp.notes.equalsIgnoreCase(""))) {
			rqstDtlPage.addNotes(engSupp.notes);
		}
		rqstDtlPage.assignResource(engSupp.assignResource);

		// Verify user is navigated to DashBoard Page on successful assignment of
		// Resource.
		dashPage.verifyIsDashBoardPage();

		// Check InProgressCount should be increased by 1
		dashPage.udpateInProgressResultsSetting("100");
		net_mng_statsMap = dashPage.verifyInProgressRequest(net_mng_statsMap);
		dashPage.verifyRequestMovetoInProgressQueue(requestId);
		homePage.clickHomePageURL();

	}

	@Test(dataProvider = "ReturnFlowByNWEngr", dataProviderClass = EngSupportTestDataProvider.class, description = "Valdiate Network manager is able to assign Resurce to request", priority = 3)
	public void testReturnRequestByNetworkEngineer(EngineeringAndSupport engSupp) {
		/*
		 * 1) Enter MSID,select the NW_ENGR role from the dropdown and click on LOAD
		 * button 2) Click on "dashboard" link at "my requests" category in the Home
		 * Page 3) Search for the request which has been created newly in
		 * "Action Required" table of dashboard of Network Engineer 4) Click on the
		 * request id of the newly created request which is matching with Request Title
		 * 5) Expand the Requestor Details tab and check the existing information 6)
		 * Expand the Request Details and check the existing information 7) Expand the
		 * Request Status tab and check the request status 8) Upload the attachment and
		 * verify the uploading functionality 9) Enter the notes and click on Add Notes
		 * button 10) Navigate to Audits tab of Request details page and check the above
		 * actions in the Audits log 11) Click on Return button at the bottom of the
		 * request details page
		 * "12) Check for the pop up with ""Request will be returned. Are you sure you want to return the request?"
		 * " and the title as ""Confirm Request Return""" 13) Click on "YES"
		 * 
		 */
		if (net_eng_statsMap.isEmpty()) {
			homePage.clickDashBoardLink();
			ldr.setMSID(engSupp.MSID);
			ldr.setRoleThroughDashboard(engSupp.role);
			boolean isClicked = ldr.clickLoad();
			if (!(isClicked)) {
				net_eng_statsMap = dashPage.getStats();
				homePage.clickHomePageURL();
			} else {
				homePage.clickDashBoardLink();
				net_eng_statsMap = dashPage.getStats();
				homePage.clickHomePageURL();
			}
		}
		homePage.clickDashBoardLink();
		dashPage.udpateActionRequiredResultsSetting("100");
		int requestId = dashPage.getRequestId(engSupp.RequestTitle);
		dashPage.selectRequestForAssignment(engSupp.RequestTitle);

		// Verify RequestDeatil Page is loaded for same RequestID
		rqstDtlPage.verifyRequestDeatilPage(requestId);

		// Verify Requester Information for only one request. So in sheet if this column
		// is populated it will verify, else not
		if (!(engSupp.expRequestorInformation.equalsIgnoreCase(""))) {
			rqstDtlPage.clickRequestorInformation();
			requestorInfPage.verifyRequestorInformation(engSupp.expRequestorInformation, String.valueOf(requestId));
		}

		// Verify Request Information for only one request. So in sheet if atleast one
		// field is present will verify, else not
		if (!(engSupp.WorkedInUnitedStates.equalsIgnoreCase(""))) {
			rqstDtlPage.clickRequestInformation();
			requestInfPage.verifyRequestInformation(engSupp, String.valueOf(requestId));
		}

		// check Request Status
		rqstDtlPage.verifyRequestStatus(RequestStatus.AwaitingResource, "active");
		rqstDtlPage.verifyRequestStatus(RequestStatus.ResourceAssignment, "active");
		rqstDtlPage.verifyRequestStatus(RequestStatus.AwaitingReview, "inactive");
		rqstDtlPage.verifyRequestStatus(RequestStatus.Completed, "inactive");
		if (!(engSupp.attachments.equalsIgnoreCase(""))) {
			rqstDtlPage.addAttachment(engSupp.attachments);
		}
		
		// Add Notes
		if (!(engSupp.notes.equalsIgnoreCase(""))) {
			rqstDtlPage.addNotes(engSupp.notes);
		}
		rqstDtlPage.navigateToAuditTab();
		rqstDtlPage.verifyDetailsonAuditTab();
		rqstDtlPage.navigateToDetailTab();
		/*
		 * There is bug in application if user enters Actual Hours and navigate to Audit tab. Then on returning to Detail tab again actual Hours goes away.
		 */
		if (!(engSupp.actualHours.equalsIgnoreCase(""))) {
			rqstDtlPage.addActualHours(engSupp.actualHours);
		}
		rqstDtlPage.returnRequest();
		// Verify user is navigated to DashBoard Page on successful assignment of
		// Resource.
		dashPage.verifyIsDashBoardPage();
		dashPage.udpateInProgressResultsSetting("100");
		// Check InProgressCount should be increased by 1
		net_eng_statsMap = dashPage.verifyInProgressRequest(net_eng_statsMap);
		dashPage.verifyRequestMovetoInProgressQueue(requestId);
		homePage.clickHomePageURL();

	}

	@Test(dataProvider = "ReviewFlowByBSRqstr", dataProviderClass = EngSupportTestDataProvider.class, description = "Valdiate Network manager is able to assign Resurce to request", priority = 4)
	public void testReviewFunctionalityByBussinessRequestor(EngineeringAndSupport engSupp) {
		/*
		 * 1) Enter MSID,select the BUSS_REQSTR role from the dropdown and click on LOAD
		 * button 2) Click on "dashboard" link at "my requests" category in the Home
		 * Page 3) Search for the request which has been created newly in
		 * "Action Required" table of dashboard of Network Engineer 4) Click on the
		 * request id of the newly created request which is matching with Request Title
		 * 5) Expand the Requestor Details tab and check the existing information 6)
		 * Expand the Request Details and check Contractually obligate field, Action
		 * type and Detailed Description fields are editable 7) Expand the Request
		 * Status tab and check the request status 8) Upload the attachment and verify
		 * the uploading functionality 9) Enter the notes and click on Add Notes button
		 * 10) Navigate to Audits tab of Request details page and check the above
		 * actions in the Audits log 11) Click on the Reviewed Button 12) Check the
		 * message pop with ""Request will be returned. Are you sure you want to return
		 * the request?"" and title ""Confirm Request Return"" should be displayed.
		 * Click on Yes Button"
		 * 
		 */

		homePage.clickDashBoardLink();
		ldr.setMSID(engSupp.MSID);
		ldr.setRoleThroughDashboard(engSupp.role);
		boolean isClicked = ldr.clickLoad();
		if (!(isClicked)) {
			bus_req_statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
		} else {
			homePage.clickDashBoardLink();
			bus_req_statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
		}

		homePage.clickDashBoardLink();
		int requestId = dashPage.getRequestId(engSupp.RequestTitle);
		dashPage.selectRequestForAssignment(engSupp.RequestTitle);

		// Verify RequestDeatil Page is loaded for same RequestID
		rqstDtlPage.verifyRequestDeatilPage(requestId);

		// Verify Requester Information for only one request. So in sheet if this column
		// is populated it will verify, else not
		if (!(engSupp.expRequestorInformation.equalsIgnoreCase(""))) {
			rqstDtlPage.clickRequestorInformation();
			requestorInfPage.verifyRequestorInformation(engSupp.expRequestorInformation, String.valueOf(requestId));
		}

		// Verify Request Information for only one request. So in sheet if atleast one
		// field is present will verify, else not
		if (!(engSupp.WorkedInUnitedStates.equalsIgnoreCase(""))) {
			engSupportPage.verifyData(engSupp);
		}

		// check Request Status
		rqstDtlPage.verifyRequestStatus(RequestStatus.AwaitingResource, "active");
		rqstDtlPage.verifyRequestStatus(RequestStatus.ResourceAssignment, "active");
		rqstDtlPage.verifyRequestStatus(RequestStatus.AwaitingReview, "active");
		rqstDtlPage.verifyRequestStatus(RequestStatus.Completed, "inactive");
		if (!(engSupp.attachments.equalsIgnoreCase(""))) {
			rqstDtlPage.addAttachment(engSupp.attachments);
		}

		// Add Notes
		if (!(engSupp.notes.equalsIgnoreCase(""))) {
			rqstDtlPage.addNotes(engSupp.notes);
		}
		rqstDtlPage.navigateToAuditTab();
		rqstDtlPage.verifyDetailsonAuditTab();
		rqstDtlPage.navigateToDetailTab();
		rqstDtlPage.reviewRequest();
		// Verify user is navigated to DashBoard Page on successful assignment of
		// Resource.
		dashPage.verifyIsDashBoardPage();
		dashPage.udpateInProgressResultsSetting("100");
		// Check InProgressCount should be increased by 1
		bus_req_statsMap = dashPage.verifyInProgressRequest(bus_req_statsMap);
		dashPage.verifyRequestMovetoInProgressQueue(requestId);
		homePage.clickHomePageURL();
	}

	@Test(dataProvider = "CancelFlowByBSReq", dataProviderClass = EngSupportTestDataProvider.class, description = "Valdiate Network manager is able to assign Resurce to request", priority = 5)
	public void testCancelRequestByBussinessReq(EngineeringAndSupport engSupp) {
		/*
		 * Not verifying Requester Information and Request Info. We already verified
		 * that in above test.
		 */homePage.clickDashBoardLink();
		ldr.setMSID(engSupp.MSID);
		ldr.setRoleThroughDashboard(engSupp.role);
		boolean isClicked = ldr.clickLoad();
		if (!(isClicked)) {
			bus_req_statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
		} else {
			homePage.clickDashBoardLink();
			bus_req_statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
		}

		homePage.clickDashBoardLink();
		int requestId = dashPage.getRequestId(engSupp.RequestTitle);
		dashPage.selectRequestForAssignment(engSupp.RequestTitle);

		// Verify RequestDeatil Page is loaded for same RequestID
		rqstDtlPage.verifyRequestDeatilPage(requestId);

		// check Request Status
		rqstDtlPage.verifyRequestStatus(RequestStatus.AwaitingResource, "active");
		rqstDtlPage.verifyRequestStatus(RequestStatus.ResourceAssignment, "active");
		rqstDtlPage.verifyRequestStatus(RequestStatus.AwaitingReview, "active");
		rqstDtlPage.verifyRequestStatus(RequestStatus.Completed, "inactive");
		if (!(engSupp.attachments.equalsIgnoreCase(""))) {
			rqstDtlPage.addAttachment(engSupp.attachments);
		}

		// Add Notes
		if (!(engSupp.notes.equalsIgnoreCase(""))) {
			rqstDtlPage.addNotes(engSupp.notes);
		}
		rqstDtlPage.navigateToAuditTab();
		rqstDtlPage.verifyDetailsonAuditTab();
		rqstDtlPage.navigateToDetailTab();
		rqstDtlPage.cancelRequest(engSupp.comments);
		// Verify user is navigated to DashBoard Page on successful assignment of
		// Resource.
		dashPage.verifyIsDashBoardPage();

		// Check InProgressCount should be increased by 1
		bus_req_statsMap = dashPage.verifyClosedRequest(bus_req_statsMap);
		dashPage.verifyRequestMovetoClosedQueueWithStatus(requestId, "Cancelled");
		homePage.clickHomePageURL();
	}

	@Test(dataProvider = "CancelFlowByNWEng", dataProviderClass = EngSupportTestDataProvider.class, description = "Valdiate Network manager is able to assign Resurce to request", priority = 6)
	public void testCancelRequestByNetworkEngineer(EngineeringAndSupport engSupp) {
		/*
		 * Not verifying Requester Information and Request Info. We already verified
		 * that ReturnFlow test.
		 */homePage.clickDashBoardLink();
		ldr.setMSID(engSupp.MSID);
		ldr.setRoleThroughDashboard(engSupp.role);
		boolean isClicked = ldr.clickLoad();
		if (!(isClicked)) {
			net_eng_statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
		} else {
			homePage.clickDashBoardLink();
			net_eng_statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
		}

		homePage.clickDashBoardLink();
		dashPage.udpateActionRequiredResultsSetting("100");
		int requestId = dashPage.getRequestId(engSupp.RequestTitle);
		dashPage.selectRequestForAssignment(engSupp.RequestTitle);

		// Verify RequestDeatil Page is loaded for same RequestID
		rqstDtlPage.verifyRequestDeatilPage(requestId);

		// check Request Status
		rqstDtlPage.verifyRequestStatus(RequestStatus.AwaitingResource, "active");
		rqstDtlPage.verifyRequestStatus(RequestStatus.ResourceAssignment, "active");
		rqstDtlPage.verifyRequestStatus(RequestStatus.AwaitingReview, "inactive");
		rqstDtlPage.verifyRequestStatus(RequestStatus.Completed, "inactive");
		if (!(engSupp.attachments.equalsIgnoreCase(""))) {
			rqstDtlPage.addAttachment(engSupp.attachments);
		}

		// Add Notes
		if (!(engSupp.notes.equalsIgnoreCase(""))) {
			rqstDtlPage.addNotes(engSupp.notes);
		}
		rqstDtlPage.navigateToAuditTab();
		rqstDtlPage.verifyDetailsonAuditTab();
		rqstDtlPage.navigateToDetailTab();
		if (!(engSupp.actualHours.equalsIgnoreCase(""))) {
			rqstDtlPage.addActualHours(engSupp.actualHours);
		}
		if(engSupp.ActionType.equalsIgnoreCase("Network Ports")||engSupp.ActionType.equalsIgnoreCase("Network Connectivity")) {
			rqstDtlPage.clickRequestInformation();
			rqstDtlPage.setDCFRequiredNo();
		}
		rqstDtlPage.cancelRequest(engSupp.comments);
		// Verify user is navigated to DashBoard Page on successful assignment of
		// Resource.
		dashPage.verifyIsDashBoardPage();

		// Check InProgressCount should be increased by 1
		net_eng_statsMap = dashPage.verifyClosedRequest(net_eng_statsMap);
		dashPage.verifyRequestMovetoClosedQueueWithStatus(requestId, "Cancelled");
		homePage.clickHomePageURL();
	}

	@Test(dataProvider = "CompleteFlowByNWEng", dataProviderClass = EngSupportTestDataProvider.class, description = "Valdiate Network manager is able to assign Resurce to request", priority = 7)
	public void testCompleteRequestByNetworkEngineer(EngineeringAndSupport engSupp) {
		/*
		 * Not verifying Requester Information and Request Info. We already verified
		 * that ReturnFlow test.
		 */homePage.clickDashBoardLink();
		ldr.setMSID(engSupp.MSID);
		ldr.setRoleThroughDashboard(engSupp.role);
		boolean isClicked = ldr.clickLoad();
		if (!(isClicked)) {
			net_eng_statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
		} else {
			homePage.clickDashBoardLink();
			net_eng_statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
		}

		homePage.clickDashBoardLink();
		dashPage.udpateActionRequiredResultsSetting("100");
		int requestId = dashPage.getRequestId(engSupp.RequestTitle);
		dashPage.selectRequestForAssignment(engSupp.RequestTitle);

		// Verify RequestDeatil Page is loaded for same RequestID
		rqstDtlPage.verifyRequestDeatilPage(requestId);

		// check Request Status
		rqstDtlPage.verifyRequestStatus(RequestStatus.AwaitingResource, "active");
		rqstDtlPage.verifyRequestStatus(RequestStatus.ResourceAssignment, "active");
		rqstDtlPage.verifyRequestStatus(RequestStatus.AwaitingReview, "inactive");
		rqstDtlPage.verifyRequestStatus(RequestStatus.Completed, "inactive");
		if(engSupp.ActionType.equalsIgnoreCase("Console Port ( Raritan )")) {
			rqstDtlPage.setStatustoComplete();
		}
		else if(engSupp.ActionType.equalsIgnoreCase("Network Ports")||engSupp.ActionType.equalsIgnoreCase("Network Connectivity")) {
			rqstDtlPage.clickRequestInformation();
			rqstDtlPage.setDCFRequiredNo();
		}
		if (!(engSupp.attachments.equalsIgnoreCase(""))) {
			rqstDtlPage.addAttachment(engSupp.attachments);
		}

		// Add Notes
		if (!(engSupp.notes.equalsIgnoreCase(""))) {
			rqstDtlPage.addNotes(engSupp.notes);
		}
		rqstDtlPage.navigateToAuditTab();
		rqstDtlPage.verifyDetailsonAuditTab();
		rqstDtlPage.navigateToDetailTab();
		if (!(engSupp.actualHours.equalsIgnoreCase(""))) {
			rqstDtlPage.addActualHours(engSupp.actualHours);
		}
		rqstDtlPage.completeRequest();
		// Verify user is navigated to DashBoard Page on successful assignment of
		// Resource.
		dashPage.verifyIsDashBoardPage();

		// Check InProgressCount should be increased by 1
		net_eng_statsMap = dashPage.verifyClosedRequest(net_eng_statsMap);
		dashPage.verifyRequestMovetoClosedQueueWithStatus(requestId, "Completed");
		homePage.clickHomePageURL();
	}
	@Test(dataProvider = "PartialCompleteFlowByNWEng", dataProviderClass = EngSupportTestDataProvider.class, description = "Valdiate Network manager is able to assign Resurce to request", priority = 8)
	public void testPartialCompleteRequestByNetworkEngineer(EngineeringAndSupport engSupp) {
		/*
		 * Not verifying Requester Information and Request Info. We already verified
		 * that ReturnFlow test.
		 */homePage.clickDashBoardLink();
		ldr.setMSID(engSupp.MSID);
		ldr.setRoleThroughDashboard(engSupp.role);
		boolean isClicked = ldr.clickLoad();
		if (!(isClicked)) {
			net_eng_statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
		} else {
			homePage.clickDashBoardLink();
			net_eng_statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
		}

		homePage.clickDashBoardLink();
		dashPage.udpateActionRequiredResultsSetting("100");
		int requestId = dashPage.getRequestId(engSupp.RequestTitle);
		dashPage.selectRequestForAssignment(engSupp.RequestTitle);

		// Verify RequestDeatil Page is loaded for same RequestID
		rqstDtlPage.verifyRequestDeatilPage(requestId);

		// check Request Status
		
		if(engSupp.ActionType.equalsIgnoreCase("Console Port ( Raritan )")) {
			rqstDtlPage.setStatustoPartiallyComplete();
		}
		if (!(engSupp.attachments.equalsIgnoreCase(""))) {
			rqstDtlPage.addAttachment(engSupp.attachments);
		}

		// Add Notes
		if (!(engSupp.notes.equalsIgnoreCase(""))) {
			rqstDtlPage.addNotes(engSupp.notes);
		}
		rqstDtlPage.navigateToAuditTab();
		rqstDtlPage.verifyDetailsonAuditTab();
		rqstDtlPage.navigateToDetailTab();
		rqstDtlPage.partialcompleteRequest();
		// Verify user is navigated to DashBoard Page on successful assignment of
		// Resource.
		dashPage.verifyIsDashBoardPage();

		// Check InProgressCount should be increased by 1
		net_eng_statsMap = dashPage.verifyClosedRequest(net_eng_statsMap);
		dashPage.verifyRequestMovetoClosedQueueWithStatus(requestId, "Partially Completed");
		homePage.clickHomePageURL();
	}
	
	@Test(dataProvider = "DCFFlowByNWEng", dataProviderClass = EngSupportTestDataProvider.class, description = "Valdiate Network manager is able to assign Resurce to request", priority = 9)
	public void testDCFByNetworkEngineer(EngineeringAndSupport engSupp) {
		homePage.clickDashBoardLink();
		ldr.setMSID(engSupp.MSID);
		ldr.setRoleThroughDashboard(engSupp.role);
		boolean isClicked = ldr.clickLoad();
		if (!(isClicked)) {
			net_eng_statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
		} else {
			homePage.clickDashBoardLink();
			net_eng_statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
		}

		homePage.clickDashBoardLink();
		dashPage.udpateActionRequiredResultsSetting("100");
		int requestId = dashPage.getRequestId(engSupp.RequestTitle);
		dashPage.selectRequestForAssignment(engSupp.RequestTitle);

		// Verify RequestDeatil Page is loaded for same RequestID
		rqstDtlPage.verifyRequestDeatilPage(requestId);

		if (!(engSupp.attachments.equalsIgnoreCase(""))) {
			rqstDtlPage.addAttachment(engSupp.attachments);
		}

		// Add Notes
		if (!(engSupp.notes.equalsIgnoreCase(""))) {
			rqstDtlPage.addNotes(engSupp.notes);
		}
		rqstDtlPage.navigateToAuditTab();
		rqstDtlPage.verifyDetailsonAuditTab();
		rqstDtlPage.navigateToDetailTab();
		if (!(engSupp.actualHours.equalsIgnoreCase(""))) {
			rqstDtlPage.addActualHours(engSupp.actualHours);
		}
		if(engSupp.ActionType.equalsIgnoreCase("Network Ports")||engSupp.ActionType.equalsIgnoreCase("Network Connectivity")) {
			rqstDtlPage.clickRequestInformation();
			rqstDtlPage.setDCFRequiredYes();
		}
		rqstDtlPage.submitRequest();
		// Verify user is navigated to DashBoard Page on successful assignment of
		// Resource.
		dashPage.verifyIsDashBoardPage();

		// Check InProgressCount should be increased by 1
		net_eng_statsMap = dashPage.verifyInProgressRequest(net_eng_statsMap);
		dashPage.verifyRequestMovetoInProgressQueue(requestId);
		homePage.clickHomePageURL();
	}
	@Test(dataProvider = "DCFFlowByDCFMngr", dataProviderClass = EngSupportTestDataProvider.class, description = "Valdiate DCF Network manager is able to assign Resurce to request", priority = 10)
	public void testDCFFlowByNetworkManager(EngineeringAndSupport engSupp) {
			homePage.clickDashBoardLink();
			ldr.setMSID(engSupp.MSID);
			ldr.setRoleThroughDashboard(engSupp.role);
			boolean isClicked = ldr.clickLoad();
			if (!(isClicked)) {
				net_mng_statsMap = dashPage.getStats();
				homePage.clickHomePageURL();
			} else {
				homePage.clickDashBoardLink();
				net_mng_statsMap = dashPage.getStats();
				homePage.clickHomePageURL();
			}
		
		homePage.clickDashBoardLink();
		
		int requestId = dashPage.getRequestId(engSupp.RequestTitle);
		dashPage.selectRequestForAssignment(engSupp.RequestTitle);

		// Verify RequestDeatil Page is loaded for same RequestID
		rqstDtlPage.verifyRequestDeatilPage(requestId);

		// Add Estimated Hours for some cases.
		if (!(engSupp.estimatedHours.equalsIgnoreCase(""))) {
			rqstDtlPage.addEstimatedHours(engSupp.estimatedHours);
		}

		// Add Attachement if this fields is present in Sheet.
		if (!(engSupp.attachments.equalsIgnoreCase(""))) {
			rqstDtlPage.addAttachment(engSupp.attachments);
		}

		// Add Notes
		if (!(engSupp.notes.equalsIgnoreCase(""))) {
			rqstDtlPage.addNotes(engSupp.notes);
		}
		rqstDtlPage.assignResource(engSupp.assignResource);

		// Verify user is navigated to DashBoard Page on successful assignment of
		// Resource.
		dashPage.verifyIsDashBoardPage();

		// Check InProgressCount should be increased by 1
		dashPage.udpateInProgressResultsSetting("100");
		net_mng_statsMap = dashPage.verifyInProgressRequest(net_mng_statsMap);
		dashPage.verifyRequestMovetoInProgressQueue(requestId);
		homePage.clickHomePageURL();
	}
	@Test(dataProvider = "ReturnDCFFlowByDCFEngr", dataProviderClass = EngSupportTestDataProvider.class, description = "Valdiate Network manager is able to assign Resurce to request", priority = 11)
	public void testReturnDCFFlowByDCFEngineer(EngineeringAndSupport engSupp) {
		homePage.clickDashBoardLink();
		ldr.setMSID(engSupp.MSID);
		ldr.setRoleThroughDashboard(engSupp.role);
		boolean isClicked = ldr.clickLoad();
		if (!(isClicked)) {
			net_eng_statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
		} else {
			homePage.clickDashBoardLink();
			net_eng_statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
		}

		homePage.clickDashBoardLink();
		int requestId = dashPage.getRequestId(engSupp.RequestTitle);
		dashPage.selectRequestForAssignment(engSupp.RequestTitle);

		// Verify RequestDeatil Page is loaded for same RequestID
		rqstDtlPage.verifyRequestDeatilPage(requestId);

		if (!(engSupp.attachments.equalsIgnoreCase(""))) {
			rqstDtlPage.addAttachment(engSupp.attachments);
		}

		// Add Notes
		if (!(engSupp.notes.equalsIgnoreCase(""))) {
			rqstDtlPage.addNotes(engSupp.notes);
		}
		rqstDtlPage.navigateToAuditTab();
		rqstDtlPage.verifyDetailsonAuditTab();
		rqstDtlPage.navigateToDetailTab();
		if (!(engSupp.actualHours.equalsIgnoreCase(""))) {
			rqstDtlPage.addActualHours(engSupp.actualHours);
		}
		
		rqstDtlPage.returnRequest();
		// Verify user is navigated to DashBoard Page on successful assignment of
		// Resource.
		dashPage.verifyIsDashBoardPage();

		// Check InProgressCount should be increased by 1
		net_eng_statsMap = dashPage.verifyInProgressRequest(net_eng_statsMap);
		dashPage.verifyRequestMovetoInProgressQueue(requestId);
		homePage.clickHomePageURL();
	}
	
	@Test(dataProvider = "CompleteDCFFlowByDCFEngr", dataProviderClass = EngSupportTestDataProvider.class, description = "Valdiate Network manager is able to assign Resurce to request", priority = 12)
	public void testCompleteDCFFlowByDCFEngineer(EngineeringAndSupport engSupp) {
		homePage.clickDashBoardLink();
		ldr.setMSID(engSupp.MSID);
		ldr.setRoleThroughDashboard(engSupp.role);
		boolean isClicked = ldr.clickLoad();
		if (!(isClicked)) {
			net_eng_statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
		} else {
			homePage.clickDashBoardLink();
			net_eng_statsMap = dashPage.getStats();
			homePage.clickHomePageURL();
		}

		homePage.clickDashBoardLink();
		int requestId = dashPage.getRequestId(engSupp.RequestTitle);
		dashPage.selectRequestForAssignment(engSupp.RequestTitle);

		// Verify RequestDeatil Page is loaded for same RequestID
		rqstDtlPage.verifyRequestDeatilPage(requestId);

		if (!(engSupp.attachments.equalsIgnoreCase(""))) {
			rqstDtlPage.addAttachment(engSupp.attachments);
		}

		// Add Notes
		if (!(engSupp.notes.equalsIgnoreCase(""))) {
			rqstDtlPage.addNotes(engSupp.notes);
		}
		rqstDtlPage.navigateToAuditTab();
		rqstDtlPage.verifyDetailsonAuditTab();
		rqstDtlPage.navigateToDetailTab();
		if (!(engSupp.actualHours.equalsIgnoreCase(""))) {
			rqstDtlPage.addActualHours(engSupp.actualHours);
		}
		
		rqstDtlPage.completeRequest();
		// Verify user is navigated to DashBoard Page on successful assignment of
		// Resource.
		dashPage.verifyIsDashBoardPage();

		// Check InProgressCount should be increased by 1
		net_eng_statsMap = dashPage.verifyClosedRequest(net_eng_statsMap);
		dashPage.verifyRequestMovetoClosedQueueWithStatus(requestId, "Completed");
		homePage.clickHomePageURL();
	}
	@Test(dataProvider = "InValidData", dataProviderClass = EngSupportTestDataProvider.class, description = "Test Mandatory Fields", priority = 13)
	public void testMandatoryFields(EngineeringAndSupport engSupp) {
		Log.startTestCase("Starting Test Case " + engSupp.testCaseID + ":" + engSupp.testDescription);
		if (!(engSupportPage.isEngAndSupportPage())) {
			homePage.clickEngSupportLink();
			ldr.setMSID(engSupp.MSID);
			ldr.setRole(engSupp.role);
			boolean isClicked = ldr.clickLoad();
			if (isClicked) {
				homePage.clickEngSupportLink();
			}

		}
		engSupportPage.populateData(engSupp);
		engSupportPage.submitForm();
		engSupportPage.verifyDataValidationPopUpAppears();
		engSupportPage.verifyErrorText(engSupp.expectedErrorText);
		engSupportPage.closePopup();
		engSupportPage.refreshPage();
		Log.endTestCase();

	}

}
