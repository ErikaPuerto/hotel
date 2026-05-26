const API = "http://localhost:8082";

//
// INICIO
//

window.onload = function () {

    mostrarSeccion("clientes");

    aplicarPermisos();

    cargarClientes();
};

//
// CAMBIO DE SECCIÓN
//

function mostrarSeccion(id) {

    const secciones =
        document.querySelectorAll(".seccion");

    secciones.forEach(seccion => {
        seccion.classList.add("oculto");
    });

    document
        .getElementById(id)
        .classList.remove("oculto");
}

//
// ROLES
//

document
    .getElementById("selectorRol")
    .addEventListener("change", aplicarPermisos);

function aplicarPermisos() {

    const rol =
        document.getElementById("selectorRol").value;

    const botonesClientes =
        document.querySelectorAll("#clientes button");

    const botonesHabitaciones =
        document.querySelectorAll("#habitaciones button");

    const botonesReservas =
        document.querySelectorAll("#reservas button");

    const botonesPagos =
        document.querySelectorAll("#pagos button");

    //
    // ADMIN
    //

    if (rol === "admin") {

        habilitarTodo();

        return;
    }

    //
    // EMPLEADO
    //

    if (rol === "empleado") {

        habilitarTodo();

        botonesClientes.forEach(btn => {
            btn.disabled = true;
        });

        return;
    }

    //
    // CLIENTE
    //

    if (rol === "cliente") {

        habilitarTodo();

        botonesHabitaciones.forEach(btn => {
            btn.disabled = true;
        });

        botonesPagos.forEach(btn => {
            btn.disabled = true;
        });

        return;
    }
}

function habilitarTodo() {

    document
        .querySelectorAll("button")
        .forEach(btn => {
            btn.disabled = false;
        });
}

//
// CLIENTES
//

async function cargarClientes() {

    try {

        const response =
            await fetch(API + "/clientes");

        const clientes =
            await response.json();

        let html = `
            <table>
                <tr>
                    <th>ID</th>
                    <th>Usuario</th>
                    <th>Documento</th>
                    <th>Teléfono</th>
                    <th>Correo</th>
                </tr>
        `;

        clientes.forEach(cliente => {

            html += `
                <tr>
                    <td>${cliente.idCliente}</td>
                    <td>${cliente.usuario}</td>
                    <td>${cliente.documento}</td>
                    <td>${cliente.telefono}</td>
                    <td>${cliente.correo}</td>
                </tr>
            `;
        });

        html += "</table>";

        document.getElementById("tablaClientes")
            .innerHTML = html;

    } catch (error) {

        console.error(error);

        alert("Error cargando clientes");
    }
}

async function crearCliente() {

    try {

        const cliente = {

            usuario:
                document.getElementById("clienteUsuario").value,

            documento:
                document.getElementById("clienteDocumento").value,

            telefono:
                document.getElementById("clienteTelefono").value,

            correo:
                document.getElementById("clienteCorreo").value
        };

        const response = await fetch(API + "/clientes", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(cliente)
        });

        if (!response.ok) {

            throw new Error("Error al crear cliente");
        }

        alert("Cliente creado");

        limpiarFormularioClientes();

        cargarClientes();

    } catch (error) {

        console.error(error);

        alert("No se pudo guardar el cliente");
    }
}

function limpiarFormularioClientes() {

    document.getElementById("clienteUsuario").value = "";
    document.getElementById("clienteDocumento").value = "";
    document.getElementById("clienteTelefono").value = "";
    document.getElementById("clienteCorreo").value = "";
}

//
// HABITACIONES
//

async function cargarHabitaciones() {

    try {

        const response =
            await fetch(API + "/habitaciones");

        const habitaciones =
            await response.json();

        let html = `
            <table>
                <tr>
                    <th>ID</th>
                    <th>Número</th>
                    <th>Estado</th>
                    <th>Tipo</th>
                    <th>Sede</th>
                </tr>
        `;

        habitaciones.forEach(habitacion => {

            html += `
                <tr>
                    <td>${habitacion.idHabitacion}</td>
                    <td>${habitacion.numero}</td>
                    <td>${habitacion.estado}</td>
                    <td>${habitacion.idTipo}</td>
                    <td>${habitacion.idSede}</td>
                </tr>
            `;
        });

        html += "</table>";

        document.getElementById("tablaHabitaciones")
            .innerHTML = html;

    } catch (error) {

        console.error(error);

        alert("Error cargando habitaciones");
    }
}

