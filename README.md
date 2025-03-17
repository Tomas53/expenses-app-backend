# Expense Tracker Application

A full-stack web application for tracking personal expenses. This application allows users to manage people and their associated expenses, providing an intuitive interface for expense management.

## Technologies Used

### Backend
- Java 11+
- Spring Boot 2.7+
- Spring Data JPA
- H2 Database (can be configured to use MySQL, PostgreSQL, etc.)
- Maven

### Frontend
- Angular 15
- TypeScript
- Bootstrap 5
- Angular Reactive Forms

## Features

- Create, view, and delete people
- Add expenses for each person
- View expense details for each person
- Calculate total expenses
- Responsive design for desktop and mobile devices

## Prerequisites

- Java 11 or higher
- Node.js 14.x or higher
- npm 6.x or higher
- Maven 3.6 or higher

## Project Structure

```
expenses_full_app/
├── backend/                  # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/expenses/
│   │   │   │   ├── config/
│   │   │   │   ├── controller/
│   │   │   │   ├── dto/
│   │   │   │   ├── exception/
│   │   │   │   ├── model/
│   │   │   │   ├── repository/
│   │   │   │   └── service/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
└── frontend/                 # Angular frontend
    ├── src/
    │   ├── app/
    │   │   ├── components/
    │   │   ├── models/
    │   │   ├── services/
    │   │   └── ...
    │   ├── assets/
    │   └── ...
    ├── package.json
    └── angular.json
```

## Backend Setup

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd expenses_full_app
   ```

2. Build and run the Spring Boot application:
   ```bash
   cd backend
   mvn clean install
   mvn spring-boot:run
   ```

   The backend server will start on http://localhost:8080

## Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the Angular development server:
   ```bash
   ng serve
   ```

   The frontend application will be available at http://localhost:4200

## API Endpoints

### People Endpoints

- `GET /api/people` - Get all people
- `GET /api/people/{id}` - Get a person by ID
- `POST /api/people` - Create a new person
- `DELETE /api/people/{id}` - Delete a person

### Expense Endpoints

- `GET /api/expenses/person/{id}` - Get expenses by person ID
- `GET /api/expenses/person/name/{name}` - Get expenses by person name

## Database Configuration

The application uses an in-memory H2 database by default. To configure a different database:

1. Update `application.properties` in the `src/main/resources` directory with your database connection details:

```properties
# MySQL Example
spring.datasource.url=jdbc:mysql://localhost:3306/expenses_db
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect

# PostgreSQL Example
# spring.datasource.url=jdbc:postgresql://localhost:5432/expenses_db
# spring.datasource.username=postgres
# spring.datasource.password=password
# spring.jpa.hibernate.ddl-auto=update
# spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

2. Add the corresponding database driver dependency to the `pom.xml` file.

## Running the Full Application

1. Start the backend server:
   ```bash
   cd backend
   mvn spring-boot:run
   ```

2. In a separate terminal, start the frontend application:
   ```bash
   cd frontend
   ng serve
   ```

3. Access the application at http://localhost:4200

## Development

### Adding New Features

To add new features to the backend:

1. Create the necessary model classes in the `model` package
2. Create repositories in the `repository` package
3. Create DTOs in the `dto` package
4. Implement service logic in the `service` package
5. Create REST controllers in the `controller` package

To add new features to the frontend:

1. Create model interfaces in the `models` directory
2. Implement services in the `services` directory
3. Create components using Angular CLI: `ng generate component components/my-component`
4. Update routing in `app-routing.module.ts`

## Testing

### Backend Testing

Run backend tests using Maven:
```bash
cd backend
mvn test
```

### Frontend Testing

Run frontend tests using Angular CLI:
```bash
cd frontend
ng test
```

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature-name`
5. Submit a pull request
