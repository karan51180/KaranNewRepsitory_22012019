/**
 * 
 */
package com.optum.navigatorapp.base.pageobjects;

import static org.testng.Assert.assertEquals;

import com.optum.automation.coreframework.baseclasses.BasePageFunctions;
import com.optum.automation.coreframework.browser.Locator;
import com.optum.automation.coreframework.browser.Locator.DynamicLocator;
import com.optum.automation.coreframework.browser.LocatorType;
import com.optum.automation.coreframework.browser.PageFactory;
import com.optum.automation.coreframework.browser.PageFactory.Page;
import com.optum.navigatorapp.intakerequest.databeans.EngineeringAndSupport;
import com.optum.navigatorapp.intakerequest.pageObjects.EngineeringAndSupportPage;

/**
 * @author mshar107
 *
 */
@Page
public class RequestDetailPage extends BasePageFunctions {

	private AssignResourcePage				assignResPage															= null;
	private EngineeringAndSupportPage	engSuppPage																= null;
	DynamicLocator										requestDetail_title												= new DynamicLocator(LocatorType.xpath, "//a/span[text()='(%d)']");
	Locator														requestDetail_requestorInf_link						= new Locator(LocatorType.xpath, "//span[contains(text(),'Requestor Information')]");
	Locator														requestDetail_requestInf_link							= new Locator(LocatorType.xpath, "//span[contains(text(),'Request Information')]");
	Locator														requestDetail_awating_resource_status			= new Locator(LocatorType.xpath, "//p[contains(text(),'Awaiting')]/br/following-sibling::text()[contains( . ,'Resource')]/ancestor::td");
	Locator														requestDetail_resource_assignment_Status	= new Locator(LocatorType.xpath, "//p[contains(text(),'Resource')]/parent::td");
	Locator														requestDetail_awaiting_review_status			= new Locator(LocatorType.xpath, "//p[contains(text(),'Awaiting')]/br/following-sibling::text()[contains( . ,'Review')]/ancestor::td");
	Locator														requestDetail_completed_status						= new Locator(LocatorType.xpath, "//p[contains(text(),'Completed')]/parent::td");
	Locator														requestDetail_estimatedHrs								= new Locator(LocatorType.id, "estimatedHours");
	Locator														requestDetail_actualHrs										= new Locator(LocatorType.id, "actualHours");
	Locator														requestDetail_attachments									= new Locator(LocatorType.id, "attachments");
	Locator														requestDetail_addFile_btn									= new Locator(LocatorType.id, "moreAttachments");
	Locator														requestDetail_notes												= new Locator(LocatorType.id, "customerNotes");
	Locator														requestDetail_addNotes_btn								= new Locator(LocatorType.id, "addNotes");
	Locator														requestDetail_addedNotes_span							= new Locator(LocatorType.xpath, "//span[@class='custNotes' and contains(text(),'Automation-Verifying Return flow')]");
	Locator														requestDetail_assignResource_btn					= new Locator(LocatorType.xpath, "//button[text()='Assign Resource']");
	Locator														requestDetail_return_rqst_btn							= new Locator(LocatorType.xpath, "//button[text()='Return']");
	Locator														requestDetail_complete_rqst_btn						= new Locator(LocatorType.xpath, "//button[text()='Complete']");
	Locator														requestDetail_partialcomplete_rqst_btn		= new Locator(LocatorType.xpath, "//button[contains(text(),'Partially') and contains(text(),'Complete')]");
	Locator														requestDetail_cancel_rqst_btn							= new Locator(LocatorType.xpath, "//button[contains(text(),'Cancel') and contains(text(),'Request')]");
	Locator														requestDetail_confirm_btn									= new Locator(LocatorType.xpath, "//button[text()='Yes']");
	Locator														requestDetail_Audit_tab										= new Locator(LocatorType.xpath, "//strong[text()='Audit']");
	Locator														requestDetail_Detail_tab									= new Locator(LocatorType.xpath, "//strong[text()='Details']");
	Locator														requestDetail_Audit_RequestUpdated				= new Locator(LocatorType.xpath, "//span[text()='REQUEST UPDATED']/parent::td/following-sibling::td/span[contains(text(),'Notes added')]");
	Locator														requestDetail_reviewRequest								= new Locator(LocatorType.xpath, "//button[text()='Reviewed']");
	Locator														requestDetail_canceRequestComments				= new Locator(LocatorType.id, "cancelRequestComments");
	DynamicLocator										requestDetail_cport_status								= new DynamicLocator(LocatorType.xpath, "//tr[%d]//select[@name='deviceStatus']");
	Locator														requestDetail_dcfRequired									= new Locator(LocatorType.id, "dcfRequired");
	Locator														requestDetail_submit_rqst_btn							= new Locator(LocatorType.xpath, "//button[text()='Submit']");
	public RequestDetailPage() {

		assignResPage = PageFactory.newInstance(AssignResourcePage.class);
		engSuppPage = PageFactory.newInstance(EngineeringAndSupportPage.class);

	}

	/**
	 * This Method will assign resource based on passed JSON String. Format of
	 * JSONString should be like below. <br>
	 * Json Format: <br>
	 * { <br>
	 * LastName:"Sharma", <br>
	 * FirstName:"", <br>
	 * MSID:"", <br>
	 * Email:"" <br>
	 * reasonForChange:""<br>
	 * } <br>
	 * Only the property for which value is present in file will used to populat
	 * data on screen. for e.g. <br>
	 * 1. If you only want to search for LastName. Just provide LastName field.</br>
	 * 2. if you want to search on the basis of LastName and FirstName , provide
	 * value for both property.</br>
	 * 
	 * @param jsonAsssignResource
	 */
	public void assignResource(String jsonAsssignResource) {
		clickElement(requestDetail_assignResource_btn);
		assignResPage.assignResource(jsonAsssignResource);

	}

