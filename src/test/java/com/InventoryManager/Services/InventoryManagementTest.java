package com.InventoryManager.Services;

import com.InventoryManager.Database.DataBaseManagement;
import com.InventoryManager.Model.ProviderClass;
import com.InventoryManager.Model.Purchase;
import com.InventoryManager.Services.PurchaseManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


import java.io.File;
import java.util.List;

class PurchaseManagementTest {
    private DataBaseManagement db;
    private PurchaseManagement purchaseManagement;

    @BeforeEach
    void setup() {
        db = mock(DataBaseManagement.class);
        purchaseManagement = new PurchaseManagement(db);
    }





    @Test
    void whenAddProvider_thenSaveDataCalled() {
        ProviderClass provider = new ProviderClass(1, "Apple", "apple@support.com", "Proveedor oficial");

        purchaseManagement.addProvider(provider);

        verify(db).saveData("provider", List.of(provider.toString()));
    }


}
