package com.optum.automation.coreframework.baseclasses;

/**
 * @author Manoj Sharma
 *
 */
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.optum.automation.coreframework.browser.Locator;
import com.optum.automation.coreframework.browser.Locator.DynamicLocator;
import com.optum.automation.coreframework.browser.LocatorType;
import com.optum.automation.coreframework.utils.AutomationProfile;

public class BasePageFunctions  {
	private static Logger				logger	= Logger.getLogger(BasePageFunctions.class.getName());
	protected static WebDriver	driver;

	
	public static void setDriver(WebDriver driver) {
		// TODO Auto-generated method stub
		BasePageFunctions.driver = driver;

	}

	/*
	 * ====================================================== Driver related
	 * Functions ==================================================
	 * 
	 */
	/******
	 * This will be the wrapper method for drive.findElement
	 */

	protected WebElement findElement(Locator locator) {
		// Before finding a element we will wait for it.
		WebElement e = null;

		if (waitForElementToAppear(locator)) {
			e = driver.findElement(locator.getWrappedLocator());
			/*
			 * Sometime Element is present in DOM , but not into view. So below code will
			 * bring element to focus
			 */
			if (!(isVisibleInViewport(e))) {
				logger.info("Element is not in view, So bringing it to focus");
				bringElementToFocus(e);
			}
		}
		return e;

	}

	protected void bringElementToFocus(WebElement e) {
		executeJavaScript("arguments[0].scrollIntoView(true);", e);
	}

	protected boolean waitForElementToAppear(Locator locator) {
		WebElement we = null;
		logger.info("Waiting for Element '" + locator + "'to be appear on page.");
		try {
			we = (new WebDriverWait(driver, 120)).until(ExpectedConditions.visibilityOfElementLocated(locator.getWrappedLocator()));

		} catch (TimeoutException toe) {
			logger.error("Timeout waiting for webelement+ '" + locator + "' to be enabled<br></br>" + toe.getStackTrace());
		} catch (Exception e) {
			logger.error("Exception occured<br></br>" + e.getStackTrace());
		}
		return (we != null);
	
	}

	protected boolean waitForElementToEnabled(Locator locator) {
		WebElement we = null;
		logger.info("Waiting for Element '" + locator + "'to be clickable on page.");
		try {
			we = (new WebDriverWait(driver,Long.parseLong(AutomationProfile.getProfile().implicitWait))).until(ExpectedConditions.elementToBeClickable(locator.getWrappedLocator()));
		} catch (TimeoutException toe) {
			logger.error("Timeout waiting for webelement+ '" + locator + "' to be enabled<br></br>" + toe.getStackTrace());
		} catch (Exception e) {
			logger.error("Exception occured<br></br>" + e.getStackTrace());
		}

		return (we != null);
	
	}

	protected boolean waitForElementToEnabled(WebElement element) {
		WebElement we = null;
		logger.info("Waiting for Element '" + element + "'to be clickable on page.");
		try {
			we = (new WebDriverWait(driver,Long.parseLong(AutomationProfile.getProfile().implicitWait))).until(ExpectedConditions.elementToBeClickable(element));
		} catch (TimeoutException toe) {
			logger.error("Timeout waiting for webelement+ '" + element + "' to be enabled<br></br>" + toe.getStackTrace());
		} catch (Exception e) {
			logger.error("Exception occured<br></br>" + e.getStackTrace());
		}

		return (we != null);
		
	}

