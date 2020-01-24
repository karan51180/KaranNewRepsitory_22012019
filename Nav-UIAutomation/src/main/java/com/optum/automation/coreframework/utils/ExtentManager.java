package com.optum.automation.coreframework.utils;
/**
 * @author Manoj Sharma
 *
 */
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	private static ExtentHtmlReporter htmlReporter;
	private static String filePath= System.getProperty("user.dir") + "/extentreport.html";
	
	public static ExtentReports GetExtent() {
		
		if(extent != null) {
			return extent;
		}else {
			
			extent =new ExtentReports();
			extent.attachReporter(getHtmlReporter(filePath));
			AutomationProfile.createProfile();
			extent.setSystemInfo("Host Name", AutomationProfile.getProfile().appUrl);
			extent.setAnalysisStrategy(AnalysisStrategy.TEST);
			
			
			return extent;
		}
	}
	
	public static ExtentHtmlReporter getHtmlReporter(String fileName) {
		
		htmlReporter =new ExtentHtmlReporter(filePath);
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);;
			htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setTheme(Theme.DARK);
	        htmlReporter.config().setDocumentTitle(fileName);
	        htmlReporter.config().setEncoding("utf-8");
	        htmlReporter.config().setReportName(fileName);
		
		htmlReporter.setAppendExisting(false);
	//	htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"");
		return htmlReporter;
	}
	
}
