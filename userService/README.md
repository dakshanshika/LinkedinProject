# User Service (Spring Boot + Eureka + PostgreSQL Docker)

## Overview

User Service microservice providing **signup** and **login (JWT)** APIs.
Uses **PostgreSQL (Docker)** and registers with **Eureka Server**.

---

## Tech Stack

* Java, Spring Boot
* Spring Data JPA
* PostgreSQL (Docker)
* Eureka Client
* JWT

---

## API Endpoints

### Signup

```
POST /auth/signup
```

### Login

```
POST /auth/login
```

---

## PostgreSQL (Docker)

Run:

```
docker run -d \
  --name postgres-db \
  -e POSTGRES_DB=userdb \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=admin \
  -p 5432:5432 \
  postgres:latest
```

---

## Configuration

```
spring.application.name=userService
server.port=9020

spring.datasource.url=jdbc:postgresql://localhost:5432/userdb
spring.datasource.username=admin
spring.datasource.password=admin

spring.jpa.hibernate.ddl-auto=update

jwt.secretKey=your-secret-key

eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```

---

## Run

1. Start PostgreSQL (Docker)
2. Start Eureka Server → http://localhost:8761
3. Run app:

```
mvn spring-boot:run
```

---

## Notes

* Enable async using `@EnableAsync`
* Keep JWT secret secure
* Do not expose DB credentials in production
