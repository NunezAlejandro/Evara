$().ready(function () {
    view();
    DropDown();
    btnCrear();
    btnSeleccionar();
    btnEditar();
    btnIr();
    btnIrCartilla();
    MostrarSanos();
    MostrarNoSanos();
});
/*Acciones : */

function view() {
    $.ajax({
        url: 'indicadorController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'listar'}
    }).done(function (response) {
        Table(response);
    }).fail(function (error) {
        console.log(error);
    });
}
;
function Table(data) {
    let divUsar = $('.tabla');
    let div = '';
    div += '    <table id="tablaUsar" class="table text-center">';
    div += '                        <thead>';
    div += '                            <tr>';
    div += '                                <th scope="col" class="fw-bold"  style="background-color: #C6E7FF; color: #004577;">Item</th>';
    div += '                                <th scope="col" class="fw-bold"  style="background-color: #C6E7FF; color: #004577;">Nombres y Apellidos</th>';
    div += '                                <th scope="col" class="fw-bold"  style="background-color: #C6E7FF; color: #004577;">Mediciones</th>';
    div += '                            </tr>';
    div += '                        </thead>';
    div += '                        <tbody>';
    let item = 1;
    for (const i of data) {
        div += '                            <tr>';
        div += '                                <td>' + item + '</td>';
        div += '                                <td>' + i.nom + '</td>';
        div += '                                <td>';
        div += '                                    <button type="submit" data-id="' + i.dni + '" class="btn btnIr fw-bold" style="border-radius: 15px;width: 150px;background: #FFDDAE; color: #004577;">Entrar</button>';
        div += '                                </td>';
        div += '                            </tr>';
        item++;
    }
    div += '                        </tbody>';
    div += '                    </table>';
    divUsar.html(div);
    $('#tablaUsar').DataTable({
        language: {
            lengthMenu: "Mostrar _MENU_ registros por página",
            zeroRecords: "No se encontraron resultados",
            info: "Mostrando página _PAGE_ de _PAGES_",
            infoEmpty: "No hay registros disponibles",
            infoFiltered: "(filtrado de _MAX_ registros totales)",
            search: "Buscar:",
            paginate: {
                first: "Primero",
                last: "Último",
                next: "Siguiente",
                previous: "Anterior"
            }
        },
        paging: true,
        pageLength: 10,
        lengthMenu: [10, 25, 50, 100],
        searching: true,
        ordering: true,
        info: true
    }
    );
}

function TableHistorial(data) {
    let divUsar = $('.tabla');
    let escolares = document.querySelector(".escolares");
    let h2 = document.querySelector(".h2");
    escolares.style.display = "none";
    let nuevo = document.querySelector(".nuevo");
    nuevo.style.display = "none";
    h2.textContent = "Mediciones";
    let div = '';
    div += '<div class="row d-flex justify-content-between align-items-center mb-2">';
    div += '                <div class="col-6 mb-3">';
    div += '                    <a href="indicadoresView.jsp" class="btn btn-primary fw-bold">Volver</a>';
    div += '                </div>';
    div += '    <table id="tablaUsar" class="table text-center">';
    div += '                        <thead>';
    div += '                            <tr>';
    div += '                                <th scope="col" class="fw-bold"  style="background-color: #C6E7FF; color: #004577;">Item</th>';
    div += '                                <th scope="col" class="fw-bold"  style="background-color: #C6E7FF; color: #004577;">Fecha</th>';
    div += '                                <th scope="col" class="fw-bold"  style="background-color: #C6E7FF; color: #004577;">Acciones</th>';
    div += '                            </tr>';
    div += '                        </thead>';
    div += '                        <tbody>';
    let item = 1;
    for (const i of data) {
        div += '                            <tr>';
        div += '                                <td>' + item + '</td>';
        div += '                                <td>' + i.fecha + '</td>';
        div += '                                <td>';
        div += '                                    <button type="submit" data-dni="' + i.dni + '" data-fecha="' + i.fecha + '" class="btn btnIrCartilla btn-white"><img src="Assets/Image/eye.png" alt="alt"/></button>';
        div += '                                </td>';
        div += '                            </tr>';
        item++;
    }
    div += '                        </tbody>';
    div += '                    </table>';
    divUsar.html(div);
    $('#tablaUsar').DataTable({
        language: {
            lengthMenu: "Mostrar _MENU_ registros por página",
            zeroRecords: "No se encontraron resultados",
            info: "Mostrando página _PAGE_ de _PAGES_",
            infoEmpty: "No hay registros disponibles",
            infoFiltered: "(filtrado de _MAX_ registros totales)",
            search: "Buscar:",
            paginate: {
                first: "Primero",
                last: "Último",
                next: "Siguiente",
                previous: "Anterior"
            }
        },
        paging: true,
        pageLength: 10,
        lengthMenu: [10, 25, 50, 100],
        searching: true,
        ordering: true,
        info: true
    }
    );
}

