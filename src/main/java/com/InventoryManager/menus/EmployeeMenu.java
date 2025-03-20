package com.InventoryManager.menus;

import com.InventoryManager.model.Employee;
import com.InventoryManager.repositories.EmployeeRepository;
import com.InventoryManager.utilities.DBConnection;
import com.InventoryManager.utilities.DBController;
import com.InventoryManager.utilities.DatabaseConfig;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeMenu implements MenuInterface {
    private final Scanner scanner = new Scanner(System.in);
    private final EmployeeRepository employeeRepository;

    public EmployeeMenu() {
        DBConnection dbConnection = new DBConnection(DatabaseConfig.JDBC_URL, DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD);
        this.employeeRepository = new EmployeeRepository(new DBController(dbConnection));
    }

    @Override
    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n ----- GESTIÓN DE EMPLEADOS ----- ");
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
                default -> System.out.println(" Opción inválida.");
            }
        }
    }

    private void addEmployee() {
        System.out.print("Ingrese el nombre del empleado: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el correo del empleado: ");
        String mail = scanner.nextLine();

        Employee employee = new Employee(null, name, mail, null);
        employeeRepository.save(employee);

        System.out.println("-> Empleado agregado correctamente.");
    }

    private void editEmployee() {
        System.out.print("Ingrese el ID del empleado a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        Employee employee = optionalEmployee.get();
        System.out.print("Nuevo nombre (" + employee.getName() + "): ");
        String name = scanner.nextLine();
        System.out.print("Nuevo correo (" + employee.getMail() + "): ");
        String mail = scanner.nextLine();

        employee.setName(name.isEmpty() ? employee.getName() : name);
        employee.setMail(mail.isEmpty() ? employee.getMail() : mail);

        employeeRepository.update(employee);
        System.out.println("-> Empleado actualizado correctamente.");
    }

    private void deleteEmployee() {
        System.out.print("Ingrese el ID del empleado a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        employeeRepository.deleteById(id);
        System.out.println("Empleado eliminado correctamente.");
    }

    private void listEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        System.out.println("\nLista de Empleados:");
        for (Employee emp : employees) {
            System.out.println("ID: " + emp.getId() + " |  Nombre: " + emp.getName() + " |  Correo: " + emp.getMail());
        }
    }
}
