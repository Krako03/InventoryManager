package com.InventoryManager.Services;

import com.InventoryManager.Database.DataBaseManagement;
import com.InventoryManager.Model.Product;
import com.InventoryManager.Model.ProviderClass;
import com.InventoryManager.Model.Purchase;

import java.util.List;

public class PurchaseManagement {
    private final DataBaseManagement dataBaseManagement;

    public PurchaseManagement(DataBaseManagement dataBaseManagement) {
        this.dataBaseManagement = dataBaseManagement;
    }

    public void createPurchase(Purchase purchase) {
        if(dataBaseManagement.notExists("purchase", purchase.getId())) {
            dataBaseManagement.saveData("purchase", List.of(purchase.toString()));
        }else{
            System.out.println("Error. ID already in the db");
        }

    }

    public void editPurchase(String id, Purchase newPurchase) {
        List<String> purchases =dataBaseManagement.getData("purchase");
        for (int i = 0; i < purchases.size(); i++) {
            if (purchases.get(i).contains("id=" + id + "")) {
                purchases.set(i, newPurchase.toString());
                break;
            }
        }
        dataBaseManagement.saveData("purchase", purchases);

    }

    public void deletePurchase(String id) {
        List<String> purchases=dataBaseManagement.getData("purchase");
        purchases.removeIf(purchase->purchase.contains(id));
        dataBaseManagement.saveData("purchase",purchases);
    }

    public boolean containsPurchase(String id){
        List<Purchase> purchasesObj = dataBaseManagement.getPurchases();

        for (int i =0;  i<purchasesObj.size(); i++){
            if (purchasesObj.get(i).getId().equals(id)){
                return true;
            }

        }
        return false;
    }

    public Purchase getPurchaseById(String id){
        List<Purchase> purchasesObj = dataBaseManagement.getPurchases();

        for (int i =0;  i<purchasesObj.size(); i++){
            if (purchasesObj.get(i).getId().equals(id)){
                return purchasesObj.get(i);
            }

        }
        return null;
    }
    public void addProvider(ProviderClass provider) {
        dataBaseManagement.saveData("provider", List.of(provider.toString()));
    }

    public void editProvider(String id, ProviderClass newProvider) {
        List<String> providers =dataBaseManagement.getData("provider");
        for (int i = 0; i < providers.size(); i++) {
            if (providers.get(i).contains("id=" + id)) {
                providers.set(i, newProvider.toString());
                break;
            }
        }
        dataBaseManagement.saveData("provider", providers);
    }

    public void deleteProvider(String id) {
        List<String> providers=dataBaseManagement.getData("provider");
        providers.removeIf(provider->provider.contains(id));
        dataBaseManagement.saveData("provider",providers);
    }
    public boolean containsIdProvider(String id) {
        int providerId;
        try {
            providerId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid provider id format: " + id);
            return false;
        }

        List<ProviderClass> providers = dataBaseManagement.getProviders();
        for (ProviderClass provider : providers) {
            if (provider.getId() == providerId) {
                return true;
            }
        }
        return false;
    }

    public ProviderClass getProviderById(String id) {
        int providerId;
        try {
            providerId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid provider id format: " + id);
            return null;
        }

        List<ProviderClass> providers = dataBaseManagement.getProviders();
        for (ProviderClass provider : providers) {
            if (provider.getId() == providerId) {
                return provider;
            }
        }
        return null;
    }

}
