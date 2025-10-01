# Delivery Service

This project is a **Spring Boot application** for managing the storing and finding of items in boxes for delivery services.

## 🚀 Features
- Create a box
- Load a box with items
- Check loaded items for a given box
- Check available boxes for loading
- Service layer with repository pattern
- Utility class for code generation
- Organized domain-driven package structure

## 🛠️ Tech Stack
- Java 21
- Spring Boot 3.4.11
- Spring Data JPA
- Maven
- Microsoft SQL Server (configurable)
- OpenAPI/Swagger (via springdoc)

## 📂 Project Structure
```text
src/main/java/com/miltech/deliveryservice
 ┣ 📂 config        # Application configuration
 ┣ 📂 controller    # REST controllers
 ┣ 📂 domain        # Entities and domain models
 ┣ 📂 enums         # Enumerations
 ┣ 📂 exception     # Custom exceptions
 ┣ 📂 interfaces    # Interfaces
 ┣ 📂 model         # DTOs and Models
 ┣ 📂 repository    # Spring Data JPA repositories
 ┣ 📂 service       # Business logic
 ┣ 📂 util          # Utility classes (e.g., CodeGeneratorUtils)
 ┗ DeliveryServiceApplication.java
🌐 API Endpoints (Sample)
POST   /api/v1/delivery/boxes                 → create box
PUT    /api/v1/delivery/boxes/{boxCode}/items → load box with item
GET    /api/v1/delivery/boxes/{boxCode}/items → check loaded items
GET    /api/v1/delivery/boxes/available       → available boxes
GET    /api/v1/delivery/boxes/{boxCode}/battery-level → battery level
▶️ Getting Started
Prerequisites

Java 21

Maven 3.8+
Run the Application
mvn spring-boot:run
Once running, you can access:

API docs: http://localhost:6060/swagger-ui.html

📌 Core Dependencies

spring-boot-starter-web

spring-boot-starter-data-jpa

spring-boot-starter-validation

spring-boot-starter-actuator

springdoc-openapi-starter-webmvc-ui

mssql-jdbc

lombok

modelmapper