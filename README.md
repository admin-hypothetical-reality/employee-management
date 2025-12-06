# Employee Management System

A full-stack Employee Management System built with Spring Boot and Thymeleaf.

## Features

### Admin Features
- Add and manage employees
- Approve/reject leave requests
- Generate payroll records
- View all employees and their details

### Employee Features
- Apply for leave
- View leave request status
- View salary history

## Tech Stack

- **Backend**: Spring Boot 3.2.0, Java 17
- **Frontend**: Thymeleaf, HTML5, CSS3
- **Database**: MySQL 8.0
- **ORM**: Spring Data JPA / Hibernate
- **Build Tool**: Maven

## Setup Instructions

### Prerequisites
- Java JDK 17+
- Maven
- MySQL Server

### Database Setup
1. Create MySQL database:
```sql
CREATE DATABASE ems_db;
```

2. Update `src/main/resources/application.properties` with your MySQL credentials:
```properties
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

### Running the Application

1. Navigate to project directory:
```bash
cd "d:/employee management"
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

4. Open browser and go to: `http://localhost:8080`

## Demo Credentials

**Admin Login:**
- Email: admin@ems.com
- Password: admin123

**Employee Login:**
- Email: employee@ems.com
- Password: emp123

## Project Structure

```
employee-management-system/
├── src/main/java/com/ems/
│   ├── EmsApplication.java
│   ├── config/
│   │   └── DataInitializer.java
│   ├── controller/
│   │   ├── AuthController.java
│   │   ├── AdminController.java
│   │   └── EmployeeController.java
│   ├── model/
│   │   ├── User.java
│   │   ├── LeaveRequest.java
│   │   └── Payroll.java
│   ├── repository/
│   │   ├── UserRepository.java
│   │   ├── LeaveRequestRepository.java
│   │   └── PayrollRepository.java
│   └── service/
│       ├── UserService.java
│       ├── LeaveService.java
│       └── PayrollService.java
├── src/main/resources/
│   ├── application.properties
│   ├── templates/
│   │   ├── login.html
│   │   ├── admin-dashboard.html
│   │   └── employee-dashboard.html
│   └── static/css/
│       └── style.css
└── pom.xml
```

## License

MIT License
