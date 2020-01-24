package com.optum.automation.coreframework.browser;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



/**
 * 
 * @author NavAutomationTeam
 * 
 */
public class SeWebElement extends SeWebDriver {
/*	protected WebElement element;
	private ByMechanism findby;
	private String loc;
	protected SeWebDriver dr = null;
	
	public SeWebElement(ByMechanism by, String using)
	{
		this.dr = driver;
		this.findby = by;
		this.loc = using;
	}

	public SeWebElement(WebElement element) {
		this.element = element;
	}

	public String getLocator()
	{
		return this.loc;
	}
	
	public ByMechanism getFindBy()
	{
		return this.findby;
	}
	
	public boolean isExist()
	{
		boolean flag = false;
		try
		{
			this.dr.waitForPageToLoad();	
			 this.element =  this.dr.findElement(this.findby,  this.loc);
			 flag = this.element.isDisplayed();
		}
		catch(Exception e)
		{
			 return flag;
		}
		return flag;
		 
	}
	
	public boolean isSelected() {
		 if(this.isExist())		 
		 {
			 	return this.element.isSelected();
		 }
		 
		 return false;
	}

	public boolean isEnabled() {
		if(this.isExist())
		{
			return this.element.isEnabled();
		}
		return false;
	}

	public boolean isDisplayed() {
		if(this.isExist())
		{
			return this.element.isDisplayed();
		}
		return false;
	}

	public String getText() {
		if(this.isExist())
		{
			return this.element.getText();
		}
		return "";
	}

	public String getTagName() {
		if(this.isExist())
		{
			return this.element.getTagName();
		}
		
		return "";
	}

	public Dimension getSize() {
		if(this.isExist())
		{
			return this.element.getSize();
		}
		return  new Dimension(0,0);
	}

	public Point getLocation() {
		if(this.isExist())
		{
			return this.element.getLocation();
		}
		return new Point(0,0);
	}

	public String getCssValue(String arg0) {
		if(this.isExist())
		{
			return this.element.getCssValue(arg0);
		}
		return "";
	}

	public String getAttribute(String attribute) {
		if(this.isExist())
			
		{
			return this.element.getAttribute(attribute);
		}
		return "";
	}

	public void submit() {
		 
		if(this.isExist())
		{
			this.element.submit();
		}
		else
		{
			System.out.println("Element not submit : " + this.loc);
		}
	}

	public void sendKeys(CharSequence... key) {
		if(this.isExist())
		{
			this.element.sendKeys(key);
		}
		else
		{
			System.out.println("Element data not entered :  " + this.loc);
		}
		
	}

	 
	
	
	public void setValue(CharSequence... key) {
		
		if(this.isExist())
		{
			this.element.clear();
			this.element.sendKeys(key);	
		}
		else
		{
			System.out.println("Element data not entered :  " + this.loc);
		}
		
		 
			
	}

	public void click() {
		if(this.isExist())
		{
			this.element.click();
		}
		else
		{
			System.out.println("Element does not clicked " + this.loc);
		}
	}

	public void clear() {
		if(this.isExist())
		{
			this.element.clear();
		}
		else
		{
			System.out.println("Element does not cleared " + this.loc);;
		}
	}

	public boolean isAvailableForAction() {
		 
		if (isDisplayed() && isEnabled())
			return true;
		 
			return false;
	}

	public boolean verifyAttributeEquals(String attribute, String value) {
		if(this.isExist())
		{
			if (this.element.getAttribute(attribute).equals(value))
				return true;
			 
		}
		return false;
	}

	@SuppressWarnings("unused")
	private WebElement findElement(ByMechanism findByType, String locator) {
		WebElement retElement = this.element.findElement(FindBy
				.get(findByType, locator));
		return retElement;
	}

	public boolean verifyAttributeContains(String attribute, String value) {
		if(this.isExist())
			
		{
			if (this.element.getAttribute(attribute).contains(value))
		
			return true;
		}
		 
		return false;
		
	}

	public boolean verifyTextEquals(String value) {
		if(this.isExist())
			
		{
			if (this.element.getText().equals(value))
				return true;
			 
		}
		return false;
	}

	public boolean verifyTextContains(String value) {
		if(this.isExist())
		{
			if (this.element.getText().contains(value))
				return true;
			 
		}
		return false;
	}

	public WebElement getWebElement() {
		this.isExist();
		return this.element;
	}
	
	public void waitTillLoadOrPresent()
	{
		this.isExist();
		dr.waitForElementPresent(this.findby, this.loc);
		
	}
	
	public int getTableRowsCount()
	{
		int count=0;
		if(this.isExist())
		{
			List<WebElement> retElement =   this.element.findElements(FindBy
		
				.get(ByMechanism.TAGNAME, "tr"));
		
			count= retElement.size();
		}
		return count;
	}
	 
	public int getTableColumnsCount()
	{
		int count=0;
		if(this.isExist())
		{
			List<WebElement> retElement =   this.element.findElements(FindBy
		 
				.get(ByMechanism.TAGNAME, "td"));
		count= retElement.size();
		}
		return count;
	}
	public String getTableCellData(int row,int col)
	{
		String text="";
		if(this.isExist())
		{
			WebElement  retElement =   this.element.findElement(FindBy
				.get(ByMechanism.CSS, "tr:nth-child("+row +")>td:nth-child(" + col + ")" ));
			text= retElement.getText();
		}
		return text;
	}
	public String getTableCellData(int row,int col,String labelOrspan)
	{
		String text="";
		if(this.isExist())
		{
			WebElement  retElement =   this.element.findElement(FindBy
					.get(ByMechanism.CSS, "tr:nth-child("+row + ")>td:nth-child(" + col + ")>" + labelOrspan));  //"tr[" + row + "]/td[" + col + "]/" + labelOrspan));
			text= retElement.getText();
		}
		return text;
	}
	
	public List<String> getTableRowsCellData(int col,String labelOrspan)
	{
		 
		 List<String> lst = new ArrayList<String>();
		 List<WebElement> retElementTd = new ArrayList<WebElement>();
		if(this.isExist())
		{
			 
			List<WebElement>   retElement =   this.element.findElements(FindBy
				.get(ByMechanism.TAGNAME, "tr"));
			
			for(WebElement e: retElement)
			{ 
				if(labelOrspan!="")
				{
					retElementTd =e.findElements(FindBy.get(ByMechanism.TAGNAME,labelOrspan));
					
				}
				else
				{
					retElementTd =e.findElements(FindBy.get(ByMechanism.TAGNAME,"td"));
					
				}
			 
				 lst.add(retElementTd.get(col-1).getText());
			}
		}
		return lst;
	}
	
	public int listItemsCount()
	{
		int count=0;
		if(this.isExist())
		{
			List<WebElement> retElement =   this.element.findElements(FindBy
		
				.get(ByMechanism.TAGNAME, "option"));
		     count= retElement.size();
		}
		
		return count;
		
	}
	public void selectTableRow(int row)
	{
		if(this.isExist())
		{
		String tr = "/tr[" + row +"]";
		 this.element.findElement(FindBy
				.get(ByMechanism.XPATH, tr)).click();
		
		}
		else
		{
			System.out.println("Row is not selected for the table : " + this.loc);
		}
	}
	
	@SuppressWarnings("null")
	public List<String> getElementsText()
	{
		List<String> lst = new ArrayList<String>();
		if(this.isExist())
		{
			
		 
			List<WebElement> retElement =   this.element.findElements(FindBy
					.get(ByMechanism.TAGNAME, "td"));
			 
			 for( WebElement e : retElement)
				 lst.add(e.getText());
		}
		 return lst;
		
	}
	
	@SuppressWarnings("null")
	public List<String> getElementsText(int col)
	{
		List<String> lst = new ArrayList<String>();
		if(this.isExist())
		{
		 
			List<WebElement> retElement =   this.element.findElements(FindBy
					.get(ByMechanism.TAGNAME, "td[" + col + "]"));
			 
			 for( WebElement e : retElement)
				 lst.add(e.getText());
		} 
		 return lst;
		
	}
	 
	public List<String> getElementsText(int col,String labelOrSpan)
	{
		List<String> lst = new ArrayList<String>();
		if(this.isExist())
		{
		 
			List<WebElement> retElement =   this.element.findElements(FindBy
					.get(ByMechanism.TAGNAME, "td[" + col + "]/" + labelOrSpan));
			 
			 for( WebElement e : retElement)
				 lst.add(e.getText());
		} 
		 return lst;
		
	}
	 
	public void selectByIndex(int index)
	{
		if(this.isExist())
			
			this.dr.selectByIndex(  this.element, index);
		else
			System.out.println("element not selected by index: " + this.loc);
	}
	public void selectByValue(String valueToSelect)
	{
		if(this.isExist())
			this.dr.selectByValue(this.element, valueToSelect);
			else
				System.out.println("element not selected by value : " + this.loc);
	}
	public void selectByVisibleText(String visibleText)
	{
			if(this.isExist())
				this.dr.selectByVisibleText(this.element, visibleText);
			else
				System.out.println("element not selected by visible text: " + this.loc);
	}
	
	public void selectByVisibleText(String visibleText,String Option)
	{
			if(this.isExist())
			{
				 
				List<WebElement> eles =  this.element.findElements(FindBy
						.get(ByMechanism.TAGNAME,Option)  );
				for(WebElement e: eles)
				{
					if(e.getText().equalsIgnoreCase(visibleText))
					{
						e.click();
						break;
					}
				}
				
			}
				
			else
				System.out.println("element not selected by visible text: " + this.loc);
	}
	
	public void deSelectByIndex(int index)
	{
		if(this.isExist())
			this.dr.deselectByIndex(this.element, index);
		else
			System.out.println("element not deselected by index: " + this.loc);
	}
	public void deSelectByValue(String valueToSelect)
	{
		if(this.isExist())
			this.dr.deselectByValue(this.element, valueToSelect);
		else
			System.out.println("element not deselected by value: " + this.loc);
	}
	public void deselectByVisibleText(String visibleText)
	{
		if(this.isExist())
			this.dr.deselectByVisibleText(this.element, visibleText);
		else
			System.out.println("element not deselected by visible text : " + this.loc);
	}
	
	public void deselectAll()
	{
		if(this.isExist())
			this.dr.deselectAll( this.element );
		else
			System.out.println("elements not selected : " + this.loc);
	}
	public String getSelectedElement()
	{
		String text="";
		 if(this.isExist())
			 {
			 WebElement ele =  this.element;
			 
			 List<WebElement> eles = new Select( ele).getAllSelectedOptions();
			 text = eles.get(0).getText();
			 }
		 return text;
		
	}
	
	@SuppressWarnings("null")
	public List<String> getSelectedElements()
	{
		List<String> lst = new ArrayList<String>();
		this.isExist();
		WebElement ele =  this.element;
		 List<WebElement> eles = new Select( ele).getAllSelectedOptions();
		 
		 for( WebElement e : eles)
			 lst.add(e.getText());
			 
		 return lst;
		
	}
	
	
	@SuppressWarnings("null")
	public List<String> getAllListElements()
	{
		List<String> lst = new ArrayList<String>();
		this.isExist();
		WebElement ele =  this.element;
		 List<WebElement> eles = new Select(ele).getOptions();
		 
		 for( WebElement e : eles)
			 lst.add(e.getText());
			 
		 return lst;
		
	}
	
	@SuppressWarnings("null")
	public List<String> getAllListElements(String Options)
	{
		List<String> lst = new ArrayList<String>();
		this.isExist();
		List<WebElement> eles =  this.element.findElements(FindBy.get(FindBy.ByMechanism.TAGNAME,Options));	 
		 
		 for( WebElement e : eles)
			 lst.add(e.getText());
			 
		 return lst;
		
	}
	
	public void rightClick()
	{
		if(this.isExist())
		{
		WebElement ele =  this.element;
			this.dr.contextClick(ele);
		}
		else
			System.out.println("right click not happened: " + this.loc);
	}
	
	 
	@SuppressWarnings("null")
	public List<String> getColumnNames()
	{
		List<String> lst = new ArrayList<String>();
		if(this.isExist())
		{
		
			try
			{
		 
			List<WebElement> retElement =   this.element.findElements(FindBy
					.get(ByMechanism.TAGNAME, "th"));
			 
			 for( WebElement e : retElement)
			 {
				 String eleVal = e.getText();
				 lst.add(eleVal);
			 }
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		 return lst;
	}
	 
	public int getTableRowWithCellText(String Text,int col)
	{
		List<String> lst = new ArrayList<String>();
		int rows = this.getTableRowsCount();
	 
		
		if(this.isExist())
		{
			for(int row=1;row<rows;row++)
			{
				lst.add(this.getTableCellData(row, col));
			}
		}
		return lst.indexOf(Text) ;
	}
	public int getTableRowWithCellText(String Text,int col,String Option)
	{
		String str="";
		int rows = this.getTableRowsCount();
		int count = 0;
		
		if(this.isExist())
		{
			for(int row=1;row<rows;row++)
			{
				try {
					count++;
					str = this.getTableCellData(row, col,Option);
					if(str.trim().equals(Text.trim()))
						return count;
				} catch(Exception e)
				{
					e.printStackTrace();
				}
				 
			}
		}
		return 0 ;
	}
	 */
	 
}
