# Person-Movie API

REST API para gestión de personas y sus películas asociada.

## Tech Stack

- **Java 17**
- **Spring Boot 4.0.6**
- **Gradle** (Build Tool)
- **Lombok** ( Boilerplate reduction)
- **SpringDoc OpenAPI** (Swagger documentation)
- **Clean Architecture / DDD** (Domain-Driven Design)

## Estructura del Proyecto

```
src/main/java/com/uteam/person_movie/
├── Domain/              # Entidades y contratos de repositorio
├── Application/          # Casos de uso, DTOs y contratos
│   ├── UseCases/        # Implementaciones de casos de uso
│   ├── Contracts/       # Interfaces y DTOs
│   └── Results/         # Result wrapper pattern
├── Infrastructure/       # Persistencia en memoria
│   └── Persistence/     # Repositorios y Unit of Work
└── Presentation/        # Controllers y advisors
    ├── Controller/     # REST endpoints
    └── Advisors/        # Exception handling
```

## Requisitos

- **JDK 17** o superior
- **Gradle** (wrapper incluido)

## Ejecutar el Proyecto

```bash
./gradlew bootRun
```

O compilar y ejecutar el JAR:

```bash
./gradlew build
java -jar build/libs/person-movie-0.0.1-SNAPSHOT.jar
```

La aplicación inicia en: `http://localhost:8080`

## API Endpoints

### Personas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/persons` | Listar todas las personas |
| GET | `/api/persons/{id}` | Obtener persona por ID |
| GET | `/api/persons/search?firstName=&lastName=` | Buscar personas |
| POST | `/api/persons` | Crear persona |
| PATCH | `/api/persons/{id}` | Actualizar parcialmente |
| DELETE | `/api/persons/{id}` | Eliminar persona |

### Películas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/persons/{personId}/movies` | Listar películas de persona |
| POST | `/api/persons/{personId}/movies` | Agregar película a persona |
| DELETE | `/api/persons/{personId}/movies/{movieTitle}` | Eliminar película |

## Documentación Swagger

Una vez corriendo, accedé a:
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

## Tests

```bash
./gradlew test
```

## Notas

- Persistencia en **memoria** (no usa base de datos)
- Implementa **Unit of Work** para tracking de cambios
- Response envuelto en `Result<T>` con status y mensajes