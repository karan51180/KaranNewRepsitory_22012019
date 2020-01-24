package com.optum.navigatorapp.intakerequest.pageObjects;
/*
 * @author Manoj Sharma
 */

import static org.testng.Assert.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.log4testng.Logger;

import com.optum.automation.coreframework.baseclasses.BasePageFunctions;
import com.optum.automation.coreframework.browser.Locator;
import com.optum.automation.coreframework.browser.Locator.DynamicLocator;
import com.optum.automation.coreframework.browser.LocatorType;
import com.optum.automation.coreframework.browser.PageFactory;
import com.optum.automation.coreframework.browser.PageFactory.Page;
import com.optum.navigatorapp.base.pageobjects.AssignResourcePage;
import com.optum.navigatorapp.base.pageobjects.RequestDetailPage;
import com.optum.navigatorapp.base.pageobjects.RequestInformationPage;
import com.optum.navigatorapp.intakerequest.databeans.EngineeringAndSupport;

@Page
public class EngineeringAndSupportPage extends BasePageFunctions {

	private static Logger logger = Logger.getLogger(EngineeringAndSupportPage.class);
private RequestInformationPage rqstInfPage;
 	Locator	engSupport_RequestTitle							= new Locator(LocatorType.id, "requestTitle");
	Locator	engSupport_workedInUsa							= new Locator(LocatorType.id, "offshoreProhibited");
	Locator	engSupport_action										= new Locator(LocatorType.id, "actionType");
	Locator	engSupport_detailedDescription			= new Locator(LocatorType.id, "detailedDescription");
	Locator	engSupport_peakSesaon								= new Locator(LocatorType.id, "whyinPeakSeason");
	Locator	engSupport_impactIfAfterPeakSeason	= new Locator(LocatorType.id, "impactIfAfterPeakSeason");
	Locator	engSupport_whyPerformedScheduled		= new Locator(LocatorType.id, "whyPerformedScheduled");
	Locator	engSupport_attachments							= new Locator(LocatorType.id, "attachments");
	Locator	engSupport_notes										= new Locator(LocatorType.id, "customerNotes");
	Locator	engSupport_submit										= new Locator(LocatorType.id, "save_request_btn");
	Locator	engSupport_submit_Confirm						= new Locator(LocatorType.xpath, "//div[@class='ui-dialog-buttonset']//button[text()='Yes']");
	Locator	engSupport_submit_Decline						= new Locator(LocatorType.xpath, "//div[@class='ui-dialog-buttonset']//button[text()='No']");
	Locator	engSupport_dataValidationPopId			= new Locator(LocatorType.xpath, "//span[text()='Data Validation']");
	Locator	engSupport_closeBtn									= new Locator(LocatorType.xpath, "//div[@id='datavalidationpopup']/following-sibling::div//button");
	Locator	engSupport_errorTxt									= new Locator(LocatorType.id, "datavalidationpopup");
	Locator	engSupport_fromTitle								= new Locator(LocatorType.xpath, "//div[@class='ux-panl-header']/h2");
	Locator	engSupport_requesteInfo							= new Locator(LocatorType.xpath, "//div[@id='requestInfo-header']/a/span");
	Locator	engSupport_managerName							= new Locator(LocatorType.id, "mgrName");
	Locator	engSupport_addFiles												= new Locator(LocatorType.id, "moreAttachments");

