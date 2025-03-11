package com.InventoryManager.Model;
import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
public class PurchaseDetail {
    private Integer id;
    private Integer purchaseId; // FK
    private Integer assetId; // FK
    private Integer amount;
    private Double pricePerItem;
}
