INSERT INTO providers (name, contact)
VALUES
    ('TechSupply', 'techsupply@example.com'),
    ('OfficeWorld', 'contact@officeworld.com');

INSERT INTO purchases (provider_id, date, total_amount)
VALUES
    (1, '2025-03-01', 2000.00),  -- References provider_id = 1 (TechSupply)
    (2, '2025-03-02', 1500.00);  -- References provider_id = 2 (OfficeWorld)

INSERT INTO assets (name, description, series_number)
VALUES
    ('Laptop', 'Dell XPS 13', 'SN-001'),
    ('Desktop PC', 'HP Envy', 'SN-002'),
    ('Monitor', 'Samsung 27 inch', 'SN-003'),
    ('Printer', 'HP LaserJet', 'SN-004');

INSERT INTO employees (name, mail)
VALUES
    ('John Doe', 'john@example.com'),
    ('Jane Smith', 'jane@example.com');

INSERT INTO users (employee_id, role, username, password)
VALUES
    (1, 'Admin', 'john_doe', 'hashed_password_123'),
    (2, 'Employee', 'jane_smith', 'hashed_password_abc');

INSERT INTO purchase_details (purchase_id, asset_id, amount, price_per_item)
VALUES
    -- These references must match existing purchase IDs and asset IDs
    (1, 1, 2, 800.00),   -- Purchase 1 includes 2 laptops (asset 1) @ 800 each
    (1, 3, 1, 400.00),   -- Purchase 1 includes 1 monitor (asset 3) @ 400
    (2, 2, 1, 1000.00),  -- Purchase 2 includes 1 desktop PC (asset 2) @ 1000
    (2, 4, 1, 500.00);   -- Purchase 2 includes 1 printer (asset 4) @ 500

INSERT INTO assets_movements (employee_id, asset_id, movement_type, asset_movement_date)
VALUES
    (1, 1, 'ASSIGN', '2025-03-05'),  -- John Doe (emp 1) assigned asset 1 (Laptop)
    (2, 2, 'ASSIGN', '2025-03-06');  -- Jane Smith (emp 2) assigned asset 2 (Desktop PC)

INSERT INTO computers (asset_id, ram, disk, core, screen_state, keyboard_state, shell_state, comments)
VALUES
    (1, 16, 512, 'Intel i7', 'GOOD', 'GOOD', 'GOOD', 'Dell XPS laptop'),
    (2, 32, 1024, 'AMD Ryzen 7', 'GOOD', 'GOOD', 'GOOD', 'High performance desktop');