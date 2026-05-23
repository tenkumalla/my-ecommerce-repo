# Modular E-commerce Backend Platform

## Modules
- `authentication`: registration/login, JWT issuance, security, mock email sender, in-memory registration response cache.
- `shop-owner`: CRUD APIs for shop-owner domain.

Each module is independently buildable and deployable.

## Tech Stack
Java 21, Spring Boot 3, Maven multi-module, Spring Security, JWT, JPA, PostgreSQL, Swagger/OpenAPI, JUnit 5, Mockito.

## Setup
1. Start database:
```bash
docker compose up -d
```
2. Set environment variables as needed:
- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `JWT_SECRET` (>= 32 chars)

## Build
From root:
```bash
mvn clean install
```
Per module:
```bash
mvn -pl authentication clean install
mvn -pl shop-owner clean install
```

## Run
```bash
mvn -pl authentication spring-boot:run
mvn -pl shop-owner spring-boot:run
```

## API Docs
- Authentication: `http://localhost:8080/swagger-ui/index.html`
- Shop-owner: `http://localhost:8081/swagger-ui/index.html`

## Auth APIs
- `POST /api/auth/register`
- `POST /api/auth/login` (Basic Auth header)
