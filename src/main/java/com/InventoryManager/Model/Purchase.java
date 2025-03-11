package com.InventoryManager.Model;

import lombok.Data;

import java.util.List;

@Data
public class Purchase {
    private Integer id;
    private Integer providerId; // FK
    private String date;
    private Double totalAmount;
    private List<PurchaseDetail> purchaseDetails;
}
