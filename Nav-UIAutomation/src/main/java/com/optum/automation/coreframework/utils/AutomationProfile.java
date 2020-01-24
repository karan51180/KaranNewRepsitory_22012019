package com.optum.automation.coreframework.utils;

/**
 * @author Manoj Sharma
 *
 */
import java.io.InputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * This class will load all the framework related Properties ,present in
 * Config.properties file. This file must be present in classpath(under resource
 * folder)
 * 
 * In order to use this first we have to call createProfile function and then we
 * can call get profile function. If creatprofile function is nevere called then
 * you will receive IllegalAccessException. This is singelton class so only
 * first invoke will create the object.
 */

public class AutomationProfile {

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public static @interface Parameter {
		public String value();
	}

	private static Logger logger = Logger.getLogger(AutomationProfile.class);

	@Parameter("runOnGrid")
	public String runOnGrid = "OFF";
	@Parameter("gridURL")
	public String hubURL;
	@Parameter("desiredBrowserVersion")
	public String desiredBrowserVersion;
	@Parameter("desiredPlatform")
	public String desiredPlatform;

	@Parameter("URL")
	public String appUrl;

	@Parameter("encryptedMode")
	public String encryptedMode = "OFF";
	@Parameter("username")
	public String userName;
	@Parameter("password")
	public String password;

	@Parameter("browser")
	public String browser = "firefox";

	// Whether To take screenshot or Not
	@Parameter("screenshot.required")
	public String screenShotRequired = "true";
	@Parameter("screenshot.dir")
	public String screenShotDir;

	// Testdata Related Properties
	// If testdata is residing in diffrent location then provide testdata Directory
	// name here .
	// Else it will take folder name as "Testdata"
	@Parameter("testdata.dir")
	public String testDataProviderDir;
	@Parameter("testdata.file")
	public String testDataFile;

	// Database Related Properties
	@Parameter("database.url")
	public String jdbcUrl;
	@Parameter("pageLoadTimeout")
	public String pageLoadTimeout;
	@Parameter("implicitWait")
	public String implicitWait;
	@Parameter("scriptTimeout")
	public String scriptTimeout;

	private static Properties automationProperties = null;
	private static AutomationProfile singletonInstance = null;

	public static AutomationProfile getProfile() {
		if (singletonInstance == null) {
			throw new Error(
					"getProfile called before createProfile. Please call AutomationProfile.createProfile before calling this method.");
		}

		return singletonInstance;
	}

	public static synchronized AutomationProfile createProfile() {

		if (singletonInstance == null) {

			singletonInstance = new AutomationProfile();

			Field fields[] = singletonInstance.getClass().getDeclaredFields();
			for (Field field : fields) {
				Parameter param = field.getAnnotation(Parameter.class);
				if (param != null) {
					String propValue = getProperty(param.value());
					if (propValue != null && !propValue.isEmpty()) {
						propValue = propValue.trim();
						try {
							field.set(singletonInstance, propValue);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		return singletonInstance;
	}

	private static String getProperty(String propertyName) {

		String propertyValue = null;

		if (automationProperties == null) {
			automationProperties = new Properties();

			InputStream in = AutomationProfile.class.getResourceAsStream("/config.properties");

			if (in == null) {
				try {
					throw new Exception("config.properties not found in classpath");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				automationProperties.load(in);
			} catch (Exception e) {
				logger.fatal("Failed to load config.properties: " + e.getMessage());
				throw new Error(e);
			}
		}

		// get value from property file, if it is not set, get JVM argument
		propertyValue = automationProperties.getProperty(propertyName);
		if (propertyValue == null) {
			propertyValue = System.getProperty(propertyName);
		}
		return propertyValue;
	}

	public boolean isInternetExplorer() {

		return browser.equalsIgnoreCase("ie");
	}

	public boolean isChrome() {

		return browser.equalsIgnoreCase("chrome");
	}

	public boolean isfirefox() {

		return browser.equalsIgnoreCase("firefox");
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		for (Field field : this.getClass().getDeclaredFields()) {
			Parameter param = field.getAnnotation(Parameter.class);
			if (param != null) {
				try {
					sb.append(String.format("%s : %s, ", param.value(), field.get(this)));
				} catch (Exception e) {
					logger.fatal(e.getMessage());
					throw new Error(e);
				}
			}
		}
		return sb.toString();
	}

}
