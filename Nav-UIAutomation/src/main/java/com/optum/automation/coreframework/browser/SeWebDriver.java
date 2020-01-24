package com.optum.automation.coreframework.browser;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.optum.automation.coreframework.browser.Locator;


public class SeWebDriver {

	/*protected WebDriver driver;
	protected String driverpath;
	protected String webUrl;
	private long defTimeOut = 60;

	protected SeWebDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public WebElement findElement(Locator findLocator) {
		
		List<WebElement> retElement = new ArrayList<WebElement>();
		try {
			waitForElementLocated(findLocator);		
		
			  retElement =  driver.findElements(findBy.getwrappedLocator
					.get(findLocator, locator));		
		
			if(retElement.size() == 0)
			{
				retElement.add(null);
			}
		}
		catch(Exception e)
		{
			 e.printStackTrace(); 
			return retElement.get(0);
		}
		return retElement.get(0);
	}
	

	*//**
	 
	 * 
	 * it takes only one parameter Properites
	 * @param prp
	 * @return
	 *//*
	public WebElement findElement(Properties prp) {
		WebElement retElement = null;
		
		try {
			
			 System.out.println("value1=" + prp.getProperty("findBy.getwrappedLocator"));
			System.out.println("value2=" + prp.getProperty("prpValue")); 
			
			 retElement =  driver.findElement(findBy.getwrappedLocator.get(prp.getProperty("findBy.getwrappedLocator"),prp.getProperty("prpValue")));
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println(prp.getProperty("prpValue").toString());
		}
		
		return retElement;
	}

	public List<WebElement> findElements(Locator findLocator,
			String locator) {
		List<WebElement> webElements = null;
		webElements = driver.findElements(findBy.getwrappedLocator.get(findLocator,
				locator));

		return  webElements;
	}

	 

	// Wrapping-ups WebDriver methods

	public WebDriver.Options manage() {
		return driver.manage();
	}

	public void launch(String webURL) {
		this.webUrl = webURL;
		driver.get(webURL);
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public void close() {
		driver.close();
	}

	public void quit() {
		driver.quit();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	// wrapping-up WebDriver.Navigation

	public void back() {
		getWebDriver().navigate().back();

	}

	public void forward() {
		getWebDriver().navigate().forward();
	}

	public void refresh() {
		getWebDriver().navigate().refresh();

	}

	public void to(String url) {
		getWebDriver().navigate().to(url);
	}

	public void to(URL url) {
		getWebDriver().navigate().to(url);
	}

	// wrapping-up WebDriver.Options

	public void addCookie(Cookie cookie) {
		getWebDriver().manage().addCookie(cookie);

	}

	public void deleteAllCookies() {
		getWebDriver().manage().deleteAllCookies();

	}

	public void deleteCookie(Cookie cookie) {
		getWebDriver().manage().deleteCookie(cookie);

	}

	public void deleteCookieNamed(String name) {
		getWebDriver().manage().deleteCookieNamed(name);

	}

	public Cookie getCookieNamed(String name) {
		return getWebDriver().manage().getCookieNamed(name);
	}

	public Set<Cookie> getCookies() {
		return getWebDriver().manage().getCookies();
	}

	public SeWebDriver window(String nameOrHandle) {
		return toSeWebDriver((getWebDriver().switchTo().window(nameOrHandle)));
	}

	// wrapping WebDriver.TargetLocator
	public WebElement activeElement() {
		return  getWebDriver().switchTo().activeElement();
	}

	public Alert alert() {
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.alertIsPresent());
			return getWebDriver().switchTo().alert();
		}
		catch(NoAlertPresentException ex)
		{
			return null;
		}
	}

	public void dismissAlert() {
		if(alert()!=null)
			alert().dismiss();
	}

	public void acceptAlert() {
		if(alert()!=null)
			alert().accept();
	}

	public String getTextAlert() {
		if(alert()!=null)
		return alert().getText();
		return "";
	}

	public void sendKeysAlert(String keysToSend) {
		if(alert()!=null)
		alert().sendKeys(keysToSend);
	}

	public SeWebDriver defaultContent() {
		return toSeWebDriver((getWebDriver().switchTo().defaultContent()));
	}

	public SeWebDriver frame(int index) {
		return toSeWebDriver(getWebDriver().switchTo().frame(index));

	}

	public SeWebDriver frame(String nameOrId) {
		return toSeWebDriver(getWebDriver().switchTo().frame(nameOrId));

	}

	public SeWebDriver frame(WebElement frameElement) {
		return toSeWebDriver(getWebDriver().switchTo().frame(
				 frameElement));
	}

	// wrapping WebDriver.Timeouts

	public Timeouts implicitlyWait(long time, TimeUnit unit) {
		return getWebDriver().manage().timeouts().implicitlyWait(time, unit);
	}

	public Timeouts implicitlyWait(long time) {
		return getWebDriver().manage().timeouts()
				.implicitlyWait(time, TimeUnit.MILLISECONDS);
	}

	public Timeouts setScriptTimeout(long time, TimeUnit unit) {
		return getWebDriver().manage().timeouts().setScriptTimeout(time, unit);
	}

	public Timeouts setScriptTimeout(long time) {
		return getWebDriver().manage().timeouts()
				.setScriptTimeout(time, TimeUnit.MILLISECONDS);
	}

	// wrapping WebDriver.Window
	public void maximize() {
		getWebDriver().manage().window().maximize();
	}

	public Actions getAction() {
		return new Actions(getWebDriver());
	}

	// Additional methods
	public void dragAndDropByCoordinates(WebElement source, int x, int y) {
		getAction().dragAndDropBy(source, x, y).build()
				.perform();

	}

	public void dragAndDrop(WebElement source, WebElement destination) {
		getAction()
				.dragAndDrop(source, destination)
				.build().perform();

	}

	public void keyUp(Keys key) {
		getAction().keyUp(key).perform();

	}

	public void keyUp(WebElement control, Keys key) {
		getAction().keyUp(control, key).perform();
	}

	public void keyDown(Keys key) {
		getAction().sendKeys(key).perform();
	}

	public void keyDown(WebElement control, Keys key) {
		getAction().sendKeys(control, key).perform();
	}

	public void doubleClick(WebElement control) {
		getAction().doubleClick(control).perform();
	}

	public void mouseDown(WebElement control) {
		getAction().clickAndHold(control).perform();

	}

	public void mouseUp(WebElement control) {
		getAction().release(control).perform();
	}

	public void moveByOffset(int xOffset, int yOffset) {
		getAction().moveByOffset(xOffset, yOffset).perform();
	}

	public void mouseMove(WebElement element) {
		getAction().moveToElement(element).build().perform();
	}

	public void contextClick(WebElement element) {
		getAction().contextClick(element).perform();
	}

	public static boolean isChecked(WebElement element) {
		if (element.isSelected()) {
			return true;
		}
		return false;
	}

	public static void uncheck(WebElement element) {
		if (element.isSelected()) {
			element.click();
		}
	}

	public static void submit(WebElement element) {
		element.submit();
	}

	public static void click(WebElement element) {
		element.click();
	}

	public static boolean isEditable(WebElement control) {
		String tagName = control.getTagName().toLowerCase();
		boolean acceptableTagName = "input".equals(tagName)
				|| "select".equals(tagName);
		String readonly = "";
		if ("input".equals(tagName)) {
			readonly = control.getAttribute("readonly");
			if (readonly == null)
				readonly = "";
		}

		return control.isEnabled() && acceptableTagName && "".equals(readonly);
	}

	// Wait methods

	public void waitForPageToLoad() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");
			}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver, defTimeOut);
		try {
			wait.until(expectation);
		} catch (Exception error) {
			new Exception("Failed while loading page", error);
		}
	}

	public void waitForElementPresent(Locator findBy) {
		WebDriverWait wait = new WebDriverWait(driver, defTimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(findBy.getWrappedLocator()));
	}

	public void waitForElementVisible(Locator findBy, String locator) {
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, defTimeOut);
			wait.until(ExpectedConditions.visibilityOfElementLocated(findBy.getwrappedLocator
				.get(findBy.getwrappedLocator(), locator)));
		}
		catch(Exception e)
		{
			System.out.println("Element not visible in " + defTimeOut + " Seconds");
		}
	}
	
	public void waitForElementLocated(Locator findBy, String locator) {
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, defTimeOut);
			wait.until(ExpectedConditions.elementToBeClickable(findBy.getWrappedLocator()));
		}
		catch(Exception e)
		{
			 
		}
	}

	public boolean verifyElementPresent(Locator findBy, String locator) {
		try {
			this.findElement(findBy.getwrappedLocator(), locator);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			 
			return false;
		}
	}

	public void selectByValue(WebElement element, String valueToSelect) {
		new Select(element).selectByValue(valueToSelect);
	}

	public void selectByIndex(WebElement element, int index) {
		new Select(element).selectByIndex(index);
	}

	public void selectByVisibleText(WebElement element, String visibleText) {
		new Select(element).selectByVisibleText(visibleText);
	}

	public void deselectByValue(WebElement element, String valueToDeselect) {
		new Select(element).deselectByValue(valueToDeselect);
	}

	public void deselectByIndex(WebElement element, int index) {
		new Select(element).deselectByIndex(index);
	}

	public void deselectByVisibleText(WebElement element, String visibleText) {
		new Select(element).deselectByVisibleText(visibleText);
	}

	public void deselectAll(WebElement element) {
		new Select(element).deselectAll();
	}

	 
	public SeWebDriver toSeWebDriver(WebDriver driver) {
		return new SeWebDriver(driver);
	}
	
	private void getDriver(String browserType) {
		String testpath = System.getProperty("user.dir") + System.getProperty("file.separator") ;
		+"src" + System.getProperty("file.separator")
		+"test" + System.getProperty("file.separator")
		+"java"  + System.getProperty("file.separator") 		 
		+"com" + System.getProperty("file.separator")
		+"optum" + System.getProperty("file.separator")
		+"testutils" + System.getProperty("file.separator") ;
		
		if (browserType.equalsIgnoreCase("Internet Explorer") || browserType.equalsIgnoreCase("iexplore") ||
				browserType.equalsIgnoreCase("IE") || browserType.equalsIgnoreCase("IExplorer")) {					
			 
					 
			System.setProperty("webdriver.ie.driver", testpath+ "IEDriverServer.exe" );		
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer(); 
			//capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);			
			 capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			 capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			capabilities.setJavascriptEnabled(true);
			
			 driver=new InternetExplorerDriver(capabilities);
			 driver.manage().window().maximize();
			 
			 System.out.println("IE Launched ");
			 
			
			 
			
		} else if (browserType.equalsIgnoreCase("firefox")) {
			 
			driver = new FirefoxDriver();
		} else if (browserType.equalsIgnoreCase("chrome")) {
			 
			System.setProperty("webdriver.chrome.driver",testpath+ "chromeserver.exe" );
			DesiredCapabilities Capability = DesiredCapabilities.chrome();
			 
			driver = new ChromeDriver(Capability);
			
		} 
		 
	}
	
	public WebDriver getNewRemoteDriver(String hubUrl, Capabilities capabilities) {
		RemoteWebDriver driver;
		try {
			driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
		} catch (MalformedURLException ex) {
			throw new RuntimeException("Hub URL does not seem to be working: " + hubUrl, ex);
		}
		return driver;
	}

	*/
}
