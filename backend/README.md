# Number Guessing Game - Backend

## Overview
Spring Boot backend for the Number Guessing Game application.

## Tech Stack
- Java 17
- Spring Boot 3.2.0
- H2 In-Memory Database
- Maven

## Project Structure
```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/game/
│   │   │   ├── controller/       # REST API endpoints
│   │   │   ├── service/          # Business logic
│   │   │   ├── model/            # Entity classes
│   │   │   ├── repository/       # Data access layer
│   │   │   ├── exception/        # Exception handling
│   │   │   └── NumberGuessingGameApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── logback-spring.xml
│   └── test/
└── pom.xml
```

## Prerequisites
- Java 17 or higher
- Maven 3.6+

## Running the Application

### Using Maven Wrapper
```bash
./mvnw spring-boot:run
```

### Using Maven
```bash
mvn spring-boot:run
```

### Using Java
```bash
mvn clean package
java -jar target/number-guessing-game-1.0.0.jar
```

## API Endpoints

### User Management
- `POST /api/v1/users/register` - Register new user
- `POST /api/v1/users/login` - User login
- `GET /api/v1/users/{id}` - Get user by ID
- `GET /api/v1/users` - Get all users

### Game Operations
- `POST /api/v1/games/start` - Start new game
- `POST /api/v1/games/{id}/guess` - Submit a guess
- `GET /api/v1/games/{id}` - Get game details
- `GET /api/v1/games/user/{userId}` - Get user's game history

## H2 Database Console
Access the H2 console at: http://localhost:8080/h2-console

**Connection Details:**
- JDBC URL: `jdbc:h2:mem:gamedb`
- Username: `sa`
- Password: (leave empty)

## Configuration
Edit `src/main/resources/application.properties` to customize:
- Server port
- Database settings
- Logging levels

## Testing
```bash
./mvnw test
```

## Logging
Logs are available in:
- Console output
- `logs/number-guessing-game.log`

## Game Rules
- Difficulty levels: 2-4 digits
- Max attempts per difficulty:
  - 2 digits: 7 attempts
  - 3 digits: 10 attempts
  - 4 digits: 12 attempts
- Score: 100 - (attempts × 5)
- Hints available after 2 attempts
