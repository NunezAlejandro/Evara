$(document).ready(function () {
    btnLogin();
});


function Logear() {
    $.ajax({
        url: 'LoginController',
        type: 'POST',
        datatype: 'JSON',
        data: {accion: 'login', dni: $('#dni').val(), rol: $('#rol').val()}
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

function btnLogin() {
    $(document).on('click', '#btnLogin', function () {
        Logear();
    });
}
