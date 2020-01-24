package com.optum.automation.coreframework.browser;
/**
 * @author Manoj Sharma
 *
 */
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import static com.optum.automation.coreframework.browser.DriverType.valueOf;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.optum.automation.coreframework.utils.AutomationProfile;


public class DriverFactory {
	 private static Logger logger = Logger.getLogger(DriverFactory.class.getName()); 
	private static WebDriver webDriver;
	private static DriverType selectedBrowser;
	private final static boolean useRemoteWebDriver = Boolean.parseBoolean(AutomationProfile.getProfile().runOnGrid);

	private DriverFactory() {
		
	}

	public static WebDriver getDriver() {
		if (null == webDriver) {
			DriverType driverType = null;
			String browser = AutomationProfile.getProfile().browser;
			try {
				driverType = valueOf(browser.toUpperCase());
			} catch (IllegalArgumentException ignored) {
				logger.error("Unknown driver specified");
			} catch (NullPointerException ignored) {
				logger.error("No driver specified");
			}
			selectedBrowser = driverType;
			try {
				instantiateWebDriver(selectedBrowser);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return webDriver;
	}


	public static void quitDriver() {
		if (null != webDriver) {
			webDriver.quit();
			webDriver = null;
		}
	}

	private static void instantiateWebDriver(DriverType driverType) throws MalformedURLException {
		
		logger.info("Selected Browser: " + selectedBrowser);
		logger.info("Connecting to Selenium Grid: " + useRemoteWebDriver);
		

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

		if (useRemoteWebDriver) {
			URL seleniumGridURL = new URL(AutomationProfile.getProfile().hubURL);
			String desiredBrowserVersion = AutomationProfile.getProfile().desiredBrowserVersion;
			String desiredPlatform = AutomationProfile.getProfile().desiredPlatform;

			if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
				desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
			}

			if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty()) {
				desiredCapabilities.setVersion(desiredBrowserVersion);
			}

			desiredCapabilities.setBrowserName(selectedBrowser.toString());
			webDriver = new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
		} 
		else {
			webDriver = driverType.getWebDriverObject(desiredCapabilities);
		}
	}
}
