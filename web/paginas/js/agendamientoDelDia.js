function consultar() {
    $(document).ready(function () {
        var tipoVar = $('#selectHorario').val();
        var test = $('#tst').val();
        alert(test);

        $.post(test + '/AgendamientoDelDia', {
            nombre: tipoVar
        }, function (responseText) {
            $('#tabla').html(responseText);
        });

    });
}