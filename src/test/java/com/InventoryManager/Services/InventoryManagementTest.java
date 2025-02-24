package com.InventoryManager.Services;

import com.InventoryManager.Database.DataBaseManagement;
import com.InventoryManager.Model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class InventoryManagementTest {

    private DataBaseManagement db;
    private InventoryManagement inventoryManagement;

    @BeforeEach
    public void setUp() {
        db = new DataBaseManagement();
        inventoryManagement = new InventoryManagement(db);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product("1", "MacBook Pro", "Apple", "SN12345", "Juan Pérez", "Oficina 1", "En uso", "Buen estado");
        inventoryManagement.createProduct(product);

        List<String> products = db.getData("product");
        assertFalse(products.isEmpty());
        assertTrue(products.get(0).contains("id=1"));
    }

    @Test
    public void testEditProduct() {
        Product product1 = new Product("1", "MacBook Pro", "Apple", "SN12345", "Juan Pérez", "Oficina 1", "En uso", "Buen estado");
        inventoryManagement.createProduct(product1);

        Product product2 = new Product("1", "MacBook Pro Updated", "Apple", "SN12345", "Juan Pérez", "Oficina 2", "En uso", "Excelente estado");
        inventoryManagement.editProduct("1", product2);

        List<String> products = db.getData("product");
        assertTrue(products.get(0).contains("MacBook Pro Updated"));
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product("1", "MacBook Pro", "Apple", "SN12345", "Juan Pérez", "Oficina 1", "En uso", "Buen estado");
        inventoryManagement.createProduct(product);

        inventoryManagement.deleteProduct("1");

        List<String> products = db.getData("product");
        assertTrue(products.isEmpty());
    }

    @Test
    public void testGetProducts() {
        Product product1 = new Product("1", "MacBook Pro", "Apple", "SN12345", "Juan Pérez", "Oficina 1", "En uso", "Buen estado");
        Product product2 = new Product("2", "MacBook Air", "Apple", "SN12346", "Maria Gómez", "Oficina 2", "En uso", "Buen estado");
        inventoryManagement.createProduct(product1);
        inventoryManagement.createProduct(product2);

        List<String> products = db.getData("product");
        assertEquals(2, products.size());
    }
}
