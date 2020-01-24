/**
 */
package com.optum.navigatorapp.base.pageobjects;

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
public class AssignResourcePage extends BasePageFunctions {
	private Locator assignResource_msid_inp= new Locator(LocatorType.id,"userid_autosearch");
	private Locator assignResource_firstName_inp= new Locator(LocatorType.id,"firstName_autosearch");
	private Locator assignResource_lastName_inp= new Locator(LocatorType.id,"lastName_autosearch");
	private Locator assignResource_email_inp= new Locator(LocatorType.id,"email_autosearch");
	private Locator assignResource_search_btn= new Locator(LocatorType.id,"searchLDAPBtn");
	private Locator assignResource_reasonforChange_inp= new Locator(LocatorType.id,"assigncomments");
	private Locator assignResource_select_link= new Locator(LocatorType.xpath,"//a[text()='Select']");
	private Locator assignResource_save_btn= new Locator(LocatorType.xpath,"//button[text()='Save']");
	
	/**
	 * This Method will assign resource based on passed JSON String. Format of
	 * JSONString should be like below. <br>
	 * Json Format: <br>
	 * { <br>
	 * LastName:"Sharma", <br>
	 * FirstName:"", <br>
	 * MSID:"", <br>
	 * Email:"" <br>
	 * reasonForChange:""<br>
	 * } <br>
	 * Only the property for which value is present in file will used to populat
	 * data on screen. for e.g. <br>
	 * 1. If you only want to search for LastName. Just provide LastName field.</br>
	 * 2. if you want to search on the basis of LastName and FirstName , provide
	 * value for both property.</br>
	 * 
	 * @param jsonAsssignResource
	 */
	public void assignResource(String jsonAsssignResource) {
		// TODO Auto-generated method stub
		searchResource(jsonAsssignResource);
		selectResource();
		addReasonForChange(jsonAsssignResource);
		clickSave();
	
	}
	/**
	 * 
	 */
	private void clickSave() {
		clickElement(assignResource_save_btn);
		
	}
	/**
	 * 
	 */
	private void addReasonForChange(String jsonAsssignResource) {
		// TODO Auto-generated method stub
		JSONObject resource = new JSONObject(jsonAsssignResource);
		String reasonForChange= resource.getString("reasonForChange");
		if (!(reasonForChange.equalsIgnoreCase(""))) {
			enterTextThorughScript(assignResource_reasonforChange_inp, reasonForChange);
		}
	}
	public void searchResource(String jsonAsssignResource) {
		// TODO Auto-generated method stub
		JSONObject resource = new JSONObject(jsonAsssignResource);
		String lastName= resource.getString("LastName");
		String firstName= resource.getString("FirstName");
		String msid= resource.getString("MSID");
		String email= resource.getString("Email");
		if (!(lastName.equalsIgnoreCase(""))) {
			enterTextThorughScript(assignResource_lastName_inp, lastName);
		}
		if (!(firstName.equalsIgnoreCase(""))) {
			enterTextThorughScript(assignResource_firstName_inp, firstName);
		}
		if (!(msid.equalsIgnoreCase(""))) {
			enterTextThorughScript(assignResource_msid_inp, msid);
		}
		if (!(email.equalsIgnoreCase(""))) {
			enterTextThorughScript(assignResource_email_inp, email);
		}
		clickElement(assignResource_search_btn);
	}
	
	public void selectResource() {
		clickElement(assignResource_select_link);
	}
}
