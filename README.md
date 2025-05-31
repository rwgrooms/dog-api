# Dogs API 
Simple CRUD API for Dog Objects with JPA (Hibernate)

## Version
1.0.0

## Installation

### Get the Project
**Clone:**
bash
git clone https://github.com/rwgrooms/dogs-api.git

**OR** download the zip and open the project in **VS Code**.

### Requirements
- Java 21
- Maven
- PostgreSQL database (configured via Neon.tech)

### Configuration
Edit the file at: /src/main/resources/application.properties:

1. Login to your [Neon.tech](https://neon.tech) account.
2. On your project dashboard, click **Connect** and select **Java**.
3. Copy the connection string and set it like so:
properties
spring.datasource.url=jdbc:postgresql://<your-neon-url>
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update

Ensure the database is running before launching the app.

### Build and Run
bash
./mvnw spring-boot:run

## Technologies Used
- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL (Neon.tech)

## Project Structure

### Entity
Defines the Dog class mapped to the database table.

### Repository
Interface DogRepository extends JpaRepository<Dog, Long> with basic and custom queries.

### Service
DogService handles business logic and communicates with the repository.

### Controller
DogController exposes the REST API endpoints.

## API Endpoints

**Base URL:** http://localhost:8080/api/dogs

| Method | Endpoint                       | Description                             |
|--------|--------------------------------|-----------------------------------------|
| GET    | /api/dogs                      | Get all dogs                            |
| GET    | /api/dogs/{id}                 | Get a dog by ID                         |
| GET    | /api/dogs/breed/{breed}        | Get dogs by breed                       |
| GET    | /api/dogs/search?name=substring| Search dogs by name (case-insensitive)  |
| POST   | /api/dogs                      | Create a new dog                        |
| PUT    | /api/dogs/{id}                 | Update a dog                            |
| DELETE | /api/dogs/{id}                 | Delete a dog                            |

## Sample JSON

### Request Body
json
{
  "name": "Max",
  "description": "Playful and friendly",
  "breed": "Labrador",
  "age": 3.5,
  "activeDate": "2024-06-01"
}

### Response
json
{
  "id": 1,
  "name": "Max",
  "description": "Playful and friendly",
  "breed": "Labrador",
  "age": 3.5,
  "activeDate": "2024-06-01"
}

## Author
Robert Grooms – UNCG CSC 340  
GitHub: [https://github.com/rwgrooms]
