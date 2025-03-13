package com.InventoryManager.repositories;

import com.InventoryManager.model.Asset;
import com.InventoryManager.utilities.DBController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AssetRepository implements CrudRepository<Asset> {
    private final DBController db;

    public AssetRepository(DBController db) {
        this.db = db;
    }

    @Override
    public void save(Asset asset) {
        String query = "INSERT INTO assets (name, description, series_number) VALUES (?, ?, ?)";
        db.execute(query, asset.getName(), asset.getDescription(), asset.getSeriesNumber());
    }

    @Override
    public Optional<Asset> findById(int id) {
        String query = "SELECT * FROM assets WHERE id = ?";
        return Optional.ofNullable(db.findOne(query, this::mapResultSetToAsset, id));
    }

    @Override
    public List<Asset> findAll() {
        String query = "SELECT * FROM assets";
        return db.findMany(query, this::mapResultSetToAsset);
    }

    @Override
    public void update(Asset asset) {
        String query = "UPDATE assets SET name = ?, description = ?, series_number = ? WHERE id = ?";
        db.execute(query, asset.getName(), asset.getDescription(), asset.getSeriesNumber(), asset.getId());
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM assets WHERE id = ?";
        db.execute(query, id);
    }

    private Asset mapResultSetToAsset(ResultSet rs) {
        try {
            Asset asset = new Asset();
            asset.setId(rs.getInt("id"));
            asset.setName(rs.getString("name"));
            asset.setDescription(rs.getString("description"));
            asset.setSeriesNumber(rs.getString("series_number"));
            return asset;
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping Asset", e);
        }
    }

    // Buscar assets por nombre
    public List<Asset> findByName(String name) {
        String query = "SELECT * FROM assets WHERE name LIKE ?";
        return db.findMany(query, this::mapResultSetToAsset, "%" + name + "%");
    }

    // Buscar assets por número de serie
    public Optional<Asset> findBySeriesNumber(String seriesNumber) {
        String query = "SELECT * FROM assets WHERE series_number = ?";
        return Optional.ofNullable(db.findOne(query, this::mapResultSetToAsset, seriesNumber));
    }

    // Contar assets registrados
    public int countAssets() {
        String query = "SELECT COUNT(*) FROM assets";
        return db.findOne(query, rs -> {
            try {
                return rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException("Error counting assets", e);
            }
        });
    }
}
