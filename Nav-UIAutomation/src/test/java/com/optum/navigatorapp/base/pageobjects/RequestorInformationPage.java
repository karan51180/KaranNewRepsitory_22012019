/**
 * 
 */
package com.optum.navigatorapp.base.pageobjects;

import static org.testng.Assert.assertEquals;

import org.json.JSONObject;

import com.optum.automation.coreframework.baseclasses.BasePageFunctions;
import com.optum.automation.coreframework.browser.Locator;
import com.optum.automation.coreframework.browser.LocatorType;
import com.optum.automation.coreframework.browser.PageFactory.Page;

/**
 * @author mshar107
 *
 */
@Page
public class RequestorInformationPage extends BasePageFunctions {
	Locator	requestedBy				= new Locator(LocatorType.xpath, "//td[text()='Requested By']/following-sibling::td[1]");
	Locator	requestID					= new Locator(LocatorType.xpath, "//td[text()='Request ID']/following-sibling::td[1]");
	Locator	requestedByemail	= new Locator(LocatorType.xpath, "//td[text()='Requested By Email']/following-sibling::td[1]");
	Locator	requestedByphone	= new Locator(LocatorType.xpath, "//td[text()='Requested By Phone']/following-sibling::td[1]");
	Locator	bussinessUnit			= new Locator(LocatorType.xpath, "//td[text()='Business Unit']/following-sibling::td[1]");
	Locator	manager						= new Locator(LocatorType.xpath, "//td[text()='Manager']/following-sibling::td[1]");
	Locator	manageremail			= new Locator(LocatorType.xpath, "//td[text()='Manager Email']/following-sibling::td[1]");
	Locator	managerPhone			= new Locator(LocatorType.xpath, "//td[text()='Manager Phone']/following-sibling::td[1]");
	Locator	glCode						= new Locator(LocatorType.xpath, "//td[text()='GL Code:']/following-sibling::td[1]");
	Locator	requestStatus			= new Locator(LocatorType.xpath, "//td[text()='Requested By']/following-sibling::td[1]");

	/**
	 * This method accepts JSONObject String containing requestor Information and
	 * RequestID . <br>
	 * Only property which has value will be used for verification, other will be
	 * ignored. <br>
	 * format of JSONObject String <br>
	 * { <br>
	 * requestedBy:"Sharma, Manoj", <br>
	 * requestedByemail:"manojsharma@optum.com", <br>
	 * requestedByphone:"124/622-9007", <br>
	 * bussinessUnit:"Optum Global Solutions", <br>
	 * manager:"Mehta, Gourav", <br>
	 * manageremail:"gourav_mehta@optum.com", <br>
	 * managerPhone:"783/856-1477", <br>
	 * glCode:"41346-08871-INDIN547-788292---", <br>
	 * requestStatus:"" <br>
	 * } <<To Do or Future enhancement>> bring this information automatically from
	 * LDAP or through API.
	 * 
	 * @param jsonRequestorInformation,
	 *          RequestId
	 */
	public void verifyRequestorInformation(String jsonRequestorInformation, String exprequestID) {
		JSONObject requestorInf = new JSONObject(jsonRequestorInformation);
		for (String key : requestorInf.keySet()) {
			String expKeyValue = requestorInf.getString(key);
			if (!(expKeyValue.equalsIgnoreCase(""))) {
				switch (key) {
				case "requestedBy":
					assertEquals(getText(requestedBy), expKeyValue, "Expected Value is not matching with Actual Value");
					break;
				case "requestedByemail":
					assertEquals(getText(requestedByemail), expKeyValue, "Expected Value is not matching with Actual Value");
					break;
				case "requestedByphone":
					assertEquals(getText(requestedByphone), expKeyValue, "Expected Value is not matching with Actual Value");
					break;
				case "bussinessUnit":
					assertEquals(getText(bussinessUnit), expKeyValue, "Expected Value is not matching with Actual Value");
					break;
				case "manager":
					assertEquals(getText(manager), expKeyValue, "Expected Value is not matching with Actual Value");
					break;
				case "manageremail":
					assertEquals(getText(manageremail), expKeyValue, "Expected Value is not matching with Actual Value");
					break;
				case "managerPhone":
					assertEquals(getText(managerPhone), expKeyValue, "Expected Value is not matching with Actual Value");
					break;
				case "glCode":
					assertEquals((getText(glCode)), expKeyValue, "Expected Value is not matching with Actual Value");
					break;
				case "requestStatus":
					assertEquals(getText(requestStatus), expKeyValue, "Expected Value is not matching with Actual Value");
					break;
				}
			}
		}
		// verify requestID
		if (!(exprequestID.equalsIgnoreCase(""))) {
			assertEquals((getText(requestID)).trim(), exprequestID, "Expected Value is not matching with Actual Value");
		}
	}

}
