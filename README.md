# Webhook App

A reactive webhook application built with Spring Boot that allows you to receive, process, and manage webhooks.

## Description

This application provides a platform for handling webhooks in a reactive way, using Spring WebFlux and R2DBC for non-blocking database operations. It's designed to be scalable and efficient, making it suitable for high-throughput webhook processing.

## Technologies

- Java 21
- Spring Boot 3.5.0
- Spring WebFlux (Reactive Web)
- Spring Data R2DBC (Reactive Database Connectivity)
- PostgreSQL 15.5
- Consul 1.18.0 (Service Discovery)
- Spring Cloud LoadBalancer
- Docker & Docker Compose

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- Docker and Docker Compose
- Git

## Setup and Installation

### Clone the Repository

```bash
git clone https://github.com/GabryelBoeira/Spring_cloud_webflux.git
cd webhook-app
```

### Start the Infrastructure

The application requires PostgreSQL and Consul, which can be started using Docker Compose:

```bash
docker-compose up -d
```

This will start:
- PostgreSQL on port 5432 with database name `webhookdb` (credentials: root/root)
- Consul on port 8500 with UI accessible at http://localhost:8500

### Build and Run the Application

```bash
./mvnw clean install
./mvnw spring-boot:run
```

## Development

### Database Configuration

The application is configured to connect to a PostgreSQL database. The default configuration is:

- Database: webhookdb
- Username: root
- Password: root
- Host: localhost
- Port: 5432

You can modify these settings in the application.properties file or by setting environment variables.

### Service Discovery

The application uses Consul for service discovery. The Consul UI is accessible at http://localhost:8500.

### Building for Production

To build a production-ready JAR file:

```bash
./mvnw clean package
```

The JAR file will be created in the `target` directory.

## API Documentation

*Note: API documentation will be added as the application evolves.*

## Configuration Options

The application can be configured through the `application.properties` file or environment variables:

- `spring.application.name`: The name of the application (default: "webhook app")
- `spring.r2dbc.url`: The R2DBC URL for database connection
- `spring.r2dbc.username`: Database username
- `spring.r2dbc.password`: Database password
- `spring.cloud.consul.host`: Consul host
- `spring.cloud.consul.port`: Consul port

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the [MIT License](LICENSE).
