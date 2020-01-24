package com.optum.automation.coreframework.listeners;
/**
 * @author Manoj Sharma
 *
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.optum.automation.coreframework.utils.PoiWriter;

public class ExcelListeners extends BaseListener {

	public static int counter = 0;
	public static int columnIndex = 0;
	public String[] sheetHeaders = { "Test Name", "Description", "Result",
			"Testdata" };
	public Map<String, String[]> sheetHeaderMap = new HashMap<String, String[]>();
	public String[] sheetName = { "TestResult" };
	public static String REPORTNAME = "ExcelReport.xlsx";
	public static Map<Integer, List<String>> resultMap = new LinkedHashMap<Integer, List<String>>();


	@Override
	public void onStart(ISuite arg0) {
		File file = new File(REPORTNAME);
		if (file.exists()) {
			file.delete();

		}
		PoiWriter.createExcelFile(REPORTNAME, sheetName);
		
		// TODO Auto-generated method stub
		resultMap.put(counter++, Arrays.asList(sheetHeaders));
		PoiWriter.appendDataToFile(REPORTNAME, sheetName[0], resultMap);
		PoiWriter.setRowStyle(0, REPORTNAME, sheetName[0], IndexedColors.YELLOW);
		resultMap.clear();
		
	}

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		if (arg0.isTestMethod()) {
			List<String> dataMap = new LinkedList<String>();
			String description = arg1.getMethod().getDescription();
			String CompleteDescription = (description == null) ? (arg1
					.getMethod().getMethodName() + ": No description provided")
					: description;
			if (CompleteDescription.contains(":")) {
				String[] arrDescription = CompleteDescription.split(":");
				dataMap.add(arrDescription[0]);
				dataMap.add(arrDescription[1]);
			} else {
				dataMap.add(arg1.getMethod().getMethodName());
				dataMap.add(CompleteDescription);
			}

			dataMap.add(resultStatusToString(arg1));

			if (arg1.getParameters().length == 0) {
				dataMap.add("No Parameter declared");
			} else {
				dataMap.add(arg1.getParameters()[0].toString());
			}
			resultMap.put(counter++, dataMap);

		}
	}

	private String resultStatusToString(ITestResult arg1) {
		String status = null;
		switch (arg1.getStatus()) {
		case 1:
			status = "SUCCESS";
			
			break;
		case 2:
			status = "FAIL";
			break;
		case 3:
			status = "SKIP";
			break;
		case 4:
			status = "SUCCESS_PERCENTAGE_FAILURE";

		}

		return status;
	}
 @Override
public void onAfterClass(ITestClass arg0) {
	// TODO Auto-generated method stub
	 PoiWriter.appendDataToFile(REPORTNAME, sheetName[0], resultMap);
	 resultMap.clear();
	 
}
 
 
 	@Override
	public void onFinish(ISuite arg0) {
		
	 List<List<Object>> rulesList = new ArrayList<>();

		rulesList.add(PoiWriter.SetExcelFormatRule("EQUAL", "\"SUCCESS\"", IndexedColors.GREEN));
		rulesList.add(PoiWriter.SetExcelFormatRule("EQUAL", "\"FAIL\"", IndexedColors.RED));
		rulesList.add(PoiWriter.SetExcelFormatRule("EQUAL", "\"SKIP\"", IndexedColors.YELLOW));
		
		try {
			PoiWriter.conditionalFormatCells(REPORTNAME, sheetName[0], "C1:C500", rulesList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	 
	}
 

}
