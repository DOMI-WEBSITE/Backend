drop database if exists DOMI;
CREATE DATABASE DOMI;

USE DOMI;

CREATE TABLE IF NOT EXISTS usuarios (
    idusuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    foto VARCHAR(255),
    portada VARCHAR(100),
    sobre_mi VARCHAR(255),
    email VARCHAR(50) NOT NULL UNIQUE,
    direccion VARCHAR(100),
    fecha_nacimiento DATE,
    isProfesional BOOLEAN NOT NULL DEFAULT 0
);

-- Create 'servicios' table
CREATE TABLE IF NOT EXISTS servicios (
    idservicio INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    foto VARCHAR(255)
);

-- Create 'servicio_profesion' table
CREATE TABLE IF NOT EXISTS servicio_profesion (
	idservicio_profesion INT AUTO_INCREMENT PRIMARY KEY,
    idusuario INT,
    idservicio INT,
    descripcion VARCHAR(255),
    FOREIGN KEY (idusuario) REFERENCES usuarios(idusuario) ON DELETE CASCADE,
    FOREIGN KEY (idservicio) REFERENCES servicios(idservicio) ON DELETE CASCADE
);

-- Create 'solicitudes' table
CREATE TABLE IF NOT EXISTS solicitudes (
    idsolicitud INT AUTO_INCREMENT PRIMARY KEY,
    idservicio_profesion INT,
    idusuario INT,
    mensaje VARCHAR(255),
    estado VARCHAR(20),
    resena VARCHAR(255),
    estrellas INT,
    FOREIGN KEY (idservicio_profesion) REFERENCES servicio_profesion(idservicio_profesion) ON DELETE CASCADE,
    FOREIGN KEY (idusuario) REFERENCES usuarios(idusuario) ON DELETE CASCADE
);

-- Create 'recuperar_password' table
CREATE TABLE IF NOT EXISTS recuperar_password (
    id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    fecha_creacion DATETIME(6),
    fecha_expiracion DATETIME(6),
    token VARCHAR(255),
    idusuario INT,
    FOREIGN KEY (idusuario) REFERENCES usuarios(idusuario) ON DELETE CASCADE
);