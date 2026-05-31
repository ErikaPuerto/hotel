// ============================================================
// CONFIG
// ============================================================

const API = "http://localhost:8082";

// ============================================================
// INICIO
// ============================================================

window.onload = function () {
    cargarClientes();
    aplicarPermisos();
    document.getElementById("selectorRol")
        .addEventListener("change", aplicarPermisos);
};

// ============================================================
// TOAST
// ============================================================

let toastTimer = null;

function toast(msg, tipo = "success") {
    const el = document.getElementById("toast");
    el.textContent = msg;
    el.className = "toast " + tipo;
    clearTimeout(toastTimer);
    toastTimer = setTimeout(() => {
        el.className = "toast oculto";
    }, 3200);
}

// ============================================================
// MODAL GENÉRICO
// ============================================================

let modalGuardarFn = null;

function abrirModal(titulo, htmlCuerpo, fnGuardar) {
    document.getElementById("modalTitulo").textContent = titulo;
    document.getElementById("modalCuerpo").innerHTML = htmlCuerpo;
    modalGuardarFn = fnGuardar;
    document.getElementById("btnGuardarModal").onclick = () => fnGuardar();
    document.getElementById("modal").classList.remove("oculto");
}

function cerrarModal() {
    document.getElementById("modal").classList.add("oculto");
    modalGuardarFn = null;
}

document.getElementById("modal").addEventListener("click", function (e) {
    if (e.target === this) cerrarModal();
});

// ============================================================
// NAVEGACIÓN
// ============================================================

function cambiarSeccion(id, btn) {
    document.querySelectorAll(".seccion").forEach(s => s.classList.add("oculto"));
    document.getElementById(id).classList.remove("oculto");

    document.querySelectorAll(".nav-btn").forEach(b => b.classList.remove("active"));
    btn.classList.add("active");

    // Carga automática al cambiar sección
    const loaders = {
        clientes:     cargarClientes,
        habitaciones: cargarHabitaciones,
        reservas:     cargarReservas,
        pagos:        cargarPagos,
        empleados:    cargarEmpleados,
        reportes:     cargarReportes
    };
    if (loaders[id]) loaders[id]();
}

// ============================================================
// PERMISOS POR ROL
// ============================================================

function aplicarPermisos() {
    const rol = document.getElementById("selectorRol").value;

    // Mostrar / ocultar secciones del sidebar
    document.querySelectorAll(".nav-admin").forEach(el => {
        el.style.display = (rol === "admin") ? "" : "none";
    });

    document.querySelectorAll(".nav-admin-empleado").forEach(el => {
        el.style.display = (rol === "admin" || rol === "empleado") ? "" : "none";
    });

    // Si el rol actual no puede ver la sección activa, redirigir a clientes
    const activa = document.querySelector(".seccion:not(.oculto)");
    if (activa) {
        const id = activa.id;
        if ((id === "empleados" || id === "reportes") && rol !== "admin") {
            cambiarSeccion("clientes",
                document.querySelector(".nav-btn[data-seccion='clientes']"));
        }
    }
}

// ============================================================
// BADGE DE ESTADO
// ============================================================

function badge(estado) {
    const map = {
        disponible:  "verde",
        activa:      "azul",
        ocupada:     "amarillo",
        pendiente:   "amarillo",
        confirmada:  "azul",
        completada:  "verde",
        cancelada:   "rojo",
        completado:  "verde",
        mantenimiento: "gris"
    };
    const cls = map[estado] || "gris";
    return `<span class="badge badge-${cls}">${estado}</span>`;
}

// ============================================================
// CLIENTES
// ============================================================

function abrirFormCliente() {
    document.getElementById("formCliente").classList.remove("oculto");
}
function cerrarFormCliente() {
    document.getElementById("formCliente").classList.add("oculto");
    limpiarFormClientes();
}

function limpiarFormClientes() {
    ["clienteUsuario","clienteDocumento","clienteTelefono","clienteCorreo"]
        .forEach(id => document.getElementById(id).value = "");
}

