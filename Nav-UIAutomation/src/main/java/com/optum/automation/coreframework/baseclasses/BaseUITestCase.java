package com.optum.automation.coreframework.baseclasses;

/**
 * @author Manoj Sharma
 *
 */
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.optum.automation.coreframework.browser.DriverFactory;
import com.optum.automation.coreframework.browser.PageFactory;
import com.optum.automation.coreframework.browser.PageFactory.Page;
import com.optum.automation.coreframework.utils.AutomationProfile;
import com.optum.automation.coreframework.utils.Log;
import com.optum.automation.coreframework.utils.ScreenCapture;

public abstract class BaseUITestCase {

	@SuppressWarnings("unused")
	private static Logger	logger					= Logger.getLogger(BaseUITestCase.class);
	private String				outputDirTestNG	= null;
	public static String	testClassName		= null;

	public String	browserURL	= null;
	WebDriver			driver			= null;
	String				applicationURL;

	@BeforeSuite(alwaysRun = true)
	public void startup() {
		AutomationProfile.createProfile();
		applicationURL = AutomationProfile.getProfile().appUrl;
	}

	@AfterSuite(alwaysRun = true)
	public void shutdown() {

		DriverFactory.quitDriver();
	}

	@AfterMethod(alwaysRun = true)
	public void captureScreenShot(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE && AutomationProfile.getProfile().screenShotRequired.equalsIgnoreCase("True")) {

			ScreenCapture.saveSnapshot(DriverFactory.getDriver(), result, outputDirTestNG);

		}

	}

	@BeforeClass(alwaysRun = true)
	public void loadPageObjects() {

		testClassName = this.getClass().getName();
		driver = DriverFactory.getDriver();
		driver.manage().window().maximize();
		setTimeOutsForDriver();
		driver.get(applicationURL);
		BasePageFunctions.setDriver(driver);
		try {
			for (Field field : this.getClass().getDeclaredFields()) {
				if (field.getType().isAnnotationPresent(Page.class)) {
					if (!field.isAccessible()) {
						field.setAccessible(true);
					}
					field.set(this, PageFactory.newInstance(field.getType()));
				}
			}
		} catch (Exception e) {
			throw new Error("Error instantiating page object: " + e.getMessage());
		}

	}

	private void setTimeOutsForDriver() {
		driver.manage().timeouts().implicitlyWait(Long.parseLong(AutomationProfile.getProfile().implicitWait), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(AutomationProfile.getProfile().pageLoadTimeout), TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(Long.parseLong(AutomationProfile.getProfile().scriptTimeout), TimeUnit.SECONDS);

	}

}
