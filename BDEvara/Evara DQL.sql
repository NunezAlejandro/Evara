
--CREATE Views
USE bdEvara
GO

--Usuarios
--SELECT Userid, CONCAT_WS(' ', nombres, apellidos) AS [Nombres y apellidos], genero, edad, dni FROM dbo.Usuarios
GO

--Seleccionar usuario
--SELECT * FROM dbo.Usuarios
--WHERE dni = '21345323'
GO


--Roles
--SELECT Rolid, CONCAT_WS(' ', us.nombres, us.apellidos) AS [Nombres y apellidos], us.dni FROM dbo.Roles rl 
--INNER JOIN dbo.Usuarios us ON us.Userid = rl.Userid_fk
--INNER JOIN dbo.TipRol trl ON trl.TipRol = rl.tipRolid_fk
GO

--Seleccionar rol
--SELECT Rolid, us.dni, trl.Tiprol FROM dbo.Roles rl 
--INNER JOIN dbo.Usuarios us ON us.Userid = rl.Userid_fk
--INNER JOIN dbo.TipRol trl ON trl.TipRol = rl.tipRolid_fk
--WHERE Rolid = 10
GO

--Instituciones
--SELECT * FROM dbo.Instituciones
GO

--Buscar instittución
--SELECT * FROM dbo.Instituciones
--WHERE institucionID = 'HOLA'
GO

--Alumnos
--SELECT Alumnosid, CONCAT_WS(' ', us.nombres, us.apellidos) AS [Nombres y apellidos], ins.institucion FROM dbo.Alumnos al 
--INNER JOIN dbo.Instituciones ins ON ins.Institucionid = al.Institucionid
--INNER JOIN dbo.Usuarios us ON us.Userid = al.Userid_fk
GO

--Parametros
--SELECT Parametroid, para.parametro, para.Valmin, para.Valmax, und.metrica FROM dbo.Parametros para 
--INNER JOIN dbo.Unidadesmedida und ON und.Unidadid = para.Unidadid_fk
GO

--Unidades de medida
--SELECT * FROM dbo.Unidadesmedida
GO

--Indicadores
--SELECT med.Medicionid, CONCAT_WS(' ', us.nombres, us.apellidos) AS [Nombres y apellidos] FROM dbo.Mediciones med 
--INNER JOIN dbo.Usuarios us ON us.Userid = med.Userid_fk
--INNER JOIN dbo.Alumnos al ON al.Userid_fk = us.Userid
GO

--Mediciones
--SELECT med.Medicionid, med.fecha FROM dbo.Mediciones med 
--INNER JOIN dbo.Usuarios us ON us.Userid = med.Userid_fk
--INNER JOIN dbo.Alumnos al ON al.Userid_fk = us.Userid
--WHERE al.Userid_fk = (SELECT Userid FROM dbo.Usuarios WHERE dni = '21345325')
GO

--Cartilla
--SELECT med.Medicionid, us.dni, CONCAT_WS(' ', us.nombres, us.apellidos) AS [Nombres y apellidos], med.imc, med.peso, med.talla, med.hemoglobina, med.glucosa, med.fecha FROM dbo.Mediciones med 
--INNER JOIN dbo.Usuarios us ON us.Userid = med.Userid_fk
--INNER JOIN dbo.Alumnos al ON al.Userid_fk = us.Userid
--WHERE al.Userid_fk = (SELECT Userid FROM dbo.Usuarios WHERE dni = '21345325') AND med.fecha = '2025-06-04'
GO

