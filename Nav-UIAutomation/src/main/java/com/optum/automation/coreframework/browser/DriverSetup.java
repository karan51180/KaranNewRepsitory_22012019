package com.optum.automation.coreframework.browser;
/**
 * @author Manoj Sharma
 *
 */
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface DriverSetup {
	  RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities);
}
