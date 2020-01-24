package com.optum.navigatorapp.security.pageObjects;

import com.optum.automation.coreframework.browser.Locator;
import com.optum.automation.coreframework.browser.Locator.DynamicLocator;
import com.optum.automation.coreframework.browser.LocatorType;
import com.optum.automation.coreframework.browser.PageFactory.Page;

import com.optum.navigatorapp.SecurityRequest.databeans.SecurityRequest;

import static org.testng.Assert.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;
import org.testng.log4testng.Logger;
import com.optum.automation.coreframework.baseclasses.BasePageFunctions;

@Page
public class SecurityRequestPage extends BasePageFunctions {
 private static Logger logger                                    = Logger.getLogger(SecurityRequestPage.class);
 Locator               securityRequest_RequestTitle              = new Locator(LocatorType.name, "requestTitle");
 Locator               securityRequest_SecurityRequest           = new Locator(LocatorType.name, "requestName");
 Locator               securityRequest_ServiceCategory           = new Locator(LocatorType.xpath, "//*[@id='savefirewallrequest']/fieldset/div[2]/table/tbody/tr[1]/td[4]/span[3]/button");
 Locator               securityRequest_AcquiredEntity            = new Locator(LocatorType.xpath, "//*[@id='savefirewallrequest']/fieldset/div[2]/table/tbody/tr[3]/td[2]/button");
 Locator               securityRequest_BusinessApplication       = new Locator(LocatorType.id, "cba");
 Locator               securityRequest_WorkedinUnitedStates      = new Locator(LocatorType.xpath, "//*[@id='savefirewallrequest']/fieldset/div[2]/table/tbody/tr[7]/td[2]/button");
 Locator               securityRequest_LineofBusiness            = new Locator(LocatorType.xpath, "//*[@id='savefirewallrequest']/fieldset/div[2]/table/tbody/tr[10]/td[2]/button/span[2]");
 Locator               securityRequest_DetailedDescription       = new Locator(LocatorType.name, "detailedDesc");
 Locator               securityRequest_Notes                     = new Locator(LocatorType.name, "customerNotes");
 Locator               securityRequest_RulesforUseAgreement      = new Locator(LocatorType.xpath, ".//input[@class='confirmAgrmt1 agreementCheck']");
 Locator               securityRequest_CheckoutRequirementsAEYES = new Locator(LocatorType.css, ".thirdAgreement .confirmAgrmt2.agreementCheck");
 Locator               securityRequest_CheckoutRequirementsAENO  = new Locator(LocatorType.css, ".confirmAgrmt2.agreementCheck");
 Locator               securityRequest_submit                    = new Locator(LocatorType.id, "saverequestbtn");
 Locator               securityRequest_cancel                    = new Locator(LocatorType.id, "cancelbtn");
 Locator               securityRequest_requesteInfo              = new Locator(LocatorType.xpath, "//div[@class='ux-panl-header']//h2");
 Locator               securityRequest_managerName               = new Locator(LocatorType.id, "mgrName");
 Locator               securityRequest_SecurityRequestDropdown   = new Locator(LocatorType.xpath, "//select[@name='requestName']/following-sibling::button");
 Locator               securityRequest_ReasonReview              = new Locator(LocatorType.name, "offshoreRestrictionReason");
 Locator               securityRequest_dataValidationPopIp       = new Locator(LocatorType.xpath, "//span[text()='Data Validation']");
 Locator               securityRequest_submit_Confirm            = new Locator(LocatorType.xpath, "//button[contains(@class, 'ui-button')]/span[text()='Yes']");

