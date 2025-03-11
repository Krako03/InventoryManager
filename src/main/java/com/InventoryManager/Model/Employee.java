package com.InventoryManager.Model;

import lombok.Data;

import java.util.List;

@Data
public class Employee {
    private Integer id;
    private String name;
    private String mail;
    private List<AssetMovement> assetMovements;
}