	protected void highLight(Locator locator) {
		WebElement element = driver.findElement(locator.getWrappedLocator());
		for (int i = 0; i < 7; i++) {
			try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: black; border: 4px solid red;");
				Thread.sleep(500);
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void executeJavaScript(String script, Object... args) {
		logger.info(String.format("executing JavaScript Executor with script '%s'", script));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script, args);

	}

	/*
	 * ============================================================================
	 * WebElement Related Functions
	 * ============================================================================
	 */

	protected void enterText(Locator locator, String keysToSend) {
		findElement(locator).clear();
		findElement(locator).sendKeys(keysToSend);
		logger.info("Entering text in element " + locator + " with native sendKeys Method and value= " + keysToSend);
	}

	/**
	 * Written this function specifically for Angual JS pages. As Sometime selenium
	 * sendKeys methods are not sending the complete value to page. Noticed this
	 * issue couple of time on IE11. reference
	 * https://github.com/angular/protractor/issues/698
	 * 
	 * So created this function which will enter the text through script and in last
	 * it will send whitespace character through sendKeys. So that ng-pristine model
	 * should change to ng-dirty. Else form will treat the field as empty.
	 * 
	 * @param locator
	 * @param KeysToSend
	 */
	protected void enterTextThorughScript(Locator locator, String KeysToSend) {
		WebElement e = findElement(locator);
		logger.info("Entering text in element " + locator + " With Java Script method with value =" + KeysToSend);

		// First Clear existing text and the enter.
		executeJavaScript(String.format("arguments[0].value='%s'", ""), e);
		executeJavaScript(String.format("arguments[0].value='%s'", KeysToSend), e);
		// For angular Forms sending one extra key to input box. So that "ng-pristine"
		// in class change to "ng-dirty".
		//Extra sendKeys is specially for IE browser. As there are known issue with IEdriver and Send Keys.
		e.sendKeys(" ");
		e.sendKeys(" ");
		
	}

	protected void enterNumber(Locator locator, String keysToSend) {

		WebElement e = findElement(locator);
		logger.info("Entering Number in element " + locator + " With Java Script method with value =" + keysToSend);
		if (AutomationProfile.getProfile().isInternetExplorer()) {
			executeJavaScript(String.format("arguments[0].value=%s", keysToSend), e);
			/*
			 * This extra piece of code is just to make sure Angular form take this as
			 * Input. In Future will try to identify better way of inputing number in input
			 * boxes only allowing text field.
			 */
			//App.focus("Internet Explorer");
			Robot robot = null;
			try {
				robot = new Robot();
				Thread.sleep(1000);
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// First click on Element again.
			e.click();
			// Enter any text
			robot.keyPress(KeyEvent.VK_2);
			// remove text
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
		} 
		else {
			e.sendKeys(keysToSend);
		}
	}
	protected void enterTextArea(Locator locator, String keysToSend) {
		findElement(locator).click();
		findElement(locator).clear();
		findElement(locator).sendKeys(keysToSend);
	}

	protected void clickElement(Locator locator) {
		
				WebElement e = findElement(locator);
				if (waitForElementToEnabled(e))
					logger.info("Clicking Element " + locator );
				e.click();
	

	}

	protected void clickElement(WebElement e) {

		e.click();

	}

	protected void confirmAlert() {
		driver.switchTo().alert().accept();
	}

	protected void declineAlert() {
		driver.switchTo().alert().dismiss();
	}

	public void waitForPageToLoad() {
		/*
		 * //Some below Javascript is failing becuase of known issue.
		 * //https://github.com/seleniumhq/selenium/issues/1590
		 * logger.info("Waiting For Page to Load"); long pageLoadTimeout =
		 * Long.parseLong(AutomationProfile.getProfile().pageLoadTimeout); new
		 * WebDriverWait(driver, pageLoadTimeout).until(webDriver ->
		 * ((JavascriptExecutor) webDriver)
		 * .executeScript("return document.readyState").equals("complete"));
		 * logger.info("Page loaded Successfully");
		 */

		long pageLoadTimeout = Long.parseLong(AutomationProfile.getProfile().pageLoadTimeout);
		try {
			Wait<WebDriver> wait=new WebDriverWait(driver, Long.parseLong(AutomationProfile.getProfile().pageLoadTimeout));
			wait.until(ExpectedConditions.titleIs("Navigator"));
		/*	
			Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofSeconds(1)).withTimeout(Duration.ofSeconds(pageLoadTimeout)).ignoring(NoSuchElementException.class);
			logger.info("Waiting for page to be loaded");
			fluentWait.until(ExpectedConditions.titleIs("Navigator"));*/

		} catch (TimeoutException toe) {
			logger.error("Page did not loaded in"+AutomationProfile.getProfile().pageLoadTimeout +"seconds");
		} catch (Exception e) {
			logger.error("Exception occured<br></br>" + e.getStackTrace());
		}

	}

	private Boolean isVisibleInViewport(WebElement element) {
				      return (Boolean) ((JavascriptExecutor) driver).executeScript("var elem = arguments[0],"
          	 		+ "                 " + "  box = elem.getBoundingClientRect(),    " +
          			 "  cx = box.left + box.width / 2,         " +
          	 		"  cy = box.top + box.height / 2,         " + ""
          	 				+ "  e = document.elementFromPoint(cx, cy); " +
          	 		"for (; e; e = e.parentElement) {         " + ""
          	 				+ "  if (e === elem)                        " + 
          	 		"    return true;                         " + "}    "
          	 				+ "                                    " +
          	 		"return false;                            ", element);
            
	}

	protected void waitForAjaxDialog() {
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("ajaxDialog")));
		} catch (TimeoutException te) {
			logger.info("Ajax Loading popup did not appear in 5 seconds");
		}
		// IF Pop up appear in
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.id("ajaxDialog")));
		} catch (TimeoutException te) {
			logger.info("Ajax Loading popup did not disappear in 60 seconds");
		}
	}

	protected void waitForAjaxDialog(long timeout) {
		try {
			new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(By.id("ajaxDialog")));
		} catch (TimeoutException te) {
			logger.info("Ajax Loading popup did not appear in 5 seconds");
		}
		// IF Pop up appear in
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOfElementLocated(By.id("ajaxDialog")));
		} catch (TimeoutException te) {
			logger.info("Ajax Loading popup did not disappear in" + timeout + "seconds");
		}
	}

	protected void waitFor(Locator locator) {
		try {
			logger.info("Waiting for "+ locator);
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(locator.getWrappedLocator()));
		} catch (TimeoutException te) {
			logger.info("Element did not appear even after 1 min");
		}

	}
	protected void waitFor(Locator locator, long timeout) {
		try {
			logger.info("Waiting for "+ locator);
			new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(locator.getWrappedLocator()));
		} catch (TimeoutException te) {
			logger.info("Element did not appear even after 1 min");
		}

	}
	protected boolean isElementPresent(Locator locator) {

		return findElement(locator).isDisplayed();
	}

	/**
	 * To check whether an Locator all together exists on the page. to do this first
	 * set Implicit wait to 0 and then check and again putting back the implicit
	 * wait back
	 * 
	 * @param locator
	 * @return
	 */
	protected boolean isElementExist(Locator locator) {
		waitForPageToLoad();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		boolean exists = driver.findElements(locator.getWrappedLocator()).size() != 0;
		driver.manage().timeouts().implicitlyWait(Long.parseLong(AutomationProfile.getProfile().implicitWait), TimeUnit.SECONDS);
		return exists;
	}

	protected String getText(Locator locator) {

		return findElement(locator).getText();
	}

	protected String getAttribute(Locator locator, String attributeValue) {

		return findElement(locator).getAttribute(attributeValue);
	}

	protected void waitForElementTobeDisappear(Locator locator) {

		try {
			logger.info("Waiting for Element to be disappear");
			Wait<WebDriver> wait=new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(locator.getWrappedLocator())));
			
		} catch (TimeoutException toe) {
			logger.error("Element did not disappear after 1 minute");
		} catch (Exception e) {
			logger.error("Exception occured<br></br>" + e.getStackTrace());
		}
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	public int getXpathCount(String xpath) {

		List<WebElement> elements = null;
		By by = By.xpath(xpath);
		elements = driver.findElements(by);

		return elements.size();
	}
	
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	
	public static void uploadattachment(String fileLocation) {
    try {
    
        setClipboardData(fileLocation);
        
        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    } catch (Exception exp) {
     exp.printStackTrace();
    }
}

	public void selectDateAndTime(Locator locator, String dateAttribute) {

		// Date and Time to be set in textbox

		String dateAndTime = dateAttribute;
		// button to open calendar
		WebElement selectDate = findElement(locator);
		selectDate.click();
		Locator nextLink = new Locator(LocatorType.xpath, "//span[text()='Next']");
		//Locator previousLink = new Locator(LocatorType.xpath, "//span[text()='Prev']");

		// Split the date time to get only the date part

		String date_MM_dd_yyyy[] = (dateAndTime.split(" ")[0]).split("/");
		int mm = Integer.parseInt(date_MM_dd_yyyy[0]);
		int d = Integer.parseInt(date_MM_dd_yyyy[1]);

		// convert date 01 to 1 to match calender values
		//	String yy = date_MM_dd_yyyy[2];

		// to find month difference
		int monthDiff = mm - Calendar.getInstance().get(Calendar.MONTH);
		if (monthDiff > 0) {
			for (int i = 1; i < monthDiff; i++) {
				clickElement(nextLink);
			}
		}
		// choose date

		DynamicLocator date = new DynamicLocator(LocatorType.xpath, "//td[@data-handler='selectDay']/a[text()='%s']");
		clickElement(date.format(d));
		Locator confirmDate = new Locator(LocatorType.xpath, "//button[text()='Done']");
		clickElement(confirmDate);

	}
	protected void select2Click(Locator locator,String option) {
	  clickElement(locator);
	  DynamicLocator selectOptions = new DynamicLocator(LocatorType.xpath, "//div[contains(@class,'multiselect-menu') and contains(@style,'block')]//label/span[text()='%s']");
	  clickElement(selectOptions.format(option));
	  }
	
	// ====================================================
	/*
	 * Mouse Events
	 * 
	 */

	public void doubleClick(Locator locator) {
		WebElement element = findElement(locator);
		Actions builder = new Actions(driver);
		Action dblClick = builder.doubleClick(element).build();
		dblClick.perform();

	}

	public void rightClick(Locator locator) {
		WebElement element = findElement(locator);
		if (AutomationProfile.getProfile().isChrome()) {
			((JavascriptExecutor) driver).executeScript("" + "var x=arguments[0].getBoundingClientRect().left + (arguments[0].getBoundingClientRect().right -arguments[0].getBoundingClientRect().left)/2;" + "var y=arguments[0].getBoundingClientRect().top + (arguments[0].getBoundingClientRect().bottom -arguments[0].getBoundingClientRect().top)/2;" + "var evt=document.createEvent('MouseEvents');" + "evt.initMouseEvent('contextmenu',true,true,arguments[0].ownerDocument.defaultView,1,0,0,x,y,false,false,false,false,2,null);" + "!arguments[0].dispatchEvent(evt)", element);
		} else {
			new Actions(driver).contextClick(element).perform();
		}
	}

	public void mouseDown(Locator locator) {
		WebElement element = findElement(locator);
		Actions builder = new Actions(driver);
		builder.clickAndHold(element).build().perform();
	}

	public void mouseUp(Locator locator) {
		WebElement element = findElement(locator);
		Actions builder = new Actions(driver);
		builder.release(element).build().perform();
	}

	/*
	 * Visibility of Elements Related Fuctions
	 * 
	 */
	public boolean isDisabled(Locator locator) {
		WebElement element = findElement(locator);
		return !element.isEnabled();
	}

	public boolean isEnabled(Locator locator) {
		WebElement element = findElement(locator);
		return element.isEnabled();
	}

	public boolean isVisible(Locator locator) {
		WebElement element = findElement(locator);
		return element.isDisplayed();
	}

	public boolean isNotVisible(Locator locator) {
		WebElement element = findElement(locator);
		return !element.isDisplayed();

	}

	public boolean isTextPresent(String searchText, Locator locator) {
		if (searchText.equals(getText(locator))) {
			return true;
		} else
			return false;
	}

	public void smartDoubleClick(Locator locator) {
		if (AutomationProfile.getProfile().isChrome()) {
			doubleClick(locator);
			clickElement(locator);
		} else {
			doubleClick(locator);
		}
	}

	/*
	 * Select Related Functions
	 * 
	 */
	protected void selectValueByVisibleText(Locator locator, String string) {
		WebElement e = findElement(locator);
		Select s = new Select(e);
		s.selectByVisibleText(string);

	}

	protected String getSelectedValue(Locator locator) {
		WebElement e = findElement(locator);
		Select select = new Select(e);
		WebElement tmp = select.getFirstSelectedOption();
		return tmp.getText();
	}

	protected String getCurrentSelectedValue(Locator locator) {
		WebElement e = findElement(locator);
		Select s = new Select(e);
		return s.getFirstSelectedOption().getText();

	}

	protected void selectValueByValue(Locator locator, String string) {
		WebElement e = findElement(locator);
		Select s = new Select(e);
		s.selectByValue(string);

	}

	protected void selectValueByIndex(Locator locator, String string) {
		WebElement e = findElement(locator);
		Select s = new Select(e);
		s.selectByIndex(Integer.valueOf(string));

	}

	public void selectAllValues(Locator locator) {
		WebElement e = findElement(locator);
		Select s = new Select(e);
		List<String> allValues = getAllLabels(s);
		for (String value : allValues) {
			s.selectByVisibleText(value);
		}

	}

	public List<String> getAllLabels(Select e) {

		List<String> optionLabels = new ArrayList<String>();
		for (WebElement option : e.getOptions()) {
			optionLabels.add(option.getText());
		}
		return optionLabels;
	}

	protected void unselectValueByVisibleText(Locator locator, String text) {
		WebElement e = findElement(locator);
		Select s = new Select(e);
		s.deselectByVisibleText(text);
	}

	protected void unselectByValue(Locator locator, String text) {
		WebElement e = findElement(locator);
		Select s = new Select(e);
		s.deselectByValue(text);
	}

	protected void unselectValueByIndex(Locator locator, String text) {
		WebElement e = findElement(locator);
		Select s = new Select(e);
		s.deselectByIndex(Integer.parseInt(text));
	}

	protected void unselectAll(Locator locator) {
		WebElement e = findElement(locator);
		Select s = new Select(e);
		s.deselectAll();
	}

	/**
	 * checkbox related functions
	 */
	public boolean isCheckboxOrRadioBtnSelected(Locator locator) {
		WebElement e = findElement(locator);
		return e.isSelected();
	}

	protected void selectCheckboxOrRadioBtn(Locator locator) {
		if (!(isCheckboxOrRadioBtnSelected(locator))) {
			clickElement(locator);
		}
	}
	protected void unSelectCheckboxOrRadioBtn(Locator locator) {
		if ((isCheckboxOrRadioBtnSelected(locator))) {
			clickElement(locator);
		}
	}
	/**
	 * Table Related Function
	 */
protected void addAttachment(Locator locator,String attachmentName) {
	String File = new File(this.getClass().getResource("/attachments/"+attachmentName).getFile()).getAbsolutePath();
		driver.findElement(locator.getWrappedLocator()).sendKeys(File);
	
}
}
