package com.InventoryManager.Database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseManagement {
    private int port;
    private String username;
    private String password;
    private final Map<String, List<String>> dataStorage = new HashMap<>();

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void saveData(String key, List<String> values){
        dataStorage.put(key,new ArrayList<>(values));
    }

    public List<String> getData(String key){
        return dataStorage.getOrDefault(key, Collections.emptyList());
    }

    public boolean executeQuery(String key){
        return dataStorage.containsKey(key);
    }

}
