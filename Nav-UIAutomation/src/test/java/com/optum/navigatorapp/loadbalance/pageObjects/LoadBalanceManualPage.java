package com.optum.navigatorapp.loadbalance.pageObjects;

import static org.testng.Assert.assertEquals;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.ClickAction;
import org.testng.log4testng.Logger;
import com.optum.automation.coreframework.baseclasses.BasePageFunctions;
import com.optum.automation.coreframework.browser.Locator;
import com.optum.automation.coreframework.browser.Locator.DynamicLocator;
import com.optum.automation.coreframework.browser.LocatorType;
import com.optum.automation.coreframework.browser.PageFactory.Page;
import com.optum.navigatorapp.loadbalance.databeans.LoadBalanceManual;

@Page
public class LoadBalanceManualPage extends BasePageFunctions {

 private static Logger logger = Logger.getLogger(LoadBalanceManualPage.class);

 Locator        lbManual_RequestTitle         = new Locator(LocatorType.xpath, "//input[@ name='requestTitle']");
 Locator        lbManual_RequestedImpDateTime = new Locator(LocatorType.id, "reqImplDateTimePicker");
 Locator        lbManual_workedInUsa          = new Locator(LocatorType.xpath, "//select[@name='offshoreProhibited']//following-sibling::button");
 Locator        lbManual_ESD                  = new Locator(LocatorType.name, "esdNo");
 Locator        lbManual_VIPAction            = new Locator(LocatorType.xpath, "//select[@name='vipAction']//following-sibling::button/span");
 Locator        lbManual_EnvZone              = new Locator(LocatorType.xpath, "//select[@name='envZone']//following-sibling::button/span");
 Locator        lbManual_ChangeTicket         = new Locator(LocatorType.id, "ticketNo");
 Locator        lbManual_TesterName           = new Locator(LocatorType.name, "testerName");
 Locator        lbManual_TesterEmail          = new Locator(LocatorType.name, "testerEmail");
 Locator        lbManual_TesterPhone          = new Locator(LocatorType.name, "testerPhone");
 Locator        lbManual_Instruction          = new Locator(LocatorType.name, "specialInstructions");
 Locator        lbManual_notes                = new Locator(LocatorType.name, "customerNotes");
 Locator        lbManual_check                = new Locator(LocatorType.name, "controlSubmitBtn");
 Locator        lbManual_submit               = new Locator(LocatorType.id, "save_request_btn");
 Locator        lbManual_cancel               = new Locator(LocatorType.id, "cancel_btn");
 Locator        lbManual_submit_Confirm       = new Locator(LocatorType.xpath, "//div[@class='ui-dialog-buttonset']//span[text()='Yes']");
 Locator        lbManual_submit_Decline       = new Locator(LocatorType.xpath, "//button[text()='No']");
 Locator        lbManual_closeBtn             = new Locator(LocatorType.xpath, "//button[text()='Close']");
 Locator        lbManual_errorTxt             = new Locator(LocatorType.id, "ui-dialog-title");
 Locator        lbManual_managerName          = new Locator(LocatorType.xpath, "//strong[contains(text(),'Manager')]/parent::td/following-sibling::td//tr[1]/td[2]/input");
 Locator        lbManual_dataValidationPopIp  = new Locator(LocatorType.xpath, "//span[text()='Data Validation']");
 Locator        lbManual_FormTitle            = new Locator(LocatorType.xpath, "//div[@class='ux-panl-header']/h2");
 Locator        lbManual_requesterInfo        = new Locator(LocatorType.id, "requestorHeader");
 Locator        lbManual_confirmation         = new Locator(LocatorType.className, "controlSubmitBtn");
 Locator        lbManual_impact               = new Locator(LocatorType.xpath, "//select[@id='userImpact']//following-sibling::button");
 DynamicLocator lbManual_selectOptions        = new DynamicLocator(LocatorType.xpath, "//div[contains(@class,'multiselect-menu') and contains(@style,'block')]//label/span[text()='%s']");
 // div[contains(@class,'multiselect')]//label/span[text()='Yes']

 // to return to home page after selecting a wrong ms is and correct role
 Locator lbManual_returnHome = new Locator(LocatorType.xpath, "//a[@href='main.do']");

 // if offshore is selected yes
 Locator lbManual_offshoreNote = new Locator(LocatorType.name, "offshoreRestrictionReason");

 // if user impact is selected yes
 Locator lbManual_userimpactNote = new Locator(LocatorType.id, "ticketNo");

