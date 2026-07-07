# Rest Assured Practice

API automation practice project built with Rest Assured, JUnit 5, and Maven.

---

## Contents

- [Why and What](#why-and-what)
- [Technologies](#technologies)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running Tests](#running-tests)
- [Features](#features)
- [Future Improvements](#future-improvements)

---

## Why and What

This repository contains API automation practices developed using Rest Assured and JUnit 5.

The purpose of this project is to improve API testing skills while building a maintainable and reusable automation framework. It includes authentication scenarios, endpoint validation, reusable configurations, helper classes, and JSON Schema validation.

---

## Technologies

- Java 21
- Maven
- Rest Assured
- JUnit 5
- Hamcrest
- JSON Schema Validator
- GitHub Actions

---

## Project Structure

```text
src
├── test
│   ├── java
│   │   ├── base
│   │   ├── helper
│   │   └── tests
│   └── resources
│       └── schemas
```

---

## Prerequisites

- Java 21 or later
- Maven 3.9+
- Git

---

## Installation

Clone the repository:

```bash
git clone https://github.com/Seletria/rest-assured-practice.git
```

Navigate to the project directory:

```bash
cd rest-assured-practice
```

Install dependencies:

```bash
mvn clean install
```

---

## Running Tests

Run the complete test suite:

```bash
mvn test
```

Run a specific test class:

```bash
mvn -Dtest=ProductsApiTest test
```

---

## Features

- ✔ Authentication API Tests
- ✔ Product API Tests
- ✔ JSON Schema Validation
- ✔ Reusable BaseTest Architecture
- ✔ Request Body Helper Methods
- ✔ GitHub Actions CI

---

## Future Improvements

- Add negative test scenarios
- Add data-driven testing
- Integrate Allure Reports
- Add Docker support
- Support multiple environments
- Increase endpoint coverage
