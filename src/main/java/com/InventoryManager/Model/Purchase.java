package com.InventoryManager.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    private Integer id;
    private Integer providerId; // FK
    private String date;
    private Double totalAmount;
    private List<PurchaseDetail> purchaseDetails;
}
