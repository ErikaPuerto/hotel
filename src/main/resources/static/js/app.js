const API = 'http://localhost:8082';

// ── Navegación ──────────────────────────────────────

function mostrarSeccion(nombre) {
    document.querySelectorAll('.seccion').forEach(s => s.classList.add('oculto'));
    document.getElementById(nombre).classList.remove('oculto');

    if (nombre === 'clientes') cargarClientes();
    if (nombre === 'habitaciones') cargarHabitaciones();
    if (nombre === 'reservas') cargarReservas();
    if (nombre === 'reportes') cargarReportes();
}

// ── Clientes ─────────────────────────────────────────

async function cargarClientes() {
    const res = await fetch(`${API}/clientes`);
    const datos = await res.json();
    const tbody = document.getElementById('body-clientes');
    tbody.innerHTML = datos.map(c => `
        <tr>
            <td>${c.idCliente}</td>
            <td>${c.usuario}</td>
            <td>${c.documento}</td>
            <td>${c.telefono}</td>
            <td>${c.correo}</td>
        </tr>
    `).join('');
}

// ── Habitaciones ──────────────────────────────────────

async function cargarHabitaciones() {
    const res = await fetch(`${API}/habitaciones`);
    const datos = await res.json();
    mostrarHabitaciones(datos);
}

async function cargarDisponibles() {
    const res = await fetch(`${API}/habitaciones/disponibles`);
    const datos = await res.json();
    mostrarHabitaciones(datos);
}

function mostrarHabitaciones(datos) {
    const tbody = document.getElementById('body-habitaciones');
    tbody.innerHTML = datos.map(h => `
        <tr>
            <td>${h.idHabitacion}</td>
            <td>${h.numero}</td>
            <td><span class="estado-${h.estado}">${h.estado}</span></td>
            <td>${h.idTipo}</td>
            <td>${h.idSede}</td>
        </tr>
    `).join('');
}

// ── Reservas ──────────────────────────────────────────

async function cargarReservas() {
    const res = await fetch(`${API}/reservas`);
    const datos = await res.json();
    const tbody = document.getElementById('body-reservas');
    tbody.innerHTML = datos.map(r => `
        <tr>
            <td>${r.idReserva}</td>
            <td>${r.fechaInicio}</td>
            <td>${r.fechaFin}</td>
            <td>${r.estado}</td>
            <td>${r.idCliente}</td>
            <td>${r.idHabitacion}</td>
        </tr>
    `).join('');
}

// ── Reportes ──────────────────────────────────────────

async function cargarReportes() {
    // Ingresos
    const resIngresos = await fetch(`${API}/reportes/ingresos`);
    const ingresos = await resIngresos.json();
    document.getElementById('card-ingresos').innerHTML = `
        <h3>💰 Ingresos</h3>
        <p>Total pagos: <strong>${ingresos.totalPagos}</strong></p>
        <p>Ingresos totales: <strong>$${ingresos.ingresosTotales}</strong></p>
        <p>Promedio pago: <strong>$${ingresos.promedioPago}</strong></p>
    `;

    // Ocupación
    const resOcupacion = await fetch(`${API}/reportes/ocupacion`);
    const ocupacion = await resOcupacion.json();
    document.getElementById('body-ocupacion').innerHTML = ocupacion.map(o => `
        <tr>
            <td>${o.numero}</td>
            <td>${o.estado}</td>
            <td>${o.totalReservas}</td>
        </tr>
    `).join('');

    // Reservas por sede
    const resSedes = await fetch(`${API}/reportes/reservas-sede`);
    const sedes = await resSedes.json();
    document.getElementById('body-sedes').innerHTML = sedes.map(s => `
        <tr>
            <td>${s.nombreSede}</td>
            <td>${s.totalReservas}</td>
        </tr>
    `).join('');
}

// Cargar clientes al inicio
mostrarSeccion('clientes');