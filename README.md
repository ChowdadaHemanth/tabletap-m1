# TableTap — Restaurant Table Management API

TableTap is a Spring Boot backend for managing restaurant tables and starting dining sessions through QR-code scans. It demonstrates a layered REST API backed by MySQL.

## Features

- Create, list, retrieve, update, and delete restaurant tables
- Store table capacity, status, and unique QR codes
- Scan a QR code to reuse an active session or start a new dining session
- Validate API requests and return structured error responses
- Explore endpoints through Swagger UI

## Technology

- Java 17
- Spring Boot 3
- Spring MVC
- Spring Data JPA
- MySQL
- Bean Validation
- Maven
- Springdoc OpenAPI

## Architecture

```text
HTTP request → Controller → Service → Repository → MySQL
                         ↘ DTOs and validation
```

## API endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| `POST` | `/api/admin/tables` | Create a table |
| `GET` | `/api/admin/tables` | List tables |
| `GET` | `/api/admin/tables/{id}` | Get a table |
| `PUT` | `/api/admin/tables/{id}` | Update a table |
| `DELETE` | `/api/admin/tables/{id}` | Delete a table |
| `GET` | `/api/scan?qrCode=...` | Start or retrieve a dining session |

## Run locally

Prerequisites: Java 17+, MySQL, and Maven (or use the included Maven wrapper).

1. Create a MySQL database named `tabletap_db`.
2. Set environment variables in PowerShell:

   ```powershell
   $env:DB_URL="jdbc:mysql://localhost:3306/tabletap_db"
   $env:DB_USERNAME="your_username"
   $env:DB_PASSWORD="your_password"
   ```

3. Start the application:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

4. Open Swagger UI at `http://localhost:8080/swagger-ui.html`.

## Testing

```powershell
.\mvnw.cmd test
```

Database credentials are supplied through environment variables and are not committed to the repository.
