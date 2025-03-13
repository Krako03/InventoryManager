/*package com.InventoryManager.services;

import com.InventoryManager.utilities.DataBaseManagement;
import com.InventoryManager.model.User;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@RequiredArgsConstructor
@Setter
public class Login {
    private final DataBaseManagement dataBaseManagement;
    private final String username;
    private final String password;

    public boolean tryLogin() {
        Optional<User> user = dataBaseManagement.containsUser(username, password);
        return user.isPresent();
    }
}*/
