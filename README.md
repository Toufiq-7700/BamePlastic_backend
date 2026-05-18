# Bame Plastic

2.5D Co-op Multiplayer Bus Simulation Game set in Dhaka, Bangladesh.

## Tech Stack
- **Backend:** Java 17, Spring Boot 4.0.6, PostgreSQL
- **Client:** Unity 2022 LTS, C#, URP
- **Communication:** REST API + WebSocket (STOMP)

## Roles
- **Driver** — Drives the bus in 3D lane-based environment
- **Conductor 1 (Outside)** — Gathers passengers from street
- **Conductor 2 (Inside)** — Collects fares inside bus

## Setup
1. Install PostgreSQL and create database `bame_plastic_db`
2. Update `application.properties` with your database credentials
3. Run `./mvnw spring-boot:run`
4. Open Unity project and connect to backend

## Database Tables
- players
- buses
- passengers
- routes
- game_sessions
- game_events
- session_players

## OOP Patterns Used
Singleton, Factory, State, Strategy, Observer, Command, Decorator, Chain of Responsibility, Template Method, Facade, Mediator