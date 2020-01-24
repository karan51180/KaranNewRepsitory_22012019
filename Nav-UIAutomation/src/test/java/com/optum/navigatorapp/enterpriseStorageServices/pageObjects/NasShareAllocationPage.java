package com.optum.navigatorapp.enterpriseStorageServices.pageObjects;

import static org.testng.Assert.assertEquals;

import org.testng.log4testng.Logger;

import com.optum.automation.coreframework.baseclasses.BasePageFunctions;
import com.optum.automation.coreframework.browser.Locator;
import com.optum.automation.coreframework.browser.Locator.DynamicLocator;
import com.optum.automation.coreframework.browser.LocatorType;
import com.optum.automation.coreframework.browser.PageFactory.Page;
import com.optum.navigatorapp.enterpriseStorageServices.databeans.NasShareAllocation;

@Page
public class NasShareAllocationPage extends BasePageFunctions {
	private static Logger logger = Logger.getLogger(NasShareAllocationPage.class);

	/*
	 * Locators for creation nas share
	 */
	Locator					nas_requestTitle									= new Locator(LocatorType.id, "requestTitle");
	Locator					nas_requestType										= new Locator(LocatorType.id, "reqType");
	Locator					nas_infrastructureService					= new Locator(LocatorType.id, "isGlCode");
	Locator					nas_ppmoProjectNumber							= new Locator(LocatorType.xpath, ".//input[contains(@class ,'ppmofield')]");
	Locator					nas_askApplication								= new Locator(LocatorType.xpath, ".//input[contains(@class,'askfield')]");
	Locator					nas_nextButton										= new Locator(LocatorType.xpath, ".//button[@class='btn autobtn'][text()='Next']");
	Locator					nas_managerInfo										= new Locator(LocatorType.id, "mgrName");
	DynamicLocator	nas_extraFields_ppmoProjectNumber	= new DynamicLocator(LocatorType.xpath, ".//div[contains(@class,'ppmofield')]//li//span[text() = '%s']");
	DynamicLocator	nas_applicationOptions						= new DynamicLocator(LocatorType.xpath, ".//div[@class='col-sm-5']//input[@type='radio'][@value='%s']");
	DynamicLocator	nas_extraFields_askApplication		= new DynamicLocator(LocatorType.xpath, ".//div[contains(@class,'askfield')]//li//span[text()='%s']");

	/*
	 * Locators for Owner ship page
	 */
	Locator nas_adminGroup = new Locator(LocatorType.id, "adminGroup");

	/*
	 * Locators for Configuration Page
	 */

	Locator	nas_backUp													= new Locator(LocatorType.id, "ToBeBackedUp");
	Locator	nas_storage													= new Locator(LocatorType.id, "storage");
	Locator	nas_dataCenter											= new Locator(LocatorType.id, "dataCenter");
	Locator	nas_environZone											= new Locator(LocatorType.id, "envZone");
	Locator	nas_networkZone											= new Locator(LocatorType.id, "networkZone");
	Locator	nas_interfaceZone										= new Locator(LocatorType.id, "interfaceZone");
	Locator	nas_protocol												= new Locator(LocatorType.id, "Protocol");
	Locator	nas_shareName												= new Locator(LocatorType.id, "shareName");
	Locator	nas_globalGroups										= new Locator(LocatorType.id, "globalGroup");
	Locator	nas_searchGlobalgroups							= new Locator(LocatorType.id, "globalGroupID");
	Locator	nas_extraFields_globalGroups_Add		= new Locator(LocatorType.xpath, ".//div[@class='form-group row'][position( )=1]//button[text()='Add']");
	Locator	nas_globalGroupType									= new Locator(LocatorType.id, "globalGroupType");
	Locator	nas_serviceAccount									= new Locator(LocatorType.id, "serviceAccount");
	Locator	nas_searchServiceAccount						= new Locator(LocatorType.id, "serviceAccountID");
	Locator	nas_serviceAccountType							= new Locator(LocatorType.id, "serviceAccountType");
	Locator	nas_extraFields_serviceAccount_Add	= new Locator(LocatorType.xpath, ".//div[@class='form-group row'][position( )=5]//button[text()='Add']");

	DynamicLocator	nas_extraFields_globalGroups		= new DynamicLocator(LocatorType.xpath, ".//div[@class='form-group row' ][position()=1]//li//span[text()='%s']");
	DynamicLocator	nas_extraFields_serviceAccount	= new DynamicLocator(LocatorType.xpath, ".//div[@class='form-group row' ][position()=5]//li//span[text()='%s']");

