# My E-commerce Repo

Spring Boot 3 / Java 21 backend for e-commerce authentication with Shop Owner registration/login.

## Features
- JWT-based auth responses
- Spring Security + HTTP Basic enabled
- Layered architecture (Entity, Repository, Service, Controller)
- Registration mocked in-memory (per requirement), with DB fallback for login
- PostgreSQL-ready configuration
- Email confirmation on registration
- OpenAPI docs: `/swagger-ui/index.html`
- Unit tests for auth service

## Run
```bash
mvn spring-boot:run
```
