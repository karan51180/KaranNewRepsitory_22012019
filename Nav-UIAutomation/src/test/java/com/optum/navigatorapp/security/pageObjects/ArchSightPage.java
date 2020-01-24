package com.optum.navigatorapp.security.pageObjects;
/*
 * @author Venu
 */

import org.testng.log4testng.Logger;

import com.optum.automation.coreframework.baseclasses.BasePageFunctions;
import com.optum.automation.coreframework.browser.Locator;
import com.optum.automation.coreframework.browser.LocatorType;
import com.optum.automation.coreframework.browser.PageFactory.Page;
import com.optum.navigatorapp.security.databeans.ArchSight;

@Page
public class ArchSightPage extends BasePageFunctions {

	private static Logger logger = Logger.getLogger(ArchSightPage.class);

	Locator	archSight_requestTitle							= new Locator(LocatorType.id, "requestTitle");
	Locator	archSight_inegrationType						= new Locator(LocatorType.id, "integrationType");
	Locator	archSight_sourceEnvironment					= new Locator(LocatorType.id, "sourceEnvironment");
	Locator	archSight_actionPlanPex							= new Locator(LocatorType.id, "actionPlan");
	Locator	archSight_technicalSME							= new Locator(LocatorType.id, "technicalSME");
	Locator	archSight_helDeskGroup							= new Locator(LocatorType.id, "helpDeskGroup");
	Locator	archSight_projectName								= new Locator(LocatorType.id, "projectName");
	Locator	archSight_projectNumber							= new Locator(LocatorType.id, "projectNumber");
	Locator	archSight_productName								= new Locator(LocatorType.id, "applicationName");
	Locator	archSight_manufacturer							= new Locator(LocatorType.id, "manufacturer");
	Locator	archSight_versionNumber							= new Locator(LocatorType.id, "versionNumber");
	Locator	archSight_productType								= new Locator(LocatorType.id, "logType");
	Locator	archSight_aSKID											= new Locator(LocatorType.id, "askId");
	Locator	archSight_productDescription				= new Locator(LocatorType.id, "applicationDescription");
	Locator	archSight_reasonforIntegration			= new Locator(LocatorType.id, "integrationReason");
	Locator	archSight_loggingMethod							= new Locator(LocatorType.id, "loggingMethod");
	Locator	archSight_networkZone								= new Locator(LocatorType.id, "networkZone");
	Locator	archSight_flatFileTransportMethod		= new Locator(LocatorType.id, "flatFileTransportMethod");
	Locator	archSight_domain										= new Locator(LocatorType.id, "domain");
	Locator	archSight_estimatedEventCount				= new Locator(LocatorType.id, "estimatedEventCount");
	Locator	archSight_estimatedEventCountType		= new Locator(LocatorType.id, "estimatedEventCountType");
	Locator	archSight_hosts											= new Locator(LocatorType.id, "hosts");
	Locator	archSight_logMessageSamples					= new Locator(LocatorType.id, "logMessageSamples");
	Locator	archSight_actionableEvents					= new Locator(LocatorType.id, "actionableEvents");
	Locator	archSight_attachments								= new Locator(LocatorType.id, "attachments");
	Locator	archSight_notes											= new Locator(LocatorType.id, "customerNotes");
	Locator	archSight_addFiles									= new Locator(LocatorType.id, "moreAttachments");
	Locator	archSight_submit										= new Locator(LocatorType.id, "save_request_btn");
	Locator	archSight_cyberSecurityVerification	= new Locator(LocatorType.id, "cyberSecurityVerification");
	Locator	archSight_contentVerification				= new Locator(LocatorType.id, "contentVerification");
	Locator	archSight_deleteAttachment					= new Locator(LocatorType.className, "deleteExtraAttachment");
	Locator	archSight_cEFSyslog									= new Locator(LocatorType.id, "CEFSyslog");
	Locator	archSight_windowsEvents							= new Locator(LocatorType.id, "windowsEventDetails");
	Locator	archSight_closeBtn									= new Locator(LocatorType.xpath, "//div[@id='datavalidationpopup']/following-sibling::div//button");
	Locator	archSight_dataValidationPopId				= new Locator(LocatorType.xpath, "//span[text()='Data Validation']");
	Locator	archSight_submit_Confirm						= new Locator(LocatorType.xpath, "//div[@class='ui-dialog-buttonset']//button[text()='Yes']");
	Locator	archSight_submit_NoConfirm					= new Locator(LocatorType.xpath, "//div[@class='ui-dialog-buttonset']//button[text()='No']");
	Locator	archSight_dURequest									= new Locator(LocatorType.id, "duRequest");
	Locator	archSight_requestCategory						= new Locator(LocatorType.id, "requestType");
	Locator	archSight_requestSubCategory				= new Locator(LocatorType.id, "requestSubCategory");
	Locator	archSight_requestDepartment					= new Locator(LocatorType.id, "requestorDept");
	Locator	archSight_details										= new Locator(LocatorType.id, "details");
	Locator	archSight_justificationStatement		= new Locator(LocatorType.id, "justificationStatement");
	Locator	archSight_requestTypeOtherValue			= new Locator(LocatorType.id, "requestTypeOtherValue");
	Locator	archSight_zoneOtherValue						= new Locator(LocatorType.id, "zoneOtherValue");
	Locator	archSight_zone											= new Locator(LocatorType.id, "zone");