async function cargarClientes() {
    try {
        const res = await fetch(API + "/clientes");
        const data = await res.json();
        const tbody = document.getElementById("bodyClientes");
        tbody.innerHTML = data.map(c => `
            <tr>
                <td>${c.idCliente}</td>
                <td>${c.usuario}</td>
                <td>${c.documento}</td>
                <td>${c.telefono}</td>
                <td>${c.correo}</td>
                <td class="acciones-td">
                    <button class="btn-sm btn-edit" onclick="editarCliente(${c.idCliente})">Editar</button>
                    <button class="btn-sm btn-del" onclick="eliminarCliente(${c.idCliente})">Eliminar</button>
                </td>
            </tr>
        `).join("");
    } catch (e) {
        toast("Error cargando clientes", "error");
    }
}

async function crearCliente() {
    const cliente = {
        usuario:   document.getElementById("clienteUsuario").value.trim(),
        documento: document.getElementById("clienteDocumento").value.trim(),
        telefono:  document.getElementById("clienteTelefono").value.trim(),
        correo:    document.getElementById("clienteCorreo").value.trim()
    };

    if (!cliente.usuario || !cliente.documento) {
        toast("Usuario y documento son obligatorios", "error"); return;
    }

    try {
        const res = await fetch(API + "/clientes", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(cliente)
        });
        if (!res.ok) throw new Error();
        toast("Cliente creado ✓");
        cerrarFormCliente();
        cargarClientes();
    } catch {
        toast("No se pudo crear el cliente", "error");
    }
}

async function editarCliente(id) {
    try {
        const res = await fetch(API + "/clientes/" + id);
        const c = await res.json();
        abrirModal("Editar cliente",
            `<div class="form-grid">
                <input id="eCliUsuario"   value="${c.usuario}"   placeholder="Usuario">
                <input id="eCliDocumento" value="${c.documento}" placeholder="Documento">
                <input id="eCliTelefono"  value="${c.telefono}"  placeholder="Teléfono">
                <input id="eCliCorreo"    value="${c.correo}"    placeholder="Correo" type="email">
            </div>`,
            async () => {
                const body = {
                    usuario:   document.getElementById("eCliUsuario").value,
                    documento: document.getElementById("eCliDocumento").value,
                    telefono:  document.getElementById("eCliTelefono").value,
                    correo:    document.getElementById("eCliCorreo").value
                };
                const r = await fetch(API + "/clientes/" + id, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(body)
                });
                if (!r.ok) throw new Error();
                toast("Cliente actualizado ✓");
                cerrarModal();
                cargarClientes();
            }
        );
    } catch {
        toast("Error al cargar cliente", "error");
    }
}

async function eliminarCliente(id) {
    if (!confirm("¿Eliminar cliente #" + id + "?")) return;
    try {
        const res = await fetch(API + "/clientes/" + id, { method: "DELETE" });
        if (!res.ok) throw new Error();
        toast("Cliente eliminado");
        cargarClientes();
    } catch {
        toast("No se pudo eliminar", "error");
    }
}

// ============================================================
// HABITACIONES
// ============================================================

function abrirFormHabitacion() {
    document.getElementById("formHabitacion").classList.remove("oculto");
}
function cerrarFormHabitacion() {
    document.getElementById("formHabitacion").classList.add("oculto");
}

async function cargarHabitaciones() {
    try {
        const res = await fetch(API + "/habitaciones");
        renderHabitaciones(await res.json());
    } catch {
        toast("Error cargando habitaciones", "error");
    }
}

async function cargarHabitacionesDisponibles() {
    try {
        const res = await fetch(API + "/habitaciones/disponibles");
        renderHabitaciones(await res.json(), true);
    } catch {
        toast("Error cargando disponibles", "error");
    }
}

