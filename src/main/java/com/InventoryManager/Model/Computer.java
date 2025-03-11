package com.InventoryManager.Model;

import lombok.Data;

@Data
public class Computer {
    private Integer id;
    private Integer assetId; // FK
    private Integer ram;
    private Integer disk;
    private String core;
    private String screenState;
    private String keyboardState;
    private String shellState;
    private String comments;
}
