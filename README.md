# Proyecto_Final_Backend
Conexión de Backend con Spring Boot

# 🚀 Sistema de Gestión de Eventos UMG - Backend (Spring Boot REST API)

¡Hola! Este es el backend del proyecto final, desarrollado en **Java con Spring Boot 3** y usando **MariaDB**. ¡Está listo para manejar eventos, clientes, ventas de entradas y reportes, todo protegido con JWT!

## 🌟 Características Clave del Proyecto

Este sistema cumple con todos los requerimientos mínimos (y varios extra) de la rúbrica:

* **Modelo de Datos:** Mínimo 5 entidades con relaciones (Evento, Cliente, Entrada, Pago, Ubicación).
* **APIs REST:** CRUD completo para Eventos, Clientes, Entradas, Pagos y Ubicaciones.
* **Reportes:**
    1.  Reporte de Ingresos por Evento (Calcula el total recaudado).
    2.  Reporte de Asistencia (Calcula el porcentaje de asistentes vs. entradas vendidas).
* **Seguridad JWT (+5 Puntos Extra):** Usa JSON Web Tokens para el login y autoriza el acceso a endpoints sensibles (POST, PUT, DELETE) solo al rol **ADMIN**.
* **Documentación OpenAPI/Swagger (+3 Puntos Extra):** Interfaz web para probar y documentar todos los endpoints.

## 🛠️ Instalación y Configuración

Sigue estos pasos para que el proyecto corra en tu máquina.

### 1. Requisitos Previos

* **Java JDK 17+** (yo usé JDK 24, pero 17 funciona perfecto).
* **Maven** (para manejar las dependencias).
* **MariaDB/MySQL** (o la base de datos que hayas configurado en `application.properties`).
* **Postman** (para probar las APIs) o usa **Swagger UI** (ver más abajo).

### 2. Configuración de la Base de Datos

1.  **Crea la base de datos:**
    ```sql
    CREATE DATABASE sistema_eventos;
    USE sistema_eventos;
    ```
2.  **Scripts SQL:** Ejecuta los scripts `schema.sql` y `data.sql` (que contienen la estructura de tablas y datos iniciales, incluyendo el usuario `admin`).

3.  **Archivo `application.properties`:** Verifica que los datos de conexión coincidan con tu base de datos:
    ```properties
    # Ejemplo de configuración
    spring.datasource.url=jdbc:mariadb://localhost:3306/sistema_eventos
    spring.datasource.username=root
    spring.datasource.password=tu_contraseña_de_db
    spring.jpa.hibernate.ddl-auto=update
    # ... otras propiedades ...
    ```

### 3. Ejecución del Proyecto

1.  Abre el proyecto en IntelliJ IDEA o tu IDE favorito.
2.  Ejecuta la clase principal: `SistemaEventosApplication.java`.
3.  Verás el mensaje de Spring Boot: `Started SistemaEventosApplication...` en el puerto **8080**.

## 🔑 Uso y Autenticación (¡Muy Importante!)

### 1. Login (Obtener el Token)

Antes de probar la mayoría de los `POST`, `PUT` o `DELETE`, necesitas el token:

* **Método:** `POST`
* **URL:** `http://localhost:8080/api/v1/auth/login`
* **Body (JSON):**
    ```json
    {
      "username": "admin",
      "password": "admin123"
    }
    ```
* **Respuesta:** Copia la cadena larga del campo **`token`**.

### 2. Usar el Token

Para usar cualquier endpoint protegido (como Crear Cliente `POST /api/v1/clientes`):

* En Postman, ve a la pestaña **Authorization**.
* Selecciona **Type: Bearer Token**.
* Pega el token copiado en el campo **Token**.

## 📄 Documentación Interactiva (Swagger UI)

¡La forma más fácil de probar todo!

* **URL:** Abre tu navegador y navega a: `http://localhost:8080/swagger-ui/index.html`
* **Autorización en Swagger:** Haz clic en el botón **Authorize** arriba a la derecha, pega tu token JWT (el que copiaste en el paso anterior, sin la palabra "Bearer"), y ya puedes probar todos los endpoints desde allí.
