package com.InventoryManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Computer extends Asset {
    private Integer ram;
    private Integer disk;
    private String core;
    private String screenState;
    private String keyboardState;
    private String shellState;
    private String comments;
}