	// Xpaths when Action Type is Console Port.
	DynamicLocator	engSupport_extraFields_deviceName						= new DynamicLocator(LocatorType.xpath, "//td[text()=%d]/following-sibling::td//textarea[@name='deviceName ']");
	DynamicLocator	engSupport_extraFields_deviceModel					= new DynamicLocator(LocatorType.xpath, "//td[text()=%d]/following-sibling::td//textarea[@name='deviceModel ']");
	DynamicLocator	engSupport_extraFields_dataCenterLoc_txt		= new DynamicLocator(LocatorType.xpath, "//td[text()=%d]/following-sibling::td//textarea[@name='dataCenterLocation ']");
	DynamicLocator	engSupport_extraFields_dataCenterLoc_select	= new DynamicLocator(LocatorType.xpath, "//td[text()=%d]/following-sibling::td//select[@name='dataCenterLocation ']");
	DynamicLocator	engSupport_extraFields_serverRoom_txt				= new DynamicLocator(LocatorType.xpath, "//td[text()=%d]/following-sibling::td//textarea[@name='serverRoom ']");
	DynamicLocator	engSupport_extraFields_serverRoom_select		= new DynamicLocator(LocatorType.xpath, "//td[text()=%d]/following-sibling::td//select[contains(@name,'serverRoom')]");
	DynamicLocator	engSupport_extraFields_deviceType						= new DynamicLocator(LocatorType.xpath, "//td[text()=%d]/following-sibling::td//select[contains(@name,'deviceType')]");
	DynamicLocator	engSupport_extraFields_flowControl					= new DynamicLocator(LocatorType.xpath, "//td[text()=%d]/following-sibling::td//select[contains(@name,'flowControl')]");
	DynamicLocator	engSupport_extraFields_dataBits							= new DynamicLocator(LocatorType.xpath, "//td[text()=%d]/following-sibling::td//select[contains(@name,'dataBits')]");
	DynamicLocator	engSupport_extraFields_baudRate							= new DynamicLocator(LocatorType.xpath, "//td[text()=%d]/following-sibling::td//select[contains(@name,'baudRate')]");
	DynamicLocator	engSupport_extraFields_saveRule							= new DynamicLocator(LocatorType.xpath, "//td[text()=%d]/following-sibling::td//img[contains(@title,'Save')]");
	DynamicLocator	engSupport_extraFields_cancelRule						= new DynamicLocator(LocatorType.xpath, "//td[text()=%d]/following-sibling::td//img[contains(@title,'Cancel')]");
	DynamicLocator	engSupport_extraFields_editRule							= new DynamicLocator(LocatorType.xpath, "//td[text()=%d]/following-sibling::td//img[contains(@title,'Edit')]");
	DynamicLocator	engSupport_extraFields_updateRule						= new DynamicLocator(LocatorType.xpath, "//td[text()=%d]/following-sibling::td//img[contains(@title,'Update')]");
	DynamicLocator	engSupport_extraFields_deleteRule						= new DynamicLocator(LocatorType.xpath, "//span[contains(text(),'%s')]/parent::td/following-sibling::td//img[contains(@title, 'Delete')]");
	DynamicLocator	engSupport_extraFields_updateddeviceName		= new DynamicLocator(LocatorType.xpath, "//td[text()=%d]/following-sibling::td//span[contains(text(),'%s')]");

	// Xpaths when Action Type is Network Port.
	Locator	engSupport_networkPt_RequestType_sel		= new Locator(LocatorType.id, "reqType");
	Locator	engSupport_networkPt_decom_esc_inp			= new Locator(LocatorType.id, "escNumber");
	Locator	engSupport_networkPt_InterfaceType_sel	= new Locator(LocatorType.id, "interfaceType");
	Locator	engSupport_networkPt_speed_sel					= new Locator(LocatorType.id, "speed");
	Locator	engSupport_networkPt_cableSuffix_sel		= new Locator(LocatorType.id, "cableSuffix");
	Locator	engSupport_networkPt_dataCenter_sel			= new Locator(LocatorType.id, "dataCenter");
	Locator	engSupport_networkPt_requiredPort_sel		= new Locator(LocatorType.id, "requiredPort");
	Locator	engSupport_networkPt_ida_inp						= new Locator(LocatorType.id, "ida");
	Locator	engSupport_networkPt_vlan_inp						= new Locator(LocatorType.id, "vlan");
	Locator	engSupport_networkPt_serverName_inp			= new Locator(LocatorType.id, "serverName");

