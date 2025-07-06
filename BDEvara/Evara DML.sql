USE bdEvara
GO

CREATE PROCEDURE usuarios_crud 
@option INT, @nom TEXT, @app TEXT, @gen TINYINT, @ed INT, @di varchar(8)
AS
	IF @option = 1
		BEGIN
			INSERT INTO dbo.Usuarios VALUES(@nom, @app, @gen, @ed, @di);
		END
	ELSE IF @option = 2
		BEGIN
			UPDATE dbo.Usuarios SET nombres = @nom, apellidos = @app, genero = @gen, edad = @ed, dni = @di
			WHERE Userid = (SELECT Userid FROM dbo.Usuarios WHERE dni = @di)
		END
	ELSE IF @option = 3
		BEGIN
			DELETE FROM dbo.Usuarios
			WHERE Userid = (SELECT Userid FROM dbo.Usuarios WHERE dni = @di)
		END
GO

EXEC usuarios_crud @option = 3, @nom = 'Melisa', @app = 'Milla Julca', @gen = 1, @ed = 20, @di = '90981714'
GO


CREATE PROCEDURE instituciones_crud 
@option INT, @ins TEXT, @id INT NULL
AS
	IF @option = 1
		BEGIN
			INSERT INTO dbo.Instituciones VALUES(@ins);
		END
	ELSE IF @option = 2
		BEGIN
			UPDATE dbo.Instituciones SET institucion = @ins
			WHERE Institucionid = @id
		END
	ELSE IF @option = 3
		BEGIN
			DELETE FROM dbo.Instituciones
			WHERE Institucionid = @id
		END
GO

EXEC instituciones_crud @option = 3, @ins = 'Perú', @id = 2
GO


CREATE PROCEDURE Unidadesmedida_crud 
@option INT, @metrica TEXT, @id INT NULL
AS
	IF @option = 1
		BEGIN
			INSERT INTO dbo.Unidadesmedida VALUES(@metrica);
		END
	ELSE IF @option = 2
		BEGIN
			UPDATE dbo.Unidadesmedida SET metrica = @metrica
			WHERE Unidadid = @id
		END
	ELSE IF @option = 3
		BEGIN
			DELETE FROM dbo.Unidadesmedida
			WHERE Unidadid = @id
		END
GO

EXEC Unidadesmedida_crud @option = 3, @metrica = 'g/dL', @id = 4
GO


CREATE PROCEDURE tiprol_crud 
@option INT, @rol TEXT, @id INT NULL
AS
	IF @option = 1
		BEGIN
			INSERT INTO dbo.TipRol VALUES(@rol);
		END
	ELSE IF @option = 2
		BEGIN
			UPDATE dbo.TipRol SET tip = @rol
			WHERE TipRol = @id
		END
	ELSE IF @option = 3
		BEGIN
			DELETE FROM dbo.TipRol
			WHERE TipRol = @id
		END
GO

EXEC tiprol_crud @option = 3, @rol = 'Poder', @id = 5
GO


CREATE PROCEDURE roles_crud 
@option INT, @tip INT , @di varchar(8), @id INT NULL
AS
	IF @option = 1
		BEGIN
			INSERT INTO dbo.Roles VALUES(@tip, (SELECT Userid FROM dbo.Usuarios WHERE dni = @di));
		END
	ELSE IF @option = 2
		BEGIN
			UPDATE dbo.Roles SET tipRolid_fk = @tip, Userid_fk = (SELECT Userid FROM dbo.Usuarios WHERE dni = @di)
			WHERE Rolid = @id
		END
	ELSE IF @option = 3
		BEGIN
			DELETE FROM dbo.Roles
			WHERE Rolid = @id
		END
GO

EXEC roles_crud  @option = 1, @tip = 4, @di = '90897615', @id = NULL
GO


CREATE PROCEDURE alumnos_crud 
@option INT, @di varchar(8), @ins INT, @id INT NULL
AS
	IF @option = 1
		BEGIN
			INSERT INTO dbo.Alumnos VALUES((SELECT Userid FROM dbo.Usuarios WHERE dni = @di), @ins);
		END
	ELSE IF @option = 2
		BEGIN
			UPDATE dbo.Alumnos SET Userid_fk = (SELECT Userid FROM dbo.Usuarios WHERE dni = @di), Institucionid = @ins
			WHERE Alumnosid = @id 
		END
	ELSE IF @option = 3
		BEGIN
			DELETE FROM dbo.Alumnos
			WHERE Alumnosid = @id 
		END
GO

EXEC alumnos_crud  @option = 3, @di = '213455', @ins = 1, @id = 3
GO


CREATE PROCEDURE parametros_crud 
@option INT, @min FLOAT, @max FLOAT, @medida INT,@para TEXT, @id INT NULL
AS
	IF @option = 1
		BEGIN
			INSERT INTO dbo.Parametros VALUES(@min, @max, @medida, @para);
		END
	ELSE IF @option = 2
		BEGIN
			UPDATE dbo.Parametros SET Valmin = @min, Valmax = @max, Unidadid_fk = @medida, parametro = @para
			WHERE Parametroid = @id 
		END
	ELSE IF @option = 3
		BEGIN
			DELETE FROM dbo.Parametros
			WHERE Parametroid = @id 
		END
GO

EXEC parametros_crud  @option = 2, @min = 11.5, @max = 15, @para = 'Hemoglbina mujeres', @medida = 6, @id = 3
GO


CREATE PROCEDURE mediciones_crud 
@option INT, @peso FLOAT, @talla FLOAT, @hemoglobina FLOAT, @glucosa FLOAT, @di varchar(8), @id INT NULL
AS
	IF @option = 1
		BEGIN
			INSERT INTO dbo.Mediciones (imc, peso, talla, hemoglobina, glucosa, Userid_fk) VALUES((@peso / SQUARE(@talla)), @peso, @talla, @hemoglobina, @glucosa, (SELECT Userid FROM dbo.Usuarios WHERE dni = @di));
		END
	ELSE IF @option = 2
		BEGIN
			UPDATE dbo.Mediciones SET imc = (@peso / SQUARE(@talla)), peso = @peso, talla = @talla, hemoglobina = @hemoglobina, glucosa = @glucosa, Userid_fk = (SELECT Userid FROM dbo.Usuarios WHERE dni = @di)
			WHERE Medicionid = @id 
		END
	ELSE IF @option = 3
		BEGIN
			DELETE FROM dbo.Mediciones
			WHERE Medicionid = @id
		END
GO

EXEC mediciones_crud  @option = 2, @peso = 178.8, @talla = 2.10, @hemoglobina = 19.5 , @glucosa = 50 , @di = '21345325', @id = 1
GO
