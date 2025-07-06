
# 🍽️ Restaurant API

A Spring Boot REST API for managing restaurant menu items and customer orders. Supports CRUD operations for menu items and orders, including validation, relational mapping, and unit testing.

---

## 📖 Table of Contents
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Getting Started](#-getting-started)
- [API Endpoints](#-api-endpoints)
- [Database Schema](#-database-schema)
- [Sample API Requests](#-sample-api-requests)
- [Testing](#-testing)
- [Future Improvements](#-future-improvements)
- [Author](#-author)

---

## ✨ Features

✅ CRUD operations for Menu Items  
✅ CRUD operations for Orders  
✅ Relational mapping between `menu_items`, `orders`, and `order_items` tables  
✅ Validation using Jakarta Bean Validation  
✅ Custom error handling with proper HTTP status codes  
✅ Unit tests for API endpoints (JUnit 5 + Mockito)  

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.2**
- **Spring Data JPA**
- **H2 Database** (for testing) / PostgreSQL/MySQL
- **JUnit 5 & Mockito** (for unit testing)
- **Maven** (build tool)

---

## 🚀 Getting Started

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/Aneon21/restaurant-api.git
cd restaurant-api
```

### 2️⃣ Configure the Database
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

### 3️⃣ Build and Run
```bash
mvn clean install
mvn spring-boot:run
```

API will run at: [http://localhost:8080](http://localhost:8080)

---

## 📬 API Endpoints

### 📝 Menu Endpoints

| Method | Endpoint           | Description                |
|--------|---------------------|----------------------------|
| POST   | `/menu`             | Create a new menu item     |
| GET    | `/menu`             | Get all menu items         |
| GET    | `/menu/{id}`        | Get menu item by ID        |
| PUT    | `/menu/{id}`        | Update menu item           |
| DELETE | `/menu/{id}`        | Delete menu item           |

---

### 📦 Order Endpoints

| Method | Endpoint            | Description                |
|--------|----------------------|----------------------------|
| POST   | `/orders`            | Create a new order         |
| GET    | `/orders`            | Get all orders             |
| GET    | `/orders/{id}`       | Get order by ID            |
| PUT    | `/orders/{id}`       | Update an existing order   |
| DELETE | `/orders/{id}`       | Delete an order            |

---

## 🗄️ Database Schema

**Tables**:
- `menu_items`: Stores menu items
- `orders`: Stores customer orders
- `order_items`: Maps orders to menu items with quantities

**Entity Relationships:**
- One `Order` → Many `OrderItems`
- One `MenuItem` → Many `OrderItems`

---

## 📦 Sample API Requests

### ✅ Create Menu Item
**POST** `/menu`
```json
{
  "name": "Buffalo Wings",
  "description": "Crispy wings with spicy buffalo sauce",
  "cost": 300
}
```

---

### ✅ Create Order
**POST** `/orders`
```json
{
  "customerName": "John Doe",
  "deliveryAddress": "123 Main Street, Springfield",
  "items": [
    {
      "menuItemId": 3,
      "quantity": 1
    }
  ]
}
```

**Response**
```json
{
  "id": 4,
  "customerName": "John Doe",
  "deliveryAddress": "123 Main Street, Springfield",
  "status": "PENDING",
  "cost": 300,
  "items": [
    {
      "id": 3,
      "menuItemId": 3,
      "menuItemName": "Buffalo Wings",
      "quantity": 1,
      "costPerItem": 300
    }
  ]
}
```

---

## ✅ Testing
Unit tests are written for all API endpoints using:
- **JUnit 5**
- **Mockito**
- **Spring Boot Starter Test**

To run tests:
```bash
mvn test
```

---

## 🌱 Future Improvements

- Add JWT-based Authentication & Authorization
- Implement Pagination & Filtering
- Dockerize the application
- CI/CD integration for automated testing & deployment

---

## 👨‍💻 Author

- **Sayak Bose**
- 📧 sayakbose20@hotmail.com
- 🌐 [GitHub](https://github.com/Aneon21) | [LinkedIn](https://www.linkedin.com/in/sayakbose2000/)

---
