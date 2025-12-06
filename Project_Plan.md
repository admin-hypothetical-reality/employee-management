# Employee Management System Implementation Plan

## Goal Description
Build an Employee Management System with a **Spring Boot Backend** and a **Python (Flask) Frontend**.
- **Admin**: Manage employees, leaves, and payroll.
- **Employee**: View leave status and salary history.

## User Review Required
> [!NOTE]
> **Frontend Technology**: Proceeding with **Python (Flask)** as originally requested. This will serve HTML templates and communicate with the backend via REST APIs.

## Project Structure
```text
employee-management-system/
├── backend/ (Spring Boot)
│   ├── src/main/java/com/ems/
│   │   ├── EmsApplication.java
│   │   ├── config/
│   │   │   └── CorsConfig.java
│   │   ├── controller/
│   │   │   ├── AuthController.java
│   │   │   ├── AdminController.java
│   │   │   └── EmployeeController.java
│   │   ├── model/
│   │   │   ├── User.java
│   │   │   ├── LeaveRequest.java
│   │   │   └── Payroll.java
│   │   ├── repository/
│   │   │   ├── UserRepository.java
│   │   │   ├── LeaveRequestRepository.java
│   │   │   └── PayrollRepository.java
│   │   └── service/
│   │       ├── UserService.java
│   │       ├── LeaveService.java
│   │       └── PayrollService.java
│   └── src/main/resources/
│       └── application.properties
└── frontend/ (Python Flask)
    ├── app.py
    ├── requirements.txt
    ├── static/
    │   └── css/
    │       └── style.css
    └── templates/
        ├── login.html
        ├── admin_dashboard.html
        └── employee_dashboard.html
```

## Detailed Class Design (Backend)

### 1. Entities (`com.ems.model`)

#### `User.java`
Represents both Admin and Employees.
- `Long id` (Primary Key)
- `String name`
- `String email` (Unique)
- `String password`
- `String role` (Enum: ADMIN, EMPLOYEE)
- `Double salary`
- `String department`
- `LocalDate joinDate`

#### `LeaveRequest.java`
- `Long id`
- `User employee` (ManyToOne)
- `LocalDate startDate`
- `LocalDate endDate`
- `String reason`
- `String status` (PENDING, APPROVED, REJECTED)

#### `Payroll.java`
- `Long id`
- `User employee` (ManyToOne)
- `String month` (e.g., "January 2024")
- `Double amount`
- `String status` (PAID, PENDING)

### 2. Services (`com.ems.service`)

#### `UserService`
- `User registerUser(User user)`
- `User authenticate(String email, String password)`
- `List<User> getAllEmployees()`

#### `LeaveService`
- `LeaveRequest applyLeave(LeaveRequest request)`
- `List<LeaveRequest> getLeavesByEmployee(Long employeeId)`
- `List<LeaveRequest> getAllPendingLeaves()`
- `void approveLeave(Long leaveId)`

#### `PayrollService`
- `void generatePayroll(Long employeeId, String month, Double amount)`
- `List<Payroll> getPayrollHistory(Long employeeId)`

### 3. Controllers (`com.ems.controller`)

#### `AuthController`
- `POST /api/auth/login`: Returns User details on success.

#### `AdminController`
- `POST /api/admin/employees`: Add new employee.
- `GET /api/admin/employees`: List all employees.
- `PUT /api/admin/leaves/{id}/approve`: Approve leave.
- `POST /api/admin/payroll`: Generate payroll record.

#### `EmployeeController`
- `GET /api/employee/{id}/leaves`: View my leaves.
- `POST /api/employee/leaves`: Apply for leave.
- `GET /api/employee/{id}/payroll`: View my salary history.

## Frontend Design (Python Flask)

### `app.py` (Routes)
- `/`: Renders `login.html`.
- `/login` (POST): Calls Backend `/api/auth/login`. Stores user in session.
- `/admin/dashboard`: Renders `admin_dashboard.html`. Fetches data from Backend `/api/admin/*`.
- `/employee/dashboard`: Renders `employee_dashboard.html`. Fetches data from Backend `/api/employee/*`.
- `/logout`: Clears session.

## Verification Plan
### Automated Tests
- **Backend**: JUnit tests for `UserService` and `LeaveService` logic.
- **Frontend**: Manual click-through verification.

### Manual Verification
1.  **Admin Flow**: Login -> Add Employee -> View Employee List -> Approve Leave.
2.  **Employee Flow**: Login -> Apply Leave -> Check Status -> View Payroll.
