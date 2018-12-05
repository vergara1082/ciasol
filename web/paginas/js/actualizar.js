var app = new Vue({
    el: "#app",
    data: {
        items: [],
        selected: [],
        renderDatos: false,
        detalle_curso_persona: {
            horario: '',
            tipo_documento: '',
            numero_documento: '',
            nombres_persona: '',
            apellido_persona: '',
            numero_infracion: '',
            numero_factura: '',
            fecha_factura: '',
            valor_curso: '',
            numero_comparendo: '',
            fecha_comparendo: ''

        },
        detalle_curso_persona_current: {
            horario: '',
            tipo_documento: '',
            numero_documento: '',
            nombres_persona: '',
            apellido_persona: '',
            numero_infracion: '',
            numero_factura: '',
            fecha_factura: '',
            valor_curso: '',
            numero_comparendo: '',
            fecha_comparendo: '',

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
        guardar: function () {
            var tipo_curso = document.getElementById("cmbTipoDocumento").value;
            var numero_documento = document.getElementById("txtDocumento").value;
            axios.get("<%= request.getContextPath()%>/actualizar", {params: {tipo_curso: tipo_curso, numero_documento: numero_documento, action: "consultar"}}).
                    then(response => {
                        if (response.data.length > 0) {
                            this.items = [];
                            for (var i = 0; response.data.length; i++) {
                                this.detalle_curso_persona = {};
                                this.detalle_curso_persona.tipo_documento = response.data[i].per_tipo_documento;
                                this.detalle_curso_persona.numero_documento = response.data[i].per_documetos;
                                this.detalle_curso_persona.numero_infracion = response.data[i].codigo_infracion;
                                this.detalle_curso_persona.nombres_persona = response.data[i].per_nombres;
                                this.detalle_curso_persona.apellido_persona = response.data[i].per_apellidos;
                                this.detalle_curso_persona.fecha_comparendo = response.data[i].fecha_comparendo;
                                this.detalle_curso_persona.numero_factura = response.data[i].numero_factura;
                                this.detalle_curso_persona.fecha_factura = response.data[i].fecha_factura;
                                this.detalle_curso_persona.valor_curso = response.data[i].valor_curso;
                                this.detalle_curso_persona.numero_comparendo = response.data[i].drc_id;
                                this.items.push(this.detalle_curso_persona);
                            }

                        } else {
                            this.items = [];
                            alert("el tipo y el documento no fueron encontrado en la base de datos");
                        }

                    }).catch(error => {
                console.log(error);
            });
        },
        guardarAsistencias: function () {

            var data = this.selected;
            if (data.length > 0) {
                axios.get("<%= request.getContextPath()%>/asistencias", {params: {data: JSON.stringify(data), action: "procesarPersona"}}).
                        then(response => {

                            this.report();
                        }).catch(error => {
                });
            } else {
                alert("debe elegir al menos a una persona");
            }

        },
        editarDatos: function () {
            axios.get("<%= request.getContextPath()%>/actualizar", {params: {tipo_curso: this.detalle_curso_persona_current, action: "update"}}).
                    then(response => {
                        if (response.data.length > 0) {
                            this.items = [];
                            for (var i = 0; response.data.length; i++) {
                                this.detalle_curso_persona = {};
                                this.detalle_curso_persona.numero_documento = response.data[i].per_documetos;
                                this.detalle_curso_persona.tipo_documento = response.data[i].per_tipo_documento;
                                this.detalle_curso_persona.numero_infracion = response.data[i].drc_id;
                                this.detalle_curso_persona.nombres_persona = response.data[i].per_nombres;
                                this.detalle_curso_persona.apellido_persona = response.data[i].per_apellidos;
                                this.detalle_curso_persona.numero_factura = response.data[i].numero_factura;
                                this.detalle_curso_persona.fecha_factura = response.data[i].fecha_factura;
                                this.detalle_curso_persona.valor_curso = response.data[i].valor_curso;
                                this.detalle_curso_persona.numero_comparendo = response.data[i].valor_curso;
                                this.items.push(this.detalle_curso_persona);
                            }

                        } else {
                            this.items = [];
                            alert("el tipo y el documento no fueron encontrado en la base de datos");
                        }

                    }).catch(error => {
                console.log(error);
            });
        },
        test: function (i) {
            this.detalle_curso_persona_current = i;
            this.items = [];
            this.renderDatos = true;

        }
    }
})
