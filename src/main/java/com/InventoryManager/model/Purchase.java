package com.InventoryManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    private Integer id;
    private Integer providerId; // FK
    private LocalDate date;
    private Double totalAmount;
    private List<PurchaseDetail> purchaseDetails;
}
