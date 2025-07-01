# Cursos EduTech

Servicio backend para la gestión de cursos y categorías, desarrollado con Spring Boot, JPA, MySQL y Docker. Incluye integración con usuarios externos, documentación OpenAPI/Swagger y pruebas unitarias.

## Características

- CRUD de cursos y categorías
- Integración con API externa de usuarios (`api.users.url`)
- Documentación interactiva con Swagger UI (`/doc/swagger-ui.html`)
- Pruebas unitarias y de integración con JUnit y Mockito
- Variables de entorno gestionadas con `.env`
- Soporte para perfiles (`dev`, `test`)
- Contenedores Docker y despliegue automatizado en AWS EC2 (ver [docker-compose.yml](docker-compose.yml) y [dockerfile](dockerfile))
- HATEOAS para endpoints v2

## Estructura del proyecto

- `src/main/java/com/example/Cursos/` - Código fuente principal
- `src/main/resources/` - Archivos de configuración (`application.properties`, etc.)
- `src/test/java/com/example/Cursos/` - Pruebas unitarias
- `dockerfile` y `docker-compose.yml` - Contenedores y orquestación
- `.env` - Variables sensibles (no versionado)
- `.github/workflows/main.yml` - CI/CD para despliegue en AWS

## Configuración

1. **Variables de entorno**  
   Crea un archivo `.env` en la raíz con tus credenciales de base de datos y la URL de la API de usuarios:
   ```
   DB_URL=jdbc:mysql://<host>:<port>/<db>
   DB_USERNAME=usuario
   DB_PASSWORD=contraseña
   ```

2. **Perfiles de Spring**  
   - `dev`: Desarrollo local
   - `test`: Pruebas (carga datos de ejemplo con [`DataLoader`](src/main/java/com/example/Cursos/DataLoader.java))

3. **Base de datos**  
   Usa MySQL 8. El esquema se crea automáticamente.

## Ejecución local

```sh
./mvnw spring-boot:run
```

O con Docker:

```sh
docker compose up --build
```

## Endpoints principales

- **Cursos v1:** `/api/v1/cursos`
- **Categorías v1:** `/api/v1/categorias`
- **Cursos v2 (HATEOAS):** `/api/v2/cursos`
- **Categorías v2 (HATEOAS):** `/api/v2/categorias`
- **Swagger UI:** [http://localhost:8080/doc/swagger-ui.html](http://localhost:8080/doc/swagger-ui.html)

## Pruebas

```sh
./mvnw test
```

## Despliegue en AWS

El flujo de CI/CD está definido en [main.yml](.github/workflows/main.yml). Al hacer push a `main`, el código se sincroniza y despliega automáticamente en el servidor EC2 usando Docker Compose.

## Integración con API de Usuarios

El proyecto consume el endpoint externo de usuarios a través de `RestTemplate`:

- **Endpoint:** `http://localhost:8080/api/v1/usuarios/1`
- **Método:** `POST`
- **Parámetro:** `idUsuario`

Esto permite asociar un usuario como instructor al crear un nuevo curso, enviando el parámetro `idUsuario` en la petición.

---

**Autor:** Andrés Ortega