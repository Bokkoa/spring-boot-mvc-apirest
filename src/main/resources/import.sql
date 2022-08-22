INSERT INTO regions(id, name) VALUES (1, "Southamerica");
INSERT INTO regions(id, name) VALUES (2, "Northamerica");
INSERT INTO regions(id, name) VALUES (3, "Europe");
INSERT INTO regions(id, name) VALUES (4, "Africa");
INSERT INTO regions(id, name) VALUES (5, "Asia");
INSERT INTO regions(id, name) VALUES (6, "Oceania");
INSERT INTO regions(id, name) VALUES (7, "Antartida");

INSERT INTO clients (id, region_id, name, last_name, email, created_at) VALUES(1, 1, 'Okamy', 'Ansuz', 'okamy@example.com', '2022-11-30');
INSERT INTO clients (id, region_id, name, last_name, email, created_at) VALUES(2, 2, 'Burrito', 'Ansuz', 'burrito@example.com', '2022-11-12');
INSERT INTO clients (id, region_id, name, last_name, email, created_at) VALUES(3, 4, 'Mitsu', 'Ansuz', 'mitsu@example.com', '2022-11-30');
INSERT INTO clients (id, region_id, name, last_name, email, created_at) VALUES(4, 5, 'Nina', 'Ansuz', 'nina@example.com', '2022-11-30');
INSERT INTO clients (id, region_id, name, last_name, email, created_at) VALUES(5, 3, 'Chibi', 'Ansuz', 'chibi@example.com', '2022-11-30');
INSERT INTO clients (id, region_id, name, last_name, email, created_at) VALUES(6, 2, 'Manteco', 'Ansuz', 'manteco@example.com', '2022-11-30');
INSERT INTO clients (id, region_id, name, last_name, email, created_at) VALUES(7, 6, 'Kira', 'Ansuz', 'kira@example.com', '2022-11-30');
INSERT INTO clients (id, region_id, name, last_name, email, created_at) VALUES(8, 7, 'Mane', 'Ansuz', 'mane@example.com', '2022-11-30');
INSERT INTO clients (id, region_id, name, last_name, email, created_at) VALUES(9, 1, 'Ginger', 'Ansuz', 'ginger@example.com', '2022-11-30');


INSERT INTO users (username, password, enabled, name, last_name, email) VALUES ("konejo", "", 1, "Ansuz", "Berkana", "ansuz@berkana.com");
INSERT INTO users (username, password, enabled, name, last_name, email) VALUES ("blaz", "", 1, "Ansuz", "Berkana", "ansuz@berkana.es");

INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);


INSERT INTO products (name, price, created_at) VALUES("Platano chiapas", 20000, NOW());
INSERT INTO products (name, price, created_at) VALUES("Platano macho", 231231, NOW());
INSERT INTO products (name, price, created_at) VALUES("Manzana verde", 20342000, NOW());
INSERT INTO products (name, price, created_at) VALUES("Manzana roja", 43234, NOW());


INSERT INTO bills (description, observation, client_id, created_at) VALUES("Frutas", null, 1, NOW())
INSERT INTO bill_items (quantity, bill_id, product_id) VALUES(1, 1, 1);
INSERT INTO bill_items (quantity, bill_id, product_id) VALUES(2, 1, 3);
INSERT INTO bill_items (quantity, bill_id, product_id) VALUES(1, 1, 2);
INSERT INTO bill_items (quantity, bill_id, product_id) VALUES(1, 1, 4);

INSERT INTO bills (description, observation, client_id, created_at) VALUES("Frutas 2", "nota importante", 1, NOW())
INSERT INTO bill_items (quantity, bill_id, product_id) VALUES(1, 1, 1);
INSERT INTO bill_items (quantity, bill_id, product_id) VALUES(2, 1, 4);
INSERT INTO bill_items (quantity, bill_id, product_id) VALUES(1, 1, 3);
INSERT INTO bill_items (quantity, bill_id, product_id) VALUES(1, 1, 2);