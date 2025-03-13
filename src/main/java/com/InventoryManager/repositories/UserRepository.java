package com.InventoryManager.repositories;

import com.InventoryManager.model.Role;
import com.InventoryManager.model.User;
import com.InventoryManager.utilities.DBController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepository implements CrudRepository<User> {
    private final DBController db;

    public UserRepository(DBController db) {
        this.db = db;
    }

    @Override
    public void save(User user) {
        String query = "INSERT INTO users (employee_id, role, username, password) VALUES (?, ?, ?, ?)";
        db.execute(query, user.getEmployeeId(), user.getRole().name(), user.getUsername(), user.getPassword());
    }

    @Override
    public Optional<User> findById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        return Optional.ofNullable(db.findOne(query, this::mapResultSetToUser, id));
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM users";
        return db.findMany(query, this::mapResultSetToUser);
    }

    @Override
    public void update(User user) {
        String query = "UPDATE users SET employee_id = ?, role = ?, username = ?, password = ? WHERE id = ?";
        db.execute(query, user.getEmployeeId(), user.getRole().name(), user.getUsername(), user.getPassword(), user.getId());
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        db.execute(query, id);
    }

    private User mapResultSetToUser(ResultSet rs) {
        try {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setEmployeeId(rs.getInt("employee_id"));
            user.setRole(Role.valueOf(rs.getString("role").toUpperCase()));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping User", e);
        }
    }
}
