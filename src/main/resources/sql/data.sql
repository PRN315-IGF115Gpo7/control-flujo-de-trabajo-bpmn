--CARGO
INSERT INTO cargos (id_cargo, nombre_cargo, descripcion_cargo) VALUES(100, "Administrador", "Administrador del sistema");
INSERT INTO cargos (id_cargo, nombre_cargo, descripcion_cargo) VALUES(101, "Empleado", "Persona que labora en la empresa");
INSERT INTO cargos (id_cargo, nombre_cargo, descripcion_cargo) VALUES(102, "Depto finanzas", "Persona que se encarga de revisar las solicitudes que le implique algún gasto a la empresa");
INSERT INTO cargos (id_cargo, nombre_cargo, descripcion_cargo) VALUES(103, "Gerente de finanzas", "Encargado de aprobar la salida de dinero de la empresa");

--ADMIN
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (99,0,0,'admin@gmail.com',1,0,'Enrique Menjívar','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'admin', 100);

--PARTICIPANTES
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (100,0,0,'mt16007@ues.edu.sv',1,0,'Enrique Menjívar','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'enrique', 102);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (101,0,0,'dc16009@ues.edu.sv',1,0,'Pablo Díaz','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'pablo', 102);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (102,0,0,'ap16014@ues.edu.sv',1,0,'Edwin Palacios','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'edwin', 103);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (103,0,0,'ac18107@ues.edu.sv',1,0,'Alexander Aparicio','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'alexander', 102);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (104,0,0,'be17005@ues.edu.sv',1,0,'Brandon Bolaños','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'brandon', 102);

--DUENIOS DEL PROCESO
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (105,0,0,'josue.arc@hotmail',1,0,'Alberto Rivera','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'alberto', 101);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (106,0,0,'mh18083@ues.edu.sv',1,0,'Marvin Martínez','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'marvin', 101);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (107,0,0,'ta18022@ues.edu.sv',1,0,'Armando Torres','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'armando', 101);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (108,0,0,'om18026@ues.edu.sv',1,0,'Gonzalo Ortiz','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'gonzalo', 101);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (109,0,0,'cc18117@ues.edu.sv',1,0,'José Castro','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'jose', 101);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (110,0,0,'gg18112@ues.edu.sv',1,0,'Amanda Gaitán','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'amanda', 101);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (111,0,0,'mh19028@ues.edu.sv',1,0,'Ernesto Mendoza','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'ernesto', 101);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (112,0,0,'ra17073@ues.edu.sv',1,0,'Fernando Alonzo','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'fernando', 101);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (113,0,0,'lc10015@ues.edu.sv',1,0,'Blanca Lara','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'blanca', 101);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (114,0,0,'vr16021@ues.edu.sv',1,0,'Alonso Vásquez','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'alonso', 101);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (115,0,0,'ml19017@ues.edu.sv',1,0,'Alberto Mejía','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'albertomej', 101);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (116,0,0,'ba18048@ues.edu.ev',1,0,'Samael Bermudez','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'samael', 101);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (117,0,0,'bh18014@ues.edu.sv',1,0,'Atlai Bonilla','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'atlai', 101);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (118,0,0,'lv19015@ues.edu.sv',1,0,'Brenda Laínez','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'brenda', 101);
INSERT INTO usuarios (ID_USUARIO,ACCOUNT_EXPERIRED,ACCOUNT_LOCKED,EMAIL,ENABLED,INTENTOS,NOMBRE_COMPLETO,PASSWORD,PASSWORD_EXPIRED,USERNAME, CARGO_ID) VALUES (119,0,0,'rc18118@ues.edu.sv',1,0,'Kenia Rivas','$2a$10$DO.xn3q1enS4z5XH2BHk1u/24c3R92S1Sqt/ClwxpsvvBCCh1DPu6',0,'kenia', 101);

--TIPOS DE DATO
INSERT INTO tipos_datos(tipo_dato_id, tipo_dato_nombre) VALUES(1, "String");
INSERT INTO tipos_datos(tipo_dato_id, tipo_dato_nombre) VALUES(2, "Boolean");
INSERT INTO tipos_datos(tipo_dato_id, tipo_dato_nombre) VALUES(3, "Date");
INSERT INTO tipos_datos(tipo_dato_id, tipo_dato_nombre) VALUES(4, "Double");
INSERT INTO tipos_datos(tipo_dato_id, tipo_dato_nombre) VALUES(5, "Integer");

--ROL
INSERT INTO roles(id_rol, authority) VALUES(100, "ADMIN");
INSERT INTO roles(id_rol, authority) VALUES(101, "DIAGRAMADOR");
INSERT INTO roles(id_rol, authority) VALUES(102, "PARTICIPANTE");

--USER - ROL
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(99, 100);

INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(100, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(101, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(102, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(103, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(104, 101);

INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(105, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(106, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(107, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(108, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(109, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(110, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(111, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(112, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(113, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(114, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(115, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(116, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(117, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(118, 101);
INSERT INTO usuarios_roles(id_usuario, id_rol) VALUES(119, 101);

--RECURSO
INSERT INTO recursos(id_recurso, recurso) VALUES(100, 'USUARIO');
INSERT INTO recursos(id_recurso, recurso) VALUES(101, 'CARGO');
INSERT INTO recursos(id_recurso, recurso) VALUES(102, 'PROCESO');
INSERT INTO recursos(id_recurso, recurso) VALUES(103, 'ROL');
INSERT INTO recursos(id_recurso, recurso) VALUES(104, 'RECURSO');
INSERT INTO recursos(id_recurso, recurso) VALUES(105, 'PRIVILEGIO');
INSERT INTO recursos(id_recurso, recurso) VALUES(106, 'ROLRECURSOPRIVILEGIO');

--PRIVILEGIO
INSERT INTO privilegios(id_privilegio, privilegio) VALUES(100, 'INDEX');
INSERT INTO privilegios(id_privilegio, privilegio) VALUES(101, 'EDIT');
INSERT INTO privilegios(id_privilegio, privilegio) VALUES(102, 'CREATE');
INSERT INTO privilegios(id_privilegio, privilegio) VALUES(103, 'DELETE');
INSERT INTO privilegios(id_privilegio, privilegio) VALUES(104, 'SHOW');

--ROLE - RECURSO - PRIVILEGIO PARA EL ROL ADMIN
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

----ROLE - RECURSO - PRIVILEGIO PARA EL ROL DIAGRAMADOR
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(135, 100, 102, 101);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(136, 101, 102, 101);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(137, 102, 102, 101);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(138, 103, 102, 101);
INSERT INTO roles_recursos_privilegios(id_rol_usuario_privilegio, id_privilegio, id_recurso, id_rol) VALUES(139, 104, 102, 101);

/*
INSERT INTO procesos(proceso_id, es_automatizado, proceso_activo, proceso_descripcion, proceso_nombre, usuario_id) VALUES(100, 1, 1, 'Esta es una descripción de proceso', 'Solicitud de aumento de suelto', 100);
INSERT INTO procesos(proceso_id, es_automatizado, proceso_activo, proceso_descripcion, proceso_nombre, usuario_id) VALUES(101, 1, 1, 'Esta es una descripción de proceso', 'Solicitud para revisión de nota', 100);

INSERT INTO instancias_procesos(instancia_proceso_id, finalizado, proceso_id, usuario_id) VALUES(100, 0, 100, 100);
INSERT INTO instancias_procesos(instancia_proceso_id, finalizado, proceso_id, usuario_id) VALUES(101, 0, 101, 100);
INSERT INTO instancias_procesos(instancia_proceso_id, finalizado, proceso_id, usuario_id) VALUES(102, 1, 100, 100);
INSERT INTO instancias_procesos(instancia_proceso_id, finalizado, proceso_id, usuario_id) VALUES(103, 0, 100, 100);
INSERT INTO instancias_procesos(instancia_proceso_id, finalizado, proceso_id, usuario_id) VALUES(104, 1, 101, 100);
*/

INSERT INTO tipos_elemento_bpmn(tipo_elemento_bpmn_iD, tipo_elemento_bpmn_nombre) values (100, 'bpmn:Task');
INSERT INTO tipos_elemento_bpmn(tipo_elemento_bpmn_iD, tipo_elemento_bpmn_nombre) values (101, 'bpmn:Lane');
INSERT INTO tipos_elemento_bpmn(tipo_elemento_bpmn_iD, tipo_elemento_bpmn_nombre) values (102, 'bpmn:Participant');
INSERT INTO tipos_elemento_bpmn(tipo_elemento_bpmn_iD, tipo_elemento_bpmn_nombre) values (103, 'bpmn:StartEvent');
INSERT INTO tipos_elemento_bpmn(tipo_elemento_bpmn_iD, tipo_elemento_bpmn_nombre) values (104, 'bpmn:ExclusiveGateway');
INSERT INTO tipos_elemento_bpmn(tipo_elemento_bpmn_iD, tipo_elemento_bpmn_nombre) values (105, 'bpmn:EndEvent');
