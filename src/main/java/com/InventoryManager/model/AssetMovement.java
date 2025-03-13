package com.InventoryManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetMovement {
    private Integer id;
    private Integer employeeId; // FK
    private Integer assetId; // FK
    private MovementType movementType;
    private LocalDate assetMovementDate;
}
