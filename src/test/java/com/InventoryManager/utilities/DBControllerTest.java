package com.InventoryManager.utilities;

import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DBControllerTest {
    private static DBConnection dbConnection;
    private static DBController dbController;

    @BeforeAll
    static void beforeAll() {
        dbConnection = new DBConnection(
                "jdbc:h2:mem:test",
                "sa",
                "");
        dbController = new DBController(dbConnection);

        try {
            dbController.execute("CREATE TABLE providers ("
                    + "id SERIAL PRIMARY KEY, "
                    + "name VARCHAR(255) NOT NULL, "
                    + "contact VARCHAR(255) NOT NULL"
                    + ");");

            dbController.execute("CREATE TABLE purchases ("
                    + "id SERIAL PRIMARY KEY, "
                    + "provider_id INTEGER NOT NULL, "
                    + "date DATE NOT NULL, "
                    + "total_amount NUMERIC(10,2) NOT NULL, "
                    + "CONSTRAINT fk_purchases_provider FOREIGN KEY (provider_id) REFERENCES providers(id) ON DELETE CASCADE"
                    + ");");

            dbController.execute("CREATE TABLE assets ("
                    + "id SERIAL PRIMARY KEY, "
                    + "name VARCHAR(255) NOT NULL, "
                    + "description TEXT, "
                    + "series_number VARCHAR(255) UNIQUE"
                    + ");");

            dbController.execute("CREATE TABLE purchase_details ("
                    + "id SERIAL PRIMARY KEY, "
                    + "purchase_id INTEGER NOT NULL, "
                    + "asset_id INTEGER NOT NULL, "
                    + "amount INTEGER NOT NULL, "
                    + "price_per_item NUMERIC(10,2) NOT NULL, "
                    + "CONSTRAINT fk_purchase_details_purchase FOREIGN KEY (purchase_id) REFERENCES purchases(id) ON DELETE CASCADE, "
                    + "CONSTRAINT fk_purchase_details_asset FOREIGN KEY (asset_id) REFERENCES assets(id) ON DELETE CASCADE"
                    + ");");

            dbController.execute("CREATE TABLE employees ("
                    + "id SERIAL PRIMARY KEY, "
                    + "name VARCHAR(255) NOT NULL, "
                    + "mail VARCHAR(255) UNIQUE NOT NULL"
                    + ");");

            dbController.execute("CREATE TABLE users ("
                    + "id SERIAL PRIMARY KEY, "
                    + "employee_id INTEGER NOT NULL UNIQUE, "
                    + "role VARCHAR(50) NOT NULL, "
                    + "username VARCHAR(100) UNIQUE NOT NULL, "
                    + "password VARCHAR(255) NOT NULL, "
                    + "CONSTRAINT fk_users_employee FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE"
                    + ");");

            // Note: For assets_movements, although the original schema stated ON DELETE SET NULL,
            // we keep the column as NOT NULL so we use ON DELETE CASCADE here.
            dbController.execute("CREATE TABLE assets_movements ("
                    + "id SERIAL PRIMARY KEY, "
                    + "employee_id INTEGER NOT NULL, "
                    + "asset_id INTEGER NOT NULL, "
                    + "movement_type VARCHAR(50) NOT NULL CHECK (movement_type IN ('ASSIGN', 'TRANSFER', 'RETURN')), "
                    + "asset_movement_date DATE NOT NULL, "
                    + "CONSTRAINT fk_assets_movements_employee FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE, "
                    + "CONSTRAINT fk_assets_movements_asset FOREIGN KEY (asset_id) REFERENCES assets(id) ON DELETE CASCADE"
                    + ");");

            dbController.execute("CREATE TABLE computers ("
                    + "id SERIAL PRIMARY KEY, "
                    + "asset_id INTEGER NOT NULL UNIQUE, "
                    + "ram INTEGER NOT NULL, "
                    + "disk INTEGER NOT NULL, "
                    + "core VARCHAR(50) NOT NULL, "
                    + "screen_state VARCHAR(50) NOT NULL, "
                    + "keyboard_state VARCHAR(50) NOT NULL, "
                    + "shell_state VARCHAR(50) NOT NULL, "
                    + "comments TEXT, "
                    + "CONSTRAINT fk_computers_asset FOREIGN KEY (asset_id) REFERENCES assets(id) ON DELETE CASCADE"
                    + ");");

            // Preload initial data (using the same order as dependencies)
            dbController.execute("INSERT INTO providers (name, contact) VALUES "
                    + "('TechSupply', 'techsupply@example.com'), "
                    + "('OfficeWorld', 'contact@officeworld.com');");

            dbController.execute("INSERT INTO purchases (provider_id, date, total_amount) VALUES "
                    + "(1, '2025-03-01', 2000.00), "
                    + "(2, '2025-03-02', 1500.00);");

            dbController.execute("INSERT INTO assets (name, description, series_number) VALUES "
                    + "('Laptop', 'Dell XPS 13', 'SN-001'), "
                    + "('Desktop PC', 'HP Envy', 'SN-002'), "
                    + "('Monitor', 'Samsung 27 inch', 'SN-003'), "
                    + "('Printer', 'HP LaserJet', 'SN-004');");

            dbController.execute("INSERT INTO employees (name, mail) VALUES "
                    + "('John Doe', 'john@example.com'), "
                    + "('Jane Smith', 'jane@example.com');");

            dbController.execute("INSERT INTO users (employee_id, role, username, password) VALUES "
                    + "(1, 'Admin', 'john_doe', 'hashed_password_123'), "
                    + "(2, 'Employee', 'jane_smith', 'hashed_password_abc');");

            dbController.execute("INSERT INTO purchase_details (purchase_id, asset_id, amount, price_per_item) VALUES "
                    + "(1, 1, 2, 800.00), "
                    + "(1, 3, 1, 400.00), "
                    + "(2, 2, 1, 1000.00), "
                    + "(2, 4, 1, 500.00);");

            dbController.execute("INSERT INTO assets_movements (employee_id, asset_id, movement_type, asset_movement_date) VALUES "
                    + "(1, 1, 'ASSIGN', '2025-03-05'), "
                    + "(2, 2, 'ASSIGN', '2025-03-06');");

            dbController.execute("INSERT INTO computers (asset_id, ram, disk, core, screen_state, keyboard_state, shell_state, comments) VALUES "
                    + "(1, 16, 512, 'Intel i7', 'GOOD', 'GOOD', 'GOOD', 'Dell XPS laptop'), "
                    + "(2, 32, 1024, 'AMD Ryzen 7', 'GOOD', 'GOOD', 'GOOD', 'High performance desktop');");

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    static void afterAll() {
        dbConnection.close();
    }

    @Order(1)
    @Test
    void testProvidersPositiveInsertion() {
        try {
            dbController.execute("INSERT INTO providers (name, contact) VALUES (?, ?)", "NewProvider", "newprovider@example.com");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        // Check Provider
    }

    @Order(2)
    @Test
    void testProviderNegativeInsertion() {
        assertThrows(SQLException.class, () -> dbController.execute("INSERT INTO providers (name, contact) VALUES (?, ?)", "noname"));
    }

    @Order(3)
    @Test
    void testComputersNegativeInvalidAsset() {
        assertThrows(SQLException.class, () -> dbController.execute(
                "INSERT INTO computers (asset_id, ram, disk, core, screen_state, keyboard_state, shell_state, comments) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                9999, 8, 256, "Intel i3", "Good", "Good", "Good", "Invalid asset test"));
    }
}