package com.InventoryManager.Model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
public class User {
    private Integer id;
    private Integer employeeId; // FK
    private Role role;
    private String username;
    private String password;
}
