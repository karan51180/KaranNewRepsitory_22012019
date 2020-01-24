package com.optum.automation.coreframework.browser;
/**
 * @author Manoj Sharma
 *
 */
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.By.ByXPath;

/**
 * This is a Wrapper class for Selenium By class. It also has DynamicLocator class for Dynamic Locators. 
*
 */
public class Locator {
	
	private LocatorType locatorType  = null;
	private String      locatorValue = null;
	private By			wrappedLocator = null;
	
	public Locator(LocatorType locatorType, String locatorValue) {
		this.locatorType = locatorType;
		this.locatorValue = locatorValue;
		setWrappedLocator();
	}

	protected Locator() {
		// Empty constructor for derived classes
	}
	
	
	@Override
	public String toString() {
		return (locatorType + "=" + locatorValue);
	}
	


	/**
	 *  Converts a locator to a webdriver locator of type {@link By) Class.
	 * LocatorType.ID= By.Id
	 * LocatorType.Name= By.Name
	 * LocatorType.partialLink=By.partialLinkText
	 * LocatorType.link=By.linkText
	 * LocatorType.xpath=By.xpath
	 * LocatorType.css=By.cssSelector
	 * LocatorType.className=By.className
	 * LocatorType.tagName=By.tagName
	 */
	public By getWrappedLocator() {
		return wrappedLocator;
	}
	
	private void setWrappedLocator() {
		
		switch (locatorType) {
		case id:
			wrappedLocator = By.id(locatorValue);
			break;
		
		case name:
			wrappedLocator = By.name(locatorValue);
			break;
			
		case link:
			wrappedLocator = By.linkText(locatorValue);
			break;
			
		case xpath:
			wrappedLocator = By.xpath(locatorValue);
			break;
			
		case css:
			wrappedLocator = By.cssSelector(locatorValue);
			break;
			
		case className:
			wrappedLocator = By.className(locatorValue);
			break;
			
		case tagName:
			wrappedLocator = By.tagName(locatorValue);
			break;
			
		case partialLink:
			wrappedLocator = By.partialLinkText(locatorValue);
			break;

		default:
			throw new Error("Unsupported LocatorType: " +locatorType.toString());
		}
	}
	
	
	/**
	 * Dynamic Locator class.
	 *
	 */
	public static class DynamicLocator {
		
		private LocatorType locatorType  = null;
		private String      locatorValue = null;
		
		public DynamicLocator(LocatorType locatorType, String locatorValue) {
			this.locatorType = locatorType;
			this.locatorValue = locatorValue;
		}

		/**
		 *Format method is similar to {@link String#format(String, Object...) method)
		 */
		public Locator format(Object... args) {

			String interpolatedLocatorValue = String.format(locatorValue, args);
			return new Locator(locatorType, interpolatedLocatorValue);
		}
		
	}
	
}
