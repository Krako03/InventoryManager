package com.InventoryManager.Services;

import com.InventoryManager.Model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpreadsheetUploaderTest {
    private String testFilePath;

    @BeforeEach
    void setUp() {
        // Obtener la ruta del archivo en test/resources
        URL resource = getClass().getClassLoader().getResource("Basic.xlsx");
        assertNotNull(resource, "El archivo productos.xlsx no se encontró en test/resources");
        Path path = Paths.get(resource.getPath());
        testFilePath = path.toString();
    }

    @Test
    void testUploadSpreadsheet() {
        SpreadsheetUploader uploader = new SpreadsheetUploader(testFilePath);
        List<Product> products = uploader.uploadSpreadsheet();

        // Verificar que se cargaron 9 productos
        assertNotNull(products, "La lista de productos no debería ser nula");
        assertEquals(9, products.size(), "Debería haber 9 productos en la lista");

        // Verificar los datos del primer producto
        Product firstProduct = products.get(0);
        assertEquals("C16806", firstProduct.getId());
        assertEquals("OK", firstProduct.getStatus());
        assertEquals("Laptop", firstProduct.getName());
        assertEquals("Abraham Saenz Dominguez", firstProduct.getAssigmentName());
        assertEquals("MP2MXVT09L", firstProduct.getSerialNumber());
        assertEquals("Apple", firstProduct.getBrand());
        assertEquals("Local", firstProduct.getLocation());
        assertEquals("Not Used", firstProduct.getComments());
    }
}