package com.InventoryManager.Database;

import com.InventoryManager.Model.User;
import com.InventoryManager.Model.Product;
import lombok.Setter;

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

            // Revisar si el ID ya está en la lista
            for (int i = 0; i < storedValues.size(); i++) {
                String storedValue = storedValues.get(i);
                String storedId = extractId(storedValue);
                if (newId.equals(storedId)) {
                    storedValues.set(i, newValue); // Sobrescribir el valor si ya existe el ID
                    exists = true;
                    break;
                }
            }

            // Si el ID no existe, agregar el nuevo valor
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
}
