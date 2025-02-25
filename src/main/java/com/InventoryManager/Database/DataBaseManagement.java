package com.InventoryManager.Database;

import com.InventoryManager.Model.User;
import com.InventoryManager.Model.Product;
import com.InventoryManager.Model.ProviderClass;
import com.InventoryManager.Model.Purchase;
import lombok.Setter;

import java.util.*;

@Setter
public class DataBaseManagement {
    private int port;
    private String username;
    private String password;

    private final Map<String, List<Product>> dataProducts = new HashMap<>();
    private final Map<String, List<ProviderClass>> dataProviders = new HashMap<>();
    private final Map<String, List<Purchase>> dataPurchases = new HashMap<>();

    private final List<User> users = List.of(
            new User("saul", "12345678", "Admin"),
            new User("oscar", "87654321", "Admin"),
            new User("alex", "qwerty", "ItAdmin"));

    public Map<String, List<Product>> getDataProducts() {
        return dataProducts;
    }

    public Map<String, List<ProviderClass>> getDataProviders() {
        return dataProviders;
    }

    public Map<String, List<Purchase>> getDataPurchases() {
        return dataPurchases;
    }

    public Optional<User> containsUser(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username)
                        && user.getPassword().equals(password))
                .findAny();
    }

    public void printAllData() {
        System.out.println("--- Products ---");
        dataProducts.forEach((key, value) -> System.out.println(key + " -> " + value));

        System.out.println("--- Providers ---");
        dataProviders.forEach((key, value) -> System.out.println(key + " -> " + value));

        System.out.println("--- Purchases ---");
        dataPurchases.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}
