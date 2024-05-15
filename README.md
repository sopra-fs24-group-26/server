# Saboteur Web Version

A webversion of the board game Saboteur, project for the Software Engineering Lab FS24 at UZH. <br>
Our focus was to implement something that would exite us, leading to the creation of a simple web version of a fun board game we had played together

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Make sure you have the following installed:

-   [Node.js](https://nodejs.org/)
-   [Gradle](https://gradle.org/)

### Installing

1. Clone the repository
2. Navigate to project directory and build the server: ./gradlew build
3. Run the server locally: ./gradlew bootrun

## Running the tests

The test suite mainly consists of three parts: ControllerTest, RepositoryIntegrationTest and ServiceTests.

The ControllerTest is responsible to test REST endpoints, ServiceTest contains unit tests, while RepositoryIntegrationTest asserts interaction with database behaves as expected.

To run the test, use the gradle task bar, click on "Task" -> "verification" -> "test" to run the test suite. A JaCoCo test report will be generated automatically.

## Deployment

Add additional notes about how to deploy this on a live system

## Technologies used

### Server

-   [Spring Boot](https://spring.io/projects/spring-boot) - Framework for building Java-based web applications
-   [Gradle](https://gradle.org/) - Build automation
-   [JUnit](https://junit.org/junit5/) - Testing framework for Java
-   [SonarQube](https://www.sonarqube.org/) - Code quality and security analysis tool
-   [JaCoCo](https://www.jacoco.org/jacoco/) - Java Code Coverage Library

### Client

-   [npm](https://www.npmjs.com/) - Package manager
-   [React](https://reactjs.org/) - JavaScript library for building user interfaces
-   [Phaser](https://phaser.io/) - The Game Framework used
-   [DiceBear](https://www.dicebear.com/) - Avatar library

## High-level components

Our project consists of three main high-level components: Controller, Service and Repository.

### Controller

The Controller is takes care of REST endpoints. Valid requests and expected behavior of
each request to server is defined here. This layer has direct dependency with the Service layer.

### Service

The Service component handles actions called by Controller and interaction with database. There are three classes associated
with this layer: PlayerService, SessionService and TileService that each operate with the corresponding type of entity. The
Service layer communicates directly with the database/ Repository layer.

### Repository

The Repository layer represents the database. We use the Spring framework's JPARepository library for persistence.

### General remarks

In our project, the server only keeps track of the crucial information regarding a session/ game. It receives update
request from client, updates the information in database, but doesn't actively send data to client. The client fetches
game state in a constant time interval via the ping request.

## Roadmap - Ideas for future implementations

-   Make personalizable Avatar creation
-   Add a timer to a players turn
-   Add chat to game
-   Add animations/more particles

## Pattern

**Issue:** "Userstory: Issue Summary" <br>
**Branch:** "Issuenumber-issue-description" <br>
**Commit:** "Issuenumber: Commit message" <br>
**PR:** "Userstory: PR/Issue description" <br>

## Workflow

1. Create Userstories
2. Create architecture through in person meeting.
3. Create issues based on userstories, connecting them in development tasks.
4. Assign issues to members.
5. Members create branches and commit changes.
6. Members open pull requests which the others review.
7. When merged, issue gets closed.

## Formatting

-   Generally good structuring in folders (for example for all DTOs)
-   Generally small functions and classes
-   For TS: Prettier formatting
-   For Java: still to find out

## Authors

-   **Patric Brandao** - [Patertuck](https://github.com/Patertuck)
-   **Noah Bussinger** - [C0DECYCLE](https://github.com/C0DECYCLE)
-   **Leon Braga** - [Twhining](https://github.com/Twhining)
-   **Ting-Chun Huang** - [paul891112](https://github.com/paul891112)
-   **Roxane Jaecklin** - [Croxsy](https://github.com/Croxsy)

See also the list of [contributors](https://github.com/sopra-fs24-group-26/server/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

We thank the team of the Sopra FS24 modul for giving us the opportunity to freely work on a project.
