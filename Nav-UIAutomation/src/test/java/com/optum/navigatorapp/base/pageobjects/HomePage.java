package com.optum.navigatorapp.base.pageobjects;

import com.optum.automation.coreframework.baseclasses.BasePageFunctions;
import com.optum.automation.coreframework.browser.Locator;
import com.optum.automation.coreframework.browser.LocatorType;
import com.optum.automation.coreframework.browser.PageFactory.Page;

@Page
public class HomePage extends BasePageFunctions  {

	Locator	engSupport_URL							= new Locator(LocatorType.link, "engineering & support");
	Locator	dashBoard_URL								= new Locator(LocatorType.link, "dashboard");
	Locator	homePage_URL								= new Locator(LocatorType.link, "Home");
	Locator	assignResource_URL					= new Locator(LocatorType.link, "assign resource");
	Locator	searchRequest_URL						= new Locator(LocatorType.link, "search request");
	Locator	entitlement_URL							= new Locator(LocatorType.link, "entitlement");
	Locator	navHelpGuide_URL						= new Locator(LocatorType.link, "navigator help guides");
	Locator	f5lookup_URL								= new Locator(LocatorType.link, "f5 lookup");
	Locator	f5compare_URL								= new Locator(LocatorType.link, "f5 compare");
	Locator	togglerHealthcheck_URL			= new Locator(LocatorType.link, "toggler & healthcheck");
	Locator	loadBalanceNew_URL					= new Locator(LocatorType.link, "load balance new");
	Locator	loadBalanceManagement_URL		= new Locator(LocatorType.link, "load balance management");
	Locator	loadBalanceManual_URL				= new Locator(LocatorType.link, "load balance manual");
	Locator	VTP_URL											= new Locator(LocatorType.link, "vendor & third party");
	Locator	networkDeliveryIntake_URL		= new Locator(LocatorType.link, "network delivery intake");
	Locator	broadbandWFH_URL						= new Locator(LocatorType.link, "broadband work from home portal");
	Locator	opsrdy_URL									= new Locator(LocatorType.link, "opsrdy");
	Locator	cockpit_URL									= new Locator(LocatorType.link, "ecg cockpit");
	Locator	securityRequest_URL					= new Locator(LocatorType.link, "security request");
	Locator	bandwidthTester_URL					= new Locator(LocatorType.link, "bandwidth tester");
	Locator	DLP_URL											= new Locator(LocatorType.link, "data loss prevention (DLP)");
	Locator	archsightRequest_URL				= new Locator(LocatorType.link, "arcsight request");
	Locator	DNS_URL											= new Locator(LocatorType.link, "dns management");
	Locator	sanStorageDecom_URL					= new Locator(LocatorType.link, "san storage decommission");
	Locator	nasShareAllocation_URL			= new Locator(LocatorType.link, "nas share allocation");
	Locator	storageApplication_URL			= new Locator(LocatorType.link, "storage applications");
	Locator	newTechnologyIntake_URL			= new Locator(LocatorType.link, "new technology engagement intake");
	Locator	serverReboot_URL						= new Locator(LocatorType.link, "server reboot (VM Hard Reset)");
	Locator	unixServerManagement_URL		= new Locator(LocatorType.link, "unix server management");
	Locator	NHSProjectRequest_URL				= new Locator(LocatorType.link, "NHS project delivery request");
	Locator	dcRemoteSiteManagement_URL	= new Locator(LocatorType.link, "dc and remote site migration");
	Locator	infraEnginerring_URL				= new Locator(LocatorType.link, "infrastructure engineering engagement");

	public void clickEngSupportLink() {
		clickElement(engSupport_URL);

		waitForPageToLoad();
	}

	public void clickLoadBalanceNewLink() {
		clickElement(loadBalanceNew_URL);
		waitForPageToLoad();
	}

	public void clickDashBoardLink() {
		clickElement(dashBoard_URL);
		waitForPageToLoad();
	}

	public void clickHomePageURL() {
		clickElement(homePage_URL);
		waitForPageToLoad();
	}

	
	public void clickAssignResourceLink() {
		clickElement(assignResource_URL);
		waitForPageToLoad();
	}

	public void clickSearchRequestLink() {
		clickElement(searchRequest_URL);
		waitForPageToLoad();
	}

	public void clickEntitlementLink() {
		clickElement(entitlement_URL);
		waitForPageToLoad();
	}

	public void clickNavigatorHelpGuideLink() {
		clickElement(navHelpGuide_URL);
		waitForPageToLoad();
	}

	public void clickF5LookupLink() {
		clickElement(f5lookup_URL);
		waitForPageToLoad();
	}

	public void clickF5CompareLink() {
		clickElement(f5compare_URL);
		waitForPageToLoad();
	}

	public void clickTogglerHealthCheckLink() {
		clickElement(togglerHealthcheck_URL);
		waitForPageToLoad();
	}

	public void clickLoadBalanceManagementLink() {
		clickElement(loadBalanceManagement_URL);
		waitForPageToLoad();
	}

	public void clickLoadBalanceManualLink() {
		clickElement(loadBalanceManual_URL);
		waitForPageToLoad();
	}

	public void clickVTPLink() {
		clickElement(VTP_URL);
		waitForPageToLoad();
	}

	public void clickNetworkDeliveryIntakeLink() {
		clickElement(networkDeliveryIntake_URL);
		waitForPageToLoad();
	}

	public void clickBroadbandWFHLink() {
		clickElement(broadbandWFH_URL);
		waitForPageToLoad();
	}

	public void clickOPSRDYLink() {
		clickElement(opsrdy_URL);
		waitForPageToLoad();
	}

	public void clickCockpitLink() {
		clickElement(cockpit_URL);
		waitForPageToLoad();
	}

	public void clickSecurityRequestLink() {
		clickElement(securityRequest_URL);
		waitForPageToLoad();
	}

	public void clickBandwidthTesterLink() {
		clickElement(bandwidthTester_URL);
		waitForPageToLoad();
	}

	public void clickDLPLink() {
		clickElement(DLP_URL);
		waitForPageToLoad();
	}

	public void clickArchSightRequestLink() {
		clickElement(archsightRequest_URL);
		waitForPageToLoad();
	}

	public void clickDNSManagementLink() {
		clickElement(DNS_URL);
		waitForPageToLoad();
	}

	public void clickSanStorageDecomLink() {
		clickElement(sanStorageDecom_URL);
		waitForPageToLoad();
	}

	public void clickNASShareAllocationLink() {
		clickElement(nasShareAllocation_URL);
		waitForPageToLoad();
	}

	public void clickStorageApplicationLink() {
		clickElement(storageApplication_URL);
		waitForPageToLoad();
	}

	public void clickNewTechEngagementLink() {
		clickElement(newTechnologyIntake_URL);
		waitForPageToLoad();
	}

	public void clickServerRebootLink() {
		clickElement(serverReboot_URL);
		waitForPageToLoad();
	}

	public void clickUnixServerManagementLink() {
		clickElement(unixServerManagement_URL);
		waitForPageToLoad();
	}

	public void clickNHSProjectDeliveryLink() {
		clickElement(NHSProjectRequest_URL);
		waitForPageToLoad();
	}

	public void clickDCRemoteSiteMigrationLink() {
		clickElement(dcRemoteSiteManagement_URL);
		waitForPageToLoad();
	}

	public void clickInfraEngineeringLink() {
		clickElement(infraEnginerring_URL);
		waitForPageToLoad();
	}

}

