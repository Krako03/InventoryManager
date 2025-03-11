package com.InventoryManager.Model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
public class Asset {
    private Integer id;
    private String name;
    private String description;
    private String seriesNumber;
    private List<AssetMovement> assetMovements;
    private Computer computer;
}
