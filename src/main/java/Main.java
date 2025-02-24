import com.InventoryManager.Database.DataBaseManagement;
import com.InventoryManager.Model.Product;
import com.InventoryManager.Model.ProviderClass;
import com.InventoryManager.Model.Purchase;
import com.InventoryManager.Services.InventoryManagement;
import com.InventoryManager.Services.PurchaseManagement;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Inicia db
        DataBaseManagement db = new DataBaseManagement();

        // Iniciar servicios
        PurchaseManagement purchaseManagement = new PurchaseManagement(db);
        InventoryManagement inventoryManagement = new InventoryManagement(db);

        // Crear proveedores
        ProviderClass provider1 = new ProviderClass(1, "Apple", "apple@support.com", "Proveedor oficial");
        purchaseManagement.addProvider(provider1);

        ProviderClass provider2 = new ProviderClass(1, "Apple2", "apple@support.com2", "Proveedor oficial2");
        purchaseManagement.editProvider("1",provider2);

        //ProviderClass providerNew = new ProviderClass(1, "Sangub", "saungu@support.com", "Proveedor sanfun");
        //purchaseManagement.editProvider("1", providerNew);

        //Crea product
        Product product1 = new Product("1", "MacBook Pro", "Apple", "SN12345", "Juan Pérez", "Oficina 1", "En uso", "Buen estado");
        inventoryManagement.createProduct(product1);

        Product product2 = new Product("1", "MacBook Pro2", "Apple2", "SN123452", "Juan Pérez", "Oficina 1", "En uso", "Buen estado");
        inventoryManagement.editProduct("1",product2);


        // Crear una compra
        Purchase purchase1 = new Purchase("P001", new File("invoice1.pdf"), true, "applecare1.pdf", "Compra de prueba", "Almacén A", 25000d, 1200d, true, "Apple");
        Purchase purchase2 = new Purchase("P001", new File("invoice2.pdf"), true, "applecare2.pdf", "Compra de prueba2", "Almacén A2", 25000d, 1200d, true, "Apple");
        purchaseManagement.createPurchase(purchase1);
        System.out.println(product2.toString());

        purchaseManagement.editPurchase("P001",purchase2);

        //purchaseManagement.createPurchase(purchase2);

        //purchaseManagement.deletePurchase("P001");
        //purchaseManagement.deletePurchase("P002");
        //purchaseManagement.deleteProvider("2");
        //purchaseManagement.deleteProvider("1");
        //inventoryManagement.deleteProduct("PRD001");


        db.printAllData();
    }
}
