CREATE TABLE products(
   id BIGINT PRIMARY KEY,
   code VARCHAR(255) NOT NULL,
   name VARCHAR(255) NOT NULL,
   description TEXT NOT NULL,
   price INT NOT NULL,
   quantity INT NOT NULL,
   inventory_status VARCHAR(255) NOT NULL,
   category VARCHAR(255) NOT NULL,
   image VARCHAR(255) NOT NULL,
   rating INT NOT NULL
);