function renderHabitaciones(data, soloDisponibles = false) {
    const rol = document.getElementById("selectorRol").value;
    const tbody = document.getElementById("bodyHabitaciones");
    tbody.innerHTML = data.map(h => `
        <tr>
            <td>${h.idHabitacion}</td>
            <td>${h.numero}</td>
            <td>${badge(h.estado)}</td>
            <td>${h.idTipo}</td>
            <td>${h.idSede}</td>
            <td class="acciones-td">
                ${rol === "admin" ? `
                    <button class="btn-sm btn-edit" onclick="editarHabitacion(${h.idHabitacion})">Editar</button>
                    <button class="btn-sm btn-del" onclick="eliminarHabitacion(${h.idHabitacion})">Eliminar</button>
                ` : "—"}
            </td>
        </tr>
    `).join("");
}

async function crearHabitacion() {
    const hab = {
        numero:  parseInt(document.getElementById("habitacionNumero").value),
        estado:  document.getElementById("habitacionEstado").value,
        idTipo:  parseInt(document.getElementById("habitacionTipo").value),
        idSede:  parseInt(document.getElementById("habitacionSede").value)
    };

    if (!hab.numero || !hab.idTipo || !hab.idSede) {
        toast("Todos los campos son obligatorios", "error"); return;
    }

    try {
        const res = await fetch(API + "/habitaciones", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(hab)
        });
        if (!res.ok) throw new Error();
        toast("Habitación creada ✓");
        cerrarFormHabitacion();
        cargarHabitaciones();
    } catch {
        toast("No se pudo crear la habitación", "error");
    }
}

async function editarHabitacion(id) {
    try {
        const res = await fetch(API + "/habitaciones/" + id);
        const h = await res.json();
        abrirModal("Editar habitación",
            `<div class="form-grid">
                <input type="number" id="eHabNumero" value="${h.numero}" placeholder="Número">
                <select id="eHabEstado">
                    <option value="disponible"    ${h.estado==="disponible"?"selected":""}>Disponible</option>
                    <option value="ocupada"       ${h.estado==="ocupada"?"selected":""}>Ocupada</option>
                    <option value="mantenimiento" ${h.estado==="mantenimiento"?"selected":""}>Mantenimiento</option>
                </select>
                <input type="number" id="eHabTipo" value="${h.idTipo}" placeholder="ID Tipo">
                <input type="number" id="eHabSede" value="${h.idSede}" placeholder="ID Sede">
            </div>`,
            async () => {
                const body = {
                    numero: parseInt(document.getElementById("eHabNumero").value),
                    estado: document.getElementById("eHabEstado").value,
                    idTipo: parseInt(document.getElementById("eHabTipo").value),
                    idSede: parseInt(document.getElementById("eHabSede").value)
                };
                const r = await fetch(API + "/habitaciones/" + id, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(body)
                });
                if (!r.ok) throw new Error();
                toast("Habitación actualizada ✓");
                cerrarModal();
                cargarHabitaciones();
            }
        );
    } catch {
        toast("Error al cargar habitación", "error");
    }
}

async function eliminarHabitacion(id) {
    if (!confirm("¿Eliminar habitación #" + id + "?")) return;
    try {
        const res = await fetch(API + "/habitaciones/" + id, { method: "DELETE" });
        if (!res.ok) throw new Error();
        toast("Habitación eliminada");
        cargarHabitaciones();
    } catch {
        toast("No se pudo eliminar", "error");
    }
}

// ============================================================
// RESERVAS
// ============================================================

function abrirFormReserva() {
    document.getElementById("formReserva").classList.remove("oculto");
}
function cerrarFormReserva() {
    document.getElementById("formReserva").classList.add("oculto");
}

