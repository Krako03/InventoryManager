package com.InventoryManager.Services;

import com.InventoryManager.Database.DataBaseManagement;
import com.InventoryManager.Model.User;
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
}
