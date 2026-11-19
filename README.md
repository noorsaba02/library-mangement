# Library Management System

A console-based Library Management System in core Java 17 (no external dependencies), built with Maven.

## Features
- Add books, register members
- List and search books (by title or author)
- Issue books (14-day loan) and return them
- Automatic late fine: Rs 5 per overdue day
- List active loans

## Project structure
```
library-management/
├── pom.xml
└── src/main/java/com/library/
    ├── Main.java                  # console menu
    ├── model/    Book, Member, Loan
    └── service/  LibraryService   # all business logic
```

## Run
Requires JDK 17+ and Maven.

```bash
mvn clean package
java -jar target/library-management-1.0.0.jar
```

Without Maven:

```bash
mkdir -p out
javac -d out $(find src/main/java -name "*.java")
java -cp out com.library.Main
```

## Ideas to extend
- Save data to files or MySQL using JDBC
- Convert to a Spring Boot REST API with a React frontend
- Add unit tests with JUnit 5
