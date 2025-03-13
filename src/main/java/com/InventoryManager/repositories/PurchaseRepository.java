package com.InventoryManager.repositories;

import com.InventoryManager.model.Purchase;
import com.InventoryManager.utilities.DataBaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PurchaseRepository implements CrudRepository<Purchase> {
    private final DataBaseConnection db;

    public PurchaseRepository(DataBaseConnection db) {
        this.db = db;
    }

    @Override
    public void save(Purchase purchase) {
        String query = "INSERT INTO purchases (provider_id, date, total_amount) VALUES (?, ?, ?)";
        db.execute(query, purchase.getProviderId(), java.sql.Date.valueOf(purchase.getDate()), purchase.getTotalAmount());
    }

    @Override
    public Optional<Purchase> findById(int id) {
        String query = "SELECT * FROM purchases WHERE id = ?";
        return Optional.ofNullable(db.findOne(query, this::mapResultSetToPurchase, id));
    }

    @Override
    public List<Purchase> findAll() {
        String query = "SELECT * FROM purchases";
        return db.findMany(query, this::mapResultSetToPurchase);
    }

    @Override
    public void update(Purchase purchase) {
        String query = "UPDATE purchases SET provider_id = ?, date = ?, total_amount = ? WHERE id = ?";
        db.execute(query, purchase.getProviderId(), java.sql.Date.valueOf(purchase.getDate()), purchase.getTotalAmount(), purchase.getId());
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM purchases WHERE id = ?";
        db.execute(query, id);
    }

    private Purchase mapResultSetToPurchase(ResultSet rs) {
        try {
            Purchase purchase = new Purchase();
            purchase.setId(rs.getInt("id"));
            purchase.setProviderId(rs.getInt("provider_id"));
            purchase.setDate(rs.getDate("date").toLocalDate());
            purchase.setTotalAmount(rs.getDouble("total_amount"));
            return purchase;
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping Purchase", e);
        }
    }
}
