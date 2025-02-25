package com.InventoryManager.Services;

import com.InventoryManager.Database.DataBaseManagement;
import com.InventoryManager.Model.ProviderClass;
import com.InventoryManager.Model.Purchase;

import java.util.ArrayList;
import java.util.List;

public class PurchaseManagement {
    private final DataBaseManagement dataBaseManagement;

    public PurchaseManagement(DataBaseManagement dataBaseManagement) {
        this.dataBaseManagement = dataBaseManagement;
    }

    public void createPurchase(Purchase purchase) {
        dataBaseManagement.getDataPurchases().putIfAbsent("purchase", new ArrayList<>());
        List<Purchase> purchases = dataBaseManagement.getDataPurchases().get("purchase");
        if (purchases.stream().noneMatch(p -> p.getId().equals(purchase.getId()))) {
            purchases.add(purchase);
        } else {
            System.out.println("Error. ID already in the db");
        }
    }

    public void editPurchase(String id, Purchase newPurchase) {
        List<Purchase> purchases = dataBaseManagement.getDataPurchases().getOrDefault("purchase", new ArrayList<>());
        for (int i = 0; i < purchases.size(); i++) {
            if (purchases.get(i).getId().equals(id)) {
                purchases.set(i, newPurchase);
                break;
            }
        }
    }

    public void deletePurchase(String id) {
        List<Purchase> purchases = dataBaseManagement.getDataPurchases().getOrDefault("purchase", new ArrayList<>());
        purchases.removeIf(purchase -> purchase.getId().equals(id));
    }

    public void addProvider(ProviderClass provider) {
        dataBaseManagement.getDataProviders().putIfAbsent("provider", new ArrayList<>());
        dataBaseManagement.getDataProviders().get("provider").add(provider);
    }

    public void editProvider(String id, ProviderClass newProvider) {
        List<ProviderClass> providers = dataBaseManagement.getDataProviders().getOrDefault("provider", new ArrayList<>());
        for (int i = 0; i < providers.size(); i++) {
            if (String.valueOf(providers.get(i).getId()).equals(id)) {
                providers.set(i, newProvider);
                break;
            }
        }
    }

    public void deleteProvider(String id) {
        List<ProviderClass> providers = dataBaseManagement.getDataProviders().getOrDefault("provider", new ArrayList<>());
        providers.removeIf(provider -> String.valueOf(provider.getId()).equals(id));
    }


}
