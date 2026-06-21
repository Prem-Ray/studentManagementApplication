# Student Management System

A RESTful Spring Boot application designed to manage Students, Professors, Subjects, and Admission Records. This project demonstrates CRUD operations, DTO-based architecture, JPA entity relationships, and PostgreSQL integration following clean layered architecture principles.

## Features

### Student Management

* Create, update, delete, and retrieve students
* Search students by ID and name
* Manage student-professor associations

### Professor Management

* Create and manage professors
* Assign professors to students
* View professors associated with a student

### Subject Management

* Create and manage subjects
* Assign subjects to professors

### Admission Record Management

* Store and manage admission details for students

### Technical Features

* DTO Pattern for API responses
* Spring Data JPA and Hibernate
* PostgreSQL Database Integration
* Exception Handling
* Layered Architecture (Controller, Service, Repository)

## Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Lombok
* ModelMapper

## Entity Relationships

### Student ↔ Professor

**Many-to-Many**

* A student can have multiple professors.
* A professor can teach multiple students.

### Professor ↔ Subject

**One-to-Many**

* One professor can teach multiple subjects.
* Each subject belongs to one professor.

### Student ↔ AdmissionRecord

**One-to-One**

* Each student has one admission record.
* Each admission record belongs to one student.

## Project Structure

```text
src/main/java
├── Controller
├── Service
├── Repository
├── Entity
├── DTO
├── utils
```


## Key Concepts Implemented

* RESTful API Development
* CRUD Operations
* DTO Pattern
* Entity Relationships
* JPA & Hibernate
* PostgreSQL Integration
* Dependency Injection
* Exception Handling
* Layered Architecture
* ModelMapper Configuration



## Author

**Premanshu Ray**

Java Backend Developer | Spring Boot Enthusiast
