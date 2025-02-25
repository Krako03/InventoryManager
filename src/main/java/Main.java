import com.InventoryManager.Database.DataBaseManagement;
import com.InventoryManager.Model.Product;
import com.InventoryManager.Model.ProviderClass;
import com.InventoryManager.Model.Purchase;
import com.InventoryManager.Services.InventoryManagement;
import com.InventoryManager.Services.PurchaseManagement;
import com.InventoryManager.Services.SpreadSheetGenerator;
import com.InventoryManager.Services.SpreadsheetUploader;

import java.util.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        DataBaseManagement dataBaseManagement = new DataBaseManagement();
        Scanner scanner = new Scanner(System.in);

        // Login
        System.out.println("Welcome to the Inventory Management System!");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        //Login login = new Login(dataBaseManagement, username, password);
        //if (login.tryLogin()) {
        if (true){
            System.out.println("Login successful!");

            // Main Menu
            boolean isRunning = true;
            while (isRunning) {
                System.out.println("\nMain Menu:");
                System.out.println("1. Manage Products");
                System.out.println("2. Manage Providers");
                System.out.println("3. Manage Purchases");
                System.out.println("4. Export Products to Excel");
                System.out.println("5. Import Products from Excel");
                System.out.println("6. Print All Data");
                System.out.println("7. Log out");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        manageProducts(scanner, dataBaseManagement);
                        break;
                    case 2:
                        manageProviders(scanner, dataBaseManagement);
                        break;
                    case 3:
                        managePurchases(scanner, dataBaseManagement);
                        break;
                    case 4:
                        exportProductsToExcel(scanner, dataBaseManagement);
                        break;
                    case 5:
                        importProductsFromExcel(scanner, dataBaseManagement);
                        break;
                    case 6:
                        dataBaseManagement.printAllData();
                        break;
                    case 7:
                        System.out.println("Logging out...");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        } else {
            System.out.println("Invalid username or password. Exiting...");
        }
    }

    private static void manageProducts(Scanner scanner, DataBaseManagement dataBaseManagement) {
        InventoryManagement inventoryManagement = new InventoryManagement(dataBaseManagement);

        boolean isProductMenuRunning = true;
        while (isProductMenuRunning) {
            System.out.println("\nManage Products Menu:");
            System.out.println("1. Create Product");
            System.out.println("2. Edit Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createProduct(scanner, inventoryManagement);
                    break;
                case 2:
                    editProduct(scanner, inventoryManagement);
                    break;
                case 3:
                    deleteProduct(scanner, inventoryManagement);
                    break;
                case 4:
                    isProductMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void createProduct(Scanner scanner, InventoryManagement inventoryManagement) {
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Product Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter Product Serial Number: ");
        String serialNumber = scanner.nextLine();
        System.out.print("Enter Assignment Name: ");
        String assigmentName = scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Status: ");
        String status = scanner.nextLine();
        System.out.print("Enter Comments: ");
        String comments = scanner.nextLine();

        Product product = new Product(id, name, brand, serialNumber, assigmentName, location, status, comments);
        inventoryManagement.createProduct(product);
    }

    private static void editProduct(Scanner scanner, InventoryManagement inventoryManagement) {
        System.out.print("Enter Product ID to edit: ");
        String id = scanner.nextLine();
        System.out.print("Enter New Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Product Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter New Product Serial Number: ");
        String serialNumber = scanner.nextLine();
        System.out.print("Enter New Assignment Name: ");
        String assigmentName = scanner.nextLine();
        System.out.print("Enter New Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter New Status: ");
        String status = scanner.nextLine();
        System.out.print("Enter New Comments: ");
        String comments = scanner.nextLine();

        Product product = new Product(id, name, brand, serialNumber, assigmentName, location, status, comments);
        inventoryManagement.editProduct(id, product);
    }

    private static void deleteProduct(Scanner scanner, InventoryManagement inventoryManagement) {
        System.out.print("Enter Product ID to delete: ");
        String id = scanner.nextLine();
        inventoryManagement.deleteProduct(id);
    }

    private static void manageProviders(Scanner scanner, DataBaseManagement dataBaseManagement) {
        PurchaseManagement purchaseManagement = new PurchaseManagement(dataBaseManagement);

        boolean isProviderMenuRunning = true;
        while (isProviderMenuRunning) {
            System.out.println("\nManage Providers Menu:");
            System.out.println("1. Add Provider");
            System.out.println("2. Edit Provider");
            System.out.println("3. Delete Provider");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addProvider(scanner, purchaseManagement);
                    break;
                case 2:
                    editProvider(scanner, purchaseManagement);
                    break;
                case 3:
                    deleteProvider(scanner, purchaseManagement);
                    break;
                case 4:
                    isProviderMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addProvider(Scanner scanner, PurchaseManagement purchaseManagement) {
        System.out.print("Enter Provider ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter Provider Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Provider Contact Info: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Enter Provider Comments: ");
        String comments = scanner.nextLine();

        ProviderClass provider = new ProviderClass(id, name, contactInfo, comments);
        purchaseManagement.addProvider(provider);
    }

    private static void editProvider(Scanner scanner, PurchaseManagement purchaseManagement) {
        System.out.print("Enter Provider ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter New Provider Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Provider Contact Info: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Enter New Provider Comments: ");
        String comments = scanner.nextLine();

        ProviderClass provider = new ProviderClass(id, name, contactInfo, comments);
        purchaseManagement.editProvider(String.valueOf(id), provider);
    }

    private static void deleteProvider(Scanner scanner, PurchaseManagement purchaseManagement) {
        System.out.print("Enter Provider ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        purchaseManagement.deleteProvider(String.valueOf(id));
    }

    private static void managePurchases(Scanner scanner, DataBaseManagement dataBaseManagement) {
        PurchaseManagement purchaseManagement = new PurchaseManagement(dataBaseManagement);

        boolean isPurchaseMenuRunning = true;
        while (isPurchaseMenuRunning) {
            System.out.println("\nManage Purchases Menu:");
            System.out.println("1. Create Purchase");
            System.out.println("2. Edit Purchase");
            System.out.println("3. Delete Purchase");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    createPurchase(scanner, purchaseManagement);
                    break;
                case 2:
                    editPurchase(scanner, purchaseManagement);
                    break;
                case 3:
                    deletePurchase(scanner, purchaseManagement);
                    break;
                case 4:
                    isPurchaseMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void createPurchase(Scanner scanner, PurchaseManagement purchaseManagement) {
        System.out.print("Enter Purchase ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Provider ID: ");
        String provider = scanner.nextLine();
        System.out.print("Does the purchase have AppleCare? (true/false): ");
        boolean hasAppleCare = scanner.nextBoolean();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter AppleCare Invoice Number (if applicable): ");
        String appleCareInvoice = scanner.nextLine();
        System.out.print("Enter Comments: ");
        String comments = scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Price (MX): ");
        Double priceMx = scanner.nextDouble();
        System.out.print("Enter Price (USA): ");
        Double priceUsa = scanner.nextDouble();
        System.out.print("Enter Delivery Status (true/false): ");
        Boolean deliveryStatus = scanner.nextBoolean();

        // Placeholder for the invoice file (this could be improved to handle actual file uploads)
        File invoice = new File("path_to_invoice_file");

        Purchase purchase = new Purchase(id, invoice, hasAppleCare, appleCareInvoice, comments, location, priceMx, priceUsa, deliveryStatus, provider);
        purchaseManagement.createPurchase(purchase);
    }

    private static void editPurchase(Scanner scanner, PurchaseManagement purchaseManagement) {
        System.out.print("Enter Purchase ID to edit: ");
        String id = scanner.nextLine();
        System.out.print("Enter New Provider ID: ");
        String provider = scanner.nextLine();
        System.out.print("Does the purchase have AppleCare? (true/false): ");
        boolean hasAppleCare = scanner.nextBoolean();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter New AppleCare Invoice Number (if applicable): ");
        String appleCareInvoice = scanner.nextLine();
        System.out.print("Enter New Comments: ");
        String comments = scanner.nextLine();
        System.out.print("Enter New Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter New Price (MX): ");
        Double priceMx = scanner.nextDouble();
        System.out.print("Enter New Price (USA): ");
        Double priceUsa = scanner.nextDouble();
        System.out.print("Enter New Delivery Status (true/false): ");
        Boolean deliveryStatus = scanner.nextBoolean();

        // Placeholder for the invoice file (this could be improved to handle actual file uploads)
        File invoice = new File("path_to_invoice_file");

        Purchase purchase = new Purchase(id, invoice, hasAppleCare, appleCareInvoice, comments, location, priceMx, priceUsa, deliveryStatus, provider);
        purchaseManagement.editPurchase(id, purchase);
    }

    private static void deletePurchase(Scanner scanner, PurchaseManagement purchaseManagement) {
        System.out.print("Enter Purchase ID to delete: ");
        String id = scanner.nextLine();
        purchaseManagement.deletePurchase(id);
    }



    private static void exportProductsToExcel(Scanner scanner, DataBaseManagement dataBaseManagement) {
        SpreadSheetGenerator sp =new SpreadSheetGenerator();
        System.out.println("Write a file name to export");
        String filepath=scanner.nextLine();
        System.out.println(dataBaseManagement.getProducts());
        sp.exportToExcel(dataBaseManagement.getProducts(),filepath+".xlsx");
    }

    private static void importProductsFromExcel(Scanner scanner, DataBaseManagement dataBaseManagement) {
        System.out.println("Write a file name to import");
        String filepath=scanner.nextLine();
        SpreadsheetUploader up = new SpreadsheetUploader(filepath);

        List<Product> productList = up.uploadSpreadsheet();

        dataBaseManagement.saveData("product", Collections.singletonList(productList.toString()));
        System.out.println("Products imported successfully!");
    }
}
