# Number Guessing Game - Product Requirements Document (PRD)

## 1. Introduction
### 1.1 Purpose
Create an engaging number guessing game that challenges players to guess numbers with varying difficulty levels, providing hints and scoring based on performance.

### 1.2 Scope
The game will be a web-based application with user management, multiple difficulty levels, and a hint system.

### 1.3 Definitions
- **Attempt**: A single guess made by the player
- **Hint**: A clue provided after specific number of attempts
- **Difficulty Level**: Determined by number of digits (2-4)
- **Score**: Calculated as 100 - (attempts * 5)

## 2. Product Overview
### 2.1 Product Perspective
A standalone web application with:
- Angular 19+ frontend
- Java 17+ Spring Boot backend
- MySQL database

### 2.2 User Classes and Characteristics
- **Player**: Regular user who plays the game
- **Admin**: System administrator with additional privileges

## 3. Requirements
### 3.1 User Management
````typescript
export interface User {
    id: number;
    email: string;    // One user per email
    name: string;
    role: UserRole;
}

export enum UserRole {
    PLAYER = 'PLAYER',
    ADMIN = 'ADMIN'
}
````

### 3.2 Game Configuration
#### 3.2.1 Difficulty Levels
1. Two Digits
   - Range: 10-99
   - Max Attempts: 7
   
2. Three Digits
   - Range: 100-999
   - Max Attempts: 10
   
3. Four Digits
   - Range: 1000-9999
   - Max Attempts: 12

#### 3.2.2 Hint System
- Activates after 2 attempts
- Displays in right corner
- Persists until correct digit entered
- Auto-dismisses on correct digit entry

### 3.3 Database Structure
````sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE,
    name VARCHAR(100),
    role VARCHAR(20)
);

CREATE TABLE games (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    target_number INT,
    difficulty INT,
    attempts_count INT,
    score INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
````

## 4. Non-Functional Requirements
### 4.1 Performance
- Page load time < 2 seconds
- API response time < 500ms
- Support 100 concurrent users

### 4.2 Security
- JWT authentication
- Input validation
- XSS protection
- CSRF protection

### 4.3 Reliability
- System uptime: 99.9%
- Data backup daily
- Error logging and monitoring

## 5. Interface Requirements
### 5.1 User Interface
- Clean, modern design
- Mobile-responsive
- Accessibility compliant

### 5.2 Software Interfaces
````typescript
export interface GameAPI {
    startGame(difficulty: number): Promise<GameSession>;
    submitGuess(gameId: number, guess: number): Promise<GuessResult>;
    getHint(gameId: number): Promise<HintResponse>;
}
````

## 6. Technical Requirements
### 6.1 Development Environment
````bash
# Backend setup
./mvnw spring-boot:run

# Frontend setup
npm install
ng serve
````

### 6.2 Testing Requirements
- Unit tests coverage > 80%
- Integration tests for API endpoints
- E2E tests for critical paths

## 7. Future Enhancements
### 7.1 Planned Features
- Multiplayer mode
- Global leaderboard
- Achievement system
- Social sharing

## 8. Acceptance Criteria
### 8.1 Core Features
- User registration and login
- Three difficulty levels playable
- Hint system functional
- Score calculation accurate
- Game history tracked

### 8.2 Quality Gates
- All unit tests passing
- Code coverage requirements met
- No critical security vulnerabilities
- Performance benchmarks achieved