##  Movie Management API

A backend project built with **Spring Boot** and **MongoDB** that provides CRUD operations for managing movies.
It follows a layered architecture (**Controller → Service → Repository**) and includes **Swagger/OpenAPI documentation** and **unit testing**.

---

##  Tech Stack

* **Backend Framework:** Spring Boot 3.x
* **Database:** MongoDB (NoSQL)
* **Data Access:** Spring Data MongoDB (MongoRepository)
* **Validation:** Jakarta Bean Validation (JSR 380)
* **API Documentation:** Swagger UI (via `springdoc-openapi-starter-webmvc-ui`)
* **Testing:** JUnit 5, Mockito, Spring Boot Test

---

##  Project Structure

```
movie-management/
├── controller/      # REST Controllers (API Endpoints)
├── service/         # Business Logic Layer
├── repository/      # DAO Layer (MongoRepository)
├── model/           # Entity classes (Movie)
├── exception/       # Custom exceptions
└── test/            # Unit tests
```

---

##  Features

* Create, Read, Update, and Delete (CRUD) Movies
* Validation for required fields (title, rating range, year validity)
* Swagger documentation with OpenAPI 3.0
* Unit tests for controllers & services
* RESTful API design with proper error handling

---

##  How to Run the Project

### 1. Clone the Repository

```bash
git clone https://github.com/Prathamesh47/ZYMR.git
cd ZYMR
```

### 2. Configure MongoDB

Make sure MongoDB is running locally on **port 27017** (default).
Or update the connection string in **`src/main/resources/application.properties`**:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/moviedb
server.port=8080
```

### 3. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run the `MovieManagementApplication` class from your IDE.

---

##  How to Run Tests

Run unit tests with Maven:

```bash
mvn test
```

This runs:

* **Controller Tests** (MockMvc)
* **Service Tests** (Mockito)

---

## API Documentation

Once the application is running, Swagger UI will be available at:

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

You can test endpoints directly from this UI.

---

##  API Endpoints

| Method | Endpoint       | Description           |
| ------ | -------------- | --------------------- |
| GET    | `/movies`      | List all movies       |
| GET    | `/movies/{id}` | Get movie by ID       |
| POST   | `/movies`      | Create a new movie    |
| PUT    | `/movies/{id}` | Update existing movie |
| DELETE | `/movies/{id}` | Delete movie by ID    |

---

##  Example Request

**POST** `/movies`

```json
{
  "title": "Inception",
  "director": "Christopher Nolan",
  "releaseYear": 2010,
  "genre": "Sci-Fi",
  "rating": 8.8
}
```


