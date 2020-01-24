package com.optum.automation.coreframework.listeners;
/**
 * @author Manoj Sharma
 *
 */
import java.util.Arrays;
import org.testng.IInvokedMethod;
import org.testng.ISuite;
import org.testng.ITestClass;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.optum.automation.coreframework.utils.ExtentManager;


public class ExtentListeners extends BaseListener {
	public static ThreadLocal<ExtentTest> testCaseLogger = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	public static ExtentReports extent;

	public void onTestFailure(ITestResult arg0) {

		
		String exceptionMessage = Arrays.toString(arg0.getThrowable()
				.getStackTrace());
		testCaseLogger.get().fail(
				"<details>" + "<summary>" + "<b>" + "<font color=" + "red>"
						+ "Exception Occured : Click to see" + "</font>"
						+ "</b>" + "</summary>"
						+ exceptionMessage.replaceAll(",", "<br>")
						+ "</details>");

		String failureLogg = "This test case got failed";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testCaseLogger.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult arg0) {

		String methodName = arg0.getMethod().getMethodName();
		String description = arg0.getMethod().getDescription();
		// String
		// CompleteDescription=(description==null)?(methodName+": No description provided"):description;

		String logText = "<b>" + "Test case:- " + methodName + "Skipped"
				+ "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testCaseLogger.get().skip(m);

	}

	public void onTestStart(ITestResult arg0) {

		String methodName = arg0.getMethod().getMethodName();
		String description = arg0.getMethod().getDescription();
		String CompleteDescription = (description == null) ? ("DESCRIPTION - "
				+ methodName + ": No description provided") : "DESCRIPTION - "
				+ description;
		ExtentTest child = parentTest.get().createNode(methodName,	CompleteDescription);

		testCaseLogger.set(child);
		testCaseLogger.get().info(
				"<b>" + "Starting execution of Test Case:- " + methodName
						+ "</b>");

	}

	public void onTestSuccess(ITestResult arg0) {

		String methodName = arg0.getMethod().getMethodName();
		String description = arg0.getMethod().getDescription();
		String CompleteDescription = (description == null) ? (methodName + ": No description provided")
				: description;
		String logText = "<b>" + "Test case:- " + methodName + "Passed"
				+ "</b>";
		// testCaseLogger.get().pass("<b>"+"Test case:- "+methodName+
		// "Passed"+"</b>");
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testCaseLogger.get().pass(m);

	}

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		if (arg0.isTestMethod()) {
			extent.flush();
		}
	}

	@Override
	public void onBeforeClass(ITestClass arg0) {

		ExtentTest parent = extent.createTest(arg0.getRealClass().getSimpleName());
		parentTest.set(parent);
	}

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		extent = ExtentManager.GetExtent();
	}

}
