INSERT INTO usuarios (nombre, apellidos, username, password, telefono, foto, portada, sobre_mi, email, direccion, fecha_nacimiento, isProfesional) VALUES
('Juan', 'Perez', 'juanperez', '$2a$10$2GyY1WgnSOi0TYDc3YkKPOEnT1iOtTkCQmNl9JpwdMIHWZIhgN4m6', '1234567890', 'default.jpg','default.jpg','Soy un electricista con años de experiencia en la instalación, mantenimiento y reparación de sistemas eléctricos en entornos residenciales, comerciales e industriales.', 'juan@example.com', 'Calle 123, Ciudad', '1985-05-15', TRUE),
('Maria', 'Gomez', 'mariagomez', '$2a$10$2GyY1WgnSOi0TYDc3YkKPOEnT1iOtTkCQmNl9JpwdMIHWZIhgN4m6', '0987654321', 'default.jpg', 'default.jpg', '', 'maria@example.com', 'Avenida 456, Ciudad', '1990-08-25', FALSE),
('Carlos', 'Ramirez', 'carlosramirez', '$2a$10$2GyY1WgnSOi0TYDc3YkKPOEnT1iOtTkCQmNl9JpwdMIHWZIhgN4m6', '1122334455', 'default.jpg', 'default.jpg', 'Soy un aseador profesional con años de experiencia en la limpieza y mantenimiento de diversos entornos, incluyendo oficinas, edificios residenciales, espacios comerciales y establecimientos industriales.', 'carlos@example.com', 'Boulevard 789, Ciudad', '1982-12-05', TRUE);

-- Insert services
INSERT INTO servicios (nombre, foto) VALUES
('Plomero', 'plomeria.jpg'),
('Electricista', 'electricista.jpg'),
('Limpieza', 'limpieza.jpg');

-- Insert service_profesion data
INSERT INTO servicio_profesion (idusuario, idservicio, descripcion) VALUES
(1, 1, 'Servicio de plomería para cocina, baños.'), -- Juan offers Plumbing
(1, 2, 'Servicio de electricidad para todo tipo de luces.'), -- Juan also offers Electrician services
(3, 3, 'Servicio de limpieza para apartamentos, oficinas.'); -- Carlos offers Cleaning

-- Insert requests (solicitudes)
INSERT INTO solicitudes (idservicio_profesion, idusuario, mensaje, estado, resena, estrellas) VALUES
(1, 2, 'Necesita servicio de plomería para fregadero de cocina.', 'Pendiente', NULL, NULL), -- Maria requests Plumbing
(2, 2, 'Necesito electricista para nuevas luminarias.', 'Terminada', 'Gran servicio!', 5), -- Maria requests Electrician
(3, 1, 'Necesito servicio de limpieza para mi apartamento.', 'Pendiente', NULL, NULL); -- Juan requests Cleaning