package com.InventoryManager.menus;

import java.util.Scanner;

public class EmployeeMenu {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n👨‍💼 GESTIÓN DE EMPLEADOS");
            System.out.println("1. Agregar empleado");
            System.out.println("2. Editar empleado");
            System.out.println("3. Eliminar empleado");
            System.out.println("4. Listar empleados");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> editEmployee();
                case 3 -> deleteEmployee();
                case 4 -> listEmployees();
                case 5 -> running = false;
                default -> System.out.println("❌ Opción inválida.");
            }
        }
    }

    private void addEmployee() {
        System.out.println("👉 Agregar empleado (pendiente de implementación)");
    }

    private void editEmployee() {
        System.out.println("👉 Editar empleado (pendiente de implementación)");
    }

    private void deleteEmployee() {
        System.out.println("👉 Eliminar empleado (pendiente de implementación)");
    }

    private void listEmployees() {
        System.out.println("👉 Listar empleados (pendiente de implementación)");
    }
}
