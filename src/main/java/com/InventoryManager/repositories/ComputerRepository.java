package com.InventoryManager.repositories;

import com.InventoryManager.model.Computer;
import com.InventoryManager.utilities.DataBaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ComputerRepository implements CrudRepository<Computer> {
    private final DataBaseConnection db;

    public ComputerRepository(DataBaseConnection db) {
        this.db = db;
    }

    @Override
    public void save(Computer computer) {
        // Primero insertar en assets para obtener el asset_id
        String assetQuery = "INSERT INTO assets (name, description, series_number) VALUES (?, ?, ?) RETURNING id";
        int assetId = db.findOne(assetQuery, rs -> {
            try {
                return rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, computer.getName(), computer.getDescription(), computer.getSeriesNumber());

        // Luego insertar en computers usando asset_id
        String computerQuery = "INSERT INTO computers (asset_id, ram, disk, core, screen_state, keyboard_state, shell_state, comments) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        db.execute(computerQuery, assetId, computer.getRam(), computer.getDisk(), computer.getCore(), computer.getScreenState(), computer.getKeyboardState(), computer.getShellState(), computer.getComments());
    }

    @Override
    public Optional<Computer> findById(int id) {
        String query = """
                SELECT a.id, a.name, a.description, a.series_number, 
                       c.ram, c.disk, c.core, c.screen_state, c.keyboard_state, c.shell_state, c.comments 
                FROM computers c 
                JOIN assets a ON c.asset_id = a.id 
                WHERE c.id = ?
                """;
        return Optional.ofNullable(db.findOne(query, this::mapResultSetToComputer, id));
    }

    @Override
    public List<Computer> findAll() {
        String query = """
                SELECT a.id, a.name, a.description, a.series_number, 
                       c.ram, c.disk, c.core, c.screen_state, c.keyboard_state, c.shell_state, c.comments 
                FROM computers c 
                JOIN assets a ON c.asset_id = a.id
                """;
        return db.findMany(query, this::mapResultSetToComputer);
    }

    @Override
    public void update(Computer computer) {
        // Actualizar assets
        String assetQuery = "UPDATE assets SET name = ?, description = ?, series_number = ? WHERE id = ?";
        db.execute(assetQuery, computer.getName(), computer.getDescription(), computer.getSeriesNumber(), computer.getId());

        // Actualizar computers
        String computerQuery = "UPDATE computers SET ram = ?, disk = ?, core = ?, screen_state = ?, keyboard_state = ?, shell_state = ?, comments = ? WHERE id = ?";
        db.execute(computerQuery, computer.getRam(), computer.getDisk(), computer.getCore(), computer.getScreenState(), computer.getKeyboardState(), computer.getShellState(), computer.getComments(), computer.getId());
    }

    @Override
    public void deleteById(int id) {
        // Borrar primero de computers (hijos)
        String computerQuery = "DELETE FROM computers WHERE id = ?";
        db.execute(computerQuery, id);

        // Luego de assets (padre)
        String assetQuery = "DELETE FROM assets WHERE id = ?";
        db.execute(assetQuery, id);
    }

    private Computer mapResultSetToComputer(ResultSet rs) {
        try {
            Computer computer = new Computer();
            computer.setId(rs.getInt("id"));
            computer.setName(rs.getString("name"));
            computer.setDescription(rs.getString("description"));
            computer.setSeriesNumber(rs.getString("series_number"));
            computer.setRam(rs.getInt("ram"));
            computer.setDisk(rs.getInt("disk"));
            computer.setCore(rs.getString("core"));
            computer.setScreenState(rs.getString("screen_state"));
            computer.setKeyboardState(rs.getString("keyboard_state"));
            computer.setShellState(rs.getString("shell_state"));
            computer.setComments(rs.getString("comments"));
            return computer;
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping Computer", e);
        }
    }
}
