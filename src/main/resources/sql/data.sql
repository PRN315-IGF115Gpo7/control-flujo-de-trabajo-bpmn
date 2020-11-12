--Cargo
INSERT INTO cargos (id_cargo, nombre_cargo, descripcion_cargo) VALUES(100, "Adminitrador", "Esta es una descripción del cargo");

INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (100,0,0,'admin@gmail.com',1,0,'Usuario administrador','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'admin', 100);

--Tipos de dato
INSERT INTO tipos_datos(tipo_dato_id, tipo_dato_nombre) VALUES(1, "String");
INSERT INTO tipos_datos(tipo_dato_id, tipo_dato_nombre) VALUES(2, "Boolean");
INSERT INTO tipos_datos(tipo_dato_id, tipo_dato_nombre) VALUES(3, "Date");
INSERT INTO tipos_datos(tipo_dato_id, tipo_dato_nombre) VALUES(4, "Double");
INSERT INTO tipos_datos(tipo_dato_id, tipo_dato_nombre) VALUES(5, "Integer");

INSERT INTO roles(id_rol, authority) VALUES(100, "admin");
INSERT INTO roles(id_rol, authority) VALUES(101, "diagramador");
INSERT INTO roles(id_rol, authority) VALUES(102, "participante");