async function cargarReservas() {
    try {
        const res = await fetch(API + "/reservas");
        const data = await res.json();
        const rol = document.getElementById("selectorRol").value;
        const tbody = document.getElementById("bodyReservas");
        tbody.innerHTML = data.map(r => {
            let acciones = "";
            if (rol === "admin" || rol === "empleado") {
                if (r.estado === "pendiente" || r.estado === "confirmada") {
                    acciones += `<button class="btn-sm btn-ok"  onclick="checkIn(${r.idReserva})">Check-in</button>`;
                }
                if (r.estado === "activa") {
                    acciones += `<button class="btn-sm btn-warn" onclick="checkOut(${r.idReserva})">Check-out</button>`;
                }
                if (r.estado !== "completada" && r.estado !== "cancelada") {
                    acciones += `<button class="btn-sm btn-del" onclick="cancelarReserva(${r.idReserva})">Cancelar</button>`;
                }
            }
            if (rol === "admin") {
                acciones += `<button class="btn-sm btn-edit" onclick="editarReserva(${r.idReserva})">Editar</button>`;
                acciones += `<button class="btn-sm btn-del" onclick="eliminarReserva(${r.idReserva})">Eliminar</button>`;
            }
            return `
                <tr>
                    <td>${r.idReserva}</td>
                    <td>${r.fechaInicio}</td>
                    <td>${r.fechaFin}</td>
                    <td>${badge(r.estado)}</td>
                    <td>${r.idCliente}</td>
                    <td>${r.idHabitacion}</td>
                    <td>${r.idEmpleado}</td>
                    <td class="acciones-td">${acciones || "—"}</td>
                </tr>
            `;
        }).join("");
    } catch {
        toast("Error cargando reservas", "error");
    }
}

async function crearReserva() {
    const reserva = {
        fechaInicio:   document.getElementById("fechaInicio").value,
        fechaFin:      document.getElementById("fechaFin").value,
        idCliente:     parseInt(document.getElementById("idClienteReserva").value),
        idHabitacion:  parseInt(document.getElementById("idHabitacionReserva").value),
        idEmpleado:    parseInt(document.getElementById("idEmpleadoReserva").value)
    };

    if (!reserva.fechaInicio || !reserva.fechaFin || !reserva.idCliente || !reserva.idHabitacion) {
        toast("Todos los campos son obligatorios", "error"); return;
    }

    if (reserva.fechaInicio >= reserva.fechaFin) {
        toast("La fecha de fin debe ser posterior a la de inicio", "error"); return;
    }

    try {
        const res = await fetch(API + "/reservas", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(reserva)
        });

        if (res.status === 409) {
            toast("La habitación no está disponible para esas fechas", "error"); return;
        }
        if (!res.ok) throw new Error();
        toast("Reserva creada ✓");
        cerrarFormReserva();
        cargarReservas();
    } catch {
        toast("No se pudo crear la reserva", "error");
    }
}

async function editarReserva(id) {
    try {
        const res = await fetch(API + "/reservas/" + id);
        const r = await res.json();
        abrirModal("Editar reserva",
            `<div class="form-grid">
                <div>
                    <label class="field-label">Fecha inicio</label>
                    <input type="date" id="eResInicio" value="${r.fechaInicio}">
                </div>
                <div>
                    <label class="field-label">Fecha fin</label>
                    <input type="date" id="eResFin" value="${r.fechaFin}">
                </div>
                <select id="eResEstado">
                    <option value="pendiente"  ${r.estado==="pendiente"?"selected":""}>Pendiente</option>
                    <option value="confirmada" ${r.estado==="confirmada"?"selected":""}>Confirmada</option>
                    <option value="activa"     ${r.estado==="activa"?"selected":""}>Activa</option>
                    <option value="completada" ${r.estado==="completada"?"selected":""}>Completada</option>
                    <option value="cancelada"  ${r.estado==="cancelada"?"selected":""}>Cancelada</option>
                </select>
                <input type="number" id="eResCliente"    value="${r.idCliente}"    placeholder="ID Cliente">
                <input type="number" id="eResHabitacion" value="${r.idHabitacion}" placeholder="ID Habitación">
                <input type="number" id="eResEmpleado"   value="${r.idEmpleado}"   placeholder="ID Empleado">
            </div>`,
            async () => {
                const body = {
                    fechaInicio:  document.getElementById("eResInicio").value,
                    fechaFin:     document.getElementById("eResFin").value,
                    estado:       document.getElementById("eResEstado").value,
                    idCliente:    parseInt(document.getElementById("eResCliente").value),
                    idHabitacion: parseInt(document.getElementById("eResHabitacion").value),
                    idEmpleado:   parseInt(document.getElementById("eResEmpleado").value)
                };
                const resp = await fetch(API + "/reservas/" + id, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(body)
                });
                if (!resp.ok) throw new Error();
                toast("Reserva actualizada ✓");
                cerrarModal();
                cargarReservas();
            }
        );
    } catch {
        toast("Error al cargar reserva", "error");
    }
}

