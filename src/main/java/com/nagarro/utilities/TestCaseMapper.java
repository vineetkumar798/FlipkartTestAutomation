package com.nagarro.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestCaseMapper {
    private static final String EXCEL_FILE_PATH = "src/main/resources/testdata/ExecutionTests.xlsx";

    public static List<String> getSelectedTestCases() {
        List<String> selectedTestCases = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(EXCEL_FILE_PATH);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(0); 
            for (Row row : sheet) {
                String testCaseName = row.getCell(0).getStringCellValue();
                String executionRequired = row.getCell(1).getStringCellValue();
                if ("Yes".equalsIgnoreCase(executionRequired)) {
                    selectedTestCases.add(testCaseName);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test case execution data from Excel", e);
        }
        return selectedTestCases;
    }
}
