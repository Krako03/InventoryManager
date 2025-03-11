package com.InventoryManager.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetail {
    private Integer id;
    private Integer purchaseId; // FK
    private Integer assetId; // FK
    private Integer amount;
    private Double pricePerItem;
}
