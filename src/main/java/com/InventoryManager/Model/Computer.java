package com.InventoryManager.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
