package com.nagarro.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    public static List<String[]> readExcel(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                String[] rowData = new String[row.getLastCellNum()];
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    rowData[i] = row.getCell(i).toString();
                }
                data.add(rowData);
            }
        }
        return data;
    }
    
    public static List<String[]> readSpecificExcel(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; 
                String[] rowData = new String[3]; 
                rowData[0] = row.getCell(0).toString(); 
                rowData[1] = getCellValueAsString(row.getCell(1)); 
                rowData[2] = row.getCell(2).toString(); 
                data.add(rowData);
            }
        }
        return data;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // Convert numeric cell to a plain string without scientific notation
                return BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
            default:
                return cell.toString();
        }
    }


}
