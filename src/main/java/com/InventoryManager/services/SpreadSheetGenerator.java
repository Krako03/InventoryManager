/*package com.InventoryManager.services;

import com.InventoryManager.model.Asset;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SpreadSheetGenerator {
    public void exportToExcel(List<Asset> assets, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Products");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Status", "Name", "Assignment Name", "Serial Number", "Brand", "Location", "Comments"};

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            // Apply a bold style to the header
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            cell.setCellStyle(headerStyle);
        }

        // Populate rows with product data
        int rowNum = 1;
        for (Asset asset : assets) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(asset.getId());
            row.createCell(1).setCellValue(asset.getStatus());
            row.createCell(2).setCellValue(asset.getName());
            row.createCell(3).setCellValue(asset.getAssigmentName());
            row.createCell(4).setCellValue(asset.getSerialNumber());
            row.createCell(5).setCellValue(asset.getBrand());
            row.createCell(6).setCellValue(asset.getLocation());
            row.createCell(7).setCellValue(asset.getComments());
        }

        // Auto-size columns for better readability
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        filePath = "src/main/resources/" + filePath;

        // Write to file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Excel file created successfully: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}*/