async function checkIn(id) {
    try {
        const res = await fetch(API + "/reservas/" + id + "/check-in", { method: "PUT" });
        if (!res.ok) {
            const err = await res.text();
            toast(err || "Error en check-in", "error"); return;
        }
        toast("Check-in realizado ✓");
        cargarReservas();
    } catch {
        toast("Error en check-in", "error");
    }
}

async function checkOut(id) {
    try {
        const res = await fetch(API + "/reservas/" + id + "/check-out", { method: "PUT" });
        if (!res.ok) {
            const err = await res.text();
            toast(err || "Error en check-out", "error"); return;
        }
        toast("Check-out realizado ✓");
        cargarReservas();
    } catch {
        toast("Error en check-out", "error");
    }
}

async function cancelarReserva(id) {
    if (!confirm("¿Cancelar reserva #" + id + "?")) return;
    try {
        const res = await fetch(API + "/reservas/" + id + "/cancelar", { method: "PUT" });
        if (!res.ok) {
            const err = await res.text();
            toast(err || "No se pudo cancelar", "error"); return;
        }
        toast("Reserva cancelada");
        cargarReservas();
    } catch {
        toast("Error al cancelar", "error");
    }
}

async function eliminarReserva(id) {
    if (!confirm("¿Eliminar reserva #" + id + "? Esta acción no se puede deshacer.")) return;
    try {
        const res = await fetch(API + "/reservas/" + id, { method: "DELETE" });
        if (!res.ok) throw new Error();
        toast("Reserva eliminada");
        cargarReservas();
    } catch {
        toast("No se pudo eliminar", "error");
    }
}

// ============================================================
// PAGOS
// ============================================================

function abrirFormPago() {
    document.getElementById("formPago").classList.remove("oculto");
}
function cerrarFormPago() {
    document.getElementById("formPago").classList.add("oculto");
}

async function cargarPagos() {
    try {
        const res = await fetch(API + "/pagos");
        const data = await res.json();
        const rol = document.getElementById("selectorRol").value;
        const tbody = document.getElementById("bodyPagos");
        tbody.innerHTML = data.map(p => `
            <tr>
                <td>${p.idPago}</td>
                <td>$${Number(p.monto).toLocaleString("es-CO")}</td>
                <td>${p.fecha}</td>
                <td>${badge(p.estado)}</td>
                <td>${p.idMetodo}</td>
                <td>${p.idReserva}</td>
                <td>${p.idCliente}</td>
                <td class="acciones-td">
                    ${rol === "admin" ? `
                        <button class="btn-sm btn-edit" onclick="editarPago(${p.idPago})">Editar</button>
                        <button class="btn-sm btn-del"  onclick="eliminarPago(${p.idPago})">Eliminar</button>
                    ` : "—"}
                </td>
            </tr>
        `).join("");
    } catch {
        toast("Error cargando pagos", "error");
    }
}

async function crearPago() {
    const pago = {
        monto:     parseFloat(document.getElementById("montoPago").value),
        fecha:     document.getElementById("fechaPago").value,
        idMetodo:  parseInt(document.getElementById("idMetodoPago").value),
        idReserva: parseInt(document.getElementById("idReservaPago").value),
        idCliente: parseInt(document.getElementById("idClientePago").value)
    };

    if (!pago.monto || !pago.fecha || !pago.idReserva || !pago.idCliente) {
        toast("Todos los campos son obligatorios", "error"); return;
    }

    try {
        const res = await fetch(API + "/pagos", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(pago)
        });

        if (res.status === 404) { toast("La reserva no existe", "error"); return; }
        if (res.status === 400) { toast("Reserva cancelada — no se puede pagar", "error"); return; }
        if (res.status === 409) { toast("Ya existe un pago completado para esta reserva", "error"); return; }
        if (!res.ok) throw new Error();
        toast("Pago registrado ✓");
        cerrarFormPago();
        cargarPagos();
    } catch {
        toast("No se pudo registrar el pago", "error");
    }
}

