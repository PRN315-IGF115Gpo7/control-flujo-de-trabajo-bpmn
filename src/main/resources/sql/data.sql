--Cargo
INSERT INTO cargos (id_cargo, nombre_cargo, descripcion_cargo) VALUES(100, "Adminitrador", "Esta es una descripción del cargo");

INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (100,0,0,'admin@gmail.com',1,0,'Usuario administrador','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'admin', 100);

--Tipos de dato
INSERT INTO tipos_datos(tipo_dato_id, tipo_dato_nombre) VALUES(1, "String");
INSERT INTO tipos_datos(tipo_dato_id, tipo_dato_nombre) VALUES(2, "Boolean");
INSERT INTO tipos_datos(tipo_dato_id, tipo_dato_nombre) VALUES(3, "Date");
INSERT INTO tipos_datos(tipo_dato_id, tipo_dato_nombre) VALUES(4, "Double");
INSERT INTO tipos_datos(tipo_dato_id, tipo_dato_nombre) VALUES(5, "Integer");

INSERT INTO roles(id_rol, authority) VALUES(100, "ADMIN");
INSERT INTO roles(id_rol, authority) VALUES(101, "DIAGRAMADOR");
INSERT INTO roles(id_rol, authority) VALUES(102, "PARTICIPANTE");

INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(100, 100);

INSERT INTO recursos(id_recurso, recurso) VALUES(100, 'CARGO');
INSERT INTO recursos(id_recurso, recurso) VALUES(101, 'PERMISOo');
INSERT INTO recursos(id_recurso, recurso) VALUES(102, 'PRIVILEGIO');
INSERT INTO recursos(id_recurso, recurso) VALUES(103, 'PROCESO');
INSERT INTO recursos(id_recurso, recurso) VALUES(104, 'RECURSO');
INSERT INTO recursos(id_recurso, recurso) VALUES(105, 'ROL');
INSERT INTO recursos(id_recurso, recurso) VALUES(106, 'USUARIO');
INSERT INTO recursos(id_recurso, recurso) VALUES(107, 'ROLRECURSOPRIVILEGIO');

INSERT INTO privilegios(id_privilegio, privilegio) VALUES(100, 'INDEX');
INSERT INTO privilegios(id_privilegio, privilegio) VALUES(101, 'EDIT');
INSERT INTO privilegios(id_privilegio, privilegio) VALUES(102, 'CREATE');
INSERT INTO privilegios(id_privilegio, privilegio) VALUES(103, 'DELETE');
INSERT INTO privilegios(id_privilegio, privilegio) VALUES(104, 'SHOW');

INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(100, 100, 105, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(101, 101, 105, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(102, 102, 105, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(103, 103, 105, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(104, 104, 105, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(105, 100, 107, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(106, 101, 107, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(107, 102, 107, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(108, 103, 107, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(109, 104, 107, 100);

INSERT INTO procesos(proceso_id, es_automatizado, proceso_activo, proceso_descripcion, proceso_nombre, usuario_id) VALUES(100, 1, 1, 'Esta es una descripción de proceso', 'Solicitud de aumento de suelto', 100);
INSERT INTO procesos(proceso_id, es_automatizado, proceso_activo, proceso_descripcion, proceso_nombre, usuario_id) VALUES(101, 1, 1, 'Esta es una descripción de proceso', 'Solicitud para revisión de nota', 100);

INSERT INTO instancias_procesos(instancia_proceso_id, finalizado, proceso_id, usuario_id) VALUES(100, 0, 100, 100);
INSERT INTO instancias_procesos(instancia_proceso_id, finalizado, proceso_id, usuario_id) VALUES(101, 0, 101, 100);
INSERT INTO instancias_procesos(instancia_proceso_id, finalizado, proceso_id, usuario_id) VALUES(102, 1, 100, 100);
INSERT INTO instancias_procesos(instancia_proceso_id, finalizado, proceso_id, usuario_id) VALUES(103, 0, 100, 100);
INSERT INTO instancias_procesos(instancia_proceso_id, finalizado, proceso_id, usuario_id) VALUES(104, 1, 101, 100);