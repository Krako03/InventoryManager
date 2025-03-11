/*package com.InventoryManager.Services;

import com.InventoryManager.Model.Asset;
import com.InventoryManager.Utilities.DataBaseManagement;

import java.util.List;

public class InventoryManagement {
    private final DataBaseManagement dataBaseManagement;

    public InventoryManagement(DataBaseManagement dataBaseManagement) {
        this.dataBaseManagement=dataBaseManagement;
    }

    public void createProduct(Asset asset) {
        if(dataBaseManagement.notExists("asset", asset.getId())) {
            dataBaseManagement.saveData("asset", List.of(asset.toString()));
        } else {
            System.out.println("Error: ID already defined in the db");
        }
    }

    public void editProduct(String id, Asset newAsset) {
        List<String> products = dataBaseManagement.getData("product");
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).contains("id=" + id + "")) {
                products.set(i, newAsset.toString());
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

    public List<Asset> getProducts() {
        List<String> productStrings = dataBaseManagement.getData("product");
        return null;
    }

    public boolean containsId(String id){

        List<Asset> productsObj = dataBaseManagement.getProducts();

        for (int i =0;  i<productsObj.size(); i++){
            if (productsObj.get(i).getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public Asset getProductById(String id){
        List<Asset> productsObj = dataBaseManagement.getProducts();

        for (int i =0;  i<productsObj.size(); i++){
            if (productsObj.get(i).getId().equals(id)){
                return productsObj.get(i);
            }
        }
        return null;
    }
}*/
