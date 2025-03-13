package com.InventoryManager.repositories;

import com.InventoryManager.model.ProviderClass;
import com.InventoryManager.utilities.DataBaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProviderRepository implements CrudRepository<ProviderClass>{
    private final DataBaseConnection db;

    public ProviderRepository(DataBaseConnection db) {
        this.db = db;
    }

    @Override
    public void save(ProviderClass provider) {
        String query = "INSERT INTO providers (name, contact) VALUES (?, ?)";
        db.execute(query, provider.getName(), provider.getContact());
    }//segun eso es buena practica obtener el id despues de insertar un valor

    @Override
    public Optional<ProviderClass> findById(int id) {
        String query = "SELECT * FROM providers WHERE id = ?";
        return Optional.ofNullable(db.findOne(query, this::mapResultSetToProvider, id));
    }

    @Override
    public void update(ProviderClass provider) {
        String query = "UPDATE providers SET name = ?, contact = ? WHERE id = ?";
        db.execute(query, provider.getName(), provider.getContact(), provider.getId());
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM providers WHERE id = ?";
        db.execute(query, id);
    }

    @Override
    public List<ProviderClass> findAll() {
        String query = "SELECT * FROM providers";
        return db.findMany(query, this::mapResultSetToProvider);
    }

    private ProviderClass mapResultSetToProvider(ResultSet rs) {
        try {
            ProviderClass provider = new ProviderClass();
            provider.setId(rs.getInt("id"));
            provider.setName(rs.getString("name"));
            provider.setContact(rs.getString("contact"));
            return provider;
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping Provider", e);
        }
    }
}
