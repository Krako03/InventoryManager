package com.InventoryManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asset {
    private Integer id;
    private String name;
    private String description;
    private String seriesNumber;
    private List<AssetMovement> assetMovements;
    private Computer computer;
}
