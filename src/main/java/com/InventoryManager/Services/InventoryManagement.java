package com.InventoryManager.Services;

import com.InventoryManager.Database.DataBaseManagement;
import com.InventoryManager.Model.Product;

import java.util.List;

public class InventoryManagement {
    private final DataBaseManagement dataBaseManagement;

    public InventoryManagement(DataBaseManagement dataBaseManagement) {
        this.dataBaseManagement=dataBaseManagement;
    }

    public void createProduct(Product product) {
        if(dataBaseManagement.notExists("product", product.getId())) {
            dataBaseManagement.saveData("product", List.of(product.toString()));
        } else {
            System.out.println("Error: ID already defined in the db");
        }
    }

    public void editProduct(String id, Product newProduct) {
        List<String> products = dataBaseManagement.getData("product");
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).contains("id=" + id + "")) {
                products.set(i, newProduct.toString());
                break;
            }
        }
        dataBaseManagement.saveData("product", products);
    }
    
    public void deleteProduct(String id) {
        List<String> products  = dataBaseManagement.getData("product");
        products.removeIf(product->product.contains(id));
        dataBaseManagement.saveData("product",products);
    }

    public List<Product> getProducts() {
        List<String> productStrings = dataBaseManagement.getData("product");
        return null;
    }

    public boolean containsId(String id){

        List<Product> productsObj = dataBaseManagement.getProducts();

        System.out.println(productsObj);
        for (int i =0;  i<productsObj.size(); i++){
            if (productsObj.get(i).getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public Product getProductById(String id){
        List<Product> productsObj = dataBaseManagement.getProducts();

        System.out.println(productsObj);
        for (int i =0;  i<productsObj.size(); i++){
            if (productsObj.get(i).getId().equals(id)){
                return productsObj.get(i);
            }

        }
        return null;
    }
}