	/*
	 * Locators for UserNotification Page
	 */

	Locator	nas_additionalUsers							= new Locator(LocatorType.xpath, ".//app-step4//input[contains(@class,'ppmofield')]");
	Locator	nas_extraFieldsAdditionalUsers	= new Locator(LocatorType.xpath, ".//app-step4//div[contains(@class,'ppmofield')]//li//span");
	Locator	nas_add_additionalUsers					= new Locator(LocatorType.xpath, ".//app-step4//button[text()='Add']");

	/*
	 * Locators for Review Page
	 */
	Locator	nas_review_checkbox	= new Locator(LocatorType.id, "agree");
	Locator	nas_submit_button		= new Locator(LocatorType.xpath, ".//button[text()='Submit']");
	Locator	nas_dialog_yes			= new Locator(LocatorType.xpath, ".//div[@class='ui-dialog-buttonset']//button[text()='Yes']");

	public void populateData(NasShareAllocation nasShare) {

		// waitFor(nas_requestTitle);
		if (!(nasShare.RequestTitle.equalsIgnoreCase(""))) {
			enterTextThorughScript(nas_requestTitle, nasShare.RequestTitle);
		}
		if (!(nasShare.RequestType.equalsIgnoreCase(""))) {
			selectValueByVisibleText(nas_requestType, nasShare.RequestType);
			switch (nasShare.RequestType) {
			case "Create New NAS Share":
				populateCreateNasShare(nasShare);
				break;
			case "Add/Change to Existing NAS Share":
				populateAddExistingNasShare(nasShare);
				break;
			case "Delete Existing NAS Share":
				populateDeleteExistingNasShare(nasShare);
				break;

			}
		}
	}

	public void populateCreateNasShare(NasShareAllocation nasShare) {
		if (!(nasShare.InfrastructureService.equalsIgnoreCase(""))) {
			selectValueByVisibleText(nas_infrastructureService, nasShare.InfrastructureService);
			if (nasShare.InfrastructureService.equalsIgnoreCase("Yes")) {
				if (!(nasShare.PPMOProjectNumber.equalsIgnoreCase(""))) {
					enterText(nas_ppmoProjectNumber, nasShare.PPMOProjectNumber);
					clickElement(nas_extraFields_ppmoProjectNumber.format(nasShare.PPMOProjectNumber.toUpperCase()));
				}
				clickElement(nas_applicationOptions.format(nasShare.ApplicationOptions));
				if (!nasShare.ApplicationOptions.equalsIgnoreCase("")) {
					enterText(nas_askApplication, nasShare.ASKApplicationName);
					clickElement(nas_extraFields_askApplication.format(nasShare.ASKApplicationName));
				}
			} else {
				/**
				 * If the Infrastructure was set to NO this will execute
				 */
			}
		}
		clickNextButton();

	}

	public void clickNextButton() {
		// TODO Auto-generated method stub
		clickElement(nas_nextButton);

	}

	public void populateAddExistingNasShare(NasShareAllocation nasShare) {

	}

	public void populateDeleteExistingNasShare(NasShareAllocation nasShare) {

	}

	public void checkRequesterInformation(String expManagerName) {

		String managerName = getAttribute(nas_managerInfo, "value");
		assertEquals(managerName, expManagerName);

	}

	public void populateAdminGroupData(NasShareAllocation nasShare) {
		if (!(nasShare.AdminGroup.equalsIgnoreCase(""))) {
			selectValueByVisibleText(nas_adminGroup, nasShare.AdminGroup);
			clickNextButton();
		}
	}