function Cartilla(data) {
    let escolares = document.querySelector(".escolares");
    let titulo = document.querySelector(".titulo");
    escolares.style.display = "none";
    titulo.style.display = "none";
    let divUsar = $('.tabla');
    let div = '';
    div += '<div class="row d-flex justify-content-between align-items-center mb-2">';
    div += '                <div class="col-6">';
    div += '                    <a href="indicadoresView.jsp" class="btn btn-primary fw-bold">Volver</a>';
    div += '                </div>';
    for (const i of data) {
        div += '                <div class="col-6 d-flex justify-content-end align-items-center">';
        div += '                    <a type="submit" class="btn btn-white btnEditarCartilla" data-id=' + i.id + '><img src="Assets/Image/Edit.png" alt="alt"/></a>';
        div += '                </div>';
    }
    div += '            </div>';
    div += '    <div class="row">';
    for (const i of data) {
        div += '                    <p class="m-2"><strong>DNI: </strong> ' + i.dni + '</p>';
        div += '                    <p class="m-2"><strong>Nombres y apellidos: </strong>' + i.nombres + '</p>';
        div += '                </div>';
        div += '                <hr>';
        div += '                <div class="row">';
        div += '                    <div class="row">';
        div += '                        <h5 class="m-3">Mediciones</h5>';
        div += '                        <hr class="ms-3 col-3">';
        div += '                    </div>';
        div += '                    <div class="row"><h2 class="col-6 offset-6 d-flex justify-content-center">RANGO</h2></div>';
        div += '                    <div class="row"><hr class="col-4 offset-7 d-flex justify-content-center"></div>';
        div += '                    <div class="row m-3">';
        div += '                 <div class="col-5">';
        div += '                     <div class="d-flex justify-content-between">';
        div += '                         <p><strong>IMC:</strong></p>';
        div += '                         <p>' + i.imc + '</p>';
        div += '                     </div>';
        div += '                     <div class="d-flex justify-content-between">';
        div += '                         <p><strong>Peso:</strong></p>';
        div += '                         <p>' + i.peso + '</p>';
        div += '                     </div>';
        div += '                     <div class="d-flex justify-content-between">';
        div += '                         <p><strong>Talla:</strong></p>';
        div += '                         <p>' + i.talla + '</p>';
        div += '                     </div>';
        div += '                     <div class="d-flex justify-content-between">';
        div += '                         <p><strong>Hemoglobina:</strong></p>';
        div += '                         <p>' + i.hemoglobina + '</p></div>';
        div += '                     <div class="d-flex justify-content-between">';
        div += '                         <p><strong>Glucosa:</strong></p>';
        div += '                         <p>' + i.glucosa + '</p></div>';
        div += '                 </div>';
    }
    div += '                 <div class="col-7">';
    div += '                     <p class="text-center"><strong>18.5 a 21.4 IMC</strong></p>';
    div += '                     <p class="text-center"><strong>-</strong></p>';
    div += '                     <p class="text-center"><strong>-</strong></p>';
    div += '                     <p class="text-center"><strong>12.4 a 17 g/d</strong></p>';
    div += '                     <p class="text-center"><strong>4 a 4.5 millones/mm3</strong></p>';
    div += '                 </div>';
    div += '             </div>';
    div += '                </div>';
    divUsar.html(div);
}

function IrCartilla(id, fecha) {
    $.ajax({
        url: 'indicadorController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'listarCartilla', di: id, fecha: fecha}
    }).done(function (response) {
        Cartilla(response);
    }).fail(function (error) {
        console.log(error);
    });
}

