-- Queries
SELECT * FROM usuarios;

SELECT * FROM servicios;

-- Profesionales y sus servicios
SELECT u.nombre AS Profesional, s.nombre AS Servicio
FROM usuarios u
JOIN servicio_profesion sp ON u.idusuario = sp.idusuario
JOIN servicios s ON sp.idservicio = s.idservicio
WHERE u.isProfesional = TRUE;

-- Todas las solicitudes y sus estados
SELECT so.idsolicitud, us.nombre as Professional, u.nombre AS Usuario, s.nombre AS Servicio, so.mensaje, so.estado
FROM solicitudes so
JOIN usuarios u ON so.idusuario = u.idusuario
JOIN servicio_profesion sp ON so.idservicio_profesion = sp.idservicio_profesion
JOIN usuarios us ON sp.idusuario = us.idusuario
JOIN servicios s ON sp.idservicio = s.idservicio;

-- Solicitudes completas con rating
SELECT so.idsolicitud, u.nombre AS Usuario, s.nombre AS Servicio, so.resena, so.estrellas
FROM solicitudes so
JOIN usuarios u ON so.idusuario = u.idusuario
JOIN servicio_profesion sp ON so.idservicio_profesion = sp.idservicio_profesion
JOIN servicios s ON sp.idservicio = s.idservicio
WHERE so.estado = 'Terminada';

-- Solicitudes pendientes
SELECT so.idsolicitud, u.nombre AS Usuario, s.nombre AS Servicio, so.mensaje
FROM solicitudes so
JOIN usuarios u ON so.idusuario = u.idusuario
JOIN servicio_profesion sp ON so.idservicio_profesion = sp.idservicio_profesion
JOIN usuarios us ON sp.idusuario = us.idusuario
JOIN servicios s ON sp.idservicio = s.idservicio
WHERE so.estado = 'Pendiente';


-- Profesionales con servicio especifico
SELECT u.nombre, u.apellidos, u.telefono, u.email
FROM usua;rios u
JOIN servicio_profesion sp ON u.idusuario = sp.idusuario
JOIN servicios s ON sp.idservicio = s.idservicio
WHERE s.nombre = 'Pendiente';