	public EngineeringAndSupportPage() {

		rqstInfPage = PageFactory.newInstance(RequestInformationPage.class);

	}
	public void populateData(EngineeringAndSupport engSupp) {
		/*
		 * On IE browser, sometime through native sendKeys method we are not able to
		 * enter text. Also sendkeys method is bit slow in entering text which overall
		 * increasing the time. Therefore we are using Javascript executor methodfor
		 * entering text.
		 */
		if (!(engSupp.RequestTitle.equalsIgnoreCase(""))) {
			enterTextThorughScript(engSupport_RequestTitle, engSupp.RequestTitle);
		}
		if (!(engSupp.WorkedInUnitedStates.equalsIgnoreCase(""))) {
			selectValueByVisibleText(engSupport_workedInUsa, engSupp.WorkedInUnitedStates);
		}
		if (!(engSupp.ActionType.equalsIgnoreCase(""))) {
			selectValueByVisibleText(engSupport_action, engSupp.ActionType);
			switch (engSupp.ActionType) {
			case "Console Port ( Raritan )":
				populateConsolePortFields(engSupp.cortianExtraFields);
				break;
			case "Network Ports":
				populateNetworkPortFields(engSupp.networkPortFields);
				break;

			}
		}
		if (!(engSupp.DetailedDescription.equalsIgnoreCase(""))) {
			enterTextThorughScript(engSupport_detailedDescription, engSupp.DetailedDescription);
		}
		if (!(engSupp.PeakSesaon.equalsIgnoreCase(""))) {
			enterTextThorughScript(engSupport_peakSesaon, engSupp.PeakSesaon);
		}
		if (!(engSupp.ImpactIfAfterPeakSeason.equalsIgnoreCase(""))) {
			enterTextThorughScript(engSupport_impactIfAfterPeakSeason, engSupp.ImpactIfAfterPeakSeason);
		}
		if (!(engSupp.PerformedScheduled.equalsIgnoreCase(""))) {
			enterTextThorughScript(engSupport_whyPerformedScheduled, engSupp.PerformedScheduled);
		}

		if ((!(engSupp.attachments.equalsIgnoreCase(""))) || engSupp.attachments != null) {
			/*
			 * Need AutoIt tool for file uploading will work on this later
			 * 
			 */
			addAttachment(engSupport_attachments, engSupp.attachments);
			clickElement(engSupport_addFiles);
		}
		if ((!(engSupp.notes.equalsIgnoreCase(""))) || engSupp.notes != null) {
			enterTextThorughScript(engSupport_notes, engSupp.notes);
		}

	}

	/**
	 * @param networkPortFields
	 */
	public void populateNetworkPortFields(String networkPortFields) {
		JSONObject networkPortdata = new JSONObject(networkPortFields);

		enterTextThorughScript(engSupport_networkPt_serverName_inp, networkPortdata.getString("serverName"));
		selectValueByVisibleText(engSupport_networkPt_RequestType_sel, networkPortdata.getString("requestType"));
		if (networkPortdata.getString("requestType").equalsIgnoreCase("Decommission")) {
			enterTextThorughScript(engSupport_networkPt_decom_esc_inp, networkPortdata.getString("ESC"));
		}
		selectValueByVisibleText(engSupport_networkPt_InterfaceType_sel, networkPortdata.getString("interface"));
		selectValueByVisibleText(engSupport_networkPt_speed_sel, networkPortdata.getString("speed"));
		enterTextThorughScript(engSupport_networkPt_vlan_inp, networkPortdata.getString("vlan"));
		selectValueByVisibleText(engSupport_networkPt_cableSuffix_sel, networkPortdata.getString("cableSuffix"));
		selectValueByVisibleText(engSupport_networkPt_dataCenter_sel, networkPortdata.getString("datacenter"));
		enterTextThorughScript(engSupport_networkPt_ida_inp, networkPortdata.getString("ida"));
		selectValueByVisibleText(engSupport_networkPt_requiredPort_sel, networkPortdata.getString("portsRequired"));

	}

	/*
	 * public void populateData1(EngineeringAndSupport engSupp) { // Now able to
	 * enter text through normal script so using Javascript executor.
	 * enterText(engSupport_RequestTitle, engSupp.RequestTitle); if
	 * (!(engSupp.WorkedInUnitedStates.equalsIgnoreCase(""))) {
	 * selectValueByVisibleText(engSupport_workedInUsa,
	 * engSupp.WorkedInUnitedStates); } if
	 * (!(engSupp.ActionType.equalsIgnoreCase(""))) {
	 * selectValueByVisibleText(engSupport_action, engSupp.ActionType); }
	 * enterText(engSupport_detailedDescription, engSupp.DetailedDescription);
	 * enterText(engSupport_peakSesaon, engSupp.PeakSesaon);
	 * enterText(engSupport_impactIfAfterPeakSeason,
	 * engSupp.ImpactIfAfterPeakSeason); enterText(engSupport_whyPerformedScheduled,
	 * engSupp.PerformedScheduled); if (engSupp.attachments != null) {
	 * 
	 * Need AutoIt tool for file uploading will work on this later
	 * 
	 * 
	 * } if (engSupp.notes != null) { enterText(engSupport_notes, engSupp.notes); }
	 * 
	 * }
	 */

	public void submitForm() {
		clickElement(engSupport_submit);
	}

	public void submitConfirm() {
		// highLight(engSupport_submit_Confirm);
		clickElement(engSupport_submit_Confirm);
	}

	public void submitDecline() {
		// highLight(engSupport_submit_Confirm);
		clickElement(engSupport_submit_Confirm);
	}

