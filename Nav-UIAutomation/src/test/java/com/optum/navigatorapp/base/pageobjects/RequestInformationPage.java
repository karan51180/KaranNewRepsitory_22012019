/**
 * 
 */
package com.optum.navigatorapp.base.pageobjects;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Field;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.optum.automation.coreframework.baseclasses.BasePageFunctions;
import com.optum.automation.coreframework.browser.Locator;
import com.optum.automation.coreframework.browser.LocatorType;
import com.optum.automation.coreframework.browser.Locator.DynamicLocator;
import com.optum.automation.coreframework.browser.PageFactory.Page;
import com.optum.navigatorapp.intakerequest.databeans.EngineeringAndSupport;

/**
 * @author mshar107
 *
 */
@Page
public class RequestInformationPage extends BasePageFunctions {
	Locator	requestType					= new Locator(LocatorType.xpath, "//td[text()='Request Type']/following-sibling::td[1]");
	Locator	requestID						= new Locator(LocatorType.xpath, "//td[text()='Request ID']/following-sibling::td[1]");
	Locator	requestTitle				= new Locator(LocatorType.xpath, "//td[text()='Request Title']/following-sibling::td[1]");
	Locator	ContractuallyObl		= new Locator(LocatorType.xpath, "//td[contains(text(),'Contractually')]/following-sibling::td[1]");
	Locator	actionType					= new Locator(LocatorType.xpath, "//td[text()='Action Type:']/following-sibling::td[1]");
	Locator	detailedDescription	= new Locator(LocatorType.xpath, "//td[text()='Detailed Description:']/following-sibling::td[1]");
	Locator	duringPeakSeason		= new Locator(LocatorType.xpath, "//td[contains(text(),'during peak season')]/following-sibling::td[1]");
	Locator	afterPeakSeason			= new Locator(LocatorType.xpath, "//td[contains(text(),'after peak season')]/following-sibling::td[1]");
	Locator	scheduledStatus			= new Locator(LocatorType.xpath, "//td[contains(text(),'scheduled')]/following-sibling::td[1]");

	// Xpath when Action Type is network Port

	Locator	networkPt_RequestType		= new Locator(LocatorType.xpath, "//td[text()='Request Type:']/following-sibling::td[1]");
	Locator	networkPt_decom_esc			= new Locator(LocatorType.xpath, "//td[text()='ESC Number:']/following-sibling::td[1]");
	Locator	networkPt_InterfaceType	= new Locator(LocatorType.xpath, "//td[text()='Interface Type:']/following-sibling::td[1]");
	Locator	networkPt_speed					= new Locator(LocatorType.xpath, "//td[text()='Speed:']/following-sibling::td[1]");
	Locator	networkPt_cableSuffix		= new Locator(LocatorType.xpath, "//td[text()='Cable Suffix:']/following-sibling::td[1]");
	Locator	networkPt_dataCenter		= new Locator(LocatorType.xpath, "//td[text()='Data Center:']/following-sibling::td[1]");
	Locator	networkPt_requiredPort	= new Locator(LocatorType.xpath, "//td[text()='Ports Required:']/following-sibling::td[1]");
	Locator	networkPt_ida						= new Locator(LocatorType.xpath, "//td[text()='IDA (Locations):']/following-sibling::td[1]");
	Locator	networkPt_vlan					= new Locator(LocatorType.xpath, "//td[text()='VLAN:']/following-sibling::td[1]");
	Locator	networkPt_serverName		= new Locator(LocatorType.xpath, "//td[text()='Server Name']/following-sibling::td[1]");

	// Xpath when action Type is console port
	DynamicLocator	extraFields_deviceName		= new DynamicLocator(LocatorType.xpath, "//tr[%d]//td[2]/span");
	DynamicLocator	extraFields_deviceModel		= new DynamicLocator(LocatorType.xpath, "//tr[%d]//td[3]/span");
	DynamicLocator	extraFields_datacenterLoc	= new DynamicLocator(LocatorType.xpath, "//tr[%d]//td[4]/span");
	DynamicLocator	extraFields_serverRoom		= new DynamicLocator(LocatorType.xpath, "//tr[%d]//td[5]/span");
	DynamicLocator	extraFields_deviceType		= new DynamicLocator(LocatorType.xpath, "//tr[%d]//td[6]/span");
	DynamicLocator	extraFields_baudRate			= new DynamicLocator(LocatorType.xpath, "//tr[%d]//td[7]/span");
	DynamicLocator	extraFields_flowControl		= new DynamicLocator(LocatorType.xpath, "//tr[%d]//td[8]/span");
	DynamicLocator	extraFields_dataBits			= new DynamicLocator(LocatorType.xpath, "//tr[%d]//td[9]/span");

