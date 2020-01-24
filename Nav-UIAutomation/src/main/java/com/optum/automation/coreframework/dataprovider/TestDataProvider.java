package com.optum.automation.coreframework.dataprovider;
/**
 * @author Manoj Sharma
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.format.CellFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.optum.automation.coreframework.utils.AutomationProfile;

public class TestDataProvider  {

	private static Logger logger = Logger.getLogger(TestDataProvider.class);
	private String testDataFile = null;
	private Map<String, Map<String, List<Map<String, String>>>> contents = 
		new HashMap<String, Map<String, List<Map<String, String>>>>();
	
	private FormulaEvaluator formulaEv = null;
	private String worksheetName = null;
	public Map<String, Map<String, List<Map<String, String>>>> loadWorkBookContents() throws Exception {
		
		try {
			
			InputStream dataIn = new FileInputStream(new File(testDataFilePath()));
			Workbook fixtureWorkbook = WorkbookFactory.create(dataIn);
			formulaEv = fixtureWorkbook.getCreationHelper().createFormulaEvaluator();
			
			for (int i = 0; i < fixtureWorkbook.getNumberOfSheets(); i++) {
				Sheet sheet = fixtureWorkbook.getSheetAt(i);
				String sheetName = sheet.getSheetName();
				logger.info("Processing Sheet: " + sheetName);
				
				
				int columnCount = 0;
				List<String> attributes = new ArrayList<String>();
				for (Iterator<Cell> ite = sheet.getRow(0).cellIterator(); 
															ite.hasNext();) {
					Cell cell = ite.next();
					if ( CellType.BLANK!= cell.getCellType()) {
						attributes.add(cell.getStringCellValue());
						++columnCount;
					}
				}
				attributes.remove(0);
				
				// fetch the unique notations used in the sheet
				// starting from second row
				List<String> notations = new ArrayList<String>();
				boolean skipFirstRow = true;
				for (Row row : sheet) {
					if (skipFirstRow) { skipFirstRow = false; continue; }
					Cell cell = row.getCell(0);
					if (cell != null) {
						String notation = cell.getStringCellValue();
						if (notation != null && !notation.isEmpty()) {
							if (! notations.contains(notation)) {
								notations.add(notation);
							}
						}
					}
				}

				// fetch the data into contents
				Map<String, List<Map<String, String>>> notationFixtures =
					new HashMap<String, List<Map<String,String>>>();
				for (String notation : notations) {
					List<Map<String, String>> fixtures = null;
					if (notationFixtures.containsKey(notation)) {
						fixtures = notationFixtures.get(notation);
					} else {
						fixtures = new ArrayList<Map<String, String>>();
						notationFixtures.put(notation, fixtures);
					}
					skipFirstRow = true;
					for (Row row : sheet) {
						if (skipFirstRow) { skipFirstRow = false; continue; }
						List<Cell> data = new ArrayList<Cell>();
						// warning: do not engineer with iterator: can't be 
						// used since it skips empty cells
						for (int j = 0; j < columnCount; j++) {
							Cell cell = row.getCell(j);
							data.add(cell);
						}
						if (data.size() > 0) {
							Map<String, String> fixture = 
								new HashMap<String, String>();
							Cell notationCell = data.get(0);
							if (notationCell != null) {
								String cellNotation = notationCell
													.getStringCellValue();
								if (notation.equals(cellNotation)) {
									for (int j = 1; j < data.size(); j++) {
										String fKey = attributes.get(j - 1);
										String fValue = getCellStringValue(
												data.get(j));
										fixture.put(fKey, fValue);
									}
									fixtures.add(fixture);
								}	
							}
						}
					}
				}
				
				contents.put(sheetName, notationFixtures);
			}
			
			dataIn.close();
			
		} catch (Exception e) {
			logger.fatal(e.getMessage());
			throw new Exception(e);
		}
		
		return contents;
	}

	private String getCellStringValue(Cell cell) throws Exception {
		
		String fValue = null;
		CellValue cValue = formulaEv.evaluate(cell);
		
		if (cValue != null) {
			switch (cValue.getCellType()) {
			
			case ERROR:
				throw new Exception(String.format(
						"Error cell found - Sheet: %s, Row:%d, Column:%d, Code: %d", 
						cell.getSheet().getSheetName(),
						cell.getRowIndex() + 1, cell.getColumnIndex() + 1,
						cell.getErrorCellValue()));
				
			case STRING :
				fValue = cValue.getStringValue();
				break;
				
			case NUMERIC:
				String dataFmt = cell.getCellStyle().getDataFormatString();
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					double dv = cValue.getNumberValue();
					Date date = HSSFDateUtil.getJavaDate(dv);
					fValue = new CellDateFormatter(dataFmt).format(date);
				} else {
					CellFormat cf = CellFormat.getInstance(dataFmt);
					fValue = cf.apply(cell).text;
				}
				break;
			
			case BOOLEAN:
				fValue = new Boolean(cValue.getBooleanValue()).toString();
				break;
			
			default:
				fValue = cValue.getStringValue();
				break;
			}	
		}
		
		if (fValue == null) {
			fValue = "";
		} 
		
		return fValue;
	}
	public  <T> Iterator<Object[]> getObjects(Class<T> instance, String notation){
		List<Object[]>l=new ArrayList<Object[]>();
		
		if(contents.isEmpty()) {
			try {
				loadWorkBookContents();
			} catch (Exception e) {
				logger.warn(e);
				}
		}
		Object klass = null ;
		worksheetName =  instance.getSimpleName();
		if(contents.containsKey(worksheetName))
		{
			 Map<String, List<Map<String, String>>> pageData=contents.get(worksheetName);
			 if(pageData.containsKey(notation))
			{
				List<Map<String, String>> data=pageData.get(notation);
				for(Map<String, String> m:data){
					try {
						 klass = instance.newInstance();
					} catch (InstantiationException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				Field[] f=instance.getFields();
					for(Field f1:f){
						if(m.containsKey(f1.getName())){
							try {
								f1.set(klass, m.get(f1.getName()));
							} catch (IllegalArgumentException
									| IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
					
					
					Object[] objectAry={ klass };
					l.add(objectAry);
				}
			}
			else{
				logger.error(String.format("\"%s\" Notation not found in \"'%s'\" Sheet. Please check Spelling(case Senstive) or notation. ",notation,instance.getSimpleName()));
				
			}
		}
		
		return l.iterator();
		
	}
	private String getTestDataFile() {

		if (testDataFile == null) {
			testDataFile = AutomationProfile.getProfile().testDataFile;
			if (testDataFile == null) {
				throw new Error("you have not specified the TestData File properly");
			}
			if ( !(testDataFile.endsWith(".xls")||testDataFile.endsWith(".xlsx")) ) {
				testDataFile = StringUtils.join(new String[] {
						testDataFile, "xlsx"	
				}, "."); 
			}
		}
		return testDataFile;
	}
private static String getConfiguredtestDataDir() {
		
		String configuredDir = AutomationProfile.getProfile().testDataProviderDir;

		if (configuredDir == null) {
			configuredDir = "Testdata";
		}
		
		return configuredDir;
	}

private String testDataFilePath() {
	
	String dataSourceFile = getTestDataFile();
	if ( ! (new File(dataSourceFile)).isAbsolute() ) {
		
		String configuredtestDataDir = getConfiguredtestDataDir();
		String pathComponents[] = {
				configuredtestDataDir , dataSourceFile
		};
		if ( (new File(configuredtestDataDir)).isAbsolute() ) {
			dataSourceFile = StringUtils.join(pathComponents, File.separator);
		} else {
			dataSourceFile = this.getClass().getResource("/" +
					StringUtils.join(pathComponents, "/")).getFile();
		}
	}
	logger.info("Loading testData from: " + dataSourceFile);
	
	return dataSourceFile;
}


/*Template function.
 *  if we have any other folder in Testdata Folder as well in rder to get that use below function
 * 
 * public static String getResource(String resource) {

	String fullPath = null;
	String configuredtestDataDir = getConfiguredtestDataDir();
	String[] pathComponents = new String[] {
			configuredFixtureDir, resource
	};
	if ( new File(configuredtestDataDir).isAbsolute() ) {
		fullPath = StringUtils.join(pathComponents, File.separator);
	} else {
		URL url = this.class.getResource("/" +
				StringUtils.join(pathComponents, "/"));
		if (url != null) {
			fullPath = url.getFile();	
		}
	}*/
}
