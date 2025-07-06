$().ready(function () {
    view();
    DropDown();
    btnCrear();
    btnSeleccionar();
    btnEditar();
    btnEliminar();
});
/*Acciones : */

function view() {
    $.ajax({
        url: 'RolController',
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
    div += '<table id="tablaUsar" class="table text-center">';
    div += '<thead>';
    div += '<tr>';
    div += '<th scope="col" class="fw-bold"  style="background-color: #C6E7FF; color: #004577;">Item</th>';
    div += '<th scope="col" class="fw-bold"  style="background-color: #C6E7FF; color: #004577;">Usuarios</th>';
    div += '<th scope="col" class="fw-bold"  style="background-color: #C6E7FF; color: #004577;">Acciones</th>';
    div += '</tr>';
    div += '</thead>';
    div += '<tbody>';
    let item = 1;
    for (const i of data) {
        div += '<tr>';
        div += '<td>' + item + '</td>';
        div += '<td>' + i.nombres + '</td>';
        div += '<td>';
        div += '<a class="btn btn-white btnSeleccionar" data-id="' + i.id + '"><img src="Assets/Image/Edit.png" alt="alt"/></a>';
        div += '<button type="submit" data-id="' + i.id + '" class="btn btn-white btnEliminar"><img src="Assets/Image/Trash.png" alt="alt"/></button>';
        div += '</td>';
        div += '</tr>';
        item++;
    }
    div += '</tbody>';
    div += '</table>';
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
    $.ajax({
        url: 'RolController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'registrar', dni: $('#dni').val(), rol: $('#rol').val()}
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
        url: 'RolController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'seleccionar', id: buscar}
    }).done(function (response) {
        window.location.href = 'rolEditar.jsp?id=' + buscar;
    }).fail(function (error) {
        console.log(error);
    });
}
;
function Editar() {
    $.ajax({
        url: 'RolController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'editar', dni: $('#dni').val(), rol: $('#rol').val(), id: $('#id').val()}
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
function Eliminar(id) {
    $.ajax({
        url: 'RolController',
        type: 'GET',
        datatype: 'JSON',
        data: {accion: 'eliminar', id: id}
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

/*Botones : */

function btnCrear() {
    $(document).on('click', '#btnCrear', function () {
        Crear();
        DropDown();
    });
}

function btnSeleccionar() {
    $(document).on('click', '.btnSeleccionar', function () {
        const buscar = $(this).data('id');
        Seleccionar(buscar);
    });
}

function btnEditar() {
    $(document).on('click', '#btnEditar', function () {
        Editar();
    });
}

function btnEliminar() {
    $(document).on('click', '.btnEliminar', function () {
        const id = $(this).data('id');
        Eliminar(id);
    });
}
