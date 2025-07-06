$(document).ready(function () {
    btnCrear();
    btnEliminar();
    btnEditar();
    btnSeleccionar();
    view();
});

function view() {
    $.ajax({
        url: 'UserController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'listar'}
    }).done(function (response) {
        Table(response);
    }).fail(function (error) {
        console.log(error);
    });
}

function Seleccionar(dni) {
    $.ajax({
        url: 'UserController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'seleccionar', dni: dni}
    }).done(function (response) {
        window.location.href = 'userEditar.jsp?dni=' + dni;
    }).fail(function (error) {
        console.log(error);
    });
}

function btnSeleccionar() {
    $(document).on('click', '#btnSeleccionar', function () {
        const dni = $(this).data('dni');
        Seleccionar(dni);
    });
}

function Table(data) {
    const divUser = $('.table-users');
    let div = '';
    div += '<table id="tablaUsuarios" class="table text-center">';
    div += '<thead>';
    div += '<tr>';
    div += '<th scope="col" class="fw-bold"  style="background-color: #C6E7FF; color: #004577;">Item</th>';
    div += '<th scope="col" class="fw-bold"  style="background-color: #C6E7FF; color: #004577;">Nombres y Apellidos</th>';
    div += '<th scope="col" class="fw-bold"  style="background-color: #C6E7FF; color: #004577;">Género</th>';
    div += '<th scope="col" class="fw-bold"  style="background-color: #C6E7FF; color: #004577;">Edad</th>';
    div += '<th scope="col" class="fw-bold"  style="background-color: #C6E7FF; color: #004577;">DNI</th>';
    div += '<th scope="col" class="fw-bold"  style="background-color: #C6E7FF; color: #004577;">Acciones</th>';
    div += '</tr>';
    div += '</thead>';
    div += '<tbody>';
    let item = 1;
    for (const i of data) {
        div += '<tr>';
        div += '<td>' + item + '</td>';
        div += '<td>' + i.nom + '</td>';
        div += '<td>' + i.gen + '</td>';
        div += '<td>' + i.edad + '</td>';
        div += '<td>' + i.dni + '</td>';
        div += '<td>';
        div += '<a class="btn btn-white" id="btnSeleccionar" data-dni="' + i.dni + '"><img src="Assets/Image/Edit.png" alt="alt"/></a>';
        div += '<button type="submit" id="btnEliminar" class="btn btn-white"><img src="Assets/Image/Trash.png" alt="alt"/></button>';
        div += '</td>';
        div += '<input type="hidden" id="dni" value="' + i.dni + '">';
        div += '</tr>';
        item++;
    }
    div += '</tbody>';
    div += '</table>';
    divUser.html(div);

    $('#tablaUsuarios').DataTable({
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

function Crear() {
    $.ajax({
        url: 'UserController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'registrar', dni: $('#dni').val(), nom: $('#nom').val(), app: $('#app').val(), gen: $('#gen').val(), edad: $('#edad').val()}
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

function btnCrear() {
    $(document).on('click', '#btnRegistrar', function () {
        Crear();
    });
}

function Eliminar() {
    $.ajax({
        url: 'UserController',
        type: 'GET',
        datatype: 'JSON',
        data: {accion: 'eliminar', dni: $('#dni').val()}
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

function btnEliminar() {
    $(document).on('click', '#btnEliminar', function () {
        Eliminar();
    });
}


function Editar() {
    $.ajax({
        url: 'UserController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'editar', dni: $('#dni').val(), nom: $('#nom').val(), app: $('#app').val(), gen: $('#gen').val(), edad: $('#edad').val()}
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

function btnEditar() {
    $(document).on('click', '#btnEditar', function () {
        Editar();
    });
}