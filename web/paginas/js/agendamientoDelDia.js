function consultar() {
    $(document).ready(function () {
        var tipoVar = $('#selectHorario').val();
        $.post('../AgendamientoDelDia', {
            tipo: tipoVar
        }, function (responseText) {
            $('#tabla').html(responseText);
        });

    });
}

function descargarExcel() {
    $.post('../report');
}