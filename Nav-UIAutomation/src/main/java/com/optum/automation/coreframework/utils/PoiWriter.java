 package com.optum.automation.coreframework.utils;
 /**
  * @author Manoj Sharma
  *
  */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ComparisonOperator;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFConditionalFormatting;
import org.apache.poi.xssf.usermodel.XSSFConditionalFormattingRule;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFFontFormatting;
import org.apache.poi.xssf.usermodel.XSSFPatternFormatting;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheetConditionalFormatting;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiWriter
    {
        static XSSFWorkbook workbook = new XSSFWorkbook();
        static XSSFSheet    worksheet;
        static XSSFRow      row;
        static XSSFCell     cell;
        
        public static void createExcelFile(String fileName, String[] sheetName)
            {
                try
                    {
                        FileOutputStream fileOut = new FileOutputStream(fileName);
                        for (String sheet : sheetName)
                            {
                                worksheet = workbook.createSheet(sheet);
                            }
                        workbook.write(fileOut);
                        fileOut.flush();
                        fileOut.close();
                    } catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
            }
        
        public static void setCellStyle(int rowindex, int columnindex, String fileName, String sheetName, IndexedColors foreGroundcolor)
            {
                try
                    {
                        FileInputStream inp = new FileInputStream(fileName);
                        XSSFCellStyle cellStyle = null;
                        // InputStream inp = new
                        // FileInputStream("workbook.xlsx");
                        workbook = new XSSFWorkbook(inp);
                        worksheet = workbook.getSheet(sheetName);
                        row = worksheet.getRow(rowindex);
                        cell = row.getCell(columnindex);
                        cellStyle = workbook.createCellStyle();
                        cellStyle.setFillForegroundColor(foreGroundcolor.getIndex());
                        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        Font font = workbook.createFont();
                        font.setFontHeightInPoints((short) 12);
                        font.setFontName("Calibri");
                        font.setBold(true);;
                        cellStyle.setFont(font);
                        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
                        cellStyle.setBorderTop(BorderStyle.MEDIUM);
                        cellStyle.setBorderRight(BorderStyle.MEDIUM);
                        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
                        cell.setCellStyle(cellStyle);
                        // Write the output to a file
                        FileOutputStream fileOut = new FileOutputStream(fileName);
                        workbook.write(fileOut);
                        fileOut.close();
                    } catch (IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            }
        
        public static void mergeCell(int firstRow, int lastRow, int firstColumn, int lastColumn, String fileName, String sheetName)
            {
                try
                    {
                        FileInputStream inp = new FileInputStream(fileName);
                        workbook = new XSSFWorkbook(inp);
                        worksheet = workbook.getSheet(sheetName);
                        worksheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstColumn, lastColumn));
                        row = worksheet.getRow(firstRow);
                        XSSFCell startCell = row.getCell(firstColumn);
                        CellUtil.setAlignment(startCell, HorizontalAlignment.CENTER);
                        FileOutputStream fileOut = new FileOutputStream(fileName);
                        workbook.write(fileOut);
                        fileOut.close();
                        fileOut.flush();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
            }
        
        public static void appendDataToFile(String fileName, String sheetName, int rowIndex, int cellIndex, String value)
            {
                try
                    {
                        InputStream inp = new FileInputStream(fileName);
                        workbook = new XSSFWorkbook(inp);
                        worksheet = workbook.getSheet(sheetName);
                        if (!(worksheet.getRow(rowIndex) == null))
                            {
                                row = worksheet.getRow(rowIndex);
                            } else
                            {
                                row = worksheet.createRow(rowIndex);
                            }
                        cell = row.createCell(cellIndex);
                        cell.setCellValue(value);
                        // Write the output to a file
                        FileOutputStream fileOut = new FileOutputStream(fileName);
                        workbook.write(fileOut);
                        fileOut.close();
                    } catch (IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            }
        
        public static void appendDataToFile(String fileName, String sheetName, int rowIndex, int cellIndex, String value, boolean isHeaderRow)
            {
                try
                    {
                        InputStream inp = new FileInputStream(fileName);
                        XSSFCellStyle cellStyle = null;
                        // InputStream inp = new
                        // FileInputStream("workbook.xlsx");
                        workbook = new XSSFWorkbook(inp);
                        worksheet = workbook.getSheet(sheetName);
                        if (!(worksheet.getRow(rowIndex) == null))
                            {
                                row = worksheet.getRow(rowIndex);
                            } else
                            {
                                row = worksheet.createRow(rowIndex);
                            }
                        cell = row.createCell(cellIndex);
                        if (isHeaderRow)
                            {
                                cellStyle = workbook.createCellStyle();
                                cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                Font font = workbook.createFont();
                                font.setFontHeightInPoints((short) 12);
                                font.setFontName("Calibri");
                                font.setBold(true);
                                cellStyle.setFont(font);
                            }
                        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
                        cellStyle.setBorderTop(BorderStyle.MEDIUM);
                        cellStyle.setBorderRight(BorderStyle.MEDIUM);
                        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
                        cell.setCellValue(value);
                        cell.setCellStyle(cellStyle);
                        // Write the output to a file
                        FileOutputStream fileOut = new FileOutputStream(fileName);
                        workbook.write(fileOut);
                        fileOut.close();
                    } catch (IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            }
        
        public static int getLastColumnOfSheet(String fileName, String sheetName, int rowIndex)
            {
                int lastCellindex = 0;
                try
                    {
                        InputStream inp = new FileInputStream(fileName);
                        // InputStream inp = new
                        // FileInputStream("workbook.xlsx");
                        workbook = new XSSFWorkbook(inp);
                        worksheet = workbook.getSheet(sheetName);
                        if (!(worksheet.getRow(rowIndex) == null))
                            {
                                row = worksheet.getRow(rowIndex);
                                lastCellindex = row.getLastCellNum();
                            }
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                return lastCellindex;
            }
        
        public static boolean isRowExists(String fileName, String sheetName, int rowIndex)
            {
                try
                    {
                        InputStream inp = new FileInputStream(fileName);
                        // InputStream inp = new
                        // FileInputStream("workbook.xlsx");
                        workbook = new XSSFWorkbook(inp);
                        worksheet = workbook.getSheet(sheetName);
                        if (!(worksheet.getRow(rowIndex) == null))
                            {
                                return true;
                            }
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                return false;
            }
        
        public static int findRow(String fileName, String sheetName, String value, int cellindex)
            {
                int rowNum = 0;
                try
                    {
                        InputStream inp = new FileInputStream(fileName);
                        workbook = new XSSFWorkbook(inp);
                        worksheet = workbook.getSheet(sheetName);
                        int rowcount = worksheet.getLastRowNum();
                        for (int i = 2; i <= rowcount; i++)
                            {
                                // System.out.println(worksheet.getRow(i).getCell(cellindex).getRichStringCellValue().getString());
                                if (!(worksheet.getRow(i).getCell(cellindex)==null)){
                                        if (worksheet.getRow(i).getCell(cellindex).getRichStringCellValue().getString().contains(value))
                                            {
                                                return i;
                                            }
                                }
                                else 
                                    break;
                                
                            }
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                return rowNum;
            }

        public static String getCellValue(String fileName, String sheetName, int row, int column)
            {
                try
                    {
                        InputStream inp = new FileInputStream(fileName);
                        workbook = new XSSFWorkbook(inp);
                        worksheet = workbook.getSheet(sheetName);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                return worksheet.getRow(row).getCell(column).getStringCellValue();
            }
        public static void appendDataToFile(String fileName, String sheetName, Map<Integer, List<String>> datamap) {
    		try {
    			InputStream inp = new FileInputStream(fileName);
    			XSSFCellStyle cellStyle = null;
    			// InputStream inp = new
    			// FileInputStream("workbook.xlsx");
    			workbook = new XSSFWorkbook(inp);
    			worksheet = workbook.getSheet(sheetName);
    			for (Integer i : datamap.keySet()) {

    				if (!(worksheet.getRow(i.intValue()) == null)) {
    					row = worksheet.getRow(i.intValue());
    				} else {
    					row = worksheet.createRow(i.intValue());
    				}
    				int cellIndex = 0;
    				for (String s : datamap.get(i)) {
    					cell = row.createCell(cellIndex++);
    					cell.setCellValue(s);
    				}
    			}
    			// Write the output to a file
    			FileOutputStream fileOut = new FileOutputStream(fileName);
    			workbook.write(fileOut);
    			fileOut.close();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}  
        public static void setRowStyle(int rowindex, String fileName, String sheetName, IndexedColors BackGroundcolor)
        {
            try
                {
                  
                   
               InputStream inp = new   FileInputStream(fileName);
                    workbook = new XSSFWorkbook(inp);
                    XSSFCellStyle cellStyle = null;
                    worksheet = workbook.getSheet(sheetName);
                    row = worksheet.getRow(rowindex);
                    //cell = row.getCell(columnindex);
                    int lastCell=row.getLastCellNum();
                    for (int i=0;i<lastCell;i++) {
                    	cell = row.getCell(i);
                        cellStyle = workbook.createCellStyle();
                        cellStyle.setFillForegroundColor(BackGroundcolor.getIndex());
                        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        Font font = workbook.createFont();
                        font.setFontHeightInPoints((short) 12);
                        font.setFontName("Calibri");
                        font.setBold(true);
                        cellStyle.setFont(font);
                        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
                        cellStyle.setBorderTop(BorderStyle.MEDIUM);
                        cellStyle.setBorderRight(BorderStyle.MEDIUM);
                        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
                        cell.setCellStyle(cellStyle);
                    }
                  
                    // Write the output to a file
                    FileOutputStream fileOut = new FileOutputStream(fileName);
                    workbook.write(fileOut);
                    fileOut.close();
                } catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

	/*public static void main(String[] args) {
		String filename = "C:\\Users\\punit.vashisth.3PILLAR\\Perforce\\Cadents\\bas\\projects\\khaleesi.1\\baautomation\\SalesSuiteAutomation\\ExcelReport.xlsx";
		String sheetName = "TestResult";

		List<List<Object>> rulesList = new ArrayList<>();

		rulesList.add(PoiWriter.SetExcelFormatRule("EQUAL", "\"SUCCESS\"", IndexedColors.GREEN));
		rulesList.add(PoiWriter.SetExcelFormatRule("EQUAL", "\"FAIL\"", IndexedColors.RED));
		rulesList.add(PoiWriter.SetExcelFormatRule("EQUAL", "\"SKIP\"", IndexedColors.YELLOW));
		
		try {
			PoiWriter.conditionalFormatCells(filename, sheetName, "C1:C100", rulesList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
	public static void conditionalFormatCells(String filename, String sheetName, String cellRange, List<List<Object>> rulesList) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream inp = new FileInputStream(filename);
		workbook = new XSSFWorkbook(inp);
		worksheet = workbook.getSheet(sheetName);
		XSSFSheetConditionalFormatting cond = worksheet.getSheetConditionalFormatting();

		for (List<Object> rule : rulesList) {

			byte c = (byte) rule.get(0);
			String cellValue = (String) rule.get(1);
			short colorIndex = (short) rule.get(2);
			
			XSSFConditionalFormattingRule rulestobeApplied = cond.createConditionalFormattingRule(c, cellValue);
			XSSFPatternFormatting ruleFormatting = rulestobeApplied.createPatternFormatting();
			ruleFormatting.setFillBackgroundColor(colorIndex);
			//ruleFormatting.setFillPattern(XSSFPatternFormatting.SOLID_FOREGROUND);
			CellRangeAddress[] rule_range = { CellRangeAddress.valueOf(cellRange) };
			cond.addConditionalFormatting(rule_range, rulestobeApplied);
		}
		FileOutputStream out = new FileOutputStream(filename);
		workbook.write(out);
		out.close();
		
	}

	public static List<Object> SetExcelFormatRule(String comaparisonOperator, String stringToComapre, IndexedColors colortoSet) {
		List<Object> rule = new ArrayList<>();
		// To do Need to add more case statements.
		switch (comaparisonOperator) {
		case "EQUAL":
			rule.add(ComparisonOperator.EQUAL);
			break;
		case "LT":
			rule.add(ComparisonOperator.LT);
			break;
		case "GT":
			rule.add(ComparisonOperator.GT);
			break;
		case "BETWEEN":
			rule.add(ComparisonOperator.BETWEEN);
			break;
		case "GE":
			rule.add(ComparisonOperator.GE);
			break;
		case "LE":
			rule.add(ComparisonOperator.LE);
			break;
		case "NOT_BETWEEN":
			rule.add(ComparisonOperator.NOT_BETWEEN);
			break;
		case "NOT_EQUAL":
			rule.add(ComparisonOperator.NOT_EQUAL);
			break;
		case "NO_COMPARISON":
			rule.add(ComparisonOperator.NO_COMPARISON);
			break;	

		}

		rule.add(stringToComapre);
		rule.add(colortoSet.getIndex());
		return rule;
	}
		 
	
    }
    
    