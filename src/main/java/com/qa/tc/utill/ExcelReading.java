package com.qa.tc.utill;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

import static com.qa.tc.constant.FilePaths.API_DETAILS_SHEET;
import static com.qa.tc.constant.FilePaths.API_TEST_DATA_DIR;

public class ExcelReading {

    public static String[][] readExcelData(String fileName, String sheetName) {
        File file = new File(API_TEST_DATA_DIR + API_DETAILS_SHEET);

        try {
            InputStream inputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            int firstRow = sheet.getFirstRowNum();
            int lastRow = sheet.getLastRowNum();

            String data[][] = new String[lastRow][1];
            for (int i = firstRow; i < lastRow; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    data[i][j] = row.getCell(j).getStringCellValue();
                    System.out.println(data[i][j]);
                }
            }
            workbook.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        readExcelData("testData.xlsx", "Sheet1");
    }
}
