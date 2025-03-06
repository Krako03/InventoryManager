-- Inserciones para la tabla "users" (4 registros)
INSERT INTO users (username, password, role) VALUES
    ('alex', 'i2345678', 'ADMIN'),
    ('oscar', 'oscar', 'ADMIN'),
    ('saul', 'saul', 'PURCHASE_MANAGER')
ON CONFLICT DO NOTHING;

-- Inserciones para la tabla "provider" (10 registros)
INSERT INTO providers (name, contact_info) VALUES
    ('TechSupplier', 'techsupplier@example.com'),
    ('OfficeDepot', 'officedepot@example.com'),
    ('HardwarePro', 'hardwarepro@example.com')
ON CONFLICT DO NOTHING;

-- Inserciones para la tabla "product" (10 registros)
INSERT INTO products (name, description, stock) VALUES
  ('Laptop', 'High-performance laptop', 50),
  ('Mouse', 'Wireless ergonomic mouse', 200),
  ('Monitor', '27-inch LED Monitor', 75),
  ('Keyboard', 'Mechanical keyboard', 120),
  ('Printer', 'Laser Printer', 30)
ON CONFLICT DO NOTHING;

-- Inserciones para la tabla "purchase" (10 registros)
-- Se asume que los provider_id corresponden a los insertados anteriormente (1 a 10)
INSERT INTO purchases (provider_id, total_amount, purchase_date) VALUES
  (1, 5000.00, '2024-03-01'),
  (2, 1500.00, '2024-03-02'),
  (3, 3000.00, '2024-03-03')
ON CONFLICT DO NOTHING;

-- Inserciones para la tabla "purchase_details" (10 registros)
-- Se asume que cada registro asocia la compra i con el producto i.
INSERT INTO purchase_details (purchase_id, product_id, quantity, unit_price) VALUES
  (1, 1, 10, 500.00),  -- 10 laptops
  (1, 3, 15, 200.00),  -- 15 monitors
  (2, 2, 50, 30.00),   -- 50 mouses
  (2, 4, 20, 40.00),   -- 20 keyboards
  (3, 5, 10, 300.00)   -- 10 printers
ON CONFLICT DO NOTHING;

-- Inserciones para la tabla "inventory_movement" (10 registros)
-- Cada registro se asocia a un producto (product_id 1 a 10) con un movimiento inicial de tipo 'entrada'
INSERT INTO inventory_movement (product_id, movement_type, movement_date, comments) VALUES
  (1, 'IN', '2024-03-01', 'Stock added from purchase'),
  (3, 'IN', '2024-03-01', 'Stock added from purchase'),
  (2, 'IN', '2024-03-02', 'Stock added from purchase'),
  (4, 'IN', '2024-03-02', 'Stock added from purchase'),
  (5, 'IN', '2024-03-03', 'Stock added from purchase')
ON CONFLICT DO NOTHING;
