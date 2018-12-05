var app = new Vue({
    el: "#app",
    data: {
        items: [],
        selected: [],
        selectAll: false,
        detcur: {
            numero_documento: '',
            nombres_persona: '',
            apellido_persona: '',
            id_detalle_curso: ''


        }
    },
    methods: {
        select: function () {
            this.selected = [];
            if (!this.selectAll) {
                for (let i in this.items) {
                    this.selected.push(this.items[i]);
                }
            }
        },

        consultarCurso: function () {
            var tipoDoc = document.getElementById("cmbTipoDocumento").value;
            var Doc = document.getElementById("txtDocumento").value;

            if (tipoDoc.value === 0) {
                alert("Seleccione tipo Documento");
                return;
            }

            if (Doc.length === 0) {
                alert("Digite documento");
                return;
            }

            axios.get("<%= request.getContextPath()%>/reasignacionCur", {params: {documento: Doc, tipoDoc: tipoDoc, action: "consultarPersonasCursos"}}).
                    then(response => {
                        if (response.data.length > 0) {
                            this.items = [];
                            for (var i = 0; response.data.length; i++) {
                                this.detcur = {};
                                this.detcur.numero_documento = response.data[i].per_documeto;
                                this.detcur.id_detalle_curso = response.data[i].drc_id;
                                this.detcur.nombres_persona = response.data[i].per_nombres;
                                this.detcur.apellido_persona = response.data[i].per_apellidos;
                                this.detcur.inf_numero = response.data[i].inf_numero;
                                this.detcur.inf_codigo = response.data[i].inf_codigo;
                                this.detcur.dcr_fecha = response.data[i].dcr_fecha;
                                this.items.push(this.detcur);
                            }

                        } else {
                            this.items = [];
                            alert("La persona no tiene cursos pendientes");
                        }

                    }).catch(error => {
                alert("Ups algo salio mal... \n intente nuevamente ");
                console.log(error);
            });
        },

        guardarAsistencias: function () {

            var data = this.selected;
            var tipo_curso = document.getElementById("tipoCurso").value;
            var fecha = document.getElementById("txtFechaFac").value;

            if (fecha.length < 10) {
                alert("Digite fecha validad");
                return;
            }
            if (tipo_curso.value === 0) {
                alert("Seleccione Curso");
                return;
            }

            if (data.length > 0) {
                axios.get("<%= request.getContextPath()%>/historicoCert", {params: {data: JSON.stringify(data), tipo_curso: tipo_curso, fecha_curso: fecha, action: "ReasigarCurso"}}).
                        then(response => {

                            alert(response);

                        }).catch(error => {
                });

            } else {
                alert("debe elegir al menos un curso");
            }

        },

        report: function () {
            window.open('<%= request.getContextPath()%>/asistencia/reporte.jsp', 'mywindow', 'resizable=no,toolbar=no,scrollbars=yes,height=450,width=530,top=145,left=235');
        }
    }
})