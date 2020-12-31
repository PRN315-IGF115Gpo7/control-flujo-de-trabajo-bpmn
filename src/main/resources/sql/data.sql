--Cargo
INSERT INTO cargos (id_cargo, nombre_cargo, descripcion_cargo) VALUES(100, "Docente", "Esta es una descripción del cargo");
INSERT INTO cargos (id_cargo, nombre_cargo, descripcion_cargo) VALUES(101, "Secretaria", "Esta es una descripción del cargo");
INSERT INTO cargos (id_cargo, nombre_cargo, descripcion_cargo) VALUES(102, "Estudiante", "Esta es una descripción del cargo");

INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (100,0,0,'mt16007@ues.edu.sv',1,0,'Usuario administrador','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'admin', 100);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (101,0,0,'dc16009@ues.edu.sv',1,0,'Usuario diagramador','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'diagramador', 101);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (102,0,0,'ap16014@ues.edu.sv',1,0,'Usuario participante','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'participante', 102);

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
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(101, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(102, 102);

INSERT INTO recursos(id_recurso, recurso) VALUES(100, 'CARGO');
INSERT INTO recursos(id_recurso, recurso) VALUES(101, 'PERMISO');
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

INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(100, 100, 100, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(101, 101, 100, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(102, 102, 100, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(103, 103, 100, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(104, 104, 100, 100);

INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(105, 100, 101, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(106, 101, 101, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(107, 102, 101, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(108, 103, 101, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(109, 104, 101, 100);

INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(110, 100, 102, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(111, 101, 102, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(112, 102, 102, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(113, 103, 102, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(114, 104, 102, 100);

INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(115, 100, 103, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(116, 101, 103, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(117, 102, 103, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(118, 103, 103, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(119, 104, 103, 100);

INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(120, 100, 104, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(121, 101, 104, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(122, 102, 104, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(123, 103, 104, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(124, 104, 104, 100);

INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(125, 100, 105, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(126, 101, 105, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(127, 102, 105, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(128, 103, 105, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(129, 104, 105, 100);

INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(130, 100, 106, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(131, 101, 106, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(132, 102, 106, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(133, 103, 106, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(134, 104, 106, 100);

INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(135, 100, 107, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(136, 101, 107, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(137, 102, 107, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(138, 103, 107, 100);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(139, 104, 107, 100);

INSERT INTO procesos(proceso_id, es_automatizado, proceso_activo, proceso_descripcion, proceso_nombre, usuario_id) VALUES(100, 1, 1, 'Esta es una descripción de proceso', 'Solicitud de aumento de suelto', 100);
INSERT INTO procesos(proceso_id, es_automatizado, proceso_activo, proceso_descripcion, proceso_nombre, usuario_id) VALUES(101, 1, 1, 'Esta es una descripción de proceso', 'Solicitud para revisión de nota', 100);

INSERT INTO instancias_procesos(instancia_proceso_id, finalizado, proceso_id, usuario_id) VALUES(100, 0, 100, 100);
INSERT INTO instancias_procesos(instancia_proceso_id, finalizado, proceso_id, usuario_id) VALUES(101, 0, 101, 100);
INSERT INTO instancias_procesos(instancia_proceso_id, finalizado, proceso_id, usuario_id) VALUES(102, 1, 100, 100);
INSERT INTO instancias_procesos(instancia_proceso_id, finalizado, proceso_id, usuario_id) VALUES(103, 0, 100, 100);
INSERT INTO instancias_procesos(instancia_proceso_id, finalizado, proceso_id, usuario_id) VALUES(104, 1, 101, 100);

INSERT INTO tipos_elemento_bpmn(tipo_elemento_bpmn_iD, tipo_elemento_bpmn_nombre) values (100, 'bpmn:Task');
INSERT INTO tipos_elemento_bpmn(tipo_elemento_bpmn_iD, tipo_elemento_bpmn_nombre) values (101, 'bpmn:Lane');
INSERT INTO tipos_elemento_bpmn(tipo_elemento_bpmn_iD, tipo_elemento_bpmn_nombre) values (102, 'bpmn:Participant');
INSERT INTO tipos_elemento_bpmn(tipo_elemento_bpmn_iD, tipo_elemento_bpmn_nombre) values (103, 'bpmn:StartEvent');
INSERT INTO tipos_elemento_bpmn(tipo_elemento_bpmn_iD, tipo_elemento_bpmn_nombre) values (104, 'bpmn:ExclusiveGateway');
INSERT INTO tipos_elemento_bpmn(tipo_elemento_bpmn_iD, tipo_elemento_bpmn_nombre) values (105, 'bpmn:EndEvent');
