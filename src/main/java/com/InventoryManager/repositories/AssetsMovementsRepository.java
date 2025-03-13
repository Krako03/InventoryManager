package com.InventoryManager.repositories;

import com.InventoryManager.model.AssetMovement;
import com.InventoryManager.utilities.DataBaseConnection;
import com.InventoryManager.model.MovementType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AssetsMovementsRepository implements CrudRepository<AssetMovement> {
    private final DataBaseConnection db;

    public AssetsMovementsRepository(DataBaseConnection db) {
        this.db = db;
    }

    @Override
    public void save(AssetMovement movement) {
        String query = "INSERT INTO assets_movements (employee_id, asset_id, movement_type, asset_movement_date) VALUES (?, ?, ?, ?)";
        db.execute(query, movement.getEmployeeId(), movement.getAssetId(), movement.getMovementType().name(), movement.getAssetMovementDate());
    }

    @Override
    public Optional<AssetMovement> findById(int id) {
        String query = "SELECT * FROM assets_movements WHERE id = ?";
        return Optional.ofNullable(db.findOne(query, this::mapResultSetToAssetsMovements, id));
    }

    @Override
    public List<AssetMovement> findAll() {
        String query = "SELECT * FROM assets_movements";
        return db.findMany(query, this::mapResultSetToAssetsMovements);
    }

    @Override
    public void update(AssetMovement movement) {
        String query = "UPDATE assets_movements SET employee_id = ?, asset_id = ?, movement_type = ?, asset_movement_date = ? WHERE id = ?";
        db.execute(query, movement.getEmployeeId(), movement.getAssetId(), movement.getMovementType().name(), movement.getAssetMovementDate(), movement.getId());
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM assets_movements WHERE id = ?";
        db.execute(query, id);
    }

    public List<AssetMovement> findByAssetId(int assetId) {
        String query = "SELECT * FROM assets_movements WHERE asset_id = ?";
        return db.findMany(query, this::mapResultSetToAssetsMovements, assetId);
    }

    public List<AssetMovement> findByEmployeeId(int employeeId) {
        String query = "SELECT * FROM assets_movements WHERE employee_id = ?";
        return db.findMany(query, this::mapResultSetToAssetsMovements, employeeId);
    }

    public int countMovements() {
        String query = "SELECT COUNT(*) FROM assets_movements";
        return db.findOne(query, rs -> {
            try {
                return rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException("Error counting movements", e);
            }
        });
    }

    private AssetMovement mapResultSetToAssetsMovements(ResultSet rs) {
        try {
            return new AssetMovement(
                    rs.getInt("id"),
                    rs.getInt("employee_id"),
                    rs.getInt("asset_id"),
                    MovementType.valueOf(rs.getString("movement_type")),
                    rs.getDate("asset_movement_date").toLocalDate()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping AssetsMovements", e);
        }
    }
}
