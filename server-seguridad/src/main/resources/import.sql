
INSERT INTO usuarios (nombre, apellido, username, password, enabled) VALUES ('admin 1', 'surname', 'admin1','$2a$10$ulMpCW3i6mhx5U4bN8JHrOnZYkdtfzRJtVulQRO1ze7pWVUXyCyLO',1);

INSERT INTO usuarios (nombre, apellido, username, password, enabled) VALUES ('evaluador 1', 'surname', 'eval1','$2a$10$0t2cCpvCKCNE3zpXlB3pIuiGVswP86XoVaq2Swb8jc6HF964xj3t2',1);

INSERT INTO usuarios (nombre, apellido, username, password, enabled) VALUES ('user 1', 'surname', 'user1','$2a$10$d.YoM4GcPSUecrQc03YazO./gMxSx4PgBujBaLet4/E7cdQ3d4vCK',1);

INSERT INTO usuarios (nombre, apellido, username, password, enabled) VALUES ('user 2', 'surname', 'user2','$2a$10$lThcWjVVOi16FYe0s47XcuDhU5cgTIy37t9JsmI6h4GQEp24g7oBm',1);







INSERT INTO `roles` (nombre) VALUES ('ROLE_EVALUADOR');

INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');







INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 2);

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1);

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (3, 3);

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (4, 3);







INSERT INTO empresas (id, nombre, direccion, telefono, cif, nombre_empleado, apellido_empleado, cargo_empleado, email_empleado) VALUES (2, 'Company 1', 'Calle Bueno N 1', 621934211, 'B2913012', 'Sergio', 'Gutierrez', 'Administrador', 'sergiogutierrez@gmail.com')

INSERT INTO empresas (id, nombre, direccion, telefono, cif, nombre_empleado, apellido_empleado, cargo_empleado, email_empleado) VALUES (3, 'Company 2', 'Calle Cal N 12', 633429032, 'C213214', 'Alvaro', 'Aranda', 'Jefe de sección', 'alvaroaranda@gmail.com')

INSERT INTO empresas (id, nombre, direccion, telefono, cif, nombre_empleado, apellido_empleado, cargo_empleado, email_empleado) VALUES (1, 'Company 3', 'Calle Ciua N 82', 623444512, 'X29301238', 'Tomas', 'Camacho', 'Administrador', 'tomascamacho@gmail.com')




INSERT INTO productos (nombre, version, empresa_id) VALUES ('Product 1','1.0', 2)
	
INSERT INTO productos (nombre, version, empresa_id) VALUES ('Product 2','1.1', 3)

INSERT INTO productos (nombre, version, empresa_id) VALUES ('Product 3','1.0', 3)




INSERT INTO versiones (nombre, producto_id) VALUES ('1.1.1', 1)

INSERT INTO versiones (nombre, producto_id) VALUES ('1.4.2', 1)

INSERT INTO versiones (nombre, producto_id) VALUES ('1.2.2', 1)

INSERT INTO versiones (nombre, producto_id) VALUES ('1.1.3', 1)

INSERT INTO versiones (nombre, producto_id) VALUES ('1.4.2', 2)

INSERT INTO versiones (nombre, producto_id) VALUES ('1.2.2', 2)

INSERT INTO versiones (nombre, producto_id) VALUES ('1.1.3', 3)





INSERT INTO macrocharacteristic (id, acronym, name, value) VALUES (1, 'CSEG', 'Ciberseguridad', 2)

INSERT INTO macrocharacteristic (id, acronym, name, value) VALUES (2, 'CSEG', 'Ciberseguridad', 5)

INSERT INTO macrocharacteristic (id, acronym, name, value) VALUES (3, 'CSEG', 'Ciberseguridad', 1)





INSERT INTO evaluaciones (create_at, macrocharacteristic_id, producto_id) VALUES (NOW(), 1, 1)

INSERT INTO evaluaciones (create_at, macrocharacteristic_id, producto_id) VALUES (NOW(), 1, 2)




INSERT INTO usuarios_evaluaciones(usuario_id, evaluacion_id) VALUES (1,1)

INSERT INTO usuarios_evaluaciones(usuario_id, evaluacion_id) VALUES (1,2)

INSERT INTO usuarios_evaluaciones(usuario_id, evaluacion_id) VALUES (2,2)

INSERT INTO usuarios_evaluaciones(usuario_id, evaluacion_id) VALUES (3,2)

INSERT INTO usuarios_evaluaciones(usuario_id, evaluacion_id) VALUES (3,1)

INSERT INTO usuarios_evaluaciones(usuario_id, evaluacion_id) VALUES (4,1)

INSERT INTO usuarios_evaluaciones(usuario_id, evaluacion_id) VALUES (4,2)





INSERT INTO charateristics (id, acronym, macrocharacteristic_id, name, value) VALUES (1, 'CUMP', 1,'Cumplimiento', 3)

INSERT INTO charateristics (id, acronym, macrocharacteristic_id, name, value) VALUES (2, 'CONF', 1, 'Confidencialidad', 4)

INSERT INTO charateristics (id, acronym, macrocharacteristic_id, name, value) VALUES (3, 'TRAZ', 1, 'Trazabilidad', 2)

INSERT INTO charateristics (id, acronym, macrocharacteristic_id, name, value) VALUES (4, 'DISP', 1, 'Disponibilidad', 2)

INSERT INTO charateristics (id, acronym, macrocharacteristic_id, name, value) VALUES (5, 'RECUP', 1, 'Recuperabilidad', 4)



INSERT INTO properties (id, acronym, name, value, charateristics_id) VALUES (1, 'CUMP_VAL', 'Cumplimiento Normativo de Valor y/o Formato', 45, 1)
INSERT INTO properties (id, acronym, name, value, charateristics_id) VALUES (2, 'CUMP_TEC', 'Cumplimiento Normativo Debido a la Tecnología', 55, 1)
INSERT INTO properties (id, acronym, name, value, charateristics_id) VALUES (3, 'ENC', 'Uso de Encriptación', 72, 2)
INSERT INTO properties (id, acronym, name, value, charateristics_id) VALUES (4, 'NO_VUL', 'No Vulnerabilidad', 79, 2)
INSERT INTO properties (id, acronym, name, value, charateristics_id) VALUES (5, 'TRAZ_DAT', 'Trazabilidad del Acceso a los Datos', 36, 3)
INSERT INTO properties (id, acronym, name, value, charateristics_id) VALUES (6, 'TRAZ_VAL', 'Trazabilidad de los Valores de Datos', 22, 3)
INSERT INTO properties (id, acronym, name, value, charateristics_id) VALUES (7, 'RAT_DISP', 'Ratio de Disponibilidad de Datos', 29, 4)
INSERT INTO properties (id, acronym, name, value, charateristics_id) VALUES (8, 'PROB_DISP', 'Probabilidad de Disponibilidad de Datos', 21, 4)
INSERT INTO properties (id, acronym, name, value, charateristics_id) VALUES (9, 'DISP_ARQ', 'Disponibilidad de Elementos de Arquitectura', 27, 4)
INSERT INTO properties (id, acronym, name, value, charateristics_id) VALUES (10, 'RAT_REC', 'Ratio de Recuperabilidad de Datos', 61, 5)
INSERT INTO properties (id, acronym, name, value, charateristics_id) VALUES (11, 'BACK', 'Backup Periódico', 55, 5)
INSERT INTO properties (id, acronym, name, value, charateristics_id) VALUES (12, 'REC_ARQ', 'Recuperabilidad de Arquitectur', 62, 5)

