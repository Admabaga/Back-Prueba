# MiAppBackend

Esta es una aplicación backend desarrollada con Java Spring Boot. La aplicación está diseñada para proporcionar una API RESTful robusta y escalable, enfocada en gestionar proyectos y tareas.

## Características principales

- Desarrollada utilizando Java 17 y Spring Boot 2.x
- Conexión a una base de datos MySQL H2
- API RESTful completa con múltiples endpoints para gestionar proyectos y usuarios
- Implementación segura con autenticación JWT

## Tecnologías utilizadas

- Java 17
- Spring Boot 2.x
- Spring Data JPA
- MySQL H2 (base de datos)
- Maven (gestión de dependencias)

## Funcionamiento

La aplicación utiliza Spring Boot para facilitar el desarrollo, la integración y el despliegue. Está diseñada para funcionar como un servicio web, ofreciendo una API RESTful que puede ser consumida por aplicaciones frontend o cualquier otro cliente HTTP.

## Base de datos

La aplicación utiliza MySQL H2 como su base de datos. Esta solución permite:

- Simplicidad de configuración
- Facilidad de uso en entornos de desarrollo locales
- Escalabilidad para entornos de producción

## Endpoints disponibles

### Proyectos

- **Crear un nuevo proyecto**: `POST /users/{userId}/projects`
  - Requiere autenticación
  - Crea un nuevo proyecto asociado a un usuario específico

- **Obtener proyectos**: `GET /projects`
  - Obtiene una lista de todos los proyectos

- **Obtener detalles de un proyecto**: `GET /projects/{projectId}`
  - Devuelve información detallada sobre un proyecto específico

- **Eliminar un proyecto**: `DELETE /projects/{projectId}`
  - Elimina un proyecto existente

- **Actualizar un proyecto**: `PUT /users/{userId}/projects{projectId}`
  - Actualiza la información de un proyecto existente

### Tareas

- **Crear una nueva tarea**: `POST /users/{userId}/projects/{projectId}/tasks`
  - Requiere autenticación
  - Crea una nueva tarea asociada a un proyecto y un usuario específicos

- **Obtener tareas por proyecto**: `GET /projects/{projectId}/tasks`
  - Obtiene una lista de todas las tareas asociadas a un proyecto específico

- **Actualizar una tarea**: `PUT /users/{userId}/projects/{projectId}/tasks/{taskId}`
  - Actualiza la información de una tarea existente

### Usuarios

- **Crear un nuevo usuario**: `POST /users`
  - Crea un nuevo usuario en el sistema

- **Autenticar un usuario**: `POST /users/logins`
  - Genera un token JWT para autenticación futura del usuario

- **Obtener usuarios**: `GET /users`
  - Obtiene una lista de todos los usuarios registrados

Todos los endpoints están documentados y siguen las mejores prácticas RESTful. La mayoría de los endpoints requieren autenticación para garantizar la seguridad de la API.

## Pruebas

Puedes probar todos los endpoints haciendo peticiones HTTP GET, POST, PUT, DELETE a los endpoints correspondientes. Por ejemplo:

- Para crear un nuevo proyecto: `POST http://localhost:8080/users/{userId}/projects`
- Para obtener una lista de proyectos: `GET http://localhost:8080/projects`
- Para autenticarse: `POST http://localhost:8080/users/logins`

## Requisitos previos

Antes de ejecutar la aplicación, asegúrate de tener instalado:

- Java Development Kit (JDK) 17
- Maven 3.x

## Cómo ejecutar la aplicación
1. Clona el repositorio
2. Instala las dependencias
3. Ejecuta la aplicación
