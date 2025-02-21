package com.InventoryManager.Services;

import com.InventoryManager.Model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpreadSheetGeneratorTest {
    private String testFilePath;
    List<Product> products;

    @BeforeEach
    void setUp() {
        products = List.of(
                new Product("C10293", "Laptop", "Apple", "2938475828hf8", "Juan Pedro", "Local", "OK", "Con"),
                new Product("1", "Available", "Laptop", "IT Dept", "SN12345", "Dell", "Office", "Good condition")
        );

        testFilePath = "Data.xlsx";
    }

    @AfterEach
    void tearDown() {
        File file = new File(testFilePath);
        if (file.exists()) {
            assertTrue(file.delete(), "Failed to delete test file!");
        }
    }

    @Test
    void testCreationFile() {
        SpreadSheetGenerator generator = new SpreadSheetGenerator();
        generator.exportToExcel(products, testFilePath);

        File file = new File(testFilePath);
        assertTrue(file.exists() && file.length() > 0, "Excel file was not created properly!");
    }
}