var app = new Vue({
    el: "#app",
    data: {
        items: [],
        selected: [],
        selectAll: false,
        detalle_curso_persona: {
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
            var tipo_curso = document.getElementById("tipoCurso").value;
            var fecha = document.getElementById("txtFechaFac").value;
            axios.get("<%= request.getContextPath()%>/historicoCert", {params: {tipo_curso: tipo_curso, fecha_curso: fecha, action: "consultarPersonasCursos"}}).
                    then(response => {
                        if (response.data.length > 0) {
                            this.items = [];
                            for (var i = 0; response.data.length; i++) {
                                this.detalle_curso_persona = {};
                                this.detalle_curso_persona.numero_documento = response.data[i].per_documetos;
                                this.detalle_curso_persona.id_detalle_curso = response.data[i].drc_id;
                                this.detalle_curso_persona.nombres_persona = response.data[i].per_nombres;
                                this.detalle_curso_persona.apellido_persona = response.data[i].per_apellidos;
                                this.items.push(this.detalle_curso_persona);
                            }

                        } else {
                            this.items = [];
                            alert("no hay persona inscrita en este curso");
                        }

                    }).catch(error => {
                console.log(error);
            });
        },

        guardarAsistencias: function () {

            var data = this.selected;
            if (data.length > 0) {
                axios.get("<%= request.getContextPath()%>/historicoCert", {params: {data: JSON.stringify(data), action: "procesarPersona"}}).
                        then(response => {

                            this.report();

                        }).catch(error => {
                });

            } else {
                alert("debe elegir al menos a una persona");
            }

        },

        report: function () {
            window.open('<%= request.getContextPath()%>/asistencia/reporte.jsp', 'mywindow', 'resizable=no,toolbar=no,scrollbars=yes,height=450,width=530,top=145,left=235');
        }
    }
})