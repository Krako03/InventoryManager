package com.InventoryManager.Database;

import com.InventoryManager.Model.User;
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

    public void saveData(String key, List<String> values){
        dataStorage.putIfAbsent(key, new ArrayList<>());
        dataStorage.get(key).addAll(values);
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
