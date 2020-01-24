package com.optum.navigatorapp.base.pageobjects;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mshar107
 *
 */
import org.testng.log4testng.Logger;
import com.optum.automation.coreframework.baseclasses.BasePageFunctions;
import com.optum.automation.coreframework.browser.Locator;
import com.optum.automation.coreframework.browser.Locator.DynamicLocator;
import com.optum.automation.coreframework.browser.LocatorType;
import com.optum.automation.coreframework.browser.PageFactory.Page;

@Page
public class DashBoardPage extends BasePageFunctions {

	private static Logger logger = Logger.getLogger(DashBoardPage.class);

	Locator					dashBoard_formTitle								= new Locator(LocatorType.xpath, "//div[@class='ux-panl-content']//h1");
	Locator					dashBoard_inProgressRequest				= new Locator(LocatorType.xpath, "//a[@id='inprogressRequests']/span");
	Locator					dashBoard_actionRequests					= new Locator(LocatorType.xpath, "//a[@id='actionRequests']/span");
	Locator					dashBoard_closedRequests					= new Locator(LocatorType.xpath, "//a[@id='closedRequests']/span");
	Locator					dashBoard_arResult								= new Locator(LocatorType.id, "arOptions");
	Locator					dashBoard_ipResult								= new Locator(LocatorType.id, "ipOptions");
	Locator					dashBoard_clResult								= new Locator(LocatorType.id, "");
	DynamicLocator	dashBoard_requestLink_in_Action		= new DynamicLocator(LocatorType.xpath, "//div[contains(@class,'actionRequestsDiv')]/parent::div//td/a[contains(@data-requesttitle, '%s')]");
	DynamicLocator	dashBoard_requestLink_in_Progress	= new DynamicLocator(LocatorType.xpath, "//div[@id='inprogress-results_wrapper']//a[@data-id='%d']");
	DynamicLocator	dashBoard_requestLink_closed			= new DynamicLocator(LocatorType.xpath, "//div[@id='closed-results_wrapper']//a[@data-id='%d']");
	DynamicLocator	dashBoard_closed_Status						= new DynamicLocator(LocatorType.xpath, "//div[@id='closed-results_wrapper']//a[@data-id='%d']/parent::td/following-sibling::td[5]");
	// div[contains(@class,'actionRequestsDiv')]/parent::div//td/a[contains(@data-requesttitle,
	// ' Auto EnSupp-Network Connectivity-Rqst_6-160119')]

	/**
	 * @return
	 * 
	 */
	public Map<String, Integer> getStats() {
		Map<String, Integer> statsMap = new HashMap<String, Integer>();
		// highLight(dashBoard_inProgressRequest);
		waitForPageToLoad();
		statsMap.put("inProgress", Integer.parseInt(getText(dashBoard_inProgressRequest)));
		statsMap.put("action", Integer.parseInt(getText(dashBoard_actionRequests)));
		statsMap.put("closed", Integer.parseInt(getText(dashBoard_closedRequests)));
		return statsMap;

	}

	public boolean isDashBoardPage() {
		waitForPageToLoad();
		if (isElementPresent(dashBoard_formTitle)) {
			String checkTitle = getText(dashBoard_formTitle);
			if (checkTitle.equalsIgnoreCase("Dashboard"))
				return true;
		}

		return false;

	}

	public void verifyIsDashBoardPage() {

		assertEquals(isDashBoardPage(), true, "Not redirected to DashBoardPage");

	}

	/**
	 * @param statsMap
	 * @return
	 */
	public Map<String, Integer> verifyInProgressRequest(Map<String, Integer> statsMap) {
		int beforeInProgressRequest = statsMap.get("inProgress");
		Map<String, Integer> updatedStats = getStats();
		int afterInProgressRequest = updatedStats.get("inProgress");
		assertEquals(afterInProgressRequest,beforeInProgressRequest + 1 );
		return updatedStats;
	}

	public Map<String, Integer> verifyClosedRequest(Map<String, Integer> statsMap) {
		int beforeclosedRequest = statsMap.get("closed");
		Map<String, Integer> updatedStats = getStats();
		int afterclosedRequest = updatedStats.get("closed");
		assertEquals(afterclosedRequest,beforeclosedRequest + 1);
		return updatedStats;
	}

	public Map<String, Integer> verifyActionRequest(Map<String, Integer> statsMap) {
		int beforeactionRequest = statsMap.get("action");
		Map<String, Integer> updatedStats = getStats();
		int afteractionRequest = updatedStats.get("action");
		assertEquals( afteractionRequest,beforeactionRequest + 1);
		return updatedStats;
	}

	/**
	 * @param requestTitle
	 * @return
	 */
	public int getRequestId(String requestTitle) {
		String requestId = getAttribute(dashBoard_requestLink_in_Action.format(requestTitle), "data-id");
		return Integer.parseInt(requestId);
	}

	/**
	 * @param requestTitle
	 */
	public void selectRequestForAssignment(String requestTitle) {
		clickElement(dashBoard_requestLink_in_Action.format(requestTitle));

	}

	/**
	 * 
	 */
	public void verifyRequestMovetoInProgressQueue(int requestId) {
		boolean actual = isElementExist(dashBoard_requestLink_in_Progress.format(requestId));
		assertEquals(actual, true, "Request did not moved to In Progress Queue.");

	}

	public void verifyRequestMovetoClosedQueueWithStatus(int requestId, String status) {
		boolean actual = isElementExist(dashBoard_requestLink_closed.format(requestId));
		assertEquals(actual, true, "Request did not moved to Closed Queue.");
		String actualStatus = getText(dashBoard_closed_Status.format(requestId));
		assertEquals(actualStatus, status);

	}

	/**
	 * 
	 */
	public void udpateActionRequiredResultsSetting(String value) {
		// TODO Auto-generated method stub
		selectValueByVisibleText(dashBoard_arResult, value);
		waitForAjaxDialog();
	}

	public void udpateInProgressResultsSetting(String value) {
		// TODO Auto-generated method stub
		selectValueByVisibleText(dashBoard_ipResult, value);
		waitForAjaxDialog();
	}

	public void udpateClosedResultsSetting() {
		// TODO Auto-generated method stub

	}
}
