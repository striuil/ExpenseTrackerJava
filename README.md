# Expense Tracker API

A REST API for tracking personal expenses, built with Spring Boot as a self-study project. Supports creating and
querying expenses, filtering by category and date range, and summarizing spend by category and month.

### Status

This is a work in progress. The current version supports full CRUD on expenses and categories, filtering, and summary
aggregation. Planned next steps include authentication, pagination, and a persistent (non-in-memory) database.

### Tech Stack

- Java
- Spring Boot (Web, Data JPA, Validation)
- H2 (in-memory database)
- Maven

### Features

- **CRUD for expenses** — _create_, _read_, _update_, and _delete_ individual expense records
- **CRUD for categories** — manage expense categories (e.g. Food, Transport, Rent)
- **Filtering** — retrieve expenses by category and/or date range
- **Summary endpoint** — total spend in a specified period or by category
- **Validation** — request bodies are validated (e.g. required fields, positive amounts)

### API Endpoints

| Method     | Endpoint                                                  | Description                                              |
|------------|-----------------------------------------------------------|----------------------------------------------------------|
| **POST**	  | /expenses                                                 | Create a new expense                                     |
| **GET**    | /expenses                                                 | List expenses (supports category, from, to query params) |
| **GET**    | /expenses/{id}                                            | Get a single expense by ID                               |
| **PUT**    | /expenses/{id}                                            | Update an expense                                        |
| **DELETE** | /expenses/{id}                                            | Delete an expense                                        |
| **GET**    | /expenses/summary?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD | Get total spend for a specified date range               |
| **GET**    | /expenses/summary?categoryId={id}                         | Get total per-category spend                             |
| **POST**   | /categories	                                              | Create a category                                        |
| **GET**    | /categories                                               | List all categories                                      |

### Data Model

#### Category

* id
* name

#### Expense

* id
* amount (BigDecimal)
* date
* description
* category (many-to-one relationship)

### Running Locally

Make sure to open **application.properties** file to set up database connection(URL + login info) to have no problem
with running the API.

- bash: `./mvnw spring-boot:run `
- IDE: Open and run **ExpenseTrackerJavaApplication.java**

The API will be available at _http://localhost:8080_. The _H2_ console (if enabled) is available at
_http://localhost:8080/h2-console_.

### Testing Endpoints

Requests can be tested using _curl/Postman_ against _http://localhost:8080_.

### Roadmap

1. [ ] Pagination and sorting on the expense list endpoint
2. [ ] Switch from H2 to a persistent database (e.g. PostgreSQL)
3. [ ] Authentication/authorization
4. [ ] Unit and integration tests
5. [ ] Frontend client

### Motivation

Built as a self-study project to practice Spring Boot fundamentals: layered architecture (
controller/service/repository), JPA relationships, query methods, and basic API design.