package com.InventoryManager.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProviderClass {
    private int id;
    private String name;
    private String contactInfo;
    private String comments;
}
