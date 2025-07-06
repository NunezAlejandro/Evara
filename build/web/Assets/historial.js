$().ready(function () {
    view();
    btnCrear();
    btnSeleccionar();
    btnEditar();
    btnEliminar();
});


/*Acciones : */

function view() {
    $.ajax({
        url: 'historialController',
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
        div += '                                    <button type="submit" class="btn fw-bold" style="border-radius: 15px;width: 150px;background: #FFDDAE; color: #004577;">Entrar</button>';
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


function btnCrear() {
    $(document).on('click', '#btnCrear', function () {
        Crear();
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


