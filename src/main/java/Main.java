import com.InventoryManager.Database.DataBaseManagement;
import com.InventoryManager.Model.Product;
import com.InventoryManager.Model.ProviderClass;
import com.InventoryManager.Model.Purchase;
import com.InventoryManager.Services.InventoryManagement;
import com.InventoryManager.Services.Login;
import com.InventoryManager.Services.PurchaseManagement;
import com.InventoryManager.Services.SpreadSheetGenerator;
import com.InventoryManager.Services.SpreadsheetUploader;

import java.util.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        DataBaseManagement dataBaseManagement = new DataBaseManagement();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Inventory Management System!");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Login login = new Login(dataBaseManagement, username, password);
        if (login.tryLogin()) {
            System.out.println("Login successful!");

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

                /*int choice=0;
                while (true) {
                    System.out.print("Choose an option: ");
                    if (scanner.hasNextInt()) {
                       choice = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    } else {
                        System.out.println("Error: Not valid Option.");
                        scanner.next();
                    }
                }
                */


                switch (validChoice(scanner)) {
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


            switch (validChoice(scanner)) {
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
        if (inventoryManagement.containsId(id)) {
            Product currentProduct = inventoryManagement.getProductById(id);
            System.out.println("[1] Name: " + currentProduct.getName());
            System.out.println("[2] Brand: " + currentProduct.getBrand());
            System.out.println("[3] Serial Number: " + currentProduct.getSerialNumber());
            System.out.println("[4] Assignment Name: " + currentProduct.getAssigmentName());
            System.out.println("[5] Location: " + currentProduct.getLocation());
            System.out.println("[6] Status: " + currentProduct.getStatus());
            System.out.println("[7] Comments: " + currentProduct.getComments());

            System.out.print("Enter the number of the field you want to modify (1-7) or 0 to cancel: ");
            int choice = validChoice(scanner);

            String name = currentProduct.getName();
            String brand = currentProduct.getBrand();
            String serialNumber = currentProduct.getSerialNumber();
            String assigmentName = currentProduct.getAssigmentName();
            String location = currentProduct.getLocation();
            String status = currentProduct.getStatus();
            String comments = currentProduct.getComments();

            switch (choice) {
                case 1:
                    System.out.print("Enter New Product Name: ");
                    name = scanner.nextLine();
                    break;
                case 2:
                    System.out.print("Enter New Product Brand: ");
                    brand = scanner.nextLine();
                    break;
                case 3:
                    System.out.print("Enter New Product Serial Number: ");
                    serialNumber = scanner.nextLine();
                    break;
                case 4:
                    System.out.print("Enter New Assignment Name: ");
                    assigmentName = scanner.nextLine();
                    break;
                case 5:
                    System.out.print("Enter New Location: ");
                    location = scanner.nextLine();
                    break;
                case 6:
                    System.out.print("Enter New Status: ");
                    status = scanner.nextLine();
                    break;
                case 7:
                    System.out.print("Enter New Comments: ");
                    comments = scanner.nextLine();
                    break;
                case 0:
                    System.out.println("No changes made.");
                    return;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }
            Product updatedProduct = new Product(id, name, brand, serialNumber, assigmentName, location, status, comments);

            inventoryManagement.editProduct(id, updatedProduct);
        }
        else{
            System.out.println("Id not found.");
        }

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


            switch (validChoice(scanner)) {
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
        scanner.nextLine();
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
        scanner.nextLine();

        ProviderClass currentProvider = purchaseManagement.getProviderById(String.valueOf(id));
        if (currentProvider == null) {
            System.out.println("Provider not found.");
            return;
        }

        System.out.println("[1] Name: " + currentProvider.getName());
        System.out.println("[2] Contact Info: " + currentProvider.getContactInfo());
        System.out.println("[3] Comments: " + currentProvider.getComments());

        System.out.print("Enter the number of the field you want to modify (1-3) or 0 to cancel: ");
        int choice = validChoice(scanner);

        String name = currentProvider.getName();
        String contactInfo = currentProvider.getContactInfo();
        String comments = currentProvider.getComments();

        switch (choice) {
            case 1:
                System.out.print("Enter new Provider Name: ");
                name = scanner.nextLine();
                break;
            case 2:
                System.out.print("Enter new Provider Contact Info: ");
                contactInfo = scanner.nextLine();
                break;
            case 3:
                System.out.print("Enter new Provider Comments: ");
                comments = scanner.nextLine();
                break;
            case 0:
                System.out.println("No changes made.");
                return;
            default:
                System.out.println("Invalid option. No changes made.");
                return;
        }

        ProviderClass updatedProvider = new ProviderClass(id, name, contactInfo, comments);

        purchaseManagement.editProvider(String.valueOf(id), updatedProvider);
        System.out.println("Provider updated successfully!");
    }


    private static void deleteProvider(Scanner scanner, PurchaseManagement purchaseManagement) {
        System.out.print("Enter Provider ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
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


            switch (validChoice(scanner)) {
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

        File invoice = new File("path_to_invoice_file");

        Purchase purchase = new Purchase(id, invoice, hasAppleCare, appleCareInvoice, comments, location, priceMx, priceUsa, deliveryStatus, provider);
        purchaseManagement.createPurchase(purchase);
    }

    private static void editPurchase(Scanner scanner, PurchaseManagement purchaseManagement) {
        System.out.print("Enter Purchase ID to edit: ");
        String id = scanner.nextLine();

        if (purchaseManagement.containsPurchase(id)) {

            Purchase currentPurchase = purchaseManagement.getPurchaseById(id);

            System.out.println("[1] Provider: " + currentPurchase.getProvider());
            System.out.println("[2] Invoice Path: " + currentPurchase.getInvoice().getPath());
            System.out.println("[3] Has AppleCare: " + currentPurchase.isHasAppleCare());
            System.out.println("[4] AppleCare Invoice: " + currentPurchase.getAppleCareInvoice());
            System.out.println("[5] Comments: " + currentPurchase.getComments());
            System.out.println("[6] Location: " + currentPurchase.getLocation());
            System.out.println("[7] Price (MX): " + currentPurchase.getPriceMx());
            System.out.println("[8] Price (USA): " + currentPurchase.getPriceUsa());
            System.out.println("[9] Delivery Status: " + currentPurchase.getDeliveryStatus());
            System.out.print("Enter the number of the field you want to modify (1-9) or 0 to cancel: ");
            int choice = validChoice(scanner);

            String provider = currentPurchase.getProvider();
            File invoice = currentPurchase.getInvoice();
            boolean hasAppleCare = currentPurchase.isHasAppleCare();
            String appleCareInvoice = currentPurchase.getAppleCareInvoice();
            String comments = currentPurchase.getComments();
            String location = currentPurchase.getLocation();
            Double priceMx = currentPurchase.getPriceMx();
            Double priceUsa = currentPurchase.getPriceUsa();
            Boolean deliveryStatus = currentPurchase.getDeliveryStatus();

            switch (choice) {
                case 1:
                    System.out.print("Enter New Provider: ");
                    provider = scanner.nextLine();
                    break;
                case 2:
                    System.out.print("Enter New Invoice File Path: ");
                    String invoicePath = scanner.nextLine();
                    invoice = new File(invoicePath);
                    break;
                case 3:
                    System.out.print("Does the purchase have AppleCare? (true/false): ");
                    hasAppleCare = scanner.nextBoolean();
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.print("Enter New AppleCare Invoice: ");
                    appleCareInvoice = scanner.nextLine();
                    break;
                case 5:
                    System.out.print("Enter New Comments: ");
                    comments = scanner.nextLine();
                    break;
                case 6:
                    System.out.print("Enter New Location: ");
                    location = scanner.nextLine();
                    break;
                case 7:
                    System.out.print("Enter New Price (MX): ");
                    priceMx = scanner.nextDouble();
                    scanner.nextLine();
                    break;
                case 8:
                    System.out.print("Enter New Price (USA): ");
                    priceUsa = scanner.nextDouble();
                    scanner.nextLine();
                    break;
                case 9:
                    System.out.print("Enter New Delivery Status (true/false): ");
                    deliveryStatus = scanner.nextBoolean();
                    scanner.nextLine();
                    break;
                case 0:
                    System.out.println("No changes made.");
                    return;
                default:
                    System.out.println("Invalid option. No changes made.");
                    return;
            }

            Purchase updatedPurchase = new Purchase(
                    id,
                    invoice,
                    hasAppleCare,
                    appleCareInvoice,
                    comments,
                    location,
                    priceMx,
                    priceUsa,
                    deliveryStatus,
                    provider
            );

            purchaseManagement.editPurchase(id, updatedPurchase);
            System.out.println("Purchase updated successfully!");
        } else {
            System.out.println("Purchase ID not found.");
        }
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
        String filepath = scanner.nextLine();
        SpreadsheetUploader up = new SpreadsheetUploader(filepath);

        List<Product> productList = up.uploadSpreadsheet();

        if (!productList.isEmpty()) {
            dataBaseManagement.saveData("product", Collections.singletonList(productList.toString()));
            System.out.println("Products imported successfully!");
        }
    }

    private static int validChoice(Scanner scanner){
        int choice =0;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine();

        } else {
            System.out.println("");
            scanner.next();
        }
        return choice;
    }
}
