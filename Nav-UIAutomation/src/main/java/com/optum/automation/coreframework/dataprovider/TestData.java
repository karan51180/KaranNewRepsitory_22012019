package com.optum.automation.coreframework.dataprovider;
/**
 * @author Manoj Sharma
 *
 */
import java.util.Iterator;

public class TestData {

	
	private static TestDataProvider data=null;
	public static <T> Iterator<Object[]> getData(Class<T> instance, String notation) {
		if (data==null) {
		 data=new TestDataProvider();
		}
			
		return data.getObjects(instance, notation);
	}
}
