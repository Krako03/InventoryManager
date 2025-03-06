-- 01-init.sql

-- Creamos la tabla "users" si no existe
CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS providers (
    provider_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    contact_info VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS products (
    product_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    stock INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS purchases (
    purchase_id SERIAL PRIMARY KEY,
    provider_id INT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    purchase_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS purchase_details (
    purchase_details_id SERIAL PRIMARY KEY,
    purchase_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS inventory_movement (
    inventory_movement_id SERIAL PRIMARY KEY,
    product_id INT NOT NULL,
    movement_type VARCHAR(50) NOT NULL,
    movement_date DATE NOT NULL,
    comments VARCHAR(255)
);

-- Claves foráneas con "IF NOT EXISTS" (sintaxis disponible desde PostgreSQL 13)
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1
    FROM pg_constraint
    WHERE conname = 'fk_purchase_provider'
  ) THEN
    ALTER TABLE purchases
      ADD CONSTRAINT fk_purchase_provider
      FOREIGN KEY (provider_id)
      REFERENCES providers (provider_id)
      ON UPDATE CASCADE
      ON DELETE RESTRICT;
  END IF;
END
$$;

DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1
    FROM pg_constraint
    WHERE conname = 'fk_purchase_details_purchase'
  ) THEN
    ALTER TABLE purchase_details
      ADD CONSTRAINT fk_purchase_details_purchase
      FOREIGN KEY (purchase_id)
      REFERENCES purchases (purchase_id)
      ON UPDATE CASCADE
      ON DELETE CASCADE;
  END IF;
END
$$;

DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1
    FROM pg_constraint
    WHERE conname = 'fk_purchase_details_product'
  ) THEN
    ALTER TABLE purchase_details
      ADD CONSTRAINT fk_purchase_details_product
      FOREIGN KEY (product_id)
      REFERENCES products (product_id)
      ON UPDATE CASCADE
      ON DELETE RESTRICT;
  END IF;
END
$$;

DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1
    FROM pg_constraint
    WHERE conname = 'fk_inventory_movement_product'
  ) THEN
    ALTER TABLE inventory_movement
      ADD CONSTRAINT fk_inventory_movement_product
      FOREIGN KEY (product_id)
      REFERENCES products (product_id)
      ON UPDATE CASCADE
      ON DELETE RESTRICT;
  END IF;
END
$$;