async function editarPago(id) {
    try {
        const res = await fetch(API + "/pagos/" + id);
        const p = await res.json();
        abrirModal("Editar pago",
            `<div class="form-grid">
                <input type="number" step="0.01" id="ePagMonto"   value="${p.monto}"    placeholder="Monto">
                <div>
                    <label class="field-label">Fecha</label>
                    <input type="date" id="ePagFecha" value="${p.fecha}">
                </div>
                <select id="ePagEstado">
                    <option value="pendiente"  ${p.estado==="pendiente"?"selected":""}>Pendiente</option>
                    <option value="completado" ${p.estado==="completado"?"selected":""}>Completado</option>
                    <option value="rechazado"  ${p.estado==="rechazado"?"selected":""}>Rechazado</option>
                </select>
                <input type="number" id="ePagMetodo"  value="${p.idMetodo}"  placeholder="ID Método">
                <input type="number" id="ePagReserva" value="${p.idReserva}" placeholder="ID Reserva">
                <input type="number" id="ePagCliente" value="${p.idCliente}" placeholder="ID Cliente">
            </div>`,
            async () => {
                const body = {
                    monto:     parseFloat(document.getElementById("ePagMonto").value),
                    fecha:     document.getElementById("ePagFecha").value,
                    estado:    document.getElementById("ePagEstado").value,
                    idMetodo:  parseInt(document.getElementById("ePagMetodo").value),
                    idReserva: parseInt(document.getElementById("ePagReserva").value),
                    idCliente: parseInt(document.getElementById("ePagCliente").value)
                };
                const r = await fetch(API + "/pagos/" + id, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(body)
                });
                if (!r.ok) throw new Error();
                toast("Pago actualizado ✓");
                cerrarModal();
                cargarPagos();
            }
        );
    } catch {
        toast("Error al cargar pago", "error");
    }
}

async function eliminarPago(id) {
    if (!confirm("¿Eliminar pago #" + id + "?")) return;
    try {
        const res = await fetch(API + "/pagos/" + id, { method: "DELETE" });
        if (!res.ok) throw new Error();
        toast("Pago eliminado");
        cargarPagos();
    } catch {
        toast("No se pudo eliminar", "error");
    }
}

// ============================================================
// EMPLEADOS
// ============================================================

function abrirFormEmpleado() {
    document.getElementById("formEmpleado").classList.remove("oculto");
}
function cerrarFormEmpleado() {
    document.getElementById("formEmpleado").classList.add("oculto");
}

async function cargarEmpleados() {
    try {
        const res = await fetch(API + "/empleados");
        const data = await res.json();
        const tbody = document.getElementById("bodyEmpleados");
        tbody.innerHTML = data.map(e => `
            <tr>
                <td>${e.idEmpleado}</td>
                <td>${e.nombre}</td>
                <td>${e.idRol}</td>
                <td>${e.idSede}</td>
                <td class="acciones-td">
                    <button class="btn-sm btn-edit" onclick="editarEmpleado(${e.idEmpleado})">Editar</button>
                    <button class="btn-sm btn-del"  onclick="eliminarEmpleado(${e.idEmpleado})">Eliminar</button>
                </td>
            </tr>
        `).join("");
    } catch {
        toast("Error cargando empleados", "error");
    }
}

async function crearEmpleado() {
    const emp = {
        nombre: document.getElementById("empleadoNombre").value.trim(),
        idRol:  parseInt(document.getElementById("empleadoRol").value),
        idSede: parseInt(document.getElementById("empleadoSede").value)
    };

    if (!emp.nombre || !emp.idRol || !emp.idSede) {
        toast("Todos los campos son obligatorios", "error"); return;
    }

    try {
        const res = await fetch(API + "/empleados", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(emp)
        });
        if (!res.ok) throw new Error();
        toast("Empleado creado ✓");
        cerrarFormEmpleado();
        cargarEmpleados();
    } catch {
        toast("No se pudo crear el empleado", "error");
    }
}

