//pageObjects

package com.optum.navigatorapp.intakerequest.pageObjects;
/*
 * @author SundarSharma
 */

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.optum.automation.coreframework.baseclasses.BasePageFunctions;
import com.optum.automation.coreframework.browser.Locator;
import com.optum.automation.coreframework.browser.Locator.DynamicLocator;
import com.optum.automation.coreframework.browser.LocatorType;
import com.optum.automation.coreframework.browser.PageFactory;
import com.optum.automation.coreframework.browser.PageFactory.Page;
import com.optum.navigatorapp.intakerequest.databeans.VendorAndThridParty;

@Page
public class VendorAndThirdpartyPage extends BasePageFunctions {

	private static Logger logger = Logger.getLogger(VendorAndThirdpartyPage.class);

	/* VTP Request Submission Locators*/
	/*Locator vtp_RequestAction                     = new Locator (LocatorType.name,"requestAction");*/
	Locator					vtp_RequestAction1				= new Locator(LocatorType.xpath, "//select[@name='requestAction']/following-sibling::button");
	Locator					vtp_RequestTitle					= new Locator(LocatorType.name, "requestTitle");
	Locator					vtp_WorkedinUS						= new Locator(LocatorType.xpath, "//select[@name='offshoreProhibited']/following-sibling::button");
	Locator					vtp_ConnectingEntity			= new Locator(LocatorType.xpath, "//select[@name='connectingEntity']/following-sibling::button");
	Locator					vtp_SUI										= new Locator(LocatorType.name, "sui");
	Locator					vtp_PhiInvolved						= new Locator(LocatorType.xpath, "//select[@name='phiInvolved']/following-sibling::button");
	Locator					vtp_ThirdPartyNm					= new Locator(LocatorType.name, "thirdPartName");
	Locator					vtp_DrgRequired						= new Locator(LocatorType.xpath, "//select[@name='drgRequired']/following-sibling::button");
	Locator					vtp_ReasonForConnection		= new Locator(LocatorType.name, "connectionReason");
	Locator					vtp_DuringPeakSeason			= new Locator(LocatorType.name, "whyInPeakSeason");
	Locator					vtp_AfterPeakSeason				= new Locator(LocatorType.name, "impactIfAfterPeakSeason");
	Locator					vtp_PerformedasScheduled	= new Locator(LocatorType.name, "whyPerformedScheduled");
	Locator					vtp_BusinessName					= new Locator(LocatorType.name, "businessName");
	Locator					vtp_ContactName						= new Locator(LocatorType.name, "backReqContactName");
	Locator					vtp_ContactEmail					= new Locator(LocatorType.name, "backReqContactEmail");
	Locator					vtp_CustomerNotes					= new Locator(LocatorType.name, "customerNotes");
	Locator					vtp_submit								= new Locator(LocatorType.id, "saverequestbtn");
	Locator					vtp_cancel								= new Locator(LocatorType.id, "cancelbtn");
	Locator					vtp_submit_Confirm				= new Locator(LocatorType.xpath, "//div[@class='ui-dialog-buttonset']//button//Span[text()='Yes']");
	Locator					vtp_submit_Decline				= new Locator(LocatorType.xpath, "//div[@class='ui-dialog-buttonset']//button//Span[text()='No']");
	Locator					vtp_formTitle							= new Locator(LocatorType.xpath, "//div[@class='ux-panl-header ']//h2");
	Locator					vtp_attachments						= new Locator(LocatorType.id, "attachments");
	Locator					vtp_addFiles							= new Locator(LocatorType.id, "moreAttachments");
	Locator					vtp_contractIdNumber			= new Locator(LocatorType.name, "contractIdNumber");
	Locator					vtp_vendorId							= new Locator(LocatorType.name, "egrcVendorId");
	Locator					vtp_EngagementId					= new Locator(LocatorType.name, "egrcEngagementId");
	Locator					vtp_TrackingId						= new Locator(LocatorType.name, "egrcAssessmentId");
	DynamicLocator	vtp_selectOptions					= new DynamicLocator(LocatorType.xpath, "//div[contains(@class,'multiselect')]//label/span[text()='%s']");
    
