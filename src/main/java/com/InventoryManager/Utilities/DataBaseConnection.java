package com.InventoryManager.Utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class DataBaseConnection {
    private final DBConnection dbConnection;

    public DataBaseConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    void execute(String query, Object...args) {
        try (Connection con = dbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query: " + query, e);
        }
    }

    void execute(String query, Consumer<PreparedStatement> args) {
        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            args.accept(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query: " + query, e);
        }
    }

    <T> T findOne(String query, Function<ResultSet, T> mapper, Object...args) {
        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]); // Set query parameters dynamically
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;  // No results found
                }

                T result = mapper.apply(rs);  // Map the first result

                if (rs.next()) {
                    throw new RuntimeException("Query returned more than one result");
                }

                return result;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query: " + query, e);
        }
    }

    <T> List<T> findMany(String query, Function<ResultSet, T> mapper, Object...args) {
        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]); // Set query parameters dynamically
            }

            try (ResultSet rs = ps.executeQuery()) {
                List<T> objectList = new ArrayList<>();
                while(rs.next()) {
                    objectList.add(mapper.apply(rs));
                }

                return objectList.isEmpty() ? Collections.emptyList() : objectList;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query: " + query, e);
        }
    }
}
