package com.InventoryManager.Database;

import com.InventoryManager.Model.ProviderClass;
import com.InventoryManager.Model.Purchase;
import com.InventoryManager.Model.User;
import com.InventoryManager.Model.Product;
import lombok.Setter;

import java.io.File;
import java.util.*;

@Setter
public class DataBaseManagement {
    private int port;
    private String username;
    private String password;
    private final Map<String, List<String>> dataStorage = new HashMap<>();
    private final List<User> users = List.of(
            new User("saul", "12345678", "Admin"),
            new User("oscar", "87654321", "Admin"),
            new User("alex", "qwerty", "ItAdmin"));

    public void saveData(String key, List<String> values) {
        dataStorage.putIfAbsent(key, new ArrayList<>());
        List<String> storedValues = dataStorage.get(key);

        for (String newValue : values) {
            String newId = extractId(newValue);
            boolean exists = false;

            for (int i = 0; i < storedValues.size(); i++) {
                String storedValue = storedValues.get(i);
                String storedId = extractId(storedValue);
                if (newId.equals(storedId)) {
                    storedValues.set(i, newValue);
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                storedValues.add(newValue);
            }
        }
    }

    private String extractId(String data) {
        int startIndex = data.indexOf("id=") + 3;
        int endIndex = data.indexOf(",", startIndex);
        return endIndex == -1 ? data.substring(startIndex) : data.substring(startIndex, endIndex);
    }

    public List<String> getData(String key){
        return dataStorage.getOrDefault(key, Collections.emptyList());
    }

    public boolean executeQuery(String key){
        return dataStorage.containsKey(key);
    }

    public void printAllData() {
        System.out.println("Contenido de la base de datos: ");
        dataStorage.forEach((key, value) -> System.out.println(key + " -> " + value));
    }

    public boolean notExists(String key, String id) {
        return dataStorage.getOrDefault(key, Collections.emptyList())
                .stream()
                .noneMatch(entry -> entry.contains("id='" + id + "'"));
    }

    public Optional<User> containsUser(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username)
                        && user.getPassword().equals(password))
                .findAny();
    }

    public List<Product> getProducts() {
        List<String> productStrings = dataStorage.getOrDefault("product", Collections.emptyList());
        List<Product> products = new ArrayList<>();

        for (String productString : productStrings) {
            Product product = parseProduct(productString);
            if (product != null) {
                products.add(product);
            }
        }
        return products;
    }

    private Product parseProduct(String data) {
        try {
            data = data.replace("Product(", "").replace(")", "");
            String[] parts = data.split(", ");

            Map<String, String> values = new HashMap<>();

            for (String part : parts) {
                String[] keyValue = part.split("=", 2);
                if (keyValue.length == 2) {
                    values.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }

            return new Product(
                    values.getOrDefault("id", ""),
                    values.getOrDefault("name", ""),
                    values.getOrDefault("brand", ""),
                    values.getOrDefault("serialNumber", ""),
                    values.getOrDefault("assigmentName", ""),
                    values.getOrDefault("location", ""),
                    values.getOrDefault("status", ""),
                    values.getOrDefault("comments", "")
            );
        } catch (Exception e) {
            System.out.println("Error parsing product: " + data);
            return null;
        }
    }

    public List<Purchase> getPurchases() {
        List<String> purchaseStrings = dataStorage.getOrDefault("purchase", Collections.emptyList());
        List<Purchase> purchases = new ArrayList<>();

        for (String purchaseString : purchaseStrings) {
            Purchase purchase = parsePurchase(purchaseString);
            if (purchase != null) {
                purchases.add(purchase);
            }
        }
        return purchases;
    }

    private Purchase parsePurchase(String data) {
        try {
            // Limpiar la cadena de formato Purchase()
            data = data.replace("Purchase(", "").replace(")", "");
            String[] parts = data.split(", ");

            // Usar un mapa para almacenar las claves y valores
            Map<String, String> values = new HashMap<>();

            // Extraer los valores
            for (String part : parts) {
                String[] keyValue = part.split("=", 2);
                if (keyValue.length == 2) {
                    values.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }

            // Asignar los valores extraídos al objeto Purchase
            String id = values.getOrDefault("id", "");
            // En el caso de 'invoice', tendrías que gestionar el tipo File, es posible que necesites una conversión si es necesario
            File invoice = new File(values.getOrDefault("invoice", ""));
            boolean hasAppleCare = Boolean.parseBoolean(values.getOrDefault("hasAppleCare", "false"));
            String appleCareInvoice = values.getOrDefault("appleCareInvoice", "");
            String comments = values.getOrDefault("comments", "");
            String location = values.getOrDefault("location", "");
            Double priceMx = Double.parseDouble(values.getOrDefault("priceMx", "0.0"));
            Double priceUsa = Double.parseDouble(values.getOrDefault("priceUsa", "0.0"));
            Boolean deliveryStatus = Boolean.valueOf(values.getOrDefault("deliveryStatus", "false"));
            String provider = values.getOrDefault("provider", "");

            // Crear y devolver el objeto Purchase
            return new Purchase(id, invoice, hasAppleCare, appleCareInvoice, comments, location, priceMx, priceUsa, deliveryStatus, provider);
        } catch (Exception e) {
            System.out.println("Error parsing purchase: " + data);
            return null;
        }

    }

    public List<ProviderClass> getProviders() {
        List<String> providerStrings = dataStorage.getOrDefault("provider", Collections.emptyList());
        List<ProviderClass> providers = new ArrayList<>();

        for (String providerString : providerStrings) {
            ProviderClass provider = parseProvider(providerString);
            if (provider != null) {
                providers.add(provider);
            }
        }
        return providers;
    }

    private ProviderClass parseProvider(String data) {
        try {
            // Suponiendo que el formato es "ProviderClass(id=..., name=..., contactInfo=..., comments=...)"
            data = data.replace("ProviderClass(", "").replace(")", "");
            String[] parts = data.split(", ");

            Map<String, String> values = new HashMap<>();
            for (String part : parts) {
                String[] keyValue = part.split("=", 2);
                if (keyValue.length == 2) {
                    values.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }

            // Extraer y convertir los valores según el modelo de ProviderClass
            int id = Integer.parseInt(values.getOrDefault("id", "0"));
            String name = values.getOrDefault("name", "");
            String contactInfo = values.getOrDefault("contactInfo", "");
            String comments = values.getOrDefault("comments", "");

            return new ProviderClass(id, name, contactInfo, comments);
        } catch (Exception e) {
            System.out.println("Error parsing provider: " + data);
            return null;
        }
    }



}
