package com.optum.navigatorapp.base.pageobjects;

import java.util.logging.Logger;

import com.optum.automation.coreframework.baseclasses.BasePageFunctions;
import com.optum.automation.coreframework.browser.Locator;
import com.optum.automation.coreframework.browser.Locator.DynamicLocator;
import com.optum.automation.coreframework.browser.LocatorType;
import com.optum.automation.coreframework.browser.PageFactory.Page;

@Page
public class LoadRole extends BasePageFunctions {
	private static Logger	logger													= Logger.getLogger(LoadRole.class.getSimpleName());
	Locator								requestRole_DropDown						= new Locator(LocatorType.id, "requestRole");
	Locator								dashboard_requestRole_DropDown	= new Locator(LocatorType.xpath, "//select[@id='requestRole']/following-sibling::button/span/following-sibling::span");
	DynamicLocator				dashBoard_requestRole_options		= new DynamicLocator(LocatorType.xpath, "//span[text()='%s']");
	Locator								msID														= new Locator(LocatorType.id, "msId");
	Locator								load_Btn												= new Locator(LocatorType.id, "refreshPage");

	boolean	isMSIDChanged;
	boolean	isRoleChanged;
	

	// SetRole Through Dashboard is handled in different way(different in other
	// pages)
	public void setRoleThroughDashboard(String role) {
		isRoleChanged	= true;
		String currentSelectedRole = getText(dashboard_requestRole_DropDown);
		if (!(role.equalsIgnoreCase(currentSelectedRole))) {
			clickElement(dashboard_requestRole_DropDown);
			// highLight(dashBoard_requestRole_options.format(role));
			clickElement(dashBoard_requestRole_options.format(role));

		} else
			isRoleChanged = false;
	}

	// Setting Role through dashBoard Page.
	public void setRole(String role) {
		isRoleChanged	= true;
		String currentSelectedRole = getCurrentSelectedValue(requestRole_DropDown);
		if (!(role.equalsIgnoreCase(currentSelectedRole)))
			selectValueByVisibleText(requestRole_DropDown, role);
		else
			isRoleChanged = false;
	}

	public void setMSID(String MSID) {
		isMSIDChanged	= true;
		if (!(MSID.equalsIgnoreCase(""))) {
			String currentText = getAttribute(msID, "value");
			if (!(currentText.equalsIgnoreCase(MSID))) {
				enterText(msID, MSID);
			} else
				isMSIDChanged = false;
		} else
			isMSIDChanged = false;

	}

	/**
	 * @param msID2
	 */

	public boolean clickLoad() {
		boolean isClicked = false;
		if (isMSIDChanged || isRoleChanged) {
			clickElement(load_Btn);
			isClicked = true;
		} else
			logger.info("Not Clicking Load Button as niether MSID nor Role was changed");
		return isClicked;
	}

}
