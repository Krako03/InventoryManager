package com.InventoryManager.Database;

import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
public class DataBaseManagement {
    private int port;
    private String username;
    private String password;
    private final Map<String, List<String>> dataStorage = new HashMap<>();

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
        System.out.println("Contenido de la base de datos:");
        dataStorage.forEach((key, value) -> System.out.println(key + " -> " + value));
    }

    public boolean notExists(String key, String id) {
        return dataStorage.getOrDefault(key, Collections.emptyList())
                .stream()
                .noneMatch(entry -> entry.contains("id='" + id + "'"));
    }

}
