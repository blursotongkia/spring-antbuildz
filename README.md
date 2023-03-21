# Anti-Social
## _SpringBoot Application_

[![N|Solid](https://cldup.com/dTxpPi9lDf.thumb.png)](https://nodesource.com/products/nsolid)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Anti-social is a Java application that uses spring as a framework to provide REST API communication between the server and the client

- Spring Data JPA
- Hibernate
- Maven
- Apache
- JS-powered HTML5

## Installation

Anti-social requires Java Version 8+ to run.

Install the dependencies and start the server.

```sh
sudo apt update
```

For Java Version 15

```sh
sudo apt install openjdk-15-jdk-headless
java -version
```

For Maven

```sh
sudo apt install maven
mvn -version
```

For PostgreSQL

```sh
sudo apt install postgresql
postgres --version
```

## Open Source Libraries

Anti-social uses a number of open source projects to work properly:

- [Spring Boot] - an application framework and inversion of control container for the Java platform.
- [Javascript] - HTML enhanced for web apps!
- [PostgreSQL] - a powerful, open source object-relational database system.
- [lombok] - Never write another getter or equals method again.
- [Bootstrap] - great UI boilerplate for modern web apps
- [jQuery] - a JavaScript library designed to simplify HTML DOM tree traversal and manipulation, as well as event handling, CSS animation, and Ajax.

## Steps on running the SpringBoot Application 

- Switch to Standard Mode under Java Projects
- Install extension "Lombok Annotations Support for VS Code"
- Install PostgreSQL and start pgAdmin4, create new database "antisocial"
- Open cmd.exe and navigate the filepath to "project-IS442G1-anti-social-group"
- Run the command below `mvnw spring-boot:run` to kick start the SpringBoot Application
- Tables are automatically created when the application runs as it is using ORM
- Import the insert_tables.sql into PostgreSQL to populate data.
