import com.InventoryManager.Database.DataBaseManagement;
import com.InventoryManager.Services.InventoryManagement;
import com.InventoryManager.Services.PurchaseManagement;
import com.InventoryManager.Services.SpreadSheetGenerator;
import com.InventoryManager.Services.SpreadsheetUploader;
import com.InventoryManager.Model.User;
import com.InventoryManager.Model.Product;
import com.InventoryManager.Model.ProviderClass;
import com.InventoryManager.Model.Purchase;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        DataBaseManagement dataBaseManagement = new DataBaseManagement();
        InventoryManagement inventoryManagement = new InventoryManagement(dataBaseManagement);
        PurchaseManagement purchaseManagement = new PurchaseManagement(dataBaseManagement);
        SpreadSheetGenerator spreadSheetGenerator = new SpreadSheetGenerator();
        Scanner scanner = new Scanner(System.in);

        private static void printDatabaseInfo (DataBaseManagement dataBaseManagement){
        System.out.println("\n=== Database Information ===");
        dataBaseManagement.printAllData();

        Product newProduct = new Product(
                "P001",           // ID
                "Laptop HP",      // Name
                "HP",             // Brand
                "SN123456",       // Serial Number
                "IT Department",  // Assignment Name
                "Office A",       // Location
                "Available",      // Status
                "Brand new"       // Comments
        );

// Llamar al método para agregar el producto
        inventoryManagement.createProduct(newProduct);

    }

    }
}
