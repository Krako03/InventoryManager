package com.InventoryManager.Model;

import lombok.Data;

@Data
public class AssetMovement {
    private Integer id;
    private Integer employeeId; // FK
    private Integer assetId; // FK
    private MovementType movementType;
    private String assetMovementDate;
}
