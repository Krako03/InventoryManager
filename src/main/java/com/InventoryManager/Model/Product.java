package com.InventoryManager.Model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id;
    private String name;
    private String brand;
    private String serialNumber;
    private String assigmentName;
    private String location;
    private String status;
    private String comments;
}
