package com.optum.automation.coreframework.browser;
/**
 * @author Manoj Sharma
 *
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.log4j.Logger;

/**
 * Singleton factory to produce page objects. It must be annotated with the
 * {@link Page} annotation at the class level.
 *
 */
public class PageFactory {

	private static Logger logger = Logger.getLogger(PageFactory.class);

	private PageFactory() {
	}

	/**
	 * Dynamically creates instances of Page Class(which have @Page annotation)
	 * 
	 * @param pageClass
	 * @return T
	 */
	public static <T> T newInstance(Class<T> pageClass) {

		T instance = null;
		try {
			instance = pageClass.newInstance();

		} catch (Exception e) {
			throw new Error(e);
		}

		return instance;
	}

	/**
	 * Marks a type (class for our purpose) as a Page.
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface Page {
		// marker purpose
	}

}