	public void clickRequestorInformation() {
		clickElement(requestDetail_requestorInf_link);

	}

	public void clickRequestInformation() {
		clickElement(requestDetail_requestInf_link);

	}

	/**
	 * @param requestStatus
	 * @param value
	 */
	public void verifyRequestStatus(RequestStatus requestStatus, String value) {
		String style = null;
		boolean ispresent = false;
		switch (requestStatus) {
		case AwaitingResource:
			style = getAttribute(requestDetail_awating_resource_status, "style");
			if (style.contains(value)) {
				ispresent = true;
			}
			assertEquals(ispresent, true);
			break;
		case AwaitingReview:
			style = getAttribute(requestDetail_awaiting_review_status, "style");
			if (style.contains(value)) {
				ispresent = true;
			}
			assertEquals(ispresent, true);
			break;
		case ResourceAssignment:
			style = getAttribute(requestDetail_resource_assignment_Status, "style");
			if (style.contains(value)) {
				ispresent = true;
			}
			assertEquals(ispresent, true);
			break;
		case Completed:
			style = getAttribute(requestDetail_completed_status, "style");
			if (style.contains(value)) {
				ispresent = true;
			}
			assertEquals(ispresent, true);
			break;
		}

	}

	/**
	 * @param estimatedHours
	 */
	public void addEstimatedHours(String estimatedHours) {

		enterNumber(requestDetail_estimatedHrs, estimatedHours);

	}

	public void addActualHours(String actualHours) {

		enterNumber(requestDetail_actualHrs, actualHours);

	}

	/**
	 * @param attachments
	 */
	public void addAttachment(String attachments) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param notes
	 */
	public void addNotes(String notes) {
		enterTextThorughScript(requestDetail_notes, notes);
		clickElement(requestDetail_addNotes_btn);

	}

	/**
	 * @param requestId
	 */
	public void verifyRequestDeatilPage(int requestId) {
		waitForPageToLoad();
		boolean actual = isElementExist(requestDetail_title.format(requestId));
		assertEquals(actual, true, "Either screen did not redirected to Request detail Page or It is not same requestID, Actual RequestID:"+actual+"Expected RequestID:"+ requestId);

	}

	public enum RequestStatus {
		AwaitingResource, ResourceAssignment, AwaitingReview, Completed;
	}

	/**
	 * 
	 */
	public void navigateToAuditTab() {
		clickElement(requestDetail_Audit_tab);
	}

	public void navigateToDetailTab() {
		clickElement(requestDetail_Detail_tab);

	}

	public void returnRequest() {
		clickElement(requestDetail_return_rqst_btn);
		clickElement(requestDetail_confirm_btn);

	}

	/**
	 * @param engSupp
	 */
	public void verifyDetailsonAuditTab() {
		// TODO Auto-generated method stub
		waitFor(requestDetail_Audit_RequestUpdated);
		boolean isNotesAdded = isElementExist(requestDetail_Audit_RequestUpdated);
		assertEquals(isNotesAdded, true);
	}

	/**
	 * 
	 */
	public void reviewRequest() {
		// TODO Auto-generated method stub
		clickElement(requestDetail_reviewRequest);
		clickElement(requestDetail_confirm_btn);
	}

	public void cancelRequest(String reason) {
		// TODO Auto-generated method stub
		clickElement(requestDetail_cancel_rqst_btn);
		// First Verify if we don't enter comments for reason of cancellation then error
		// message should appear.
		clickElement(requestDetail_confirm_btn);
		engSuppPage.verifyDataValidationPopUpAppears();
		engSuppPage.verifyErrorText("Please provide the comments for termination.");
		engSuppPage.closePopup();

		// Submit the cancel request again with valid Comments

		enterTextThorughScript(requestDetail_canceRequestComments, reason);
		clickElement(requestDetail_confirm_btn);

	}

	public void completeRequest() {
		// TODO Auto-generated method stub
		clickElement(requestDetail_complete_rqst_btn);
		clickElement(requestDetail_confirm_btn);
	}
	public void partialcompleteRequest() {
		clickElement(requestDetail_partialcomplete_rqst_btn);
		clickElement(requestDetail_confirm_btn);
		
	}

	public void setStatustoComplete() {

		int actualRows = getXpathCount("//app-engineeringsupportstaticrequestinformation//table[@class=\"ux-tabl-data\"]/tbody//tr");
		for (int i = 1; i <= actualRows; i++) {
			selectValueByVisibleText(requestDetail_cport_status.format(i), "Complete");
		}
	}
	public void setStatustoPartiallyComplete() {

			int actualRows = getXpathCount("//app-engineeringsupportstaticrequestinformation//table[@class=\"ux-tabl-data\"]/tbody//tr");
			int i;
			for (i = actualRows; i > 1; i--) {
				selectValueByVisibleText(requestDetail_cport_status.format(i), "Cancel");
			}
			selectValueByVisibleText(requestDetail_cport_status.format(i), "Complete");
	}

	/**
	 * 
	 */
	public void setDCFRequiredNo() {
		selectValueByVisibleText(requestDetail_dcfRequired, "No");
		
	}
	public void setDCFRequiredYes() {
		selectValueByVisibleText(requestDetail_dcfRequired, "Yes");
		
	}

	/**
	 * 
	 */
	public void submitRequest() {
		clickElement(requestDetail_submit_rqst_btn);
		clickElement(requestDetail_confirm_btn);
	}
	

};