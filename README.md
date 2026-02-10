# CarManager

A fleet management application for managing drivers and cars. Built with a Spring Boot REST API backend and a Vaadin web frontend.

## Project Structure

```
├── backend/     # REST API (Spring Boot, port 8080)
└── frontend/    # Web UI (Vaadin, port 8081)
```

## Technologies

**Backend**
- Java 21
- Spring Boot 3.2.x
- Spring Data JPA / Hibernate 6
- H2 In-Memory Database
- Swagger / OpenAPI (springdoc)
- Maven

**Frontend**
- Java 21
- Spring Boot 3.2.x
- Vaadin 24
- Maven

## Prerequisites

- Java 21+
- Maven 3.9+

## Getting Started

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

The API will be available at http://localhost:8080

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- H2 Console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:testdb`, user: `sa`)

### Frontend

```bash
cd frontend
./mvnw spring-boot:run
```

The web UI will be available at http://localhost:8081

> Both backend and frontend must be running simultaneously for the application to work.

## API Endpoints

### Drivers

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/v1/drivers/{driverId}` | Get driver by ID |
| GET | `/v1/drivers?onlineStatus=ONLINE` | List drivers by status |
| POST | `/v1/drivers` | Create a new driver |
| PUT | `/v1/drivers/{driverId}?longitude=x&latitude=y` | Update driver location |
| DELETE | `/v1/drivers/{driverId}` | Delete a driver |

### Cars

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/v1/cars` | List all cars |
| GET | `/v1/cars/{carId}` | Get car by ID |
| PUT | `/v1/cars/{carId}?licensePlate=XX` | Update car license plate |

## Running Tests

```bash
cd backend
./mvnw test
```
