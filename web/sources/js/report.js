/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function verReport() {
    ancho = 789;
    alto = 600;
    barra = 0;
    izquierda = (document.width) ? (document.width - ancho) / 2 : 100;
    arriba = (document.height) ? (document.height - alto) / 2 : 100;
    opciones = 'toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=' + barra + ',resizable=0,width=' + ancho + ',height=' + alto + ',left=' + izquierda + ',top=' + arriba + '';
    url = "contenido/paginas/report/imprimirEstadoCuenta.jsp";
    window.open(url, 'popUp', opciones);
}