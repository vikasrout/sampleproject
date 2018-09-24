package com.qa.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel {
	public static XSSFWorkbook wb;
	public static XSSFSheet sheetnumber;
	public static String pathname="C:/Users/vikas.rout/workspace/PractiseTestNg/src/main/java/com/qa/utility/SampleTestData.xlsx";

	public static Object[][] getdatafromexcel(String sheetname){
		FileInputStream file= null;
		try {
			FileInputStream fis= new FileInputStream(pathname);
			wb= new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheetnumber= wb.getSheet(sheetname);
		System.out.println(sheetnumber);
		Object[][] data= new Object[sheetnumber.getLastRowNum()][sheetnumber.getRow(0).getLastCellNum()];
		for(int i=0;i<=sheetnumber.getLastRowNum();i++){
			for(int k=0;k<=sheetnumber.getRow(0).getLastCellNum();k++){
				data[i][k]= sheetnumber.getRow(i+1).getCell(k).toString();
			}
		}

		return data;

	}

	
	public static void main(String[] args) {
		ReadDataFromExcel d= new ReadDataFromExcel();
		System.out.println(d.getdatafromexcel("Registration"));
		
	}
}
