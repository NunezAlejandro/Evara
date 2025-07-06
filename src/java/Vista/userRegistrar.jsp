<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Usuario</title>
        <link href="Assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css"> 
        <link rel="icon" type="Image/Icon" href="Assets/Image/Logo.png"/>
    </head>
    <body>
        <!-- Sidebar Fijo -->
        <div class="sidebar d-flex flex-column justify-content-between p-3" style="position: fixed; top: 0; left: 0; width: 280px; height: 100vh; background-color: #C6E7FF;">
            <div>
                <a href="#" class="d-flex align-items-center mb-3 link-dark text-decoration-none">
                    <img src="Assets/Image/Logo.png" alt="" width="32" height="32" class="rounded-circle me-2">
                    <span class="fs-4">Evara</span>
                </a>
                <hr>
                <ul class="nav nav-pills flex-column mb-auto">
                    <li class="row">
                        <a href="indicadoresView.jsp" class="nav-link fw-bold d-flex align-items-center" style="color: #004577;">
                            <div class="ms-2">
                                <img src="Assets/Image/indicador.png" alt="alt" />
                            </div>
                            <div>
                                <span class="d-block ms-2" style="overflow: hidden;">INDICADORES DE SALUD</span>
                            </div>
                        </a>
                    </li>
                    <li class="row">
                        <a href="parametrosView.jsp" class="nav-link fw-bold d-flex align-items-center" style="color: #004577;">
                            <div class="ms-2">
                                <img src="Assets/Image/parametro.png" alt="alt" />
                            </div>
                            <div>
                                <span class="d-block ms-2" style="overflow: hidden;">PARÁMETROS DE SALUD</span>
                            </div>
                        </a>
                    </li>
                    <li class="row">
                        <a href="alumnosView.jsp" class="nav-link fw-bold" style="color: #004577;">
                            <img class="m-2" src="Assets/Image/alumno.png" alt="alt"/>ALUMNOS</a>
                    </li>
                    <li class="row">
                        <a href="institucionesView.jsp" class="nav-link fw-bold" style="color: #004577;">
                            <img class="m-2" src="Assets/Image/institucion.png" alt="alt"/>INSTITUCIONES</a>
                    </li>
                    <li class="row">
                        <a href="rolesView.jsp" class="nav-link fw-bold" style="color: #004577;">
                            <img class="m-2" src="Assets/Image/rol.png" alt="alt"/>ROLES</a>
                    </li>
                    <li class="row">
                        <a href="userView.jsp" class="nav-link fw-bold" style="background-color: #004577; color: #FFFFFF;">
                            <img class="m-2" src="Assets/Image/usuario.png" alt="alt"/>USUARIOS</a>
                    </li>
                </ul>
            </div>
            <div>
                <hr>
                <a href="login.jsp" class="d-flex align-items-center link-dark text-decoration-none">
                    <img src="Assets/Image/Logo.png" alt="" width="32" height="32" class="rounded-circle me-2">
                    <strong>Jeanpier</strong>&nbsp;&nbsp;&nbsp;
                    <img src="Assets/Image/Logout.png" alt="" width="20" height="20">
                </a>
            </div>
        </div>

        <!-- Contenido principal desplazado -->
        <div class="content" style="margin-left: 280px; padding: 40px;">
            <div class="card" style="width: 70rem;">
                <h2 class="m-3">Registrar Usuario</h2>
                <hr>
                <div class="row m-3">
                    <div class="row">
                        <div class="col-md-2">
                            <br>
                            <label class="form-label">DNI :</label>
                        </div>
                        <div class="col-md-4">
                            <br>
                            <input type="text" required="" class="form-control" id="dni" name="dni">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2">
                            <br>
                            <label class="form-label">Nombres :</label>
                        </div>
                        <div class="col-md-4">
                            <br>
                            <input type="text" required="" class="form-control" id="nom" name="nom">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2">
                            <br>
                            <label class="form-label">Apellidos :</label>
                        </div>
                        <div class="col-md-4">
                            <br>
                            <input type="text" required="" class="form-control" id="app" name="app">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2">
                            <br>
                            <label class="form-label">Género :</label>
                        </div>
                        <div class="col-md-3">
                            <br>
                            <select class="form-control" id="gen" name="gen">
                                <option selected disabled>Selecciona una opción</option>
                                <option value="0">Masculino</option>
                                <option value="1">Femenino</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2">
                            <br>
                            <label class="form-label">Edad :</label>
                        </div>
                        <div class="col-md-4">
                            <br>
                            <input type="text" required="" class="form-control" id="edad" name="edad">
                        </div>
                    </div>
                    <br>
                    <div class="col-12 text-end">
                        <button type="submit" id="btnRegistrar" name="btnRegistrar" class="btn fw-bold" style="background: #004577; color: #fff;">Guardar</button>
                        <a href="userView.jsp" class="btn fw-bold" style="background: #D4F6FF; color: #004577;">Cancelar</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Javascript -->
        <script src="Assets/js/bootstrap.bundle.min.js"></script>
        <script src="Assets/js/jquery-3.7.1.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script> 
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> 
        <script src="Assets/js/users.js"type="text/javascript"></script>
    </body>
</html>