async function editarEmpleado(id) {
    try {
        const res = await fetch(API + "/empleados/" + id);
        const e = await res.json();
        abrirModal("Editar empleado",
            `<div class="form-grid">
                <input type="text"   id="eEmpNombre" value="${e.nombre}"  placeholder="Nombre">
                <input type="number" id="eEmpRol"    value="${e.idRol}"   placeholder="ID Rol">
                <input type="number" id="eEmpSede"   value="${e.idSede}"  placeholder="ID Sede">
            </div>`,
            async () => {
                const body = {
                    nombre: document.getElementById("eEmpNombre").value,
                    idRol:  parseInt(document.getElementById("eEmpRol").value),
                    idSede: parseInt(document.getElementById("eEmpSede").value)
                };
                const r = await fetch(API + "/empleados/" + id, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(body)
                });
                if (!r.ok) throw new Error();
                toast("Empleado actualizado ✓");
                cerrarModal();
                cargarEmpleados();
            }
        );
    } catch {
        toast("Error al cargar empleado", "error");
    }
}

async function eliminarEmpleado(id) {
    if (!confirm("¿Eliminar empleado #" + id + "?")) return;
    try {
        const res = await fetch(API + "/empleados/" + id, { method: "DELETE" });
        if (!res.ok) throw new Error();
        toast("Empleado eliminado");
        cargarEmpleados();
    } catch {
        toast("No se pudo eliminar", "error");
    }
}

// ============================================================
// REPORTES
// ============================================================

async function cargarReportes() {
    cargarIngresosHotel();
    cargarOcupacion();
    cargarReservasPorSede();
}

async function cargarIngresosHotel() {
    try {
        const res = await fetch(API + "/reportes/ingresos");
        const d = await res.json();
        document.getElementById("ingresosTotales").innerHTML = `
            <div class="ingreso-stat">
                <div class="stat-row">
                    <span class="stat-label">Total pagos</span>
                    <span class="stat-value">${d.totalPagos}</span>
                </div>
                <div class="stat-row">
                    <span class="stat-label">Ingresos totales</span>
                    <span class="stat-value">$${Number(d.ingresosTotales).toLocaleString("es-CO")}</span>
                </div>
                <div class="stat-row">
                    <span class="stat-label">Promedio por pago</span>
                    <span class="stat-value">$${Number(d.promedioPago).toLocaleString("es-CO")}</span>
                </div>
            </div>
        `;
    } catch {
        document.getElementById("ingresosTotales").textContent = "No disponible";
    }
}

async function cargarOcupacion() {
    try {
        const res = await fetch(API + "/reportes/ocupacion");
        const data = await res.json();
        document.getElementById("bodyOcupacion").innerHTML = data.map(o => `
            <tr>
                <td>${o.idHabitacion}</td>
                <td>${o.numero}</td>
                <td>${badge(o.estado)}</td>
                <td>${o.totalReservas}</td>
            </tr>
        `).join("");
    } catch {
        document.getElementById("bodyOcupacion").innerHTML =
            '<tr><td colspan="4" style="color:var(--text-muted);text-align:center">No disponible</td></tr>';
    }
}

async function cargarReservasPorSede() {
    try {
        const res = await fetch(API + "/reportes/reservas-sede");
        const data = await res.json();
        document.getElementById("bodySedes").innerHTML = data.map(s => `
            <tr>
                <td>${s.idSede}</td>
                <td>${s.nombreSede}</td>
                <td>${s.totalReservas}</td>
            </tr>
        `).join("");
    } catch {
        document.getElementById("bodySedes").innerHTML =
            '<tr><td colspan="3" style="color:var(--text-muted);text-align:center">No disponible</td></tr>';
    }
}