 public void populateLBData(LoadBalanceManual lbm) {

  if (!(lbm.RequestTitle.equalsIgnoreCase(""))) {
   enterTextThorughScript(lbManual_RequestTitle, lbm.RequestTitle);
  }

  if (!(lbm.dateAndTime.equalsIgnoreCase(""))) {
   selectDateAndTime(lbManual_RequestedImpDateTime, lbm.dateAndTime);
  }
  if (!(lbm.WorkedInUnitedStates.equalsIgnoreCase(""))) {
   select2Click(lbManual_workedInUsa, lbm.WorkedInUnitedStates);
   if (lbm.WorkedInUnitedStates.equalsIgnoreCase("Yes")) {
    enterTextThorughScript(lbManual_offshoreNote, lbm.offshoreNote);
   }
  }
  if (!(lbm.ESD.equalsIgnoreCase(""))) {
   enterTextThorughScript(lbManual_ESD, lbm.ESD);
  }
  if (!(lbm.VIPAction.equalsIgnoreCase(""))) {
   select2Click(lbManual_VIPAction, lbm.VIPAction);
  }
  if (!(lbm.EnvironmentZone.equalsIgnoreCase(""))) {
   select2Click(lbManual_EnvZone, lbm.EnvironmentZone);
  }
  if (!(lbm.UserImpact.equalsIgnoreCase(""))) {
   select2Click(lbManual_impact, lbm.UserImpact);
   if (lbm.UserImpact.equalsIgnoreCase("Yes")) {
    enterTextThorughScript(lbManual_userimpactNote, lbm.userimpactNote);
   }
  }
  if (!(lbm.ChangeTicket.equalsIgnoreCase(""))) {
   enterTextThorughScript(lbManual_ChangeTicket, lbm.ChangeTicket);
  }
  if (!(lbm.TesterName.equalsIgnoreCase(""))) {
   enterTextThorughScript(lbManual_TesterName, lbm.TesterName);
  }
  if (!(lbm.TesterEmail.equalsIgnoreCase(""))) {
   enterTextThorughScript(lbManual_TesterEmail, lbm.TesterEmail);
  }
  if (!(lbm.TesterPhone.equalsIgnoreCase(""))) {
   enterTextThorughScript(lbManual_TesterPhone, lbm.TesterPhone);
  }
  if ((!(lbm.SpecialInstruction.equalsIgnoreCase(""))) || lbm.SpecialInstruction != null) {
   enterTextThorughScript(lbManual_Instruction, lbm.SpecialInstruction);
  }
  if ((!(lbm.confirmation.equalsIgnoreCase(""))) || lbm.confirmation != null) {
   selectCheckboxOrRadioBtn(lbManual_confirmation);
   // clickCheckBox(lbManual_confirmation);
  }

 }

 public void submitLBForm() { // click on submit
  clickElement(lbManual_submit);
 }

 public void submitLBConfirm() { // to confirm the submission

  clickElement(lbManual_submit_Confirm);
 }

 public void submitLBDecline() { // to reject the submission

  clickElement(lbManual_submit_Confirm);
 }

 public void cancelLBForm() { // click on cancel
  clickElement(lbManual_cancel);
 }

 public void verifyDataValidationPopUpAppears() {
  boolean result = isElementExist(lbManual_dataValidationPopIp);
  assertEquals(result, true, "Validation Pop Up did not appear on the page");
 }

 public void verifyErrorText(String errorText) {
  String actualErrorText = getText(lbManual_errorTxt);
  logger.info("Actual Error text is :" + actualErrorText);
  assertEquals(actualErrorText, errorText, "Actual Error " + actualErrorText + " did not match with ExpectedText " + errorText);
 }

 public void closePopup() {
  clickElement(lbManual_closeBtn);
  waitForElementTobeDisappear(lbManual_dataValidationPopIp);

 }

 public boolean isLBManualPage() {
  if (isElementExist(lbManual_FormTitle)) {
   String checkTitle = getText(lbManual_FormTitle);
   if (checkTitle.equalsIgnoreCase("Load Balance Manual"))
    return true;
  }

  return false;

 }

 public void checkRequesterInformation(String expManagerName) {
  clickElement(lbManual_requesterInfo);
  highLight(lbManual_managerName);
  String managerName = getAttribute(lbManual_managerName, "value");
  assertEquals(managerName, expManagerName);

 }

}
