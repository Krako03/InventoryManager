package com.InventoryManager.Services;

import com.InventoryManager.Database.DataBaseManagement;
import com.InventoryManager.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class InventoryManagement {
    private final DataBaseManagement dataBaseManagement;

    public InventoryManagement(DataBaseManagement dataBaseManagement) {
        this.dataBaseManagement = dataBaseManagement;
    }

    public void createProduct(Product product) {
        dataBaseManagement.getDataProducts().putIfAbsent("product", new ArrayList<>());
        List<Product> products = dataBaseManagement.getDataProducts().get("product");
        if (products.stream().noneMatch(p -> p.getId().equals(product.getId()))) {
            products.add(product);
        } else {
            System.out.println("Error: ID already defined in the db");
        }
    }

    public void editProduct(String id, Product newProduct) {
        List<Product> products = dataBaseManagement.getDataProducts().getOrDefault("product", new ArrayList<>());
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.set(i, newProduct);
                break;
            }
        }
    }

    public void deleteProduct(String id) {
        List<Product> products = dataBaseManagement.getDataProducts().getOrDefault("product", new ArrayList<>());
        products.removeIf(product -> product.getId().equals(id));
    }

    public List<Product> getProducts() {
        return dataBaseManagement.getDataProducts().getOrDefault("product", new ArrayList<>());
    }
}
