package com.InventoryManager.Services;

import com.InventoryManager.Model.Asset;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SpreadsheetUploader {
    private final String fileName;
    private List<Asset> dataBase;

    public SpreadsheetUploader(String fileName) {
        URL resource = getClass().getClassLoader().getResource(fileName);
        assert resource != null;
        Path path = Paths.get(resource.getPath());
        this.fileName = path.toString();
        dataBase = new ArrayList<>();
    }

    public List<Asset> uploadSpreadsheet() {
        try (FileInputStream fis = new FileInputStream(new File(fileName));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            List<Asset> loaded = new ArrayList<>();
            int index = 0;

            for (Row row : sheet) {
                if (row.getRowNum() == 0 || isRowEmpty(row)) continue;

                Asset asset = new Asset();
                for (int columnIndex = 0; columnIndex < 8; columnIndex++) {
                    Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String content = cell.toString();

                    if (!content.isEmpty()) {
                        switch (columnIndex) {
                            case 0 -> asset.setId(content);
                            case 1 -> asset.setStatus(content);
                            case 2 -> asset.setName(content);
                            case 3 -> asset.setAssigmentName(content);
                            case 4 -> asset.setSerialNumber(content);
                            case 5 -> asset.setBrand(content);
                            case 6 -> asset.setLocation(content);
                            case 7 -> asset.setComments(content);
                        }
                    }
                }
                loaded.add(asset);
            }
            return loaded;
        } catch (IOException e) {
            System.out.println("Error reading the Excel file: " + e.getMessage());
            return List.of();
        }
    }

    private boolean isRowEmpty(Row row) {
        for (Cell cell : row) {
            if (cell.getCellType() != CellType.BLANK && !getCellValueAsString(cell).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private String getCellValueAsString(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }
}
