insert into regiones (id, nombre) values (1, 'Sudamérica'); 
insert into regiones (id, nombre) values (2, 'Centroamérico'); 
insert into regiones (id, nombre) values (3, 'Norteamérica'); 
insert into regiones (id, nombre) values (4, 'Europa'); 
insert into regiones (id, nombre) values (5, 'Asia'); 
insert into regiones (id, nombre) values (6, 'Africa'); 
insert into regiones (id, nombre) values (7, 'Oceanía'); 
insert into regiones (id, nombre) values (8, 'Antártida'); 

insert into clientes (nombre, apellido, email, create_at, region_id) values ('Aaron', 'Isaacs', 'admin@devaaron.com', '2020-01-01', 1);
insert into clientes (nombre, apellido, email, create_at, region_id) values ('Katherine', 'Murillo', 'admin@cyso.com', '2020-01-01', 2);
insert into clientes (nombre, apellido, email, create_at, region_id) values ('Linus', 'Torvalds', 'linus.tolvards@gmail.com', '2020-01-03', 3);
insert into clientes (nombre, apellido, email, create_at, region_id) values ('Rasmus', 'Lerdorf', 'rasmus.lerdord@gmail.com', '2020-01-04', 4);
insert into clientes (nombre, apellido, email, create_at, region_id) values ('Erich', 'Gamma', 'eric.gamma@gmail.com', '2020-02-01', 5);
insert into clientes (nombre, apellido, email, create_at, region_id) values ('Richard', 'Helm', 'richard.heml@gmail.com', '2020-02-10', 6);
insert into clientes (nombre, apellido, email, create_at, region_id) values ('Ralph', 'Johnson', 'ralph.jhonson@gmail.com', '2020-02-18', 7);
insert into clientes (nombre, apellido, email, create_at, region_id) values ('John', 'Vlissides', 'jhon.vlissides@gmail.com', '2020-02-29', 8);
insert into clientes (nombre, apellido, email, create_at, region_id) values ('Dr. James', 'Gosling', 'james.gosling@gmail.com', '2020-03-03', 1);
insert into clientes (nombre, apellido, email, create_at, region_id) values ('Magma', 'Lee', 'magma.ee@gmail.com', '2020-03-04', 2);
insert into clientes (nombre, apellido, email, create_at, region_id) values ('Tornado', 'Roe', 'tornado.roe@gmail.com', '2020-03-05', 3);
insert into clientes (nombre, apellido, email, create_at, region_id) values ('Jane', 'Doe', 'jane.doe@gmail.com', '2020-03-06', 4);

insert into usuarios (username, password, enabled, nombre, apellido, email) values ('aaron', '$2a$10$li9pKHF35RFkxTQPxz.44.E79kOIy2ephjzHm/pw.2tamgdjQtKDq', 1, 'Aaron', 'Isaacs', 'aaron.isaacs.ing@gmail.com');
insert into usuarios (username, password, enabled, nombre, apellido, email) values ('admin', '$2a$10$wqAcDKT98ebJsntsmJqPluGu86fDJP55JnwYGTWU6eVKS1nBv7N7K', 1, 'System', 'Admin', 'sysadmin@aarodev.com');

insert into roles (nombre) values ('ROLE_USER');
insert into roles (nombre) values ('ROLE_ADMIN');

insert into usuarios_roles (usuario_id, rol_id) values (1, 1);
insert into usuarios_roles (usuario_id, rol_id) values (2, 2);
insert into usuarios_roles (usuario_id, rol_id) values (2, 1);