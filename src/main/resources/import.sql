INSERT INTO regions(id, name) VALUES (1, 'Southamerica')
INSERT INTO regions(id, name) VALUES (2, 'Northamerica')
INSERT INTO regions(id, name) VALUES (3, 'Europe')
INSERT INTO regions(id, name) VALUES (4, 'Africa')
INSERT INTO regions(id, name) VALUES (5, 'Asia')
INSERT INTO regions(id, name) VALUES (6, 'Oceania')
INSERT INTO regions(id, name) VALUES (7, 'Antartida')

INSERT INTO clients (region_id, name, last_name, email, created_at) VALUES(1, 'Okamy', 'Ansuz', 'okamy@example.com', '2022-11-30');
INSERT INTO clients (region_id, name, last_name, email, created_at) VALUES(2, 'Burrito', 'Ansuz', 'burrito@example.com', '2022-11-12');
INSERT INTO clients (region_id, name, last_name, email, created_at) VALUES(4, 'Mitsu', 'Ansuz', 'mitsu@example.com', '2022-11-30');
INSERT INTO clients (region_id, name, last_name, email, created_at) VALUES(5, 'Nina', 'Ansuz', 'nina@example.com', '2022-11-30');
INSERT INTO clients (region_id, name, last_name, email, created_at) VALUES(3, 'Chibi', 'Ansuz', 'chibi@example.com', '2022-11-30');
INSERT INTO clients (region_id, name, last_name, email, created_at) VALUES(2, 'Manteco', 'Ansuz', 'manteco@example.com', '2022-11-30');
INSERT INTO clients (region_id, name, last_name, email, created_at) VALUES(6, 'Kira', 'Ansuz', 'kira@example.com', '2022-11-30');
INSERT INTO clients (region_id, name, last_name, email, created_at) VALUES(7, 'Mane', 'Ansuz', 'mane@example.com', '2022-11-30');
INSERT INTO clients (region_id, name, last_name, email, created_at) VALUES(1, 'Ginger', 'Ansuz', 'ginger@example.com', '2022-11-30');



INSERT INTO users (username, password, enabled) VALUES ("konejo", '', 1);
INSERT INTO users (username, password, enabled) VALUES ("blaz", '', 1);

INSERT INTO roles (name) VALUES ('ROLE_USER')
INSERT INTO roles (name) VALUES ('ROLE_ADMIN')

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1);