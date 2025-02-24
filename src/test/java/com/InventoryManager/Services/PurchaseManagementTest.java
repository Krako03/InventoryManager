package com.InventoryManager.Services;

import com.InventoryManager.Database.DataBaseManagement;
import com.InventoryManager.Model.ProviderClass;
import com.InventoryManager.Model.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

public class PurchaseManagementTest {

    private DataBaseManagement db;
    private PurchaseManagement purchaseManagement;

    @BeforeEach
    public void setUp() {
        db = new DataBaseManagement();
        purchaseManagement = new PurchaseManagement(db);
    }

    @Test
    public void testCreatePurchase() {
        Purchase purchase = new Purchase("P001", new File("invoice1.pdf"), true, "applecare1.pdf", "Compra de prueba", "Almacén A", 25000d, 1200d, true, "Apple");
        purchaseManagement.createPurchase(purchase);

        List<String> purchases = db.getData("purchase");
        assertFalse(purchases.isEmpty());
        assertTrue(purchases.get(0).contains("id=P001"));
    }

    @Test
    public void testEditPurchase() {
        Purchase purchase = new Purchase("P001", new File("invoice1.pdf"), true, "applecare1.pdf", "Compra de prueba", "Almacén A", 25000d, 1200d, true, "Apple");
        purchaseManagement.createPurchase(purchase);

        Purchase updatedPurchase = new Purchase("P001", new File("invoice2.pdf"), true, "applecare2.pdf", "Compra de prueba actualizada", "Almacén B", 26000d, 1300d, true, "Apple");
        purchaseManagement.editPurchase("P001", updatedPurchase);

        List<String> purchases = db.getData("purchase");
        assertTrue(purchases.get(0).contains("Compra de prueba actualizada"));
    }

    @Test
    public void testDeletePurchase() {
        Purchase purchase = new Purchase("P001", new File("invoice1.pdf"), true, "applecare1.pdf", "Compra de prueba", "Almacén A", 25000d, 1200d, true, "Apple");
        purchaseManagement.createPurchase(purchase);

        purchaseManagement.deletePurchase("P001");

        List<String> purchases = db.getData("purchase");
        assertTrue(purchases.isEmpty());
    }

    @Test
    public void testAddProvider() {
        ProviderClass provider = new ProviderClass(1, "Apple", "apple@support.com", "Proveedor oficial");
        purchaseManagement.addProvider(provider);

        List<String> providers = db.getData("provider");
        assertFalse(providers.isEmpty());
        assertTrue(providers.get(0).contains("id=1"));
    }

    @Test
    public void testEditProvider() {
        ProviderClass provider = new ProviderClass(1, "Apple", "apple@support.com", "Proveedor oficial");
        purchaseManagement.addProvider(provider);

        ProviderClass updatedProvider = new ProviderClass(1, "Apple Inc.", "apple@inc.com", "Proveedor actualizado");
        purchaseManagement.editProvider("1", updatedProvider);

        List<String> providers = db.getData("provider");
        assertTrue(providers.get(0).contains("Proveedor actualizado"));
    }

    @Test
    public void testDeleteProvider() {
        ProviderClass provider = new ProviderClass(1, "Apple", "apple@support.com", "Proveedor oficial");
        purchaseManagement.addProvider(provider);

        purchaseManagement.deleteProvider("1");

        List<String> providers = db.getData("provider");
        assertTrue(providers.isEmpty());
    }
}