	public void populateConfigDetails(NasShareAllocation nasShare) {
		if (!(nasShare.Backup.equalsIgnoreCase(""))) {
			selectValueByVisibleText(nas_backUp, nasShare.Backup);
		}
		if (!(nasShare.Storage.equalsIgnoreCase(""))) {

			enterText(nas_storage, String.valueOf(nasShare.Storage));
			// assertEquals(actual, Expected);
		}
		if (!(nasShare.DataCenter.equalsIgnoreCase(""))) {
			selectValueByVisibleText(nas_dataCenter, nasShare.DataCenter);
		}
		if (!(nasShare.EnvironmentZone.equalsIgnoreCase(""))) {
			selectValueByVisibleText(nas_environZone, nasShare.EnvironmentZone);
		}
		if (!(nasShare.NetworkZone.equalsIgnoreCase(""))) {
			selectValueByVisibleText(nas_networkZone, nasShare.NetworkZone);
			if ((nasShare.NetworkZone).equals("Internet (DMZ)") && (!(nasShare.InterfaceZone.equalsIgnoreCase("")))) {
				selectValueByVisibleText(nas_interfaceZone, nasShare.InterfaceZone);
			}
		}
		if (!(nasShare.Protocol.equalsIgnoreCase(""))) {
			selectValueByVisibleText(nas_protocol, nasShare.Protocol);
			switch (nasShare.Protocol) {
			case "CIFS - Windows only NAS Share":
				populateShareInformation(nasShare);
				break;
			case "NFS - Unix only NAS Share":
				populateMountInformation(nasShare);
				break;

			case "Both - Windows and Unix NAS Share":
				populateShareAndMountInformation(nasShare);
				break;
			}
		}
	}

	private void populateShareInformation(NasShareAllocation nasShare) {
		// TODO Auto-generated method stub
		if (!(nasShare.ShareName.equalsIgnoreCase(""))) {
			enterText(nas_shareName, nasShare.ShareName);
		}
		/**
		 * Global Groups Adding
		 * 
		 */
		if (!(nasShare.GlobalGroupsForModify.equalsIgnoreCase(""))) {
			// Add modify server
			enterText(nas_globalGroups, nasShare.GlobalGroupsForModify);
			clickElement(nas_searchGlobalgroups);
			clickElement(nas_extraFields_globalGroups.format(nasShare.GlobalGroupsForModify));
			selectValueByVisibleText(nas_globalGroupType, "Modify");
			clickElement(nas_extraFields_globalGroups_Add);

		}
		if (!(nasShare.GlobalGroupsForReadOnly.equalsIgnoreCase(""))) {
			// Add read-only server
			enterText(nas_globalGroups, nasShare.GlobalGroupsForReadOnly);
			clickElement(nas_searchGlobalgroups);
			clickElement(nas_extraFields_globalGroups.format(nasShare.GlobalGroupsForReadOnly));
			selectValueByVisibleText(nas_globalGroupType, "Read Only");
			clickElement(nas_extraFields_globalGroups_Add);

		}
		/**
		 * Service Account Adding
		 * 
		 */
		if (!(nasShare.ServiceAccountForModify.equalsIgnoreCase(""))) {
			// Add modify server
			enterText(nas_serviceAccount, nasShare.ServiceAccountForModify);
			clickElement(nas_searchServiceAccount);
			clickElement(nas_extraFields_serviceAccount.format(nasShare.ServiceAccountForModify));
			selectValueByVisibleText(nas_serviceAccountType, "Modify");
			clickElement(nas_extraFields_serviceAccount_Add);

		}
		if (!(nasShare.GlobalGroupsForReadOnly.equalsIgnoreCase(""))) {
			// Add read-only server
			enterText(nas_serviceAccount, nasShare.ServiceAccountForReadOnly);
			clickElement(nas_searchServiceAccount);
			clickElement(nas_extraFields_serviceAccount.format(nasShare.ServiceAccountForReadOnly));
			selectValueByVisibleText(nas_serviceAccountType, "Read Only");
			clickElement(nas_extraFields_serviceAccount_Add);

		}
		clickNextButton();

	}

	private void populateMountInformation(NasShareAllocation nasShare) {
		// TODO Auto-generated method stub

	}

	private void populateShareAndMountInformation(NasShareAllocation nasShare) {
		// TODO Auto-generated method stub

	}

	public void populateUserNotificationPage(NasShareAllocation nasShare) {
		if (!(nasShare.AdditionalUsers.equalsIgnoreCase(""))) {
			enterText(nas_additionalUsers, nasShare.AdditionalUsers);
			waitFor(nas_extraFieldsAdditionalUsers);
			clickElement(nas_extraFieldsAdditionalUsers);
			clickElement(nas_add_additionalUsers);

		}
		clickNextButton();
	}

	public void populateReviewPage(NasShareAllocation nasShare) {

		boolean isClicked = isCheckboxOrRadioBtnSelected(nas_review_checkbox);
		if (!isClicked) {
			selectCheckboxOrRadioBtn(nas_review_checkbox);
		}

	}

	public void submitForm() {
		clickElement(nas_submit_button);

	}

	public void SubmitConfirm() {
		waitFor(nas_dialog_yes);
		clickElement(nas_dialog_yes);
	}

}