async function crearHabitacion() {

    try {

        const habitacion = {

            numero: parseInt(
                document.getElementById("habitacionNumero").value
            ),

            estado:
                document.getElementById("habitacionEstado").value,

            idTipo: parseInt(
                document.getElementById("habitacionTipo").value
            ),

            idSede: parseInt(
                document.getElementById("habitacionSede").value
            )
        };

        const response =
            await fetch(API + "/habitaciones", {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(habitacion)
            });

        if (!response.ok) {

            throw new Error("Error al crear habitación");
        }

        alert("Habitación creada");

        cargarHabitaciones();

    } catch (error) {

        console.error(error);

        alert("No se pudo guardar la habitación");
    }
}

//
// RESERVAS
//

async function cargarReservas() {

    try {

        const response =
            await fetch(API + "/reservas");

        const reservas =
            await response.json();

        let html = `
            <table>
                <tr>
                    <th>ID</th>
                    <th>Inicio</th>
                    <th>Fin</th>
                    <th>Estado</th>
                    <th>Cliente</th>
                    <th>Habitación</th>
                    <th>Empleado</th>
                </tr>
        `;

        reservas.forEach(reserva => {

            html += `
                <tr>
                    <td>${reserva.idReserva}</td>
                    <td>${reserva.fechaInicio}</td>
                    <td>${reserva.fechaFin}</td>
                    <td>${reserva.estado}</td>
                    <td>${reserva.idCliente}</td>
                    <td>${reserva.idHabitacion}</td>
                    <td>${reserva.idEmpleado}</td>
                </tr>
            `;
        });

        html += "</table>";

        document.getElementById("tablaReservas")
            .innerHTML = html;

    } catch (error) {

        console.error(error);

        alert("Error cargando reservas");
    }
}

async function crearReserva() {

    try {

        const reserva = {

            fechaInicio:
                document.getElementById("fechaInicio").value,

            fechaFin:
                document.getElementById("fechaFin").value,

            estado:
                document.getElementById("estadoReserva").value,

            idCliente: parseInt(
                document.getElementById("idClienteReserva").value
            ),

            idHabitacion: parseInt(
                document.getElementById("idHabitacionReserva").value
            ),

            idEmpleado: parseInt(
                document.getElementById("idEmpleadoReserva").value
            )
        };

        const response =
            await fetch(API + "/reservas", {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(reserva)
            });

        if (!response.ok) {

            throw new Error("Error al crear reserva");
        }

        alert("Reserva creada");

        cargarReservas();

    } catch (error) {

        console.error(error);

        alert("No se pudo guardar la reserva");
    }
}

//
// PAGOS
//

async function cargarPagos() {

    try {

        const response =
            await fetch(API + "/pagos");

        const pagos =
            await response.json();

        let html = `
            <table>
                <tr>
                    <th>ID</th>
                    <th>Monto</th>
                    <th>Fecha</th>
                    <th>Estado</th>
                    <th>Método</th>
                    <th>Reserva</th>
                    <th>Cliente</th>
                </tr>
        `;

        pagos.forEach(pago => {

            html += `
                <tr>
                    <td>${pago.idPago}</td>
                    <td>${pago.monto}</td>
                    <td>${pago.fecha}</td>
                    <td>${pago.estado}</td>
                    <td>${pago.idMetodo}</td>
                    <td>${pago.idReserva}</td>
                    <td>${pago.idCliente}</td>
                </tr>
            `;
        });

        html += "</table>";

        document.getElementById("tablaPagos")
            .innerHTML = html;

    } catch (error) {

        console.error(error);

        alert("Error cargando pagos");
    }
}

async function crearPago() {

    try {

        const pago = {

            monto: parseFloat(
                document.getElementById("montoPago").value
            ),

            fecha:
                document.getElementById("fechaPago").value,

            estado:
                document.getElementById("estadoPago").value,

            idMetodo: parseInt(
                document.getElementById("idMetodoPago").value
            ),

            idReserva: parseInt(
                document.getElementById("idReservaPago").value
            ),

            idCliente: parseInt(
                document.getElementById("idClientePago").value
            )
        };

        const response =
            await fetch(API + "/pagos", {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(pago)
            });

        if (!response.ok) {

            throw new Error("Error al crear pago");
        }

        alert("Pago creado");

        cargarPagos();

    } catch (error) {

        console.error(error);

        alert("No se pudo guardar el pago");
    }
}