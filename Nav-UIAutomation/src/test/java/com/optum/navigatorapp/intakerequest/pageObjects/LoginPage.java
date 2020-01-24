package com.optum.navigatorapp.intakerequest.pageObjects;

import com.optum.automation.coreframework.baseclasses.BasePageFunctions;
import com.optum.automation.coreframework.browser.Locator;
import com.optum.automation.coreframework.browser.LocatorType;
import com.optum.automation.coreframework.browser.PageFactory.Page;
import com.optum.navigatorapp.intakerequest.databeans.Login;

@Page
public class LoginPage extends BasePageFunctions {

	Locator username=new Locator(LocatorType.xpath, "");
	Locator password=new Locator(LocatorType.id,"");
	Locator submit =new Locator(LocatorType.id,"");
	//DynamicLocator a=new DynamicLocator(LocatorType.xpath,"requesttext_%d");
	
	public void doLogin(Login lgn) {
		enterText(username, lgn.UserName);
		enterText(password, lgn.Password);
		clickElement(submit);
		
	}
}
