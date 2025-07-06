-- Crear BD
--USE master
GO
--CREATE DATABASE bdEvara 
--	ON (
--		NAME = Evara_data, 
--		FILENAME = 'D:\Proyectos web\3. JSP\1. Evara\BD\evara_data.mdf', 
--		SIZE = 10MB, 
--		MAXSIZE = 100MB
--		)
--	LOG ON (
--		NAME = Evara_log, 
--		FILENAME = 'D:\Proyectos web\3. JSP\1. Evara\BD\evara_log.ldf', 
--		SIZE = 5MB, 
--		MAXSIZE = 25MB
--		);
GO

--Crear tablas
USE bdEvara
GO

--CREATE TABLE Usuarios(
--	Userid INT NOT NULL PRIMARY KEY IDENTITY(1,1),
--	nombres TEXT NOT NULL,
--	apellidos TEXT NOT NULL,
--	genero TINYINT NOT NULL CONSTRAINT CHK_Genero CHECK (genero IN(0, 1)),
--	edad INT NOT NULL CONSTRAINT CHK_Edad CHECK(edad BETWEEN 0 AND 100),
--	dni varchar(8) NOT NULL UNIQUE CONSTRAINT CHK_Dni CHECK (LEN(dni) <= 8),
--);
GO

--CREATE TABLE Roles (
--	Rolid INT NOT NULL PRIMARY KEY IDENTITY(1,1),
--	tipRolid_fk INT NOT NULL FOREIGN KEY REFERENCES dbo.TipRol(TipRol),
--	Userid_fk INT NOT NULL FOREIGN KEY REFERENCES dbo.Usuarios(Userid),
--);
GO

--CREATE TABLE TipRol (
--	TipRol INT NOT NULL PRIMARY KEY IDENTITY(1,1),
--	tip varchar(50) NOT NULL
--);
GO

--CREATE TABLE Instituciones (
--	Institucionid INT NOT NULL PRIMARY KEY IDENTITY(1,1),
--	institucion TEXT NOT NULL,
--);
GO

--CREATE TABLE Unidadesmedida (
--	Unidadid INT NOT NULL PRIMARY KEY IDENTITY(1,1),
--	metrica TEXT NOT NULL,
--);
GO

--CREATE TABLE Alumnos (
--	Alumnosid INT NOT NULL PRIMARY KEY IDENTITY(1,1),
--	Userid_fk INT NOT NULL FOREIGN KEY REFERENCES dbo.Usuarios(Userid),
--	Institucionid INT NOT NULL FOREIGN KEY REFERENCES dbo.Instituciones(Institucionid)
--);
GO

--CREATE TABLE Parametros (
--	Parametroid INT NOT NULL PRIMARY KEY IDENTITY(1,1),
--	Valmin FLOAT NOT NULL,
--	Valmax FLOAT NOT NULL,
--	Unidadid_fk INT FOREIGN KEY REFERENCES dbo.Unidadesmedida(Unidadid)
--);
GO

--CREATE TABLE Mediciones (
--	Medicionid INT NOT NULL PRIMARY KEY IDENTITY(1,1),
--	imc FLOAT NULL,
--	peso FLOAT NULL,
--	talla FLOAT NULL, 
--	hemoglobina FLOAT NULL,
--	glucosa FLOAT NULL,
--	fecha DATE NOT NULL DEFAULT GETDATE(),
--	Userid_fk INT NOT NULL FOREIGN KEY REFERENCES dbo.Usuarios(Userid),
--);
GO


