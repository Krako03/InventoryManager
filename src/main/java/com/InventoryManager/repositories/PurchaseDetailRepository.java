package com.InventoryManager.repositories;

import com.InventoryManager.model.PurchaseDetail;
import com.InventoryManager.utilities.DataBaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PurchaseDetailRepository implements CrudRepository<PurchaseDetail> {
    private final DataBaseConnection db;

    public PurchaseDetailRepository(DataBaseConnection db) {
        this.db = db;
    }

    @Override
    public void save(PurchaseDetail purchaseDetail) {
        String query = "INSERT INTO purchase_details (purchase_id, asset_id, amount, price_per_item) VALUES (?, ?, ?, ?)";
        db.execute(query, purchaseDetail.getPurchaseId(), purchaseDetail.getAssetId(),
                purchaseDetail.getAmount(), purchaseDetail.getPricePerItem());
    }

    @Override
    public Optional<PurchaseDetail> findById(int id) {
        String query = "SELECT * FROM purchase_details WHERE id = ?";
        return Optional.ofNullable(db.findOne(query, this::mapResultSetToPurchaseDetail, id));
    }

    @Override
    public List<PurchaseDetail> findAll() {
        String query = "SELECT * FROM purchase_details";
        return db.findMany(query, this::mapResultSetToPurchaseDetail);
    }

    @Override
    public void update(PurchaseDetail purchaseDetail) {
        String query = "UPDATE purchase_details SET purchase_id = ?, asset_id = ?, amount = ?, price_per_item = ? WHERE id = ?";
        db.execute(query, purchaseDetail.getPurchaseId(), purchaseDetail.getAssetId(),
                purchaseDetail.getAmount(), purchaseDetail.getPricePerItem(), purchaseDetail.getId());
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM purchase_details WHERE id = ?";
        db.execute(query, id);
    }

    private PurchaseDetail mapResultSetToPurchaseDetail(ResultSet rs) {
        try {
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setId(rs.getInt("id"));
            purchaseDetail.setPurchaseId(rs.getInt("purchase_id"));
            purchaseDetail.setAssetId(rs.getInt("asset_id"));
            purchaseDetail.setAmount(rs.getInt("amount"));
            purchaseDetail.setPricePerItem(rs.getDouble("price_per_item"));
            return purchaseDetail;
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping PurchaseDetail", e);
        }
    }
}