    public void populateData(VendorAndThridParty vtp) {
		
    	if (!(vtp.RequestAction.equalsIgnoreCase(""))) {
    		
    		select2Click(vtp_RequestAction1,vtp.RequestAction );

    	}
    	
    	if(!(vtp.RequestTitle.equalsIgnoreCase(""))) {
    		enterTextThorughScript(vtp_RequestTitle, vtp.RequestTitle);
    	}
    	
    	if (!(vtp.WorkedinUnitedStates.equalsIgnoreCase(""))) {
    		select2Click(vtp_WorkedinUS,vtp.WorkedinUnitedStates);
    		/*clickElement(vtp_WorkedinUS);
    		clickElement(vtp_selectOptions.format(vtp.WorkedinUnitedStates));*/
    	}
    	
    	if(!(vtp.ConnectingEntity.equalsIgnoreCase(""))) {
    		select2Click(vtp_ConnectingEntity,vtp.ConnectingEntity);
    		/*clickElement(vtp_ConnectingEntity);
    		clickElement(vtp_selectOptions.format(vtp.ConnectingEntity));*/
    	}
    	
    	if(!(vtp.SUI.equalsIgnoreCase(""))) {
    		enterTextThorughScript(vtp_SUI, vtp.SUI);
    	}
    	
           	
    	if(!(vtp.PHIInvolved.equalsIgnoreCase(""))) {  
    		select2Click(vtp_PhiInvolved,vtp.PHIInvolved);
    		/*clickElement(vtp_PhiInvolved);
    		clickElement(vtp_selectOptions.format(vtp.PHIInvolved));*/
    	}
    	
    	if(!(vtp.ThridPartyName.equalsIgnoreCase(""))) {
    		enterTextThorughScript(vtp_ThirdPartyNm, vtp.ThridPartyName);
    	}
    	
    	if (!(vtp.SecureShareRequired.equalsIgnoreCase(""))) {
    		select2Click(vtp_DrgRequired,vtp.SecureShareRequired);
//    		clickElement(vtp_DrgRequired);
//    		clickElement(vtp_selectOptions.format(vtp.SecureShareRequired));
    	}
    	
    	if(!(vtp.ReasonForConnection.equalsIgnoreCase(""))) {
    		enterTextThorughScript(vtp_ReasonForConnection, vtp.ReasonForConnection);
    	}
    	
    	if(!(vtp.DuringPeakSeason.equalsIgnoreCase(""))) {
    		enterTextThorughScript(vtp_DuringPeakSeason, vtp.DuringPeakSeason);
    	}
    	
    	if(!(vtp.AfterPeakSeason.equalsIgnoreCase(""))) {
    		enterTextThorughScript(vtp_AfterPeakSeason, vtp.AfterPeakSeason);    		
    	}
    	
    	if(!(vtp.PerformedasScheduled.equalsIgnoreCase(""))) {
    		enterTextThorughScript(vtp_PerformedasScheduled, vtp.PerformedasScheduled);    		
    	}
    	
    	if(!(vtp.ContractIDNumber.equalsIgnoreCase(""))) {
    		enterTextThorughScript(vtp_contractIdNumber, vtp.ContractIDNumber);
    	}
    	
    	if(!(vtp.VendorId.equalsIgnoreCase(""))) {
    		enterTextThorughScript(vtp_vendorId, vtp.VendorId);
    	}
    	
    	if(!(vtp.EngagementId.equalsIgnoreCase(""))) {
    		enterTextThorughScript(vtp_EngagementId, vtp.EngagementId);
    	}
    	
    	if(!(vtp.TrackingId.equalsIgnoreCase(""))) {
    		enterTextThorughScript(vtp_TrackingId, vtp.TrackingId);
    	}
    	
    	if(!(vtp.BusinessName.equalsIgnoreCase(""))) {
    		enterTextThorughScript(vtp_BusinessName, vtp.BusinessName);    		
    	}
    	
    	if(!(vtp.ContactName.equalsIgnoreCase(""))) {
    		enterTextThorughScript(vtp_ContactName, vtp.ContactName);
    	}
    	
    	
    	if(!(vtp.ContactEmail.equalsIgnoreCase(""))) {
    		enterTextThorughScript(vtp_ContactEmail, vtp.ContactEmail);
    	}
    	
    	if (!(vtp.attachments.equalsIgnoreCase("")))
    	{
    		addAttachment(vtp_attachments, vtp.attachments);
    	 	clickElement(vtp_addFiles);
    	}
    	
    	if(!(vtp.CustomerNotes.equalsIgnoreCase(""))) {
    		enterTextThorughScript(vtp_CustomerNotes, vtp.CustomerNotes);
    	}
    	
    }


	public void submitForm() {
		
		clickElement(vtp_submit);
		// TODO Auto-generated method stub
		
		
	} 
	
	
	
	public void submitConfirmation(VendorAndThridParty vtp) {
	if((vtp.RequestSubmission.contentEquals("Yes"))) {
    	clickElement(vtp_submit_Confirm);
    }
    	else if (vtp.RequestSubmission.contentEquals("No"))
    	clickElement(vtp_submit_Decline);
		}
	
	
	
	
	
	
	
	
	
//	public void submitconfirm() {
//		clickElement(vtp_submit_Confirm);
//	}
	
	public boolean isVtpPage() {
		if (isElementExist(vtp_formTitle)) {
			String checkTitle = getText(vtp_formTitle);
			if (checkTitle.equalsIgnoreCase("Vendor and Third Party"))
			return true;
		}

		return false;

	}
	 
	}