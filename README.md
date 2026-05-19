# Bame Plastic Backend System Documentation

## AOOP Project — Multiplayer Game Backend
### Java Spring Boot + PostgreSQL + WebSocket(STOMP)

---

# Table of Contents

1. Project Overview  
2. Backend Responsibilities  
3. Technology Stack  
4. Spring Boot Dependencies  
5. Backend Workflow  
6. Database System  
7. Entity Relationships  
8. Repository Layer  
9. Security System  
10. REST API Flow  
11. WebSocket Architecture  
12. How WebSocket Works with Backend  
13. Multiplayer Session Flow  
14. Backend Package Structure  
15. OOP Design Patterns  
16. Current Progress  
17. Future Work  
18. How to Run the Backend  

---

# 1. Project Overview

## What is Bame Plastic?

Bame Plastic is a multiplayer 2.5D co-op bus simulation game inspired by the chaotic local bus system of Dhaka, Bangladesh.

The backend system is developed using:

- Java 17
- Spring Boot
- PostgreSQL
- WebSocket(STOMP)

The backend controls:

- Player accounts
- Game sessions
- Multiplayer synchronization
- Passenger management
- Route management
- Real-time game updates
- Event systems
- Database storage
- Security and authentication

The backend follows a **server-authoritative architecture**.

This means:

> The server controls the real game state.

Unity clients only send player actions.
The server validates everything before updating the game.

---

# 2. Backend Responsibilities

The backend system handles:

| System | Responsibility |
|---|---|
| Authentication | Register/login players |
| Database | Store game data |
| Multiplayer | Manage game sessions |
| WebSocket | Real-time communication |
| Routes | Route information |
| Passengers | Passenger handling |
| Events | Random gameplay events |
| Security | Authorization and protection |
| Synchronization | Sync all players in real-time |

---

# 3. Technology Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4.0.6 |
| Database | PostgreSQL |
| Real-time Communication | WebSocket (STOMP) |
| Security | Spring Security + JWT |
| Build Tool | Maven |
| IDE | IntelliJ IDEA |

---

# 4. Spring Boot Dependencies

## Dependencies Used

```xml
<dependencies>

    <!-- Spring Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- PostgreSQL -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Spring Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- WebSocket -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>

    <!-- Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- DevTools -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
    </dependency>

</dependencies>
```

---

# 5. Backend Workflow

## Complete Backend Process

The backend works in the following order:

---

## Step 1 — Spring Boot Server Starts

When the backend starts:

- Spring Boot initializes
- Database connection established
- Hibernate creates tables
- Security configuration loads
- WebSocket server starts
- REST API endpoints become active

Server runs on:

```text
http://localhost:8080
```

---

## Step 2 — Unity Client Connects

The Unity game client sends requests to the backend.

Example:

```text
http://localhost:8080/api/test/players
```

The backend processes the request and returns JSON data.

---

## Step 3 — Database Operations

Spring Data JPA communicates with PostgreSQL.

Example flow:

```text
Controller → Service → Repository → PostgreSQL
```

The backend stores:

- Players
- Sessions
- Routes
- Passengers
- Events
- Scores

---

## Step 4 — Multiplayer Synchronization

WebSocket handles real-time multiplayer communication.

Players send actions.

Backend validates them.

Updated game state is broadcast to all connected players.

---

# 6. Database System

## PostgreSQL Setup

Database Name:

```text
bame_plastic_db
```

Default Port:

```text
5432
```

---

## Application Properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bame_plastic_db
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

---

# 7. Entity Relationships

| Relationship | Type |
|---|---|
| Player ↔ GameSession | Many-to-Many |
| GameSession → Bus | One-to-One |
| GameSession → Route | Many-to-One |
| Bus → Passenger | One-to-Many |
| GameSession → GameEvent | One-to-Many |

---

# 8. Repository Layer

Repositories communicate directly with PostgreSQL.

Example:

```java
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByUsername(String username);

    boolean existsByEmail(String email);
}
```

---

## Repository Responsibilities

| Repository | Purpose |
|---|---|
| PlayerRepository | Player data |
| BusRepository | Bus data |
| PassengerRepository | Passenger data |
| RouteRepository | Route data |
| GameSessionRepository | Multiplayer sessions |
| GameEventRepository | Random events |

---

# 9. Security System

## Spring Security

Spring Security protects:

- API endpoints
- Player accounts
- Multiplayer sessions
- Sensitive data

---

## Current Security Rules

