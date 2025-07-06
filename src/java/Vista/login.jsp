<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="Assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
        <link rel="icon" type="Image/Icon" href="Assets/Image/Logo.png"/>
    </head>
    <body>
        <div class="d-flex align-items-center justify-content-center vh-100">
            <div class="card" style="width: 70rem;">
                <div class="row">
                    <div class="col-6 d-flex align-items-center gap-3 justify-content-center" style="background: #004577">
                        <h2 class="text-white">EVARA</h2>
                        <img src="Assets/Image/Logo.png" alt="Logo"/>
                    </div>
                    <div class="col-6">
                        <div class="row m-3">
                            <div class="row">
                                <h2 class="text-center">LOGIN</h2>
                            </div>
                            <div class="row">
                                <div class="row">
                                    <div class="col-md-2 offset-md-3">
                                        <br>
                                        <label class="form-label">Rol :</label>
                                    </div>
                                    <div class="col-md-6">
                                        <br>
                                        <select class="form-control" id="rol" name="rol">
                                            <option value="deseleccionado" selected disabled>Selecciona una opción</option>
                                            <option value="1">Administrador</option>
                                            <option value="2">Médico</option>
                                            <option value="3">Docente</option>
                                            <option value="4">Padre/Tutor</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-2 offset-md-3">
                                        <br>
                                        <label class="form-label">DNI :</label>
                                    </div>
                                    <div class="col-md-6">
                                        <br>
                                        <input type="text" required="" class="form-control" id="dni" name="dni">
                                    </div>
                                </div>
                                <br>
                                <div class="d-grid gap-2 col-2 mx-auto p-2">
                                    <button type="submit" id="btnLogin" name="btnLogin" class="btn btn-primary fw-bold">Login</button>
                                    <a href="#" type="submit" class="btn btn-light fw-bold">Cancelar</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Javascript -->
        <script src="Assets/js/bootstrap.bundle.min.js"></script>
        <script src="Assets/js/jquery-3.7.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> 
        <script src="Assets/js/login.js"type="text/javascript"></script>
    </body>
</html>