	/**
	 * 
	 * @param engSupp
	 * @param requestId
	 */
	public void verifyRequestInformation(EngineeringAndSupport engSupp, String requestId) {

		assertEquals(getText(requestType), "ENGINEERING AND SUPPORT");
		assertEquals(getText(requestID), requestId);
		assertEquals(getText(requestTitle).trim(), engSupp.RequestTitle);
		assertEquals(getText(ContractuallyObl), engSupp.WorkedInUnitedStates);
		assertEquals(getText(detailedDescription).trim(), engSupp.DetailedDescription);
		if (!(engSupp.PeakSesaon.equalsIgnoreCase(""))) {
			assertEquals(getText(duringPeakSeason).trim(), engSupp.PeakSesaon);
		}
		if (!(engSupp.ImpactIfAfterPeakSeason.equalsIgnoreCase(""))) {
			assertEquals(getText(afterPeakSeason).trim(), engSupp.ImpactIfAfterPeakSeason);
		}
		if (!(engSupp.PerformedScheduled.equalsIgnoreCase(""))) {
			assertEquals(getText(scheduledStatus).trim(), engSupp.PerformedScheduled);
		}
		assertEquals(getText(actionType), engSupp.ActionType);
		switch (engSupp.ActionType) {
		case "Console Port ( Raritan )":
			verifyConsolePortFields(engSupp.cortianExtraFields);
			break;
		case "Network Ports":
			verifyNetworkPortFields(engSupp.networkPortFields);
			break;

		}

	}

	/**
	 * @param cortianExtraFields
	 */
	private void verifyConsolePortFields(String cortianExtraFields) {
		// TODO Auto-generated method stub
		int actualRows = getXpathCount("//app-engineeringsupportstaticrequestinformation//table[@class=\"ux-tabl-data\"]/tbody//tr");
		JSONArray extraFieldsArray = new JSONArray(cortianExtraFields);
		int expRows = extraFieldsArray.length();
		assertEquals(actualRows, expRows, "Actual table count is not matching with expected");
		for (int i = 0; i < extraFieldsArray.length(); i++) {
			JSONObject dataset = extraFieldsArray.getJSONObject(i);
			verifyconsoleportRow(dataset, i + 1);
		}

	}

	/**
	 * @param dataset
	 */
	public void verifyconsoleportRow(JSONObject dataset, int row) {

		assertEquals(getText(extraFields_deviceName.format(row)).trim(), dataset.get("deviceName"));
		assertEquals(getText(extraFields_deviceModel.format(row)).trim(), dataset.get("deviceModel"));
		assertEquals(getText(extraFields_deviceType.format(row)).trim(), dataset.get("deviceType"));
		assertEquals(getText(extraFields_serverRoom.format(row)).trim(), dataset.get("serverRoom"));
		assertEquals(getText(extraFields_datacenterLoc.format(row)).trim(), dataset.get("dataCenterLocation"));
		assertEquals(getText(extraFields_dataBits.format(row)).trim(), dataset.get("databits"));
		assertEquals(getText(extraFields_baudRate.format(row)).trim(), dataset.get("baudRate"));
		assertEquals(getText(extraFields_flowControl.format(row)).trim(), dataset.get("flowControl"));

	}

	/**
	 * @param networkPortFields
	 */
	private void verifyNetworkPortFields(String networkPortFields) {
		JSONObject networkPortdata = new JSONObject(networkPortFields);
		// This field is currently not getting populated.
		// assertEquals(getText(networkPt_serverName_inp),
		// networkPortdata.getString("serverName"));
		// adding extra semicolon;
		// assertEquals(getText(networkPt_RequestType).trim(),
		// networkPortdata.getString("requestType"));

		assertEquals(getText(networkPt_decom_esc).trim(), networkPortdata.getString("ESC"));
		assertEquals(getText(networkPt_InterfaceType).trim(), networkPortdata.getString("interface"));
		assertEquals(getText(networkPt_speed), networkPortdata.getString("speed"));
		assertEquals(getText(networkPt_vlan).trim(), networkPortdata.getString("vlan"));
		assertEquals(getText(networkPt_cableSuffix).trim(), networkPortdata.getString("cableSuffix"));
		assertEquals(getText(networkPt_dataCenter).trim(), networkPortdata.getString("datacenter"));
		assertEquals(getText(networkPt_ida).trim(), networkPortdata.getString("ida"));
		assertEquals(getText(networkPt_requiredPort).trim(), networkPortdata.getString("portsRequired"));

	}
}
