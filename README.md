# Hotel Management System

Sistema de gestión hotelera desarrollado con Spring Boot y PostgreSQL para la administración de habitaciones, clientes, reservas y pagos.

## Descripción

Este proyecto permite gestionar operaciones fundamentales de un hotel mediante una arquitectura basada en Spring Boot, implementando lógica de negocio para reservas, control de disponibilidad y manejo de usuarios.

El sistema incluye un frontend básico desarrollado con HTML, CSS y JavaScript para interacción inicial con la aplicación.

---

## Funcionalidades

### Gestión de entidades

El sistema administra las siguientes entidades:

- Habitación
- Tipo de habitación
- Cliente
- Reserva
- Pago

### Operaciones CRUD

Cada entidad cuenta con operaciones completas de:

- GET (listar registros)
- GET by ID (consultar por identificador)
- POST (crear)
- PUT (actualizar)
- DELETE (eliminar)

---

## Lógica de negocio implementada

### Gestión de reservas

El sistema incorpora validaciones orientadas a la operación hotelera:

- Validación de disponibilidad de habitaciones
- Restricción de reservas duplicadas
- Prevención de fechas cruzadas para una misma habitación
- Actualización automática del estado de habitación

Estados gestionados:

- Disponible → Ocupada al registrar reserva
- Ocupada → Disponible al cancelar reserva
- Ocupada → Disponible al realizar checkout

---

## Seguridad y autenticación

El proyecto incorpora Spring Security para el manejo de autenticación y control de acceso.

Roles contemplados en el sistema:

- Administrador
- Empleado
- Cliente / Usuario

---

## Validaciones

Se implementan validaciones utilizando Bean Validation y reglas de negocio específicas.

Entre ellas:

- `@NotNull`
- `@NotBlank`
- `@Min`
- Validaciones relacionadas con reservas y disponibilidad

---

## Manejo de excepciones

El sistema implementa manejo controlado de errores mediante:

- Excepciones personalizadas
- Manejo global de excepciones
- Respuestas consistentes desde el backend

---

## Tecnologías utilizadas

### Backend

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- Maven

### Base de datos

- PostgreSQL
- pgAdmin

### Frontend

- HTML
- CSS
- JavaScript

---

## Estructura del proyecto

```text
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── exception
 ├── security
 └── resources
      ├── static
      └── application.properties
```

---

## Instalación y configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/ErikaPuerto/hotel.git
cd hotel
```

### 2. Configurar PostgreSQL

Crear una base de datos llamada:

```sql
hotel
```

Editar el archivo:

```text
src/main/resources/application.properties
```

Configurar las credenciales correspondientes:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hotel
spring.datasource.username=postgres
spring.datasource.password=tu_password
```

---

## Ejecución del proyecto

Ejecutar mediante Maven:

```bash
mvn spring-boot:run
```

o utilizando Maven Wrapper:

```bash
./mvnw spring-boot:run
```

---

## Acceso a la aplicación

Una vez iniciada la aplicación:

```text
http://localhost:8082
```

---

## Estado del proyecto

Proyecto académico en desarrollo.

Posibles mejoras futuras:

- Optimización del frontend
- Mejoras en experiencia de usuario
- Gestión avanzada de roles y permisos
- Refactorización de modelo Usuario / Rol / Cargo
- Documentación completa de la API

---

## Equipo de desarrollo

Proyecto desarrollado con fines académicos para la gestión hotelera.