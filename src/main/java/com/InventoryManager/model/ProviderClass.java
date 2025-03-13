package com.InventoryManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderClass {
    private Integer id;
    private String name;
    private String contact;
    private List<Purchase> purchases;
}
