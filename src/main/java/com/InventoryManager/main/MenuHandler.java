package com.InventoryManager.menus;

import java.util.Scanner;

public class MenuHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final EmployeeMenu employeeMenu = new EmployeeMenu();
    //private final UserMenu userMenu = new UserMenu();
    //private final ProviderMenu providerMenu = new ProviderMenu();
    //private final AssetMenu assetMenu = new AssetMenu();
    //private final PurchaseMenu purchaseMenu = new PurchaseMenu();
    //private final MovementMenu movementMenu = new MovementMenu();

    public void showMainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n📌 MENÚ PRINCIPAL");
            System.out.println("1. Gestión de empleados");
            System.out.println("2. Gestión de usuarios");
            System.out.println("3. Gestión de proveedores");
            System.out.println("4. Gestión de activos");
            System.out.println("5. Gestión de compras");
            System.out.println("6. Gestión de movimientos");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> employeeMenu.showMenu();
                //case 2 -> userMenu.showMenu();
                //case 3 -> providerMenu.showMenu();
                //case 4 -> assetMenu.showMenu();
                //case 5 -> purchaseMenu.showMenu();
                //case 6 -> movementMenu.showMenu();
                case 7 -> {
                    System.out.println("👋 Saliendo del sistema...");
                    running = false;
                }
                default -> System.out.println("❌ Opción inválida. Intente de nuevo.");
            }
        }
    }
}