	public void verifyDataValidationPopUpAppears() {
		boolean result = isElementExist(engSupport_dataValidationPopId);
		assertEquals(result, true, "Validation Pop Up did not appear on the page");
	}

	public void verifyErrorText(String errorText) {
		String actualErrorText = getText(engSupport_errorTxt);
		logger.info("Actual Error text is :" + actualErrorText);
		assertEquals(actualErrorText, errorText, "Actual Error " + actualErrorText + " did not match with ExpectedText " + errorText);
	}

	/**
	 * 
	 */
	public void closePopup() {
		// highLight(engSupport_closeBtn);
		clickElement(engSupport_closeBtn);
		waitForElementTobeDisappear(engSupport_dataValidationPopId);

	}

	/**
	 * To Check whether user in already on Engineering and Support Page
	 * 
	 * @return
	 */
	public boolean isEngAndSupportPage() {
		if (isElementExist(engSupport_fromTitle)) {
			String checkTitle = getText(engSupport_fromTitle);
			if (checkTitle.equalsIgnoreCase("Engineering And Support"))
				return true;
		}

		return false;

	}

	/**
	 * 
	 */
	public void checkRequesterInformation(String expManagerName) {
		clickElement(engSupport_requesteInfo);
		String managerName = getAttribute(engSupport_managerName, "value");
		assertEquals(managerName, expManagerName);

	}

