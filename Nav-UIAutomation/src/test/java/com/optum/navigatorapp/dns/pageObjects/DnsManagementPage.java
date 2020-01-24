package com.optum.navigatorapp.dns.pageObjects;
/*
 * @author Manoj Sharma
 */

import static org.testng.Assert.assertEquals;

import java.util.Map;
import java.util.TreeMap;

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
public class DnsManagementPage extends BasePageFunctions {

	private static Logger logger = Logger.getLogger(DnsManagementPage.class);
private RequestInformationPage rqstInfPage;
 	Locator	dnsManage_DomainIs_select						= new Locator(LocatorType.id, "domainAlias");
	Locator	dnsManage_DomainName_txt							= new Locator(LocatorType.id, "aliasfqdn");
	Locator	dnsManage_search_btn										= new Locator(LocatorType.id, "aliasDnsSearchBtn");
	Locator	dnsManage_NewAlias_btn			= new Locator(LocatorType.id, "edit_create_btn");
	Locator	dnsManage_newAliasfound_img								= new Locator(LocatorType.id, "fqdnImgId");
	Locator	dnsManage_newAlias_txt							= new Locator(LocatorType.xpath, "//img[@id='fqdnImgId']/../following-sibling::td[text()='Alias Domain is Avaliable - Click New!']");
	Locator	dnsManage_ownerMsid_txt	= new Locator(LocatorType.id, "ownerMsid");
	Locator	dnsManage_adminGroupValue_txt		= new Locator(LocatorType.id, "adminGroupValue");
	Locator	dnsManage_previous_btn										= new Locator(LocatorType.xpath, "//button[text()='Previous']");
	Locator	dnsManage_next_btn										= new Locator(LocatorType.xpath, "//button[text()='Next']");
	Locator	dnsManage_cancel_btn							= new Locator(LocatorType.xpath, "//strong[text()='Cancel']");
	Locator	dnsManage_targetfqdn_txt							= new Locator(LocatorType.id, "targetfqdn");
	Locator	dnsManage_targetFQDNfound_img								= new Locator(LocatorType.id, "fqdnImgIdTag");
	Locator	dnsManage_targetFQDN_txt							= new Locator(LocatorType.xpath, "//img[@id='fqdnImgIdTag']/../following-sibling::td[1]");
	
	Locator	dnsManage_submit_Decline						= new Locator(LocatorType.xpath, "//div[@class='ui-dialog-buttonset']//button[text()='No']");
	Locator	dnsManage_dataValidationPopId			= new Locator(LocatorType.xpath, "//span[text()='Data Validation']");
	Locator	dnsManage_closeBtn									= new Locator(LocatorType.xpath, "//div[@id='datavalidationpopup']/following-sibling::div//button");
	Locator	dnsManage_errorTxt									= new Locator(LocatorType.id, "datavalidationpopup");
	Locator	dnsManage_fromTitle								= new Locator(LocatorType.xpath, "//div[@class='ux-panl-header']/h2");
	Locator	dnsManage_requesteInfo							= new Locator(LocatorType.xpath, "//div[@id='requestInfo-header']/a/span");
	Locator	dnsManage_managerName							= new Locator(LocatorType.id, "mgrName");

	// Review Form Fields.
	Locator	dnsManage_aliasdomainName_lbl						= new Locator(LocatorType.id, "aliasdomainName");
	Locator	dnsManage_ownermsid_lbl					= new Locator(LocatorType.id, "ownermsid");
	Locator	dnsManage_admingrp_lbl		= new Locator(LocatorType.id, "admingrp");
	Locator	dnsManage_targetdomainname_lbl	= new Locator(LocatorType.id, "targetdomainname");
	Locator	dnsManage_targettypeid_lbl				= new Locator(LocatorType.id, "targettypeid");
	Locator	dnsManage_targetip_lbl		= new Locator(LocatorType.id, "targetip");
	Locator	dnsManage_controlSubmitBtn_chkbox						= new Locator(LocatorType.className, "controlSubmitBtn");
	
	
	public void verifyFoundImageIsPresent(FoundImages fi) {
		Locator locator = null;
		switch(fi) {
		case Target:
			locator=dnsManage_targetFQDNfound_img;
			break;
		case NewAlias:
			locator=dnsManage_newAliasfound_img;
			break;
		}
		String srcValue=getAttribute(locator,"src");
		boolean greenImagePresent=false;
		if(srcValue.contains("found.png")) {
		 greenImagePresent=true;	
		}
		assertEquals(greenImagePresent, true);
	}
	
	public void verifyNewAliasText() {
		boolean msgCheck=isElementExist(dnsManage_newAlias_txt);
		assertEquals(msgCheck, true,"Alias Domain is Avaliable - Click New!, message did not appeared on screen");
	}
	
	public void VerifytargetFQDNText(String text ) {
		//FQDN found
		//Fqdn / Domain does not found
		String targetFQDNText=getText(dnsManage_targetFQDN_txt);
		assertEquals(targetFQDNText, text);
	}
	public void verifyAdminGroupText() {
		String actualText=getAttribute(dnsManage_adminGroupValue_txt,"placeholder");
		
		assertEquals(actualText, "Select group with up to 40 users and hit tab");
	}
	public void verifyAgreementRules() {
		Map<Integer, String>agreementRulesMap=new TreeMap<Integer, String>();
		agreementRulesMap.put(1, "The following rules apply to DNS Registration:");
		agreementRulesMap.put(2, " This request is not intended to be used to modify standard names issued from automated provisioning.");
		agreementRulesMap.put(3, " This request cannot be used to change the server name or standard interface names provided through build automation, but it can be used to add DNS entries.");
		agreementRulesMap.put(4, " Production changes or deletions must be accompanied with an approved change control.");
		agreementRulesMap.put(5, " The new name being registered must not already exist.");
		agreementRulesMap.put(6, " The IP Address for A-Records must not be registered.:");
		agreementRulesMap.put(7, " IP generation can only be done on primary interfaces of provisioned servers.");
		agreementRulesMap.put(8, " The requester must be a member of the group that is allowed access to the subscription of the server.");

	}
	public enum FoundImages{
		Target,NewAlias;
	}
	
}
