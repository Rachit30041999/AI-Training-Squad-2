````markdown
<!-- Author: Rachit30041999 -->
# Number Guessing Game - Project Documentation

## Project Overview
This is a number guessing game where players guess a randomly generated number. The game features:
- Multiple difficulty levels based on number of digits
- Score tracking based on number of attempts
- User registration and history tracking

## Technical Stack
- Frontend: Angular 19+ with Material UI
- Backend: Spring Boot 3 with Java 17+
- Database: MySQL

## Game Rules
1. Default range: Based on number of digits (1-9 for 1 digit, 10-99 for 2 digits, etc.)
2. Maximum attempts: 10 per round
3. Score calculation: 100 - (attempts * 5)

## Core Features
- User registration (name, email)
- Difficulty selection (1-5 digits)
- Attempt tracking
- Score history
- Hint system after 5 attempts

## API Endpoints
```
GET  /api/v1/users         # List users
POST /api/v1/users         # Register user
GET  /api/v1/games        # Get game history
POST /api/v1/games        # Start new game
PUT  /api/v1/games/{id}   # Submit guess
```

## Data Models
### User
```json
{
  "id": number,
  "name": string,
  "email": string
}
```

### Game
```json
{
  "id": number,
  "userId": number,
  "digits": number,
  "targetNumber": number,
  "attempts": number[],
  "complete": boolean,
  "score": number
}
```

## Development Setup
```bash
# Backend
cd backend
./mvnw spring-boot:run

# Frontend
cd frontend
npm install
ng serve
```

## Testing Guidelines
- Unit tests required for all services
- E2E tests for critical game flows
- Test edge cases: boundary numbers, invalid inputs

## Game Logic
1. User selects number of digits (n)
2. System generates random n-digit number
3. Range automatically set: 10^(n-1) to (10^n)-1
4. User gets 10 attempts to guess
5. Hints provided after 5th attempt
6. Score calculated based on attempts used

## Deployment
```bash
# Build
./mvnw clean package
ng build --prod

# Docker
docker-compose up -d
```

## Next Steps
- [ ] Add multiplayer support
- [ ] Implement leaderboard
- [ ] Add difficulty-based scoring
- [ ] Support custom ranges

*Note: This is a living document - update as features are added/modified*