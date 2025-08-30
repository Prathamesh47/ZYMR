##  Movie Management API

A backend project built with **Spring Boot** and **MongoDB** that provides CRUD operations for managing movies.
It follows a layered architecture (**Controller -> Service -> Repository**) and includes **Swagger/OpenAPI documentation** and **unit testing**.

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
├── controller/     
├── service/         
├── repository/      
├── model/          
├── exception/       
└── test/            
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

 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

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

```markdown
 Movie Management - Docker Setup Guide

 Step 1: Package Your Spring Boot App

Before Dockerizing, build your app as a JAR.

```bash
mvn clean package
```

* This will create a JAR in the `target/` folder, e.g., `movie-management-0.0.1-SNAPSHOT.jar`.
* Make sure your `application.properties` or `application.yml` is configured properly (like MongoDB URI if using an external DB).

---

## Step 2: Create a Dockerfile

At the root of your project (same level as `pom.xml`), create a file named **Dockerfile**:

```dockerfile
# Step 1: Use a base image with Java 17
FROM eclipse-temurin:17-jdk-alpine

# Step 2: Set a working directory inside the container
WORKDIR /app

# Step 3: Copy the JAR file into the container
COPY target/movie-management-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port the app will run on
EXPOSE 8080

# Step 5: Run the Spring Boot app
ENTRYPOINT ["java","-jar","app.jar"]
```

**Notes:**

* `eclipse-temurin:17-jdk-alpine` is a lightweight JDK image.
* Port `8080` is the default Spring Boot port; change it if you configured a different port.

---

## Step 3: Build Docker Image

Run this command from the project root:

```bash
docker build -t movie-management:1.0 .
```

* `-t` assigns a name and tag to the image.
* `.` tells Docker to use the Dockerfile in the current directory.

---

## Step 4: Run Docker Container

```bash
docker run -p 8080:8080 --name movie-management-app movie-management:1.0
```

* `-p 8080:8080` maps container port `8080` to host port `8080`.
* `--name` assigns a custom container name to avoid random names.
* Your app should now be accessible at [http://localhost:8080](http://localhost:8080).

---

## Step 5: Optional – Use Docker Compose (MongoDB + App)

Create a `docker-compose.yml` if you want to run **MongoDB and Spring Boot together**:

```yaml
version: '3.8'
services:
  mongo:
    image: mongo:7.0
    container_name: mongo
    ports:
      - "27017:27017"
    environment:
      MONGO_INITDB_ROOT_USERNAME: root
      MONGO_INITDB_ROOT_PASSWORD: root

  app:
    build: .
    container_name: movie-management-app
    ports:
      - "8080:8080"
    depends_on:
      - mongo
    environment:
      SPRING_DATA_MONGODB_URI: mongodb://root:root@mongo:27017/admin
```

Run both services:

```bash
docker-compose up --build
```

* This will start MongoDB and your Spring Boot app together.

---

## Step 6: Verify

* Open your browser: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) → Swagger docs should load.
* Your endpoints are now fully accessible via Docker.

---

```
* Added pagination to GET /movies