 /*
  * DynamicLocator securityRequest_OptionSelection = new
  * DynamicLocator(LocatorType.xpath, "//span[text()='%s']"); DynamicLocator
  * securityRequest_OptionServiceCategory = new
  * DynamicLocator(LocatorType.xpath, "/html/body/div[10]/ul/li[1]/label/span");
  * DynamicLocator securityRequest_OptionAE = new
  * DynamicLocator(LocatorType.xpath,
  * ".//label[@for = 'ui-multiselect-acquiredEntity-option-%s']");
  * DynamicLocator securityRequest_OptionBusiness = new
  * DynamicLocator(LocatorType.xpath, "//*[@id='ui-id-6']"); DynamicLocator
  * securityRequest_OptionUS = new DynamicLocator(LocatorType.xpath,
  * "/html/body/div[16]/ul/li[2]/label"); DynamicLocator
  * securityRequest_BusinessOption = new DynamicLocator(LocatorType.xpath,
  * "//*[@id='savefirewallrequest']/fieldset/div[2]/table/tbody/tr[10]/td[2]/button"
  * );
  */

 DynamicLocator securityRequest_optionsselection = new DynamicLocator(LocatorType.xpath, "//span[text()='%s']");
 DynamicLocator securityRequest_OptionBusiness   = new DynamicLocator(LocatorType.xpath, "//li//a[text()='%s']");

 public void populateData(SecurityRequest secReq) {

  if (!(secReq.SecurityRequest).equalsIgnoreCase("")) {
   select2Click(securityRequest_SecurityRequestDropdown, secReq.SecurityRequest);
  }

  if (!(secReq.ServiceCategory.equalsIgnoreCase(""))) {
   select2Click(securityRequest_ServiceCategory, secReq.ServiceCategory);
  }

  if (!(secReq.RequestforAE.equalsIgnoreCase(""))) {
   select2Click(securityRequest_AcquiredEntity, secReq.RequestforAE);

  }

  if (!(secReq.Criticalbusinessapplication.equalsIgnoreCase(""))) {

   clickElement(securityRequest_BusinessApplication);
   clickElement(securityRequest_OptionBusiness.format(secReq.Criticalbusinessapplication));
  }
  if (!(secReq.RequestTitle.equalsIgnoreCase(""))) {

   enterTextThorughScript(securityRequest_RequestTitle, secReq.RequestTitle);

  }

  if (!(secReq.workedinUS.equalsIgnoreCase(""))) {

   select2Click(securityRequest_WorkedinUnitedStates, secReq.workedinUS);
  }

  if (!(secReq.ReasonReview.equalsIgnoreCase(""))) {
   enterTextThorughScript(securityRequest_ReasonReview, secReq.ReasonReview);

  }
  if (!(secReq.Lineofbusiness.equalsIgnoreCase(""))) {
   select2Click(securityRequest_LineofBusiness, secReq.Lineofbusiness);
  }

  if (!(secReq.DetailedDescription.equalsIgnoreCase(""))) {
   enterTextThorughScript(securityRequest_DetailedDescription, secReq.DetailedDescription);
  }

  if (!(secReq.Notes.equalsIgnoreCase(""))) {
   enterTextThorughScript(securityRequest_Notes, secReq.Notes);
  }

  selectCheckboxOrRadioBtn(securityRequest_RulesforUseAgreement);

  if (secReq.RequestforAE.equals("Yes")) {
   selectCheckboxOrRadioBtn(securityRequest_CheckoutRequirementsAEYES);
  } else {
   selectCheckboxOrRadioBtn(securityRequest_CheckoutRequirementsAENO);
  }

  // setCheckBox(securityRequest_CheckoutRequirements, true);
  // clickElement(securityRequest_CheckoutRequirements);
  // clickElement(securityRequest_submit);

 }

 public void submitForm() {
  clickElement(securityRequest_submit);
 }

 public void submitConfirm() {

  clickElement(securityRequest_submit_Confirm);
 }

 public void cancel() {
  clickElement(securityRequest_cancel);

 }

 public void verifyDataValidationPopUpAppears() {
  boolean result = isElementExist(securityRequest_dataValidationPopIp);
  assertEquals(result, true, "Validation Pop Up did not appear on the page");
 }

 public void checkRequesterInformation(String expManagerName) {
  clickElement(securityRequest_requesteInfo);
  String managerName = getAttribute(securityRequest_managerName, "value");
  assertEquals(managerName, expManagerName);

 }

}