| Endpoint | Access |
|---|---|
| /api/test/** | Public |
| /ws/** | Public |
| Others | Authentication Required |

---

## JWT Authentication (Planned)

Future authentication flow:

1. Player logs in
2. Backend generates JWT token
3. Unity stores token
4. Every request includes JWT
5. Backend validates token

Example:

```http
Authorization: Bearer jwt_token_here
```

---

# 10. REST API Flow

## REST API Communication

REST APIs handle:

- Login
- Registration
- Database retrieval
- Route loading
- Profile management

---

## Example API Flow

### Unity Sends Request

```http
GET /api/test/players
```

---

### Spring Boot Controller Receives

```java
@GetMapping("/players")
public List<Player> getPlayers() {
    return playerRepository.findAll();
}
```

---

### Repository Fetches Data

```java
playerRepository.findAll();
```

---

### PostgreSQL Returns Data

Backend converts data into JSON.

---

### Unity Receives Response

```json
[
  {
    "id": 1,
    "username": "toufiq"
  }
]
```

---

# 11. WebSocket Architecture

## Why WebSocket?

REST API is not suitable for real-time multiplayer systems.

WebSocket provides:

- Persistent connection
- Instant updates
- Low latency
- Bidirectional communication

Perfect for multiplayer synchronization.

---

# 12. How WebSocket Works with Backend

## WebSocket Connection Flow

---

## Step 1 — Backend Starts WebSocket Server

Spring Boot creates a WebSocket endpoint.

Example:

```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/topic");

        registry.setApplicationDestinationPrefixes("/app");
    }
}
```

---

## Step 2 — Unity Connects to WebSocket

Unity connects using:

```text
ws://localhost:8080/ws
```

After connection:

- Handshake happens
- Session created
- Client becomes connected

---

## Step 3 — Players Subscribe to Topics

Each game room has its own topic.

Example:

```text
/topic/session/ABC123
```

All players inside the same room subscribe to this topic.

This allows:

- Shared updates
- Multiplayer synchronization
- Real-time communication

---

## Step 4 — Unity Sends Player Action

Example JSON:

```json
{
  "playerId": 1,
  "action": "MOVE_LEFT",
  "timestamp": 1723456789
}
```

Destination:

```text
/app/player/move
```

---

## Step 5 — Spring Boot Receives Message

Example:

```java
@MessageMapping("/player/move")
public void move(PlayerMoveDTO dto) {

    gameService.processMove(dto);
}
```

The backend now processes the player action.

---

## Step 6 — Server Validates Action

Backend checks:

- Is player authenticated?
- Is movement valid?
- Is player inside session?
- Is speed legal?
- Is game active?

If invalid:

```text
Action rejected
```

If valid:

```text
Game state updated
```

---

## Step 7 — Backend Broadcasts Updated State

Example:

```java
messagingTemplate.convertAndSend(
    "/topic/session/ABC123",
    updatedGameState
);
```

All connected players instantly receive the update.

---

## Step 8 — Unity Updates Game

Every Unity client updates:

- Bus position
- Passenger state
- Shared money
- Events
- Game objects

All players now see the same synchronized world.

---

# 13. Multiplayer Session Flow

## Session Creation

### Host Creates Room

Backend generates:

```text
ABC123
```

Session stored in database.

---

## Player Joining

Player joins using room code.

Backend:

- Validates session
- Assigns player role
- Adds player to room
- Connects WebSocket topic

---

## Match Running

During gameplay:

- Players send inputs
- Server validates
- State updates broadcast
- Events synchronized

---

## Match End

Backend:

- Calculates score
- Saves result
- Updates leaderboard
- Closes session

---

# 14. Backend Package Structure

```text
src/main/java/com/bameplastic/backend/

├── BackendApplication.java
├── config/
│   ├── SecurityConfig.java
│   └── WebSocketConfig.java
│
├── controller/
│   ├── TestController.java
│   └── GameController.java
│
├── entity/
│   ├── Player.java
│   ├── Bus.java
│   ├── Passenger.java
│   ├── Route.java
│   ├── GameSession.java
│   └── GameEvent.java
│
├── repository/
│   ├── PlayerRepository.java
│   ├── BusRepository.java
│   ├── PassengerRepository.java
│   ├── RouteRepository.java
│   ├── GameSessionRepository.java
│   └── GameEventRepository.java
│
├── service/
│   ├── GameService.java
│   ├── PassengerService.java
│   └── SessionService.java
│
└── websocket/
    ├── GameWebSocketController.java
    └── WebSocketEventListener.java
```

---

# 15. OOP Design Patterns

| Pattern | Usage |
|---|---|
| Singleton | GameSessionManager |
| Factory | RandomEventFactory |
| State | GameSession states |
| Strategy | Player role strategies |
| Observer | Event system |
| Command | Player actions |
| Decorator | Bus conditions |
| Chain of Responsibility | Fare calculation |
| Template Method | Event generation |
| Facade | Unity communication |
| Mediator | Session communication |

---

# 16. Current Progress

## Completed

- Spring Boot setup
- PostgreSQL integration
- Entity creation
- Repository layer
- Security configuration
- Basic API testing
- Database table creation

---

## In Progress

- WebSocket multiplayer system
- Game loop service
- Passenger synchronization
- Session management

---

## Future Work

- JWT authentication
- Full multiplayer synchronization
- Random event scheduler
- Leaderboard system
- Match history
- Unity integration

---

# 17. How to Run the Backend

## Step 1 — Start PostgreSQL

Make sure PostgreSQL is running.

---

## Step 2 — Open Project

```bash
cd backend
```

---

## Step 3 — Run Spring Boot

```bash
./mvnw spring-boot:run
```

---

## Step 4 — Verify Backend

Backend URL:

```text
http://localhost:8080
```

Test API:

```text
http://localhost:8080/api/test/players
```

WebSocket Endpoint:

```text
ws://localhost:8080/ws
```

---

# Final Architecture Diagram

```text
Unity Client 1
       │
Unity Client 2
       │
Unity Client 3
       │
       ▼
Spring Boot Backend
       │
       ▼
PostgreSQL Database
```

---

# Conclusion

The Bame Plastic backend system uses:

- Spring Boot
- PostgreSQL
- WebSocket(STOMP)
- Spring Security
- JPA/Hibernate

to create a scalable multiplayer backend architecture.

The backend acts as:

- Multiplayer authority
- Database manager
- Real-time synchronization server
- Security validator
- Event controller

This architecture allows all players to stay synchronized in real-time while keeping the game secure and maintainable.

---

# Author

## Bame Plastic Team
### AOOP Project — Bangladesh