	public void populateConsolePortFields(String extraFields) {
		JSONArray extraFieldsArray = new JSONArray(extraFields);
		int rulesCreated = 1;
		for (int i = 0; i < extraFieldsArray.length(); i++) {
			JSONObject dataset = extraFieldsArray.getJSONObject(i);
			String actionTobePerformed = dataset.getString("action");
			switch (actionTobePerformed) {
			case "Save":
				String deviceName = dataset.getString("deviceName");
				populateConsolePortFields(rulesCreated, dataset);
				clickElement(engSupport_extraFields_saveRule.format(rulesCreated));
				assertEquals(isElementExist(engSupport_extraFields_updateddeviceName.format(rulesCreated, deviceName)), true);
				rulesCreated++;
				break;
			case "Edit":
				int rowToBeEdited = Integer.parseInt(dataset.getString("rowToBeEdited"));
				String deviceNametoBeUpdated = dataset.getString("deviceName");
				clickElement(engSupport_extraFields_editRule.format(rowToBeEdited));
				// Pass which row ID needs to be edited.
				populateConsolePortFields(rowToBeEdited, dataset);
				clickElement(engSupport_extraFields_updateRule.format(rowToBeEdited));
				// To validate updated device Name is reflecting.
				assertEquals(isElementExist(engSupport_extraFields_updateddeviceName.format(rowToBeEdited, deviceNametoBeUpdated)), true);
				break;
			case "Delete":
				clickElement(engSupport_extraFields_deleteRule.format(dataset.getString("deviceName")));
				// To validate row get deleted from table
				assertEquals(isElementExist(engSupport_extraFields_deleteRule.format(dataset.getString("deviceName"))), false);
				rulesCreated--;
				break;
			case "Cancel":
				populateConsolePortFields(rulesCreated, dataset);
				clickElement(engSupport_extraFields_cancelRule.format(rulesCreated));
				String actualString = getAttribute(engSupport_extraFields_deviceName.format(rulesCreated), "value");
				// To validate on Cancel fields will get blank.
				assertEquals(actualString, "");
				break;
			default:
				try {
					throw new Exception("Atleast provide one action type in you JSON data");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}

		}

	}

	/**
	 * @param string
	 * @param dataset
	 */
	private void populateConsolePortFields(int row, JSONObject dataset) {
		// TODO Auto-generated method stub
		enterTextThorughScript(engSupport_extraFields_deviceName.format(row), dataset.getString("deviceName"));
		enterTextThorughScript(engSupport_extraFields_deviceModel.format(row), dataset.getString("deviceModel"));
		selectValueByVisibleText(engSupport_extraFields_dataCenterLoc_select.format(row), dataset.getString("dataCenterLocation"));
		if (dataset.getString("dataCenterLocation").equalsIgnoreCase("Other")) {
			enterTextThorughScript(engSupport_extraFields_dataCenterLoc_txt.format(row), dataset.getString("datacenterLocationtxt"));
		}
		selectValueByVisibleText(engSupport_extraFields_serverRoom_select.format(row), dataset.getString("serverRoom"));
		if (dataset.getString("serverRoom").equalsIgnoreCase("Other")) {
			enterTextThorughScript(engSupport_extraFields_serverRoom_txt.format(row), dataset.getString("serverRoomtxt"));
		}
		selectValueByVisibleText(engSupport_extraFields_deviceType.format(row), dataset.getString("deviceType"));
		if (!(dataset.getString("baudRate")).equals("")) {
			selectValueByVisibleText(engSupport_extraFields_baudRate.format(row), dataset.getString("baudRate"));
		}
		if (!(dataset.getString("flowControl")).equals("")) {
			selectValueByVisibleText(engSupport_extraFields_flowControl.format(row), dataset.getString("flowControl"));
		}
		if (!(dataset.getString("databits")).equals("")) {
			selectValueByVisibleText(engSupport_extraFields_dataBits.format(row), dataset.getString("databits"));
		}

	}

	public void verifyData(EngineeringAndSupport engSupp) {
		assertEquals(getAttribute(engSupport_RequestTitle,"value").trim(), engSupp.RequestTitle);
		assertEquals(getSelectedValue(engSupport_workedInUsa).trim(), engSupp.WorkedInUnitedStates);
		assertEquals(getAttribute(engSupport_detailedDescription,"value").trim(), engSupp.DetailedDescription);
		if (!(engSupp.PeakSesaon.equalsIgnoreCase(""))) {
			assertEquals(getAttribute(engSupport_peakSesaon,"value").trim(), engSupp.PeakSesaon);

		}
		if (!(engSupp.ImpactIfAfterPeakSeason.equalsIgnoreCase(""))) {
			assertEquals(getAttribute(engSupport_impactIfAfterPeakSeason,"value").trim(), engSupp.ImpactIfAfterPeakSeason);

		}
		if (!(engSupp.PerformedScheduled.equalsIgnoreCase(""))) {
			assertEquals(getAttribute(engSupport_whyPerformedScheduled,"value").trim(), engSupp.PerformedScheduled);

		}
		String actionType = getSelectedValue(engSupport_action);
		assertEquals(actionType, engSupp.ActionType);

		switch (actionType) {
		case "Console Port ( Raritan )":
			verifyConsolePortFields(engSupp.cortianExtraFields);
			break;
		case "Network Ports":
			verifyNetworkPortFields(engSupp.networkPortFields);
			break;
		}
		}
		private void verifyConsolePortFields(String cortianExtraFields) {
			int actualRows = getXpathCount("//app-esrequestinformation//table/tbody//tr");
			JSONArray extraFieldsArray = new JSONArray(cortianExtraFields);
			int expRows = extraFieldsArray.length();
			assertEquals(actualRows-1, expRows, "Actual table count is not matching with expected");
			for (int i = 0; i < extraFieldsArray.length(); i++) {
				JSONObject dataset = extraFieldsArray.getJSONObject(i);
				rqstInfPage.verifyconsoleportRow(dataset,i+1);
			}
	}
		public void verifyNetworkPortFields(String networkPortFields) {
			JSONObject networkPortdata = new JSONObject(networkPortFields);

			assertEquals(getAttribute(engSupport_networkPt_serverName_inp,"value").trim(), networkPortdata.getString("serverName"));
			assertEquals(getSelectedValue(engSupport_networkPt_RequestType_sel), networkPortdata.getString("requestType"));
			if (getSelectedValue(engSupport_networkPt_RequestType_sel).equalsIgnoreCase("Decommission")) {
				assertEquals(getAttribute(engSupport_networkPt_decom_esc_inp,"value").trim(), networkPortdata.getString("ESC"));
			}
			assertEquals(getSelectedValue(engSupport_networkPt_InterfaceType_sel), networkPortdata.getString("interface"));
			assertEquals(getSelectedValue(engSupport_networkPt_speed_sel), networkPortdata.getString("speed"));
			assertEquals(getAttribute(engSupport_networkPt_vlan_inp,"value").trim(), networkPortdata.getString("vlan"));
			assertEquals(getSelectedValue(engSupport_networkPt_cableSuffix_sel), networkPortdata.getString("cableSuffix"));
			assertEquals(getSelectedValue(engSupport_networkPt_dataCenter_sel), networkPortdata.getString("datacenter"));
			assertEquals(getAttribute(engSupport_networkPt_ida_inp,"value").trim(), networkPortdata.getString("ida"));
			assertEquals(getSelectedValue(engSupport_networkPt_requiredPort_sel), networkPortdata.getString("portsRequired"));

		}
}
