/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var r = {'special': /[^A-Za-z1234567890'áéíóúÁÉÍÓÚàèìòùÀÈÌÒÙâêîôûÂÊÎÔÛÑñäëïöüÄËÏÖÜ\s\t]/g,
    'nombre': /[´ªº¨]|['¡¿?!1234567890]|[^A-Za-z'áéíóúÁÉÍÓÚàèìòùÀÈÌÒÙâêîôûÂÊÎÔÛÑñäëïöüÄËÏÖÜ\s\t]/g}
function valida_caracteres(o, w) {
    o.value = o.value.replace(r[w], '');
}
function revisar() {
    var x = document.getElementsByName("g-recaptcha-response")[0].valueOf();
    if (x.value.length === 0) {
        alert("Complete el Captcha");
        return false;
    }
    valida_caracteres(y, 'special');
    if (document.form.txtplaca.length > 2) {
        return true;
    } else {
        alert("Digite un numero de placa valida");
    }
    return false;
}


$(document).ready(function () {
    $('#submit').click(function (event) {
        var x = document.getElementsByName("g-recaptcha-response")[0].valueOf();
        var placa = $('#txtPlaca').val();
        if (x.value.length === 0) {
            alert("Complete el Captcha");
            return false;
        }
        valida_caracteres(y, 'special');
        if (placa > 2) {
            $.post("dispacher", {
                txtPlaca: placa,
                grecaptcharesponse: x
            },
                    function (response, status) {
                        alert(response);
                    })
                    );
        } else {
            alert("Digite un numero de placa valida");
        }
    });
});