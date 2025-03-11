package com.InventoryManager.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetMovement {
    private Integer id;
    private Integer employeeId; // FK
    private Integer assetId; // FK
    private MovementType movementType;
    private String assetMovementDate;
}
