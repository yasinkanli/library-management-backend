# 📚 Library Management System – Backend

This is the **backend service** of the Library Management System developed with **Java Spring Boot** and **H2 in-memory database**. It handles all core operations such as managing books, authors, members, reviews, and loans.

---

## 🚀 Features

- ✅ Add, update, delete, and list books and authors
- ✅ Register library members with card numbers
- ✅ Create and manage book loans with due dates
- ✅ Submit and retrieve book reviews with ratings
- ✅ RESTful API architecture
- ✅ H2 database integration with web console
- ✅ Input validation and error handling

---

## 🛠 Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database**
- **Maven**

---

## 🔌 API Endpoints Overview

| Resource  | Method | Endpoint                  | Description              |
|-----------|--------|---------------------------|--------------------------|
| Book      | POST   | `/api/books/create`       | Create a new book        |
| Book      | GET    | `/api/books/list`         | List all books           |
| Member    | POST   | `/api/members/create`     | Register a new member    |
| Loan      | POST   | `/api/loans/borrow`       | Borrow a book            |
| Review    | GET    | `/api/reviews/byBook/{id}`| Get reviews by book ID   |