function btnIrCartilla() {
    $(document).on('click', '.btnIrCartilla', function () {
        const buscar = $(this).data('dni');
        const fecha = $(this).data('fecha');
        IrCartilla(buscar, fecha);
    });
}


function IrFecha(id) {
    $.ajax({
        url: 'indicadorController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'listarFecha', di: id}
    }).done(function (response) {
        TableHistorial(response);
    }).fail(function (error) {
        console.log(error);
    });
}


function btnIr() {
    $(document).on('click', '.btnIr', function () {
        const buscar = $(this).data('id');
        IrFecha(buscar);
    });
}

function MostrarSanos() {
    $.ajax({
        url: 'indicadorController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'listarSanos'}
    }).done(function (response) {
        RegistroSanos(response);
    }).fail(function (error) {
        console.log(error);
    });
}

function RegistroSanos(data) {
    let divUsar = $('.sanos');
    let div = '';
    for (const i of data) {
        div += '<h3 class="fw-bold">' + i.cantSaludables + '</h3>';
    }
    divUsar.html(div);
}

function MostrarNoSanos() {
    $.ajax({
        url: 'indicadorController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'listarNoSanos'}
    }).done(function (response) {
        RegistroNoSanos(response);
    }).fail(function (error) {
        console.log(error);
    });
}

function RegistroNoSanos(data) {
    let divUsar = $('.nosanos');
    let div = '';
    for (const i of data) {
        div += '<h3 class="fw-bold">' + i.cantNoSaludables + '</h3>';
    }
    divUsar.html(div);
}

function DropDown(dniSeleccionado = '') {
    $.ajax({
        url: 'RolController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'dni'}
    }).done(function (response) {
        Desplegar(response, dniSeleccionado);
    }).fail(function (error) {
        console.log(error);
    });
}

function Desplegar(data, dniSeleccionado = '') {
    let divUsar = $('.dni');
    let div = '';
    div += '<select class="form-control" id="dni" name="dni">';
    div += '<option value="" disabled ' + (dniSeleccionado === '' ? 'selected' : '') + '>Selecciona una opción</option>';
    for (const i of data) {
        const selected = i.dni === dniSeleccionado ? 'selected' : '';
        div += '<option value="' + i.dni + '" ' + selected + '>' + i.nom + '</option>';
    }
    div += '</select>';
    divUsar.html(div);
}

function Crear() {
    console.log();
    $.ajax({
        url: 'indicadorController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'registrar', dni: $('#dni').val(), peso: $('#peso').val(), talla: $('#talla').val(), hemoglobina: $('#hemoglobina').val(), glucosa: $('#glucosa').val()}
    }).done(function (response) {
        if (response.estado === 'success') {
            Swal.fire({
                icon: 'success',
                title: 'Éxito',
                text: response.mensaje,
                showConfirmButton: false,
                timer: 1500
            });
            setTimeout(() => {
                window.location.href = response.redirect;
            }, 1600);
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: response.mensaje
            });
        }
    }).fail(function (error) {
        console.log(error);
    });
}
;

function Seleccionar(buscar) {
    $.ajax({
        url: 'indicadorController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'seleccionar', id: buscar}
    }).done(function (response) {
        window.location.href = 'cartillaEditar.jsp?id=' + buscar;
    }).fail(function (error) {
        console.log(error);
    });
}
;

function Editar() {
    $.ajax({
        url: 'indicadorController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'editar', dni: $('#dni').val(), peso: $('#peso').val(), talla: $('#talla').val(), hemoglobina: $('#hemoglobina').val(), glucosa: $('#glucosa').val(), id: $('#id').val()}
    }).done(function (response) {
        if (response.estado === 'success') {
            Swal.fire({
                icon: 'success',
                title: 'Éxito',
                text: response.mensaje,
                showConfirmButton: false,
                timer: 1500
            });
            setTimeout(() => {
                window.location.href = response.redirect;
            }, 1600);
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: response.mensaje
            });
        }
    }).fail(function (error) {
        console.log(error);
    });
}
;

function btnCrear() {
    $(document).on('click', '#btnCrear', function () {
        Crear();
    });
}

function btnSeleccionar() {
    $(document).on('click', '.btnEditarCartilla', function () {
        const buscar = $(this).data('id');
        Seleccionar(buscar);
    });
}

function btnEditar() {
    $(document).on('click', '#btnEditar', function () {
        Editar();
    });
}
