# MySQL Setup Guide for Employee Management System

## Quick Setup (3 Steps)

### Step 1: Create Database
Open **MySQL Workbench** or **MySQL Command Line** and run:

```sql
CREATE DATABASE ems_db;
```

### Step 2: Update Password
If your MySQL root password is NOT "root", update line 4 in `application.properties`:

```properties
spring.datasource.password=YOUR_ACTUAL_PASSWORD
```

### Step 3: Enable Full Application
I need to uncomment all the code. Run these find-and-replace operations:

**In all Service files** (`UserService.java`, `LeaveService.java`, `PayrollService.java`):
- Find: `//@ Service`
- Replace with: `@Service`

**In all Controller files** (`AuthController.java`, `AdminController.java`, `EmployeeController.java`):
- Uncomment all the `/*` and `*/` blocks

**OR** I can do this for you automatically. Just let me know!

## What Happens After Setup:

1. **Restart the server**
2. **Tables auto-create**: `users`, `leave_requests`, `payroll`
3. **Demo users created**:
   - Admin: `admin@ems.com` / `admin123`
   - Employee: `employee@ems.com` / `emp123`
4. **Access**: `http://localhost:8080/login`

## Verify MySQL is Running:

```bash
# Check MySQL service status
mysql -u root -p
```

If you can login, MySQL is ready!

---

**Ready to enable the full app?** Let me know and I'll uncomment all the code for you!
