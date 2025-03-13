package com.InventoryManager.main;

import com.InventoryManager.model.*;
import com.InventoryManager.repositories.*;
import com.InventoryManager.utilities.DBConnection;
import com.InventoryManager.utilities.DataBaseConnection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Configurar conexión a la base de datos
        String jdbcUrl = "jdbc:postgresql://localhost:5436/managementDB";
        String username = "admin";
        String password = "i2345678";

        DBConnection dbConnection = new DBConnection(jdbcUrl, username, password);
        DataBaseConnection dataBaseConnection = new DataBaseConnection(dbConnection);

        // 🔹 Crear repositorios
        EmployeeRepository employeeRepository = new EmployeeRepository(dataBaseConnection);
        UserRepository userRepository = new UserRepository(dataBaseConnection);
        ProviderRepository providerRepository = new ProviderRepository(dataBaseConnection);
        PurchaseRepository purchaseRepository = new PurchaseRepository(dataBaseConnection);
        PurchaseDetailRepository purchaseDetailRepository = new PurchaseDetailRepository(dataBaseConnection);
        AssetsMovementsRepository assetsMovementsRepository = new AssetsMovementsRepository(dataBaseConnection);
        AssetRepository assetRepository = new AssetRepository(dataBaseConnection);
        ComputerRepository computerRepository = new ComputerRepository(dataBaseConnection);
/*
        //assets
        Asset newAsset = new Asset();
        newAsset.setName("Dell Monitor");
        newAsset.setDescription("27-inch LED display");
        newAsset.setSeriesNumber("DM123456");
        //assetRepository.save(newAsset);
        System.out.println("✅ Asset insertado");

        //COMPUTER
        Computer newComputer = new Computer();
        newComputer.setName("MacBook Pro");
        newComputer.setDescription("16-inch M2 Pro");
        newComputer.setSeriesNumber("MBP123352");
        newComputer.setRam(16);
        newComputer.setDisk(512);
        newComputer.setCore("M2 Pro");
        newComputer.setScreenState("Good");
        newComputer.setKeyboardState("Good");
        newComputer.setShellState("Minor scratches");
        newComputer.setComments("Used for development");

        //computerRepository.save(newComputer);
        System.out.println("✅ Computadora insertada");

        //USER
        List<Employee> employeesList = employeeRepository.findAll();
        if (employeesList.isEmpty()) {
            System.out.println("❌ No hay empleados disponibles.");
            return;
        }

        // 🔍 Verificar si el empleado ya tiene un usuario
        int existingEmployeeId = employeesList.get(0).getId();
        Optional<User> existingUser = userRepository.findById(existingEmployeeId);

        if (existingUser.isPresent()) {
            System.out.println("⚠️ El empleado con ID " + existingEmployeeId + " ya tiene un usuario.");
        } else {
            User newUser = new User();
            newUser.setEmployeeId(existingEmployeeId);
            newUser.setRole(Role.EMPLOYEE);
            newUser.setUsername("newname");
            newUser.setPassword("securepasswordq");
            //userRepository.save(newUser);
            System.out.println("✅ Usuario insertado");
        }

        //EMPLOYEES
        // Buscar un empleado sin usuario asignado
        List<Employee> employeesWithoutUsers = employeeRepository.findAll().stream()
                .filter(emp -> userRepository.findById(emp.getId()).isEmpty())
                .toList();

        if (employeesWithoutUsers.isEmpty()) {
            System.out.println("❌ No hay empleados disponibles sin usuario.");
            return;
        }

        int availableEmployeeId = employeesWithoutUsers.get(0).getId();

        User newUser = new User();
        newUser.setEmployeeId(availableEmployeeId);
        newUser.setRole(Role.ADMIN);
        newUser.setUsername("new_user_" + availableEmployeeId);
        newUser.setPassword("securepassword");
        //userRepository.save(newUser);
        System.out.println("✅ Usuario insertado con ID de empleado: " + availableEmployeeId);

        //proveedor
        // Obtener un ID válido de proveedor
        List<ProviderClass> providersList = providerRepository.findAll();
        if (providersList.isEmpty()) {
            System.out.println("❌ No hay proveedores disponibles.");
            return;
        }


        int existingProviderId = providersList.get(0).getId(); // Usa el primer proveedor disponible

       // Insertar la compra con un proveedor válido
        Purchase newPurchase = new Purchase();
        newPurchase.setProviderId(existingProviderId);
        newPurchase.setDate(LocalDate.of(2024, 3, 11));
        newPurchase.setTotalAmount(2500.50);
        purchaseRepository.save(newPurchase);
        System.out.println("✅ Compra insertada");


        List<Purchase> purchasesList = purchaseRepository.findAll();
        List<Asset> assetsList = assetRepository.findAll();

        if (purchasesList.isEmpty() || assetsList.isEmpty()) {
            System.out.println(purchasesList);
            System.out.println("❌ No hay compras o assets disponibles.");
            return;
        }

        int existingPurchaseId = purchasesList.get(0).getId();
        int existingAssetId = assetsList.get(0).getId();

        PurchaseDetail newDetail = new PurchaseDetail();
        newDetail.setPurchaseId(existingPurchaseId);
        newDetail.setAssetId(existingAssetId);
        newDetail.setAmount(5);
        newDetail.setPricePerItem(300.00);
        purchaseDetailRepository.save(newDetail);
        System.out.println("✅ Detalle de compra insertado");

        //AssetsMovements

        List<Asset> assetList = assetRepository.findAll();
        List<Employee> employeeList = employeeRepository.findAll();

        if(assetList.isEmpty() || employeeList.isEmpty()){
            System.out.println("❌ No hay empleados oa assets disponibles.");
            return;
        }

        int existingAsset = assetList.get(0).getId();
        int existingEmployee = employeeList.get(0).getId();



        AssetMovement newAssetMovement = new AssetMovement();
        newAssetMovement.setEmployeeId(existingEmployee);
        newAssetMovement.setAssetId(existingAsset);
        newAssetMovement.setAssetMovementDate(LocalDate.of(2024, 3, 12));
        newAssetMovement.setMovementType(MovementType.ASSIGN);
        assetsMovementsRepository.save(newAssetMovement);
        System.out.println("✅✅✅✅✅✅✅✅ asset movement insertada");


        ProviderClass newProvider = new ProviderClass();
        newProvider.setName("Tech Supplies Inc2.");
        newProvider.setContact("contact@techsupplies.com");

        // Guardar en la base de datos
        providerRepository.save(newProvider);

        System.out.println("✅ Proveedor insertado exitosamente.");
*/
    }
}