	public void populateData(ArchSight archSight) {
		/*
		 * On IE browser, sometime through native sendKeys method we are not able to
		 * enter text. Also sendkeys method is bit slow in entering text which overall
		 * increasing the time. Therefore we are using Javascript executor methodfor
		 * entering text.
		 */

		// wait till page archsight page is loaded.

		waitForElementToAppear(archSight_requestTitle);

		if (!(archSight.requestTitle.equalsIgnoreCase(""))) {
			enterText(archSight_requestTitle, archSight.requestTitle);
		}

		if (!(archSight.inegrationType.equalsIgnoreCase(""))) {
			selectValueByVisibleText(archSight_inegrationType, archSight.inegrationType);

		}

		if (!(archSight.dURequest.equalsIgnoreCase(""))) {
			clickElement(archSight_dURequest);

		}

		if (!(archSight.requestCategory.equalsIgnoreCase(""))) {
			selectValueByVisibleText(archSight_requestCategory, archSight.requestCategory);

		}

		if (!(archSight.requestSubCategory.equalsIgnoreCase(""))) {
			waitForElementToAppear(archSight_requestSubCategory);
			selectValueByVisibleText(archSight_requestSubCategory, archSight.requestSubCategory);

		}

		if (!(archSight.requestTypeOtherValue.equalsIgnoreCase(""))) {
			enterText(archSight_requestTypeOtherValue, archSight.requestTypeOtherValue);

		}

		if (!(archSight.sourceEnvironment.equalsIgnoreCase(""))) {
			selectValueByVisibleText(archSight_sourceEnvironment, archSight.sourceEnvironment);

		}

		if (!(archSight.cyberSecurityVerification.equalsIgnoreCase(""))) {

			clickElement(archSight_cyberSecurityVerification);
		}

		if (!(archSight.actionPlanPex.equalsIgnoreCase(""))) {
			enterText(archSight_actionPlanPex, archSight.actionPlanPex);

		}

		if (!(archSight.technicalSME.equalsIgnoreCase(""))) {
			enterText(archSight_technicalSME, archSight.technicalSME);

		}

		if (!(archSight.helDeskGroup.equalsIgnoreCase(""))) {
			enterText(archSight_helDeskGroup, archSight.helDeskGroup);

		}

		if (!(archSight.projectName.equalsIgnoreCase(""))) {
			enterText(archSight_projectName, archSight.projectName);

		}

		if (!(archSight.projectNumber.equalsIgnoreCase(""))) {
			enterText(archSight_projectNumber, archSight.projectNumber);

		}

		if (!(archSight.productName.equalsIgnoreCase(""))) {
			enterText(archSight_productName, archSight.productName);

		}

		if (!(archSight.manufacturer.equalsIgnoreCase(""))) {
			enterText(archSight_manufacturer, archSight.manufacturer);

		}

		if (!(archSight.versionNumber.equalsIgnoreCase(""))) {
			enterText(archSight_versionNumber, archSight.versionNumber);

		}

		if (!(archSight.productType.equalsIgnoreCase(""))) {
			selectValueByVisibleText(archSight_productType, archSight.productType);

		}

		if (!(archSight.aSKID.equalsIgnoreCase(""))) {
			enterText(archSight_aSKID, archSight.aSKID);

		}

		if (!(archSight.productDescription.equalsIgnoreCase(""))) {
			enterText(archSight_productDescription, archSight.productDescription);

		}

		if (!(archSight.reasonforIntegration.equalsIgnoreCase(""))) {
			enterText(archSight_reasonforIntegration, archSight.reasonforIntegration);

		}

		if (!(archSight.contentVerification.equalsIgnoreCase(""))) {
			clickElement(archSight_contentVerification);

		}

		if (!(archSight.loggingMethod.equalsIgnoreCase(""))) {
			selectValueByVisibleText(archSight_loggingMethod, archSight.loggingMethod);

		}

		if (!(archSight.cEFSyslog.equalsIgnoreCase(""))) {
			selectValueByVisibleText(archSight_cEFSyslog, archSight.cEFSyslog);

		}

		if (!(archSight.networkZone.equalsIgnoreCase(""))) {
			selectValueByVisibleText(archSight_networkZone, archSight.networkZone);

		}

		if (!(archSight.zone.equalsIgnoreCase(""))) {
			selectValueByVisibleText(archSight_zone, archSight.zone);

		}

		if (!(archSight.zoneOtherValue.equalsIgnoreCase(""))) {
			enterText(archSight_zoneOtherValue, archSight.zoneOtherValue);

		}

		if (!(archSight.flatFileTransportMethod.equalsIgnoreCase(""))) {
			selectValueByVisibleText(archSight_flatFileTransportMethod, archSight.flatFileTransportMethod);

		}

		if (!(archSight.domain.equalsIgnoreCase(""))) {
			enterText(archSight_domain, archSight.domain);

		}

		if (!(archSight.estimatedEventCount.equalsIgnoreCase(""))) {
			enterText(archSight_estimatedEventCount, archSight.estimatedEventCount);

		}

		if (!(archSight.estimatedEventCountType.equalsIgnoreCase(""))) {
			selectValueByVisibleText(archSight_estimatedEventCountType, archSight.estimatedEventCountType);

		}

		if (!(archSight.hosts.equalsIgnoreCase(""))) {
			enterText(archSight_hosts, archSight.hosts);

		}

		if (!(archSight.windowsEventsDetails.equalsIgnoreCase(""))) {
			enterText(archSight_windowsEvents, archSight.windowsEventsDetails);

		}

		if (!(archSight.logMessageSamples.equalsIgnoreCase(""))) {
			enterText(archSight_logMessageSamples, archSight.logMessageSamples);

		}

		if (!(archSight.actionableEvents.equalsIgnoreCase(""))) {
			enterText(archSight_actionableEvents, archSight.actionableEvents);

		}

		if (!(archSight.requestDepartment.equalsIgnoreCase(""))) {
			enterText(archSight_requestDepartment, archSight.requestDepartment);

		}

		if (!(archSight.details.equalsIgnoreCase(""))) {
			enterText(archSight_details, archSight.details);

		}

		if (!(archSight.justificationStatement.equalsIgnoreCase(""))) {
			enterText(archSight_justificationStatement, archSight.justificationStatement);

		}

		if (!(archSight.attachments.equalsIgnoreCase(""))) {
			addAttachment(archSight_attachments, archSight.attachments);
			clickElement(archSight_addFiles);
			waitFor(archSight_deleteAttachment);

		}

		if (!(archSight.notes.equalsIgnoreCase(""))) {
			enterText(archSight_notes, archSight.notes);

		}
	}

	public void submitForm() {
		clickElement(archSight_submit);
	}

	public void closePopup() {
		// highLight(engSupport_closeBtn);
		clickElement(archSight_closeBtn);
		waitForElementTobeDisappear(archSight_dataValidationPopId);

	}

	public void submitConfirm() {
		// highLight(engSupport_submit_Confirm);
		clickElement(archSight_submit_Confirm);
	}

	public void submitDecline() {
		// highLight(engSupport_submit_Confirm);
		clickElement(archSight_submit_NoConfirm);
	}

}