--Escolares saludables
--SELECT COUNT(med.Medicionid) AS [Alumnos] FROM dbo.Mediciones med 
--INNER JOIN dbo.Usuarios us ON us.Userid = med.Userid_fk
--INNER JOIN dbo.Alumnos al ON al.Userid_fk = us.Userid
--WHERE med.imc BETWEEN (SELECT Valmin FROM dbo.Parametros WHERE Parametroid = 4) AND (SELECT Valmax FROM dbo.Parametros WHERE Parametroid = 4) AND
--med.glucosa BETWEEN (SELECT Valmin FROM dbo.Parametros WHERE Parametroid = 1) AND (SELECT Valmax FROM dbo.Parametros WHERE Parametroid = 1) AND
--((us.genero = 1 AND med.hemoglobina BETWEEN (SELECT Valmin FROM dbo.Parametros WHERE Parametroid = 2) AND (SELECT Valmax FROM dbo.Parametros WHERE Parametroid = 2)) OR
--(us.genero = 0 AND med.hemoglobina BETWEEN (SELECT Valmin FROM dbo.Parametros WHERE Parametroid = 3) AND (SELECT Valmax FROM dbo.Parametros WHERE Parametroid = 3)))
GO

--Escolares no saludables
--SELECT COUNT(med.Medicionid) AS [Alumnos] FROM dbo.Mediciones med 
--INNER JOIN dbo.Usuarios us ON us.Userid = med.Userid_fk
--INNER JOIN dbo.Alumnos al ON al.Userid_fk = us.Userid
--WHERE med.imc < (SELECT Valmin FROM dbo.Parametros WHERE Parametroid = 4) OR med.imc > (SELECT Valmax FROM dbo.Parametros WHERE Parametroid = 4) AND
--med.glucosa < (SELECT Valmin FROM dbo.Parametros WHERE Parametroid = 1) OR med.glucosa > (SELECT Valmax FROM dbo.Parametros WHERE Parametroid = 1) AND
--((us.genero = 1 AND med.hemoglobina < (SELECT Valmin FROM dbo.Parametros WHERE Parametroid = 2) OR med.hemoglobina > (SELECT Valmax FROM dbo.Parametros WHERE Parametroid = 2)) OR
--(us.genero = 0 AND med.hemoglobina < (SELECT Valmin FROM dbo.Parametros WHERE Parametroid = 3) OR med.hemoglobina > (SELECT Valmax FROM dbo.Parametros WHERE Parametroid = 3)))
GO

--Buscar alumnos
--SELECT Alumnosid, us.dni, ins.Institucionid FROM dbo.Alumnos al 
--INNER JOIN dbo.Instituciones ins ON ins.Institucionid = al.Institucionid
--INNER JOIN dbo.Usuarios us ON us.Userid = al.Userid_fk
GO

--Seleccionar usuario
--CREATE PROCEDURE usuario_sl
--@dni varchar(8), @rol INT
--AS
--	SELECT 1 FROM dbo.Usuarios us 
--	INNER JOIN dbo.Roles rl ON us.Userid = rl.Userid_fk 
--	INNER JOIN dbo.TipRol tprl ON tprl.TipRol = rl.tipRolid_fk 
--	WHERE us.dni = @DNI AND tprl.TipRol = @rol
GO

--EXEC usuario_sl @dni= '12346547', @rol = 1
GO

--Seleccionar una medicion
--SELECT med.Medicionid, us.dni, CONCAT_WS(' ', us.nombres, us.apellidos) AS [Nombres y apellidos], med.imc, med.peso, med.talla, med.hemoglobina, med.glucosa, med.fecha FROM dbo.Mediciones med 
--INNER JOIN dbo.Usuarios us ON us.Userid = med.Userid_fk
--INNER JOIN dbo.Alumnos al ON al.Userid_fk = us.Userid
--WHERE med.Medicionid = 2


SELECT us.nombres FROM dbo.Usuarios us 
	INNER JOIN dbo.Roles rl ON us.Userid = rl.Userid_fk 
	INNER JOIN dbo.TipRol tprl ON tprl.TipRol = rl.tipRolid_fk 
	WHERE us.dni = '77489602' AND tprl.TipRol = 4

SELECT * FROM dbo.Usuarios
SELECT * FROM dbo.Roles
SELECT * FROM dbo.TipRol