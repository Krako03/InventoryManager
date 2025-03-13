package com.InventoryManager.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
